import businessLayer.service.AdminService;
import businessLayer.service.ClientService;
import model.Admin;
import model.Client;
import org.junit.jupiter.api.Test;

import presentationLayer.view.ILoginView;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

    public class LoginControllerTests {
        @Test
        public void givenAdminUsernameAndPassword_login_showAdminView() throws SQLException, IOException, ClassNotFoundException {
            Admin expected = new Admin(1, "Rahe", "necti");
            ILoginView loginView = mock(ILoginView.class);
            when(loginView.getUsername()).thenReturn("Rahe");
            when(loginView.getPassword()).thenReturn("necti");

            AdminService adminService = new AdminService();
            Admin actual = adminService.findAdminByName("Rahe");

            assertEquals(expected.toString(), actual.toString());
        }

        @Test
        public void givenRegularUsernameAndPassword_login_showRegularView() throws SQLException, IOException, ClassNotFoundException {
            Client expected = new Client(1, "Ana", "ana", "ana", 985.0);
            ILoginView loginView = mock(ILoginView.class);
            when(loginView.getUsername()).thenReturn("Ana");
            when(loginView.getPassword()).thenReturn("ana");

            ClientService clientService = new ClientService();
            Client actual = clientService.findClientByName("Ana");

            assertEquals(expected.toString(), actual.toString());
        }


        class TestLoginView implements ILoginView
        {
            private final String username;
            private final String password;

            TestLoginView(String username, String password)
            {
                this.username = username;
                this.password = password;
            }
            @Override
            public void showAdminView() {

            }

            @Override
            public void showRegularView() {

            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public String getPassword() {
                return password;
            }

            public String shownErrorMessage;
            @Override
            public void showErrorMessage(String message) {
                shownErrorMessage = message;
            }
        }
    }

