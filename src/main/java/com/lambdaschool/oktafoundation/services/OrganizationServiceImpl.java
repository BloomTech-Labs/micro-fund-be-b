package com.lambdaschool.oktafoundation.services;
import com.lambdaschool.oktafoundation.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "organizationService")
public class OrganizationServiceImpl implements OrganizationService
{
    @Autowired
    private OrganizationRepository orgrepos;
}
