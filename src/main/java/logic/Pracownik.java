package logic;

public class Pracownik extends User
{


    public Pracownik( String name, String lastName, String gender, String email, String login, String password)
    {
        super(name, lastName, gender, email, login, password);
    }

    public String getType() { return "Pracownik"; }
}
