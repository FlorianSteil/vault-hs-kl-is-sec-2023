package com.floriansteil.stundesmoneyapp.controllers;

import java.util.List;
import com.floriansteil.stundesmoneyapp.database.Studens;

import com.floriansteil.stundesmoneyapp.database.StudensTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudensController {

    @Autowired
    private StudensTable table;

    @GetMapping("/studens")
    public ResponseEntity<List<Studens>> Studens() {

        List<Studens> studens = table.findAll();

        if ( studens == null || studens.size() == 0 ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(studens, HttpStatus.OK);
    }

}
