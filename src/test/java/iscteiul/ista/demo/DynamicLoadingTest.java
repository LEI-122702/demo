package iscteiul.ista.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DynamicLoadingTest {
    private WebDriver driver;
    private DynamicLoadingPage loadingPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navegar para o Exemplo 1 do Dynamic Loading
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        loadingPage = new DynamicLoadingPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testDynamicLoadingHiddenElement() {
        // 1. Clicar no botão Start
        loadingPage.clickStart();

        // 2. Esperar e verificar se o texto aparece
        // O método que criámos na Page Object trata da espera (WebDriverWait)
        boolean isVisible = loadingPage.isFinishTextDisplayed();

        // Asserts (Verificações)
        assertTrue(isVisible, "O texto final devia estar visível (falha na espera?)");
        assertEquals("Hello World!", loadingPage.getFinishText(), "O texto apresentado não está correto");
    }
}