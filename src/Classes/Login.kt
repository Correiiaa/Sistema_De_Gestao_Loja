package Classes

import Classes.Utils.selecionarProdutos
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
        while (true){
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
                                    println()
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
                                            val dados = partes[5]

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
                                            val encomendasAtualizadas = linhas.filterNot { it.split(",")[0].toIntOrNull() == idEncomenda }
                                            File(caminhoFicheiro).writeText(encomendasAtualizadas.joinToString("\n"))



                                            println("Venda registrada com sucesso e relatorio de encomendas atualizado!")
                                        }
                                    } else {
                                        println("Encomenda com ID $idEncomenda não encontrada.")
                                    }
                                } else {
                                    println("ID inválido.")
                                }
                            }
                            3 ->{
                                println("Saindo...")
                                return
                            }

                            else -> println("Opção inválida.")
                        }
                    }
                }

                2 -> {
                    println("Menu do Gerente de Armazém:")
                    println("1. Ver estoque/repor armazém")
                    println("2. Repor Stock da Loja")
                    println("3. Sair")

                    readLine()?.toIntOrNull()?.let { opcao ->
                        when (opcao) {
                            1 -> {
//                                println("Digite o ID do armazem: ")
//                                val idArmazem = readLine()?.toIntOrNull()
//                                val caminhoFicheiro = "src/BaseDados/stockArmazem.csv"
//                                val produtosarmazemstock = mutableListOf<Produto>()
//                                val linhas = File(caminhoFicheiro).readLines()
//                                val produtosArmazem = linhas.filter { it.split(",")[0].toIntOrNull() == idArmazem }
//                                if (produtosArmazem.isNotEmpty()) {
//                                    println("Produtos no armazém $idArmazem:")
//                                    produtosArmazem.forEach { linha ->
//                                        val partes = linha.split(",")
//                                        if (partes.size == 5 && partes[0].toIntOrNull() == idArmazem) {
//                                            val idArmazem = partes[0].toInt()
//                                            val idproduto = partes[1].toInt()
//                                            val nomeProduto = partes[2]
//                                            val produtoCategoria = partes[3]
//                                            val quantidadeTotal = partes[4].toInt()
//
//
//                                            println("ID: $idproduto, Nome: $nomeProduto, Categoria: $produtoCategoria, Quantidade em Stock: $quantidadeTotal")
//
//                                            val produto = Produto(idproduto, nomeProduto, produtoCategoria, 0.0, quantidadeTotal)
//                                            produtosarmazemstock.add(produto)
//                                            val fornecedor = Fornecedor(1, "Rogério", produtosarmazemstock)
//                                            val gerente = GerenteArmazem(id, utilizadorGuardado, produtosarmazemstock, fornecedor)
//                                            gerente.verificarEstoque()
//
//
//                                        }
//
//                                    }
//                                } else {
//                                    println("Armazém com ID $idArmazem não encontrado ou sem produtos.")
//                                }
                                println("Digite o ID do armazem: ")
                                val idArmazem = readLine()?.toIntOrNull()
                                val caminhoFicheiro = "src/BaseDados/stockArmazem.csv"
                                val produtosarmazemstock = mutableListOf<Produto>()
                                val linhas = File(caminhoFicheiro).readLines()
                                val produtosArmazem = linhas.filter { it.split(",")[0].toIntOrNull() == idArmazem }
                                if (produtosArmazem.isNotEmpty()) {
                                    println("Produtos no armazém $idArmazem:")
                                    val produtosFormatados = produtosArmazem.map { linha ->
                                        val partes = linha.split(",")
                                        if (partes.size == 5 && partes[0].toIntOrNull() == idArmazem) {
                                            val idproduto = partes[1].toInt()
                                            val nomeProduto = partes[2]
                                            val produtoCategoria = partes[3]
                                            val quantidadeTotal = partes[4].toInt()
                                            produtosarmazemstock.add(Produto(idproduto, nomeProduto, produtoCategoria, 0.0, quantidadeTotal))
                                            "ID: $idproduto, Nome: $nomeProduto, Categoria: $produtoCategoria, Quantidade em Stock: $quantidadeTotal"
                                        } else null
                                    }.filterNotNull()
                                    println(produtosFormatados.joinToString("\n"))
                                    val fornecedor = Fornecedor(1, "Rogério", produtosarmazemstock)
                                    val gerente = GerenteArmazem(id, utilizadorGuardado, produtosarmazemstock, fornecedor)
                                    gerente.verificarEstoque()
                                } else {
                                    println("Armazém com ID $idArmazem não encontrado ou sem produtos.")
                                }

                            }

                            2 -> {
                                println("Digite o ID do armazem: ")
                                val idArmazem = readLine()?.toIntOrNull()
                                val caminhoFicheiro = "src/BaseDados/stockArmazem.csv"
                                val produtosarmazemstock = mutableListOf<Produto>()
                                val linhas = File(caminhoFicheiro).readLines()
                                val produtosArmazem = linhas.filter { it.split(",")[0].toIntOrNull() == idArmazem }
                                if (produtosArmazem.isNotEmpty()) {
                                    println("Produtos no armazém $idArmazem:")
                                    produtosArmazem.forEach { linha ->
                                        val partes = linha.split(",")
                                        if (partes.size == 5 && partes[0].toIntOrNull() == idArmazem) {
                                            val idproduto = partes[1].toInt()
                                            val nomeProduto = partes[2]
                                            val produtoCategoria = partes[3]
                                            val quantidadeTotal = partes[4].toInt()
                                            println("ID: $idproduto, Nome: $nomeProduto, Categoria: $produtoCategoria, Quantidade em Stock: $quantidadeTotal")

                                            val produto = Produto(idproduto, nomeProduto, produtoCategoria, 0.0, quantidadeTotal)
                                            produtosarmazemstock.add(produto)
                                        }
                                    }

                                    println("Digite o ID do produto que quer repor: ")
                                    val produtoId: Int = readLine()?.toIntOrNull() ?: 0
                                    println("Digite a quantidade que quer repor: ")
                                    val quantidadeRepor: Int = readLine()?.toIntOrNull() ?: 0
                                    val reporstock = Armazem(id = idArmazem ?: 0, produtosArmazem = produtosarmazemstock)
                                    reporstock.reporStock(produtoId, quantidadeRepor, idArmazem ?: 0)
                                } else {
                                    println("Armazém com ID $idArmazem não encontrado ou sem produtos.")
                                }

                            }

                            3 ->{
                                println("Saindo...")
                                return
                            }

                            else -> println("Opção inválida.")
                        }
                    }
                }
                3 -> {
                    println("Menu do Gerente de funcionarios:")
                    println("1. Ver lista de utilizadores")
                    println("2. Remover utilizador")
                    println("3. Ver vendas de funcionario")
                    println("4. Sair")

                    readLine()?.toIntOrNull()?.let { opcao ->
                        when (opcao) {
                            1 -> {
                                println("Exibindo lista de utilizadores...")
                                val caminhoFicheiro = "src/BaseDados/autenticacao.csv"
                                val gerentefuncionarios = GerenteFuncionarios(id = id, nome = utilizadorGuardado)
                                gerentefuncionarios.exibirfuncionarios(caminhoFicheiro)


                            }
                            2 -> {
                                println("Removendo funcionário...")
                                val caminhoFicheiro = "src/BaseDados/autenticacao.csv"
                                println("Digite a funcao do funcionario a remover: ")
                                val funcao = readLine()?.toIntOrNull()
                                val gerentefuncionarios = GerenteFuncionarios(id = id, nome = utilizadorGuardado)
                                val linhas = File(caminhoFicheiro).readLines()
                                gerentefuncionarios.exibirfuncionariosporfuncao(caminhoFicheiro, funcao ?: 0)
                                println("Digite o ID do funcionário a remover: ")
                                val idFuncionario = readLine()?.toIntOrNull()
                                if (idFuncionario != null) {
                                    val linhasAtualizadas = linhas.filterNot { it.split(",")[0].toIntOrNull() == idFuncionario }
                                    File(caminhoFicheiro).writeText(linhasAtualizadas.joinToString("\n"))
                                    println("Funcionário com ID $idFuncionario removido com sucesso.")
                                } else {
                                    println("ID inválido.")
                                }

                            }

                            3 -> {

                                println("Exibindo vendas recentes...")
                                val caminhoFicheiro = "src/BaseDados/relatoriovendas.csv"
                                println("Digite o nome do funcionario: ")
                                val nomefuncionario = readLine().toString()
                                val gerentefuncionarios = GerenteFuncionarios(id = id, nome = utilizadorGuardado)
                                gerentefuncionarios.removerfuncionario(caminhoFicheiro, nomefuncionario)
                            }


                            4 ->{
                                println("Saindo...")
                                return
                            }

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
                                println("Digite a sua morada: ")
                                val dados = readLine().toString()
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
                                        caminhoFicheiro = caminhoFicheiroEncomendas,
                                        dadosEntrega = dados
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
                                return
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
}

