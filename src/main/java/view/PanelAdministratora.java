package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import logic.Main;

public class PanelAdministratora extends VerticalLayout implements View
{
    Main mainLogic = new Main();
    Index index = new Index();

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

    public PanelAdministratora()
    {
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

        labelMenu = new Label("Super Menu");
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

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent)
    {
        menuLayout.removeAllComponents();
        contentLayout.removeAllComponents();





    }
}
