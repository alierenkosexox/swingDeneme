import java.sql.Connection;

import core.Database;
import view.UserView;

public class App {
    public static void main(String[] args) {

        Database.connection();
        UserView userView = new UserView();
    }
}
