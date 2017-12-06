package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.kafka

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.ByteBufferOutput
import org.apache.kafka.common.serialization.Deserializer
import org.apache.kafka.common.serialization.Serializer
import java.io.Closeable


/**
 * Created by jarek on 05.12.17.
 */
class KryoReadingSerializer : Closeable, AutoCloseable, Serializer<NotificationMessageReading>, Deserializer<NotificationMessageReading> {

    private val kryos = object : ThreadLocal<Kryo>() {
        override fun initialValue(): Kryo {
            val kryo = Kryo()
            kryo.addDefaultSerializer(NotificationMessageReading::class.java, KryoInternalSerializer())
            return kryo
        }
    }

    override fun serialize(topic: String?, data: NotificationMessageReading?): ByteArray {
        val output = ByteBufferOutput(100)
        kryos.get().writeObject(output, sensorReading);
        return output.toBytes();
    }

    override fun deserialize(topic: String?, data: ByteArray?): NotificationMessageReading {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun close() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
