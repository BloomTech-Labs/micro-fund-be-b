package com.lambdaschool.oktafoundation.controllers;

import com.lambdaschool.oktafoundation.models.User;
import com.lambdaschool.oktafoundation.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orgs")
public class OrganizationController
{
    @Autowired
    private OrganizationService orgService;

//KM - gets a list of all the organizations TODO
 // @GetMapping(value = "/all", produces = "application/json")
 //    public ResponseEntity<?> (@PathVariable)
 //    {
 //
 //    }

 // KM - gets a list of all the organizations TODO
 //    @PreAuthorize("hasAnyRole('ADMIN')")
 //    @GetMapping(value = "/org/{id}", produces = "application/json")
 //    public ResponseEntity<?> getUserById(@PathVariable Long userId)
 //    {
 //        User u = userService.findUserById(userId);
 //        return new ResponseEntity<>(u, HttpStatus.OK);
 //    }

 // KM - gets an organization by id and their associated users TODO
 //    @PreAuthorize("hasAnyRole('ADMIN')")
 //    @GetMapping(value = "/org/{id}/users", produces = "application/json")
 //    public ResponseEntity<?> getUserById(@PathVariable Long userId)
 //    {
 //        User u = userService.findUserById(userId);
 //        return new ResponseEntity<>(u, HttpStatus.OK);
 //    }

 // KM - gets an organization by id and their associated applications TODO
 //    @PreAuthorize("hasAnyRole('ADMIN')")
 //    @GetMapping(value = "/org/{id}/apps", produces = "application/json")
 //    public ResponseEntity<?> getUserById(@PathVariable Long userId)
 //    {
 //        User u = userService.findUserById(userId);
 //        return new ResponseEntity<>(u, HttpStatus.OK);
 //    }

    // KM - gets an organization by id and deletes it TODO
    // @PreAuthorize("hasAnyRole('ADMIN')")
    // @DeleteMapping(value = "/org/{id}")
    // public ResponseEntity<?> deleteUserById(@PathVariable long id)
    //    {
    //        userService.delete(id);
    //        return new ResponseEntity<>(HttpStatus.OK);
    //    }

    // KM - gets an organization by id and does a patch to update a part TODO
//    @PatchMapping(value = "/org/{id}", consumes = "application/json")
//    public ResponseEntity<?> updateUser(@RequestBody User updateUser, @PathVariable long id)
//    {
//        userService.update(updateUser, id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
 }




