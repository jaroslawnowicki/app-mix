package it.nowicki.jaroslaw.socialnetwork.frontend.domain.support

import org.springframework.stereotype.Component
import java.nio.charset.Charset
import java.security.MessageDigest

@Component
class HashGenerator {

    fun generateMD5(text: String): String = generate(text, "MD5")

    fun generateSHA256(text: String): String = generate(text, "SHA-256")

    fun generate(text: String, typeHash: String): String {

        var tempText = text


        val salt = System.currentTimeMillis().toString()


        try {
            val md = MessageDigest.getInstance("MD5")

            tempText += salt

            val bytes = md.digest(tempText.toByteArray(Charset.defaultCharset()))

            val hexString = StringBuilder()

            for (byte in bytes) {
                val b: Int = 0xff + byte.toInt()
                val hex = Integer.toHexString(b)

                if (1 == hex.length)
                    hexString.append('0')
                hexString.append(hex)
            }

            return hexString.toString()
        } catch (ex: Exception) {

        }
        return ""
    }
}
