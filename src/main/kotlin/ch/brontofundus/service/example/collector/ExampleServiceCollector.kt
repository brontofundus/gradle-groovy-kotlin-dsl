package ch.brontofundus.service.example.collector

import ch.brontofundus.service.example.model.ExampleDataRow
import ch.brontofundus.service.example.model.ExampleServiceModel
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ExampleServiceCollector(val exampleServiceModel: ExampleServiceModel) {

    val log = LoggerFactory.getLogger(ExampleServiceCollector::class.java)!!

    @Scheduled(fixedDelay = 60_000, initialDelay = 1000)
    fun collectInformation() {
        log.debug("collecting data...")

        try {
            val releases = listOf<ExampleDataRow>()
            exampleServiceModel.updateReleases(releases)

        } catch (ex: Exception) {
            log.warn("could not read releases!", ex)
        }
    }

}