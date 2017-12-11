package it.nowicki.jaroslaw.socialnetwork.backend.domain.support

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.time.LocalDate

@Component
class FileHelper {


    @Value("\${storage.media}")
    lateinit var storagePlace: String


    fun createDistDirectory(fileName: String): File {
        val currentDate = LocalDate.now()

        val sb = StringBuilder()
        sb.append(storagePlace)
        sb.append(currentDate.year)
        sb.append("/")
        sb.append(currentDate.monthValue)
        sb.append("/")
        sb.append(currentDate.dayOfMonth)
        sb.append("/")


        var file: File? = File(sb.toString())

        file!!.mkdirs()

        sb.append(fileName).append(".storage")

        return File(sb.toString())
    }

    fun readFile(path: String?): String {
        val sb = StringBuilder()

        try {
            BufferedReader(FileReader(storagePlace + path)).use { br ->
                var line: String
                while (br.readLine() != null) {
                    line = br.readLine()
                    sb.append(line)
                }
            }
        } catch (ioEx: IOException) {
            ioEx.printStackTrace()
        }
        return sb.toString()
    }

    fun removeStoragePlace(path: String): String = path.replace(storagePlace, "")

}