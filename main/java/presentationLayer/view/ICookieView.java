package presentationLayer.view;


    interface ICookieViewDataProvider {

        String getCookieType();
        Double getCookiePrice();
        Double getCookieSweets();
        int getCookieStock();
        Double getCookieRating();
    }

    public interface ICookieView extends ICookieViewDataProvider {

        Double getCookieRating();
    }

