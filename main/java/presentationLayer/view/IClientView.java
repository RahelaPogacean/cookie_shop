package presentationLayer.view;

    interface IClientViewDataProvider {

        String getUsername();
        String getPassword();
        String getEmail();
        Double getAmmount();

    }

    public interface IClientView extends IClientViewDataProvider {

    }

