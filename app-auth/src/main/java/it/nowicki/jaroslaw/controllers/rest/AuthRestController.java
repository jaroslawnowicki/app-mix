package it.nowicki.jaroslaw.controllers.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by jarek on 09.11.17.
 */
@RestController
@RequestMapping("/")
public class AuthRestController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}