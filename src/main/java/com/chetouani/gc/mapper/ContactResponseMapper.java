package com.chetouani.gc.mapper;

import com.chetouani.gc.dto.response.ContactResponse;
import com.chetouani.gc.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactResponseMapper implements MapperInterface<Contact, ContactResponse>{
    @Override
    public ContactResponse map(Contact input) {
        return new ContactResponse(input.getId(), input.getLastName(), input.getFirstName());
    }
}
