package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import logic.Main;

import java.sql.SQLException;

public class Login extends VerticalLayout implements View
{
    Main main = new Main();
    private TextField textLogin;
    private PasswordField pass;

    public Login()
    {

        //elements
        textLogin = new TextField("Email");
        pass = new PasswordField("Hasło");


        //UI - buttons
        Button buttonLogin = new Button("Login");
        buttonLogin.addStyleName("friendly");
        buttonLogin.addClickListener(clickEvent ->
        {
            boolean zalogowany = false;

            try
            {
                zalogowany = main.logIn(textLogin.getValue(),pass.getValue());
            } catch (SQLException e)
            {
                e.printStackTrace();
            }

            if(zalogowany)
            {
                Page.getCurrent().reload();
                Notification.show("Poprawnie zalogowano");
            }
            else
                Notification.show("Podano błędny login lub hasło!");

        });

        Button buttonSingUp = new Button("Rejestracja");
        buttonSingUp.addStyleName("primary");
        buttonSingUp.addClickListener(clickEvent -> getUI().getNavigator().navigateTo("rejestracja"));

        Button buttonBack = new Button("Wróć");
        buttonBack.addStyleName("danger");
        buttonBack.addClickListener((Button.ClickListener) clickEvent -> getUI().getNavigator().navigateTo("index"));

        addComponent(buttonBack);
        setComponentAlignment(buttonBack,Alignment.TOP_RIGHT);

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
