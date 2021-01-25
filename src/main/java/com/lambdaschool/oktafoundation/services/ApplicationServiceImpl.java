package com.lambdaschool.oktafoundation.services;
import com.github.javafaker.App;
import com.lambdaschool.oktafoundation.exceptions.ResourceFoundException;
import com.lambdaschool.oktafoundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.oktafoundation.models.Application;
import com.lambdaschool.oktafoundation.models.Organization;
import com.lambdaschool.oktafoundation.models.User;
import com.lambdaschool.oktafoundation.repository.ApplicationRepository;

import com.lambdaschool.oktafoundation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "applicationService")
public class ApplicationServiceImpl implements ApplicationService
{
    @Autowired
    private ApplicationRepository apprepos;

    @Autowired
    private UserRepository userrepos;

    @Transactional
    @Override
    public void deleteAll()
    {
        apprepos.deleteAll();
    }

    @Override
    public List<Application> findAll()
    {
        List<Application> list = new ArrayList<>();

        apprepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Application findAppById(long id)
    {
        return apprepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Application id " + id + " not found!"));
    }

    @Transactional
    @Override
    public Application save(Application application)
    {
        Application newApp = new Application();

        if (application.getAppid() != 0)
        {
            apprepos.findById(application.getAppid()).orElseThrow(() -> new ResourceNotFoundException("Application id " + application.getAppid() + " not found!"));
            newApp.setAppid(application.getAppid());
        }

        //        String name,
        //        String address,
        //        String phone,
        //        String reason,
        //        String status

        newApp.setName(application.getName());

        newApp.setAddress(application.getAddress());

        newApp.setPhone(application.getPhone());

        newApp.setReason(application.getReason());

        newApp.setStatus(application.getStatus());

        //user
        newApp.setUser(application.getUser());

        //organization
        newApp.setOrganization(application.getOrganization());


        return apprepos.save(newApp);
    }

    @Transactional
    @Override
    public Application update(User user, Application application, long id)
    {
        User currUser = user;

        Application currentApp = apprepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Application id " + id + " not found!"));

//        String name,
//        String address,
//        String phone,
//        String reason,
//        String status,
//        Organization organization,
//        User user

        if (application.getName() != null)
        {
            currentApp.setName(application.getName());
        }

        if (application.getAddress() != null)
        {
            currentApp.setAddress(application.getAddress());
        }

        if (application.getPhone() != null)
        {
            currentApp.setPhone(application.getPhone());
        }

        if (application.getReason() != null)
        {
            currentApp.setReason(application.getReason());
        }
        //since users won't update this then we can either leave out or reset it here
        if (application.getStatus() != null)
        {
            currentApp.setPhone(application.getPhone());
        }
        // applications many to one with organization
        currentApp.setOrganization(application.getOrganization());
        // applications many to one with user
        currentApp.setUser(currUser);

        return apprepos.save(currentApp);
    }

    @Transactional
    @Override
    public Application updateAppStatus(Application application, long id)
    {
        Application currentApp = apprepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Application id " + id + " not found!"));

        // users won't update this
        if (application.getStatus() != null)
        {
            currentApp.setStatus(application.getStatus());
        }
        // applications many to one with organization
        currentApp.setOrganization(application.getOrganization());
        // applications many to one with user
        currentApp.setUser(application.getUser());

        return apprepos.save(currentApp);
    }

    @Transactional
    @Override
    public Application saveByAuth(
        User user,
        Application application, long id)
    {
        Application newApp = new Application();
        User dbuser = userrepos.findById(user.getUserid())
            .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found"));
        newApp.setUser(dbuser);


        if (application.getAppid() != 0)
        {
            apprepos.findById(application.getAppid())
                .orElseThrow(() -> new ResourceNotFoundException("App id " + application.getAppid() + " not found!"));
        }

        //        String name,
        //        String address,
        //        String phone,
        //        String reason,
        //        String status,
        //        Organization organization,
        //        User user

        newApp.setName(application.getName());
        newApp.setAddress(application.getAddress());
        newApp.setPhone(application.getPhone());
        newApp.setReason(application.getReason());
        newApp.setStatus(application.getStatus());
        // Application has a many to one relationship with organization
        newApp.setOrganization(application.getOrganization());
        // Application has a many to one relationship with user
        newApp.setUser(user);

        return apprepos.save(newApp);
    }

}
