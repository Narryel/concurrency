package staticAndNonStaticSync;

public class Syncronized2 {
    public static void main(String[] args) {
        var exchanger = new MixedSyncronizedExchanger();


        var thread1 = new Thread(() -> {
                    for (int i = 0; i < 1000; i++) {
                        exchanger.setObject("" + i);
                        System.out.printf("Thread %s finished setting object %s %n", Thread.currentThread().getName(), "" + i);
                    }
                }
        );

        var thread2 = new Thread(() -> {
                    for (int i = 0; i < 1000; i++) {
                        System.out.println(exchanger.getObject());
                    }
                }
        );

        thread1.start();
        thread2.start();

    }
}


