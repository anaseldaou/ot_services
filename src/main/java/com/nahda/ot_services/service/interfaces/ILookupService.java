package com.nahda.ot_services.service.interfaces;

import java.util.List;
import java.util.Map;
import com.nahda.ot_services.dto.LookupDTO;

public interface ILookupService {
    Map<String, List<LookupDTO>> getLookups(List<String> tableNames);
}
