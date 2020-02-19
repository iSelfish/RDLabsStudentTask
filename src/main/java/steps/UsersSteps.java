package steps;

import grids.UsersGrid;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import pageComponents.FilterUsersModalWindow;

import java.util.List;

import static utils.SessionVariables.FILTER_USERS_WINDOW;

@Slf4j
public class UsersSteps extends DefaultStepsData {

    @Step
    public void openFilterWindow() {
        log.info("Opens Filter Users window");
        usersPage.clickOnFilterButton();
        FILTER_USERS_WINDOW.put(new FilterUsersModalWindow(usersPage.getFilterUsersModalWindow()));
    }

    @Step
    public FilterUsersModalWindow getFilterUsersWindow() {
        return new FilterUsersModalWindow(usersPage.getFilterUsersModalWindow());
    }

    @Step
    public void clickOnTheSearchButton() {
        log.info("Clicking on the Search button");
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        filterUsersModalWindow.clickOnSearchButton();
    }

    @Step
    public void filterUsersByEmployeeName(String employeeName) {
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        log.info("Filtering by Employee Name: " + employeeName);
        log.info("Typing employee name into [Employee Name] input field");
        filterUsersModalWindow.getEmployeeNameField().waitUntilEnabled().sendKeys(employeeName);
        WebElementFacade employeeDropDown = filterUsersModalWindow.getEmployeeNameField().find(By.xpath("./..//div[contains(@class,'angucomplete-row')]"));
        log.info("Clicking on the autocomplete search result");
        employeeDropDown.waitUntilVisible().waitUntilClickable().click();
        employeeDropDown.waitUntilNotVisible();
    }

    @Step
    public void changeStatusTo(String status) {
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        log.info("Filtering by Status: " + status);
        filterUsersModalWindow.changeStatusTo(status);
    }

    @Step
    public boolean employeeIsShown(String employeeName) {
        List<UsersGrid> allItems;
        int amountOfShownEmployees = 0;
        boolean thereIsNextPage;
        do {
            allItems = getUsersGrid();
            for (UsersGrid allItem : allItems) {
                if (allItem.getEmployeeName().equals(employeeName)) {
                    amountOfShownEmployees++;
                }
            }
            allItems.clear();
            if (usersPage.getNextPageButton().isVisible()) {
                thereIsNextPage = true;
                usersPage.getNextPageButton().waitUntilClickable().click();
            } else {
                thereIsNextPage = false;
            }
        } while (thereIsNextPage);
        return amountOfShownEmployees != 0;
    }

    @Step
    public List<UsersGrid> getUsersGrid() {
        log.info("Getting [Users] grid");
        return new UsersGrid().getAllItems(usersPage.getDriver());
    }
}
