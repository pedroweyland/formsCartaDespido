package com.forms.cartadespido.form;

import com.forms.cartadespido.model.Amount;
import com.forms.cartadespido.model.Details;
import com.forms.cartadespido.model.Quotes;
import com.forms.cartadespido.model.Worker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.forms.cartadespido.form.InputsUtils.*;

public class FillForms {

    public void fillForm(Worker worker, Details detail, Amount amount, Quotes quote, WebDriver driver) {

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
            safeSleep(2);
        }
    }

    private void fillFormWorkers(Worker worker, WebDriverWait wait) {
        WebElement sexoRadio, submitButton;

        rellenarFormularioByName(wait, "RutTrab", worker.getRutTrab().toString());

        rellenarFormularioByName(wait, "Nom", worker.getNom());

        rellenarFormularioByName(wait, "ApePat", worker.getApePat());

        rellenarFormularioByName(wait, "ApeMat", worker.getApeMat());

        rellenarFormularioByName(wait, "SelDom", worker.getSelDom());

        rellenarSelectByName(wait, "CodCom", worker.getCodCom().toString());

        String sexoValue = String.valueOf(worker.getSex()); // 1 = Masculino, 2 = Femenino
        sexoRadio = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@name='Sex' and @value='" + sexoValue + "']")
        ));
        sexoRadio.click();

        rellenarSelectByName(wait, "nac", worker.getNac().toString());

        submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("Siguiente")));
        submitButton.click();
    }

    private void fillFormDetails(Details detail, WebDriverWait wait, WebDriver driver) {
        WebElement formaRadio, fechaInicioInput, fechaTerminoInput, fechaComisionInput;
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

        rellenarFormularioByName(wait, "Motivo", detail.getMotivo());

    }

    private void fillFormAmount(Amount amount, WebDriverWait wait) {

        rellenarFormularioByName(wait, "AnoSer", amount.getAnoSer().toString());

        rellenarFormularioByName(wait, "Aviso", amount.getAviso().toString());
    }

    private void fillFormQuotes(Quotes quote, WebDriverWait wait) {

        rellenarFormularioByName(wait, "Prev", quote.getPrev());

        String docValue = String.valueOf(quote.getDoc());
        WebElement docInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@name='Doc' and @value='" + docValue + "']")
        ));
        docInput.click();
    }

}
