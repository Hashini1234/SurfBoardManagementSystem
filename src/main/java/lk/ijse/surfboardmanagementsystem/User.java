package lk.ijse.surfboardmanagementsystem;

public class User {
    private static String username;
    private static String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static boolean verifyLogin(String inputUsername, String inputPassword) {
        return username.equals(inputUsername) && password.equals(inputPassword);
    }

}