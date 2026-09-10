public class GamingPC {

    private final String cpu;
    private final String gpu;
    private final int ramGb;
    private final String storageType;
    private final int storageGb;
    private final int psuWatts;
    private final String coolingType;
    private final String motherboard;
    private final String caseType;

    private static final int MIN_PSU_FOR_HIGH_END_GPU = 650;

    private GamingPC(Builder builder) {
        this.cpu = builder.cpu;
        this.gpu = builder.gpu;
        this.ramGb = builder.ramGb;
        this.storageType = builder.storageType;
        this.storageGb = builder.storageGb;
        this.psuWatts = builder.psuWatts;
        this.coolingType = builder.coolingType;
        this.motherboard = builder.motherboard;
        this.caseType = builder.caseType;
    }

    @Override
    public String toString() {
        return "GamingPC{" +
                "cpu='" + cpu + '\'' +
                ", gpu='" + gpu + '\'' +
                ", ramGb=" + ramGb +
                ", storage='" + storageType + " " + storageGb + "GB'" +
                ", psuWatts=" + psuWatts +
                ", cooling='" + coolingType + '\'' +
                ", motherboard='" + motherboard + '\'' +
                ", caseType='" + caseType + '\'' +
                '}';
    }

    public static class Builder {
        private String cpu;
        private String gpu;
        private int ramGb;
        private String storageType;
        private int storageGb;
        private int psuWatts;
        private String coolingType;
        private String motherboard;
        private String caseType;

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setGpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder setRam(int ramGb) {
            this.ramGb = ramGb;
            return this;
        }

        public Builder setStorage(String storageType, int storageGb) {
            this.storageType = storageType;
            this.storageGb = storageGb;
            return this;
        }

        public Builder setPsu(int psuWatts) {
            this.psuWatts = psuWatts;
            return this;
        }

        public Builder setCooling(String coolingType) {
            this.coolingType = coolingType;
            return this;
        }

        public Builder setMotherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }

        public Builder setCaseType(String caseType) {
            this.caseType = caseType;
            return this;
        }

        public GamingPC build() {
            if (cpu == null || gpu == null) {
                throw new IllegalStateException("CPU and GPU are required to build a Gaming PC");
            }

            if (psuWatts <= 0) {
                throw new IllegalStateException("PSU wattage must be specified and positive");
            }

            if (gpu.contains("RTX") && psuWatts < MIN_PSU_FOR_HIGH_END_GPU) {
                throw new IllegalStateException(
                        "High-end GPU requires at least " + MIN_PSU_FOR_HIGH_END_GPU + "W PSU, got " + psuWatts + "W"
                );
            }

            return new GamingPC(this);
        }
    }
}