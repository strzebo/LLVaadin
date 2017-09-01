package view;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import logic.Main;
import logic.Uzytkownik;

import java.sql.SQLException;

public class Konto extends VerticalLayout implements View
{
    Main main = new Main();
    Grid<Uzytkownik> gridUzytkownicy = new Grid<>();
    Binder<Uzytkownik> binder = new Binder<>(Uzytkownik.class);
    Uzytkownik uzytkownik;
    int ID;
    TextField name = new TextField("Imię");
    TextField lastName = new TextField("Nazwisko");
    TextField email = new TextField("Email");
    PasswordField pass = new PasswordField("Hasło");
    TextField address = new TextField("Adres");
    TextField phoneNumber = new TextField("Numer telefonu");
    TextField idNumber = new TextField("Numer dokumentu");
    TextField accountType = new TextField("Typ konta");
    Button save = new Button("Zapisz"); //, e -> zapiszUzytkownika(ID, name.getValue(), lastName.getValue(), email.getValue(), pass.getValue(), address.getValue(), phoneNumber.getValue(), idNumber.getValue(), Integer.parseInt(accountType.getValue())));


    public Konto()
    {
        FormLayout formLayout = new FormLayout();

        setMargin(true);
        setSpacing(true);

        formLayout.setMargin(false);
        formLayout.setWidth("35%");;
        formLayout.addStyleName("dark");

        addComponent(formLayout);
        setComponentAlignment(formLayout, Alignment.TOP_CENTER);

        HorizontalLayout footer = new HorizontalLayout();

        footer.setMargin(new MarginInfo(false, false, true, false));
        footer.setSpacing(true);

        gridUzytkownicy.setWidth("1050");
        gridUzytkownicy.setHeight("200");
        try
        {
            gridUzytkownicy.setItems(main.getUzytkownikList(main.getUserType()));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        gridUzytkownicy.setSelectionMode(Grid.SelectionMode.SINGLE);
        SingleSelect<Uzytkownik> selection = gridUzytkownicy.asSingleSelect();

        gridUzytkownicy.addColumn(Uzytkownik::getID).setCaption("ID");
        gridUzytkownicy.addColumn(Uzytkownik::getName).setCaption("Imię");
        gridUzytkownicy.addColumn(Uzytkownik::getLastName).setCaption("Nazwisko");
        gridUzytkownicy.addColumn(Uzytkownik::getEmail).setCaption("E-mail");
        gridUzytkownicy.addColumn(Uzytkownik::getPassword).setCaption("Hasło");
        gridUzytkownicy.addColumn(Uzytkownik::getAddress).setCaption("Adres");
        gridUzytkownicy.addColumn(Uzytkownik::getPhoneNumber).setCaption("Numer telefonu");
        gridUzytkownicy.addColumn(Uzytkownik::getIdNumber).setCaption("Numer dokumentu");
        gridUzytkownicy.addColumn(Uzytkownik::getType).setCaption("Typ konta");

        gridUzytkownicy.addSelectionListener(e ->
        {
            if(gridUzytkownicy.asSingleSelect().isEmpty())
            {
            }
            else{
                uzytkownik = gridUzytkownicy.asSingleSelect().getValue();
                binder.setBean(uzytkownik);
            }
        });

        binder.bindInstanceFields(this);

        GridLayout formLayout2 = new GridLayout(4,2);
        formLayout2.addComponent(name);
        formLayout2.addComponent(lastName);
        formLayout2.addComponent(email);
        formLayout2.addComponent(pass);
        formLayout2.addComponent(address);
        formLayout2.addComponent(phoneNumber);
        formLayout2.addComponent(idNumber);
        formLayout2.addComponent(accountType);
        formLayout2.setMargin(true);
        formLayout.setSpacing(true);

        save.addClickListener(e -> {
            try
            {
                main.updateData(selection.getValue().getID(),name.getValue() ,
                        lastName.getValue(), email.getValue(), pass.getValue(),
                        address.getValue(), phoneNumber.getValue(), idNumber.getValue(),
                        Integer.parseInt(accountType.getValue())
                );

            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        });

        formLayout2.addComponent(save);

        formLayout.addComponent(footer);

        footer.addComponent(gridUzytkownicy);

        this.addComponent(footer);

        addComponent(formLayout2);

    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    public void zapiszUzytkownika(int ID, String name, String lastName, String email,String pass, String address, String phoneNumber, String idNumber, int accountType){
        try{
            main.updateData(ID, name, lastName, email, pass, address, phoneNumber, idNumber, accountType);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }


}
