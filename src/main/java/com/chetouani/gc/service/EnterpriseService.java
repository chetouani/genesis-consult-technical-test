package com.chetouani.gc.service;

import com.chetouani.gc.entity.Contact;
import com.chetouani.gc.entity.Enterprise;
import com.chetouani.gc.exception.IntegrityViolationException;
import com.chetouani.gc.repository.ContactRepositoryInterface;
import com.chetouani.gc.repository.EnterpriseRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class EnterpriseService implements ServiceInterface<Enterprise> {

    static final String ENTITY_NAME = "Enterprise";
    private EnterpriseRepositoryInterface enterpriseRepository;
    private ContactRepositoryInterface contactRepository;

    @Override
    public Enterprise add(Enterprise enterprise) {
        return this.enterpriseRepository.save(enterprise);
    }

    @Override
    public Enterprise update(Long id, Enterprise enterprise) {
        checkIfEntityExist(enterpriseRepository, ENTITY_NAME, id);

        Enterprise existingEnterprise = this.enterpriseRepository.findById(id).get();

        existingEnterprise.setTvaNumber(enterprise.getTvaNumber());
        existingEnterprise.setAddress(enterprise.getAddress());

        return this.enterpriseRepository.save(existingEnterprise);
    }

    public Enterprise addContact(Long enterpriseId, Long contactId) {
        checkIfEntityExist(enterpriseRepository, ENTITY_NAME, enterpriseId);
        checkIfEntityExist(contactRepository, ContactService.ENTITY_NAME, contactId);

        Enterprise selectedEnterprise = this.enterpriseRepository.findById(enterpriseId).get();
        Contact selectedContact = this.contactRepository.findById(contactId).get();


        Set<Enterprise> enterprises = selectedContact.getEnterprises();
        Set<Contact> contacts = selectedEnterprise.getContacts();

        if (enterprises.contains(selectedEnterprise)
                || contacts.contains(selectedContact)) {
            throw new IntegrityViolationException("This contact is already part of the enterprise");
        }

        enterprises.add(selectedEnterprise);
        contacts.add(selectedContact);

        return this.enterpriseRepository.save(selectedEnterprise);
    }
}
