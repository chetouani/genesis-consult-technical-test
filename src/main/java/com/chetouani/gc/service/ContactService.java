package com.chetouani.gc.service;

import com.chetouani.gc.entity.Contact;
import com.chetouani.gc.repository.ContactRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactService implements ServiceInterface<Contact> {

    private ContactRepositoryInterface repository;

    @Override
    public Contact add(Contact contact) {
        return this.repository.save(contact);
    }

    @Override
    public Contact update(Long id, Contact contact) {
        Contact existingContact = this.repository.findById(id).orElseThrow();

        existingContact.setLastName(contact.getLastName());
        existingContact.setFirstName(contact.getFirstName());
        existingContact.setTvaNumber(contact.getTvaNumber());
        existingContact.setContractType(contact.getContractType());
        existingContact.setAddress(contact.getAddress());

        return this.repository.save(existingContact);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

}
