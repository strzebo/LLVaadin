package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import logic.Main;

public class ZmianaDanych extends VerticalLayout implements View
{
    public ZmianaDanych()
    {
        FormLayout formLayout = new FormLayout();
        Main mainLogic = new Main();

        Label labelHeader = new Label("Dane personalne");
        labelHeader.addStyleName("h2");
        labelHeader.addStyleName("colored");

        TextField textName = new TextField("Imię");
        textName.setRequiredIndicatorVisible(true);

        TextField textLastName = new TextField("Nazwisko");
        textLastName.setRequiredIndicatorVisible(true);

        // adres e-mail to login w bazie danych
        TextField textEmail = new TextField("Adres E-mail");
        textEmail.setRequiredIndicatorVisible(true);

        PasswordField pass = new PasswordField("Hasło");
        pass.setRequiredIndicatorVisible(true);

        TextField textAddress = new TextField("Adres korespondecyjny");
        textAddress.setRequiredIndicatorVisible(true);

        TextField textPhoneNumber = new TextField("Numer telefonu");
        textPhoneNumber.setRequiredIndicatorVisible(true);

        TextField textIDNumber = new TextField("Numer dokumentu (dowód osobisty lub paszport)");
        textIDNumber.setStyleName("multiline");
        textIDNumber.setRequiredIndicatorVisible(true);

        HorizontalLayout footer = new HorizontalLayout();

        footer.setMargin(new MarginInfo(true,false,true,false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        addComponent(formLayout);
        setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);

        formLayout.addComponent(labelHeader);
        formLayout.addComponent(textName);
        formLayout.addComponent(textLastName);
        formLayout.addComponent(textEmail);
        formLayout.addComponent(pass);
        formLayout.addComponent(textAddress);
        formLayout.addComponent(textPhoneNumber);
        formLayout.addComponent(textIDNumber);
        formLayout.addComponent(footer);

        Button buttonConfirm = new Button("Potwierdź");
        buttonConfirm.addStyleName("primary");

        buttonConfirm.addClickListener((Button.ClickListener) clickEvent ->
        {


            Notification.show("Zmiana danych przebiegła pomyślnie!");
        });

        Button buttonCancel = new Button("Anuluj");
        buttonCancel.addStyleName("danger");

        buttonCancel.addClickListener((Button.ClickListener) clickEvent -> getUI().getNavigator().navigateTo("index"));

        footer.addComponent(buttonConfirm);
        footer.addComponent(buttonCancel);




    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent)
    {

    }
}
