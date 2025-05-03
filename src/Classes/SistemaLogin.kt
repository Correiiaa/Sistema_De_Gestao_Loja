package Classes

class SistemaLogin {
    private val usuarios = listOf<Utilizador>(
        GerenteArmazem("gerenteArmazem", "1234"),
        GerenteLoja("gerenteLoja", "abcd"),
        Funcionario("funcionario,""123")
    )

    fun login() {
        println("=== Sistema de Login ===")
        print("Username: ")
        val username = readLine() ?: ""
        print("Senha: ")
        val senha = readLine() ?: ""

        val usuario = usuarios.find { it.username == username && it.senha == senha }

        if (usuario != null) {
            println("Login bem-sucedido! Bem-vindo, $username.")
            usuario.exibirMenu()
        } else {
            println("Credenciais inválidas. Tente novamente.")
        }
    }
}