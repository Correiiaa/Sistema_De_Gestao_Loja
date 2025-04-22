import java.io.File
import Classes.*
import kotlin.io.println

fun main() {
    val cliente1 = Cliente(1, "Manuel", "manuel231@gmail.com")
    val funcionario1 = Funcionario(1, "Tiago", "tiago123", "senha123")
//
//    val produto1 = Produto(1, "Produto A", "teste", 10.0, 100)
//    val produto2 = Produto(2, "Produto B", "teste", 20.0, 50)
//
//    val venda1 = Venda(1,
//        cliente1.id,
//        funcionario1.id,
//        listOf(Pair
//            (produto1, 2),
//            Pair(produto2, 3)),
//            0.0)
//
//    venda1.processarVenda(cliente1, funcionario1, "src/BaseDados/relatoriovendas.csv")
//
//    println("Cliente: ${cliente1.nome}, Historico de Compras: ${cliente1.historicoCompras}")
//    println("Funcionario: ${funcionario1.nome}, Historico de Vendas: ${funcionario1.historicoVendas}")
//
//    println("Estoque Atualizados dos Produtos: Produto A: ${produto1.quantidadeStock}, Produto B: ${produto2.quantidadeStock}")


    // Função para ler produtos de um arquivo CSV
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

    val caminhoCSV = "src/BaseDados/produtos.csv"
    val listaprodutos = testeListaProduto(caminhoCSV)



    // Função para selecionar produtos
    fun selecionarProdutos(listaprodutos: List<Produto>): List<Pair<Produto, Int>> {
        val produtosSelecionados = mutableListOf<Pair<Produto, Int>>()

        while(true){
            println("Lista de produtos:")
            listaprodutos.forEach { println("ID: ${it.id}, Nome: ${it.nome}, Categoria: ${it.categoria}, Preço: ${it.preco}, Quantidade em Stock: ${it.quantidadeStock}") }

            println("Digite o ID do preoduto")
            val idProduto = readLine()?.toIntOrNull() ?: 0
            if(idProduto == 0) break

            val produto = listaprodutos.find { it.id == idProduto }
            if (produto != null) {
                println("Digite a quantidade que deseja")
                val quantidade = readLine()?.toIntOrNull() ?: 0
                if (quantidade <= produto.quantidadeStock ){
                    produtosSelecionados.add(Pair(produto, quantidade))
                }
                else{
                    println("Quantidade maior que o stock disponível de ${produto.nome}.")
                }

            }

            else {
                println("Produto não encontrado.")
            }

        }
        return produtosSelecionados
    }


    val produtosSelecionados = selecionarProdutos(listaprodutos)
    println("Produtos selecionados:")
    produtosSelecionados.forEach { println("Produto: ${it.first.nome}, Quantidade: ${it.second}") }

    val venda1 = Venda(1, 1, 1, produtosSelecionados, 0.0)
    venda1.processarVenda(cliente1, funcionario1, "src/BaseDados/relatoriovendas.csv")
}