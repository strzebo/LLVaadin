package logic;

public class User
{
    protected String name;
    protected String lastName;
    protected String email;
    protected String password;
    protected String address;
    protected String phoneNumber;
    protected String idNumber;
    protected int typKonta;


    public User(String name, String lastName, String email, String password, String address, String phoneNumber, String idNumber, int typKonta)
    {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
        this.typKonta = typKonta;
    }

    public String getName() {   return name;    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {   return lastName;    }

    public String getEmail()    {   return email;   }

    public String getPassword() {   return password;    }

    public String getAddress() {    return address;    }

    public String getPhoneNumber() {    return phoneNumber; }

    public String getIdNumber() {   return idNumber; }

    public int getType() {   return typKonta;  }
}
