package com.nahda.ot_services.service;

import com.nahda.ot_services.repository.interfaces.IFamilyRepository;
import com.nahda.ot_services.service.interfaces.IFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FamilyServiceImpl implements IFamilyService {

    @Autowired
    IFamilyRepository familyRepository;
    @Override
    public UUID addFamily(UUID uuid) {
        return familyRepository.addFamily(uuid);
    }
}
