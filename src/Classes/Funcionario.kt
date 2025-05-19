package Classes
import java.io.File

class Funcionario (var id: Int,
                   var nome: String,
                   var historicoVendas: MutableList<String> = mutableListOf() ) : Utilizador {

    fun addVenda(detalhesVenda: String){
        historicoVendas.add(detalhesVenda)
    }

    override fun exibirMenu() {
        println("=== Menu Funcionário ===")
        println("1. Processar vendas")
        println("2. Sair")
    }

}