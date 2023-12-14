package com.chetouani.gc.dto.request;


import jakarta.validation.constraints.NotBlank;

public record EnterpriseRequest(@NotBlank(message = "Field 'tvaNumber' cannot be null or  blank") String tvaNumber,
                                @NotBlank(message = "Field 'country' cannot be null or  blank") String country,
                                @NotBlank(message = "Field 'city' cannot be null or blank") String city,
                                @NotBlank(message = "Field 'postalCode' cannot be null or blank") String postalCode,
                                @NotBlank(message = "Field 'streetName' cannot be null or blank") String streetName,
                                @NotBlank(message = "Field 'streetNumber' cannot be null or blank") String streetNumber) {}
