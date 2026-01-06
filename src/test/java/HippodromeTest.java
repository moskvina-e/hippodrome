import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    private List<Horse> horses = new ArrayList<>();
    private Hippodrome hippodrome;

    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("Name horse " + i, i, i));
        }
        hippodrome = new Hippodrome(horses);
    }

    @Test
    @DisplayName("When create Hippodrome with null")
    void whenCreateHippodromeWithNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("When create Hippodrome with empty List")
    void whenCreateHippodromeWithEmptyList() {
        horses = new ArrayList<>();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses() {
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 0; i <= 50; i++) {
            Horse mockHorse = Mockito.mock(Horse.class);
            mockHorses.add(mockHorse);
        }
        hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();

        for (Horse horse : mockHorses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner() {
    assertEquals(horses.get(29), hippodrome.getWinner());
    }
}