package structural.patterns.decorator;

public class Main {
    public static void main(String[] args) {
        // Create a basic Email notifier
        Notifier emailNotifier = new EmailNotifier();

        // Decorate it with SMS and Slack notifications
        Notifier smsAndSlackNotifier = new SMSNotifier(new SlackNotifier(emailNotifier));

        // Send a notification
        smsAndSlackNotifier.send("Hello, this is a notification!");
    }
}
