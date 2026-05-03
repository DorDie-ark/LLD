import java.util.Scanner;

class Desktop {
    private String motherboard;
    private String processor;
    private String memory;
    private String storage;
    private String graphicsCard;

    @Override
    public String toString() {
        return "Desktop{" +
                "motherboard='" + motherboard + '\'' +
                ", processor='" + processor + '\'' +
                ", memory='" + memory + '\'' +
                ", storage='" + storage + '\'' +
                ", graphicsCard='" + graphicsCard + '\'' +
                '}';
    }

    public void display() {
        System.out.println("Desktop Specs:");
        System.out.println("Motherboard: " + motherboard);
        System.out.println("Processor: " + processor);
        System.out.println("Memory: " + memory);
        System.out.println("Storage: " + storage);
        System.out.println("Graphics Card: " + graphicsCard);
    }

    public String getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }
}

abstract class DesktopBuilder {
    protected Desktop desktop = new Desktop();

    public Desktop getDesktop() {
        return desktop;
    }

    abstract void buildMotherboard();
    abstract void buildProcessor();
    abstract void buildMemory();
    abstract void buildStorage();
    abstract void buildGraphicsCard();

}

class DellDesktopBuilder extends DesktopBuilder {
    @Override
    void buildMotherboard() {
        desktop.setMotherboard("Dell Motherboard");
    }

    @Override
    void buildProcessor() {
        desktop.setProcessor("Dell Processor");
    }

    @Override
    void buildMemory() {
        desktop.setMemory("32GB DDR4 RAM");

    }

    @Override
    void buildStorage() {
        desktop.setStorage("1TB SSD + 2TB HDD");
    }

    @Override
    void buildGraphicsCard() {
        desktop.setGraphicsCard("NVIDIA RTX 3080");
    }
}

class HPDesktopBuilder extends DesktopBuilder {

    @Override
    void buildMotherboard() {
        desktop.setMotherboard("HP Motherboard");
    }

    @Override
    void buildProcessor() {
        desktop.setProcessor("HP Processor");
    }

    @Override
    void buildMemory() {
        desktop.setMemory("32GB DDR4 RAM");
    }

    @Override
    void buildStorage() {
        desktop.setStorage("1TB SSD + 2TB HDD");
    }

    @Override
    void buildGraphicsCard() {
        desktop.setGraphicsCard("NVIDIA RTX 3080");
    }
}

class MacDesktopBuilder extends DesktopBuilder {

    @Override
    void buildMotherboard() {
        desktop.setMotherboard("MAC Motherboard");
    }

    @Override
    void buildProcessor() {
        desktop.setProcessor("MAC Processor");
    }

    @Override
    void buildMemory() {
        desktop.setMemory("64GB M4 RAM");
    }

    @Override
    void buildStorage() {
        desktop.setStorage("1TB SSD + 2TB HDD");
    }

    @Override
    void buildGraphicsCard() {
        desktop.setGraphicsCard("Apple M4 GPU");
    }
}

//
//class DesktopDirector {
//
//    public Desktop buildDesktop(DesktopBuilder desktopBuilder)
//    {
//        desktopBuilder.buildMotherboard();
//        desktopBuilder.buildMemory();
//        desktopBuilder.buildProcessor();
//        desktopBuilder.buildStorage();
//        desktopBuilder.buildGraphicsCard();
//        return desktopBuilder.getDesktop();
//    }
//
//}

class DesktopDirector {

    private final DesktopBuilder desktopBuilder;
    public Desktop buildDesktop()
    {
        desktopBuilder.buildMotherboard();
        desktopBuilder.buildMemory();
        desktopBuilder.buildProcessor();
        desktopBuilder.buildStorage();
        desktopBuilder.buildGraphicsCard();
        return desktopBuilder.getDesktop();
    }

    public DesktopDirector(DesktopBuilder desktopBuilder) {
        this.desktopBuilder = desktopBuilder;
    }
}

public class DesktopBuilderDemo {
    static Scanner scanner = new Scanner(System.in);
    static void main() {
        System.out.println("Select Type : (MAC/HP/DELL)");
        String choice = scanner.nextLine();
        DesktopBuilder desktopBuilder = resolve(choice);
        DesktopDirector desktopDirector = new DesktopDirector(desktopBuilder);
        Desktop desktop = desktopDirector.buildDesktop();
        desktop.display();
    }

    static DesktopBuilder resolve(String choice)
    {
        return switch (choice.toUpperCase())
        {
            case "MAC" -> new MacDesktopBuilder();
            case "HP" -> new HPDesktopBuilder();
            case "DELL" -> new DellDesktopBuilder();
            default -> throw new IllegalArgumentException("UNKNOWN");
        };
    }
}


