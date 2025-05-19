package Classes

import java.io.File

class Registo {
    fun registarUtilizador(username: String, senha: String, funcao: Int, caminhoFicheiro: String) {
        val senhaEncriptada = Criptografia.encriptar(senha)
        val ficheiro = File(caminhoFicheiro)
        val id = if (ficheiro.exists() && ficheiro.readLines().isNotEmpty()) {
            val ultimaLinha = ficheiro.readLines().last()
            val ultimoId = ultimaLinha.split(",").firstOrNull()?.toIntOrNull() ?: 0
            ultimoId + 1
        } else {
            1
        }
        ficheiro.appendText("$id,$username,$senhaEncriptada,$funcao\n")
        println("Utilizador registado com sucesso!")

    }
}