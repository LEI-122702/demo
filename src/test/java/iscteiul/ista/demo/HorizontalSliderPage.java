package iscteiul.ista.demo;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HorizontalSliderPage {
    private WebDriver driver;

    @FindBy(tagName = "input")
    public WebElement sliderInput;

    @FindBy(id = "range")
    public WebElement rangeValue;

    public HorizontalSliderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Define o valor do slider, resetando o valor para 0.0 (HOME) e usando setas.
     * Esta versão usa um RESET agressivo para garantir o movimento.
     */
    public void setSliderValue(String value) {
        // 1. Clicar para garantir o foco
        sliderInput.click();

        try {
            // 2. Limpar o campo com CTRL+A e DELETE (ou Command+A em Mac).
            // Isto garante que qualquer valor anterior seja removido.
            String os = System.getProperty("os.name").toLowerCase();
            Keys ctrl = os.contains("mac") ? Keys.COMMAND : Keys.CONTROL;

            sliderInput.sendKeys(Keys.chord(ctrl, "a"), Keys.DELETE);

            // 3. Resetar para 0.0 (HOME)
            sliderInput.sendKeys(Keys.HOME);

            double target = Double.parseDouble(value);
            int steps = (int) Math.round(target / 0.5);

            // 4. Enviar as setas direitas
            for (int i = 0; i < steps; i++) {
                sliderInput.sendKeys(Keys.ARROW_RIGHT);
            }

        } catch (NumberFormatException e) {
            System.err.println("Erro: Valor inválido: " + value + " | " + e.getMessage());
        }
    }

    /**
     * Obtém o valor atual exibido junto ao slider.
     */
    public String getCurrentRangeValue() {
        return rangeValue.getText();
    }
}