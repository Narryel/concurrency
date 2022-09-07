fun main() {

    val runnable = infiniteRunnable()
    println("$prefix starting another thread")

    // Will stop jvm process since started thread is deamon
    val thread = Thread(runnable, "Infinite running thread ").apply {
        isDaemon = true
        start()
    }

    // will block current thread on this thread thus jvm process will not stop ever
    thread.join()

    Thread.sleep(5000)
    println("$prefix finished")
}