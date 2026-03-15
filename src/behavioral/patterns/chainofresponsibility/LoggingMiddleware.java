package behavioral.patterns.chainofresponsibility;

public class LoggingMiddleware extends  Middleware{
    @Override
    public boolean check(Request request) {
        System.out.println("Log: Requesting URL" + request.url);
        return this.checkNext(request);
    }
}
