package structural.patterns.facade;

public class HomeTheaterFacade {
    Lights lights;
    Projector projector;
    SoundSystem soundSystem;
    StreamingPlayer streamingPlayer;

    public HomeTheaterFacade(Lights lights, Projector projector, SoundSystem soundSystem, StreamingPlayer streamingPlayer) {
        this.lights = lights;
        this.projector = projector;
        this.soundSystem = soundSystem;
        this.streamingPlayer = streamingPlayer;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        lights.dim(10);
        projector.on();
        projector.setInput("Streaming Player");
        soundSystem.on();
        soundSystem.setVolume(5);
        streamingPlayer.on();
        streamingPlayer.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting down movie theater...");
        streamingPlayer.stop();
        streamingPlayer.off();
        soundSystem.off();
        projector.off();
        lights.on();
    }
}
