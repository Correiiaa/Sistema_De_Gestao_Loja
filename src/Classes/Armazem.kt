package Classes

class Armazem (var id : Int,
               var produtosArmazem: List<Produto>,
               var IdGerente: Int, ) {

    fun listarProdutos() {
        println("Produtos no Armazém $id:")
        produtosArmazem.forEach { produto ->
            println("ID: ${produto.id}, Nome: ${produto.nome}, Categoria: ${produto.categoria}, Preço: ${produto.preco}, Quantidade: ${produto.quantidadeStock}")
        }
    }
}