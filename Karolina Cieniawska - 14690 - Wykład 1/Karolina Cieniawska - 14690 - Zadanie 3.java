// ============================================
//
// ZADANIE 3
//
// ============================================
public class OrderValidator {
    private static final String CUSTOMER_TYPE_VIP = "VIP";
    private static final String CUSTOMER_TYPE_REGULAR = "REG";
    
    private static final double VIP_MIN_AMOUNT = 1000.0;
    private static final int VIP_MIN_QUANTITY = 5;

    private static final double REGULAR_MIN_AMOUNT = 500.0;
    private static final int REGULAR_MIN_QUANTITY = 2;

    private static final int EXPECTED_COUNTRY_CODE_LENGTH = 2;

    public boolean isValidOrder(String customerType, double amount, int quantity, String countryCode) {
        
        if (!hasValidInputs(customerType, amount, quantity, countryCode)) {
            return false;
        }

        if (CUSTOMER_TYPE_VIP.equals(customerType)) {
            return amount > VIP_MIN_AMOUNT && quantity > VIP_MIN_QUANTITY;
        }

        if (CUSTOMER_TYPE_REGULAR.equals(customerType)) {
            return amount > REGULAR_MIN_AMOUNT && quantity > REGULAR_MIN_QUANTITY;
        }

        return false;
    }
    private boolean hasValidInputs(String customerType, double amount, int quantity, String countryCode) {
        if (customerType == null || customerType.trim().isEmpty()) {
            return false;
        }
        if (amount <= 0 || quantity <= 0) {
            return false;
        }
        return countryCode != null && countryCode.length() == EXPECTED_COUNTRY_CODE_LENGTH;
    }
}