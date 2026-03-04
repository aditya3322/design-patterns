package structural.patterns.facade;

public class Projector {
    public void on() {
        System.out.println("Projector is ON");
    }

    public void off() {
        System.out.println("Projector is OFF");
    }

    public void setInput(String source) {
        System.out.println("Setting projector input to " + source);
    }
}
