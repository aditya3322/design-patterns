package structural.patterns.adaptor;

public class AdapterDemo {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5); // Hole with radius 5
        
        // 1. Standard Round Peg
        RoundPeg rpeg = new RoundPeg(5);
        System.out.println("Does round peg (r5) fit? " + hole.fits(rpeg));

        // 2. Square Pegs
        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg largeSqPeg = new SquarePeg(10);

        // hole.fits(smallSqPeg); // This would NOT compile!

        // 3. Using the Adapter
        SquarePegAdapter smallAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter largeAdapter = new SquarePegAdapter(largeSqPeg);

        System.out.println("Does square peg (w2) fit? " + hole.fits(smallAdapter)); // True
        System.out.println("Does square peg (w10) fit? " + hole.fits(largeAdapter)); // False
    }
}