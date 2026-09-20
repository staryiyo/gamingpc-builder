public class AmdProcessor implements Processor {
    private final String model;
    private final int cores;

    public AmdProcessor(String model, int cores) {
        this.model = model;
        this.cores = cores;
    }

    @Override
    public String getName() {
        return "AMD " + model;
    }

    @Override
    public int getCoreCount() {
        return cores;
    }
}