public class PCDirector {

    public GamingPC buildBudgetPC(GamingPC.Builder builder) {
        return builder
                .setCpu("Intel Core i3-13100F")
                .setGpu("GTX 1650")
                .setRam(16)
                .setStorage("SSD", 512)
                .setPsu(450)
                .setCooling("Air")
                .setMotherboard("B760M")
                .setCaseType("Micro-ATX")
                .build();
    }

    public GamingPC buildHighEndPC(GamingPC.Builder builder) {
        return builder
                .setCpu("Intel Core i7-14700K")
                .setGpu("RTX 4080")
                .setRam(32)
                .setStorage("NVMe SSD", 2000)
                .setPsu(850)
                .setCooling("Liquid")
                .setMotherboard("Z790")
                .setCaseType("Full Tower")
                .build();
    }

    public GamingPC buildStreamingPC(GamingPC.Builder builder) {
        return builder
                .setCpu("AMD Ryzen 9 7950X")
                .setGpu("RTX 4070")
                .setRam(64)
                .setStorage("NVMe SSD", 1000)
                .setPsu(750)
                .setCooling("Liquid")
                .setMotherboard("X670")
                .setCaseType("Mid Tower")
                .build();
    }
}