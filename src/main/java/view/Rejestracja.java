package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import logic.Main;

import java.sql.SQLException;

public class Rejestracja extends VerticalLayout implements View
{
    Main mainLogic = new Main();

    private TextField textName;
    private TextField textLastName;
    private TextField textEmail;
    private PasswordField pass;
    private TextField textAddress;
    private TextField textPhoneNumber;
    private TextField textIDNumber;

    public Rejestracja()
    {
        //initial
        setSpacing(true);
        setMargin(true);

        //UI components
        FormLayout formLayout = new FormLayout();

        Label labelTitle = new Label("Rejestracja użytkownika");
        labelTitle.addStyleName("h1");

        formLayout.setMargin(false);
        formLayout.setWidth("35%");
        //formLayout.addStyleName("light");

        Label labelHeader = new Label("Dane personalne");
        labelHeader.addStyleName("h2");
        labelHeader.addStyleName("colored");

        textName = new TextField("Imię");
        textName.setRequiredIndicatorVisible(true);

        textLastName = new TextField("Nazwisko");
        textLastName.setRequiredIndicatorVisible(true);

        // adres e-mail to login w bazie danych
        textEmail = new TextField("Adres E-mail");
        textEmail.setRequiredIndicatorVisible(true);

        pass = new PasswordField("Hasło");
        pass.setRequiredIndicatorVisible(true);

        textAddress = new TextField("Adres korespondecyjny");
        textAddress.setRequiredIndicatorVisible(true);

        textPhoneNumber = new TextField("Numer telefonu");
        textPhoneNumber.setRequiredIndicatorVisible(true);

        textIDNumber = new TextField("Numer dokumentu (dowód osobisty lub paszport)");
        textIDNumber.setStyleName("multiline");
        textIDNumber.setRequiredIndicatorVisible(true);

        HorizontalLayout footer = new HorizontalLayout();

        footer.setMargin(new MarginInfo(true,false,true,false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        addComponent(labelTitle);
        setComponentAlignment(labelTitle,Alignment.TOP_CENTER);
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
            try
            {
                mainLogic.singUp(textName.getValue(), textLastName.getValue(), textEmail.getValue(), pass.getValue(), textAddress.getValue(), textPhoneNumber.getValue(), textIDNumber.getValue());
            } catch (SQLException e)
            {
                e.printStackTrace();
            }


            Notification.show("Rejestracja przebiegła pomyślnie!");

            getUI().getNavigator().navigateTo("login");
        });

        Button buttonCancel = new Button("Anuluj");
        buttonCancel.addStyleName("danger");

        buttonCancel.addClickListener((Button.ClickListener) clickEvent -> getUI().getNavigator().navigateTo("login"));

        footer.addComponent(buttonConfirm);
        footer.addComponent(buttonCancel);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent)
    {

    }
}
