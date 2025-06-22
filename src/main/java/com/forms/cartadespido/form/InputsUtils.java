package com.forms.cartadespido.form;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InputsUtils {

    public static void rellenarFormularioByName(WebDriverWait wait, String name, String value) {
        WebElement inputElement = wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
        inputElement.clear();
        inputElement.sendKeys(value);
    }

    public static void rellenarSelectByName(WebDriverWait wait, String name, String value) {
        Select inputElement = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.name(name))));
        inputElement.selectByValue(value);
    }

    public static void safeSleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
