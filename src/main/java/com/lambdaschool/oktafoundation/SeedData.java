package com.lambdaschool.oktafoundation;

import com.lambdaschool.oktafoundation.models.*;
import com.lambdaschool.oktafoundation.services.ApplicationService;
import com.lambdaschool.oktafoundation.services.OrganizationService;
import com.lambdaschool.oktafoundation.services.RoleService;
import com.lambdaschool.oktafoundation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    @Autowired
    OrganizationService organizationService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws Exception
    {
        userService.deleteAll();
        roleService.deleteAll();
        applicationService.deleteAll();

        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("partner");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        Organization organization1 = new Organization("Organization 1", "456 greenville drive, longtucky, ark 80067","567-890-2234");
        Organization organization2 = new Organization("Organization 2", "4 takona lane, nashville, tn 12546","333-222-1111");
        Organization organization3 = new Organization("Organization 3", "678 snowyhille crossing, boulder, c0 80053","888-999-1111");

        organization1 = organizationService.save(organization1);
        organization2 = organizationService.save(organization2);
        organization3 = organizationService.save(organization3);


        // The following is an example user!
        /*
        // admin, data, user
        User u1 = new User("admin",
            "password",
            "admin@lambdaschool.local");
        u1.getRoles()
            .add(new UserRoles(u1,
                r1));
        u1.getRoles()
            .add(new UserRoles(u1,
                r2));
        u1.getRoles()
            .add(new UserRoles(u1,
                r3));
        u1.getUseremails()
            .add(new Useremail(u1,
                "admin@email.local"));
        u1.getUseremails()
            .add(new Useremail(u1,
                "admin@mymail.local"));

        userService.save(u1);
        */


        User u1 = new User("admin@lambdaschool.local");
        u1.getRoles().add(new UserRoles(u1, r1));
        u1.addOrganization(organization1);

        User u2 = new User("coolorg@lambdaschool.local");
        u2.getRoles().add(new UserRoles(u2, r2));
        Application app1 = new Application("Org 1", "123 somewhere drive", "923-567-8965", "i want to help my community", "not reviewed", organization1, u2);
        Application app2 = new Application("Org 2", "124 rainbow lane", "444-111-3333", "i have a great idea i need help with", "not reviewed", organization2, u2);

        u2.getApplications().add(app1);
        u2.getApplications().add(app2);
        u2.addOrganization(organization2);

        User u3 = new User("evencoolerorg@lambdaschool.local");
        u3.getRoles().add(new UserRoles(u3, r3));
        Application app3 = new Application("Org 3", "534 abbey road", "000-345-9807", "i would love to be a part of this", "not reviewed", organization3, u3);

        u3.getApplications().add(app3);
        u3.addOrganization(organization3);

        userService.save(u1);
        userService.save(u2);
        userService.save(u3);


    }
}