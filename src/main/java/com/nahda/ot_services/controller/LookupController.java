package com.nahda.ot_services.controller;

import com.nahda.ot_services.dto.LookupDTO;
import com.nahda.ot_services.service.interfaces.ILookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lookups")
public class LookupController {

    private final ILookupService lookupService;

    public LookupController(ILookupService lookupService){
        this.lookupService = lookupService;
    }

    @GetMapping
    public ResponseEntity<Map<String, List<LookupDTO>>> getLookups(@RequestParam("tableNames") List<String> tableNames) {
        Map<String, List<LookupDTO>> lookups = lookupService.getLookups(tableNames);
        return new ResponseEntity<>(lookups, HttpStatus.OK);
    }

}
