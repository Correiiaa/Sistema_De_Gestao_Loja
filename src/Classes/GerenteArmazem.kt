package Classes

class GerenteArmazem(
    var id: Int,
    var nome: String,
    var produtosArmazem: MutableList<Produto>,
    var fornecedor: Fornecedor
) {



    fun verificarEstoque() {
        produtosArmazem.forEach { produto ->
            if (produto.quantidadeStock < 20) {
                println("Produto ${produto.nome} com estoque baixo (${produto.quantidadeStock}).")
                println("Se quiser repor o produto digite 1")
                val input = readLine().toString()
                if (input == "1"){
                    fornecedor.reabastecerProduto(produto, 50, "src/BaseDados/stockArmazem.csv")
                }
                else{
                    return
                }
            }
        }
    }
}