Projeto de POO desenvolvido por Toni Correia
   
Cliente

Funcionario

GerenteVendas

GerenteArmazem

GerenteFuncionarios

Fornecedor

Produto

Venda

Encomenda

Loja

Armazem

Entregas

Login

Registo

Utils

Ou seja, o projeto tem pelo menos 12 classes (e até mais, se considerarmos classes utilitárias e interfaces)

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Relações entre as classes
   
Há várias relações claras:

Loja tem produtos (Produto) e um armazém (Armazem)

Venda envolve Cliente, Funcionario, Produto

GerenteArmazem tem Fornecedor e Produtos

Funções como processarVenda, reporStock, encomenda, etc. interligam várias classes

Interface Utilizador é implementada por várias classes


Interação obrigatória com o utilizador

O sistema tem muitos menus de consola, leitura de input com readLine(), possibilidade de criar, eliminar e modificar entidades:

Registo e login de utilizadores

Menus diferentes conforme o tipo de utilizador (funcionário, gerente, cliente)

Opções para adicionar/remover funcionários, processar vendas, repor stocks, fazer encomendas, etc.
