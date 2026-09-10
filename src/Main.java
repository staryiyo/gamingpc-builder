public class Main {

    public static void main(String[] args) {

        PCDirector director = new PCDirector();

        GamingPC budgetPC = director.buildBudgetPC(new GamingPC.Builder());
        System.out.println("Budget PC: " + budgetPC);

        GamingPC highEndPC = director.buildHighEndPC(new GamingPC.Builder());
        System.out.println("High-End PC: " + highEndPC);

        GamingPC customPC = new GamingPC.Builder()
                .setCpu("AMD Ryzen 5 7600")
                .setGpu("RTX 4060")
                .setRam(32)
                .setStorage("SSD", 1000)
                .setPsu(650)
                .setCooling("Air")
                .setMotherboard("B650")
                .setCaseType("Mid Tower")
                .build();
        System.out.println("Custom PC: " + customPC);

        //GamingPC brokenPC = new GamingPC.Builder()
        //      .setCpu("i9-14900K")
        //      .setGpu("RTX 4090")
        //      .setPSU(400) слишком слабо для rtx
        //      .build();
    }
}