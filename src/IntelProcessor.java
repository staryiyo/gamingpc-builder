public class IntelProcessor implements Processor {
    private final String model;
    private final int cores;

    public IntelProcessor(String model, int cores) {
        this.model = model;
        this.cores = cores;
    }

    @Override
    public String getName() {
        return "Intel " + model;
    }

    @Override
    public int getCoreCount() {
        return cores;
    }
}