import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis


fun main() = runBlocking {
    launch {
        logMessage2("Pre>>>")
        val time = measureTimeMillis {
            logMessage2(logHelloWorld())
        }
        logMessage2("Time taken: $time")
    }

    logMessage2("<<<Post")
    logMessage2("completing main function...")

}


private suspend fun logHelloWorld(): String = withContext(Dispatchers.Default) {
    logMessage2("in logHelloWorld")
    val helloMessage: Deferred<String> = async { helloMessage() }
    val worldMessage = async { worldMessage() }

    logMessage2("leaving logHelloWorld")
    helloMessage.await() + worldMessage.await()
}

private suspend fun helloMessage(): String = withContext(Dispatchers.Default) {
    logMessage2("in helloMessage")
    delay(1000)
    "Hello "
}

private suspend fun worldMessage(): String = withContext(Dispatchers.Default) {
    logMessage2("in worldMessage")
    delay(1000)
    "World!!"
}


fun logMessage2(msg: String) {
    println("Running on: [${Thread.currentThread().name}] | $msg")
}


fun CoroutineScope.logContext2(id: String) {
    coroutineContext.logDetails2(id)
}


fun CoroutineContext.logDetails2(id: String) {
    sequenceOf(
        Job,
        ContinuationInterceptor,
        CoroutineExceptionHandler,
        CoroutineName
    )
        .mapNotNull { key -> this[key] }
        .forEach { logMessage2("id: $id ${it.key} = ${it}") }
}