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

    Label labelTitle;
    Label labelHeader;

    TextField textName;
    TextField textLastName;
    RadioButtonGroup<String> optionGender;
    TextField textEmail;
    TextField textLogin;
    PasswordField pass;

    ComboBox comboType;
    Button buttonConfirm;
    Button buttonCancel;


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

        labelTitle = new Label("Rejestracja");
        labelTitle.addStyleName("h1");

        formLayout.setMargin(false);
        formLayout.setWidth("35%");
        formLayout.addStyleName("light");

        labelHeader = new Label("Dane Personalne");
        labelHeader.addStyleName("h2");
        labelHeader.addStyleName("colored");;

        textName = new TextField("Imię");
        textName.setRequiredIndicatorVisible(true);

        textLastName = new TextField("Nazwisko");
        textLastName.setRequiredIndicatorVisible(true);

        optionGender = new RadioButtonGroup<>("Płeć");
        optionGender.setItems("Kobieta", "Mężczyzna", "Pozostałe");

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
        formLayout.addComponent(optionGender);
        formLayout.addComponent(textEmail);
        formLayout.addComponent(textLogin);
        formLayout.addComponent(pass);
        formLayout.addComponent(comboType);
        formLayout.addComponent(footer);

        buttonConfirm = new Button("Potwierdź");
        buttonConfirm.addStyleName("primary");

        buttonConfirm.addClickListener((Button.ClickListener) clickEvent ->
        {
            mainLogic.singUp(textName.getValue(), textLastName.getValue(), optionGender.getValue(), textEmail.getValue(),
                    textLogin.getValue(), pass.getValue(), comboType.getCaption());

            Notification.show("Rejestracja przebiegła pomyślnie!");

            getUI().getNavigator().navigateTo("login");
        });

        buttonCancel = new Button("Anuluj");
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
