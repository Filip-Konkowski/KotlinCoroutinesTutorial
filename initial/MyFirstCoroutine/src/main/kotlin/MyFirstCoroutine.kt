import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext


fun main() {

    val scope = CoroutineScope(Dispatchers.Default)

    val job = scope.launch {

        this.logContext(this.toString())
        logMessage("Hello")
        delay(1000)

    }

    Thread.sleep(2000)
    logMessage("world")
}


fun logMessage(msg: String) {
    println("Running on: [${Thread.currentThread().name}] | $msg")
}


fun CoroutineScope.logContext(id: String) {
    coroutineContext.logDetails(id)
}


fun CoroutineContext.logDetails(id: String) {
    sequenceOf(
        Job,
        ContinuationInterceptor,
        CoroutineExceptionHandler,
        CoroutineName
    )
        .mapNotNull { key -> this[key] }
        .forEach { logMessage("id: $id ${it.key} = ${it}") }
}