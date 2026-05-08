package group.contactmanager2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        TableRepository tableRepository = (TableRepository) applicationContext.getBean("tableRepository");
        tableRepository.create();
        ContactController contactController = applicationContext.getBean("contactController", ContactController.class);
        contactController.start();
    }
}