fun main() {

    val sharedObject = Holder(5)

    val thread = Thread(MyRunnable(sharedObject)).apply { start() }
    val thread2 = Thread(MyRunnable(sharedObject)).apply { start() }

    //will print same stuff since two thread local variables point on one heap object
}

class MyRunnable(
    private val holder: Holder,
) : Runnable {
    override fun run() {
        println(holder)
    }
}

class Holder(val count: Int)