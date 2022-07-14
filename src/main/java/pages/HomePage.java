package pages;

import endpoints.calculator.taxRelief.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import shared.BasePage;
import utils.Constants;
import utils.Locator;
import utils.WebElementLocator;

import java.io.*;
import java.util.*;
import java.util.List;

import static utils.SearchContextExtensions.*;

public class HomePage extends BasePage {

    private final WebElementLocator
            browseButton = new WebElementLocator(Locator.Xpath, "//input[@type='file']"),
            dispenseNowButton = new WebElementLocator(Locator.Xpath, "//*[(@role='button') and (@href='dispense')] "),
            button = new WebElementLocator(Locator.Xpath, "//*[(@type='button') and (text()='%s')]"),
            dispensedText = new WebElementLocator(Locator.Xpath, "//*[contains(@class,'font-weight-bold')]"),
            taxReliefTableRow = new WebElementLocator(Locator.Xpath, "//tr/td");

    public void clickOnBrowseAndUploadCSVFile(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("insert.csv").getFile());
        getElement(driver, browseButton).sendKeys(file.getAbsolutePath());
    }

    public void clickOnDispenseNowButton(){
        waitForClickableWebElement(driver, dispenseNowButton, Constants.PAGELOADTIMEOUT);
        getElement(driver, dispenseNowButton).click();
    }

    public void clickOnRefreshTaxReliefTableButton(){
        waitForClickableWebElement(driver, button.Format("Refresh Tax Relief Table"), Constants.PAGELOADTIMEOUT);
        getElement(driver, button.Format("Refresh Tax Relief Table")).click();
    }

    public String getButtonName(){
        waitForClickableWebElement(driver, dispenseNowButton, Constants.PAGELOADTIMEOUT);
        return getElement(driver, dispenseNowButton).getText();
    }

    public String getColor(){
        waitForVisibleWebElement(driver, dispenseNowButton, Constants.SHORT_TIMEOUT);
        return Color.fromString(getElement(driver, dispenseNowButton).getCssValue("background-color")).asHex();
    }

    public String getSectionPartOfUrl() {
        String url = driver.getCurrentUrl();
        String[] urlSplit = url.split("/");
        return urlSplit[urlSplit.length-1];
    }

    public String getText(){
        waitForClickableWebElement(driver, dispensedText, Constants.PAGELOADTIMEOUT);
        return getElement(driver, dispensedText).getText();
    }

    public List<String> getListNatIdAndReliefFromUI(){
        waitForVisibleWebElement(driver, taxReliefTableRow, Constants.SHORT_TIMEOUT);
        List<String> listNatIdAndReliefFromUI = new ArrayList<>();
        for (WebElement element : getElements(driver, taxReliefTableRow)){
            listNatIdAndReliefFromUI.add(element.getText());
        }
        return listNatIdAndReliefFromUI;
    }

    public List<String> getListNatIdFromUI(){
        List<String> listNatIdAndReliefFromUI = getListNatIdAndReliefFromUI();
        List<String> listNatIdFromUI = new ArrayList<>();
        for (int i = 0; i < listNatIdAndReliefFromUI.size(); i++){
            if (i % 2 == 0){
                listNatIdFromUI.add(listNatIdAndReliefFromUI.get(i));
            }
        }
        return listNatIdFromUI;
    }

    public List<String> getListReliefFromUI(){
        List<String> listNatIdAndReliefFromUI = getListNatIdAndReliefFromUI();
        List<String> listReliefFromUI = new ArrayList<>();
        for (int i = 0; i < listNatIdAndReliefFromUI.size(); i++){
            if (i % 2 != 0){
                listReliefFromUI.add(listNatIdAndReliefFromUI.get(i));
            }
        }
        return listReliefFromUI;
    }

    public boolean compareReliefActualWithExpected(List<WorkingClassHero> workingClassHeroListFromCSV, List<String> reliefListFromUI) throws Exception {
        if (workingClassHeroListFromCSV.size() == reliefListFromUI.size()){
            for (int i =0 ; i < workingClassHeroListFromCSV.size(); i++){
                System.out.println("Expected relief: " + workingClassHeroListFromCSV.get(i).getRelief());
                System.out.println("Actual relief: " + reliefListFromUI.get(i));
                if (!Objects.equals(workingClassHeroListFromCSV.get(i).getRelief(), reliefListFromUI.get(i))) {
                    return false;
                }
                else{
                    continue;
                }
            }
            return true;
        } else {
            throw new Exception("The Relief list calculated from CSV is not equal to the list from API returns");
        }
    }

    public boolean compareNatIdActualWithExpected(List<WorkingClassHero> workingClassHeroListFromCSV, List<String> natIdListFromUI, int position, String sign ) throws Exception {
        if (workingClassHeroListFromCSV.size() == natIdListFromUI.size()){
            for (int i =0 ; i < workingClassHeroListFromCSV.size(); i++){
                String natid = workingClassHeroListFromCSV.get(i).getNatid();
                natid = natid.substring(0, position - 1) + String.join("", Collections.nCopies(natid.length() - (position - 1), sign));
                System.out.println("Expected natId: " + natid);
                System.out.println("Actual natId: " + natIdListFromUI.get(i));
                if (!natid.equals(natIdListFromUI.get(i))) {
                    return false;
                }
                else{
                    continue;
                }
            }
            return true;
        } else {
            throw new Exception("The Name list from CSV is not equal to the list from API returns");
        }
    }
}
