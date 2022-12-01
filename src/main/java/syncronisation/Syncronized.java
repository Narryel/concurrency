package syncronisation;

public class Syncronized {
    public static void main(String[] args) {
        var exchanger = new SyncronizedExchanger();


        var thread1 = new Thread(() -> {
                    for (int i = 0; i < 1000; i++) {
                        exchanger.setObject("" + i);
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


