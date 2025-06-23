package com.forms.cartadespido.form;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.forms.cartadespido.form.InputsUtils.rellenarFormularioByName;
import static com.forms.cartadespido.form.InputsUtils.safeSleep;

public class Navigator {

    private static final Dotenv dotenv = Dotenv.load();

    final String url = dotenv.get("URL");
    final String rut = dotenv.get("RUT");
    final String password = dotenv.get("PASSWORD");

    public WebDriver loginForm(){
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.OFF);
        Logger.getLogger("org.openqa.selenium.chromium.ChromiumDriver").setLevel(Level.OFF);
        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get(url);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            login(wait);
            safeSleep(1);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }

        safeSleep(2);
        return driver;

    }

    public WebDriver navToForm(WebDriver driver, Long rutEmpleador) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            empleadorNav(wait, rutEmpleador);
            safeSleep(1);

            cartaNav(wait);
            safeSleep(1);

            driver = cartaAviso(driver, wait);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        } finally {
            safeSleep(2);
        }

        return driver;
    }

    private void empleadorNav(WebDriverWait wait, Long rutEmpleador) {
        WebElement empleadorPersonaButtom, empleadorButtom, empresaButton;

        empleadorButtom = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-empleador")));
        empleadorButtom.click();

        empleadorPersonaButtom = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='title accordion-title' and contains(., 'Empleador Persona Jurídica')]")
        ));
        empleadorPersonaButtom.click();

        if (rutEmpleador == 768816506) {
            empresaButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@class='ui basic button' and contains(.,'76881650-6 ALTERNATTIVA EMPRESA DE SERVICIOS TRANSITORIOS LIMITADA')]")
            ));
            empresaButton.click();
        } else if (rutEmpleador == 797770108) {
            empresaButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@class='ui basic button' and contains(.,'79777010-8 MARKETING Y PROMOCIONES S.A.')]")
            ));
            empresaButton.click();
        } else {
            throw new IllegalArgumentException("Rut Empleador no reconocido");
        }

    }

    private void cartaNav(WebDriverWait wait) {
        WebElement cartaDespidoButtom, contratosButtom;

        contratosButtom = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='title accordion-title' and contains(., 'Contratos de Trabajo y Despido')]")

        ));
        contratosButtom.click();

        cartaDespidoButtom = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='clickable-text' and contains(., 'Carta electrónica de aviso de despido')]")
        ));
        cartaDespidoButtom.click();
    }

    private WebDriver cartaAviso(WebDriver driver, WebDriverWait wait) {

        driver = switchToNewTabAndCloseOld(driver, wait);

        WebElement cartaAvisoBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a.CA"))
        );
        cartaAvisoBtn.click();

        return driver;
    }

    private WebDriver switchToNewTabAndCloseOld(WebDriver driver, WebDriverWait wait) {
        String originalTab = driver.getWindowHandle();
        wait.until(d -> d.getWindowHandles().size() > 1);

        String newTab = driver.getWindowHandles().stream()
                .filter(handle -> !handle.equals(originalTab))
                .findFirst().orElseThrow(() -> new RuntimeException("No se encontró nueva pestaña"));

        driver.switchTo().window(originalTab).close();
        driver.switchTo().window(newTab);
        return driver;
    }

    private void login(WebDriverWait wait) {
        WebElement loginButtom, submitButton;
        safeSleep(2);

        loginButtom = wait.until(ExpectedConditions.elementToBeClickable(By.id("nuevaSesion")));
        loginButtom.click();

        safeSleep(2);

        rellenarFormularioByName(wait, "run", rut);
        rellenarFormularioByName(wait, "password", password);

        submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-submit")));
        submitButton.click();
    }

}
