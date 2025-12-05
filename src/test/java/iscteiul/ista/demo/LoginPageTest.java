package iscteiul.ista.demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class LoginPageTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Abrir a página de Login do "The Internet Heroku App"
        driver.get("https://the-internet.herokuapp.com/login");

        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testLoginComSucesso() {
        // 1. Preencher Username (tomsmith é o user correto deste site)
        loginPage.usernameInput.sendKeys("tomsmith");

        // 2. Preencher Password (SuperSecretPassword! é a pass correta)
        loginPage.passwordInput.sendKeys("SuperSecretPassword!");

        // 3. Clicar no botão
        loginPage.loginButton.click();

        // 4. Verificações (Asserts)
        // O URL deve mudar para /secure
        assertTrue(driver.getCurrentUrl().contains("/secure"), "O URL devia ter mudado para a área segura");

        // Deve aparecer o botão de Logout
        assertTrue(loginPage.logoutButton.isDisplayed(), "O botão de Logout devia estar visível");

        // Deve aparecer a mensagem de sucesso
        assertTrue(loginPage.successMessage.getText().contains("You logged into a secure area"), "A mensagem de sucesso devia aparecer");
    }
}