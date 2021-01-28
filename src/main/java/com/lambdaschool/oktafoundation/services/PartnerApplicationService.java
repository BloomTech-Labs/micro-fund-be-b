package com.lambdaschool.oktafoundation.services;

import com.lambdaschool.oktafoundation.models.PartnerApplication;
import com.lambdaschool.oktafoundation.models.User;

import java.util.List;

public interface PartnerApplicationService
{
    public void deleteAll();

    PartnerApplication save(PartnerApplication partnerApplication);

    PartnerApplication update(User user, PartnerApplication partnerApplication, long id);

    PartnerApplication updatePartAppStatus(PartnerApplication partnerApplication, long id);

    List<PartnerApplication> findAll();

    PartnerApplication findPartAppById(long id);

    PartnerApplication saveByAuth(User user, PartnerApplication app);
}
