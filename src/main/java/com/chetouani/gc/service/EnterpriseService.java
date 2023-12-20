package com.chetouani.gc.service;

import com.chetouani.gc.entity.Contact;
import com.chetouani.gc.entity.Enterprise;
import com.chetouani.gc.exception.EntityNotFoundException;
import com.chetouani.gc.repository.ContactRepositoryInterface;
import com.chetouani.gc.repository.EnterpriseRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EnterpriseService implements ServiceInterface<Enterprise> {

    static final String ENTITY_NAME = "Enterprise";
    private final EnterpriseRepositoryInterface enterpriseRepository;
    private final ContactRepositoryInterface contactRepository;

    @Override
    public Enterprise add(Enterprise enterprise) {
        return this.enterpriseRepository.save(enterprise);
    }

    @Override
    public Enterprise update(Long id, Enterprise enterprise) {
        return this.enterpriseRepository
                .findById(id)
                .map(e -> {
                    e.setTvaNumber(enterprise.getTvaNumber());
                    e.setAddress(enterprise.getAddress());
                    return e;
                }).orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID_MESSAGE, ENTITY_NAME, id)));
}

    @Transactional
    public Enterprise addContact(Long enterpriseId, Long contactId) {
        checkIfEntityExist(enterpriseRepository, ENTITY_NAME, enterpriseId);
        checkIfEntityExist(contactRepository, ContactService.ENTITY_NAME, contactId);

        Enterprise selectedEnterprise = this.enterpriseRepository.findById(enterpriseId).get();
        Contact selectedContact = this.contactRepository.findById(contactId).get();


        Set<Enterprise> enterprises = selectedContact.getEnterprises();
        Set<Contact> contacts = selectedEnterprise.getContacts();

        enterprises.add(selectedEnterprise);
        contacts.add(selectedContact);

        return selectedEnterprise;
    }

    public Enterprise getById(Long id) {
        checkIfEntityExist(enterpriseRepository, ENTITY_NAME, id);

        return this.enterpriseRepository.findById(id).get();
    }

    public List<Contact> getContacts(Long id) {
        checkIfEntityExist(enterpriseRepository, ENTITY_NAME, id);

        return this.enterpriseRepository.findById(id).get().getContacts().stream().toList();
    }
}
