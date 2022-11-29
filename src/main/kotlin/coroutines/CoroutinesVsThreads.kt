package coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun main() = runBlocking {
//    repeat(100_000) { // launch a lot of coroutines
//        launch {
//            delay(5000L)
//            print(".")
//        }
//    }
//}


fun main() {
    repeat(100_000) { // launch a lot of coroutines
        kotlin.concurrent.thread {
            Thread.sleep(5000L)
            print(".")
        }
    }
}