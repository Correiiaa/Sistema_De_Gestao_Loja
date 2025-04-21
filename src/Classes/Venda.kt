package Classes

class Venda  (var id: Int,
              var idCliente: Int,
              var idFuncionario: Int,
              var listaProdutos: List<Pair<Produto, Int>>,
              var valorTotal: Double) {

    fun processarVenda(cliente: Cliente, funcionario: Funcionario) {
        listaProdutos.forEach {(produto, quantidade) ->
            produto.atualizarStock(quantidade)
            valorTotal += produto.preco * quantidade }

        cliente.addCompra("Venda ID: $id, Funcionario/ID: ${funcionario.nome} ${funcionario.id}, Produtos: ${listaProdutos.map { it.first.nome} } ${listaProdutos.map { it.second }}")
        funcionario.addVenda("Venda ID: $id, Cliente/ID: ${cliente.nome} ${cliente.id}, Produtos: ${listaProdutos.map { it.first.nome} } ${listaProdutos.map { it.second }}")
        }

    }

