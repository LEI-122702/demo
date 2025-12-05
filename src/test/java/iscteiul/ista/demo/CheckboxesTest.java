package iscteiul.ista.demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class CheckboxesTest {
    private WebDriver driver;
    private CheckboxesPage checkboxesPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Abrir a página das Checkboxes
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        checkboxesPage = new CheckboxesPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testCheckboxes() {
        // --- TESTE DA CHECKBOX 1 ---
        // 1. Verificar que começa desmarcada
        assertFalse(checkboxesPage.checkbox1.isSelected(), "A Checkbox 1 devia começar vazia");

        // 2. Clicar para marcar
        checkboxesPage.checkbox1.click();

        // 3. Verificar que ficou marcada
        assertTrue(checkboxesPage.checkbox1.isSelected(), "A Checkbox 1 devia estar marcada agora");


        // --- TESTE DA CHECKBOX 2 ---
        // 4. Verificar que começa JÁ marcada (neste site é assim por defeito)
        assertTrue(checkboxesPage.checkbox2.isSelected(), "A Checkbox 2 devia começar marcada");

        // 5. Clicar para desmarcar
        checkboxesPage.checkbox2.click();

        // 6. Verificar que ficou desmarcada
        assertFalse(checkboxesPage.checkbox2.isSelected(), "A Checkbox 2 devia estar vazia agora");
    }
}