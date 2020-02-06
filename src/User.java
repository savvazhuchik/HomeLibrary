/*
*
*   Класс обычного пользователя
*
* */

public class User extends Account {

    public User(String username, String password) {
        super(username, password, "User");
    }

    public User(String username) {
        super(username, "User");
    }

    @Override
    public String getType() {
        return "User";
    }

    @Override
    public String toString() {
        return super.toString() + ", User";
    }
}
