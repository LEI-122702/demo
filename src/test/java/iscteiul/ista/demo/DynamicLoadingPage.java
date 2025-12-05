package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicLoadingPage {
    private WebDriver driver;

    // Elemento: Botão Start
    @FindBy(css = "#start button")
    private WebElement startButton;

    // Elemento: Texto "Hello World!" que aparece no fim
    @FindBy(css = "#finish h4")
    private WebElement finishText;

    // Construtor
    public DynamicLoadingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Ação: Clicar no botão
    public void clickStart() {
        startButton.click();
    }

    // Ação: Verificar se o texto apareceu (COM ESPERA EXPLÍCITA)
    public boolean isFinishTextDisplayed() {
        // Cria uma espera de até 10 segundos
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // O Selenium vai "congelar" aqui até o elemento ser visível
            wait.until(ExpectedConditions.visibilityOf(finishText));
            return finishText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Ação: Obter o texto para validação
    public String getFinishText() {
        return finishText.getText();
    }
}