package Classes
import java.io.File
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

object Criptografia {
    private const val algoritmo = "AES"
    private const val caminhoChave = "src/BaseDados/chave_secreta.key"

    private val chave: SecretKey = carregarOuGerarChave()

    private fun carregarOuGerarChave(): SecretKey {
        val ficheiroChave = File(caminhoChave)
        return if (ficheiroChave.exists()) {
            // Carregar a chave do ficheiro
            val chaveBytes = ficheiroChave.readBytes()
            SecretKeySpec(chaveBytes, algoritmo)
        } else {
            // Gerar uma nova chave e salvar no ficheiro
            val keyGen = KeyGenerator.getInstance(algoritmo)
            keyGen.init(128)
            val novaChave = keyGen.generateKey()
            ficheiroChave.writeBytes(novaChave.encoded)
            novaChave
        }
    }

    fun encriptar(texto: String): String {
        val cipher = Cipher.getInstance(algoritmo)
        cipher.init(Cipher.ENCRYPT_MODE, chave)
        val bytesEncriptados = cipher.doFinal(texto.toByteArray())
        return Base64.getEncoder().encodeToString(bytesEncriptados)
    }

    fun desencriptar(textoEncriptado: String): String {
        val cipher = Cipher.getInstance(algoritmo)
        cipher.init(Cipher.DECRYPT_MODE, chave)
        val bytesDesencriptados = cipher.doFinal(Base64.getDecoder().decode(textoEncriptado))
        return String(bytesDesencriptados)
    }
}