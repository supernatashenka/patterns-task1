import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Locale;



public class ChangeDateTest {
    public static Faker faker;

    @BeforeAll
    static void setFaker() {
        faker = new Faker(new Locale("ru"));
    }
    @Test
    void ShouldGenerateTestData(){
        String name = faker.name().fullName();
        String phone = faker.phoneNumber().phoneNumber();
        printTestData(name,phone);
    }

    private void printTestData(String name, String phone) {
    }


}