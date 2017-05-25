package litecart.src.java.model;

/**
 * Created by valeriyagagarina on 5/19/17.
 */
public class User {

    public String firstName;
    public String lastName;
    public String address1;
    public String email;
    public String password;

    public User(String firstName, String lastName, String address1, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.email = email;
        this.password = password;
    }

}
