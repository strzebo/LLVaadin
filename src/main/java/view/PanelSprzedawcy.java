package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import logic.Main;

public class PanelSprzedawcy extends VerticalLayout implements View
{
    Main mainLogic = new Main();

    private VerticalLayout menuLayout = new VerticalLayout();
    private HorizontalLayout menuTitle = new HorizontalLayout();
    private VerticalLayout contentLayout = new VerticalLayout();

    private Label labelMenu;

    public PanelSprzedawcy()
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
        buttonLogout.addClickListener((Button.ClickListener) event -> getUI().getNavigator().navigateTo("index"));

        labelMenu = new Label("Menu");
        labelMenu.addStyleName("colored");
        labelMenu.addStyleName("h2");

        //Sections
        HorizontalLayout innerUpperSection = new HorizontalLayout();
        innerUpperSection.addComponent(labelHeader);
        innerUpperSection.addComponent(buttonLogin);
        innerUpperSection.addComponent(buttonSingUp);
        innerUpperSection.addComponent(buttonLogout);
        innerUpperSection.setExpandRatio(buttonLogin, 1);
        innerUpperSection.setExpandRatio(buttonSingUp, 1);
        innerUpperSection.setExpandRatio(buttonLogout, 1);
        innerUpperSection.setSpacing(true);
        innerUpperSection.setComponentAlignment(buttonLogin, Alignment.MIDDLE_RIGHT);
        innerUpperSection.setComponentAlignment(buttonSingUp, Alignment.MIDDLE_RIGHT);
        innerUpperSection.setComponentAlignment(buttonLogout, Alignment.MIDDLE_RIGHT);

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
        lowerSection.setSplitPosition(15);

        addComponent(upperSection);
        addComponent(lowerSection);

        setSizeFull();

        setExpandRatio(lowerSection,1);
    }

    private void setMenuTitlePS()
    {
        menuTitle.addComponent(labelMenu);
        menuLayout.addComponent(menuTitle);
        menuLayout.setWidth("100%");
        menuLayout.setComponentAlignment(menuTitle, Alignment.MIDDLE_CENTER);

    }

    private void addWelcomeTextPS(String value)
    {
        Label labelTitle = new Label("Welcome !"); //+ mainLogic.getCurrentUser().getName() +
        labelTitle.addStyleName("h1");
        labelTitle.addStyleName("colored");

        labelTitle.setValue(value);// + mainLogic.getCurrentUser().getType());

        contentLayout.addComponent(labelTitle);
        contentLayout.setMargin(new MarginInfo(false, false, false, true));
    }

    private void navigateHomePS(String caption)
    {
        Button button = new Button(caption);
        button.setWidth("100%");
        button.setStyleName("borderless");
        menuLayout.addComponent(button);

        button.addClickListener(event -> getUI().getNavigator().navigateTo("index"));
    }

    private Component getComponentPS(String componentName)
    {
        if (componentName.equals("ZmianaDanych"))
            return new ZmianaDanych();
        else if (componentName.equals("Konta"))
            return new Konta();
        else if (componentName.equals("Rezerwacja"))
            return new Rezerwacja();
        else
            return new Index();
    }

    private void addMenuOptionPS(String caption, String componentName)
    {
        Button button = new Button(caption);

        button.setWidth("100%");
        button.setStyleName("borderless");

        menuLayout.addComponent(button);
        button.addClickListener((Button.ClickListener) event ->
        {
            contentLayout.removeAllComponents();
            addWelcomeTextPS(caption);
            contentLayout.addComponent(getComponentPS(componentName));
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent)
    {
        menuLayout.removeAllComponents();
        contentLayout.removeAllComponents();

        this.setMenuTitlePS();

        this.navigateHomePS("Home");

        this.addMenuOptionPS("Zarządzaj rezerwacją", "Rezerwacja");
        this.addMenuOptionPS("Zarządzaj kontami", "Konta");
        this.addMenuOptionPS("Zmiana danych", "ZmianaDanych");

        this.addWelcomeTextPS("Panel sprzedawcy");
    }
}
