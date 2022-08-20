package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.delivery.data.DataGenerator.generateDate;
import static ru.netology.delivery.data.DataGenerator.generateUser;


public class CardDeliveryOrderTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }

    @Test
    void shouldSuccessfulPlanAndReplanMeeting() {
        DataGenerator.UserInfo user = generateUser();
        $("[data-test-id=city] input").setValue(user.getCity());
        $(".calendar-input input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input input").setValue(generateDate(6));
        $("[data-test-id=name] input").setValue(user.getName());
        $("[data-test-id=phone] input").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(150));
        $("[data-test-id=success-notification]")
                .shouldHave(exactText("Успешно! Встреча успешно запланирована на " + generateDate(6)));
        $(".calendar-input input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input input").setValue(generateDate(7));
        $(".button").click();
        $(withText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofSeconds(150));
        $(".button__text").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(150)).shouldHave(exactText("Успешно! Встреча успешно запланирована на " + generateDate(7)));
    }
}



