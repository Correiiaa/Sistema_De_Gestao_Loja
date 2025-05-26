package Classes
import java.io.File

class Fornecedor(
    var id: Int,
    var nome: String,
    var listaprodutos: List<Produto>
) {
    fun reabastecerProduto(produto: Produto, quantidade: Int, caminhoFicheiro: String) {
        produto.quantidadeStock += quantidade
        println("Fornecedor ${nome} reabasteceu o produto ${produto.nome} com +$quantidade unidades. Estoque atual: ${produto.quantidadeStock}.")
        println("")

        val linhas = File(caminhoFicheiro).readLines().toMutableList()
        val index = linhas.indexOfFirst { it.split(",")[1].toIntOrNull() == produto.id }

        if (index != -1) {
            val partes = linhas[index].split(",").toMutableList()
            partes[4] = produto.quantidadeStock.toString()
            linhas[index] = partes.joinToString(",")
            File(caminhoFicheiro).writeText(linhas.joinToString("\n"))
        } else {
            println("Produto ${produto.nome} não encontrado no ficheiro.")
        }
    }
}
