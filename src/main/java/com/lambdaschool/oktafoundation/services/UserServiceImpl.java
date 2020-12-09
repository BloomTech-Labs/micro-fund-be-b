package com.lambdaschool.oktafoundation.services;

import com.lambdaschool.oktafoundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.oktafoundation.models.*;
import com.lambdaschool.oktafoundation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements UserService Interface
 */
@Transactional
@Service(value = "userService")
public class UserServiceImpl
    implements UserService
{
    /**
     * Connects this service to the User table.
     */
    @Autowired
    private UserRepository userrepos;

    /**
     * Connects this service to the Role table
     */
    @Autowired
    private RoleService roleService;

    @Autowired
    private HelperFunctions helperFunctions;

    public User findUserById(long id) throws
                                      ResourceNotFoundException
    {
        return userrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public List<User> findByNameContaining(String username)
    {

        return userrepos.findByUsernameContainingIgnoreCase(username.toLowerCase());
    }

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        userrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        userrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);
    }

    @Override
    public User findByName(String username)
    {
        User uu = userrepos.findByUsername(username.toLowerCase());
        if (uu == null)
        {
            throw new ResourceNotFoundException("Username " + username + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public User save(User user)
    {

        User newUser = new User();

        if (user.getUserid() != 0)
        {
            userrepos.findById(user.getUserid())
                .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found!"));
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername()
            .toLowerCase());

        newUser.getRoles()
            .clear();
        for (UserRoles ur : user.getRoles())
        {
            Role addRole = roleService.findRoleById(ur.getRole()
                .getRoleid());
            newUser.getRoles()
                .add(new UserRoles(newUser,
                    addRole));
        }

        newUser.getUseremails()
            .clear();
        for (Useremail ue : user.getUseremails())
        {
            newUser.getUseremails()
                .add(new Useremail(newUser,
                    ue.getUseremail()));
        }

        newUser.setAddress(user.getAddress());

        newUser.setPhone(user.getPhone());

        newUser.setImageUrl(user.getImageUrl());

        newUser.setDescription(user.getDescription());

        /**
         * Save feature for organizations and applications (SFritz)
         */
        newUser.getOrganizations().clear();
        for (Organization og : user.getOrganizations())
        {
            newUser.getOrganizations()
                    .add(new Organization(og.getName(),
                            og.getAddress(),
                            og.getPhone()));
        }

        newUser.getApplications().clear();
        for (Application ap : user.getApplications())
        {
            newUser.getApplications()
                    .add(new Application(ap.getName(),
                            ap.getAddress(),
                            ap.getPhone(),
                            ap.getReason(),
                            ap.getStatus()));
        }

        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User update(
        User user,
        long id)
    {
        User currentUser = findUserById(id);

        // update own thing
        // admin update
        if (helperFunctions.isAuthorizedToMakeChange(currentUser.getUsername()))
        {
            if (user.getUsername() != null)
            {
                currentUser.setUsername(user.getUsername()
                    .toLowerCase());
            }

            if (user.getRoles()
                .size() > 0)
            {
                currentUser.getRoles()
                    .clear();
                for (UserRoles ur : user.getRoles())
                {
                    Role addRole = roleService.findRoleById(ur.getRole()
                        .getRoleid());

                    currentUser.getRoles()
                        .add(new UserRoles(currentUser,
                            addRole));
                }
            }

            if (user.getUseremails()
                .size() > 0)
            {
                currentUser.getUseremails()
                    .clear();
                for (Useremail ue : user.getUseremails())
                {
                    currentUser.getUseremails()
                        .add(new Useremail(currentUser,
                            ue.getUseremail()));
                }
            }

            /**
             * New stuff added by SFritz
             */
            if (user.getAddress() != null)
            {
                currentUser.setAddress(user.getAddress());
            }

            if (user.getPhone() != null)
            {
                currentUser.setPhone(user.getPhone());
            }

            if (user.getImageUrl() != null)
            {
                currentUser.setImageUrl(user.getImageUrl());
            }

            if (user.getDescription() != null)
            {
                currentUser.setDescription(user.getDescription());
            }

            if (user.getOrganizations().size() > 0)
            {
                currentUser.getOrganizations().clear();
                for (Organization og : user.getOrganizations())
                {
                    currentUser.getOrganizations()
                            .add(new Organization(og.getName(),
                                    og.getAddress(),
                                    og.getPhone()));
                }
            }

            if (user.getApplications().size() > 0)
            {
                currentUser.getApplications().clear();
                for (Application ap : user.getApplications())
                {
                    currentUser.getApplications()
                            .add(new Application(ap.getName(),
                                    ap.getAddress(),
                                    ap.getPhone(),
                                    ap.getReason(),
                                    ap.getStatus()));
                }
            }

            return userrepos.save(currentUser);
        } else
        {
            // note we should never get to this line but is needed for the compiler
            // to recognize that this exception can be thrown
            throw new ResourceNotFoundException("This user is not authorized to make change");
        }
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        userrepos.deleteAll();
    }
}
