package com.chetouani.gc.controller;

import com.chetouani.gc.dto.request.ContactRequest;
import com.chetouani.gc.dto.response.ContactResponse;
import com.chetouani.gc.dto.response.EnterpriseResponse;
import com.chetouani.gc.entity.Contact;
import com.chetouani.gc.entity.Enterprise;
import com.chetouani.gc.exception.IntegrityViolationException;
import com.chetouani.gc.mapper.ContactMapper;
import com.chetouani.gc.mapper.ContactResponseMapper;
import com.chetouani.gc.mapper.EnterpriseResponseMapper;
import com.chetouani.gc.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/contact", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Contact management")
public class ContactController {
 
    private final ContactService service;
    private final ContactMapper mapper;
    private final ContactResponseMapper contactResponseMapper;
    private final EnterpriseResponseMapper enterpriseResponseMapper;

    @GetMapping("{id}")
    @Operation(summary = "Get contact by id")
    public ResponseEntity<ContactResponse> getContactById(@PathVariable(name = "id") Long id) {
        Contact contact = this.service.getById(id);
        ContactResponse contactResponse = this.contactResponseMapper.map(contact);

        return ResponseEntity.ok(contactResponse);
    }

    @GetMapping("{id}/enterprises")
    @Operation(summary = "Get enterprises list for a specific contact id")
    public ResponseEntity<List<EnterpriseResponse>> getEnterprises(@PathVariable(name = "id") Long id) {
        List<Enterprise> enterprise = this.service.getEnterprises(id);
        List<EnterpriseResponse> enterpriseResponse = enterprise.stream()
                .map(this.enterpriseResponseMapper::map)
                .collect(Collectors.toList());

        return ResponseEntity.ok(enterpriseResponse);
    } 

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
