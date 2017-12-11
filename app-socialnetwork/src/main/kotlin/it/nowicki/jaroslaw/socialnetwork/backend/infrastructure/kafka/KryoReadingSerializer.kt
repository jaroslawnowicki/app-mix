package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.kafka

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.ByteBufferInput
import org.apache.kafka.common.serialization.Deserializer
import org.apache.kafka.common.serialization.Serializer
import java.io.ByteArrayOutputStream
import java.io.Closeable
import java.io.IOException
import java.io.ObjectOutputStream

class KryoReadingSerializer : Closeable, AutoCloseable, Serializer<Object>, Deserializer<NotificationMessageReading> {

    private val kryos = object : ThreadLocal<Kryo>() {
        override fun initialValue(): Kryo {
            val kryo = Kryo()
            kryo.addDefaultSerializer(NotificationMessageReading::class.java, KryoInternalSerializer())
            return kryo
        }
    }

    override fun serialize(topic: String?, data: Object?): ByteArray {
        try {
            val byteStream = ByteArrayOutputStream()
            val objectStream = ObjectOutputStream(byteStream)
            objectStream.writeObject(data)
            objectStream.flush()
            objectStream.close()
            return byteStream.toByteArray()
        } catch (e: IOException) {
            throw IllegalStateException("Can't serialize object: " + data, e)
        }

    }

    override fun deserialize(topic: String?, data: ByteArray?): NotificationMessageReading {
        try {
            return kryos.get().readObject(ByteBufferInput(data), NotificationMessageReading::class.java)
        }
        catch( e: Exception) {
            throw IllegalArgumentException("Error reading bytes", e)
        }
    }

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {}

    override fun close() {}

}
