package com.katanemimena.project;

import com.katanemimena.project.entity.ERole;
import com.katanemimena.project.entity.Role;
import com.katanemimena.project.entity.User;
import com.katanemimena.project.repository.RoleRepository;
import com.katanemimena.project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;


/**
 * This component will only execute (and get instantiated) if the
 * property com.katanemimena.project.controller.recreate is set to true in the
 * application.properties file
 */

@Component
@ConditionalOnProperty(name = "com.katanemimena.project.controller.recreate", havingValue = "true")
public class DbSeeder implements  CommandLineRunner{

    private static final Logger log = LoggerFactory.getLogger(DbSeeder.class);

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRepository userRepo;

    public DbSeeder(RoleRepository roleRepo, UserRepository userRepo) {

        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws ParseException {

        //Insert all roles
        Role role_User = new Role(ERole.ROLE_USER);
        Role role_Notary = new Role(ERole.ROLE_NOTARY);
        Role role_Admin = new Role(ERole.ROLE_ADMIN);
        log.info("Preloading " + roleRepo.save(role_User));
        log.info("Preloading " + roleRepo.save(role_Notary));
        log.info("Preloading " + roleRepo.save(role_Admin));

        //Insert User - Admin
        User user = new User("admin", "123456", "Ad", "Min", "test@test.com");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role_User);
        roleSet.add(role_Notary);
        roleSet.add(role_Admin);
        user.setRoles(roleSet);
        log.info("Preloading " + userRepo.save(user));

        //Insert a User - Notary
        User user1 = new User("notary", "123456", "Not", "Ary", "test@test.com");
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role_User);
        roleSet1.add(role_Notary);
        roleSet1.add(role_Admin);
        user1.setRoles(roleSet1);
        log.info("Preloading " + userRepo.save(user1));

        //Insert a User - User
        User user2 = new User("user", "123456", "Us", "Er", "test@test.com");
        Set<Role> roleSet2 = new HashSet<>();
        roleSet2.add(role_User);
        roleSet2.add(role_Notary);
        roleSet2.add(role_Admin);
        user2.setRoles(roleSet2);
        log.info("Preloading " + userRepo.save(user2));
    }
}

