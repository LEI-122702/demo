package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    // URL da página: https://the-internet.herokuapp.com/login

    // Campo do Username
    @FindBy(id = "username")
    public WebElement usernameInput;

    // Campo da Password
    @FindBy(id = "password")
    public WebElement passwordInput;

    // Botão de Login
    @FindBy(css = "button[type='submit']")
    public WebElement loginButton;

    // Mensagem de sucesso (aquela barra verde que aparece depois de entrar)
    @FindBy(id = "flash")
    public WebElement successMessage;

    // Botão de Logout (para confirmar que estamos dentro)
    @FindBy(css = "a[href='/logout']")
    public WebElement logoutButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}