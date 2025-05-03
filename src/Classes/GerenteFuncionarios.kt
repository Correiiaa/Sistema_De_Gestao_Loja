package Classes

class GerenteFuncionarios(override val username: String, override val senha: String) : Utilizador {

    override fun exibirMenu() {
        println("=== Menu Funcionário ===")
        println("1. Ver funcionarios")
        println("2. Sair")
    }
}