package Classes

import java.io.File

class Registo {
    fun registarUtilizador(username: String, senha: String, caminhoFicheiro: String) {
        val senhaEncriptada = Criptografia.encriptar(senha)
        val ficheiro = File(caminhoFicheiro)
        ficheiro.writeText("$username,$senhaEncriptada")
        println("Utilizador registado com sucesso!")
    }
}