import java.util.List;

public class FinancialReportGenerator {

    private static final double TAX_RATE = 1.23;

    public String generateReport(int month, int year, boolean includeTax) {
        List<Invoice> paidInvoices = Database.findInvoices(month, year);
        double totalSum = calculateTotalSum(paidInvoices, includeTax);

        return String.format("REPORT: Month %d/%d Total = %.2f", month, year, totalSum);
    }

    public void markInvoicesAsReported(int month, int year) {
        List<Invoice> invoices = Database.findInvoices(month, year);
        for (Invoice inv : invoices) {
            if ("PAID".equalsIgnoreCase(inv.getStatus())) {
                Database.executeUpdate("UPDATE invoices SET reported = true WHERE id = " + inv.getId());
            }
        }
    }

    private double calculateTotalSum(List<Invoice> invoices, boolean includeTax) {
        double sum = 0;
        for (Invoice inv : invoices) {
            if ("PAID".equalsIgnoreCase(inv.getStatus())) {
                sum += inv.getAmount();
            }
        }
        return includeTax ? sum * TAX_RATE : sum;
    }
}