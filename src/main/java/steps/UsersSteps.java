package steps;

import grids.UsersGrid;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import pageComponents.FilterUsersModalWindow;

import java.time.Duration;
import java.util.List;

import static utils.SessionVariables.FILTER_USERS_WINDOW;

@Slf4j
public class UsersSteps extends DefaultStepsData {

    @Step
    public void openFilterWindow() {
        log.info("Opens Filter Users window");
        usersPage.clickOnFilterButton();
        FILTER_USERS_WINDOW.put(new FilterUsersModalWindow(usersPage.getFilterUsersModalWindow().waitUntilVisible()));
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
    public void filterUsersBy(String select, String value) {
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        switch (select) {
            case "Employee Name":
                log.info("Filtering by Employee Name: " + value);
                filterUsersModalWindow.changeEmployeeNameTo(value);
                break;
            case "Status":
                log.info("Filtering by Status: " + value);
                filterUsersModalWindow.changeStatusTo(value);
                break;
            case "Admin Role":
                log.info("Filtering by Admin Role: " + value);
                filterUsersModalWindow.changeAdminRoleTo(value);
                break;
            default:
                throw new IllegalStateException("Unexpected value");
        }
    }

    @Step
    public String getStatus() {
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        log.info("Getting Status");
        return filterUsersModalWindow.getStatus().getValue();
    }

    @Step
    public String getAdminRole() {
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        log.info("Getting Admin Role");
        return filterUsersModalWindow.getAdminRole().getValue();
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
