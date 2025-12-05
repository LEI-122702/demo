package iscteiul.ista.demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.time.Duration;

public class FileUploadTest {
    private WebDriver driver;
    private FileUploadPage uploadPage;

    // Defina aqui o caminho ABSOLUTO para um pequeno ficheiro no seu computador.
    // Exemplo: C:\\temp\\teste.txt ou /home/user/teste.txt
    private final String FILE_TO_UPLOAD = new File("src/test/resources/ficheiro-teste.txt").getAbsolutePath();


    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Abrir a página de File Upload
        driver.get("https://the-internet.herokuapp.com/upload");

        uploadPage = new FileUploadPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSuccessfulFileUpload() throws InterruptedException {
        // 1. Simular a seleção do ficheiro
        System.out.println("A tentar selecionar o ficheiro: " + FILE_TO_UPLOAD);

        // O método sendKeys() no <input type="file"> faz o upload imediatamente
        uploadPage.fileInput.sendKeys(FILE_TO_UPLOAD);

        // 2. Clicar no botão de Upload
        System.out.println("A clicar no botão de upload...");
        uploadPage.uploadButton.click();

        // 3. Verificações (Asserts)

        // Verifica se o título da página de sucesso está correto
        assertEquals("File Uploaded!", uploadPage.successPageHeader.getText(), "O cabeçalho devia ser 'File Uploaded!'");

        // Verifica se o nome do ficheiro carregado aparece na lista de ficheiros
        String expectedFileName = "ficheiro-teste.txt"; // Deve corresponder ao nome do ficheiro real
        String actualUploadedText = uploadPage.uploadedFileNameText.getText();

        assertTrue(actualUploadedText.contains(expectedFileName),
                "O nome do ficheiro carregado (" + actualUploadedText + ") não contém o nome esperado: " + expectedFileName);

        System.out.println("Upload realizado com sucesso: " + actualUploadedText);
    }
}