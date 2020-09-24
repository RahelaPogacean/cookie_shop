package model;

public class LemonToppingsDecorator extends ToppingsDecorator {

    private Cookie cookie;

    public LemonToppingsDecorator(Cookie cookie){
        this.cookie = cookie;
    }

    @Override
    public String getType() {
        return cookie.getType() + " with LemonTopping ";
    }

    public Double getPrice(){
        return cookie.getPrice() + 1;
    }
}
