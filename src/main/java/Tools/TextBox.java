package Tools;


import Utils.ObjectGenerator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static Tools.BaseClass.getObjectGenerator;

public class TextBox   {
    ObjectGenerator OG = getObjectGenerator();
//    ObjectGenerator OG = new ObjectGenerator();

    public  boolean enterValue(WebDriver driver, WebElement element, String strValue) {
        boolean isValueEntered = false;
        try {
            OG.sync.waitForSeconds(3000);
            if (element.isDisplayed()) {
                String NewTxt = strValue;
                String strOldText= OG.textBox.getText(driver,element);
                if((strOldText == null) && (strOldText.isEmpty())){
                    element.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
                    element.sendKeys(strValue);
                }
                else if (NewTxt.equals(strOldText)){
                    System.out.println(NewTxt +" Already Value is entered");
                }
                else{
                    element.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
                    element.sendKeys(strValue);
                }
                isValueEntered = true;
            } else {
                System.out.println("Element not found to enter value");
            }
        } catch (Exception e) {

            System.out.println("Element not found" + e);
        }
        return isValueEntered;
    }

    public  boolean jsEnterValue(WebDriver driver,WebElement element, String strValue) {
        boolean isEntered = false;
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;

            if (element.isDisplayed()) {
                element.click();
                element.clear();
                executor.executeScript("arguments[0].value='+strValue+;", element);
                isEntered = true;
            } else {
                System.out.println("Element not found to enter value");
            }
        } catch (Exception e) {
            System.out.println("Element not found" + e);
        }
        return isEntered;
    }

    public void verifyElement(WebElement element) {
        if (element.isDisplayed()) {
            System.out.println("Element found");
        } else {
            System.out.println("Element not found");

        }
    }


    public  String getText(WebDriver driver, WebElement element) {
        String strText ="";
        try {
            OG.sync.ExplicityWait(driver, element, 5);
            strText = element.getText();
        } catch (Exception e) {
            System.out.println("Text not found");
        }
        return strText;
    }

    public  void CompareText(WebDriver driver, WebElement element, String Str){

        if (element.isDisplayed()){
            String ST = getText(driver, element);
            if (ST.equals(Str)){
                System.out.println(ST);
            }
        }
    }

    public  void clearValue(WebDriver driver, WebElement element) {

        if (element.isDisplayed()) {
            element = OG.sync.ExplicityWait(driver, element, 5);
            element.clear();
        }
    }

    public  boolean focus(WebDriver driver, WebElement element) {
        boolean isFocused=false;
        try{
            element = OG.sync.ExplicityWait(driver, element, 5);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].focus();", element);
            isFocused = true;
        } catch (Exception e) {
            System.out.println("Element not found");
        }
        return isFocused;
    }

}