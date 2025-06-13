# Sistema de Gestão de Vendas e Estoque

## Descrição do Projeto
Este projeto foi desenvolvido por **Toni Correia** para a disciplina de **Programação Orientada a Objetos (POO)**. O objetivo é criar um sistema completo de gestão de vendas e estoque, com interação direta com o utilizador, permitindo criar, deletar e modificar entidades do sistema.

## Funcionalidades
O sistema oferece as seguintes funcionalidades:
- **Gestão de Produtos**: Adicionar, remover e alterar preços.
- **Gestão de Vendas**: Registrar vendas e gerar relatórios.
- **Gestão de Estoque**: Verificar e repor o estoque.
- **Gestão de Encomendas**: Criar e processar encomendas.
- **Gestão de Utilizadores**: Registo e autenticação de utilizadores com diferentes funções (funcionário, gerente, cliente).
- **Relatórios**: Exibição de vendas anuais, mensais e histórico de compras.

## Estrutura do Projeto
O projeto é composto por **15 classes principais**, organizadas para garantir modularidade e reutilização de código:

### Classes Principais
1. **Cliente**: Representa os clientes do sistema.
2. **Funcionario**: Representa os funcionários que realizam vendas e interagem com encomendas.
3. **GerenteVendas**: Gerencia vendas e preços de produtos.
4. **GerenteArmazem**: Gerencia o estoque e reposição de produtos.
5. **GerenteFuncionarios**: Gerencia os funcionários do sistema.
6. **Fornecedor**: Representa os fornecedores que abastecem o armazém.
7. **Produto**: Representa os produtos disponíveis no sistema.
8. **Venda**: Processa e registra vendas realizadas.
9. **Encomenda**: Gerencia encomendas feitas pelos clientes.
10. **Loja**: Representa a loja e seus produtos.
11. **Armazem**: Gerencia o estoque do armazém.
12. **Entregas**: Gerencia entregas de encomendas.
13. **Login**: Autentica utilizadores no sistema.
14. **Registo**: Registra novos utilizadores.
15. **Utils**: Contém métodos utilitários para operações diversas.



## Relações entre as Classes
As classes possuem relações bem definidas:
- **Loja** contém produtos (`Produto`) e está ligada ao armazém (`Armazem`).
- **Venda** envolve `Cliente`, `Funcionario` e `Produto`.
- **GerenteArmazem** trabalha com `Fornecedor` e `Produto` para repor estoque.
- **Encomenda** conecta `Cliente` e `Produto`, gerenciando entregas.
- **Login** e **Registo** gerenciam autenticação e criação de utilizadores.

## Interação com o Utilizador
O sistema utiliza menus interativos para interação com o utilizador:
- **Registo e Login**: Permite autenticar utilizadores e acessar menus personalizados.
- **Menus Dinâmicos**: Cada tipo de utilizador (funcionário, gerente, cliente) tem opções específicas.
- **Operações em Tempo Real**: O utilizador pode criar, modificar e deletar entidades como produtos, vendas e encomendas.

## Tecnologias Utilizadas
- **Linguagem**: Kotlin
- **IDE**: IntelliJ IDEA
- **Entrada e Saída**: Interação via consola com `readLine()`.

## Como Executar
1. Clone o repositório.
2. Abra o projeto no IntelliJ IDEA.
3. Execute o arquivo principal para iniciar o sistema.
4. Navegue pelos menus e explore as funcionalidades.

## Autor
**Toni Correia**  
Disciplina: **Programação Orientada a Objetos (POO)**  
Ano: **2025**
