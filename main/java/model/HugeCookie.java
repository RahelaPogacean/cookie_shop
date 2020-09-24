package model;

public class HugeCookie extends Cookie {

    private Cookie cookie;

    public HugeCookie(Cookie cookie){
        this.cookie = cookie;
        setType(this.getType());
        setPrice(this.getPrice());
    }

    @Override
    public String getType(){
        return cookie.getType() + " , HugeCookie";
    }

    @Override
    public Double getPrice(){
        return cookie.getPrice() + 2;
    }
}
