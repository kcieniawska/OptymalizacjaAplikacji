import java.util.Date;

public class NotificationService {

    private final Database db = new Database();

    public static class NotificationResult {
        private final boolean success;
        private final String logMessage;

        public NotificationResult(boolean success, String logMessage) {
            this.success = success;
            this.logMessage = logMessage;
        }

        public boolean isSuccess() { return success; }
        public String getLogMessage() { return logMessage; }
    }

    public NotificationResult sendNotification(User user, String msg, String type) {
        if (!isEligibleForNotification(user)) {
            return new NotificationResult(false, "FAILURE: Could not send notification\n");
        }

        if ("EMAIL".equalsIgnoreCase(type)) {
            return processEmailNotification(user, msg);
        } else if ("SMS".equalsIgnoreCase(type)) {
            return processSmsNotification(user, msg);
        }

        return new NotificationResult(false, "FAILURE: Unsupported notification type\n");
    }

    private boolean isEligibleForNotification(User user) {
        return user != null && user.isActive();
    }

    private NotificationResult processEmailNotification(User user, String msg) {
        String formattedMsg = buildEmailBody(user.getName(), msg);
        sendSmtpEmail(formattedMsg);

        logNotificationToDatabase("email", user.getId());
        user.setLastNotifiedAt(new Date());

        return new NotificationResult(true, "SUCCESS: Email sent to " + user.getEmail() + "\n");
    }

    private NotificationResult processSmsNotification(User user, String msg) {
        if (user.getPhone() == null || user.getPhone().length() < 9) {
            return new NotificationResult(false, "FAILURE: Invalid phone number\n");
        }

        String smsText = "[ALERT] " + msg;
        sendSmsViaGateway(smsText);

        logNotificationToDatabase("sms", user.getId());
        user.setLastNotifiedAt(new Date());

        return new NotificationResult(true, "SUCCESS: SMS sent to " + user.getPhone() + "\n");
    }

    private String buildEmailBody(String userName, String msg) {
        return "<html><body><h1>Cześć " + userName + "</h1><p>" + msg + "</p></body></html>";
    }

    private void sendSmtpEmail(String body) {
        System.out.println("Connecting to SMTP server at smtp.example.com:25...");
    }

    private void sendSmsViaGateway(String smsText) {
        System.out.println("Sending SMS via Gateway...");
    }

    private void logNotificationToDatabase(String type, String userId) {
        db.executeUpdate("INSERT INTO logs VALUES ('" + type + "', '" + userId + "')");
    }
}