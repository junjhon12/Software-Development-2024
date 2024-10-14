public class ExternalSalaryAdapter implements Compensable {
    private ExternalSalaryCalculator externalCalculator;

    public ExternalSalaryAdapter(ExternalSalaryCalculator externalCalculator, double d, double e) {
        this.externalCalculator = externalCalculator;
    }

    @Override
    public double calculateTotalCompensation() {
        // Adapt third-party logic to match Compensable interface
        return externalCalculator.calculateExternalSalary();
    }
}
