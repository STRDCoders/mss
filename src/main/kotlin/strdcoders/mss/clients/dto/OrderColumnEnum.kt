package strdcoders.mss.clients.dto

enum class OrderColumnEnum(private val clientProp: String) {
    LAST_PLAYED("last_played"),
}

enum class OrderDirectionEnum(private val clientProp: String) {
    DESC("desc"),
    ASC("asc"),
}
