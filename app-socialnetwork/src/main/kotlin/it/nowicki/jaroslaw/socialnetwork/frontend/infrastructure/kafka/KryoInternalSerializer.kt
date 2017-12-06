package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.kafka

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output

/**
 * Created by jarek on 05.12.17.
 */
class KryoInternalSerializer : com.esotericsoftware.kryo.Serializer<NotificationMessageReading>() {

    override fun write(kryo: Kryo?, output: Output?, notificationMessageReading: NotificationMessageReading?) {
        output?.writeString(notificationMessageReading?.userId)
        output?.writeString(notificationMessageReading?.notificationType?.name)
//        output?.writeInt(notificationMessageReading?.created.nano)

    }

    override fun read(kryo: Kryo?, input: Input?, type: Class<NotificationMessageReading>?): NotificationMessageReading {
        return NotificationMessageReading()
    }

}