package com.chetouani.gc.dto.request;


import jakarta.validation.constraints.Min;

public record ContactIdRequest(@Min(value = 1, message = "Field 'contactId' should be a valid id") Long contactId) {}
