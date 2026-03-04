package structural.patterns.facade;

public class Lights {

    public void on() {
        System.out.println("Lights are ON");
    }

    public void off() {
        System.out.println("Lights are OFF");
    }

    public void dim(int level) {
        System.out.println("Dimming lights to " + level + "%");
    }
}
