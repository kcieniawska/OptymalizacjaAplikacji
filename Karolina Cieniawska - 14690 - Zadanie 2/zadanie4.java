public class OrderProcessor {
    public boolean validateAndPrepareOrder(Order order) {
        if (!isOrderValid(order)) {
            return false;
        }

        applyCustomerDiscount(order);

        if (!ensureItemsInStock(order)) {
            return false;
        }

        calculateAndSetTotalAmount(order);
        return true;
    }

    private boolean isOrderValid(Order order) {
        return order != null && order.getCustomer() != null && order.getItems() != null && !order.getItems().isEmpty();
    }

    private void applyCustomerDiscount(Order order) {
        Customer customer = order.getCustomer();
        if (customer.isVIP()) {
            order.setDiscount(0.15);
        } else if (customer.getOrdersCount() > 10) {
            order.setDiscount(0.05);
        }
    }

    private boolean ensureItemsInStock(Order order) {
        for (Item item : order.getItems()) {
            if (item.getStockQuantity() < item.getRequestedQuantity()) {
                System.out.println("Lack of item: " + item.getName());
                return false;
            }
        }
        return true;
    }
    private void calculateAndSetTotalAmount(Order order) {
        double rawTotal = 0;
        for (Item item : order.getItems()) {
            rawTotal += item.getPrice() * item.getRequestedQuantity();
        }
        double finalTotal = rawTotal - (rawTotal * order.getDiscount());
        order.setTotalAmount(finalTotal);
    }
}