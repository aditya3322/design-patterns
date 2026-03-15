package behavioral.patterns.chainofresponsibility;

public class RoleMiddleware extends Middleware{
    @Override
    public boolean check(Request request) {

        if (request.userRole.equalsIgnoreCase("ADMIN")) {
            System.out.println("User is authorized against valid role");
            return checkNext(request);
        }
        System.out.println("Auth Failure: 403 Unauthorized");
        return false;
    }
}
