package Classes

import java.io.File
import kotlin.collections.get
import kotlin.collections.plusAssign
import kotlin.collections.set
import kotlin.toString

class Armazem (
    var id: Int,
    var produtosArmazem: List<Produto>,
    ) {

//    fun listarProdutos() {
//        println("Produtos no Armazém $id:")
//        produtosArmazem.forEach { produto ->
//            println("ID: ${produto.id}, Nome: ${produto.nome}, Categoria: ${produto.categoria}, Preço: ${produto.preco}, Quantidade: ${produto.quantidadeStock}")
//        }
//    }


    fun reporStock(produtoId: Int, quantidadeReposta: Int, armazemId: Int) {

        val produto = produtosArmazem.find { it.id == produtoId }
        val caminhoProdutos = "src/BaseDados/produtos.csv"
        val caminhoArmazem = "src/BaseDados/stockArmazem.csv"

        if (produto != null) {
            val linhasArmazem = File(caminhoArmazem).readLines().map { it.split(",").toMutableList() }
            val linhaArmazem = linhasArmazem.find { it[0].toIntOrNull() == armazemId && it[1].toIntOrNull() == produtoId }

            if (linhaArmazem != null) {
                val quantidadeArmazem = linhaArmazem[4].toIntOrNull() ?: 0
                if (quantidadeArmazem >= quantidadeReposta) {
                    // Atualizar stock no armazém
                    linhaArmazem[4] = (quantidadeArmazem - quantidadeReposta).toString()
                    File(caminhoArmazem).writeText(linhasArmazem.joinToString("\n") { it.joinToString(",") })

                    // Atualizar stock do produto
                    val linhasProdutos = File(caminhoProdutos).readLines().map { it.split(",").toMutableList() }
                    linhasProdutos.forEach { linha ->
                        if (linha[0].toIntOrNull() == produtoId) {
                            val stockAtual = linha[4].toIntOrNull() ?: 0
                            linha[4] = (stockAtual + quantidadeReposta).toString()
                        }
                    }
                    File(caminhoProdutos).writeText(linhasProdutos.joinToString("\n") { it.joinToString(",") })

                    println("Stock atualizado: $quantidadeReposta unidades transferidas do armazém $armazemId para o produto.")
                } else {
                    println("Quantidade insuficiente no armazém para repor o stock.")
                }
            } else {
                println("Produto com ID $produtoId não encontrado no armazém $armazemId.")
            }
        } else {
            println("Produto com ID $produtoId não encontrado na lista de produtos do armazém.")
        }
    }
}