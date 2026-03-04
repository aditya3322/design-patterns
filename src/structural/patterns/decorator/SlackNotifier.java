package structural.patterns.decorator;

public class SlackNotifier extends BaseDecorator{

    public SlackNotifier(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        sendSlack(message);
    }

    public void sendSlack(String message) {
        System.out.println("Sending Slack notification: " + message);
    }
}
