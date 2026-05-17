void main() {
    EagerSingleton e1 = EagerSingleton.getInstance();
    EagerSingleton e2 = EagerSingleton.getInstance();
    System.out.println("Eager : "+(e1==e2));

    LazySingleton l1 = LazySingleton.getInstance();
    LazySingleton l2 = LazySingleton.getInstance();
    System.out.println("Lazy: " + (l1 == l2));

    DCLSingleton d1 = DCLSingleton.getInstance();
    DCLSingleton d2 = DCLSingleton.getInstance();
    System.out.println("DCL: " + (d1 == d2));

    BillPughSingleton b1 = BillPughSingleton.getInstance();
    BillPughSingleton b2 = BillPughSingleton.getInstance();
    System.out.println("BillPugh: " + (b1 == b2));

    EnumSingleton en1 = EnumSingleton.INSTANCE;
    EnumSingleton en2 = EnumSingleton.INSTANCE;
    System.out.println("Enum: " + (en1 == en2));



}
