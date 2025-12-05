package iscteiul.ista.demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions; // Novo import
import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.jetbrains.com/");

        mainPage = new MainPage(driver);

        // Aceitar Cookies
        try {
            if (mainPage.acceptCookiesButton.isDisplayed()) {
                mainPage.acceptCookiesButton.click();
                System.out.println("Cookies aceites.");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            // Ignora
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() throws InterruptedException {
        System.out.println("Teste Search: A clicar na lupa...");
        clickSafely(mainPage.searchButton);

        // Espera a barra abrir (importante!)
        Thread.sleep(2000);

        System.out.println("A tentar escrever...");
        try {
            // Tenta escrever no input identificado
            mainPage.searchInput.sendKeys("Selenium");
            mainPage.searchInput.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            System.out.println("Input não encontrado pelo nome. A tentar escrever no elemento ativo...");
            // PLANO B: Escreve onde quer que o cursor esteja (porque a barra abre com foco)
            driver.switchTo().activeElement().sendKeys("Selenium");
            driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        }

        Thread.sleep(3000);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL final: " + currentUrl);

        // Verifica se o URL mudou
        boolean mudou = currentUrl.contains("s=full") || currentUrl.contains("search") || currentUrl.contains("q=");
        assertTrue(mudou, "O URL não mudou. A pesquisa falhou.");
    }

    @Test
    public void toolsMenu() throws InterruptedException {
        System.out.println("Teste Menu: A abrir...");

        clickSafely(mainPage.toolsMenu);
        Thread.sleep(2000);

        // Se o popup estiver visível OU se o URL mudar (alguns menus são links diretos)
        boolean menuAbriu = false;
        try {
            menuAbriu = mainPage.anySubmenuItem.isDisplayed();
        } catch (Exception e) {
            System.out.println("Submenu não encontrado.");
        }

        assertTrue(menuAbriu, "O submenu devia ter aparecido.");
    }

    @Test
    public void navigationToAllTools() throws InterruptedException {
        System.out.println("Teste Navegação: A correr...");
        clickSafely(mainPage.seeDeveloperToolsButton);
        Thread.sleep(2000);
        clickSafely(mainPage.findYourToolsButton);
        Thread.sleep(2000);
        assertTrue(driver.getCurrentUrl().contains("products"), "Devia estar na página de produtos");
    }

    private void clickSafely(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        }
    }
}