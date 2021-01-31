import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {
    @ParameterizedTest(name = "return {1} when given {0}")
    @CsvSource({
            "1,'1'",
            "2,'2'",
            "3,'Fizz'",
            "5,'Buzz'",
            "13,'Fizz'",
            "25,'Buzz'",
            "15,'FizzBuzz'",
            "35,'FizzBuzz'"
    })
    void test(int input, String output) {
        assertThat(FizzBuzz.of(input)).isEqualTo(output);
    }
}