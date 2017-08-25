package logic;

public class Kierownik extends User
{

    public Kierownik(String name, String lastName, String email, String password, String address, String phoneNumber, String idNumber)
    {
        super(name, lastName, email, password, address, phoneNumber, idNumber);
    }

    public String getType() { return "3"; }
}
