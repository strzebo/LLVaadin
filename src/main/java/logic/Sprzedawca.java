package logic;

public class Sprzedawca extends User
{

    public Sprzedawca(String name, String lastName, String email, String password, String address, String phoneNumber, String idNumber)
    {
        super(name, lastName, email, password, address, phoneNumber, idNumber);
    }

    public String getType() { return "2"; }
}