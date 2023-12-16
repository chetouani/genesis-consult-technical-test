package com.chetouani.gc.service;

import com.chetouani.gc.entity.Contact;
import com.chetouani.gc.entity.Enterprise;
import com.chetouani.gc.repository.ContactRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactService implements ServiceInterface<Contact> {

    static final String ENTITY_NAME = "Contact";
    private ContactRepositoryInterface repository;

    @Override
    public Contact add(Contact contact) {
        return this.repository.save(contact);
    }

    @Override
    public Contact update(Long id, Contact contact) {
        checkIfEntityExist(repository, ENTITY_NAME, id);

        Contact existingContact = this.repository.findById(id).get();

        existingContact.setLastName(contact.getLastName());
        existingContact.setFirstName(contact.getFirstName());
        existingContact.setTvaNumber(contact.getTvaNumber());
        existingContact.setContractType(contact.getContractType());
        existingContact.setAddress(contact.getAddress());

        return this.repository.save(existingContact);
    }

    public void delete(Long id) {
        checkIfEntityExist(repository, ENTITY_NAME, id);

        this.repository.deleteById(id);
    }

    public Contact getById(Long id) {
        checkIfEntityExist(repository, ENTITY_NAME, id);

        return this.repository.findById(id).get();
    }

    public List<Enterprise> getEnterprises(Long id) {
        checkIfEntityExist(repository, ENTITY_NAME, id);

        return this.repository.findById(id).get().getEnterprises().stream().toList();
    }
}
