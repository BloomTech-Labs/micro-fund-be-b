package com.lambdaschool.oktafoundation.services;
import com.lambdaschool.oktafoundation.repository.ApplicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "applicationService")
public class ApplicationServiceImpl implements ApplicationService
{
    @Autowired
    private ApplicationRepository apprepos;
}
