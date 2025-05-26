package Classes

class Fornecedor(
    var id: Int,
    var nome: String,
    var listaprodutos: List<Produto>
) {
    fun reabastecerProduto(produto: Produto, quantidade: Int) {
        produto.quantidadeStock += quantidade
        println("Fornecedor ${nome} reabasteceu o produto ${produto.nome} com +$quantidade unidades. Estoque atual: ${produto.quantidadeStock}.")
    }
}
