import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;
public class OrderCardTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void ShouldNotOrderWithInvalidName() {
        DataGenerator.Registration.UserInfo validUser = DataGenerator.Registration.generateUser("en-US");
        int days = 4;
        String meetingDate = DataGenerator.generateDate(days);

        String secondMeetingDate = DataGenerator.generateDate(days);
        $("[data-test-id = city] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date]  input").setValue(meetingDate);
        $("[data-test-id = name] input").setValue(DataGenerator.generateName("en-US")) ;
        $("[data-test-id = phone] input").setValue(DataGenerator.generatePhoneNumber("ru"));
        $("[data-test-id = agreement]").click();
        $(".button__text").shouldHave(Condition.text("Запланировать")).click();
        $("[data-test-id = name].input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }
}
