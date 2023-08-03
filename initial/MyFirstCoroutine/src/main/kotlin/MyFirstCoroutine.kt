import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext


fun main() {

    val job1: Job = Job()

    val scope = CoroutineScope(Dispatchers.Default + job1)

    // the lunch will have scope in context of parents Dispatchers.Default + job1 see img/jobContext1.png
    val job2  = scope.launch(start = CoroutineStart.LAZY) {

//        this.logContext(this.toString())
        logMessage("Hello")
        delay(1000)

    }

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