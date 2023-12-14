package com.chetouani.gc.repository;

import com.chetouani.gc.entity.Enterprise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepositoryInterface extends CrudRepository<Enterprise, Long> { }
