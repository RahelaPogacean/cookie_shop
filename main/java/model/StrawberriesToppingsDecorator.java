package model;

public class StrawberriesToppingsDecorator extends ToppingsDecorator {

    private Cookie cookie;

    public StrawberriesToppingsDecorator(Cookie cookie){
        this.cookie = cookie;
    }

    @Override
    public String getType() {
        return cookie.getType() + " with StrawberriesTopping ";
    }

    public Double getPrice(){
        return cookie.getPrice() + 1.5;
    }
}
