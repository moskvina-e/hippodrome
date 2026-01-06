import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    private static final String VALID_NAME = "Фру-Фру";
    private static final double VALID_SPEED = 10.0;
    private static final double VALID_DISTANCE = 100.0;

    private Horse horse;

    @BeforeEach
    void setUp() {
        // Создаем валидную лошадь перед каждым тестом
        horse = new Horse(VALID_NAME, VALID_SPEED, VALID_DISTANCE);
    }

    @Test
    @DisplayName("When create horse with null name then IAE")
    void whenCreateHorseWihtNullNameThenIae() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, VALID_SPEED));
        assertEquals("Name cannot be null.", exception.getMessage());
    }
    
    @ParameterizedTest
    @ValueSource(strings = {
            "",           // empty
            " ",          // space
            "  ",         // multiple spaces
            "\t",         // tab
            "\n",         // newline
            "\r\n",       // windows newline
    })
    @DisplayName("When create horse with blank name then IAE")
    void whenCreateHorseWithBlankNameThenIae(String blankname) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(blankname, VALID_SPEED));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    @DisplayName("When create horse with negative speed then IAE")
    void whenCreateHorseWithNegativeSpeedThenIae() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(VALID_NAME, -10.00));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("When create horse with negative distance then IAE")
    void whenCreateHorseWithNegativeDistanceThenIae() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(VALID_NAME, VALID_SPEED, -10.00));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        assertEquals(VALID_NAME, horse.getName());
    }

    @Test
    void getSpeed() {
        assertEquals(VALID_SPEED, horse.getSpeed());
    }

    @Test
    void getDistance() {
        assertEquals(VALID_DISTANCE, horse.getDistance());
    }
    
    @Test
    @DisplayName("When GetDistance method should return zero")
    void whenGetDistanceMethodShouldReturnZero() {
        horse = new Horse(VALID_NAME, VALID_SPEED);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void move() {
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.5, 0.8})
    @DisplayName("Is the method move calls getRandomDouble")
    void isTheMethodMoveCallsGetRandomDouble(double mockedRandomDouble) {
        try (MockedStatic<Horse> mockedHorse = mockStatic(Horse.class)) {

            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockedRandomDouble);

            double startDistance = horse.getDistance();

            horse.move();
            mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            double newDistance = VALID_DISTANCE + VALID_SPEED * mockedRandomDouble;
            assertEquals(newDistance, horse.getDistance());
        }

    }
}