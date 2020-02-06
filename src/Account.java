/*
*
*   Класс описывает общие характеристики аккаунта.
*
* */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public abstract class Account {

    private String username, password, type;

    public Account(String username, String password, String type)  {
        this.username = username;

        // Хэшируем пароль в MD5
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            hash = Base64.getEncoder().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }

        this.password = hash;
    }

    public Account(String username, String type) {
        this.username = username;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.username + ", " + this.password;
    }
}
