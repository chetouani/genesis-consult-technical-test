package com.chetouani.gc.mapper;

import com.chetouani.gc.dto.request.EnterpriseRequest;
import com.chetouani.gc.entity.Address;
import com.chetouani.gc.entity.Enterprise;
import org.springframework.stereotype.Component;

@Component
public class EnterpriseMapper implements MapperInterface<EnterpriseRequest, Enterprise>{

    @Override
    public Enterprise map(EnterpriseRequest input) {
        Enterprise enterprise = new Enterprise();
        enterprise.setTvaNumber(input.tvaNumber());

        Address address = new Address();
        address.setCountry(input.country());
        address.setCity(input.city());
        address.setPostalCode(input.postalCode());
        address.setStreetName(input.streetName());
        address.setStreetNumber(input.streetNumber());

        enterprise.setAddress(address);

        return enterprise;
    }
}
