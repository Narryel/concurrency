package staticAndNonStaticSync;

class MixedSyncronizedExchanger {

    private Object object = null;


    // эти методы равнозначны, так как syncronized кейворд неявно использует монитором this
    // к монитору только 1 поток -> set и гет одновременно не будут
    public synchronized void setObject(Object object) {
        this.object = object;
    }

    public synchronized Object getObject() {
        return object;
    }

    public Object getObj() {
        synchronized (this) {
            return object;
        }
    }

    public void setObj(Object object) {
        synchronized (this.getClass()) {
            this.object = object;
        }
    }

}