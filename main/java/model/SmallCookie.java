package model;

public class SmallCookie extends Cookie {

    private Cookie cookie;

    public SmallCookie(Cookie cookie){
        this.cookie = cookie;
        setType(this.getType());
        setPrice(this.getPrice());
    }

    @Override
    public String getType(){
        return cookie.getType() + " , SmallCookie";
    }

    @Override
    public Double getPrice(){
        return cookie.getPrice() - 2;
    }
}
