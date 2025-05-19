package Classes
import java.io.File

class Venda  (var id: Int = 0,
              var nomeCliente: String,
              var nomeFuncionario: String,
              var produtosSelecionados: List<Pair<Produto, Int>>,
              var caminhoFicheiro: String,
              var valorTotal: Double) {

    fun processarVenda(cliente: Cliente, funcionario: Funcionario, ficheiroRelatorio: String) {
        produtosSelecionados.forEach {(produto, quantidade) ->
            produto.atualizarStock(quantidade)
            valorTotal += produto.preco * quantidade }

        cliente.addEncomenda("Emcomenda ID: $id, Funcionario: ${funcionario.nome} ${funcionario.id}, Produtos: ${produtosSelecionados.map { it.first.nome} } ${produtosSelecionados.map { it.second }}")
        funcionario.addVenda("Venda ID: $id, Cliente: ${cliente.nome} ${cliente.id}, Produtos: ${produtosSelecionados.map { it.first.nome} } ${produtosSelecionados.map { it.second }}")

        val relatorio = "$id,$nomeCliente,$nomeFuncionario,${produtosSelecionados.joinToString(";") { "${it.first.nome}:${it.second}" }},$valorTotal\n"
        File(ficheiroRelatorio).appendText(relatorio)
    }

    init {
        id = Encomenda.Companion.gerarId(caminhoFicheiro)
    }

    companion object {
        fun gerarId(caminhoFicheiro: String): Int {
            val ficheiro = File(caminhoFicheiro)
            if (ficheiro.exists() && ficheiro.readLines().isNotEmpty()) {
                val ultimaLinha = ficheiro.readLines().last()
                val ultimoId = ultimaLinha.split(",").firstOrNull()?.toIntOrNull() ?: 0
                return ultimoId + 1
            }
            return 1
        }
    }

}
