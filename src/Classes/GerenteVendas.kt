package Classes

import java.io.File

class GerenteVendas(
    var id: Int,
    var nome: String,
    var listavendas: MutableList<Produto>
) {
    fun alterarPrecoProduto(nomeProduto: String, novoPreco: Double) {
        val produto = listavendas.find { it.nome == nomeProduto }
        if (produto != null) {
            produto.preco = novoPreco
            println("Preço do produto '${produto.nome}' alterado para $novoPreco.")

            // Atualizar o ficheiro produtos.csv
            val caminhoFicheiro = "src/BaseDados/produtos.csv"
            val linhas = File(caminhoFicheiro).readLines().map { it.split(",").toMutableList() }
            for (linha in linhas) {
                if (linha[1] == nomeProduto) {
                    linha[3] = novoPreco.toString()
                }
            }
            val novasLinhas = linhas.map { it.joinToString(",") }
            File(caminhoFicheiro).writeText(novasLinhas.joinToString("\n"))
        } else {
            println("Produto '$nomeProduto' não encontrado.")
        }
    }
}