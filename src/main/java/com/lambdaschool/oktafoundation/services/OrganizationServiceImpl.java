package com.lambdaschool.oktafoundation.services;

import com.lambdaschool.oktafoundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.oktafoundation.models.Application;
import com.lambdaschool.oktafoundation.models.Organization;
import com.lambdaschool.oktafoundation.models.User;
import com.lambdaschool.oktafoundation.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "organizationService")
public class OrganizationServiceImpl implements OrganizationService
{
    @Autowired
    private OrganizationRepository orgrepos;

    @Autowired
    private HelperFunctions helperFunctions;

    @Override
    public List<Organization> findAll()
    {
        List<Organization> list = new ArrayList<>();

        orgrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public Organization findOrgById(long id) throws ResourceNotFoundException
    {
        return orgrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization id " + id + " not found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        orgrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Organization id " + id + " not found!"));
        orgrepos.deleteById(id);
    }

    @Transactional

    @Override
    public Organization save(Organization organization)
    {
        Organization newOrg = new Organization();

        if (organization.getOrgid() != 0)
        {
            orgrepos.findById(organization.getOrgid())
                    .orElseThrow(() -> new ResourceNotFoundException("Organization id " + organization.getOrgid() + " not found!"));
            newOrg.setOrgid(organization.getOrgid());
        }

        newOrg.setName(organization.getName());

        newOrg.setAddress(organization.getAddress());

        newOrg.setPhone(organization.getPhone());

        newOrg.getApplications().clear();
        for (Application ap : organization.getApplications())
        {
            newOrg.getApplications()
                    .add(new Application(ap.getName(),
                            ap.getAddress(),
                            ap.getPhone(),
                            ap.getReason(),
                            ap.getStatus()));
        }

        newOrg.getUsers().clear();
        for (User u : organization.getUsers())
        {
            newOrg.getUsers()
                    .add(new User(u.getUsername()));
        }

        return orgrepos.save(newOrg);
    }

    @Transactional
    @Override
    public Organization update(Organization organization,
                               long id)
    {
        Organization currentOrg = findOrgById(id);

        if (organization.getName() != null)
        {
            currentOrg.setName(organization.getName());
        }

        if (organization.getAddress() != null)
        {
            currentOrg.setAddress(organization.getAddress());
        }

        if (organization.getPhone() != null)
        {
            currentOrg.setPhone(organization.getPhone());
        }

        if (organization.getApplications().size() > 0)
        {
            currentOrg.getApplications().clear();
            for (Application ap : organization.getApplications())
            {
                currentOrg.getApplications()
                        .add(new Application(ap.getName(),
                                ap.getAddress(),
                                ap.getPhone(),
                                ap.getReason(),
                                ap.getStatus()));
            }
        }

        if (organization.getUsers().size() > 0)
        {
            currentOrg.getUsers().clear();
            for (User u : organization.getUsers())
            {
                currentOrg.getUsers().add(new User(u.getUsername()));
            }
        }

        return orgrepos.save(currentOrg);
    }
}
