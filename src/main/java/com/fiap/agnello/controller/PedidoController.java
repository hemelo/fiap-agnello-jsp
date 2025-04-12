package com.fiap.agnello.controller;

import com.fiap.agnello.dto.CarrinhoDto;
import com.fiap.agnello.model.*;
import com.fiap.agnello.security.UsuarioDetails;
import com.fiap.agnello.service.CupomService;
import com.fiap.agnello.service.EnderecoService;
import com.fiap.agnello.service.PedidoService;
import com.fiap.agnello.service.ProdutoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final CarrinhoDto carrinho;
    private final PedidoService pedidoService;
    private final EnderecoService enderecoService;
    private final ProdutoService produtoService;
    private final CupomService cupomService;

    @GetMapping
    public ModelAndView visualizar(@AuthenticationPrincipal UsuarioDetails userDetails,  RedirectAttributes redirectAttributes) {

        ModelAndView mv = new ModelAndView("checkout");

        if (CollectionUtils.isEmpty(carrinho.getItens())) {
            redirectAttributes.addFlashAttribute("erro", "Seu carrinho está vazio.");
            return new ModelAndView("redirect:/produtos");
        }

        List<Endereco> enderecos = null;

        if (userDetails != null) {
            try {
                enderecos = enderecoService.listarPorUsuario(userDetails.getUsuario().getId());
            } catch (Exception ignored) {
                redirectAttributes.addFlashAttribute("erro", "Erro ao buscar endereços.");
                return new ModelAndView("redirect:/produtos");
            }

            if (CollectionUtils.isEmpty(enderecos)) {
                mv.addObject("erro", "Nenhum endereço encontrado. Por favor adicione um endereço em seu perfil.");
            }
        }

        List<Map<String, Object>> itens = carrinho.getItens().stream().map(item -> {
            Produto produto = produtoService.buscarPorId(item.getProdutoId());
            return Map.of("produto", produto, "quantidade", item.getQuantidade());
        }).toList();

        double total = itens.stream().mapToDouble(i ->
                ((Produto)i.get("produto")).getPreco().doubleValue() * (int)i.get("quantidade")).sum();

        if (carrinho.getCupom() != null) {

            CupomDesconto cupom;

            try {
                cupom = cupomService.buscarCupomValidoPorCodigo(carrinho.getCupom());
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("erro", "Erro ao aplicar o cupom: " + e.getMessage());
                return new ModelAndView("redirect:/produtos");
            }

            if (cupom != null) {
                double desconto =  (total * (cupom.getPercentual() / 100.0));
                double subtotal = total;
                total -= desconto;

                mv.addObject("subtotal", subtotal);
                mv.addObject("desconto", desconto);
            }
        }

        mv.addObject("pageTitle", "Checkout");
        mv.addObject("enderecos", enderecos);
        mv.addObject("itens", itens);
        mv.addObject("total", total);

        return mv;
    }

    @GetMapping("/meus")
    public String meusPedidos(Model model, @AuthenticationPrincipal UsuarioDetails userDetails) {
        List<Pedido> pedidos = pedidoService.listarPorUsuario(userDetails.getUsuario().getId());

        model.addAttribute("pedidos", pedidos);
        return "pedidos";
    }

    @PostMapping("/checkout")
    public String finalizarPedido(
            @RequestParam Long enderecoId,
            @AuthenticationPrincipal UsuarioDetails userDetails,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        Pedido pedido;

        try {
            pedido = pedidoService.criarPedido(
                    userDetails.getUsuario().getId(),
                    enderecoId,
                    carrinho.getItens(),
                    carrinho.getCupom()
            );
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao criar o pedido: " + e.getMessage());
            return "redirect:/carrinho";
        }

        session.removeAttribute("carrinho");
        carrinho.limpar();
        redirectAttributes.addFlashAttribute("pedidoId", pedido.getId());

        //return "redirect:/pedidos/" + pedido.getId() + "/pagamento";
        return "redirect:/pedidos/" + pedido.getId() + "/confirmado";
    }

    @GetMapping("/{id}/pagamento")
    public ModelAndView pagamento(@PathVariable Long id) {

        Pedido pedido = pedidoService.buscarPedidoPorId(id);

        ModelAndView mv = new ModelAndView("pagamento");
        mv.addObject("pedido", pedido);
        return mv;
    }

    @GetMapping("/{id}/confirmado")
    public ModelAndView pedidoConfirmado(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("pedido-confirmado");

        Pedido pedido = pedidoService.buscarPedidoPorId(id);
        mv.addObject("pedido", pedido);
        return mv;
    }
}
