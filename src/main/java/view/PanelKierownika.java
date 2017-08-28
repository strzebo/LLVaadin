package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import logic.Main;

public class PanelKierownika extends VerticalLayout implements View
{
    Main main = new Main();

    private VerticalLayout menuLayout = new VerticalLayout();
    private HorizontalLayout menuTitle = new HorizontalLayout();
    private VerticalLayout contentLayout = new VerticalLayout();

    private Label labelMenu;

    public PanelKierownika()
    {
        Label labelHeader = new Label("");
        labelHeader.addStyleName("colored");
        labelHeader.addStyleName("h2");
        labelHeader.setSizeUndefined();

        Button buttonLogin = new Button("Zaloguj się");
        buttonLogin.addStyleName("small");
        buttonLogin.addStyleName("friendly");
        buttonLogin.setSizeUndefined();
        buttonLogin.addClickListener((Button.ClickListener) event -> getUI().getNavigator().navigateTo("login"));

        Button buttonSingUp = new Button("Zarejestruj się");
        buttonSingUp.addStyleName("small");
        buttonSingUp.addStyleName("friendly");
        buttonSingUp.setSizeUndefined();
        buttonSingUp.addClickListener((Button.ClickListener) event -> getUI().getNavigator().navigateTo("rejestracja"));

        Button buttonLogout = new Button("Wyloguj się");
        buttonLogout.addStyleName("small");
        buttonLogout.addStyleName("danger");
        buttonLogout.setSizeUndefined();
        buttonLogout.addClickListener((Button.ClickListener) event ->
        {
            main.setUserID(0);
            main.setUserType(0);
            Page.getCurrent().reload();
        });

        labelMenu = new Label("Menu");
        labelMenu.addStyleName("colored");
        labelMenu.addStyleName("h2");

        //Sections
        HorizontalLayout innerUpperSection = new HorizontalLayout();
        innerUpperSection.addComponent(labelHeader);

        if(main.getUserID() == 0 )
        {
            innerUpperSection.addComponent(buttonLogin);
            innerUpperSection.setExpandRatio(buttonLogin, 1);
            innerUpperSection.setComponentAlignment(buttonLogin, Alignment.MIDDLE_RIGHT);

            innerUpperSection.addComponent(buttonSingUp);
            innerUpperSection.setExpandRatio(buttonSingUp, 1);
            innerUpperSection.setComponentAlignment(buttonSingUp, Alignment.MIDDLE_RIGHT);
        }

        if(main.getUserID() != 0)
        {
            innerUpperSection.addComponent(buttonLogout);
            innerUpperSection.setExpandRatio(buttonLogout, 1);
            innerUpperSection.setComponentAlignment(buttonLogout, Alignment.MIDDLE_RIGHT);
        }

        innerUpperSection.setSpacing(true);

        HorizontalLayout upperSection = new HorizontalLayout();
        upperSection.setSizeFull();
        upperSection.addComponent(innerUpperSection);

        upperSection.setMargin(new MarginInfo(false, true, false, false));
        upperSection.setComponentAlignment(innerUpperSection, Alignment.TOP_RIGHT);
        upperSection.addStyleName("borderBottom");
        upperSection.setHeight(4, UNITS_EM);

        //menu
        menuTitle.addComponent(labelMenu);
        menuLayout.addComponent(menuTitle);
        menuLayout.setWidth("100%");
        menuLayout.setComponentAlignment(menuTitle, Alignment.MIDDLE_CENTER);

        HorizontalSplitPanel lowerSection = new HorizontalSplitPanel();
        lowerSection.addComponent(menuLayout);
        lowerSection.addComponent(contentLayout);
        contentLayout.setSizeFull();
        lowerSection.setSizeFull();
        lowerSection.setSplitPosition(17);

        addComponent(upperSection);
        addComponent(lowerSection);

        setSizeFull();

        setExpandRatio(lowerSection,1);
    }

    private void setMenuTitlePK()
    {
        menuTitle.addComponent(labelMenu);
        menuLayout.addComponent(menuTitle);
        menuLayout.setWidth("100%");
        menuLayout.setComponentAlignment(menuTitle, Alignment.MIDDLE_CENTER);

    }

    private void addWelcomeTextPK(String value)
    {
        Label labelTitle = new Label("Welcome !"); //+ mainLogic.getCurrentUser().getName() +
        labelTitle.addStyleName("h1");
        labelTitle.addStyleName("colored");

        labelTitle.setValue(value);// + mainLogic.getCurrentUser().getType());

        contentLayout.addComponent(labelTitle);
        contentLayout.setMargin(new MarginInfo(false, false, false, true));
    }

    private void navigateHomePK(String caption)
    {
        Button button = new Button(caption);
        button.setWidth("100%");
        button.setStyleName("borderless");
        menuLayout.addComponent(button);

        button.addClickListener(event -> getUI().getNavigator().navigateTo("index"));
    }

    private Component getComponentPK(String componentName)
    {
        if (componentName.equals("ZmianaDanych"))
            return new ZmianaDanych();
        else if (componentName.equals("Konto"))
            return new Konto();
        else if (componentName.equals("Trasy"))
            return new Trasy();
        else
            return new Index();
    }

    private void addMenuOptionPK(String caption, String componentName)
    {
        Button button = new Button(caption);

        button.setWidth("100%");
        button.setStyleName("borderless");

        menuLayout.addComponent(button);
        button.addClickListener((Button.ClickListener) event ->
        {
            contentLayout.removeAllComponents();
            addWelcomeTextPK(caption);
            contentLayout.addComponent(getComponentPK(componentName));
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent)
    {
        menuLayout.removeAllComponents();
        contentLayout.removeAllComponents();

        this.setMenuTitlePK();

        this.navigateHomePK("Home");

        this.addMenuOptionPK("Zarządzaj trasami", "Trasy");
        this.addMenuOptionPK("Zarządzaj kontami", "Konto");
        this.addMenuOptionPK("Zmiana danych", "ZmianaDanych");

        this.addWelcomeTextPK("Panel kierownika");
    }
}
