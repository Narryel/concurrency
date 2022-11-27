
import kotlinx.coroutines.*


/**
 * see https://proandroiddev.com/synchronization-and-thread-safety-techniques-in-java-and-kotlin-f63506370e6d
 */
fun main() {
    runBlocking {
        var sharedCounter = 0
        val scope = CoroutineScope(newFixedThreadPoolContext(4, "synchronizationPool")) // We want our code to run on 4 threads
        scope.launch {
            val coroutines = 1.rangeTo(1000).map { //create 1000 coroutines (light-weight threads).
                launch {
                    for(i in 1..1000){ // and in each of them, increment the sharedCounter 1000 times.
                        sharedCounter++
                    }
                }
            }

            coroutines.forEach {
                    corotuine->
                corotuine.join() // wait for all coroutines to finish their jobs.
            }
        }.join()

        println("The number of shared counter should be 1000000, but actually is $sharedCounter")
    }
}