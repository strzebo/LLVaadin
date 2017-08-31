package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import logic.Rezerwacje;
import logic.Main;

import java.sql.SQLException;

public class Rezerwacja extends VerticalLayout implements View
{
    public Rezerwacja() throws SQLException
    {
        Main main = new Main();
        FormLayout formLayout = new FormLayout();

        setMargin(true);
        setSpacing(true);

        formLayout.setMargin(false);
        formLayout.setWidth("35%");;
        formLayout.addStyleName("dark");

        addComponent(formLayout);
        setComponentAlignment(formLayout, Alignment.TOP_CENTER);

        VerticalLayout footer = new VerticalLayout();

        footer.setMargin(new MarginInfo(false, false, true, false));
        footer.setSpacing(true);

        Grid<Rezerwacje> gridRezerwacje = new Grid<>();
        gridRezerwacje.getEditor().setEnabled(true);
        gridRezerwacje.setWidth("1050");
        gridRezerwacje.setHeight("500");
        if(main.getUserType() == 1)
        {
            gridRezerwacje.setItems(main.getRezerwacjeList(main.getUserID(), "Zarezerwowano"));
        }
        else
        {
            gridRezerwacje.setItems(main.getRezerwacjeList(main.getUserID(), "Zarezerwowano"));
        }
        gridRezerwacje.setSelectionMode(Grid.SelectionMode.SINGLE);
        SingleSelect<Rezerwacje> selection = gridRezerwacje.asSingleSelect();

        Button buttonKup = new Button("Kup bilet");
        buttonKup.addStyleName("primary");
        buttonKup.setSizeUndefined();

        Button buttonAnuluj = new Button("Anuluj rezerwację");
        buttonAnuluj.addStyleName("danger");
        buttonAnuluj.setSizeUndefined();


        formLayout.addComponent(footer);

        gridRezerwacje.addColumn(Rezerwacje::getID).setCaption("Nr rezerwacji");
        gridRezerwacje.addColumn(Rezerwacje::getIDLotu).setCaption("Nr lotu");
        gridRezerwacje.addColumn(Rezerwacje::getStanRezerwacji).setCaption("Stan rezerwacji");

        footer.addComponent(gridRezerwacje);

        HorizontalLayout buttony = new HorizontalLayout();

        if(main.getUserType() == 1)
        {
            buttony.addComponent(buttonKup);
        }

        buttonKup.addClickListener((Button.ClickListener) event ->
        {
            try
            {
                main.zmianaStanuRezerwacji(selection.getValue().getID(),"Zakupiony");
                Notification.show("Bilet został kupiony!");
                Page.getCurrent().reload() ;
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        });

        buttony.addComponent(buttonAnuluj);

        buttonAnuluj.addClickListener((Button.ClickListener) event ->
        {
            try
            {
                main.zmianaStanuRezerwacji(selection.getValue().getID(),"Anulowana");
                Notification.show("Rezerwacja została anulowana!");
                Page.getCurrent().reload() ;
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        });

        buttony.setExpandRatio(buttonKup,1);
        buttony.setExpandRatio(buttonAnuluj,1);

        buttony.setComponentAlignment(buttonKup, Alignment.TOP_CENTER);
        buttony.setComponentAlignment(buttonAnuluj, Alignment.TOP_CENTER);

        footer.addComponent(buttony);

        this.addComponent(footer);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent)
    {

    }
}
