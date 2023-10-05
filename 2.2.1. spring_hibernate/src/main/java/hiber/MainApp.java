package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User igor = new User("Igor", "Kalov", "igor1337@mail.ru");
        User tolya = new User("Tolya", "Sidorov", "tolya_228@mail.ru");
        User timur = new User("Timur", "Petrov", "timur_apex@mail.ru");
        User alexey = new User("Alexey", "Ivanov", "leha_onlymid@mail.ru");

        Car volvo = new Car("Audi", 6);
        Car bmw = new Car("BMW", 7);
        Car suzuki = new Car("Dodge", 4);
        Car lada = new Car("Nissan", 1);

        userService.add(igor.setCar(volvo).setUser(igor));
        userService.add(tolya.setCar(bmw).setUser(tolya));
        userService.add(timur.setCar(suzuki).setUser(timur));
        userService.add(alexey.setCar(lada).setUser(alexey));

        for (User user : userService.listUsers()) {
            System.out.println(user + "" + user.getCar() + "\n");
        }

        System.out.println(userService.getUserByCar("BMW", 7));

        context.close();
    }
}
