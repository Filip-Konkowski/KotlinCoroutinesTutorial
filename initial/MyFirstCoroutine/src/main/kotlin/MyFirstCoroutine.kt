import kotlinx.coroutines.*
import java.math.BigInteger
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext


fun main() {

//    val job1: Job = Job()
//
//    val scope = CoroutineScope(Dispatchers.Default + job1)
//
//    // the lunch will have scope in context of parents Dispatchers.Default + job1 see img/jobContext1.png
//    val job2  = scope.launch(start = CoroutineStart.LAZY) {
//
////        this.logContext(this.toString())
//        logMessage("Hello")
//        delay(1000)
//
//    }

//    job2.logDetails("job2")
    // it won't start job if this line is missing
    // that would change coroutine from New to Active state
//    job2.start()
    // after code in job2 finsihed the state will change from Active to Completed
    // life cicle of job is New->Active->Completed



    // if I want to have one job to be finished after other
    // I can use job.join() suspend and resume pattern
    // output: StartOfJob2 -> Hello -> EndOfJob2
//    scope.launch {
//        logMessage("Start of Job2")
//        job2.join()
//        logMessage("End of Job2")
//    }

//    scope.launch {
//        val jobs: List<Job> = (1..5).map {
//            launch(start = CoroutineStart.LAZY) {
//                logMessage("Start coroutine $it")
//                delay(100)
//                logMessage("End coroutine $it")
//            }
//        }
//
//        logMessage("Created all Coroutines")
//        jobs.joinAll() // as start is LAZY it will only run coroutine after join() or start() called
//        logMessage("Complete all Coroutines")
//    }
//
//    Thread.sleep(2000)
//    logMessage("world")


    // run corouting in main thread first
    // it's called MAIN FIRST

    runBlocking {
        logMessage("Prev>>>")
        logHelloWorld()
        logMessage("<<<Post")
    }

    logMessage("completing main function")
}

private suspend fun logHelloWorld() {
    logMessage("in logHelloWorld")
    worldMessage()
    helloMessage()
    logMessage("leaving logHelloWorld")
}

private suspend fun worldMessage() = withContext(Dispatchers.Default) {
    val myPrime = BigInteger.probablePrime(4096, java.util.Random())
    logMessage("World $myPrime")
}

private suspend fun helloMessage() = withContext(Dispatchers.Default) {
    val myPrime = BigInteger.probablePrime(4096, java.util.Random())
    logMessage("Wrold $myPrime")
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