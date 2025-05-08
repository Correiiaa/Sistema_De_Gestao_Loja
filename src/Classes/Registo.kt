package Classes

import java.io.File

class Registo {
    fun registarUtilizador(username: String, senha: String, funcao: Int, caminhoFicheiro: String) {
        val senhaEncriptada = Criptografia.encriptar(senha)
        val ficheiro = File(caminhoFicheiro)
        ficheiro.appendText("$username,$senhaEncriptada,$funcao\n")
        println("Utilizador registado com sucesso!")
    }
}