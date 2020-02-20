package stepDefs;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import steps.DefaultStepsData;
import steps.WorkShiftsSteps;

import java.util.List;

public class WorkShiftsStepDefs extends DefaultStepsData {

    @Steps
    private WorkShiftsSteps workShiftsSteps;

    @When("I click on Add Work Shift button")
    public void clickOnAddWorkShiftButton() {
        workShiftsSteps.clickOnAddWorkShiftButton();
    }

    @Then("Rows in Work Shift column have $values default values")
    public void checkDefaultValuesOfWorkShiftColumn(List<String> expectedWorkShiftValues) {
        softly.assertThat(workShiftsSteps.checkDefaultValuesOfWorkShiftColumn(expectedWorkShiftValues))
                .as("Actual [Work Shift] values equals expected ones").isTrue();
    }
}
