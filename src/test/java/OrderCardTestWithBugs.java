import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderCardTestWithBugs {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }


    @Test
    void ShouldNotOrderWithInvalidPhone() {
        DataGenerator.Registration.UserInfo validUser = DataGenerator.Registration.generateUser("ru");
        int days = 4;
        String meetingDate = DataGenerator.generateDate(days);

        String secondMeetingDate = DataGenerator.generateDate(days);
        $("[data-test-id = city] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date]  input").setValue(meetingDate);
        $("[data-test-id = name] input").setValue(DataGenerator.generateName("ru")) ;
        $("[data-test-id = phone] input").setValue(DataGenerator.generatePhoneNumber("UA"));
        $("[data-test-id = agreement]").click();
        $("[data-test-id = phone] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".button__text").shouldHave(Condition.text("Запланировать")).click();

    }
}
