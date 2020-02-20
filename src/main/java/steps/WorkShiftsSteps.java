package steps;

import grids.WorkShiftGrid;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Step;
import pageComponents.AddWorkShiftModalWindow;
import pageComponents.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    public void clickOnAddWorkShiftButton() {
        log.info("Clicking on the [Add work shift] button");
        workShiftPage.getAddWorkShiftButton().waitUntilClickable().click();
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
