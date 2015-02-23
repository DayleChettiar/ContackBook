package com.dayle.contact.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dayle.contact.domain.Contact;
import com.dayle.contact.service.ContactService;

@RestController
@RequestMapping(value = "/rest/contacts")
public class ContactController extends BaseController {
    @Autowired
    private ContactService contactService;
    
    @RequestMapping(method = GET)
    public List<Contact> searchContacts(
            @RequestParam(defaultValue="") String keyword, 
            @RequestParam(defaultValue="0") int page, 
            @RequestParam(defaultValue="10") int pageSize) {
        return contactService.searchContacts(keyword, page, pageSize);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Contact createContact(@RequestBody @Valid Contact contact) {
        contact.setId(null);
        return contactService.saveContact(contact);
    }
    
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Contact updateContact(@PathVariable String id, @RequestBody @Valid Contact contact) {
        contact.setId(id);
        return contactService.saveContact(contact);
    }
    
    @RequestMapping(method = DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContacts(@RequestParam String[] ids) {
        contactService.deleteContacts(ids);
    }
}
