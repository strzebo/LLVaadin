package logic;

public class Rezerwacje
{
    private int ID;
    private int IDLotu;
    private int IDKlienta;
    private String stanRezerwacji;

    public Rezerwacje(int ID, int IDLotu, int IDKlienta, String stanRezerwacji) {
        this.ID = ID;
        this.IDLotu = IDLotu;
        this.IDKlienta = IDKlienta;
        this.stanRezerwacji = stanRezerwacji;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDLotu() {
        return IDLotu;
    }

    public void setIDLotu(int IDLotu) {
        this.IDLotu = IDLotu;
    }

    public int getIDKlienta() {
        return IDKlienta;
    }

    public void setIDKlienta(int IDKlienta) {
        this.IDKlienta = IDKlienta;
    }

    public String getStanRezerwacji() {
        return stanRezerwacji;
    }

    public void setStanRezerwacji(String stanRezerwacji) {
        this.stanRezerwacji = stanRezerwacji;
    }
}
