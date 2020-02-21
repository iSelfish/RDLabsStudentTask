package steps;

import grids.WorkShiftGrid;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import pageComponents.AddWorkShiftModalWindow;
import pageComponents.TimePicker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static utils.SessionVariables.ADD_WORK_SHIFT_WINDOW;

@Getter
@Slf4j
public class WorkShiftsSteps extends DefaultStepsData {

    @Step
    public List<WorkShiftGrid> getWorkShiftGrid() {
        log.info("Getting Work Shift table from Work Shifts page");
        return new WorkShiftGrid().getAllItems(getDriver());
    }

    @Step
    public boolean checkDefaultValuesOfWorkShiftColumn(List<String> expectedWorkShiftValues) {
        log.info("Checking default values from Work Shifts page Work Shift column");
        List<WorkShiftGrid> allItems = getWorkShiftGrid();
        ArrayList<String> actualWorkShiftValues = new ArrayList<>();
        for (WorkShiftGrid allItem : allItems) {
            actualWorkShiftValues.add(allItem.getWorkShift());
        }
        Collections.sort(actualWorkShiftValues);
        Collections.sort(expectedWorkShiftValues);
        return actualWorkShiftValues.equals(expectedWorkShiftValues);
    }

    @Step
    public void openAddWorkShiftWindow() {
        log.info("Clicking on the [Add work shift] button");
        workShiftPage.getAddWorkShiftButton().waitUntilClickable().click();
        ADD_WORK_SHIFT_WINDOW.put(new AddWorkShiftModalWindow(workShiftPage.getAddWorkShiftWindow()));
    }

    @Step
    public void clickOnSaveButton() {
        log.info("Clicking on the [Save] button");
        AddWorkShiftModalWindow addWorkShiftModalWindow = ADD_WORK_SHIFT_WINDOW.get();
        addWorkShiftModalWindow.getSaveButton().waitUntilClickable().click();
    }

    @Step
    public void setTimeWithTimePicker(String time, String field) {
        log.info("Setting [From] time");
        AddWorkShiftModalWindow addWorkShiftModalWindow = ADD_WORK_SHIFT_WINDOW.get();
        if (field.equals("From")) {
            addWorkShiftModalWindow.getFromClockIcon().waitUntilClickable().click();
        }
        if (field.equals("To")) {
            addWorkShiftModalWindow.getToClockIcon().waitUntilClickable().click();
        }
        String[] hoursAndMinutes = time.split(":");
        List<WebElementFacade> hoursBoard = getTimePickerElement().getHoursBoard();
        List<WebElementFacade> minutesBoard = getTimePickerElement().getMinutesBoard();
        for (WebElementFacade hour : hoursBoard) {
            if (hour.getText().equals(hoursAndMinutes[0])) {
                hour.waitUntilEnabled().waitUntilClickable().click();
            }
        }
        for (WebElementFacade minute : minutesBoard) {
            if (minute.waitUntilVisible().getText().equals(hoursAndMinutes[1])) {
                minute.waitUntilVisible().waitUntilClickable().click();
            }
        }
        getTimePickerElement().getOkButton().waitUntilEnabled().waitUntilClickable().click();
    }

    @Step
    public String getValueFromHoursPerDayField() {
        log.info("Getting value from [Hours Per Day] field");
        AddWorkShiftModalWindow addWorkShiftModalWindow = ADD_WORK_SHIFT_WINDOW.get();
        return addWorkShiftModalWindow.getHoursPerDayField().waitUntilVisible().getValue();
    }

    @Step
    public String getTextFromWorkShiftErrorField() {
        AddWorkShiftModalWindow addWorkShiftModalWindow = ADD_WORK_SHIFT_WINDOW.get();
        return addWorkShiftModalWindow.getWorkShiftNameInputField()
                .find(By.xpath("../span[@class='help-block']")).waitUntilEnabled().getText();
    }

    @Step
    private AddWorkShiftModalWindow getAddWorkShiftModalWindow() {
        return new AddWorkShiftModalWindow(workShiftPage.getAddWorkShiftWindow());
    }

    @Step
    private TimePicker getTimePickerElement() {
        return new TimePicker(workShiftPage.getTimePickerLocator());
    }
}
