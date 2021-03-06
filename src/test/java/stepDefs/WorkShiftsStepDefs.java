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
        workShiftsSteps.openAddWorkShiftWindow();
    }

    @When("I click on Save button in Add Work Shift window")
    public void clickOnSaveButton() {
        workShiftsSteps.clickOnSaveButton();
    }

    @When("Using time picker set $time value into $field filed")
    public void setTime(String time, String field) {
        workShiftsSteps.setTimeWithTimePicker(time, field);
    }

    @Then("$text error message is shown under Work Shift field")
    public void checkErrorMessageUnderWorkShiftField(String text) {
        softly.assertThat(workShiftsSteps.getTextFromWorkShiftErrorField())
                .as("After checking error message under Work Shift field").isEqualTo(text);
    }

    @Then("Rows in Work Shift column have $values default values")
    public void checkDefaultValuesOfWorkShiftColumn(List<String> expectedWorkShiftValues) {
        softly.assertThat(workShiftsSteps.checkDefaultValuesOfWorkShiftColumn(expectedWorkShiftValues))
                .as("Actual [Work Shift] values equals expected ones").isTrue();
    }

    @Then("$expectedValue value is calculated in Hours Per Day field")
    public void checkThatValueInHoursPerDayFieldEquals(String expectedValue) {
        softly.assertThat(workShiftsSteps.getValueFromHoursPerDayField())
                .as("After checking value from Hours Per Day field").isEqualTo(expectedValue);
    }
}
