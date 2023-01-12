package strdcoders.mss

import org.springframework.stereotype.Service
import strdcoders.mss.clients.TautalliClient
import strdcoders.mss.clients.dto.OrderDirectionEnum
import strdcoders.mss.clients.dto.tautalli.TautalliApiResult
import strdcoders.mss.clients.dto.tautalli.TautalliLibraryContentDTO
import strdcoders.mss.clients.dto.tautalli.TautalliLibraryDTO

@Service
class TautalliService(
    private val tautalliClient: TautalliClient,
) {
    fun getLibraryMediaInfo(order: OrderDirectionEnum): TautalliLibraryContentDTO {
        val response = tautalliClient.getMediaList(
            1,
            order,
        ).response
        return if (response.result == TautalliApiResult.SUCCESS.tautalliResponse) response.data else throw Exception("Bad request")
    }

    fun getLibraries(): List<TautalliLibraryDTO> = tautalliClient.getLibraries().response.data
}
