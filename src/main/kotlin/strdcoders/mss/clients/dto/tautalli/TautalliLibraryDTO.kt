package strdcoders.mss.clients.dto.tautalli

data class TautalliLibraryDTO(
    val section_id: String,
    val section_name: String,
    val section_type: String,
    val agent: String,
    val thumb: String,
    val art: String,
    val count: String,
    val is_active: Int,
)
