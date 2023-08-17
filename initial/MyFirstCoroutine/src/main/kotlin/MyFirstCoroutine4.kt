import kotlinx.coroutines.*

//TODO - 1 Lets run the code and see that our Coroutine is not cooperative with Cancellation.
fun main() = runBlocking {


    val scope = CoroutineScope(Dispatchers.Default + CoroutineName("Non-Cooperative"))

    val startTime = System.currentTimeMillis()
    val job1 = scope.launch() {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) {
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                logMessage4("Hello World ${i++}")
                nextPrintTime += 500L
            }
        }

    }

    delay(1000L)
    logMessage4("Cancel!")

    //job1.cancel()
    logMessage4("Done!")
    delay(5000)

}

// Please scroll down for next examples... :)


/*

//TODO - 2 Lets see how we can make our Coroutine cooperative with Cancellation by using the isActive state.
fun main() = runBlocking {


    val scope = CoroutineScope(Dispatchers.Default + CoroutineName("isActive"))

    val startTime = System.currentTimeMillis()
    val job1 = scope.launch() {
        var nextPrintTime = startTime
        var i = 0
        // Hint: We could modify the while loop.
        while (i < 5) {
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                logMessage4("Hello World ${i++}")
                nextPrintTime += 500L
            }
        }

    }

    delay(1000L)
    logMessage4("Cancel!")
    job1.cancel()
    logMessage4("Done!")
    delay(5000)

}

*/


/*
//TODO - 3 We have successfully used the isActive property, but there is another way, the ensureActive() function, lets modify the code to use it.
fun main() = runBlocking {


    val scope = CoroutineScope(Dispatchers.Default + CoroutineName("ensureActive"))

    val startTime = System.currentTimeMillis()
    val job1 = scope.launch() {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) {
            // Hint: This may be a good place to ensure our coroutine is still active.
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                logMessage4("Hello World ${i++}")
                nextPrintTime += 500L
            }
        }

    }

    delay(1000L)
    logMessage4("Cancel!")
    job1.cancel()
    logMessage4("Done!")
    delay(5000)

}*/


/*
//TODO - 4 All Kotlin Coroutine framework 'suspend' functions are cooperative, so by using them our code also becomes cooperative, lets use the yield function.
fun main() = runBlocking {


    val scope = CoroutineScope(Dispatchers.Default + CoroutineName("yield"))
    val startTime = System.currentTimeMillis()
    val job1 = scope.launch() {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) {
            //Hint: this maybe a good place to yeild.
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                logMessage4("Hello World ${i++}")
                nextPrintTime += 500L
            }
        }

    }

    delay(1000L)
    logMessage4("Cancel!")
    job1.cancel()
    logMessage4("Done!")
    delay(5000)

}

*/

/*
//TODO - 4 All Kotlin Coroutine framework 'suspend' functions are cooperative, so by using them our code also becomes cooperative, lets use the delay function.
fun main() = runBlocking {


    val scope = CoroutineScope(Dispatchers.Default + CoroutineName("delay"))

    val startTime = System.currentTimeMillis()
    val job1 = scope.launch() {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) {
            //Hint: this maybe a good place to delay.
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                logMessage4("Hello World ${i++}")
                nextPrintTime += 500L
            }
        }

    }

    delay(1000L)
    logMessage4("Cancel!")
    job1.cancel()
    logMessage4("Done!")
    delay(5000)
}

*/



fun logMessage4(msg: String) {
    println("Running on: [${Thread.currentThread().name}] | $msg")
}
