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
        DesktopBuilder DesktopBuilder = resolveBuilder(desktopType);
        Director director = new Director(DesktopBuilder);
        System.out.println(director.buildDesktop());

    }

    DesktopBuilder resolveBuilder(DesktopType desktopType) {
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

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }


    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public void setOs(String os) {
        this.os = os;
    }
}

abstract class DesktopBuilder {
    private final Desktop desktop = new Desktop();

    public Desktop build() {
        return desktop;
    }

    public abstract DesktopBuilder buildGraphicCard();

    public abstract DesktopBuilder buildStorageCard();

    public abstract DesktopBuilder buildRAM();

    public abstract DesktopBuilder buildProcessor();

    public abstract DesktopBuilder buildOs();
}

class GamingDesktopBuilder extends DesktopBuilder {

    @Override
    public DesktopBuilder buildGraphicCard() {
        build().setGraphicsCard("NVIDIA RTX 4090 24GB");
        return this;
    }

    @Override
    public DesktopBuilder buildStorageCard() {
        build().setStorage("2TB NVMe SSD");
        return this;
    }

    @Override
    public DesktopBuilder buildRAM() {
        build().setRam("64GB DDR5 6000MHz");
        return this;
    }

    @Override
    public DesktopBuilder buildProcessor() {
        build().setProcessor("Intel Core i9-14900K");
        return this;
    }

    @Override
    public DesktopBuilder buildOs() {
        build().setOs("Windows 11 Pro");
        return this;
    }
}

class OfficeDesktopBuilder extends DesktopBuilder {

    @Override
    public DesktopBuilder buildGraphicCard() {
        build().setGraphicsCard("Intel UHD Graphics 770");
        return this;
    }

    @Override
    public DesktopBuilder buildStorageCard() {
        build().setStorage("512GB SATA SSD");
        return this;
    }

    @Override
    public DesktopBuilder buildRAM() {
        build().setRam("16GB DDR4 3200MHz");
        return this;
    }

    @Override
    public DesktopBuilder buildProcessor() {
        build().setProcessor("Intel Core i5-13400");
        return this;
    }

    @Override
    public DesktopBuilder buildOs() {
        build().setOs("Windows 11 Home");
        return this;
    }
}


class Director {
    DesktopBuilder builder;

    public Director(DesktopBuilder desktopBuilder) {
        this.builder = desktopBuilder;
    }

    Desktop buildDesktop() {
        return builder.buildOs()
                .buildRAM()
                .buildProcessor()
                .buildGraphicCard()
                .buildStorageCard()
                .build();
    }

}






