package com.chetouani.gc.controller;

import com.chetouani.gc.dto.request.ContactRequest;
import com.chetouani.gc.entity.Contact;
import com.chetouani.gc.mapper.ContactMapper;
import com.chetouani.gc.service.ContactService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController()
@RequestMapping(path = "/contact", produces = MediaType.APPLICATION_JSON_VALUE)

public class ContactController {

    private ContactService service;
    private ContactMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> addContact(@Valid @RequestBody ContactRequest contactRequest) {
        Contact contact = this.mapper.map(contactRequest);
        Contact contactAdded = this.service.add(contact);

        return ResponseEntity.ok(contactAdded);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> updateContact(@PathVariable(name = "id") Long id,
                                                 @Valid @RequestBody ContactRequest contactRequest) {
        Contact contact = this.mapper.map(contactRequest);
        Contact contactUpdated = this.service.update(id, contact);

        return ResponseEntity.ok(contactUpdated);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable(name = "id") Long id) {
        this.service.delete(id);
    }
}
