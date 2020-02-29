package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import pageComponents.PopUp;

import java.util.List;

@Slf4j
@Getter
public class LoginPage extends BasePage {

    @FindBy(css = "#txtUsername")
    private WebElementFacade loginInputField;

    @FindBy(css = "#txtPassword")
    private WebElementFacade passwordInputField;

    @FindBy(css = "#btnLogin")
    private WebElementFacade loginButton;

    @FindBy(css = "#loginAsButtonGroup button")
    private WebElementFacade loginAsDifferentRoleButton;

    @FindBy(css = "#spanMessage")
    private WebElementFacade emptyFieldErrorMessage;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//li//a")
    private List<WebElementFacade> userRoles;

    @FindBy(css = ".social-buttons")
    private WebElementFacade socialMediaContainer;

    public void enterUserName(String userName) {
        log.info("Login to app with userName " + userName);
        loginInputField.waitUntilVisible().waitUntilClickable().clear();
        loginInputField.sendKeys(userName);
    }

    public String getTextFromUserName() {
        log.info("Getting text from User Name field");
        return loginInputField.waitUntilVisible().waitUntilClickable().getValue();
    }

    public String getTextFromPopUp() {
        log.info("Getting text from pop up");
        PopUp popUp = new PopUp(getPopUpElement().waitUntilVisible());
        return popUp.getPopUpMessage();
    }

    public void enterPassword(String password) {
        log.info("Login to app with password " + password);
        passwordInputField.waitUntilVisible().waitUntilClickable().clear();
        passwordInputField.sendKeys(password);
    }

    public void clickOnLoginButton() {
        log.info("Clicking on the [Login] button");
        loginButton.waitUntilEnabled().waitUntilClickable().click();
    }

    public void clickOnTheLoginAsDifferentRoleButton() {
        log.info("Clicking on the [Login as a Different Role] button");
        loginAsDifferentRoleButton.waitUntilVisible().waitUntilClickable().click();
    }
}
