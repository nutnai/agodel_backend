package agodel.seeder;

import agodel.data.UserRepository;
import agodel.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder {

    private final UserRepository userRepository;

    @Autowired
    public UserSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
        seedUserData();
    }

    private void seedUserData() {
        userRepository.save(new UserModel("10000001", "customer", "password"));
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("agodel");
        UserSeeder userSeeder = context.getBean(UserSeeder.class);
        userSeeder.seedUserData();
    }
}