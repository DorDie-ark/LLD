public class BillPughSingleton {

    private BillPughSingleton(){}

    private static class Helper
    {
        private final static BillPughSingleton instance = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance()
    {
        return Helper.instance;
    }

}

// 1. Problem  -> Ensures only ONE instance exists per JVM
// 2. Approach -> Static nested Helper class is NOT loaded at class load time
//                Loaded ONLY when getInstance() is first called
//                JVM class loader lock ensures only ONE thread loads Helper
//                No synchronized, no volatile needed — JVM handles thread safety
// 3. Weakness -> Reflection can bypass private constructor and break Singleton