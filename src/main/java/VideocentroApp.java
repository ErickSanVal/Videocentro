import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.apprentice.videocentro")
public class VideocentroApp {
    public static void main(String[] args) {
        SpringApplication.run(VideocentroApp.class, args);
    }
}
