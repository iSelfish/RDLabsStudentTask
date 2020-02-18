package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.util.IterableUtil.sizeOf;

@Getter
@Slf4j
public class DashboardPage extends BasePage {

    public static final String PAGE_TITLE = "OrangeHRM";

    @FindBy(css = "#account-name")
    private WebElementFacade accountNameLabel;

    @FindBy(xpath = "//a[@id='side-menu-hamburger']")
    private WebElementFacade hideMenuButton;

    @FindBy(css = "#dashboard__viewNewsOnDashboard")
    private WebElementFacade newsContainer;

    @FindBy(css = "#dashboard__viewDocumentsOnDashboard")
    private WebElementFacade documentsContainer;

    @FindBy(xpath = "//i[@class='material-icons moreIcon right']")
    private WebElementFacade employeeThreeDotsButton;

    @FindBy(xpath = "//i[@class='material-icons right']")
    private WebElementFacade leavesThreeDotsButton;

    @FindBy(css = "#task-list-group-panel-menu_holder-legend")
    private WebElementFacade employeeLegend;

    @FindBy(css = "#legend")
    private WebElementFacade leavesLegend;

    @FindBy(xpath = "//div[@id='dashboard__viewNewsOnDashboard']/../div[@class='dashboardCard-title-for-card']")
    private WebElementFacade newsHeader;

    @FindBy(xpath = "//div[@id='dashboard__viewDocumentsOnDashboard']/../div[@class='dashboardCard-title-for-card']")
    private WebElementFacade documentsHeader;

    @FindBy(xpath = "//div[@id='dashboard__viewNewsOnDashboard']//div[@class='right']")
    private WebElementFacade newsCount;

    @FindBy(xpath = "//div[@id='dashboard__viewDocumentsOnDashboard']//div[@class='right']")
    private WebElementFacade documentsCount;

    private By allNews = By.xpath("//div[@id='dashboard__viewNewsOnDashboard']//li");
    private By allDocuments = By.xpath("//div[@id='dashboard__viewDocumentsOnDashboard']//li");

    public void clickOnHideMenuButton() {
        log.info("Clicking on the [Hide menu] button");
        hideMenuButton.waitUntilVisible().waitUntilClickable().click();
    }

    public int getCountOfNews() {
        List<String> container = new ArrayList<>();
        for (WebElement element : getDriver().findElements(allNews)) {
            container.add(element.getText());
        }
        return sizeOf(container);
    }

    public int getRealCountOf(String newsOrDocuments) {
        List<String> container = new ArrayList<>();
        switch (newsOrDocuments) {
            case "News":
                for (WebElement element : getDriver().findElements(allNews)) {
                    container.add(element.getText());
                }
                break;
            case "Documents":
                for (WebElement element : getDriver().findElements(allDocuments)) {
                    container.add(element.getText());
                }
                break;
        }
        return sizeOf(container);
    }
}
