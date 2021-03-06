package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import logic.Main;

import java.sql.SQLException;

public class Index extends VerticalLayout implements View
{
    Main main = new Main();

    private VerticalLayout menuLayout = new VerticalLayout();
    private HorizontalLayout menuTitle = new HorizontalLayout();
    private VerticalLayout contentLayout = new VerticalLayout();

    private Label labelMenu;

    public Index()
    {
        Label labelHeader = new Label("");
        labelHeader.addStyleName("colored");
        labelHeader.addStyleName("h2");
        labelHeader.setSizeUndefined();

        Button buttonLogin = new Button("Zaloguj się / Zarejestruj się");
        buttonLogin.addStyleName("small");
        buttonLogin.addStyleName("friendly");
        buttonLogin.setSizeUndefined();
        buttonLogin.addClickListener((Button.ClickListener) event -> getUI().getNavigator().navigateTo("login"));

        /*
        Button buttonSingUp = new Button("Zarejestruj się");
        buttonSingUp.addStyleName("small");
        buttonSingUp.addStyleName("friendly");
        buttonSingUp.setSizeUndefined();
        buttonSingUp.addClickListener((Button.ClickListener) event -> getUI().getNavigator().navigateTo("rejestracja"));
        */

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

        HorizontalLayout innerUpperSection = new HorizontalLayout();
        innerUpperSection.addComponent(labelHeader);

        if(main.getUserID() == 0 )
        {
            innerUpperSection.addComponent(buttonLogin);
            innerUpperSection.setExpandRatio(buttonLogin, 1);
            innerUpperSection.setComponentAlignment(buttonLogin, Alignment.MIDDLE_RIGHT);
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

        upperSection.setMargin(new MarginInfo(false, false, false, false));
        upperSection.setComponentAlignment(innerUpperSection, Alignment.TOP_RIGHT);
        upperSection.addStyleName("borderBottom");
        upperSection.setHeight(4, UNITS_EM);

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
        setComponentAlignment(lowerSection, Alignment.MIDDLE_CENTER);
        setSizeFull();

        setExpandRatio(lowerSection,1);
    }

    private void setMenuTitle()
    {
        menuTitle.addComponent(labelMenu);
        menuLayout.addComponent(menuTitle);
        menuLayout.setWidth("100%");
        menuLayout.setComponentAlignment(menuTitle, Alignment.MIDDLE_CENTER);

    }

    private void addWelcomeText(String value)
    {
        Label labelTitle = new Label(); //+ mainLogic.getCurrentUser().getName() +
        labelTitle.addStyleName("h1");
        labelTitle.addStyleName("colored");

        labelTitle.setValue(value);// + mainLogic.getCurrentUser().getType());

        contentLayout.addComponent(labelTitle);
        contentLayout.setMargin(new MarginInfo(false, false, false, true));

    }

    private void addDashboardOption(String caption)
    {

        Button button = new Button(caption);
        button.setWidth("100%");
        button.setStyleName("borderless");
        menuLayout.addComponent(button);

        button.addClickListener(event ->
        {
            contentLayout.removeAllComponents();
            addWelcomeText("Home");
        });
    }

    //region navigate
    private void navigatePanelAdministratora()
    {
        Button button = new Button("SuperUser");
        button.setWidth("100%");
        button.setStyleName("borderless");
        menuLayout.addComponent(button);

        button.addClickListener(event -> getUI().getNavigator().navigateTo("paneladministratora"));
    }

    private void navigatePanelKierownika()
    {
        Button button = new Button("Panel kierownika");
        button.setWidth("100%");
        button.setStyleName("borderless");
        menuLayout.addComponent(button);

        button.addClickListener(event -> getUI().getNavigator().navigateTo("panelkierownika"));
    }

    private void navigatePanelSprzedawcy()
    {
        Button button = new Button("Panel sprzedawcy");
        button.setWidth("100%");
        button.setStyleName("borderless");
        menuLayout.addComponent(button);

        button.addClickListener(event -> getUI().getNavigator().navigateTo("panelsprzedawcy"));
    }

    private void navigatePanelUzytkownika()
    {
        Button button = new Button("Panel użytkownika");
        button.setWidth("100%");
        button.setStyleName("borderless");
        menuLayout.addComponent(button);

        button.addClickListener(event -> getUI().getNavigator().navigateTo("paneluzytkownika"));
    }
    //endregion

    private Component getComponent(String componentName) throws SQLException
    {
        if (componentName.equals("Kontakt"))
            return new Kontakt();
        else if (componentName.equals("Trasy"))
            return new Trasy();
        else
            return new Index();
    }

    private void addMenuOption(String caption, String componentName)
    {
        Button button = new Button(caption);

        button.setWidth("100%");
        button.setStyleName("borderless");

        menuLayout.addComponent(button);
        button.addClickListener((Button.ClickListener) event ->
        {
            contentLayout.removeAllComponents();
            addWelcomeText(caption);
            try
            {
                contentLayout.addComponent(getComponent(componentName));
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        });
    }



    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {
        menuLayout.removeAllComponents();
        contentLayout.removeAllComponents();

        this.setMenuTitle();

        //if (mainLogic.getCurrentUser().getType().equals("Pracownik"))
        this.addDashboardOption("Home");

        this.addMenuOption("Wyszukaj loty", "Trasy");

        if(main.getUserType() == 1)
            this.navigatePanelUzytkownika();
        else if (main.getUserType() == 2)
            this.navigatePanelSprzedawcy();
        else if (main.getUserType() == 3)
            this.navigatePanelKierownika();
        else if (main.getUserType() == 4)
            this.navigatePanelAdministratora();

        this.addMenuOption("Kontakt", "Kontakt");

        this.addWelcomeText("Home");
    }
}
