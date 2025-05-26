package Classes

class Entregas(val id: Int,
    val funcionario: Funcionario,
    val encomenda: Encomenda) {

    fun entrega(){
        println("A encomenda ${encomenda.id} foi entregue por ${funcionario.nome}.")
    }



}

