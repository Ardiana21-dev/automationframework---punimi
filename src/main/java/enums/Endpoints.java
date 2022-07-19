package enums;

public enum Endpoints {
    //base uri
    BOOKING_BASE_URI("https://restful-booker.herokuapp.com"),

    // complete endpoints
    AUTH("/auth"),
    BOOKING_DETAILS("https://restful-booker.herokuapp.com/booking/{bookingID}");

    private final String url;

    Endpoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getEndpoint() {
        return url;
    }
}
