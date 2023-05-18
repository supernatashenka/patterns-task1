import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
public class OrderCardTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }
    @Test
    void ShouldNotOrderWithInvalidCity() {
        DataGenerator.Registration.UserInfo invalidUser = DataGenerator.Registration.generateInvalidUser("ru");
        int days = 4;
        String meetingDate = DataGenerator.generateDate(days);

        $("[data-test-id = city] input").setValue(invalidUser.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date]  input").setValue(meetingDate);
        $("[data-test-id = phone] input").setValue(DataGenerator.generatePhoneNumber("ru"));
        $("[data-test-id = agreement]").click();
        $(".button__text").shouldHave(Condition.text("Запланировать")).click();
        $("[data-test-id='city'] span.input__sub").shouldHave(exactText
                ("Доставка в выбранный город недоступна")).shouldBe(visible);

    }

    @Test
    void ShouldNotOrderWithEmptyCity() {
        DataGenerator.Registration.UserInfo validUser = DataGenerator.Registration.generateUser("ru");
        int days = 4;
        String meetingDate = DataGenerator.generateDate(days);

        $("[data-test-id = city] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date]  input").setValue(meetingDate);
        $("[data-test-id = phone] input").setValue(DataGenerator.generatePhoneNumber("ru"));
        $("[data-test-id = agreement]").click();
        $(".button__text").shouldHave(Condition.text("Запланировать")).click();
        $("[data-test-id = city] span.input__sub").shouldHave(exactText
                ("Поле обязательно для заполнения")).shouldBe(visible);

    }



    @Test
    void ShouldNotOrderWithInvalidName() {
        DataGenerator.Registration.UserInfo validUser = DataGenerator.Registration.generateUser("en-US");
        int days = 4;
        String meetingDate = DataGenerator.generateDate(days);

        $("[data-test-id = city] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date]  input").setValue(meetingDate);
        $("[data-test-id = name] input").setValue(DataGenerator.generateName("en-US")) ;
        $("[data-test-id = phone] input").setValue(DataGenerator.generatePhoneNumber("ru"));
        $("[data-test-id = agreement]").click();
        $(".button__text").shouldHave(Condition.text("Запланировать")).click();
        $("[data-test-id = name] span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно." +
                " Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(visible);

    }

    @Test
    void ShouldNotOrderWithEmptyName() {
        DataGenerator.Registration.UserInfo validUser = DataGenerator.Registration.generateUser("ru");
        int days = 4;
        String meetingDate = DataGenerator.generateDate(days);

        $("[data-test-id = city] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date]  input").setValue(meetingDate);
        $("[data-test-id = name] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = phone] input").setValue(DataGenerator.generatePhoneNumber("ru"));
        $("[data-test-id = agreement]").click();
        $(".button__text").shouldHave(Condition.text("Запланировать")).click();
        $("[data-test-id = name] span.input__sub").shouldHave(exactText
                ("Поле обязательно для заполнения")).shouldBe(visible);

    }

    @Test
    void ShouldNotOrderWithEmptyPhone() {
        DataGenerator.Registration.UserInfo validUser = DataGenerator.Registration.generateUser("ru");
        int days = 4;
        String meetingDate = DataGenerator.generateDate(days);

        $("[data-test-id = city] input").setValue(validUser.getCity());
        $("[data-test-id = name] input").setValue(DataGenerator.generateName("ru")) ;
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date]  input").setValue(meetingDate);
        $("[data-test-id = phone] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = agreement]").click();
        $(".button__text").shouldHave(Condition.text("Запланировать")).click();
        $("[data-test-id = phone] span.input__sub").shouldHave(exactText
                ("Поле обязательно для заполнения")).shouldBe(visible);

    }

    @Test
    void ShouldNotOrderWithoutAgreement() {
        DataGenerator.Registration.UserInfo validUser = DataGenerator.Registration.generateUser("ru");
        int days = 4;
        String meetingDate = DataGenerator.generateDate(days);

        $("[data-test-id = city] input").setValue(validUser.getCity());
        $("[data-test-id = name] input").setValue(DataGenerator.generateName("ru")) ;
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date]  input").setValue(meetingDate);
        $("[data-test-id = phone] input").setValue(DataGenerator.generatePhoneNumber("ru"));
        $(".button__text").shouldHave(Condition.text("Запланировать")).click();
        $( ".checkbox__text" ).shouldHave( exactText( "Я соглашаюсь с условиями обработки " +
                "и использования моих персональных данных" ) ).shouldBe( Condition.visible );

    }
//


}
