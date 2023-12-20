package com.chetouani.gc.mapper;

import com.chetouani.gc.dto.response.EnterpriseResponse;
import com.chetouani.gc.entity.Enterprise;
import org.springframework.stereotype.Component;

@Component
public class EnterpriseResponseMapper implements MapperInterface<Enterprise, EnterpriseResponse>{

    @Override
    public EnterpriseResponse map(Enterprise input) {
        return new EnterpriseResponse(input.getId(), input.getTvaNumber());
    }
}
