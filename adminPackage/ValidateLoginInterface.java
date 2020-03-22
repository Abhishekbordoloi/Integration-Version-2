package adminPackage;

public interface ValidateLoginInterface {
    void setUsername(String username);
    void setPassword(String password);
    void setChoice(String choice);
    String getUsername();
    String getPassword();
    String getChoice();
    void login() throws InterruptedException;
}
