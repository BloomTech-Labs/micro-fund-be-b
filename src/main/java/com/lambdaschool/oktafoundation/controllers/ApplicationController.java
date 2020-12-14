package com.lambdaschool.oktafoundation.controllers;

import com.lambdaschool.oktafoundation.models.User;
import com.lambdaschool.oktafoundation.services.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/apps")
public class ApplicationController
{
    @Autowired
    private ApplicationService appService;

    //KM - gets a list of all applications TODO
    // @GetMapping(value = "/all", produces = "application/json")
    //    public ResponseEntity<?> (@PathVariable)
    //    {
    //
    //    }


    // KM - gets an application by id TODO
    //    @PreAuthorize("hasAnyRole('ADMIN')")
    //    @GetMapping(value = "/app/{id}", produces = "application/json")
    //    public ResponseEntity<?> getUserById(@PathVariable Long userId)
    //    {
    //        User u = userService.findUserById(userId);
    //        return new ResponseEntity<>(u, HttpStatus.OK);
    //    }


    // KM - gets an app by id and does a patch to update a part TODO
    //    @PatchMapping(value = "/app/{id}", consumes = "application/json")
    //    public ResponseEntity<?> updateUser(@RequestBody User updateUser, @PathVariable long id)
    //    {
    //        userService.update(updateUser, id);
    //        return new ResponseEntity<>(HttpStatus.OK);
    //    }

    // KM - gets an app by id and updates the status to 'accept' TODO
    //    @PatchMapping(value = "/app/{id}/accept", consumes = "application/json")
    //    public ResponseEntity<?> updateUser(@RequestBody User updateUser, @PathVariable long id)
    //    {
    //        userService.update(updateUser, id);
    //        return new ResponseEntity<>(HttpStatus.OK);
    //    }

    // KM - gets an app by id and and updates the status to 'reject' TODO
    //    @PatchMapping(value = "/app/{id}/reject", consumes = "application/json")
    //    public ResponseEntity<?> updateUser(@RequestBody User updateUser, @PathVariable long id)
    //    {
    //        userService.update(updateUser, id);
    //        return new ResponseEntity<>(HttpStatus.OK);
    //    }

    // KM - adds a new application to the database TODO
//    @PostMapping(value = "/app/new", consumes = "application/json")
//    public ResponseEntity<?> addNewUser(@Valid
//                                        @RequestBody
//                                            User newuser) throws URISyntaxException
//    {
//        newuser.setUserid(0);
//        newuser = userService.save(newuser);
//
//        // set the location header for the newly created resource
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
//            .path("/{userid}")
//            .buildAndExpand(newuser.getUserid())
//            .toUri();
//        responseHeaders.setLocation(newUserURI);
//
//        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
//    }

}
