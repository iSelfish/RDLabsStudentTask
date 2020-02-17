package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

@Getter
@Slf4j
public class PersonalDetailsPage extends BasePage {

    @FindBy(css = "#personal_details_tab h4")
    private WebElementFacade personalDetailsHeader;

    @FindBy(css = "#emp_birthday")
    private WebElementFacade dateOfBirthInputField;

    @FindBy(xpath = "//div[@id='nation_code_inputfileddiv']//input")
    private WebElementFacade nationalitySelect;

    @FindBy(xpath = "//div[@id='eeo_race_ent_inputfileddiv']//input")
    private WebElementFacade eeoRaceAndEthnicitySelect;

    @FindBy(xpath = "//label[@for='emp_gender_1']")
    private WebElementFacade maleRadioButton;

    @FindBy(xpath = "//label[@for='emp_gender_2']")
    private WebElementFacade femaleRadioButton;

    public void enterDateOfBirth(String date) {
        log.info(String.format("Putting %s date into [Date of birth] field", date));
        dateOfBirthInputField.clear();
        dateOfBirthInputField.waitUntilEnabled().sendKeys(date);
    }

    public void clickOnGenderRadioButton(String gender) {
        log.info("set gender radio button checked");
        switch (gender) {
            case ("Male"):
                maleRadioButton.waitUntilClickable().click();
                break;
            case ("Female"):
                femaleRadioButton.waitUntilClickable().click();
                break;
            default:
                System.out.println("Such gender wasn't found");
                break;
        }
    }

    public boolean checkSelectedGenderRadioButton(String gender) {
        log.info("check selected gender radio button");
        switch (gender) {
            case ("Male"):
                return Boolean.parseBoolean(maleRadioButton.findElement(By.xpath("./../input")).getAttribute("checked"));
            case ("Female"):
                log.info("check that {} radio button is checked",gender);
                return Boolean.parseBoolean(femaleRadioButton.findElement(By.xpath("./../input")).getAttribute("checked"));
            default:
                System.out.println("Such gender wasn't found");
                return false;
        }
    }
}
