package com.dayle.contact.service;

import org.springframework.stereotype.Component;

@Component
public class ContactService {
    public String ping() {
        return "pong";
    }
}