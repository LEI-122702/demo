package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FileUploadPage {
    private WebDriver driver;

    // Elemento para selecionar o ficheiro. É um <input type="file">.
    @FindBy(id = "file-upload")
    public WebElement fileInput;

    // Botão para submeter o upload
    @FindBy(id = "file-submit")
    public WebElement uploadButton;

    // O elemento na página de resultado que mostra o nome do ficheiro carregado
    @FindBy(id = "uploaded-files")
    public WebElement uploadedFileNameText;

    // A mensagem principal (h3) da página de sucesso
    @FindBy(xpath = "//h3")
    public WebElement successPageHeader;

    // Construtor
    public FileUploadPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
