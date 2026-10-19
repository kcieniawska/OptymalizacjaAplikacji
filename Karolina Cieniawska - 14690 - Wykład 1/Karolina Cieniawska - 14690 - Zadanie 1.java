// ============================================
//
// ZADANIE 1
//
// ============================================

public class EmployeeBonusCalculator {

    private static final double BASE_MULTIPLIER = 1.0;
    private static final double LOW_BONUS_MULTIPLIER = 1.05;
    private static final double MEDIUM_BONUS_MULTIPLIER = 1.10;
    private static final double HIGH_BONUS_MULTIPLIER = 1.15;
    private static final double VERY_HIGH_BONUS_MULTIPLIER = 1.25;

    private static final int MIN_YEARS_FOR_MEDIUM_BONUS = 2;
    private static final int MIN_YEARS_FOR_HIGH_BONUS = 5;
    public double calculateTotalSalaryWithBonus(double baseSalary, int yearsOfService, boolean isManager) {
        double bonusMultiplier = getBonusMultiplier(yearsOfService, isManager);
        
        return baseSalary * bonusMultiplier;
    }
    private double getBonusMultiplier(int yearsOfService, boolean isManager) {
        if (yearsOfService < MIN_YEARS_FOR_MEDIUM_BONUS) {
            return isManager ? LOW_BONUS_MULTIPLIER : BASE_MULTIPLIER;
        }
        
        if (yearsOfService < MIN_YEARS_FOR_HIGH_BONUS) {
            return isManager ? MEDIUM_BONUS_MULTIPLIER : LOW_BONUS_MULTIPLIER;
        }

        return isManager ? VERY_HIGH_BONUS_MULTIPLIER : HIGH_BONUS_MULTIPLIER;
    }
}