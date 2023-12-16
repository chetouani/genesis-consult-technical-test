package com.chetouani.gc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "UC_CONTACT", columnNames = {"lastName", "firstName", "contractType"})})
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(length = 100, nullable = false)
    private String firstName;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private Type contractType = Type.FREELANCE;

    @Column(length = 20)
    private String tvaNumber;

    @Embedded
    private Address address;

    //@JsonBackReference
    @JsonIgnore
    @ManyToMany
    private Set<Enterprise> enterprises = new HashSet<>();

    public enum Type {
        EMPLOYEE, FREELANCE;

        public static Type fromString(String t) {
            return switch (t.toUpperCase()) {
                case "FREELANCE" -> FREELANCE;
                default -> EMPLOYEE;
            };
        }
    }
}
