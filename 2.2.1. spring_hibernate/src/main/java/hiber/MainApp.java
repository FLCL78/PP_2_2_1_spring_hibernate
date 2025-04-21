package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      for (int i = 1; i < 5; i++) {
         Car car = new Car("Test" + i, new Random().nextInt(1000000));
         User user = new User("User" + i, "Lastname" + i, "user" + i + "@mail.ru", car);
         car.setUser(user);
         userService.add(user);
      }

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      System.out.println(userService. getUserFromCar("Test4",874069));

      context.close();
   }
}
