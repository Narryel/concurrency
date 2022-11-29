import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future


fun main() = runBlocking {

    val a = launch {
        getSquare(10)
    }
    println("launched coroutine")
    println(a)
}


suspend fun getSquare(input: Int): Int {
    Thread.sleep(5000)
    return input * input
}

class SquareCalculator {
    private val executor = Executors.newSingleThreadExecutor()

    fun calculate(input: Int): Future<Int?> {
        return executor.submit {
            Callable {
                Thread.sleep(2000)
                input * input
            }

        } as Future<Int?>
    }

}