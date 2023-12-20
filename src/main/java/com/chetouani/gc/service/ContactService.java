package com.chetouani.gc.service;

import com.chetouani.gc.entity.Contact; 
import com.chetouani.gc.entity.Enterprise; 
import com.chetouani.gc.exception.EntityNotFoundException; 
import com.chetouani.gc.repository.ContactRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService implements ServiceInterface<Contact> {

    static final String ENTITY_NAME = "Contact";
    private final ContactRepositoryInterface repository;

    @Override
    public Contact add(Contact contact) {
        return this.repository.save(contact);
    }

    @Transactional
    @Override
    public Contact update(Long id, Contact contact) {
        return this.repository
                .findById(id)
                .map(c -> {
                    c.setLastName(contact.getLastName());
                    c.setFirstName(contact.getFirstName());
                    c.setTvaNumber(contact.getTvaNumber());
                    c.setContractType(contact.getContractType());
                    c.setAddress(contact.getAddress());
                    return c;
                }).orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID_MESSAGE, ENTITY_NAME, id)));
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
