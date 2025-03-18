package com.nahda.ot_services.repository.interfaces;

import com.nahda.ot_services.dao.LookupDAO;

import java.util.List;

public interface ILookupRepository {
    List<LookupDAO> getLookup(String tableName);
}
