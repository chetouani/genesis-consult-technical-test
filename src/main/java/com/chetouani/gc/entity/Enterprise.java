package com.chetouani.gc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "UC_ENTERPRISE", columnNames = {"tvaNumber"})})
public class Enterprise implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String tvaNumber;

    @Embedded
    private Address address;

    //@JsonManagedReference
    @ManyToMany(mappedBy = "enterprises")
    private List<Contact> contacts = new ArrayList<>();

}
