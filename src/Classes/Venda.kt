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

        cliente.addCompra("Venda ID: $id, Funcionario: ${funcionario.nome}, Produtos: ${listaProdutos}")
        funcionario.addVenda("Venda ID: $id, Cliente: ${cliente.nome}, Produtos: ${listaProdutos}")
        }

    }

