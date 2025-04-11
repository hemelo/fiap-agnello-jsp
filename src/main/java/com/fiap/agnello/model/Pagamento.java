package com.fiap.agnello.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String metodo; // Ex: 'PIX', 'CARTAO', 'BOLETO'

    private String status; // Ex: 'PENDENTE', 'APROVADO', 'FALHOU'

    private Double valor;

    @CreationTimestamp
    private LocalDateTime dataPagamento;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
