package structural.patterns.decorator;

/**
 * The Task: The Smart Home Notification System
 * The Scenario:
 * You are building a notification library. Initially, the system only sends basic Email notifications.
 * However, users want to be able to "toggle on" additional channels like SMS and Slack—sometimes one, sometimes both, and always wrapping the original email.
 */

public interface Notifier {

    public void send(String message);

}
