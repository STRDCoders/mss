package strdcoders.mss.clients.dto

enum class OrderDirectionEnum(private val clientDescription: String) {
    DESC("desc"),
    ASC("asc"), ;

    override fun toString() = clientDescription
}
