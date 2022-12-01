package threadLocal;

public class ThreadLocalExample {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        var thread1 = new Thread(
                () -> {
                    threadLocal.set("Thread 1 value");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("Thread 1 threadlocal value is %s, %n", threadLocal.get());
                }
        );
        var thread2 = new Thread(
                () -> {
                    threadLocal.set("Thread 2 value");
                    System.out.printf("Thread 2 threadlocal value is %s, %n", threadLocal.get());
                }
        );
        thread1.start();
        thread2.start();
    }
}
