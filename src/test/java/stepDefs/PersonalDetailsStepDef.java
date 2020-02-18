package stepDefs;

import com.google.common.collect.Ordering;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import steps.DefaultStepsData;
import steps.PersonalDetailsSteps;

import java.util.List;

import static utils.DateUtils.DATEPATTERN_MY;
import static utils.DateUtils.getDateInFutureOrPastFromNow;
import static utils.SessionVariables.DATE_OF_BIRTH;

public class PersonalDetailsStepDef extends DefaultStepsData {

    @Steps
    PersonalDetailsSteps personalDetailsSteps;

    @When("I click on $text radio button")
    public void checkGenderButton(String gender) {
        personalDetailsSteps.checkGenderButton(gender);
    }

    @When("I click save button")
    public void clickSaveButton() {
        personalDetailsSteps.clickSaveButton();
    }

    @Then("I save current Date of Birth to session")
    public void saveCurrentDateOfBirthToSession() {
        DATE_OF_BIRTH.put(personalDetailsSteps.getValueFromDateOfBirthField());
    }

    @Then("Error message with text '$text' appears")
    public void checkTextFromErrorMessage(String errorText) {
        softly.assertThat(personalDetailsSteps.getTextFromErrorMessage())
                .as("After getting error message").isEqualTo(errorText);
    }

    @When("I change Date of Birth added 1 day to old date")
    public void changeDateOfBirth() {
        String currentDate = personalDetailsSteps.getValueFromDateOfBirthField();
        String updatedDate = getDateInFutureOrPastFromNow(DATEPATTERN_MY, 1, currentDate);
        personalDetailsSteps.enterDateIntoDateBirthField(updatedDate);
    }

    @When("I set Date Of Birth as tomorrow date")
    public void setTomorrowDateOfBirth() {
        String currentDate = personalDetailsSteps.getValueFromDateOfBirthField();
        String updatedDate = getDateInFutureOrPastFromNow(DATEPATTERN_MY, 1);
        personalDetailsSteps.enterDateIntoDateBirthField(updatedDate);
    }

    @Then("$radioButtonName radio button is $condition")
    public void checkThatRadioButtonUnchecked(String radioButtonName, String condition) {
        if (condition.contains("not")) {
            softly.assertThat(personalDetailsSteps.checkSelectedGenderRadioButton(radioButtonName))
                    .as("After checking whether the radio button is not selected").isFalse();
        } else {
            softly.assertThat(personalDetailsSteps.checkSelectedGenderRadioButton(radioButtonName))
                    .as("After checking whether the radio button is selected").isTrue();
        }
    }

    @Then("Date of Birth field contains old date (date from session)")
    public void checkThatDateOfBirthNotChange() {
        String currentDate = personalDetailsSteps.getValueFromDateOfBirthField();
        softly.assertThat(currentDate).as("After refreshing Date of Birth change").isEqualTo(DATE_OF_BIRTH.get());
    }

    @Then("I check that all countries in Nationality select box ordered by name asc")
    public void checkOrderingInNationalitySelectBox() {
        List<String> optionsFromNationalitySelect = personalDetailsSteps.getOptionsFromNationalitySelect();
        boolean isSorted = Ordering.natural().isOrdered(optionsFromNationalitySelect);
        softly.assertThat(isSorted).as("Wrong ordering inside select box").isTrue();
    }
}
