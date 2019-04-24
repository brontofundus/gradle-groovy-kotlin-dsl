package ch.brontofundus.service.example.controller

import ch.brontofundus.service.example.model.ExampleServiceModel
import org.junit.Before
import org.junit.Test
import org.springframework.mock.env.MockEnvironment

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup



class IndexControllerTest {

    var  mockMvc: MockMvc? = null

    @Before
    fun setUp() {
        val model = ExampleServiceModel()
        val mockEnv = MockEnvironment()
        this.mockMvc = standaloneSetup(IndexController("0.0.0", "J", model, mockEnv)).build()
    }

    @Test
    fun index() {
        this.mockMvc!!.perform(get("/")).andExpect(status().isOk)
    }

}
