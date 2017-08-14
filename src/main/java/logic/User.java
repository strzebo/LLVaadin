package logic;

public class User
{
    protected String name;
    protected String lastName;
    protected String email;
    protected String login;
    protected String password;

    public User(String name, String lastName, String email, String login, String password)
    {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getName() {   return name;    }

    public String getLastName() {   return lastName;    }

    public String getEmail()    {   return email;   }

    public String getLogin()    {   return login;   }

    public String getPassword() {   return password;    }

    public String getType() {   return "";  }
}
