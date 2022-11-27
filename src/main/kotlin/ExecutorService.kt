import java.util.concurrent.Executors

fun main() {
    val executorService = Executors.newFixedThreadPool(1)
    val startTime = System.currentTimeMillis()
    for (i in 0..10) {
        executorService.execute {
            Thread.sleep(1000)
            println("Thread ${Thread.currentThread().name} finished Task-$i")
        }
    }


    executorService.shutdown()

    while (executorService.isTerminated.not()){
        Thread.sleep(1)
    }
    val endTime = System.currentTimeMillis()
    println("Workload done in ${endTime - startTime} millis")

}