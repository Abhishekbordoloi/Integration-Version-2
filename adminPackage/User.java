



/*@bordoloa*/
/*YOU CAN DELETE THIS PACKAGE AND IMPORT ""   user.*   "" PACKAGE
 * (User class is there with default and parameterized constructors)*/

package adminPackage;

public class User {
    String name, status;
    String password;
    int phone_num;

    public String getStatus() {
        return status;
    }
    public void setStatus(String string) {
        this.status = status;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(int phone_num) {
        this.phone_num = phone_num;
    }

    @Override
    public String toString() {
        String s = this.name +  " " + this.password + " " + this.phone_num + " " + this.status;
        return s;
    }
}
