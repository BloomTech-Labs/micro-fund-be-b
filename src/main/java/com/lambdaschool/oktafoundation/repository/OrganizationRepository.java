package com.lambdaschool.oktafoundation.repository;

import com.lambdaschool.oktafoundation.models.Organization;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<Organization, Long>
{
}
