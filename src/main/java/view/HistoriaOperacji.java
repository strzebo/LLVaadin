package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import logic.Main;
import logic.Rezerwacje;

import java.sql.SQLException;

public class HistoriaOperacji extends VerticalLayout implements View
{
    public HistoriaOperacji() throws SQLException
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
        gridRezerwacje.setItems(main.getRezerwacjeList(main.getUserID(),""));
        gridRezerwacje.setSelectionMode(Grid.SelectionMode.SINGLE);
        SingleSelect<Rezerwacje> selection = gridRezerwacje.asSingleSelect();

        Button buttonAnuluj = new Button("Anuluj zakup");
        buttonAnuluj.addStyleName("danger");
        buttonAnuluj.setSizeUndefined();


        formLayout.addComponent(footer);

        gridRezerwacje.addColumn(Rezerwacje::getID).setCaption("Nr rezerwacji");
        gridRezerwacje.addColumn(Rezerwacje::getIDLotu).setCaption("Nr lotu");
        gridRezerwacje.addColumn(Rezerwacje::getStanRezerwacji).setCaption("Stan rezerwacji");

        footer.addComponent(gridRezerwacje);

        HorizontalLayout buttony = new HorizontalLayout();

        buttony.addComponent(buttonAnuluj);

        buttony.setExpandRatio(buttonAnuluj,1);

        buttonAnuluj.addClickListener((Button.ClickListener) event ->
        {
            try
            {
                main.zmianaStanuRezerwacji(selection.getValue().getID(),"Anulowana");
                Notification.show("Zakup biletu został anulowany!");
                Page.getCurrent().reload() ;
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        });

        buttony.setComponentAlignment(buttonAnuluj, Alignment.TOP_CENTER);

        footer.addComponent(buttony);

        this.addComponent(footer);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent)
    {

    }
}
