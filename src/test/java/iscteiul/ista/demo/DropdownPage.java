package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage {
    // URL: https://the-internet.herokuapp.com/dropdown

    @FindBy(id = "dropdown")
    public WebElement dropdownElement;

    public DropdownPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Seleciona uma opção pelo texto visível.
     */
    public void selectOptionByText(String optionText) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(optionText);
    }

    /**
     * Obtém o texto da opção que está atualmente selecionada.
     */
    public String getSelectedOptionText() {
        Select dropdown = new Select(dropdownElement);
        return dropdown.getFirstSelectedOption().getText();
    }
}