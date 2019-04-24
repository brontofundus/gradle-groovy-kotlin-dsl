package ch.brontofundus.service.example.model

import org.springframework.stereotype.Component
import java.util.*

@Component
class ExampleServiceModel {

    var latestExampleDataRows : List<ExampleDataRow> = listOf()

    var refreshDate: Date? = null

    fun updateReleases(exampleDataRows: List<ExampleDataRow>) {
        latestExampleDataRows = exampleDataRows
        refreshDate = Date()
    }

}
