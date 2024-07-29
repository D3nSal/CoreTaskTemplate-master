package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Николай", "Николаев", (byte)2);
        userService.saveUser("Иван", "Иванов", (byte)18);
        userService.saveUser("Денис", "Денисов", (byte)21);
        userService.saveUser("Егор", "Егоров", (byte)15);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
