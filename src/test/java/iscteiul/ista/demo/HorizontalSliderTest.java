package iscteiul.ista.demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions; // NOVO IMPORT!
import org.openqa.selenium.support.ui.WebDriverWait;     // NOVO IMPORT!
import java.time.Duration;

public class HorizontalSliderTest {
    private WebDriver driver;
    private HorizontalSliderPage sliderPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // A Implicit Wait está definida, mas usaremos Explicit Wait abaixo
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/horizontal_slider");
        sliderPage = new HorizontalSliderPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSetSliderValue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String targetValue1 = "3.5";

        // --- TESTE 1 ---
        sliderPage.setSliderValue(targetValue1);

        // --- Espera Explícita ---
        // Espera ATÉ que o texto no elemento 'rangeValue' seja igual a 3.5
        wait.until(ExpectedConditions.textToBePresentInElement(
                sliderPage.rangeValue, targetValue1));

        // Asserção
        String actualValue1 = sliderPage.getCurrentRangeValue();
        assertEquals(targetValue1, actualValue1, "O valor do slider devia ser " + targetValue1);


        // --- TESTE 2 ---
        String targetValue2 = "1.0";
        sliderPage.setSliderValue(targetValue2);

        // --- Espera Explícita ---
        // Espera ATÉ que o texto no elemento 'rangeValue' seja igual a 1.0
        wait.until(ExpectedConditions.textToBePresentInElement(
                sliderPage.rangeValue, targetValue2));

        // Asserção (Esta é a linha que está a falhar, agora deve funcionar)
        String actualValue2 = sliderPage.getCurrentRangeValue();
        assertEquals(targetValue2, actualValue2, "O valor do slider devia ser " + targetValue2);
    }
}