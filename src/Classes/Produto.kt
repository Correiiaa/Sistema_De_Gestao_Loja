package Classes

import java.io.File

class Produto (var id: Int,
               var nome: String,
               var categoria: String,
               var preco: Double,
               var quantidadeStock: Int,
               var taxaIva: Double) {

    fun atualizarStock(quantidadeVendida: Int) {
        if (quantidadeVendida <= quantidadeStock) {
            quantidadeStock -= quantidadeVendida
            val caminho = "src/BaseDados/produtos.csv"
            val linhas = File(caminho).readLines().map { it.split(",").toMutableList() }
            for (linha in linhas) {
                if (linha[0].toInt() == id) {
                    linha[4] = quantidadeStock.toString()

                    val novasLinhas = linhas.map { it.joinToString(",") }
                    File(caminho).writeText(novasLinhas.joinToString("\n"))

                }
            }

        } else {
            println("Quantidade vendida maior que o stock disponível de $nome.")
        }
    }


}