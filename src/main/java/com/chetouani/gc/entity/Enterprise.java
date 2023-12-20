package com.chetouani.gc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable; 
import java.util.HashSet; 
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
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
    private Set<Contact> contacts = new HashSet<>();

}
