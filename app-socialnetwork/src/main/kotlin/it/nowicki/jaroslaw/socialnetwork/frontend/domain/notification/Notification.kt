package it.nowicki.jaroslaw.socialnetwork.frontend.domain.notification

import java.time.LocalDateTime

data class Notification(var userId: String?, var type: NotificationType?, var created: LocalDateTime?) : java.io.Serializable