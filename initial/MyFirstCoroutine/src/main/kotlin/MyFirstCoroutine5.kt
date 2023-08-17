import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

//fun main() = runBlocking {
//
//    val scope = CoroutineScope(SupervisorJob())
//
//    scope.launch {
//        delay(10)
//        logMessage5("Hello")
//    }
//
//    scope.launch {
//        throw IndexOutOfBoundsException("oops")
//        delay(10)
//        logMessage5("World")
//    }
//
//    delay(100)
//    logMessage5("The End")
//}


//fun main() = runBlocking {
//
//    val scope = CoroutineScope(Job())
//
//    scope.launch {
//        // this ensure that coroutines are in the supervisor scope
//        supervisorScope {
//            launch {
//                delay(10)
//                logMessage5("Hello")
//            }
//
//            launch {
//                throw IndexOutOfBoundsException("oops")
//                delay(10)
//                logMessage5("World")
//            }
//        }
//    }
//
//
//
//    delay(100)
//    logMessage5("The End")
//}

//
//fun main() = runBlocking {
//
//    val scope = CoroutineScope(Job())
//    val sharedJob = SupervisorJob()
//
//    scope.launch(sharedJob) {
//        delay(10)
//        logMessage5("Hello")
//    }
//
//    scope.launch(sharedJob) {
//        throw IndexOutOfBoundsException("oops")
//        delay(10)
//        logMessage5("World")
//    }
//
//    delay(100)
//    logMessage5("The End")
//}


fun main() = runBlocking {

    val scope = CoroutineScope(Job())

    scope.launch {
        val result = runCatching {
            throw IndexOutOfBoundsException("oops")
        }

        if (result.isSuccess) {
            logMessage5("I am Happy!")
        }

        if (result.isFailure) {
            logMessage5("I am Sad :(")
        }
    }

    delay(100)
    logMessage5("The End")
}

fun logMessage5(msg: String) {
    println("Running on: [${Thread.currentThread().name}] | $msg")
}

