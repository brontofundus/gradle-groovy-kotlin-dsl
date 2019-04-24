package ch.brontofundus.service.example.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ExampleServiceReleaseDto(
        @JsonProperty val server: String,
        @JsonProperty val application: String,
        @JsonProperty val release: String,
        @JsonProperty val state: String
)
