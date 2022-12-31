package strdcoders.mss.clients

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import strdcoders.mss.clients.dto.LibraryMediaInfo
import strdcoders.mss.clients.dto.OrderColumnEnum
import strdcoders.mss.clients.dto.OrderDirectionEnum

@FeignClient(
    name = "tautalli",
    url = "\${mss.clients.tautalli.baseUrl}",
)
interface TautalliClient {
    @GetMapping(params = ["cmd=get_library_media_info"]) // TODO not working
    fun getMediaList(
        @RequestParam("section_id") sectionId: Number,
        @RequestParam("order_column") orderColumn: OrderColumnEnum,
        @RequestParam("order_dir") orderDirection: OrderDirectionEnum,
    ): LibraryMediaInfo
}
