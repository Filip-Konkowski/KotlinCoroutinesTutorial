import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

fun main() = runBlocking {

    val scope = CoroutineScope(Job())

    scope.launch {
        logMessage5("Hello")
    }

    scope.launch {
        logMessage5("World")
    }

    delay(100)
    logMessage5("The End")
}

fun logMessage5(msg: String) {
    println("Running on: [${Thread.currentThread().name}] | $msg")
}

