package strdcoders.mss.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import java.io.File

@SpringBootTestcontainersMssTest
class StorageUtilsTest {

    @Autowired
    private lateinit var storageUtils: StorageUtils

    @Nested
    inner class IsFileAboveLimit {

        @Test
        fun `when partition is using more space then the threshold, should return true`() {
            val file = Mockito.mock(File::class.java)
            Mockito.`when`(file.totalSpace).thenReturn(100)
            Mockito.`when`(file.usableSpace).thenReturn(9)
            assertThat(storageUtils.isFileAboveLimit(file, 90)).isTrue
        }

        @Test
        fun `when partition is using exactly the space of the threshold, should return false`() {
            val file = Mockito.mock(File::class.java)
            Mockito.`when`(file.totalSpace).thenReturn(100)
            Mockito.`when`(file.usableSpace).thenReturn(10)
            assertThat(storageUtils.isFileAboveLimit(file, 90)).isFalse
        }

        @Test
        fun `when partition is using less than the space of the threshold, should return false`() {
            val file = Mockito.mock(File::class.java)
            Mockito.`when`(file.totalSpace).thenReturn(100)
            Mockito.`when`(file.usableSpace).thenReturn(11)
            assertThat(storageUtils.isFileAboveLimit(file, 90)).isFalse
        }
    }
}
