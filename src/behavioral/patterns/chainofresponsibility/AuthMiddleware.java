package behavioral.patterns.chainofresponsibility;

public class AuthMiddleware extends Middleware{
    @Override
    public boolean check(Request request) {

        if(request.isAuthenticated) {
            System.out.println("User is authenticated ");
            return this.checkNext(request);
        }
        System.out.println("Auth failure: 401 unauthorized");
        return false;
    }
}
