package structural.patterns.bridge;

public class Radio implements Device{

    private int volume;

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void enable() {
        System.out.println("Radio is ON");
    }

    @Override
    public void disable() {
        System.out.println("Radio is OFF");
    }

    @Override
    public int getVolume() {
        return this.volume;
    }

    @Override
    public void setVolume(int percent) {
        this.volume = this.volume + this.volume * (percent/100);
    }



}
