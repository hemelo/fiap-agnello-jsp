INSERT INTO produto (id, nome, descricao, preco, imagem_url, tipo, pais, vinicola, safra, classificacao, teor_alcoolico, destaque)
VALUES (1, 'Vinho Tinto Chileno', 'Vinho encorpado com notas de frutas vermelhas.', 89.90, '/vinhos/tinto-chileno.png', 'Tinto', 'Chile', 'Concha y Toro', '2021', 'Seco', '13%', 1);

INSERT INTO produto (id, nome, descricao, preco, imagem_url, tipo, pais, vinicola, safra, classificacao, teor_alcoolico, destaque)
VALUES (2, 'Vinho Branco Argentino', 'Refrescante, ideal para dias quentes.', 69.90, '/vinhos/branco-argentino.png', 'Branco', 'Argentina', 'Norton', '2022', 'Meio-seco', '12%', 0);

INSERT INTO produto (id, nome, descricao, preco, imagem_url, tipo, pais, vinicola, safra, classificacao, teor_alcoolico, destaque)
VALUES (3, 'Espumante Italiano', 'Elegante e ideal para comemorações.', 119.90, '/vinhos/espumante-italiano.png', 'Espumante', 'Itália', 'La Marca', '2020', 'Doce', '11%', 0);

INSERT INTO produto (id, nome, descricao, preco, imagem_url, tipo, pais, vinicola, safra, classificacao, teor_alcoolico, destaque)
VALUES (4, 'Rosé Francês', 'Delicado e frutado, ótimo com petiscos.', 79.90, '/vinhos/rose-frances.png', 'Rosé', 'França', 'Château Minuty', '2021', 'Suave', '12.5%', 0);

INSERT INTO assinatura_plano (nome, descricao_curta, beneficios, preco_mensal, slug, ativo, criado_em, atualizado_em, destaque)
VALUES ('Essencial', 'Ideal para quem deseja explorar novos rótulos sem complicação. Perfeito para harmonizar com o dia a dia.', '<ul class="list-disc pl-5 space-y-1 text-left text-sm text-gray-700">
  <li>2 vinhos selecionados por nossos sommeliers</li>
  <li>Ficha técnica com sugestões de harmonização</li>
  <li>Frete grátis em todas as compras na loja</li>
</ul>', 75.5, 'essencial', true, '2025-04-11 17:51:10', '2025-04-11 17:51:10', 0);

INSERT INTO assinatura_plano (nome, descricao_curta, beneficios, preco_mensal, slug, ativo, criado_em, atualizado_em, destaque)
VALUES ('Reserva Especial', 'Para quem busca rótulos de maior sofisticação e complexidade.', '<ul class="list-disc pl-5 space-y-1 text-left text-sm text-gray-700">
  <li>2 vinhos premiados de safras especiais</li>
  <li>Acesso antecipado a lançamentos da loja</li>
  <li>Frete grátis + brindes exclusivos a cada trimestre</li>
</ul>', 149.9, 'reserva-especial', true, '2025-04-11 17:51:10', '2025-04-11 17:51:10', 1);

INSERT INTO assinatura_plano (nome, descricao_curta, beneficios, preco_mensal, slug, ativo, criado_em, atualizado_em, destaque)
VALUES ('Seleção Sommelier', 'Experiência completa com rótulos únicos e curadoria avançada.', '<ul class="list-disc pl-5 space-y-1 text-left text-sm text-gray-700">
  <li>3 vinhos premium selecionados por sommeliers</li>
  <li>Ficha técnica + vídeo de apresentação exclusivo</li>
  <li>Frete grátis + taça personalizada no 1º mês</li>
</ul>', 219.0, 'selecao-sommelier', true, '2025-04-11 17:51:10', '2025-04-11 17:51:10', 0);


INSERT INTO cupom_desconto (codigo, percentual, validade, ativo, exibir, created_at, updated_at)
VALUES ('BEMVINDO10', 10.0, '2030-12-31', true, true, '2025-04-11 22:00:10', '2025-04-11 22:00:10');
INSERT INTO cupom_desconto (codigo, percentual, validade, ativo, exibir, created_at, updated_at)
VALUES ('FRETEGRATIS', 15.0, '2030-12-31', true, true, '2025-04-11 22:00:10', '2025-04-11 22:00:10');


-- Usuário de teste
INSERT INTO usuario (id, nome, email, senha, role, created_at, updated_at)
VALUES (1, 'Teste', 'teste@vinheria.com', '$2a$10$Xk2qrrNn/EnK9Xa0ZJA98OA7y6ADLMIDe9CQez2BeQuAU5GG1Q8/i', 'CLIENTE', '2025-04-11 23:43:52', '2025-04-11 23:43:52');
-- Senha em BCrypt: "123456"


-- Endereço padrão para o usuário com ID 1
INSERT INTO endereco (id, usuario_id, logradouro, numero, complemento, bairro, cidade, estado, cep, created_at, updated_at)
VALUES (1, 1, 'Rua das Vinhas', '123', 'Apto 202', 'Centro', 'Belo Horizonte', 'MG', '30123-456', '2025-04-12 00:42:41', '2025-04-12 00:42:41');
