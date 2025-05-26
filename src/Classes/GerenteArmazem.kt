package Classes

class GerenteArmazem(
    var id: Int,
    var nome: String,
    var produtosArmazem: MutableList<Produto>,
    var fornecedor: Fornecedor
) : Utilizador {

    override fun exibirMenu() {
        println("=== Menu Gerente de Armazém ===")
        println("1. Ver estoque do armazém")
        println("2. Sair")
    }

    fun verificarEstoque() {
        produtosArmazem.forEach { produto ->
            if (produto.quantidadeStock < 20) {
                println("Produto ${produto.nome} com estoque baixo (${produto.quantidadeStock}). Solicitando reabastecimento...")
                fornecedor.reabastecerProduto(produto, 50)
            }
        }
    }
}