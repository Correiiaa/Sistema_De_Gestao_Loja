package Classes

fun main() {
    val cliente1 = Cliente(1, "Manuel", "manuel231@gmail.com")
    val funcionario1 = Funcionario(1, "Tiago", "tiago123", "senha123")

    val produto1 = Produto(1, "Produto A", 10.0, 100)
    val produto2 = Produto(2, "Produto B", 20.0, 50)

    val venda1 = Venda(1,
        cliente1.id,
        funcionario1.id,
        listOf(Pair
            (produto1, 2),
            Pair(produto2, 3)),
            0.0)

    venda1.processarVenda(cliente1, funcionario1)

    println("Cliente: ${cliente1.nome}, Historico de Compras: ${cliente1.historicoCompras}")
    println("Funcionario: ${funcionario1.nome}, Historico de Vendas: ${funcionario1.historicoVendas}")

    println("Estoque Atualizados dos Produtos: Produto A: ${produto1.quantidadeStock}, Produto B: ${produto2.quantidadeStock}")

}