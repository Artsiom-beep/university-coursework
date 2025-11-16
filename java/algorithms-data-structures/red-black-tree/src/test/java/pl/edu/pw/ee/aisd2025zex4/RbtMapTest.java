package pl.edu.pw.ee.aisd2025zex4;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex4.service.MapInterface;

public class RbtMapTest {

    private MapInterface<Integer, String> students;

    @BeforeEach
    public void setup() {
        students = new RbtMap<>();
    }

    @Test
    public void should_ThrowException_When_PuttingNullKey() {
        Integer studentId = null;
        String studentFullName = "Miś Uszatek";

        Throwable thrown = catchThrowable(() -> students.setValue(studentId, studentFullName));

        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Params (key, value) cannot be null.");
    }

    @Test
    public void should_ThrowException_When_PuttingNullValue() {
        Integer studentId = 1;
        String studentFullName = null;

        Throwable thrown = catchThrowable(() -> students.setValue(studentId, studentFullName));

        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Params (key, value) cannot be null.");
    }

    @Test
    public void should_AddAndGetValue() {
        students.setValue(1, "Miś Uszatek");

        String result = students.getValue(1);

        assertThat(result).isEqualTo("Miś Uszatek");
    }

    @Test
    public void should_UpdateValue_When_KeyAlreadyExists() {
        students.setValue(1, "Miś Uszatek");
        students.setValue(1, "Kubuś Puchatek");

        String result = students.getValue(1);

        assertThat(result).isEqualTo("Kubuś Puchatek");
    }

    @Test
    public void should_ReturnNull_When_KeyNotExists() {
        String result = students.getValue(999);

        assertThat(result).isNull();
    }


    @Test
    public void should_HandleMultipleValues() {
        students.setValue(1, "Miś Uszatek");
        students.setValue(2, "Kubuś Puchatek");

        assertThat(students.getValue(1)).isEqualTo("Miś Uszatek");
        assertThat(students.getValue(2)).isEqualTo("Kubuś Puchatek");
    }
}





