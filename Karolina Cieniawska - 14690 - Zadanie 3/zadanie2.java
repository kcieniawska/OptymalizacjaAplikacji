import java.util.List;

public class EmployeeProcessor {

    private static final int RETIREMENT_YEARS_WORKED_THRESHOLD = 30;
    private static final int RETIREMENT_AGE_THRESHOLD = 65;
    
    private static final double PENSION_BONUS_RATE = 0.25;
    private static final double LOYALTY_FLAT_BONUS = 1500.0;

    public void processEmployees(List<Employee> employees) {
        if (employees == null) {
            return;
        }

        for (Employee employee : employees) {
            if (isEligibleForFullRetirementBenefits(employee)) {
                applyFullRetirementBonus(employee);
            }
        }
    }

    private boolean isEligibleForFullRetirementBenefits(Employee employee) {
        return employee.getYearsWorked() > RETIREMENT_YEARS_WORKED_THRESHOLD 
            && employee.getAge() >= RETIREMENT_AGE_THRESHOLD;
    }

    private void applyFullRetirementBonus(Employee employee) {
        double bonusAmount = calculateFullRetirementBonus(employee.getSalary());
        employee.setBonus(bonusAmount);
    }

    private double calculateFullRetirementBonus(double salary) {
        return (salary * PENSION_BONUS_RATE) + LOYALTY_FLAT_BONUS;
    }
}