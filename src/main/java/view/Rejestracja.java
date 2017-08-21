package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import logic.Main;

import java.util.ArrayList;

public class Rejestracja extends VerticalLayout implements View
{
    Main mainLogic = new Main();

    private TextField textName;
    private TextField textLastName;
    private TextField textEmail;
    private TextField textLogin;
    private PasswordField pass;

    private ComboBox comboType;


    public Rejestracja()
    {
        //initial
        setSpacing(true);
        setMargin(true);

        //UI components
        FormLayout formLayout = new FormLayout();

        ArrayList<String> typ = new ArrayList<>();
        typ.add("Użytkownik");
        typ.add("Pracownik");

        Label labelTitle = new Label("Rejestracja");
        labelTitle.addStyleName("h1");

        formLayout.setMargin(false);
        formLayout.setWidth("35%");
        formLayout.addStyleName("light");

        Label labelHeader = new Label("Dane Personalne");
        labelHeader.addStyleName("h2");
        labelHeader.addStyleName("colored");;

        textName = new TextField("Imię");
        textName.setRequiredIndicatorVisible(true);

        textLastName = new TextField("Nazwisko");
        textLastName.setRequiredIndicatorVisible(true);

        textEmail = new TextField("Adres E-mail");
        textEmail.setRequiredIndicatorVisible(true);

        textLogin = new TextField("Login");
        textLogin.setRequiredIndicatorVisible(true);

        pass = new PasswordField("Hasło");
        pass.setRequiredIndicatorVisible(true);

        comboType  = new ComboBox("Typ konta", typ);
        comboType.setRequiredIndicatorVisible(true);


        HorizontalLayout footer = new HorizontalLayout();

        footer.setMargin(new MarginInfo(true,false,true,false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        addComponent(labelTitle);
        addComponent(formLayout);
        setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);

        formLayout.addComponent(labelHeader);
        formLayout.addComponent(textName);
        formLayout.addComponent(textLastName);
        formLayout.addComponent(textEmail);
        formLayout.addComponent(textLogin);
        formLayout.addComponent(pass);
        formLayout.addComponent(comboType);
        formLayout.addComponent(footer);

        Button buttonConfirm = new Button("Potwierdź");
        buttonConfirm.addStyleName("primary");

        buttonConfirm.addClickListener((Button.ClickListener) clickEvent ->
        {
           mainLogic.singUp(textName.getValue(), textLastName.getValue(), textEmail.getValue(), textLogin.getValue(), pass.getValue(), comboType.getCaption());



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
