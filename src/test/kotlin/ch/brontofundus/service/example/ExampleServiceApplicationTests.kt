package ch.brontofundus.service.example

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("localhost")
class ExampleServiceApplicationTests {

	@Test
	fun contextLoads() {
	}

}
