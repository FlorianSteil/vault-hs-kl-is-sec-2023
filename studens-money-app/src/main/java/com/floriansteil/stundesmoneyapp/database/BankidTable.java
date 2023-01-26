package com.floriansteil.stundesmoneyapp.database;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankidTable extends CrudRepository<Bankid, Long> {
    List<Bankid> findAll();

}
