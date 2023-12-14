package com.chetouani.gc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Address implements Serializable {

    @Column(length = 20, nullable = false)
    private String country;
    @Column(length = 50, nullable = false)
    private String city;
    @Column(length = 10, nullable = false)
    private String postalCode;
    @Column(nullable = false)
    private String streetName;
    @Column(length = 10, nullable = false)
    private String streetNumber;
}
