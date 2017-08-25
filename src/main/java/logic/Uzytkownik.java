package logic;

public class Uzytkownik extends User
{

    public Uzytkownik(String name, String lastName, String email, String password, String address, String phoneNumber, String idNumber)
    {
        super(name, lastName, email, password, address, phoneNumber, idNumber);
    }

    public String getType() { return "1"; }
}
