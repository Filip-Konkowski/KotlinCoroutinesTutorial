import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext


fun main() {

    val job1 = Job()

    val scope = CoroutineScope(Dispatchers.Default + job1)

    // the lunch will have scope in context of parents Dispatchers.Default + job1 see img/jobContext1.png
    val job2  = scope.launch {

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