package logic;

public class Sprzedawca extends User
{

    public Sprzedawca( String name, String lastName, String email, String login, String password)
    {
        super(name, lastName, email, login, password);
    }

    public String getType() { return "Sprzedawca"; }
}