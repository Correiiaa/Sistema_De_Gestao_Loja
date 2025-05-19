package Classes

class GerenteVendas(var id: Int,
                    var nome: String) : Utilizador {

    override fun exibirMenu() {
        println("=== Menu Funcionário ===")
        println("1. Ver vendas")
        println("2. Sair")
    }
}