package iscteiul.ista.demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class DropdownTest {
    private WebDriver driver;
    private DropdownPage dropdownPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dropdown");
        dropdownPage = new DropdownPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testDropdownSelection() {
        // 1. Seleciona a Option 1
        dropdownPage.selectOptionByText("Option 1");

        // 2. Verifica se a opção 1 ficou selecionada
        assertEquals("Option 1", dropdownPage.getSelectedOptionText(), "A Option 1 devia estar selecionada");

        // 3. Tenta selecionar a Option 2
        dropdownPage.selectOptionByText("Option 2");

        // 4. Verifica se a opção 2 ficou selecionada
        assertEquals("Option 2", dropdownPage.getSelectedOptionText(), "A Option 2 devia estar selecionada");
    }
}