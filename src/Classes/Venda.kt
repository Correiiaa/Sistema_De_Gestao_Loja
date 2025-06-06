package Classes
import java.io.File

class Venda  (var id: Int = 0,
              var nomeCliente: String,
              var idCliente: Int,
              var nomeFuncionario: String,
              var idFuncionario: Int,
              var produtosSelecionados: List<Pair<Produto, Int>>,
              var caminhoFicheiro: String,
              var valorTotal: Double,
              var data: String) {

    fun processarVenda(cliente: Cliente, funcionario: Funcionario, ficheiroRelatorio: String) {
        valorTotal = 0.0 // Inicializa o valor total

        produtosSelecionados.forEach { (produto, quantidade) ->
            produto.atualizarStock(quantidade) // Atualiza o stock do produto
            valorTotal += produto.preco * quantidade // Calcula o valor total da venda
        }

        // Adiciona a encomenda ao cliente
        cliente.addEncomenda("Encomenda ID: $id, Funcionario: ${funcionario.nome} ${funcionario.id}, Produtos: ${produtosSelecionados.map { it.first.nome }}, Quantidades: ${produtosSelecionados.map { it.second }}")

        // Adiciona a venda ao funcionário
        funcionario.addVenda("Venda ID: $id, Cliente: ${cliente.nome} ${cliente.id}, Produtos: ${produtosSelecionados.map { it.first.nome }}, Quantidades: ${produtosSelecionados.map { it.second }}")

        // Formata o relatório para ser escrito no ficheiro
        val relatorio = "$id,$nomeCliente,$idCliente,$nomeFuncionario,$idFuncionario,${produtosSelecionados.joinToString(";") { "${it.first.nome}:${it.first.preco}:${it.second}" }},$valorTotal,$data\n"

        // Escreve o relatório no ficheiro
        File(ficheiroRelatorio).appendText(relatorio)
    }

    init {
        id = gerarId(caminhoFicheiro)
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
