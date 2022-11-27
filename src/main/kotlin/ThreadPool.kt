
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

fun main() {


    val tp = ThreadPool(3, 10)
    for (i in 0..10) {
        tp.execute{
//            Thread.sleep(1000)
            println("Thread ${Thread.currentThread().name} finished Task-$i")
        }
    }

    tp.waitUntillAllTaskFinish()
    tp.stop()
}


class ThreadPool(
    val threadCount: Int,
    val maxTasksCount: Int,
) {
    var taskQueue: BlockingQueue<Runnable>
    var runnables = mutableListOf<PoolThreadRunnable>()
    var isStopped = false

    init {
        taskQueue = ArrayBlockingQueue(maxTasksCount)

        for (i in 0..threadCount) {
            runnables.add(PoolThreadRunnable(taskQueue))
        }

        runnables.forEach {
            Thread(it).start()
        }
    }

    fun execute(task: Runnable) = synchronized(this) {
        if (isStopped) error("Thread pool is stopped")

        taskQueue.offer(task)
    }


    fun stop() = synchronized(this) {
        isStopped = true
        runnables.forEach {
            it.doStop()
        }
    }

    fun waitUntillAllTaskFinish() = synchronized(this){
        while (taskQueue.size > 0){
            try {
                Thread.sleep(100)
            } catch (e: InterruptedException){
                e.printStackTrace()
            }
        }
    }
}

class PoolThreadRunnable : Runnable {
    lateinit var thread: Thread
    lateinit var taskQueue: BlockingQueue<Runnable>
    var isStopped = false

    constructor(queue: BlockingQueue<Runnable>) {
        taskQueue = queue
    }

    override fun run() {
        thread = Thread.currentThread()
        while (!isStopped) {
            try {
                var runnable = taskQueue.take()
                runnable.run()
            } catch (e: Exception) {
                println(e)
                // need to keep pool thread alive
            }
        }
    }

    fun doStop() = synchronized(this) {
        isStopped = true
        // break pool thread out of dequeue() call
        this.thread.interrupt()
    }


    fun isRunnableStopped() = synchronized(this) {
        isStopped
    }
}