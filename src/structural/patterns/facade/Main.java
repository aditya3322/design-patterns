package structural.patterns.facade;

public class Main {
    public static void main() {
           Lights lights = new Lights();
           Projector projector = new Projector();
           SoundSystem soundSystem = new SoundSystem();
           StreamingPlayer streamingPlayer = new StreamingPlayer();

           HomeTheaterFacade movieNight = new HomeTheaterFacade(lights, projector, soundSystem, streamingPlayer);

            System.out.println("--- Getting ready to watch a movie ---");
            movieNight.watchMovie("Inception");

            System.out.println("\n--- Movie is over ---");
            movieNight.endMovie();
    }
}
