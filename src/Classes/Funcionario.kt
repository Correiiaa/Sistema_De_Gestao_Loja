package Classes
import java.io.File

class Funcionario (var id: Int,
                   var nome: String,
                   var historicoVendas: MutableList<String> = mutableListOf() ){

    fun addVenda(detalhesVenda: String){
        historicoVendas.add(detalhesVenda)
    }

}