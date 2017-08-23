package logic;

public class Administrator extends User
{

    public Administrator( String name, String lastName, String email, String login, String password)
    {
        super(name, lastName, email, login, password);
    }

    public String getType() { return "Sprzedawca"; }
}
