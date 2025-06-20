package com.forms.cartadespido.form;

import com.forms.cartadespido.model.entity.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FillForms {

    final String url = "https://tramites.dirtrab.cl/registroempresa/Cartas/CartasInd?Rut=NzY4ODE2NTAtNg%3D%3D&Razon=QUxURVJOQVRUSVZBIEVNUFJFU0EgREUgU0VSVklDSU9TIFRSQU5TSVRPUklPUyBMSU1JVEFEQQ%3D%3D&RutTrab=0&token=7bcd43ec-ed41-4aa3-8c07-9e0e35cb3a5c";

    public void fillForm(Worker worker, Details detail, Amount amount, Quotes quote) {

        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get(url);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            fillFormWorkers(worker, wait);
            fillFormDetails(detail, wait, driver);
            fillFormAmount(amount, wait);
            fillFormQuotes(quote, wait);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        } finally {
            safeSleep(3);
            driver.quit();
        }
    }

    private void fillFormWorkers(Worker worker, WebDriverWait wait) {
        WebElement rutInput, nameInput, lastNamePatInput, lastNameMatInput, domicilioInput, sexoRadio, submitButton;
        Select comunaInput, nacionalidadInput;

        rutInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("RutTrab")));
        rutInput.sendKeys(worker.getRutTrab().toString());

        nameInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("Nom")));
        nameInput.sendKeys(worker.getNom());

        lastNamePatInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("ApePat")));
        lastNamePatInput.sendKeys(worker.getApePat());

        lastNameMatInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("ApeMat")));
        lastNameMatInput.sendKeys(worker.getApeMat());

        domicilioInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("SelDom")));
        domicilioInput.sendKeys(worker.getSelDom());

        comunaInput = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.name("CodCom"))));
        comunaInput.selectByValue(worker.getCodCom().toString());

        String sexoValue = String.valueOf(worker.getSex()); // 1 = Masculino, 2 = Femenino
        sexoRadio = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@name='Sex' and @value='" + sexoValue + "']")
        ));
        sexoRadio.click();

        nacionalidadInput = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.name("nac"))));
        nacionalidadInput.selectByValue(worker.getNac().toString());

        submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("Siguiente")));
        submitButton.click();
    }

    private void fillFormDetails(Details detail, WebDriverWait wait, WebDriver driver) {
        WebElement formaRadio, fechaInicioInput, fechaTerminoInput, fechaComisionInput, motivoInput;
        Select causalInput;
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String formaValue = String.valueOf(detail.getForma());
        formaRadio = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@name='Forma' and @value='" + formaValue + "']")
        ));
        formaRadio.click();

        fechaInicioInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("FecIniCon")));
        js.executeScript("arguments[0].value = arguments[1];", fechaInicioInput, detail.getFecIniCon());

        fechaTerminoInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("FecTerCon")));
        js.executeScript("arguments[0].value = arguments[1];", fechaTerminoInput, detail.getFecTerCon());

        fechaComisionInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("FecComDes")));
        js.executeScript("arguments[0].value = arguments[1];", fechaComisionInput, detail.getFecComDes());

        causalInput = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.name("Causal"))));
        causalInput.selectByVisibleText(detail.getCausal());

        motivoInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("Motivo")));
        motivoInput.sendKeys(detail.getMotivo());
    }

    private void fillFormAmount(Amount amount, WebDriverWait wait) {
        WebElement anoSerInput, avisoInput;

        anoSerInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("AnoSer")));
        anoSerInput.sendKeys(amount.getAnoSer().toString());

        avisoInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("Aviso")));
        avisoInput.sendKeys(amount.getAviso().toString());
    }

    private void fillFormQuotes(Quotes quote, WebDriverWait wait) {
        WebElement prevInput, docInput;

        prevInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("Prev")));
        prevInput.sendKeys(quote.getPrev());

        String docValue = String.valueOf(quote.getDoc());
        docInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@name='Doc' and @value='" + docValue + "']")
        ));
        docInput.click();
    }

    private void safeSleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
