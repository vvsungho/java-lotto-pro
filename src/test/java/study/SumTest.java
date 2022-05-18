package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class SumTest {

    @ParameterizedTest
    @DisplayName("숫자합 계산을 위한 입력값이 null 혹은 빈문자 인지 검증한다.")
    @CsvSource(value = {"''|0"}, delimiter = '|')
    void testNumbersSplitAndSum_null_또는_빈문자(String input, String expected) {
        Sum sum = new Sum(Sum.sumValue(input));
        assertThat(sum).isEqualTo(new Sum(StringParser.stringToIntValue(expected)));
    }

    @ParameterizedTest
    @CsvSource(value = {"1|1"}, delimiter = '|')
    public void testNumbersSplitAndSum_숫자하나(String input, String expected) {
        Sum sum = new Sum(Sum.sumValue(input));
        assertThat(sum).isEqualTo(new Sum(StringParser.stringToIntValue(expected)));
    }

    @ParameterizedTest
    @CsvSource(value = {"1,2,3|6"}, delimiter = '|')
    public void testNumbersSplitAndSum_쉼표구분자(String input, String expected) {
        Sum sum = new Sum(Sum.sumValue(input));
        assertThat(sum).isEqualTo(new Sum(StringParser.stringToIntValue(expected)));
    }

    @ParameterizedTest
    @CsvSource(value = {"1,2:4|7"}, delimiter = '|')
    public void testNumbersSplitAndSum_쉼표_또는_콜론_구분자(String input, String expected) {
        Sum sum = new Sum(Sum.sumValue(input));
        assertThat(sum).isEqualTo(new Sum(StringParser.stringToIntValue(expected)));
    }

    @Test
    public void testNumbersSplitAndSum_custom_구분자() {
        Sum sum = new Sum(Sum.sumValue("//;\n1;2;3"));
        assertThat(sum).isEqualTo(new Sum(6));
    }

    @ParameterizedTest
    @CsvSource(value = {"1,-2,3|6"}, delimiter = '|')
    public void testNumbersSplitAndSum_negative(String input) {
        Throwable thrown = catchThrowable(() -> new Sum(Sum.sumValue(input)));
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 허용하지 않은 값 입니다.");
    }
}