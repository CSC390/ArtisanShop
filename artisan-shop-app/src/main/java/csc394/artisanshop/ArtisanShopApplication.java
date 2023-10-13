package csc394.artisanshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class
})
//@ComponentScan(basePackages = {"csc394.artisanshop.repositories", "csc394.artisanshop.services"})
        public class ArtisanShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtisanShopApplication.class, args);
    }

}
