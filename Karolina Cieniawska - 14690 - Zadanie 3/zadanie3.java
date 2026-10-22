public class OrderCalculator {

    private static final double TAX_RATE = 0.23;
    private static final double VIP_DISCOUNT_RATE = 0.10;

    public double calculateTotal(Order order) {
        if (order == null) {
            return 0.0;
        }

        double subtotal = order.getItemsPrice();
        double tax = subtotal * TAX_RATE;
        double discount = calculateDiscount(order);

        return (subtotal + tax) - discount;
    }

    private double calculateDiscount(Order order) {
        return order.isVIP() ? order.getItemsPrice() * VIP_DISCOUNT_RATE : 0.0;
    }
}