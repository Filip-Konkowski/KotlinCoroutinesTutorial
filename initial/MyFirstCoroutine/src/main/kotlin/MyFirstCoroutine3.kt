import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

fun main() = runBlocking {
    logMessage3("Hello")
    coroutineScope {
        val jobs: List<Job> = (1..5).map {
            launch(start = CoroutineStart.LAZY) {
                logMessage3("Started Coroutine $it")
                delay(100)
                logMessage3("Ended Coroutine $it")
            }
        }

    logMessage3("Created all Coroutines")
    jobs.forEach { it.start() }
    }

    logMessage3("Finished all Coroutines")

    logMessage3("world")
}

fun logMessage3(msg: String) {
    println("Running on: [${Thread.currentThread().name}] | $msg")
}

fun CoroutineScope.logContext3(id: String) {
    coroutineContext.logDetails3(id)
}

fun CoroutineContext.logDetails3(id: String) {
    sequenceOf(
        Job,
        ContinuationInterceptor,
        CoroutineExceptionHandler,
        CoroutineName
    )
        .mapNotNull { key -> this[key] }
        .forEach { logMessage3("id: $id ${it.key} = ${it}") }
}