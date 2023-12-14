package com.chetouani.gc.controller;

import com.chetouani.gc.dto.request.ContactIdRequest;
import com.chetouani.gc.dto.request.EnterpriseRequest;
import com.chetouani.gc.entity.Enterprise;
import com.chetouani.gc.mapper.EnterpriseMapper;
import com.chetouani.gc.service.EnterpriseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/enterprise", produces = MediaType.APPLICATION_JSON_VALUE)
public class EnterpriseController {

    private EnterpriseService service;
    private EnterpriseMapper enterpriseMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Enterprise> addCompany(@Valid @RequestBody EnterpriseRequest request) {
        Enterprise enterprise = this.enterpriseMapper.map(request);
        Enterprise enterpriseAdded = this.service.add(enterprise);

        return ResponseEntity.ok(enterpriseAdded);
    }

    @PostMapping(path = "{id}/contact", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Enterprise> addContactToCompany(@PathVariable(name = "id") Long id,
                                                          @Valid @RequestBody ContactIdRequest request) {
        Enterprise enterprise = this.service.addContact(id, request.contactId());

        return ResponseEntity.ok(enterprise);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Enterprise> updateCompany(@PathVariable(name = "id") Long id,
                                                    @Valid @RequestBody EnterpriseRequest request) {
        Enterprise enterprise = this.enterpriseMapper.map(request);
        Enterprise enterpriseUpdated = this.service.update(id, enterprise);

        return ResponseEntity.ok(enterpriseUpdated);
    }

}
