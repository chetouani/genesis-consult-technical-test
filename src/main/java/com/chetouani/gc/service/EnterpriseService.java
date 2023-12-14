package com.chetouani.gc.service;

import com.chetouani.gc.entity.Enterprise;
import com.chetouani.gc.entity.Contact;
import com.chetouani.gc.repository.EnterpriseRepositoryInterface;
import com.chetouani.gc.repository.ContactRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnterpriseService implements ServiceInterface<Enterprise> {

    private EnterpriseRepositoryInterface enterpriseRepository;
    private ContactRepositoryInterface contactRepository;

    @Override
    public Enterprise add(Enterprise enterprise) {
        return this.enterpriseRepository.save(enterprise);
    }

    @Override
    public Enterprise update(Long id, Enterprise enterprise) {
        Enterprise existingEnterprise = this.enterpriseRepository.findById(id).orElseThrow();

        existingEnterprise.setTvaNumber(enterprise.getTvaNumber());
        existingEnterprise.setAddress(enterprise.getAddress());

        return this.enterpriseRepository.save(existingEnterprise);
    }

    public Enterprise addContact(Long companyId, Long contactId) {
        Enterprise selectedEnterprise = this.enterpriseRepository.findById(companyId).orElseThrow();
        Contact selectedContact = this.contactRepository.findById(contactId).orElseThrow();

        List<Enterprise> enterprises = selectedContact.getEnterprises();
        enterprises.add(selectedEnterprise);
        selectedContact.setEnterprises(enterprises);

        this.contactRepository.save(selectedContact);

        List<Contact> contacts = selectedEnterprise.getContacts();
        contacts.add(selectedContact);
        selectedEnterprise.setContacts(contacts);

        return this.enterpriseRepository.save(selectedEnterprise);
    }
}
