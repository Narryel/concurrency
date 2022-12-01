package syncronisation;

public class Reentrance {
    public static void main(String[] args) {
        var rs = new ReentrantSyncronization();
        rs.increment();
        System.out.println(rs.incrementAndGet());
        System.out.println(rs.incrementAndGet());
    }
}



class ReentrantSyncronization {
    private int count = 0;

    public synchronized void increment(){
        this.count++;
    }

    /**
     * вызов второго метода не блочит тред так как Один тред не блочится об один и тот же монитор (инстанс ReentrantSyncronization)
     */
    public synchronized int incrementAndGet(){
        increment();
        return this.count;
    }
}