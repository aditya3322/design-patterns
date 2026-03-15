package behavioral.patterns.chainofresponsibility;

class Request {
    public String url;
    public boolean isAuthenticated;
    public String userRole;

    public Request(String url, boolean auth, String role) {
        this.url = url;
        this.isAuthenticated = auth;
        this.userRole = role;
    }
}