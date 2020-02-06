/*
*
*   Класс описывает администратора.
*
* */

public class Admin extends Account {

    public Admin(String username, String password) {
        super(username, password, "Admin");
    }

    public Admin(String username) {
        super(username, "Admin");
    }

    @Override
    public String toString() {
        return super.toString() + ", Admin";
    }

    @Override
    public String getType() {
        return "Admin";
    }
}
