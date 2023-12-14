package com.chetouani.gc.repository;

import com.chetouani.gc.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepositoryInterface extends CrudRepository<Contact, Long> { }
