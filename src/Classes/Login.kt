package Classes

import java.io.File

class Login {
    fun autenticar(username: String, senha: String, caminhoFicheiro: String): Boolean {
    val ficheiro = File(caminhoFicheiro)
    if (!ficheiro.exists()) {
        println("Ficheiro de utilizadores não encontrado.")
        return false
    }

    val (utilizadorGuardado, senhaEncriptada) = ficheiro.readText().split(",")
    if (username == utilizadorGuardado) {
        val senhaDesencriptada = Criptografia.desencriptar(senhaEncriptada)
        if (senha == senhaDesencriptada) {
            println("Login bem-sucedido!")
            return true
        }
    }
    println("Credenciais inválidas.")
    return false
}
}