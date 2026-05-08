package group.contactmanager2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "group.contactmanager2")
public class Config {
    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }
}