package Classes

class GerenteLoja(override val username: String,
                  override val senha: String,
                  var id: Int,
                  var nome: String) : Utilizador {

    override fun exibirMenu() {
        println("=== Menu Gerente de Loja ===")
        println("1. Ver vendas da loja")
        println("2. Sair")
    }
}