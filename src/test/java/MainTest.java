import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;



class MainTest {

    @Test
    @Deprecated
    @Timeout(22)
    void main() throws Exception {
        String []args = new String[0];
        Main.main(args);

    }
}