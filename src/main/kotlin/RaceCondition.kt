fun main() {

    val runnable = IncrementingRunnable()

    Thread(runnable, "First thread").apply { start() }
    Thread(runnable, "Second thread").apply { start() }
}

class IncrementingRunnable : Runnable {

    private var count: Int = 0

    override fun run() {
        for (i in 1..1_000_000) {
            count++
        }

        // will never be 2 million due to race condition
        println("$prefix result count is $count")
    }
}
