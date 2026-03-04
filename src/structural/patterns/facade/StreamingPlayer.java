package structural.patterns.facade;

public class StreamingPlayer {
    public void on() {
        System.out.println("Streaming player is ON");
    }

    public void off() {
        System.out.println("Streaming player is OFF");
    }

    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }

    public void stop() {
        System.out.println("Stopping streaming player");
    }
}
