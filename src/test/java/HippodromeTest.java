import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class HippodromeTest {
    @Mock
    Hippodrome hippodrome = Mockito.mock();

    @Test
    void testEmptyConstructor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        String str = "Horses cannot be null.";
        assertEquals(str, exception.getMessage());
    }

    @Test
    void testEmptyConstructorOrBlank() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());
        });
        String str = "Horses cannot be empty.";
        assertEquals(str, exception.getMessage());
    }


    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder("Horse");
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(stringBuilder.append(i).toString(), 1.0));
            stringBuilder.delete(5, 7);
        }
        assertEquals(horses, new Hippodrome(horses).getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        new Hippodrome(horses).move();
        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        Horse horse1 = new Horse("Horse1",1, 2.4);
        Horse horse2 = new Horse("Horse2",1, 2.5);
        Horse horse3 = new Horse("Horse3",1, 2.6);
        Horse horse4 = new Horse("Horse4",1, 2.7);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3,horse4));

        assertSame(horse4,hippodrome.getWinner());

    }
}