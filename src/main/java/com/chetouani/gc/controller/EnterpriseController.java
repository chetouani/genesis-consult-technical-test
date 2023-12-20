package com.chetouani.gc.controller;

import com.chetouani.gc.dto.request.ContactIdRequest;
import com.chetouani.gc.dto.request.EnterpriseRequest;
import com.chetouani.gc.dto.response.ContactResponse;
import com.chetouani.gc.dto.response.EnterpriseResponse;
import com.chetouani.gc.entity.Contact;
import com.chetouani.gc.entity.Enterprise;
import com.chetouani.gc.mapper.ContactResponseMapper;
import com.chetouani.gc.mapper.EnterpriseMapper;
import com.chetouani.gc.mapper.EnterpriseResponseMapper;
import com.chetouani.gc.service.EnterpriseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor 
@RestController
@RequestMapping(path = "/enterprise", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Enterprise management")
public class EnterpriseController {

    private final EnterpriseService service;
    private final EnterpriseMapper enterpriseMapper;
    private final EnterpriseResponseMapper enterpriseResponseMapper;
    private final ContactResponseMapper contactResponseMapper;

    @GetMapping("{id}")
    @Operation(summary = "Get enterprise by id")
    public ResponseEntity<EnterpriseResponse> getEnterpriseById(@PathVariable(name = "id") Long id) {
        Enterprise enterprise = this.service.getById(id);
        EnterpriseResponse enterpriseResponse = this.enterpriseResponseMapper.map(enterprise);

        return ResponseEntity.ok(enterpriseResponse);
    }

    @GetMapping("{id}/contacts")
    @Operation(summary = "Get contact list for a specific enterprise id")
    public ResponseEntity<List<ContactResponse>> getContacts(@PathVariable(name = "id") Long id) {
        List<Contact> contacts = this.service.getContacts(id);
        List<ContactResponse> contactResponses = contacts.stream()
                .map(this.contactResponseMapper::map)
                .toList();

        return ResponseEntity.ok(contactResponses);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add an enterprise")
    public ResponseEntity<Enterprise> addEnterprise(@Valid @RequestBody EnterpriseRequest request) {
        Enterprise enterprise = this.enterpriseMapper.map(request);
        Enterprise enterpriseAdded = this.service.add(enterprise);

        return ResponseEntity.ok(enterpriseAdded);
    }

    @PostMapping(path = "{id}/contact", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add a contact to an enterprise")
    public ResponseEntity<Enterprise> addContactToEnterprise(@PathVariable(name = "id") Long id,
                                                             @Valid @RequestBody ContactIdRequest request) {
        Enterprise enterprise = this.service.addContact(id, request.contactId());

        return ResponseEntity.ok(enterprise);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update an enterprise")
    public ResponseEntity<Enterprise> updateEnterprise(@PathVariable(name = "id") Long id,
                                                       @Valid @RequestBody EnterpriseRequest request) {
        Enterprise enterprise = this.enterpriseMapper.map(request);
        Enterprise enterpriseUpdated = this.service.update(id, enterprise);

        return ResponseEntity.ok(enterpriseUpdated);
    }

}
