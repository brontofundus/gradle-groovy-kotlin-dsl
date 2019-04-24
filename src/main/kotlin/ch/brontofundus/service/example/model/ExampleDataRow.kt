package ch.brontofundus.service.example.model

data class ExampleDataRow(val server: String = "",
                          val application: String = "",
                          val release: String = "",
                          val state: String = "successful"
)
