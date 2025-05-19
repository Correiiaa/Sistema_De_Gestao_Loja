package Classes

import java.io.File

object Utils {
    fun testeListaProduto(caminho: String): List<Produto> {
        val produtos = mutableListOf<Produto>()
        val linhas = File(caminho).readLines()

        for (linha in linhas) {
            val partes = linha.split(",")

            if (partes.size == 5) {
                val id = partes[0].toInt()
                val nome = partes[1]
                val categoria = partes[2]
                val preco = partes[3].toDouble()
                val quantidade = partes[4].toInt()

                val produto = Produto(id, nome, categoria, preco, quantidade)
                produtos.add(produto)
            }
        }
        return produtos
    }

    fun selecionarProdutos(listaprodutos: List<Produto>): List<Pair<Produto, Int>> {
        val produtosSelecionados = mutableListOf<Pair<Produto, Int>>()

        while (true) {
            println("Lista de produtos:")
            listaprodutos.forEach { println("ID: ${it.id}, Nome: ${it.nome}, Categoria: ${it.categoria}, Preço: ${it.preco}, Quantidade em Stock: ${it.quantidadeStock}") }

            println("Digite o ID do preoduto")
            val idProduto = readLine()?.toIntOrNull() ?: 0
            if (idProduto == 0) break

            val produto = listaprodutos.find { it.id == idProduto }
            if (produto != null) {
                println("Digite a quantidade que deseja")
                val quantidade = readLine()?.toIntOrNull() ?: 0
                if (quantidade <= produto.quantidadeStock) {
                    produtosSelecionados.add(Pair(produto, quantidade))
                } else {
                    println("Quantidade maior que o stock disponível de ${produto.nome}.")
                }

            } else {
                println("Produto não encontrado.")
            }

        }
        return produtosSelecionados
    }

    fun criarListaProdutosSelecionados(produtosString: String, caminhoProdutos: String): List<Pair<Produto, Int>> {
        val produtosCSV = File(caminhoProdutos).readLines()
        val produtosSelecionados = mutableListOf<Pair<Produto, Int>>()

        produtosString.split(";").forEach { produtoInfo ->
            val detalhes = produtoInfo.split(":")
            val nomeProduto = detalhes[0]
            val quantidade = detalhes[1].toInt()

            // Search for the product in produtos.csv
            val linhaProduto = produtosCSV.find { it.split(",")[1] == nomeProduto }
            if (linhaProduto != null) {
                val partes = linhaProduto.split(",")
                val id = partes[0].toInt()
                val categoria = partes[2]
                val preco = partes[3].toDouble()
                val quantidadeStock = partes[4].toInt()

                val produto = Produto(id, nomeProduto, categoria, preco, quantidadeStock)
                produtosSelecionados.add(produto to quantidade)
            } else {
                println("Produto '$nomeProduto' não encontrado no ficheiro produtos.csv.")
            }
        }

        return produtosSelecionados
    }
}