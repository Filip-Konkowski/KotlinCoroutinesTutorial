import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

val handler1 = CoroutineExceptionHandler { _: CoroutineContext, throwable: Throwable ->
    logMessage7("CEH1 handling ${throwable.localizedMessage}")
}

val handler2 = CoroutineExceptionHandler { _: CoroutineContext, throwable: Throwable ->
    logMessage7("CEH2 handling ${throwable.localizedMessage}")
}


fun main() = runBlocking {

    val scope = CoroutineScope(Job() + handler1)

    //Parent 1
    scope.launch {

        //Child 1
        launch {

            //Child 1 of Child 1
            launch {
                try {
                    logContext7(">>>Child 1s Child")
                    delay(1)
                    logMessage7(">>>Hello from Child 1s Child!")
                } catch (e: Exception) {
                    logMessage7(">>>Child 1s Child got ${e.javaClass}  ${e.localizedMessage}")
                }
            }

            logContext7(">>Child 1")
            try {
                delay(10)
                logMessage7(">>Hello from Child 1")
            } catch (e: Exception) {
                logMessage7(">>Child 1 got ${e.javaClass}  ${e.localizedMessage}")
            }
        }

        //Child 2
        launch {

            //Child 1 of Child 2
            launch {
                try {
                    logContext7(">>>Child 2s Child")
                    delay(1)
                    logMessage7(">>>Hello from Child 2s Child!")
                } catch (e: Exception) {
                    logMessage7(">>>Child 2s Child got ${e.javaClass}  ${e.localizedMessage}")
                }
            }


            logContext7(">>Child 2")
            throw IndexOutOfBoundsException("oops!")
            delay(10)
            logMessage7(">>Hello from Child 2")
        }

        try {
            logContext7(">Parent 1")
            delay(30)
            logMessage7(">Hello from Parent 1")
        } catch (e: Exception) {
            logMessage7(">Parent 1 got ${e.javaClass}  ${e.localizedMessage}")
        }
    }

    //Parent 2
    scope.launch {

        try {
            logContext7(">Parent 2")
            delay(100)
            logMessage7(">Hello from Parent 2")

        } catch (e: Exception) {
            logMessage7(">Parent 2 got ${e.javaClass}  ${e.localizedMessage}")
        }
    }

    delay(1000)
    logMessage7("The End.")

}


fun logMessage7(msg: String) {
    println("Running on: [${Thread.currentThread().name}] | $msg")
}


fun CoroutineScope.logContext7(id: String) {
    coroutineContext.logDetails7(id)
}


fun CoroutineContext.logDetails7(id: String) {
    sequenceOf(
        Job,
        ContinuationInterceptor,
        CoroutineExceptionHandler,
        CoroutineName
    )
        .mapNotNull { key -> this[key] }
        .forEach { logMessage7("id: $id ${it.key} = $it") }
}