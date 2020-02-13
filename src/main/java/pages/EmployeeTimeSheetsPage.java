package pages;

import lombok.Getter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

@Getter
public class EmployeeTimeSheetsPage extends BasePage {

    @FindBy(css = "#x_report_employeeId_empName")
    private WebElementFacade searchInputField;

    @FindBy(css = ".ac_results li")
    private WebElementFacade employeeNameAutoCompleteElement;

    public void switchTononcoreIframe() {
        getDriver().switchTo().frame("noncoreIframe");
    }

    public void switchToDefaultIframe() {
        getDriver().switchTo().defaultContent();
    }
}
