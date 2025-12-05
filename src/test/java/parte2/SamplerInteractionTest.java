package parte2;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Importações estáticas para simplificar a escrita
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SamplerInteractionTest {

    @BeforeAll
    public static void setUpAll() {
        // Configurar o browser e o tamanho da janela
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        // Ligar o Selenide ao Allure para o relatório
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        // Abrir o site de documentação do Vaadin
        open("https://vaadin.com/docs/latest/components");

        // Fechar popup de cookies se aparecer
        if ($("button#CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll").exists()) {
            $("button#CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll").click();
        }
    }

    @Test
    public void testInteractionComponent() {
        // 1. Clicar em "Button" no menu lateral
        $(byText("Button")).click();

        // 2. Verificar que o título mudou para "Button"
        $("h1").shouldHave(text("Button"));

        // 3. Verificar que o botão de exemplo "Primary" está visível
        $(byText("Primary")).shouldBe(visible);
    }
}