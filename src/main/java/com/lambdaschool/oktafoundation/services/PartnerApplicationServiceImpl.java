package com.lambdaschool.oktafoundation.services;


import com.lambdaschool.oktafoundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.oktafoundation.models.Application;
import com.lambdaschool.oktafoundation.models.PartnerApplication;
import com.lambdaschool.oktafoundation.models.User;
import com.lambdaschool.oktafoundation.repository.PartnerApplicationRepository;
import com.lambdaschool.oktafoundation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "partnerApplicationService")
public class PartnerApplicationServiceImpl implements PartnerApplicationService
{
    @Autowired
    private PartnerApplicationRepository partnerapprepos;

    @Autowired
    private UserRepository userrepos;


    @Transactional
    @Override
    public void deleteAll()
    {
        partnerapprepos.deleteAll();
    }

    @Override
    public PartnerApplication save(PartnerApplication partnerApplication)
    {
        PartnerApplication newPartApp = new PartnerApplication();

        if (partnerApplication.getPartappid() != 0)
        {
            partnerapprepos.findById(partnerApplication.getPartappid()).orElseThrow(() -> new ResourceNotFoundException("Application id " + partnerApplication.getPartappid() + " not found!"));
            newPartApp.setPartappid(partnerApplication.getPartappid());
        }
        newPartApp.setName(partnerApplication.getName());

        newPartApp.setAddress(partnerApplication.getAddress());

        newPartApp.setPhone(partnerApplication.getPhone());

        newPartApp.setType(partnerApplication.getType());

        newPartApp.setDescription(partnerApplication.getDescription());

        newPartApp.setStatus(partnerApplication.getStatus());

        //user
        newPartApp.setUser(partnerApplication.getUser());




        return partnerapprepos.save(newPartApp);
    }

    @Transactional
    @Override
    public PartnerApplication update(User user, PartnerApplication partnerApplication, long id)
    {
        User currentUser = user;

        PartnerApplication currentPartApp = partnerapprepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Application id " + id + " not found!"));

        if (partnerApplication.getName() != null)
        {
            currentPartApp.setName(partnerApplication.getName());
        }
        if (partnerApplication.getAddress() != null)
        {
            currentPartApp.setAddress(partnerApplication.getAddress());
        }

        if (partnerApplication.getPhone() != null)
        {
            currentPartApp.setPhone(partnerApplication.getPhone());
        }

        if (partnerApplication.getType() != null)
        {
            currentPartApp.setType(partnerApplication.getType());
        }

        if (partnerApplication.getDescription() != null)
        {
            currentPartApp.setDescription(partnerApplication.getDescription());
        }
        //since users won't update this then we can either leave out or reset it here
        if (partnerApplication.getStatus() != null)
        {
            currentPartApp.setPhone(partnerApplication.getPhone());
        }

        return partnerapprepos.save(currentPartApp);
    }

    @Transactional
    @Override
    public PartnerApplication updatePartAppStatus(
        PartnerApplication partnerApplication,
        long id)
    {
        PartnerApplication currentApp = partnerapprepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Application id " + id + " not found!"));

        // user can check status
        if (partnerApplication.getStatus() != null)
        {
            currentApp.setStatus(partnerApplication.getStatus());
        }

        // many to one user
        currentApp.setUser(partnerApplication.getUser());

        return partnerapprepos.save(currentApp);
    }

    @Override
    public List<PartnerApplication> findAll()
    {
        List<PartnerApplication> list = new ArrayList<>();

        partnerapprepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public PartnerApplication findPartAppById(long id)
    {
        return partnerapprepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Application id " + id + " not found!"));
    }

    @Transactional
    @Override
    public PartnerApplication saveByAuth(User user, PartnerApplication partnerApplication)
    {
        PartnerApplication newPartApp = new PartnerApplication();
//        User u = userrepos.findById(user.getUserid())
//            .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found!"));
//        newPartApp.setUser(u);

        if (partnerApplication.getPartappid() != 0)
        {
            partnerapprepos.findById(partnerApplication.getPartappid())
                .orElseThrow(() -> new ResourceNotFoundException("Application id " + partnerApplication.getPartappid() + " not found!"));
        }

        newPartApp.setName(partnerApplication.getName());
        newPartApp.setAddress(partnerApplication.getAddress());
        newPartApp.setPhone(partnerApplication.getPhone());
        newPartApp.setType(partnerApplication.getType());
        newPartApp.setDescription(partnerApplication.getDescription());
        newPartApp.setUser(user);

        return partnerapprepos.save(newPartApp);
    }
}
