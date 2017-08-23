package logic;

public class Uzytkownik extends User
{

    public Uzytkownik(String name, String lastName, String email, String login, String password)
    {
        super(name, lastName, email, login, password);
    }

    public String getType() { return "Użytkownik"; }
}
