package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

public class Kontakt extends VerticalLayout implements View
{

    public Kontakt()
    {
        Label labelHeader = new Label("Skontaktuj się z nami");

        labelHeader.addStyleName("h2");
        labelHeader.addStyleName("colored");

        FormLayout formLayout = new FormLayout();

        formLayout.setMargin(false);
        formLayout.setWidth("50%");
        //formLayout.addStyleName("light");

        TextField textName = new TextField("Imię");
        textName.setWidth("50%");

        TextField textEmail = new TextField("Adres email");
        textEmail.setWidth("50%");

        TextArea textTresc = new TextArea("Treść");
        textTresc.setSizeUndefined();
        textTresc.setWidth("100%");
        textTresc.setHeight("200px");

        HorizontalLayout footer = new HorizontalLayout();

        footer.setMargin(new MarginInfo(true,false,true,false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        addComponent(formLayout);
        setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);

        formLayout.addComponent(labelHeader);
        formLayout.addComponent(textName);
        formLayout.addComponent(textEmail);
        formLayout.addComponent(textTresc);
        formLayout.addComponent(footer);

        Button buttonWyslij = new Button("Wyślij");
        buttonWyslij.addStyleName("primary");

        buttonWyslij.addClickListener((Button.ClickListener) clickEvent -> Notification.show("no i poszło :)"));

        footer.addComponent(buttonWyslij);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent)
    {

    }
}
