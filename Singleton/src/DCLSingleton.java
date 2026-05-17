public class DCLSingleton {

    private static volatile DCLSingleton instance;

    private DCLSingleton(){}

    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }

}

// 1. Problem  -> Ensures only ONE instance exists per JVM
// 2. Approach -> Lock acquired ONLY during first creation — double if check ensures
//                no two threads create instance simultaneously
//                After creation, threads bypass synchronized block directly
// 3. Weakness -> volatile forces read/write to main memory — skips L1 cache
//                slight performance cost + complex code + reflection can break it
