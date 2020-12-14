package com.lambdaschool.oktafoundation.controllers;

import com.lambdaschool.oktafoundation.models.Organization;
import com.lambdaschool.oktafoundation.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/orgs")
public class OrganizationController
{
    @Autowired
    private OrganizationService orgService;

     @GetMapping(value = "/all", produces = "application/json")
        public ResponseEntity<?> listAllOrgs()
        {
              List<Organization> myOrgs = orgService.findAll();
              return new ResponseEntity<>(myOrgs, HttpStatus.OK);
        }

        @PreAuthorize("hasAnyRole('ADMIN', 'PARTNER')")
        @GetMapping(value = "/org/{id}/users", produces = "application/json")
        public ResponseEntity<?> getOrgUsersById(@PathVariable long id)
        {
            Organization og = orgService.findOrgById(id);
            return new ResponseEntity<>(og.getUsers(), HttpStatus.OK);
        }

        @GetMapping(value = "/org/{id}", produces = "application/json")
        public ResponseEntity<?> getOrgById(@PathVariable long id)
        {
            Organization og = orgService.findOrgById(id);
            return new ResponseEntity<>(og, HttpStatus.OK);
        }

        @PreAuthorize("hasAnyRole('ADMIN', 'PARTNER')")
        @GetMapping(value = "/org/{id}/apps", produces = "application/json")
        public ResponseEntity<?> getOrgAppsById(@PathVariable long id)
        {
            Organization og = orgService.findOrgById(id);
            return new ResponseEntity<>(og.getApplications(), HttpStatus.OK);
        }

        @PreAuthorize("hasAnyRole('ADMIN')")
        @DeleteMapping(value = "/org/{id}")
        public ResponseEntity<?> deleteOrgById(@PathVariable long id)
        {
            orgService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        @PatchMapping(value = "/org/{id}", consumes = "application/json")
        public ResponseEntity<?> updateOrg(@RequestBody Organization updateOrg, @PathVariable long id)
        {
            orgService.update(updateOrg, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
 }




