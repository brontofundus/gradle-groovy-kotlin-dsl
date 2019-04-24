package ch.brontofundus.service.example.controller

import ch.brontofundus.service.example.model.ExampleServiceModel
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringBootVersion
import org.springframework.core.env.Environment
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@Controller
class IndexController(
        @Value("\${example.service.version}") val appVersion: String,
        @Value("\${service.environment}") val env: String,
        val exampleServiceModel: ExampleServiceModel,
        val environment: Environment) {

    @RequestMapping("/")
    fun index(model: Model): String {
        model.addAttribute("refreshDate", exampleServiceModel.refreshDate)
        model.addAttribute("bootVersion", SpringBootVersion.getVersion())
        model.addAttribute("applicationVersion", appVersion)
        model.addAttribute("environment", env)
        model.addAttribute("activeProfiles", Arrays.toString(environment.activeProfiles))

        return "index"
    }

}
