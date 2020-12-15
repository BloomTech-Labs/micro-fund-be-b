package com.lambdaschool.oktafoundation.controllers;

import com.github.javafaker.App;
import com.lambdaschool.oktafoundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.oktafoundation.models.Application;
import com.lambdaschool.oktafoundation.models.Organization;
import com.lambdaschool.oktafoundation.models.User;
import com.lambdaschool.oktafoundation.services.ApplicationService;

import com.lambdaschool.oktafoundation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apps")
public class ApplicationController
{
    @Autowired
    private ApplicationService appService;

    @Autowired
    private UserService userService;

    //KM - gets a list of all applications TODO
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> listAllApplications()
    {
        List<Application> myApps = appService.findAll();
        return new ResponseEntity<>(myApps, HttpStatus.OK);
    }

    // KM - gets an application by id TODO
    @PreAuthorize("hasAnyRole('ADMIN', 'PARTNER')")
    @GetMapping(value = "/app/{id}", produces = "application/json")
    public ResponseEntity<?> getAppById(@PathVariable long id)
    {
        Application application = appService.findAppById(id);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }


    // KM - gets an app by id and updates the status to 'accept' TODO
    @PatchMapping(value = "/app/{id}/status", consumes = "application/json")
        public ResponseEntity<?> updateUser(@RequestBody Application updateApp, @PathVariable long id)
        {
            appService.updateAppStatus(updateApp, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    // KM - gets an app by id and and updates the status to 'reject' TODO
   // @PatchMapping(value = "/app/{id}/reject", consumes = "application/json")
    //    public ResponseEntity<?> updateUser(@RequestBody User updateUser, @PathVariable long id)
    //    {
    //        userService.update(updateUser, id);
    //        return new ResponseEntity<>(HttpStatus.OK);
    //    }

    //This is the endpoint for users to use to update their own applications
    @PatchMapping(value = "/app/{appid}", consumes = {"application/json"}) public ResponseEntity<?>
    updateListing(
        Authentication authentication, @RequestBody Application updateApp, @PathVariable long appid)
    {
        User u = userService.findByName(authentication.getName());
        List<Application> userList = new ArrayList<>();
        System.out.println(authentication.getName());

        if(u.getApplications().size() == 0) throw new ResourceNotFoundException("This user does not have this application with this id " + appid);

        for (Application a : u.getApplications())
        {
            if(a.getAppid() == appid)
            {
                userList.add(a);
                System.out.println("this is application" + a);
            }
        }

        appService.update(u, updateApp, appid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // KM - adds a new application to the database TODO
    @PostMapping(value = "/app/new", consumes = "application/json")
    public ResponseEntity<?> addNewApplication(Authentication authentication, @RequestBody Application newapp) throws URISyntaxException
    {
        User u = userService.findByName(authentication.getName());

        newapp.setAppid(0);
        // could be save or saveByAuth
        newapp = appService.saveByAuth(u,newapp);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrgURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{appid}")
            .buildAndExpand(newapp.getAppid())
            .toUri();
        responseHeaders.setLocation(newOrgURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

}
