import Classes.Produto
import java.io.File
//package Classes

fun main() {
//    val cliente1 = Cliente(1, "Manuel", "manuel231@gmail.com")
//    val funcionario1 = Funcionario(1, "Tiago", "tiago123", "senha123")
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
//    venda1.processarVenda(cliente1, funcionario1)
//
//    println("Cliente: ${cliente1.nome}, Historico de Compras: ${cliente1.historicoCompras}")
//    println("Funcionario: ${funcionario1.nome}, Historico de Vendas: ${funcionario1.historicoVendas}")
//
//    println("Estoque Atualizados dos Produtos: Produto A: ${produto1.quantidadeStock}, Produto B: ${produto2.quantidadeStock}")


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

    println("Lista de Produtos:")
    listaprodutos.forEach { println("ID: ${it.id}, Nome: ${it.nome}, Categoria: ${it.categoria}, Preço: ${it.preco}, Quantidade em Stock: ${it.quantidadeStock}") }


}