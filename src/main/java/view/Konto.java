package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import logic.Konta;

public class Konto extends VerticalLayout implements View
{
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

        Grid<Konta> gridRezerwacje = new Grid<>();
        gridRezerwacje.getEditor().setEnabled(true);
        gridRezerwacje.setWidth("1050");
        gridRezerwacje.setHeight("500");


        formLayout.addComponent(footer);

        gridRezerwacje.addColumn(Konta::getID).setCaption("ID");
        gridRezerwacje.addColumn(Konta::getAdministrator).setCaption("Administrator");
        gridRezerwacje.addColumn(Konta::getUser).setCaption("Użytkownik");

        footer.addComponent(gridRezerwacje);
        this.addComponent(footer);
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent)
    {

    }
}
