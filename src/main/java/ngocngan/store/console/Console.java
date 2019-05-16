package ngocngan.store.console;

import ngocngan.store.controller.MainController;
import ngocngan.store.models.Role;
import ngocngan.store.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;
import java.util.UUID;

/**
 * @author ngan nnh on 5/13/2019
 * @project sweet
 */
@Controller
public class Console {
    private static Scanner scanner = new Scanner(System.in);

    private static RoleRepository roleRepository;

    @Autowired public Console(RoleRepository roleRepository) {
        Console.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        addRole();
        for(Role role: roleRepository.findAll()){
            System.out.println(role.getRole());
        }
    }

    private static void addRole() {
        Role role = new Role();
        role.setId(1);
        role.setRole(scanner.next());
        roleRepository.save(role);
    }
}
