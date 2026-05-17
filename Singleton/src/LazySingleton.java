public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton(){}

    public synchronized static LazySingleton getInstance() {
        if(instance==null)
        {
            instance=new LazySingleton();
        }
        return instance;
    }
}

// 1. Problem  -> Ensures only ONE instance exists per JVM
// 2. Approach -> Instance created lazily — only when first accessed
// 3. Weakness -> synchronized on entire method — every thread waits for lock
//                even after instance is already created — kills throughput
