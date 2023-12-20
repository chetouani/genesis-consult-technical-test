package com.chetouani.gc.controller;

import com.chetouani.gc.dto.request.ContactRequest;
import com.chetouani.gc.entity.Contact;
import com.chetouani.gc.exception.IntegrityViolationException;
import com.chetouani.gc.mapper.ContactMapper;
import com.chetouani.gc.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/contact", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Contact management")
public class ContactController {

    private final ContactService service;
    private final ContactMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add a contact")
    public ResponseEntity<Contact> addContact(@Valid @RequestBody ContactRequest contactRequest) {
        Contact contact = this.mapper.map(contactRequest);
        if (contact.getContractType().equals(Contact.Type.FREELANCE)
                && (contact.getTvaNumber() == null || contact.getTvaNumber().isBlank())) {
            throw new IntegrityViolationException("A freelance contact shall have a TVA number");
        }

        Contact contactAdded = this.service.add(contact);

        return ResponseEntity.ok(contactAdded);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a contact")
    public ResponseEntity<Contact> updateContact(@PathVariable(name = "id") Long id,
                                                 @Valid @RequestBody ContactRequest contactRequest) {
        Contact contact = this.mapper.map(contactRequest);
        Contact contactUpdated = this.service.update(id, contact);

        return ResponseEntity.ok(contactUpdated);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete a contact")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable(name = "id") Long id) {
        this.service.delete(id);
    }
}
