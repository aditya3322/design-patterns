package structural.patterns.adaptor;

class SquarePegAdapter extends RoundPeg {
    private SquarePeg peg;

    public SquarePegAdapter(SquarePeg peg) {
        this.peg = peg;
    }

    @Override
    public double getRadius() {
        // To find the minimum radius that fits a square:
        // Radius = (width * sqrt(2)) / 2
        return (peg.getWidth() * Math.sqrt(2)) / 2;
    }
}