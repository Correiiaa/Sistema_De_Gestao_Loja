package Classes

import java.io.File

class Produto (var id: Int,
               var nome: String,
               var categoria: String,
               var preco: Double,
               var quantidadeStock: Int) {

    fun atualizarStock(quantidadeVendida: Int) {
        if (quantidadeVendida <= quantidadeStock) {
            quantidadeStock -= quantidadeVendida
        } else {
            println("Quantidade vendida maior que o stock disponível de $nome.")
        }
    }


}