package model;

public class ChocolateToppingsDecorator extends ToppingsDecorator {

    private Cookie cookie;

    public ChocolateToppingsDecorator(Cookie cookie){
        this.cookie = cookie;
    }

    @Override
    public String getType() {
        return cookie.getType() + " with ChocolateTopping ";
    }

    public Double getPrice(){
        return cookie.getPrice() + 2;
    }
}
