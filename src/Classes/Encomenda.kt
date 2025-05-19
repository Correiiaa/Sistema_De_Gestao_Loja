package Classes

import java.io.File

class Encomenda(
    var id: Int = 0,
    var nomeCliente: String,
    var idCliente: Int,
    var produtosSelecionados: List<Pair<Produto, Int>>,
    var valortotal: Double,
    var caminhoFicheiro: String
) {

    //gerar ids
    init {
        id = gerarId(caminhoFicheiro)
    }

    companion object {
        fun gerarId(caminhoFicheiro: String): Int {
            val ficheiro = File(caminhoFicheiro)
            if (ficheiro.exists() && ficheiro.readLines().isNotEmpty()) {
                val ultimaLinha = ficheiro.readLines().last()
                val ultimoId = ultimaLinha.split(",").firstOrNull()?.toIntOrNull() ?: 0
                return ultimoId + 1
            }
            return 1
        }
    }


    fun processarencomenda(nomeCliente: Cliente, caminhoFicheiro: String) {
        produtosSelecionados.forEach {(produto, quantidade) ->
            valortotal += produto.preco * quantidade }

        val encomenda = "$id,${nomeCliente.nome},${idCliente},${produtosSelecionados.joinToString(";") 
        { "${it.first.nome}:${it.second}" }},$valortotal\n"
        File(caminhoFicheiro).appendText(encomenda)
}}
