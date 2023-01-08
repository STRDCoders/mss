package strdcoders.mss

import org.springframework.stereotype.Service
import strdcoders.mss.clients.TautalliClient
import strdcoders.mss.clients.dto.OrderColumnEnum
import strdcoders.mss.clients.dto.OrderDirectionEnum
import strdcoders.mss.configs.FeignApplicationProp

@Service
class TautalliService(
    private val tautalliClient: TautalliClient,
    private val applicationProp: FeignApplicationProp,
) {

    fun run() {
        tautalliClient.getMediaList(1, OrderColumnEnum.LAST_PLAYED, OrderDirectionEnum.ASC, applicationProp.defaultQueryParameters.apiKey, Constants.Cmd.GET_MEDIA_LIST)
        println("hey")
    }
}
