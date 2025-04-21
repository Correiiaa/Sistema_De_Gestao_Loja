package Classes

class Cliente (var id: Int,
               var nome: String,
               var email: String,
               var historicoCompras: MutableList<String> = mutableListOf() ) {

    fun addCompra(detalhesCompra: String){
        historicoCompras.add(detalhesCompra)
    }

}