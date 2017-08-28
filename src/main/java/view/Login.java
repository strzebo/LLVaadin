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
                Notification.show("Poprawnie zalogowano");
                Page.getCurrent().reload();
            }
            else
                Notification.show("Podano błędny login lub hasło!");

        });

        Button buttonSingUp = new Button("Rejestracja");
        buttonSingUp.addStyleName("primary");
        buttonSingUp.addClickListener(clickEvent -> getUI().getNavigator().navigateTo("rejestracja"));



        //layout
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        horizontalLayout.addComponent(buttonLogin);
        horizontalLayout.addComponent(buttonSingUp);;

        horizontalLayout.setSpacing(true);

        //formLayout
        FormLayout formLayout = new FormLayout(textLogin, pass, horizontalLayout);
        formLayout.setMargin(true);
        //Panel
        Panel loginPanel = new Panel("Panel logowania", formLayout);
        loginPanel.setWidth("350");
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
