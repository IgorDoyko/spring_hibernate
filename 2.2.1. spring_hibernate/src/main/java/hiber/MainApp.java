package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("userWithMersedes", "Lastname5", "user5@mail.ru", new Car("Mersedes", 777)));
        userService.add(new User("userWithAudi", "Lastname6", "user6@mail.ru", new Car("Audi", 333)));
        userService.add(new User("userWithToyota", "Lastname7", "user7@mail.ru", new Car("Toyota", 111)));
        userService.add(new User("userWithAudi2", "Lastname7", "user7@mail.ru", new Car("Audi", 666)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Model = " + user.getCar().getModel());
            System.out.println("Series = " + user.getCar().getSeries());
            System.out.println();
        }

        User user = userService.getUser("Audi",333);
        System.out.println(user.getFirstName());
        context.close();
    }
}
