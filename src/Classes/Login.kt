package Classes

import Classes.Utils.selecionarProdutos
import java.io.File
import Classes.Venda

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
            if (partes.size == 4) {
                val id = partes[0].toIntOrNull() ?: 0
                val utilizadorGuardado = partes[1]
                val senhaEncriptada = partes[2]
                val funcao = partes[3].toIntOrNull() ?: 0

                if (username == utilizadorGuardado) {
                    val senhaDesencriptada = Criptografia.desencriptar(senhaEncriptada)
                    if (senha == senhaDesencriptada) {
                        println("Login bem-sucedido!")
                        exibirMenuPorFuncao(funcao, id, utilizadorGuardado)
                        return true
                    }
                }
            }
        }
        println("Nome de utilizador ou senha incorretos.")
        return false
    }

    private fun exibirMenuPorFuncao(funcao: Int, id: Int, utilizadorGuardado: String) {
        when (funcao) {
            1 -> {
                println("Menu do Funcionário:")
                println("1. Ver encomendas")
                println("2. Registrar venda")
                println("3. Sair")

                readLine()?.toIntOrNull()?.let { opcao ->
                    when (opcao) {
                        1 -> {println("Exibindo as encomendas...")
                            val caminhoFicheiro = "src/BaseDados/encomendas.csv"
                            val encomendas = File(caminhoFicheiro).readLines()
                            if (encomendas.isNotEmpty()) {
                                println("Encomendas:")
                                encomendas.forEach { println(it) }
                            } else {
                                println("Nenhuma encomenda encontrada.")
                            }
                        }

                        2 -> {
                            println("Registrando venda...")
                            val caminhoRelatorio = "src/BaseDados/relatoriovendas.csv"
                            val caminhoFicheiro = "src/BaseDados/encomendas.csv"
                            val linhas = File(caminhoFicheiro).readLines()

                            println("Digite o ID da encomenda: ")
                            val idEncomenda = readLine()?.toIntOrNull()

                            if (idEncomenda != null) {
                                val linhaEncomenda = linhas.find { it.split(",")[0].toIntOrNull() == idEncomenda }
                                if (linhaEncomenda != null) {
                                    val partes = linhaEncomenda.split(",")
                                    if (partes.size == 5) {
                                        val idEncomenda = partes[0].toInt()
                                        val cliente = partes[1]
                                        val produtosString = partes[3]
                                        val valorTotal = partes[4].toDouble()

                                        val produtosSelecionados = Utils.criarListaProdutosSelecionados(produtosString, "src/BaseDados/produtos.csv")



                                        produtosSelecionados.forEach {
                                            println("Produto: ${it.first.nome}, Quantidade: ${it.second}, Preço: ${it.first.preco}")
                                        }
                                        // Processar a venda
                                        val clienteObj = Cliente(nome = cliente)
                                        val funcionarioObj = Funcionario(nome = utilizadorGuardado, id = id)
                                        val venda = Venda(
                                            id = idEncomenda,
                                            nomeCliente = cliente,
                                            nomeFuncionario = utilizadorGuardado,
                                            produtosSelecionados = produtosSelecionados,
                                            caminhoFicheiro = caminhoRelatorio,
                                            valorTotal = valorTotal
                                        )
                                        venda.processarVenda(clienteObj, funcionarioObj, caminhoRelatorio)

                                        println("Venda registrada com sucesso!")
                                    }
                                } else {
                                    println("Encomenda com ID $idEncomenda não encontrada.")
                                }
                            } else {
                                println("ID inválido.")
                            }
                        }
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

            4 ->{
                println("=== Menu Cliente ===")
                println("1. Ver produtos")
                println("2. Fazer encomenda")
                println("3. Sair")

                readLine()?.toIntOrNull()?.let { opcao ->
                    when (opcao) {
                        1 -> {
                            println("Exibindo produtos...")
                            val listaprodutos = Utils.testeListaProduto("src/BaseDados/produtos.csv")
                            listaprodutos.forEach { println("ID: ${it.id}, Nome: ${it.nome}, Categoria: ${it.categoria}, Preço: ${it.preco}, Quantidade em Stock: ${it.quantidadeStock}") }
                        }

                        2 -> {println("A preparar a encomenda...")
                            val listaprodutos = Utils.testeListaProduto("src/BaseDados/produtos.csv")
                            val produtosSelecionados = selecionarProdutos(listaprodutos)

                            val nomeCliente = utilizadorGuardado
                            val idCliente = id
                            val cliente = Cliente(nome = nomeCliente, id = idCliente)



                            if (produtosSelecionados.isNotEmpty()) {
                                val caminhoFicheiroEncomendas = "src/BaseDados/encomendas.csv"
                                val encomenda = Encomenda(
                                    nomeCliente = cliente.nome,
                                    idCliente = cliente.id,
                                    produtosSelecionados = produtosSelecionados,
                                    valortotal = 0.0,
                                    caminhoFicheiro = caminhoFicheiroEncomendas
                                )
                                encomenda.processarencomenda(cliente, caminhoFicheiroEncomendas)

                                // Atualizar o histórico de encomendas do cliente
                                cliente.addEncomenda("Encomenda ID: ${encomenda.id}, Produtos: ${produtosSelecionados.map { it.first.nome }}, Total: ${encomenda.valortotal}")

                                println("Encomenda processada com sucesso! ID da encomenda: ${encomenda.id}")
                                println("Produtos selecionados:")
                                produtosSelecionados.forEach { println("Cliente: ${nomeCliente}, Produto: ${it.first.nome}, " +
                                        "Quantidade: ${it.second}") }
                            } else {
                                println("Nenhum produto selecionado para a encomenda.")
                            }
                        }

                        3 -> {
                            println("Saindo...")
                        }

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
