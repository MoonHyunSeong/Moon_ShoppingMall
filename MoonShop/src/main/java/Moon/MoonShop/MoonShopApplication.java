package moon.moonShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class MoonShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoonShopApplication.class, args);
	}

}
