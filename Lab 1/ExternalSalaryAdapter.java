public class ExternalSalaryAdapter implements Compensable {
    private ExternalSalaryCalculator externalCalculator;

    public ExternalSalaryAdapter(ExternalSalaryCalculator externalCalculator) {
        this.externalCalculator = externalCalculator;
    }

    @Override
    public double calculateTotalCompensation() {
        // Adapt third-party logic to match Compensable interface
        return externalCalculator.calculateExternalSalary();
    }
}
