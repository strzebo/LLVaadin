package view;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import logic.Main;

public class Index extends VerticalLayout implements View
{
    Main mainLogic = new Main();

    HorizontalLayout upperSection = new HorizontalLayout();
    HorizontalLayout innerUpperSection = new HorizontalLayout();
    HorizontalSplitPanel lowerSection = new HorizontalSplitPanel();
    VerticalLayout menuLayout = new VerticalLayout();
    HorizontalLayout menuTitle = new HorizontalLayout();
    VerticalLayout contentLayout = new VerticalLayout();

    Label labelHeader;
    Label labelMenu;
    Button buttonLogout;
    Button buttonLogin;
    Button buttonSingUp;

    public Index()
    {
        //UI

        labelHeader = new Label("");
        labelHeader.addStyleName("colored");
        labelHeader.addStyleName("h2");
        labelHeader.setSizeUndefined();

        buttonLogin = new Button("Zaloguj się");
        buttonLogin.addStyleName("small");
        buttonLogin.addStyleName("friendly");
        buttonLogin.setSizeUndefined();
        buttonLogin.addClickListener((Button.ClickListener) event -> getUI().getNavigator().navigateTo("login"));

        buttonSingUp = new Button("Zarejestruj się");
        buttonSingUp.addStyleName("small");
        buttonSingUp.addStyleName("friendly");
        buttonSingUp.setSizeUndefined();
        buttonSingUp.addClickListener((Button.ClickListener) event -> getUI().getNavigator().navigateTo("rejestracja"));

        buttonLogout = new Button("Wyloguj się");
        buttonLogout.addStyleName("small");
        buttonLogout.addStyleName("danger");
        buttonLogout.setSizeUndefined();
        buttonLogout.addClickListener((Button.ClickListener) event -> getUI().getNavigator().navigateTo("index"));

        labelMenu = new Label("Menu");
        labelMenu.addStyleName("colored");
        labelMenu.addStyleName("h2");

        //Sections
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

    public void setMenuTitle()
    {
        menuTitle.addComponent(labelMenu);
        menuLayout.addComponent(menuTitle);
        menuLayout.setWidth("100%");
        menuLayout.setComponentAlignment(menuTitle, Alignment.MIDDLE_CENTER);
    }

    public void addWelcomeText()
    {
        Label labelTitle = new Label("Welcome !"); //+ mainLogic.getCurrentUser().getName() +
        labelTitle.addStyleName("h1");
        labelTitle.addStyleName("colored");

        labelTitle.setValue("Zalogowany ziomeczku");// + mainLogic.getCurrentUser().getType());

        contentLayout.addComponent(labelTitle);
        contentLayout.setMargin(new MarginInfo(false, false, false, true));

    }

    public void addDashboardOption(String caption) {
        //set menu buttons

        Button button = new Button(caption);
        button.setWidth("100%");
        button.setStyleName("borderless");
        menuLayout.addComponent(button);

        /*button.addClickListener(event -> {
            contentLayout.removeAllComponents();    //remove everything from the content screen section
            addWelcomeText();
        });
        */

        button.addClickListener(event -> {
            getUI().getNavigator().navigateTo("login");
        } );
    }

    public Component getComponent(String componentName) {
        if (componentName.equals("Kontakt"))
            return new Kontakt();
        else if (componentName.equals("Trasy"))
            return new Trasy();
        else if (componentName.equals("PanelAdministratora"))
            return new PanelAdministratora();
        else if (componentName.equals("PanelKierownika"))
            return new PanelKierownika();
        else if (componentName.equals("PanelSprzedawcy"))
            return new PanelSprzedawcy();
        else if (componentName.equals("PanelUzytkownika"))
            return new PanelUzytkownika();

        else
            return new Index();
    }

    public void addMenuOption(String caption, String componentName) {
        Button button = new Button(caption);
        button.setWidth("100%");
        button.setStyleName("borderless");
        menuLayout.addComponent(button);
        button.addClickListener((Button.ClickListener) event -> {
            contentLayout.removeAllComponents();
            contentLayout.addComponent(getComponent(componentName));
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        menuLayout.removeAllComponents();
        contentLayout.removeAllComponents();

        setMenuTitle();
        this.addDashboardOption("Login?");
        //if (mainLogic.getCurrentUser().getType().equals("Pracownik")){
        this.addMenuOption("Home","Index");
        this.addMenuOption("Wyszukaj loty", "Trasy");

        this.addMenuOption("Panel Użytkownika", "PanelUzytkownika");
        this.addMenuOption("Panel Sprzedawcy", "PanelSprzedawcy");
        this.addMenuOption("Panel Kierownika", "PanelKierownika");
        this.addMenuOption("SuperUser :)", "PanelAdministratora");
        this.addMenuOption("Kontakt", "Kontakt");


       // }

        addWelcomeText();
    }
}
