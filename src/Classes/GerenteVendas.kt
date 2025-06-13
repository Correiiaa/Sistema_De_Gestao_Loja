package Classes

import java.io.File

class GerenteVendas(
    var id: Int,
    var nome: String,
    var listavendas: MutableList<Produto>
) {
    fun alterarPrecoProduto(idproduto: Int, novoPreco: Double) {
        val caminhoFicheiro = "src/BaseDados/produtos.csv"
        val produtosDoFicheiro = File(caminhoFicheiro).readLines().mapNotNull { linha ->
            val partes = linha.split(",")
            if (partes.size >= 6) {
                Produto(
                    id = partes[0].toIntOrNull() ?: 0,
                    nome = partes[1],
                    categoria = partes[2],
                    preco = partes[3].toDoubleOrNull() ?: 0.0,
                    quantidadeStock = partes[4].toIntOrNull() ?: 0,
                    taxaIva = partes[5].toDoubleOrNull() ?: 0.0
                )
            } else null
        }.toMutableList()
        val produto = produtosDoFicheiro.find { it.id == idproduto }
        if (produto != null) {
            produto.preco = novoPreco
            println("Preço do produto '${produto.id}' alterado para $novoPreco.")

            // Atualizar o ficheiro produtos.csv
            val linhas = File(caminhoFicheiro).readLines().map { it.split(",").toMutableList() }
            for (linha in linhas) {
                if (linha[0].toIntOrNull() == idproduto) {
                    linha[3] = novoPreco.toString()
                }
            }
            val novasLinhas = linhas.map { it.joinToString(",") }
            File(caminhoFicheiro).writeText(novasLinhas.joinToString("\n"))
        } else {
            println("Produto '$idproduto' não encontrado.")
        }
    }

    fun vervendasmes (){
        val caminhoFicheiro = "src/BaseDados/relatoriovendas.csv"
        val linhas = File(caminhoFicheiro).readLines()
        if (linhas.isNotEmpty()) {
            val vendasPorMesAno = mutableMapOf<String, MutableList<String>>()
            linhas.forEach { line ->
                val partes = line.split(",")
                val dataVenda = if (partes.size >= 7) partes.last() else null
                if (dataVenda != null) {
                    val partesData = dataVenda.split("/")
                    if (partesData.size == 3) {
                        val mesAno = "${partesData[1]}/${partesData[2]}"
                        vendasPorMesAno.getOrPut(mesAno) { mutableListOf() }
                            .add(line)
                    }
                }
            }
            if (vendasPorMesAno.isEmpty()) {
                println("Nenhuma venda mensal encontrada.")
            } else {
                vendasPorMesAno.toSortedMap().forEach { (mesAno, vendas) ->
                    println("Mês/Ano: $mesAno")
                    vendas.forEach { venda ->
                        val partes = venda.split(",")
                        val valorComIva = partes.getOrNull(5)?.toDoubleOrNull()
                            ?: partes.getOrNull(6)?.toDoubleOrNull() ?: 0.0
                        val valorSemIva = valorComIva / 1.23
                        val produtos = partes.getOrNull(4) ?: ""
                        val idfuncionario = partes.getOrNull(3) ?: ""
                        val nomefuncionario = partes.getOrNull(2) ?: ""
                        val idcliente = partes.getOrNull(1) ?: ""
                        val idvenda = partes.getOrNull(0) ?: ""
                        val data = partes.last()
                        println(
                            "Venda ID: $idvenda | Cliente: $idcliente | Funcionário: $nomefuncionario | Produtos: $produtos | Valor c/IVA: %.2f | Valor s/IVA: %.2f | Data: %s".format(
                                valorComIva,
                                valorSemIva,
                                data
                            )
                        )
                    }
                    println()
                }
            }
        }
    }

    fun vervendasanuais(){
        println("Exibindo vendas anuais...")
        val caminhoFicheiro = "src/BaseDados/relatoriovendas.csv"
        val linhas = File(caminhoFicheiro).readLines()
        if (linhas.isNotEmpty()) {
            val vendasPorAno = mutableMapOf<String, MutableList<String>>()
            linhas.forEach { line ->
                val partes = line.split(",")
                if (partes.size == 8) {
                    val dataVenda = partes[7]
                    val ano = dataVenda.split("/").lastOrNull() ?: "Desconhecido"
                    vendasPorAno.getOrPut(ano) { mutableListOf() }.add(line)
                }
            }
            if (vendasPorAno.isEmpty()) {
                println("Nenhuma venda anual encontrada.")
            } else {
                vendasPorAno.forEach { (ano, vendas) ->
                    println("Ano: $ano")
                    vendas.forEach { venda ->
                        val partes = venda.split(",")
                        val valorComIva = partes[6].toDoubleOrNull() ?: 0.0
                        val valorSemIva = valorComIva / 1.23
                        val produtos = partes[5]
                        val idfuncionario = partes[4].toIntOrNull() ?: 0
                        val nomefuncionario = partes[3]
                        val idcliente = partes[2].toIntOrNull() ?: 0
                        val nomecliente = partes[1]
                        val idvenda = partes[0].toIntOrNull() ?: 0
                        println("Venda ID: $idvenda | Cliente: $nomecliente | Funcionário: $nomefuncionario | Produtos: $produtos | Valor c/IVA: %.2f | Valor s/IVA: %.2f | Data: %s".format(valorComIva, valorSemIva, partes[7]))
                    }
                    println()
                }
            }
        }
    }

    fun adicionarproduto (){
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

}