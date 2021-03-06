package steps;

import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class PersonalDetailsSteps extends DefaultStepsData {

    @Step
    public String getValueFromDateOfBirthField() {
        return personalDetailsPage.getDateOfBirthInputField().getAttribute("value");
    }

    @Step
    public void enterDateIntoDateBirthField(String date) {
        personalDetailsPage.getDateOfBirthInputField().clear();
        personalDetailsPage.enterDateOfBirth(date);
    }

    @Step
    public void checkGenderButton(String gender) {
        personalDetailsPage.clickOnGenderRadioButton(gender);
    }

    @Step
    public void clickSaveButton() {
        personalDetailsPage.clickSaveButton();
    }

    @Step
    public String getTextFromDateOfBirthErrorMessage() {
        return personalDetailsPage.getDateOfBirthErrorMessage().getText();
    }

    @Step
    public String getTextFromRaceAndEthnicityErrorMessage() {
        return personalDetailsPage.getRaceAndEthnicityErrorMessage().getText();
    }

    @Step
    public String getValueFromRaceAndEthnicitySelect() {
        return personalDetailsPage.getRaceAndEthnicitySelect().getValue();
    }

    @Step
    public boolean checkSelectedGenderRadioButton(String gender) {
        return personalDetailsPage.checkSelectedGenderRadioButton(gender);
    }

    @Step
    public List<String> getOptionsFromNationalitySelect() {
        List<String> nationalityOptions = personalDetailsPage.getNationalitySelect().thenFindAll(By.xpath("./..//li//span"))
                .stream().map(we -> we.getAttribute("innerText")).collect(Collectors.toList());
        return nationalityOptions;
    }
}
