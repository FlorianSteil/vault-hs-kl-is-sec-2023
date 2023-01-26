package com.floriansteil.stundesmoneyapp.database;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudensTable extends CrudRepository<Studens, Long> {
    List<Studens> findAll();

}
