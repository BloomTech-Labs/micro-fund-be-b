package com.lambdaschool.oktafoundation.services;

import com.lambdaschool.oktafoundation.models.Application;
import com.lambdaschool.oktafoundation.models.Organization;
import com.lambdaschool.oktafoundation.models.Role;
import com.lambdaschool.oktafoundation.models.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ApplicationService
{
    public void deleteAll();
    /**
     * Given a complete Application object, saved that Application object in the database.
     * If a primary key is provided, the record is completely replaced
     * If no primary key is provided, one is automatically generated and the record is added to the database.
     * <p>
     * Note that Users are not added to Roles through this process
     *
     *
     * @return the saved role object including any automatically generated fields
     */
    Application save(Application application);

    Application findAppById(long id);

    List<Application> findAll();

    Application update(User user, Application application, long id);

    Application updateAppStatus(Application application, long id);

    Application saveByAuth(User user, Application application);

}
