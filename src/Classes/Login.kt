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
        while (true) {
            when (funcao) {
                1 -> {
                    println("Menu do Funcionário:")
                    println("1. Ver encomendas")
                    println("2. Registrar venda")
                    println("3. Sair")

                    readLine()?.toIntOrNull()?.let { opcao ->
                        when (opcao) {
                            1 -> {
                                println("Exibindo as encomendas...")
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
                                        if (partes.size == 7) {
                                            val idEncomenda = partes[0].toInt()
                                            val cliente = partes[1]
                                            val idcliente = partes[2].toInt()
                                            val produtosString = partes[3]
                                            val valorTotal = partes[4].toDouble()


                                            val produtosSelecionados = Utils.criarListaProdutosSelecionados(
                                                produtosString,
                                                "src/BaseDados/produtos.csv"
                                            )



                                            produtosSelecionados.forEach {
                                                println("Produto: ${it.first.nome}, Quantidade: ${it.second}, Preço: ${it.first.preco}")
                                            }
                                            // Processar a venda
                                            val clienteObj = Cliente(nome = cliente)
                                            val funcionarioObj = Funcionario(nome = utilizadorGuardado, id = id)
                                            val venda = Venda(
                                                id = idEncomenda,
                                                nomeCliente = cliente,
                                                idCliente = idcliente,
                                                nomeFuncionario = utilizadorGuardado,
                                                idFuncionario = id,
                                                produtosSelecionados = produtosSelecionados,
                                                caminhoFicheiro = caminhoRelatorio,
                                                valorTotal = valorTotal,
                                                data = Utils.getDataAtual()
                                            )
                                            venda.processarVenda(clienteObj, funcionarioObj, caminhoRelatorio)
                                            val encomendasAtualizadas =
                                                linhas.filterNot { it.split(",")[0].toIntOrNull() == idEncomenda }
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

                            3 -> {
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
                                val produtosArmazem = linhas.filter { linha ->
                                    val partes = linha.split(",")
                                    partes.size == 5 && partes[0].toIntOrNull() == idArmazem
                                }

                                if (produtosArmazem.isNotEmpty()) {
                                    println("Produtos no armazém $idArmazem:")
                                    val produtosFormatados = produtosArmazem.map { linha ->
                                        val partes = linha.split(",")
                                        val idproduto = partes[1].toInt()
                                        val nomeProduto = partes[2]
                                        val produtoCategoria = partes[3]
                                        val quantidadeTotal = partes[4].toInt()
                                        produtosarmazemstock.add(
                                            Produto(
                                                idproduto,
                                                nomeProduto,
                                                produtoCategoria,
                                                0.0,
                                                quantidadeTotal,
                                                23.00
                                            )
                                        )
                                        "ID: $idproduto, Nome: $nomeProduto, Categoria: $produtoCategoria, Quantidade em Stock: $quantidadeTotal"
                                    }
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

                                            val produto =
                                                Produto(idproduto, nomeProduto, produtoCategoria, 0.0, quantidadeTotal, 23.00)
                                            produtosarmazemstock.add(produto)
                                        }
                                    }

                                    println("Digite o ID do produto que quer repor: ")
                                    val produtoId: Int = readLine()?.toIntOrNull() ?: 0
                                    println("Digite a quantidade que quer repor: ")
                                    val quantidadeRepor: Int = readLine()?.toIntOrNull() ?: 0
                                    val reporstock =
                                        Armazem(id = idArmazem ?: 0, produtosArmazem = produtosarmazemstock)
                                    reporstock.reporStock(produtoId, quantidadeRepor, idArmazem ?: 0)
                                } else {
                                    println("Armazém com ID $idArmazem não encontrado ou sem produtos.")
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
                                    val linhasAtualizadas =
                                        linhas.filterNot { it.split(",")[0].toIntOrNull() == idFuncionario }
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


                            4 -> {
                                println("Saindo...")
                                return
                            }

                            else -> println("Opção inválida.")

                        }
                    }
                }

                4 -> {
                    println("=== Menu Cliente ===")
                    println("1. Ver produtos")
                    println("2. Fazer encomenda")
                    println("3. Exibir histórico de compras")
                    println("4. Sair")

                    readLine()?.toIntOrNull()?.let { opcao ->
                        when (opcao) {
                            1 -> {
                                println("Exibindo produtos...")
                                val listaprodutos = Utils.testeListaProduto("src/BaseDados/produtos.csv")
                                listaprodutos.forEach { println("ID: ${it.id}, Nome: ${it.nome}, Categoria: ${it.categoria}, Preço: ${it.preco}, Quantidade em Stock: ${it.quantidadeStock}, Iva: ${it.taxaIva}") }
                            }

                            2 -> {
                                println("A preparar a encomenda...")
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
                                        dadosEntrega = dados,
                                        data = Utils.getDataAtual()
                                    )
                                    encomenda.processarencomenda(cliente, caminhoFicheiroEncomendas)

                                    // Atualizar o histórico de encomendas do cliente
                                    cliente.addEncomenda("Encomenda ID: ${encomenda.id}, Produtos: ${produtosSelecionados.map { it.first.nome }}, Total: ${encomenda.valortotal}")

                                    println("Encomenda processada com sucesso! ID da encomenda: ${encomenda.id}")
                                    println("Produtos selecionados:")
                                    produtosSelecionados.forEach {
                                        println(
                                            "Cliente: ${nomeCliente}, Produto: ${it.first.nome}, " +
                                                    "Quantidade: ${it.second}"
                                        )
                                    }
                                } else {
                                    println("Nenhum produto selecionado para a encomenda.")
                                }
                            }

                            3 -> {
                                println("Exibindo histórico de compras...")
                                val caminhoFicheiro = "src/BaseDados/relatoriovendas.csv"
                                val cliente = Cliente(nome = utilizadorGuardado, id = id)
                                val linhas = File(caminhoFicheiro).readLines()
                                val comprasCliente = linhas.filter { it.split(",").size > 1 && it.split(",")[1] == cliente.nome }
                                if (comprasCliente.isNotEmpty()) {
                                    println("Histórico de compras do cliente ${cliente.nome} com IVA de 23% : ")
                                    comprasCliente.forEach { println(it) }
                                } else {
                                    println("Nenhuma compra encontrada para o cliente ${cliente.nome}.")
                                }
                            }

                            4 -> {
                                println("Saindo...")
                                return
                            }

                            else -> println("Opção inválida.")
                        }
                    }
                }

                5 -> {
                    println("Menu do Gerente de vendas/imposto:")
                    println("1. Ver/Adicionar/Remover produtos")
                    println("2. Ver vendas")
                    println("3. Alterar preço de um produto")
                    println("4. Sair")

                    readLine()?.toIntOrNull()?.let { opcao ->
                        when (opcao) {
                            1 -> {
                                println("2. Exibir lista de produtos")
                                println("3. Adicionar produto")
                                println("4. Remover produto")

                                readLine()?.toIntOrNull()?.let { subOpcao ->
                                    when (subOpcao) {
                                        2 -> {
                                            println("Exibindo lista de produtos...")
                                            val listaprodutos = Utils.testeListaProduto("src/BaseDados/produtos.csv")
                                            listaprodutos.forEach {
                                                println("ID: ${it.id}, Nome: ${it.nome}, Categoria: ${it.categoria}, Preço: ${it.preco}, Quantidade em Stock: ${it.quantidadeStock}, Iva: ${it.taxaIva}")
                                            }
                                        }

                                        3 -> {
                                            println("Adicionar novo produto:")
                                            val linhasProdutos = File("src/BaseDados/produtos.csv").readLines()
                                            val novoId = if (linhasProdutos.isNotEmpty()) {
                                                val ultimaLinha = linhasProdutos.last()
                                                ultimaLinha.split(",").firstOrNull()?.toIntOrNull()?.plus(1) ?: 1
                                            } else {
                                                1
                                            }
                                            print("Nome: ")
                                            val novoNome = readLine().orEmpty()
                                            print("Categoria: ")
                                            val novaCategoria = readLine().orEmpty()
                                            print("Preço: ")
                                            val novoPreco = readLine()?.toDoubleOrNull() ?: 0.0
                                            print("Quantidade em Stock: ")
                                            val novaQuantidade = readLine()?.toIntOrNull() ?: 0
                                            print("IVA: ")
                                            val novoIva = readLine()?.toDoubleOrNull() ?: 0.0

                                            val caminhoProdutos = "src/BaseDados/produtos.csv"
                                            val caminhoArmazem = "src/BaseDados/stockArmazem.csv"
                                            val armazem1 = "${1},$novoId,$novoNome,$novaCategoria,${0}"
                                            val armazem2 = "${2},$novoId,$novoNome,$novaCategoria,${0}"
                                            val novoProduto = "$novoId,$novoNome,$novaCategoria,$novoPreco,$novaQuantidade,$novoIva"
                                            File(caminhoProdutos).appendText("\n$novoProduto")
                                            File(caminhoArmazem).appendText("\n$armazem1")
                                            File(caminhoArmazem).appendText("\n$armazem2")
                                            println("Produto adicionado com sucesso!")
                                        }

                                        4 -> {
                                            println("Remover produto:")
                                            print("Digite o ID do produto a remover: ")
                                            val idRemover = readLine()?.toIntOrNull()
                                            val caminhoProdutos = "src/BaseDados/produtos.csv"
                                            val linhas = File(caminhoProdutos).readLines()
                                            val linhasAtualizadas = linhas.filterNot { it.split(",")[0].toIntOrNull() == idRemover }
                                            File(caminhoProdutos).writeText(linhasAtualizadas.joinToString("\n"))
                                            println("Produto removido com sucesso!")
                                        }

                                        else -> println("Opção inválida.")
                                    }
                                }
                            }

                            2 -> {
                                println("Exibindo vendas...")
                                val caminhoFicheiro = "src/BaseDados/relatoriovendas.csv"
                                val linhas = File(caminhoFicheiro).readLines()
                                if (linhas.isNotEmpty()) {
                                    println("Vendas:")
                                    linhas.forEach { venda ->
                                        val partes = venda.split(",")
                                        if (partes.size >= 5) {
                                            val valorComIva = partes[4].toDoubleOrNull() ?: 0.0
                                            val valorSemIva = valorComIva / 1.23
                                            println("$venda | Preço sem IVA: %.2f".format(valorSemIva))
                                        } else {
                                            println(venda)
                                        }
                                    }
                                    println()
                                } else {
                                    println("Nenhuma venda encontrada.")
                                }
                            }

                            3 -> {
                                println("Digite o id do produto que deseja alterar o preço: ")
                                val idProduto = readLine()?.toIntOrNull() ?: 0
                                println("Digite o novo preço: ")
                                val novoPreco = readLine()?.toDoubleOrNull()
                                val listaprodutos = mutableListOf<Produto>()
                                val gerentevendas = GerenteVendas(id = id, nome = utilizadorGuardado, listavendas = listaprodutos)
                                gerentevendas.alterarPrecoProduto(idProduto, novoPreco ?: 0.0)
                            }

                            4 -> {
                                println("Saindo...")
                                return
                            }

                            else -> println("Opção inválida.")
                        }
                    }
                }
            }
        }
    }
}



