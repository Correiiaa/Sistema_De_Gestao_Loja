package Classes

import java.io.File

class GerenteVendas(
    var id: Int,
    var nome: String,
    var listavendas: MutableList<Produto>
) {
    fun alterarPrecoProduto(idproduto: Int, novoPreco: Double) {
        val caminhoFicheiro = "src/BaseDados/produtos.csv"
        val produtosDoFicheiro = File(caminhoFicheiro).readLines().mapNotNull { linha ->
            val partes = linha.split(",")
            if (partes.size >= 6) {
                Produto(
                    id = partes[0].toIntOrNull() ?: 0,
                    nome = partes[1],
                    categoria = partes[2],
                    preco = partes[3].toDoubleOrNull() ?: 0.0,
                    quantidadeStock = partes[4].toIntOrNull() ?: 0,
                    taxaIva = partes[5].toDoubleOrNull() ?: 0.0
                )
            } else null
        }.toMutableList()
        val produto = produtosDoFicheiro.find { it.id == idproduto }
        if (produto != null) {
            produto.preco = novoPreco
            println("Preço do produto '${produto.id}' alterado para $novoPreco.")

            // Atualizar o ficheiro produtos.csv
            val linhas = File(caminhoFicheiro).readLines().map { it.split(",").toMutableList() }
            for (linha in linhas) {
                if (linha[0].toIntOrNull() == idproduto) {
                    linha[3] = novoPreco.toString()
                }
            }
            val novasLinhas = linhas.map { it.joinToString(",") }
            File(caminhoFicheiro).writeText(novasLinhas.joinToString("\n"))
        } else {
            println("Produto '$idproduto' não encontrado.")
        }
    }
}