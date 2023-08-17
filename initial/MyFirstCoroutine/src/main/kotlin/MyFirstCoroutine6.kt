import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext


fun main() = runBlocking {

    val scope = CoroutineScope(Job())

    //Parent 1
    scope.launch {

        //Child 1
        launch {

            //Child 1 of Child 1
            launch {
                try {
                    logContext6(">>>Child 1s Child")
                    delay(1)
                    logMessage6(">>>Hello from Child 1s Child!")
                } catch (e: Exception) {
                    logMessage6(">>>Child 1s Child got ${e.javaClass}  ${e.localizedMessage}")
                }
            }

            logContext6(">>Child 1")
            try {
                delay(10)
                logMessage6(">>Hello from Child 1")
            } catch (e: Exception) {
                logMessage6(">>Child 1 got ${e.javaClass}  ${e.localizedMessage}")
            }
        }

        //Child 2
        launch {

            //Child 1 of Child 2
            launch {
                try {
                    logContext6(">>>Child 2s Child")
                    delay(1)
                    logMessage6(">>>Hello from Child 2s Child!")
                } catch (e: Exception) {
                    logMessage6(">>>Child 2s Child got ${e.javaClass}  ${e.localizedMessage}")
                }
            }

            logContext6(">>Child 2")
            // throw IndexOutOfBoundsException("oops!")
            delay(10)
            logMessage6(">>Hello from Child 2")
        }

        try {
            logContext6(">Parent 1")
            delay(30)
            logMessage6(">Hello from Parent 1")
        } catch (e: Exception) {
            logMessage6(">Parent 1 got ${e.javaClass}  ${e.localizedMessage}")
        }
    }


    //Parent 2
    scope.launch {
        try {
            logContext6(">Parent 2")
            delay(100)
            logMessage6(">Hello from Parent 2")
        } catch (e: Exception) {
            logMessage6(">Parent 2 got ${e.javaClass}  ${e.localizedMessage}")
        }
    }

    delay(1000)
    logMessage6("The End.")

}


fun logMessage6(msg: String) {
    println("Running on: [${Thread.currentThread().name}] | $msg")
}


fun CoroutineScope.logContext6(id: String) {
    coroutineContext.logDetails(id)
}


fun CoroutineContext.logDetails6(id: String) {
    sequenceOf(
        Job,
        ContinuationInterceptor,
        CoroutineExceptionHandler,
        CoroutineName
    )
        .mapNotNull { key -> this[key] }
        .forEach { logMessage6("id: $id ${it.key} = $it") }
}