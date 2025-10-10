import org.example.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {
    @Test
    public void shouldTestMain() {

        Main.main(new String[]{"123"});

    }
}
