insert into cliente (cpf, nome, email, telefone, logradouro, bairro, cidade, uf, cep) values
('123.456.789-00', 'João Silva', 'joao.silva@example.com', '(11) 98765-4321', 'Rua A', 'Bairro B', 'Cidade C', 'SP', '12345-678'),
('987.654.321-00', 'Maria Oliveira', 'maria.oliveira@example.com', '(21) 87654-3210', 'Avenida X', 'Bairro Y', 'Cidade Z', 'RJ', '87654-321'),
('456.789.123-00', 'Carlos Souza', 'carlos.souza@example.com', '(31) 76543-2109', 'Travessa Q', 'Bairro W', 'Cidade V', 'MG', '65432-109');

insert into categoria (nome) values
('Eletrônicos'),
('Móveis'),
('Eletrodomésticos');

insert into produto (nome, preco, descricao) values
('Smartphone', 1500.00, 'Smartphone com 4GB de RAM e 128GB de armazenamento'),
('Sofá', 1200.00, 'Sofá de 3 lugares com tecido impermeável'),
('Geladeira', 2500.00, 'Geladeira frost-free com 380 litros de capacidade');

insert into categoria_produto (categoria_id, produto_id) values
(1, 1),
(2, 2),
(3, 3);