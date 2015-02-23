package com.dayle.contact;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.dayle.contact.service.ContactService;

@ComponentScan
@EnableAutoConfiguration
public class Application {
    public static void main(String... args) throws IOException {
        ApplicationContext appContext = SpringApplication.run(Application.class, args);
        
        ContactService contactService = appContext.getBean(ContactService.class);
        String filePath = (args.length > 0)? args[0] : "etc/contacts.txt";
        contactService.loadContacts(filePath);
        
        contactService
            .searchContacts("", 0, 10)
            .forEach(System.out::println);
        
        SpringApplication.exit(appContext);
    }
}