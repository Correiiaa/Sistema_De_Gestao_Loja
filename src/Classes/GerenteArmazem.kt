package Classes

class GerenteArmazem(var id: Int,
                     var nome: String) : Utilizador {

    override fun exibirMenu() {
        println("=== Menu Gerente de Armazém ===")
        println("1. Ver estoque do armazém")
        println("2. Sair")
    }
}