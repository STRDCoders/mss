package strdcoders.mss.clients.dto.tautalli

data class TautalliResponseWrapperDTO<T>(
    val data: T,
    val result: String,
)

data class TautalliResponseDTO<T>(
    val response: TautalliResponseWrapperDTO<T>,
)

enum class TautalliApiResult(val tautalliResponse: String) {
    SUCCESS("success"),
    ERROR("error"),
}
