package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.kafka

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output
import it.nowicki.jaroslaw.socialnetwork.backend.domain.notification.NotificationType

class KryoInternalSerializer : com.esotericsoftware.kryo.Serializer<NotificationMessageReading>() {

    override fun write(kryo: Kryo?, output: Output?, notificationMessageReading: NotificationMessageReading?) {
        output?.writeString(notificationMessageReading?.userId)
        output?.writeString(notificationMessageReading?.notificationType?.name)
    }

    override fun read(kryo: Kryo?, input: Input?, type: Class<NotificationMessageReading>?): NotificationMessageReading =
            NotificationMessageReading(input?.readString(), NotificationType.COMMENT_NEW, input?.readLong())


}