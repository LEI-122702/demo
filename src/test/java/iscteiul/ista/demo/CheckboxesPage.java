package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckboxesPage {
    // URL da página: https://the-internet.herokuapp.com/checkboxes

    // Encontrar a Primeira Checkbox (o [1] indica que é a primeira da lista)
    @FindBy(xpath = "//form[@id='checkboxes']/input[1]")
    public WebElement checkbox1;

    // Encontrar a Segunda Checkbox
    @FindBy(xpath = "//form[@id='checkboxes']/input[2]")
    public WebElement checkbox2;

    public CheckboxesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}