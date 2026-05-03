import java.util.Scanner;

class Desktop
{
    private String motherboard;
    private String processor;
    private String memory;
    private String storage;
    private String graphicsCard;

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


    public void display() {
        System.out.println("Desktop Specs:");
        System.out.println("Motherboard: " + motherboard);
        System.out.println("Processor: " + processor);
        System.out.println("Memory: " + memory);
        System.out.println("Storage: " + storage);
        System.out.println("Graphics Card: " + graphicsCard);
    }
}

abstract class DesktopBuilder
{
    Desktop desktop = new Desktop();

    abstract DesktopBuilder buildMotherboard();
    abstract DesktopBuilder buildProcessor();
    abstract DesktopBuilder buildMemory();
    abstract DesktopBuilder buildStorage();
    abstract DesktopBuilder buildGraphicsCard();

    Desktop build()
    {
        return desktop;
    }

}

class HPDesktopBuilder extends DesktopBuilder {
    @Override
    DesktopBuilder buildMotherboard() {
        desktop.setMotherboard("HP Motherboard");
        return this;

    }

    @Override
    DesktopBuilder buildProcessor() {
        desktop.setProcessor("HP Processor");
        return this;
    }

    @Override
    DesktopBuilder buildMemory() {
        desktop.setMemory("32GB DDR4 RAM");
        return this;
    }

    @Override
    DesktopBuilder buildStorage() {
        desktop.setStorage("1TB SSD + 2TB HDD");
        return this;
    }

    @Override
    DesktopBuilder buildGraphicsCard() {
        desktop.setGraphicsCard("NVIDIA RTX 3080");
        return this;
    }
}

class DellDesktopBuilder extends DesktopBuilder {
    @Override
    DesktopBuilder buildMotherboard() {
        desktop.setMotherboard("Dell Motherboard");
        return this;

    }

    @Override
    DesktopBuilder buildProcessor() {
        desktop.setProcessor("Dell Processor");
        return this;
    }

    @Override
    DesktopBuilder buildMemory() {
        desktop.setMemory("64GB DDR4 RAM");
        return this;
    }

    @Override
    DesktopBuilder buildStorage() {
        desktop.setStorage("1TB SSD + 2TB HDD");
        return this;
    }

    @Override
    DesktopBuilder buildGraphicsCard() {
        desktop.setGraphicsCard("NVIDIA RTX 3080");
        return this;
    }
}

class MacDesktopBuilder extends DesktopBuilder {
    @Override
    DesktopBuilder buildMotherboard() {
        desktop.setMotherboard("MAC Motherboard");
        return this;

    }

    @Override
    DesktopBuilder buildProcessor() {
        desktop.setProcessor("MAC Processor");
        return this;
    }

    @Override
    DesktopBuilder buildMemory() {
        desktop.setMemory("256GB DDR4 RAM");
        return this;
    }

    @Override
    DesktopBuilder buildStorage() {
        desktop.setStorage("1TB SSD + 2TB HDD");
        return this;
    }

    @Override
    DesktopBuilder buildGraphicsCard() {
        desktop.setGraphicsCard("M4 X4");
        return this;
    }
}

class DesktopDirector
{
    DesktopBuilder desktopBuilder;

    public DesktopDirector(DesktopBuilder desktopBuilder) {
        this.desktopBuilder = desktopBuilder;
    }

    Desktop buildDesktop()
    {
        return desktopBuilder.buildMotherboard()
                .buildGraphicsCard()
                .buildStorage()
                .buildProcessor()
                .buildMemory()
                .build();

    }
}
public class DesktopBuilderDemo {
    static void main() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Select Type of Desktop (HP/DELL/MAC)");
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
            case "HP"-> new HPDesktopBuilder();
            case "DELL"-> new DellDesktopBuilder();
            case "MAC" -> new MacDesktopBuilder();
            default -> throw new IllegalArgumentException("UNKNOWN");
        };

    }
}



