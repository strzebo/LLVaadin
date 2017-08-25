package logic;

public class Konta
{

    private Administrator administrator;
    private int ID;
    private User user;

    public Konta(Administrator administrator, int ID, User user)
    {
        this.administrator = administrator;
        this.ID = ID;
        this.user = user;
    }

    public Administrator getAdministrator() {   return administrator;   }

    public int getID()  {   return ID;  }

    public User getUser()   {   return user;    }
}
