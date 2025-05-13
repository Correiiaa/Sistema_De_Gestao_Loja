package Classes
import java.io.File

class Venda  (var id: Int,
              var nomeCliente: String,
              var nomeFuncionario: String,
              var produtosSelecionados: List<Pair<Produto, Int>>,
              var valorTotal: Double) {

    fun processarVenda(cliente: Cliente, funcionario: Funcionario, ficheiroRelatorio: String) {
        produtosSelecionados.forEach {(produto, quantidade) ->
            produto.atualizarStock(quantidade)
            valorTotal += produto.preco * quantidade }

        cliente.addCompra("Venda ID: $id, Funcionario: ${funcionario.nome} ${funcionario.id}, Produtos: ${produtosSelecionados.map { it.first.nome} } ${produtosSelecionados.map { it.second }}")
        funcionario.addVenda("Venda ID: $id, Cliente: ${cliente.nome} ${cliente.id}, Produtos: ${produtosSelecionados.map { it.first.nome} } ${produtosSelecionados.map { it.second }}")

        val relatorio = "$id,$nomeCliente,$nomeFuncionario,${produtosSelecionados.joinToString(";") { "${it.first.nome}:${it.second}" }},$valorTotal\n"
        File(ficheiroRelatorio).appendText(relatorio)
    }

}
