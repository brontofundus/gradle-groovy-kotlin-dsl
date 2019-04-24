package ch.brontofundus.service.example.controller


import ch.brontofundus.service.example.model.ExampleDataRow
import ch.brontofundus.service.example.model.ExampleServiceModel
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders


class ExampleServiceControllerTest {

    var  mockMvc: MockMvc? = null

    @Before
    fun setUp() {
        val model = ExampleServiceModel()
        model.updateReleases(listOf(ExampleDataRow("server", "app", "rel")))
        this.mockMvc = MockMvcBuilders.standaloneSetup(ExampleServiceController(model)).build()
    }

    @Test
    fun shouldReturnMatchingApplikation() {
        this.mockMvc!!.perform(get("/rest/service/app"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$..server", not(isEmptyString())))
                .andExpect(jsonPath("$..application", not(isEmptyString())))
                .andExpect(jsonPath("$..release", not(isEmptyString())))
                .andExpect(jsonPath("$..state", not(isEmptyString())))
    }

    @Test
    fun shouldReturnNothingWithNoMatchingApplikation() {
        this.mockMvc!!.perform(get("/rest/service/appNotKnown"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$..server", emptyCollectionOf(String::class.java)))
                .andExpect(jsonPath("$..application", emptyCollectionOf(String::class.java)))
                .andExpect(jsonPath("$..release", emptyCollectionOf(String::class.java)))
                .andExpect(jsonPath("$..state", emptyCollectionOf(String::class.java)))
    }

}
