package stepDefs;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import steps.CommonSteps;
import steps.DashboardPageSteps;
import steps.DefaultStepsData;

public class DashboardPageStepDef extends DefaultStepsData {

    @Steps
    private DashboardPageSteps dashboardPageSteps;
    @Steps
    private CommonSteps commonSteps;

    @Then("dashboard page opens with account name $userName")
    public void checkThatDashboardPageOpens(String userName) {
        softly.assertThat(dashboardPageSteps.getDashBoardPageTitle())
                .as("Wrong page title")
                .isEqualTo(dashboardPage.getTitle());
        softly.assertThat(dashboardPageSteps.getAccountNameFromDashboard())
                .as("Wrong account name is shown on page")
                .isEqualTo(userName);
    }

    //https://jbehave.org/reference/latest/aliases.html

    @When("I click on $condition menu button")
    public void whenClickOnTheHideMenuButton(String condition) {
        if ((condition.equals("hide")) || (condition.equals("show"))) {
            dashboardPageSteps.clickOnHideMenuButton();
        }
    }

    @Then("main menu $condition")
    public void mainMenuCondition(String condition) {
        String warningMessage = "Menu not " + condition + " after clicking on the hide/show menu button";
        if (condition.equals("disappear")) {
            softly.assertThat(commonSteps.isMenuAvatarVisibleNow()).as(warningMessage).isFalse();
        }
        if (condition.equals("appear")) {
            softly.assertThat(commonSteps.isMenuAvatarVisibleNow()).as(warningMessage).isTrue();
        }
    }

    @When("I click on the three dots button inside $sectionName section")
    public void clickOnThreeDotsButton(String sectionName) {
        dashboardPageSteps.expandContainerClickingOnThreeDots(sectionName);
    }

    @Then("Legend component appears in $sectionName section")
    public void checkThatLegendAppears(String sectionName) {
        softly.assertThat(dashboardPageSteps.checkThatLegendAppearsIn(sectionName))
                .as("Legend component not appears").isTrue();
    }

    @Then("$text section is present on Dashboard page with header $text")
    public void checkSectionHeader(String sectionName, String headerText) {
        softly.assertThat(dashboardPageSteps.getTextFromSectionHeader(sectionName))
                .as("Wrong News header").isEqualTo(headerText);
    }

    @Then("counter under $text section is same as real amount of ones in list")
    public void checkCountEqualsToRealAmount(String newsOrDocuments) {
        softly.assertThat(dashboardPageSteps.getShowingCountOf(newsOrDocuments))
                .as("Wrong documents counter").isEqualTo(dashboardPageSteps.getRealCountOf(newsOrDocuments));
    }
}
