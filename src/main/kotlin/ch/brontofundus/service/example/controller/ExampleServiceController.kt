package ch.brontofundus.service.example.controller

import ch.brontofundus.service.example.controller.dto.ExampleServiceReleaseDto
import ch.brontofundus.service.example.model.ExampleServiceModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleServiceController(val exampleServiceModel: ExampleServiceModel) {

    @GetMapping("/rest/service/{application}")
    fun currentTimestamp(@PathVariable application : String): List<ExampleServiceReleaseDto> {
        val latestReleaseRows = exampleServiceModel.latestExampleDataRows
        val appReleases = latestReleaseRows.filter { releaseRow -> releaseRow.application == application }

        return appReleases.map { rel -> ExampleServiceReleaseDto(rel.server, rel.application, rel.release, rel.state) }
    }

}
