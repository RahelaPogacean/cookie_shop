package model;

public class MediumCookie extends Cookie {

    private Cookie cookie;

    public MediumCookie(Cookie cookie) {
        this.cookie = cookie;
        setType(this.getType());
        setPrice(this.getPrice());
    }

    @Override
    public String getType() {
        return cookie.getType() + " , MediumCookie";
    }

    @Override
    public Double getPrice() {
        return cookie.getPrice();
    }
}