
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void testEmptyConstructor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 5.3, 4.2));
        String str = "Name cannot be null.";
        assertEquals(str, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f"})
    void testEmptyConstructorOrBlank(String horseName) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(horseName, 2.0, 4.2));
        String str = "Name cannot be blank.";
        assertEquals(str, exception.getMessage());
    }

    @Test
    void testNegativeSpeed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("horseName", -2.0, 4.2));
        String str = "Speed cannot be negative.";
        assertEquals(str, exception.getMessage());
    }

    @Test
    void testNegativeDistance() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("horseName", 2.0, -4.2));
        String str = "Distance cannot be negative.";
        assertEquals(str, exception.getMessage());
    }

    @Test
    void getName() {
        Horse horse = new Horse("nameHorse", 4.0, 5.3);
        assertEquals("nameHorse", horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("nameHorse", 4.0, 5.3);
        assertEquals(4, horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse("nameHorse", 4.0, 5.3);
        assertEquals(5.3, horse.getDistance());
    }

    @Test
    void getZeroDistance() {
        Horse horse = new Horse("nameHorse", 4.0);
        assertEquals(0, horse.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {3.14, 18.0, 13.3, 14.5})
    void getMove(double doubles) {
        Horse horse = new Horse("anyName", 1.0, 1.0);

        try(
        MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
            mockedStatic.when(()->Horse.getRandomDouble(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(doubles);
            horse.move();
            assertEquals(doubles+1,horse.getDistance());
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

}