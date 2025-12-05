package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.jetbrains.com/
public class MainPage {

    // Botão Cookies
    @FindBy(xpath = "//button[@data-test='allow-all' or text()='Accept All']")
    public WebElement acceptCookiesButton;

    // --- PESQUISA ---
    @FindBy(xpath = "//*[@data-test='site-header-search-action' or @aria-label='Open search']")
    public WebElement searchButton;

    @FindBy(xpath = "//input[@type='search' or @data-test='search-input']")
    public WebElement searchInput;

    // --- MENU ---
    // O botão principal
    @FindBy(xpath = "//*[@data-test='main-menu-item' and contains(., 'Developer Tools')]")
    public WebElement toolsMenu;

    // A CORREÇÃO:
    // Em vez de procurar uma caixa invisível, procuramos o link do "IntelliJ IDEA"
    // que só fica visível se o menu abrir.
    @FindBy(xpath = "//a[contains(text(), 'IntelliJ IDEA')]")
    public WebElement anySubmenuItem;

    // --- NAVEGAÇÃO ---
    @FindBy(xpath = "//*[@data-test-marker='Developer Tools']")
    public WebElement seeDeveloperToolsButton;

    @FindBy(xpath = "//*[@data-test='suggestion-link'] | //a[contains(@href, '/products/')]")
    public WebElement findYourToolsButton;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}