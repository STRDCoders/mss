package strdcoders.mss

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
// TODO Delete
@Controller
class controller(private val tautalliService: TautalliService) {

    @GetMapping("/")
    fun bob() {
        tautalliService.run()
    }
}
