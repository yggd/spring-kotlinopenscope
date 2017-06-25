package org.yggd.sample

import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SpringOpencheckApplicationTests {

    @Autowired
    lateinit var hogeService: HogeService

    @Test
    fun afterAspect() {
        try {
            hogeService.execute()
            fail("Never thrown exceptions.")
        } catch (e : IllegalStateException) {
            assertThat(e.message).isEqualTo("force throwing...")
        }
    }

}
