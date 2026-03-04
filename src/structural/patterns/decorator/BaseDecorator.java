package structural.patterns.decorator;

public abstract class BaseDecorator implements Notifier{

    protected final Notifier wrappedNotifier;

    public BaseDecorator(Notifier notifier) {
        this.wrappedNotifier = notifier;
    }

    @Override
    public void send(String message) {
        wrappedNotifier.send(message);
    }
}
