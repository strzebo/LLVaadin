package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import logic.Rezerwacje;

public class Rezerwacja extends VerticalLayout implements View
{
    public Rezerwacja()
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

        Grid<Rezerwacje> gridRezerwacje = new Grid<>();
        gridRezerwacje.getEditor().setEnabled(true);
        gridRezerwacje.setWidth("1050");
        gridRezerwacje.setHeight("500");

        Button buttonKup = new Button("Potwierdź rezerwację");
        buttonKup.addStyleName("primary");
        buttonKup.setSizeUndefined();

        Button buttonAnuluj = new Button("Anuluj rezerwację");
        buttonAnuluj.addStyleName("danger");
        buttonAnuluj.setSizeUndefined();


        formLayout.addComponent(footer);

        gridRezerwacje.addColumn(Rezerwacje::getID).setCaption("ID");
        gridRezerwacje.addColumn(Rezerwacje::getNazwa).setCaption("Nazwa");
        gridRezerwacje.addColumn(Rezerwacje::getLoty).setCaption("Lot");

        footer.addComponent(gridRezerwacje);
        footer.addComponent(buttonKup);
        footer.addComponent(buttonAnuluj);

        footer.setExpandRatio(buttonKup,1);
        footer.setExpandRatio(buttonAnuluj,1);

        footer.setComponentAlignment(buttonKup, Alignment.TOP_CENTER);
        //nie dziala buttonAnuluj bo sie chowa gdzieś..
        footer.setComponentAlignment(buttonAnuluj, Alignment.BOTTOM_CENTER);
        this.addComponent(footer);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent)
    {

    }
}
