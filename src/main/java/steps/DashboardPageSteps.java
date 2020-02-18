package steps;

import emuns.ItemsContainer;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Step;

@Slf4j
public class DashboardPageSteps extends DefaultStepsData {

    @Step
    public String getDashBoardPageTitle() {
        return dashboardPage.getTitle();
    }

    @Step
    public String getAccountNameFromDashboard() {
        return dashboardPage.getAccountNameLabel().getText();
    }

    @Step
    public void clickOnHideMenuButton() {
        dashboardPage.clickOnHideMenuButton();
    }

    @Step
    public void expandContainerClickingOnThreeDots(String sectionName) {
        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(sectionName);
        switch (itemsContainer) {
            case EMPLOYEE_DISTRIBUTION:
                dashboardPage.getEmployeeThreeDotsButton().waitUntilEnabled().click();
                break;
            case LEAVE_TAKEN:
                dashboardPage.getLeavesThreeDotsButton().waitUntilEnabled().click();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + itemsContainer);
        }
    }

    public boolean checkThatLegendAppearsIn(String sectionName) {
        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(sectionName);
        switch (itemsContainer) {
            case EMPLOYEE_DISTRIBUTION:
                return dashboardPage.getEmployeeLegend().isVisible();
            case LEAVE_TAKEN:
                return dashboardPage.getLeavesLegend().isVisible();
            default:
                throw new IllegalStateException("Unexpected value: " + itemsContainer);
        }
    }

    @Step
    public int getShowingCountOfNews() {
        String countText = dashboardPage.getNewsCount().getText().split("/")[1].trim();
        return Integer.parseInt(countText);
    }

    @Step
    public int getRealCountOfNews() {
        return dashboardPage.getCountOfNews();
    }

    @Step
    public int getShowingCountOfDocuments() {
        String countText = dashboardPage.getDocumentsCount().getText().split("/")[1].trim();
        return Integer.parseInt(countText);
    }

    @Step
    public int getRealCountOfDocuments() {
        return dashboardPage.getCountOfDocuments();
    }

    @Step
    public String getTextFromSectionHeader(String sectionName) {
        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(sectionName);
        switch (itemsContainer) {
            case NEWS:
                return dashboardPage.getNewsHeader().getText();
            case DOCUMENTS:
                return dashboardPage.getDocumentsHeader().getText();
            default:
                throw new IllegalStateException("Unexpected value: " + itemsContainer);
        }
    }
}
