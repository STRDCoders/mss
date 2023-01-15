package strdcoders.mss.clients

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import strdcoders.mss.clients.dto.OrderDirectionEnum
import strdcoders.mss.clients.dto.tautalli.TautalliLibraryContentDTO
import strdcoders.mss.clients.dto.tautalli.TautalliLibraryDTO
import strdcoders.mss.clients.dto.tautalli.TautalliResponseDTO

@FeignClient(
    name = "tautalli",
)
interface TautalliClient {
    @GetMapping("?cmd=\${tautalli.api.getlibraryMediainfo}&order_column=last_played")
    fun getMediaList(
        @RequestParam("section_id") sectionId: Number,
        @RequestParam("order_dir") orderDirection: OrderDirectionEnum,
    ): TautalliResponseDTO<TautalliLibraryContentDTO>

    @GetMapping("?cmd=\${tautalli.api.getLibraries}")
    fun getLibraries(): TautalliResponseDTO<List<TautalliLibraryDTO>>
}
