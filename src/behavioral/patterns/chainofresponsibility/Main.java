package behavioral.patterns.chainofresponsibility;

public class Main {
    public static void main(String[] args) {
        // Build the chain: Log -> Auth -> Role
        Middleware chain = Middleware.link(
            new LoggingMiddleware(),
            new AuthMiddleware(),
            new RoleMiddleware()
        );

        // Scenario A: Guest user trying to access Admin panel
        System.out.println("--- Scenario A ---");
        Request reqA = new Request("/admin/delete", false, "GUEST");
        if (chain.check(reqA)) {
            System.out.println("Success: Access granted!");
        } else {
            System.out.println("Result: Access denied.");
        }

        // Scenario B: Admin user
        System.out.println("\n--- Scenario B ---");
        Request reqB = new Request("/admin/delete", true, "ADMIN");
        if (chain.check(reqB)) {
            System.out.println("Success: Access granted!");
        }
    }
}