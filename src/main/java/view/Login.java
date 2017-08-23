package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import logic.Main;

public class Login extends VerticalLayout implements View
{
    Main main = new Main();
    private TextField textLogin;
    private PasswordField pass;
    private Button buttonSingUp;
    private Button buttonLogin;

    public Login()
    {
        //elements
        textLogin = new TextField("Login");
        pass = new PasswordField("Hasło");


        //UI - buttons
        buttonLogin = new Button("Login");
        buttonLogin.addStyleName("friendly");
        buttonLogin.addClickListener(clickEvent -> getUI().getNavigator().navigateTo("index"));

        buttonSingUp = new Button("Rejestracja");
        buttonSingUp.addStyleName("primary");
        buttonSingUp.addClickListener(clickEvent -> getUI().getNavigator().navigateTo("rejestracja"));



        //layout
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        horizontalLayout.addComponent(buttonLogin);
        horizontalLayout.addComponent(buttonSingUp);

        horizontalLayout.setSpacing(true);

        //formLayout
        FormLayout formLayout = new FormLayout(textLogin, pass, horizontalLayout);
        formLayout.setMargin(true);

        //Panel
        Panel loginPanel = new Panel("Panel logowania", formLayout);
        loginPanel.setWidth("450");
        loginPanel.setHeight("250");

        //components
        addComponent(loginPanel);
        setComponentAlignment(loginPanel,Alignment.MIDDLE_CENTER);
        setHeight("100%");



    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent)
    {

    }
}
