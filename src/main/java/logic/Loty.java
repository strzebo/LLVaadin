package logic;

import java.sql.Time;
import java.util.Currency;
import java.util.Date;

public class Loty
{
    private int ID;
    private String lotniskoStartowe;
    private String lotniskoDocelowe;
    private Date dataOdlotu;
    private Date dataPrzylotu;
    private Time godzinaOdlotu;
    private Time godzinaPrzylotu;
    private Currency cenaBiletu;

    public Loty(int ID, String lotniskoStartowe, String lotniskoDocelowe, Date dataOdlotu, Date dataPrzylotu, Time godzinaOdlotu, Time godzinaPrzylotu, Currency cenaBiletu)
    {
        this.ID = ID;
        this.lotniskoStartowe = lotniskoStartowe;
        this.lotniskoDocelowe = lotniskoDocelowe;
        this.dataOdlotu = dataOdlotu;
        this.dataPrzylotu = dataPrzylotu;
        this.godzinaOdlotu = godzinaOdlotu;
        this.godzinaPrzylotu = godzinaPrzylotu;
        this.cenaBiletu = cenaBiletu;
    }

    public int getID() {    return ID;  }

    public String getLotniskoStartowe() {   return lotniskoStartowe;    }

    public String getLotniskoDocelowe() {   return lotniskoDocelowe;    }

    public Date getDataOdlotu() {   return dataOdlotu;  }

    public Date getDataPrzylotu()   {   return dataPrzylotu;    }

    public Time getGodzinaOdlotu()  {   return godzinaOdlotu;   }

    public Time getGodzinaPrzylotu()    {   return godzinaPrzylotu; }

    public Currency getCenaBiletu() {   return cenaBiletu;  }



}
