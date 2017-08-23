package logic;

public class Kierownik extends User
{

    public Kierownik( String name, String lastName, String email, String login, String password)
    {
        super(name, lastName, email, login, password);
    }

    public String getType() { return "Sprzedawca"; }
}
