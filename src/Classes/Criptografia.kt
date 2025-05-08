package Classes
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

object Criptografia {
    private val algoritmo = "AES"
    private val chave: SecretKey = KeyGenerator.getInstance(algoritmo).generateKey()

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