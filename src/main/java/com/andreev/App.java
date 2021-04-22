package com.andreev;

import com.andreev.configuration.RestConfig;
import com.andreev.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RestConfig.class);
        Communication communication = context.getBean("communication", Communication.class);

        communication.getUsers();

        User user = new User(3L, "James", "Brown", (byte)44);

        communication.saveUser(user);

        user.setName("Thomas");
        user.setLastName("Shelby");
        communication.editUser(user);
        communication.deleteUser();
    }
}
