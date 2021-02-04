package com.lambdaschool.oktafoundation.services;

import com.lambdaschool.oktafoundation.models.Organization;

import java.util.List;
public interface OrganizationService
{
    List<Organization> findAll();

    Organization findOrgById(long id);

    Organization findByName(String name);

    List<Organization> findByNameContaining(String name);

    void delete(long id);

    Organization update(Organization organization, long id);

    Organization save(Organization organization);
}
