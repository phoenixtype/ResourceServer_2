package com.phoenixtype.webservice.api.ResourceServer.controllers;


import com.phoenixtype.webservice.api.ResourceServer.response.UserRest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/status/check")
    public String status(){
        return "working...";
    }

//    @Secured("ROLE_developer")
    @PreAuthorize("#id == #jwt.subject") //hasAuthority('ROLE_developer') or
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return "Deleted user with id " + id + " and JWT subject " + jwt.getSubject();
    }

    @PostAuthorize("returnObject.userId == #jwt.subject")
    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return new UserRest("Samuel", "Akuma", "f0715214-b208-4bdd-a8c6-f23125c4ee2d");
    }
}
