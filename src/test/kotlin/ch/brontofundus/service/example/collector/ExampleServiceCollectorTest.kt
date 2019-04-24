package ch.brontofundus.service.example.collector

import ch.brontofundus.service.example.model.ExampleServiceModel
import com.nhaarman.mockito_kotlin.any
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.junit.Test
import org.mockito.Mockito.*

class ExampleServiceCollectorTest {

    @Test
    fun collectInformation() {
        // arrange
        val model = spy(ExampleServiceModel())

        val collector = ExampleServiceCollector(model)

        // act
        collector.collectInformation()

        // assert
        verify(model).updateReleases(any())
        assertThat(model.refreshDate, not(nullValue()))
    }

}
