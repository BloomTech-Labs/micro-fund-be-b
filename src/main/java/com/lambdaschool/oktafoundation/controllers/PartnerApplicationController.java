package com.lambdaschool.oktafoundation.controllers;

import com.lambdaschool.oktafoundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.oktafoundation.models.PartnerApplication;
import com.lambdaschool.oktafoundation.models.User;
import com.lambdaschool.oktafoundation.services.PartnerApplicationService;
import com.lambdaschool.oktafoundation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/partnerapps")
public class PartnerApplicationController
{
    @Autowired
    private PartnerApplicationService partnerApplicationService;

    @Autowired
    private UserService userService;

    // Gets all applications w.a.
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> listAllPartApplications()
    {
        List<PartnerApplication> myPartApps = partnerApplicationService.findAll();
        return new ResponseEntity<>(myPartApps, HttpStatus.OK);
    }

    // Gets one partner application by id w.a.
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/partapp/{id}", produces = "application/json")
    public ResponseEntity<?> getPartAppById(@PathVariable long id)
    {
        PartnerApplication partnerApplication = partnerApplicationService.findPartAppById(id);
        return new ResponseEntity<>(partnerApplication, HttpStatus.OK);
    }
    // Gets the partner application by id and updates the status to 'accept' w.a.
    @PatchMapping(value = "/app/{id}/status", consumes = "application/json")
    public ResponseEntity<?> updateUser(@RequestBody PartnerApplication updatePartApp, @PathVariable long id)
    {
        partnerApplicationService.updatePartAppStatus(updatePartApp, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Users use this endpoint to update their own applications
    @PatchMapping(value = "/partapp/{id}", consumes = "application/json")
    public ResponseEntity<?> updateList(Authentication authentication, @RequestBody PartnerApplication updatePartApp, @PathVariable long id)
    {
        User user = userService.findByName(authentication.getName());
        List<PartnerApplication> userList = new ArrayList<>();
        System.out.println(authentication.getName());



        if(user.getApplications().size() == 0) throw new ResourceNotFoundException("This user does not have this application with this id " + id);

        for (PartnerApplication p : user.getPartnerApplications())
        {
            if(p.getPartappid() == id)
            {
                userList.add(p);
                System.out.println("this is application " + p);

            }
        }

        partnerApplicationService.update(user, updatePartApp, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // posts new application to database w.a.
    @PostMapping(value = "/partapp/new", consumes = "application/json")
    public ResponseEntity<?> addNewPartnerApplication(Authentication authentication, @RequestBody PartnerApplication newapp) throws URISyntaxException
    {
        User user = userService.findByName(authentication.getName());

        newapp.setPartappid(0);
        newapp = partnerApplicationService.saveByAuth(user, newapp);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrgURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/appid")
            .buildAndExpand(newapp.getPartappid())
            .toUri();
        responseHeaders.setLocation(newOrgURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
