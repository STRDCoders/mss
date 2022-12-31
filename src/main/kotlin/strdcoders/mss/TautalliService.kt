package strdcoders.mss

import org.springframework.stereotype.Service
import strdcoders.mss.clients.TautalliClient
import strdcoders.mss.clients.dto.OrderColumnEnum
import strdcoders.mss.clients.dto.OrderDirectionEnum

@Service
class TautalliService(
    private val tautalliClient: TautalliClient,
) {

    fun run() {
        tautalliClient.getMediaList(1, OrderColumnEnum.LAST_PLAYED, OrderDirectionEnum.ASC)
        println("hey")
    }
}
