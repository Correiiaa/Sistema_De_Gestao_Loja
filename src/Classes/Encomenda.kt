package Classes

import java.io.File

class Encomenda(var id: Int = gerarId(), var nomeCliente: String, var produtosSelecionados: List<Pair<Produto, Int>>, var valortotal: Double, var caminhoFicheiro: String) {

    companion object {
        private var ultimoId = 0
        fun gerarId(): Int {
            ultimoId++
            return ultimoId
        }
    }

    fun processarencomenda(nomeCliente: Cliente, caminhoFicheiro: String) {
        produtosSelecionados.forEach {(produto, quantidade) ->
            valortotal += produto.preco * quantidade }

        val encomenda = "$id,${nomeCliente.nome},${produtosSelecionados.joinToString(",") 
        { "${it.first.nome}:${it.second}" }},$valortotal\n"
        File(caminhoFicheiro).appendText(encomenda)
}}
