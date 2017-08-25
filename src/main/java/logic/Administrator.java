package logic;

public class Administrator extends User
{

    public Administrator(String name, String lastName, String email, String password, String address, String phoneNumber, String idNumber)
    {
        super(name, lastName, email, password, address, phoneNumber, idNumber);
    }

    public String getType() { return "4"; }
}
