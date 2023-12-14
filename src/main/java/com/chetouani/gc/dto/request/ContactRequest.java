package com.chetouani.gc.dto.request;


import jakarta.validation.constraints.NotBlank;
public record ContactRequest(@NotBlank(message = "Field 'lastName' cannot be null or blank") String lastName,
                             @NotBlank(message = "Field 'firstName' cannot be null or blank") String firstName,
                             @NotBlank(message = "Field 'contractType' cannot null or be blank") String contractType,
                             String tvaNumber,
                             @NotBlank(message = "Field 'country' cannot be null or blank") String country,
                             @NotBlank(message = "Field 'city' cannot be null or blank") String city,
                             @NotBlank(message = "Field 'postalCode' cannot null or be blank") String postalCode,
                             @NotBlank(message = "Field 'streetName' cannot null or be blank") String streetName,
                             @NotBlank(message = "Field 'streetNumber' cannot null or be blank") String streetNumber) {}
