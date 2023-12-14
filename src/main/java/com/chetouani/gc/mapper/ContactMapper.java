package com.chetouani.gc.mapper;

import com.chetouani.gc.dto.request.ContactRequest;
import com.chetouani.gc.entity.Address;
import com.chetouani.gc.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper implements MapperInterface<ContactRequest, Contact>{

    @Override
    public Contact map(ContactRequest input) {
        Contact contact = new Contact();
        contact.setLastName(input.lastName());
        contact.setFirstName(input.firstName());
        contact.setTvaNumber(input.tvaNumber());
        contact.setContractType(Contact.Type.fromString(input.contractType()));

        Address address = new Address();
        address.setCountry(input.country());
        address.setCity(input.city());
        address.setPostalCode(input.postalCode());
        address.setStreetName(input.streetName());
        address.setStreetNumber(input.streetNumber());

        contact.setAddress(address);

        return contact;
    }
}
