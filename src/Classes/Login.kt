package Classes

import java.io.File

class Login {
    fun autenticar(username: String, senha: String, caminhoFicheiro: String): Boolean {
        val ficheiro = File(caminhoFicheiro)
        if (!ficheiro.exists()) {
            println("Ficheiro de utilizadores não encontrado.")
            return false
        }

        val linhas = ficheiro.readLines()
        for (linha in linhas) {
            val partes = linha.split(",")
            if (partes.size == 3) {
                val utilizadorGuardado = partes[0]
                val senhaEncriptada = partes[1]
                val funcao = partes[2].toIntOrNull() ?: 0

                if (username == utilizadorGuardado) {
                    val senhaDesencriptada = Criptografia.desencriptar(senhaEncriptada)
                    if (senha == senhaDesencriptada) {
                        println("Login bem-sucedido!")
                        exibirMenuPorFuncao(funcao)
                        return true
                    }
                }
            }
        }
        println("Nome de utilizador ou senha incorretos.")
        return false
    }

    private fun exibirMenuPorFuncao(funcao: Int) {
        when (funcao) {
            1 -> {
                println("Menu do Funcionário:")
                println("1. Ver tarefas")
                println("2. Registrar venda")
                println("3. Sair")

                readLine()?.toIntOrNull()?.let { opcao ->
                    when (opcao) {
                        1 -> println("Exibindo tarefas...")
                        2 -> println("Registrando venda...")
                        3 -> while(true) {
                            println("Saindo...")
                            break}
                        else -> println("Opção inválida.")
                    }
                }
            }
            2 -> {
                println("Menu do Gerente de Armazém:")
                println("1. Ver estoque")
                println("2. Adicionar produtos")
                println("3. Sair")

                readLine()?.toIntOrNull()?.let { opcao ->
                    when (opcao) {
                        1 -> println("Exibindo estoque...")
                        2 -> println("Adicionando produtos...")
                        3 -> while(true) {
                            println("Saindo...")
                            break}
                        else -> println("Opção inválida.")
                    }
                }
            }
            3 -> {
                println("Menu do Gerente de Funcionários:")
                println("1. Ver lista de funcionários")
                println("2. Remover funcionário")
                println("3. Sair")

                readLine()?.toIntOrNull()?.let { opcao ->
                    when (opcao) {
                        1 -> println("Exibindo lista de funcionários...")
                        2 -> println("Removendo funcionário...")
                        3 -> while(true) {
                            println("Saindo...")
                        break}
                        else -> println("Opção inválida.")

                    }
                }
            }
            else -> {
                println("Função desconhecida. Contate o administrador.")
            }
        }
    }
}
