package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("Ivan", "Petrov", "evan@mail.ru",
                new Car("Porsche", "Carrera GT")));
        userService.add(new User("Nick", "Cave", "ncave@mail.ru",
                new Car("Mercedes", "GL Class")));
        userService.add(new User("Petro", "Poorman", "petropoor@mail.ru",
                new Car("Opel", "Cadett")));

        List<User> users = userService.listUsers();
        for (User user : users) {
            printUser(user);
        }
        User user = userService.getUserByCar("Opel", "Cadett");
        printUser(user);
        context.close();
    }

    private static void printUser(User user) {
        System.out.println("Id = " + user.getId());
        System.out.println("First Name = " + user.getFirstName());
        System.out.println("Last Name = " + user.getLastName());
        System.out.println("Email = " + user.getEmail());
        System.out.println("Car = " + user.getCar());
        System.out.println();
    }
}
