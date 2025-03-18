package com.nahda.ot_services.service;

import com.nahda.ot_services.dao.LookupDAO;
import com.nahda.ot_services.dto.LookupDTO;
import com.nahda.ot_services.mappers.IIndividualMapper;
import com.nahda.ot_services.mappers.ILookupMapper;
import com.nahda.ot_services.repository.interfaces.ILookupRepository;
import com.nahda.ot_services.service.interfaces.ILookupService;
import com.nahda.ot_services.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LookupServiceImpl implements ILookupService {

    @Autowired
    private ILookupRepository lookupRepository;

    @Autowired
    private ILookupMapper lookupMapper;

    @Override
    public Map<String, List<LookupDTO>> getLookups(List<String> tableNames) {
        Map<String, List<LookupDTO>> response = new HashMap<>();

        for (String tableName : tableNames) {
            List<LookupDAO> lookups = lookupRepository.getLookup(tableName);
            response.put(StringUtils.convertToCamelCase(tableName), lookupMapper.fromListLookupDAOToListLookupDTO(lookups));
        }
        return response;
    }
}
