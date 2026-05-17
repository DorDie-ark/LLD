public class EagerSingleton {

    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton(){}

    public static EagerSingleton getInstance() {
        return instance;
    }
}

// 1. Problem  -> Ensures only ONE instance exists per JVM
// 2. Approach -> Instance created at class load time (before any thread accesses it)
// 3. Weakness -> Object created even if never used — wastes heap memory
