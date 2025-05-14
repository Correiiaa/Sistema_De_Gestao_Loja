package Classes

class Cliente (var id: Int = gerarId(),
               var nome: String,
               var historicoEncomendas: MutableList<String> = mutableListOf() ) {


    companion object {
        private var ultimoId = 0
        fun gerarId(): Int {
            ultimoId++
            return ultimoId
        }
    }

    fun addEncomenda(detalhesCompra: String){
        historicoEncomendas.add(detalhesCompra)
    }

}