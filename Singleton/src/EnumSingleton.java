public enum EnumSingleton {
    INSTANCE
}

// 1. Problem  -> Ensures only ONE instance exists per JVM
// 2. Approach -> Class loader creates Enum constants as static final objects:
//                EnumSingleton INSTANCE = new EnumSingleton("INSTANCE", 0)
//                Created once at class load time (eager)
//                Serialization safe — JVM guarantees same instance on deserialization
// 3. Strength -> Reflection CANNOT break Enum — JVM explicitly throws
//                IllegalArgumentException if attempted
//                Most bulletproof Singleton in Java
