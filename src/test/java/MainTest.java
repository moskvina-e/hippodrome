import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


class MainTest {

    @Test
    @Disabled
    @Timeout(22)
    @DisplayName("Check timeout 22 sec for main")
    void checkTimeout22SecForMain() throws Exception {
        String[] args = new String[0];
        Main.main(args);
    }
}