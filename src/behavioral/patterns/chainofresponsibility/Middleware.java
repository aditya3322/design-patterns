package behavioral.patterns.chainofresponsibility;

abstract class Middleware {
    private Middleware next;

    public static Middleware link(Middleware first, Middleware... chain) {
        Middleware head = first;
        for (Middleware nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract boolean check(Request request);

    protected boolean checkNext(Request request) {
        if (next == null) {
            return true; // End of chain reached successfully
        }
        return next.check(request);
    }
}