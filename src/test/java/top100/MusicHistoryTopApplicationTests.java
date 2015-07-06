package top100;

import com.fevi.music.top100.MusicHistoryTopApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MusicHistoryTopApplication.class)
@WebAppConfiguration
public class MusicHistoryTopApplicationTests {

	@Test
	public void contextLoads() {
	}

}
