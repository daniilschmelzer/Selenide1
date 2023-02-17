import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;

public class SelenideTest {

    @Test
    void shouldTest() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();
        String currentDateStr = dtf.format(now.plusDays(3));
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Ярославль");
        $("[placeholder='Дата встречи']").setValue(currentDateStr);
        $("[name='name']").setValue("Лютенков Даниил");
        $("[name='phone']").setValue("+79806532600");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("div[class='notification__content']").should(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDateStr));
    }
}
