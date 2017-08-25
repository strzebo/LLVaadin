package logic;

public class Rezerwacje
{
    private int ID;
    private User user;
    private String nazwa;
    private Loty loty;

    public Rezerwacje(int ID, User user, String nazwa, Loty loty)
    {
        this.ID = ID;
        this.user = user;
        this.nazwa = nazwa;
        this.loty = loty;
    }

    public int getID()  {   return ID;  }

    public User getUser()   {   return user;    }

    public String getNazwa()    {   return nazwa;   }

    public  Loty getLoty()  {   return loty; }

}
