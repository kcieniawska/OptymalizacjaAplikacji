// ============================================
//
// ZADANIE 4
//
// ============================================

public class OrderProcessor {

    private static final int LONG_TERM_CUSTOMER_DAYS_THRESHOLD = 30;
    private static final double LONG_TERM_DISCOUNT_MULTIPLIER = 0.80; // 20% rabatu

    private static final double VIP_DISCOUNT_MULTIPLIER = 0.90; // 10% rabatu
    private static final double HIGH_VALUE_ORDER_THRESHOLD = 500.0;
    private static final double HIGH_VALUE_DISCOUNT_MULTIPLIER = 0.95; // 5% rabatu
    private static final String DELIVERY_TYPE_EXPRESS = "EXPRESS";
    private static final double EXPRESS_DELIVERY_FEE = 50.0;
    private static final double STANDARD_DELIVERY_FEE = 15.0;

    public double calculateTotalOrderCost(String deliveryType, double[] itemPrices, int customerTenureDays, boolean isVipCustomer) {
        double subtotal = calculateSubtotal(itemPrices);
        double discountedSubtotal = applyDiscounts(subtotal, customerTenureDays, isVipCustomer);
        double deliveryCost = calculateDeliveryFee(deliveryType);

        return discountedSubtotal + deliveryCost;
    }
    private double calculateSubtotal(double[] itemPrices) {
        if (itemPrices == null) {
            return 0.0;
        }
        double sum = 0.0;
        for (double price : itemPrices) {
            sum += price;
        }
        return sum;
    }
    private double applyDiscounts(double initialAmount, int customerTenureDays, boolean isVipCustomer) {
        double amount = initialAmount;

        if (customerTenureDays > LONG_TERM_CUSTOMER_DAYS_THRESHOLD) {
            amount *= LONG_TERM_DISCOUNT_MULTIPLIER;
        }

        if (isVipCustomer) {
            amount *= VIP_DISCOUNT_MULTIPLIER;
        } else if (amount > HIGH_VALUE_ORDER_THRESHOLD) {
            amount *= HIGH_VALUE_DISCOUNT_MULTIPLIER;
        }

        return amount;
    }
    private double calculateDeliveryFee(String deliveryType) {
        if (DELIVERY_TYPE_EXPRESS.equalsIgnoreCase(deliveryType)) {
            return EXPRESS_DELIVERY_FEE;
        }
        return STANDARD_DELIVERY_FEE;
    }
}