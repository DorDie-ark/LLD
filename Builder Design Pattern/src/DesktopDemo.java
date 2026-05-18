import java.util.Scanner;

enum DesktopType {
    OFFICE, GAMING
}

public class DesktopDemo {
    Scanner scanner = new Scanner(System.in);

    void main() {
        System.out.println("Enter Type of Desktop (GAMING/OFFICE)");
        String desktopChoice = scanner.nextLine().toUpperCase();
        DesktopType desktopType = DesktopType.valueOf(desktopChoice);
        IDesktopBuilder iDesktopBuilder = resolveBuilder(desktopType);
        Director director = new Director(iDesktopBuilder);
        System.out.println(director.buildDesktop());

    }

    IDesktopBuilder resolveBuilder(DesktopType desktopType) {
        return switch (desktopType) {
            case GAMING -> new GamingDesktopBuilder();
            case OFFICE -> new OfficeDesktopBuilder();
        };
    }

}

class Desktop {
    @Override
    public String toString() {
        return "Desktop{" +
                "processor='" + processor + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", graphicsCard='" + graphicsCard + '\'' +
                ", os='" + os + '\'' +
                '}';
    }

    private String processor;
    private String ram;
    private String storage;
    private String graphicsCard;
    private String os;

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
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

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}

abstract class IDesktopBuilder {
    private final Desktop desktop = new Desktop();

    public Desktop build() {
        return desktop;
    }

    abstract IDesktopBuilder buildGraphicCard();

    abstract IDesktopBuilder buildStorageCard();

    abstract IDesktopBuilder buildRAM();

    abstract IDesktopBuilder buildProcessor();

    abstract IDesktopBuilder buildOs();
}

class GamingDesktopBuilder extends IDesktopBuilder {

    @Override
    IDesktopBuilder buildGraphicCard() {
        build().setGraphicsCard("NVIDIA RTX 4090 24GB");
        return this;
    }

    @Override
    IDesktopBuilder buildStorageCard() {
        build().setStorage("2TB NVMe SSD");
        return this;
    }

    @Override
    IDesktopBuilder buildRAM() {
        build().setRam("64GB DDR5 6000MHz");
        return this;
    }

    @Override
    IDesktopBuilder buildProcessor() {
        build().setProcessor("Intel Core i9-14900K");
        return this;
    }

    @Override
    IDesktopBuilder buildOs() {
        build().setOs("Windows 11 Pro");
        return this;
    }
}

class OfficeDesktopBuilder extends IDesktopBuilder {

    @Override
    IDesktopBuilder buildGraphicCard() {
        build().setGraphicsCard("Intel UHD Graphics 770");
        return this;
    }

    @Override
    IDesktopBuilder buildStorageCard() {
        build().setStorage("512GB SATA SSD");
        return this;
    }

    @Override
    IDesktopBuilder buildRAM() {
        build().setRam("16GB DDR4 3200MHz");
        return this;
    }

    @Override
    IDesktopBuilder buildProcessor() {
        build().setProcessor("Intel Core i5-13400");
        return this;
    }

    @Override
    IDesktopBuilder buildOs() {
        build().setOs("Windows 11 Home");
        return this;
    }
}


class Director {
    IDesktopBuilder desktopBuilder;

    public Director(IDesktopBuilder desktopBuilder) {
        this.desktopBuilder = desktopBuilder;
    }

    Desktop buildDesktop() {
        return desktopBuilder.buildOs()
                .buildRAM()
                .buildProcessor()
                .buildGraphicCard()
                .buildStorageCard()
                .build();
    }

}






