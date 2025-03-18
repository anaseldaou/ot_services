package com.nahda.ot_services.controller;

import com.nahda.ot_services.dto.IndividualRequestDTO;
import com.nahda.ot_services.dto.IndividualResponseDTO;
import com.nahda.ot_services.service.interfaces.IIndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/individual")
public class IndividualController {

    @Autowired
    private IIndividualService individualService;

    @PostMapping("add")
    public ResponseEntity<IndividualResponseDTO> addIndividual(@RequestBody IndividualRequestDTO individualDTO) {
        return ResponseEntity.ok(individualService.addIndividual(individualDTO));
    }
}
