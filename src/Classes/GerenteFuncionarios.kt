package Classes

import java.io.File

class GerenteFuncionarios(id: Int,
                          nome: String) {

    fun exibirfuncionarios(caminhoFicheiro: String) {
        val linhas = File(caminhoFicheiro).readLines()
        if (linhas.isNotEmpty()) {
            println("Funcionários:")
            linhas.forEach { linha ->
                val partes = linha.split(",")
                if (partes.size == 4) {
                    val id = partes[0].toIntOrNull() ?: 0
                    val nome = partes[1]
                    val funcao = partes[3].toIntOrNull() ?: 0
                    if (funcao == 1) {
                        println("ID: $id, Nome: $nome, Função: Funcionário")
                    } else if (funcao == 2) {
                        println("ID: $id, Nome: $nome, Função: Gerente de Armazém")
                    } else if (funcao == 3) {
                        println("ID: $id, Nome: $nome, Função: Gerente de Funcionários")
                    } else if (funcao == 4) {
                        println("ID: $id, Nome: $nome, Função: Cliente")
                    }else {
                        println("ID: $id, Nome: $nome, Função: Desconhecida")
                    }
                }
            }
        } else {
            println("Nenhum funcionário encontrado.")
        }
    }

    fun exibirfuncionariosporfuncao(caminhoFicheiro: String, funcao: Int) {
        val linhas = File(caminhoFicheiro).readLines()
        if (linhas.isNotEmpty()) {
            println("Funcionários com função $funcao:")
            linhas.forEach { linha ->
                val partes = linha.split(",")
                if (partes.size == 4) {
                    val id = partes[0].toIntOrNull() ?: 0
                    val nome = partes[1]
                    val funcaoFuncionario = partes[3].toIntOrNull() ?: 0
                    if (funcaoFuncionario == funcao) {
                        println("ID: $id, Nome: $nome")
                    }
                }
            }
        } else {
            println("Nenhum funcionário encontrado.")
        }
    }


    fun removerfuncionario(caminhoFicheiro: String, nomefuncionario: String) {
        val linhas = File(caminhoFicheiro).readLines()
        val vendasFuncionario = linhas.filter { it.split(",")[2] == nomefuncionario }
        if (vendasFuncionario.isNotEmpty()) {
            vendasFuncionario.forEach { linha ->
                val partes = linha.split(",")
                if (partes.size == 5 && partes[2] == nomefuncionario) {
                    val idVenda = partes[0]
                    val nomeCliente = partes[1]
                    val nome = partes[2]
                    val produtosString = partes[3]
                    val valorTotal = partes[4].toDouble()
                    println("ID: $idVenda, Cliente: $nomeCliente, Funcionario: $nome ,Produtos: $produtosString, Valor Total: $valorTotal")
                }
            }
        }
    }
}




