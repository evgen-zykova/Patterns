package ru.netology.delivery.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;
import ru.netology.delivery.data.UserInfo;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.delivery.data.DataGenerator.Registration.generateDate;


public class CardDeliveryOrderTest {
    UserInfo user = DataGenerator.Registration.generateUser("ru");

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }

    @Test
    void shouldSuccessfulPlanAndReplanMeeting() {
        $("[data-test-id=city] input").setValue(user.getCity());
        $(".calendar-input input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input input").setValue(generateDate(6));
        $("[data-test-id=name] input").setValue(user.getName());
        $("[data-test-id=phone] input").setValue(user.getNumber());
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=success-notification]")
                .shouldHave(exactText("Успешно! Встреча успешно запланирована на " + generateDate(6)));
        $(".calendar-input input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input input").setValue(generateDate(7));
        $(".button").click();
        $$(".button__text").find(exactText("Перепланировать")).click();
        $(".notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + generateDate(7)))
                .shouldBe(visible);
    }
}



