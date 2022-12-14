fun main() {
    val runnable = infiniteRunnable()

    println("$prefix starting another thread")

    // Will stop jvm process since started thread is deamon
    Thread(runnable, "Infinite running thread ").apply {
        isDaemon = true
        start()
    }

    Thread.sleep(5000)
    println("$prefix finished")
}

fun infiniteRunnable(): () -> Unit {
    val runnable = {
        while (true) {
            println("$prefix is running")
            Thread.sleep(1000)
        }
    }
    return runnable
}

val prefix: String
    get() = Thread.currentThread().name
