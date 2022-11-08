package uz.softex.common;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.softex.entity.Role;
import uz.softex.entity.User;
import uz.softex.enums.PermissionEnum;
import uz.softex.enums.RoleEnum;
import uz.softex.repository.RoleRepository;
import uz.softex.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Bekjon Bakhromov
 * @since  04.11.2022
 */
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {

        if (initialMode.equals("always")) {
            Role roleUser = new Role(
                    "USER",
                    null,
                    new HashSet<>(Arrays.asList(PermissionEnum.CREATE_CATEGORY,
                            PermissionEnum.UPDATE_CATEGORY,
                            PermissionEnum.DELETE_CATEGORY,
                            PermissionEnum.GET_CATEGORY)),
                    RoleEnum.ROLE_USER
            );

            Role roleAdmin = new Role(
                    "SUPER_ADMIN",
                    null,
                    new HashSet<>(Arrays.asList(
                           PermissionEnum.CREATE_CATEGORY,
                            PermissionEnum.DELETE_CATEGORY,
                            PermissionEnum.GET_CATEGORY,
                            PermissionEnum.GET_CATEGORY_ALL,
                            PermissionEnum.UPDATE_CATEGORY

                            )),
                    RoleEnum.ROLE_ADMIN
            );

            roleRepository.saveAll(new ArrayList<>(Arrays.asList(roleAdmin, roleUser)));

            User adminUser = User.builder()
                    .setPhoneNumber("admin@gmail.com")
                    .setFirstName("Admin")
                    .setPassword(passwordEncoder.encode("123"))
                    .setRole(roleAdmin)
                    .setEnabled(true)
                    .build();
            userRepository.save(adminUser);

        }

    }
}
