package structural.patterns.decorator;

public class SMSNotifier extends BaseDecorator {

    public SMSNotifier(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message); // Send the original notification (e.g., Email)
        sendSMS(message);   // Add SMS notification
    }

    private void sendSMS(String message) {
        System.out.println("Sending SMS notification: " + message);
    }
}
