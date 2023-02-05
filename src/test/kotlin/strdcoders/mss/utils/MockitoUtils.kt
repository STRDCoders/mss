package strdcoders.mss.utils

import org.mockito.Mockito

class MockitoUtils {
    companion object {
        inline fun <reified T> anyNonNull(): T = Mockito.any<T>(T::class.java)
    }
}
