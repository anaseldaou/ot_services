package com.nahda.ot_services.repository;

import com.nahda.ot_services.dao.*;
import com.nahda.ot_services.repository.interfaces.IPatientRepository;
import com.nahda.ot_services.repository.repoMapper.PatientDAORowMapper;
import com.nahda.ot_services.repository.repoMapper.PatientListRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Repository
public class PatientRepositoyImpl implements IPatientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public PatientDAO addPatient(PatientDAO patientDAO) {
        savePatientMainInfo(patientDAO,false);
        savePatientLists(patientDAO, false);
        savePatientMainInfo(patientDAO,true);
        savePatientLists(patientDAO, true);
        return patientDAO;
    }

    private void savePatientLists(PatientDAO patientDAO, boolean history) {
        if(!history) {
            deleteLackOfAcademicAchievement(patientDAO);
        }
        saveLackOfAcademicAchievement(patientDAO,history);
        if(!history) {
            deleteOldSchool(patientDAO);
        }
        saveOldSchool(patientDAO,history);
        if(!history) {
            deleteRepeatedClasses(patientDAO);
        }
        saveRepeatedClasses(patientDAO,history);
        if(!history) {
            deletePhysicalAccident(patientDAO);
        }
        savePhysicalAccident(patientDAO,history);

    }

    private void savePhysicalAccident(PatientDAO patientDAO, boolean history) {
        try {
            String sqlQuery = "INSERT INTO public." + ( history ? "patient_physical_accident_history " : "patient_physical_accident ") +
                    "(patient_uuid, value, note,created_user_uuid,created_user_group_uuid) " +
                    "VALUES (?, ?, ?,?,?)";
            jdbcTemplate.batchUpdate(sqlQuery, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    PhysicalAccidentDAO physicalAccidentDAO = patientDAO.getPhysicalAccident().get(i);
                    ps.setObject(1, patientDAO.getUuid());
                    ps.setString(2, physicalAccidentDAO.getName());
                    ps.setString(3, physicalAccidentDAO.getNote());
                    ps.setObject(4, patientDAO.getCreatedUserUUID() != null ? patientDAO.getCreatedUserUUID() :  patientDAO.getUpdatedUserUuid());
                    ps.setObject(5, patientDAO.getCreatedGroupUUID() != null ? patientDAO.getCreatedGroupUUID() :  patientDAO.getUpdatedGroupUuid());

                }

                @Override
                public int getBatchSize() {
                    return patientDAO.getPhysicalAccident().size();
                }
            });
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private void deletePhysicalAccident(PatientDAO patientDAO) {
        String deleteSql = "DELETE FROM public.patient_physical_accident WHERE patient_uuid = ?";
        jdbcTemplate.update(deleteSql,patientDAO.getUuid());
    }

    private void deleteLackOfAcademicAchievement(PatientDAO patientDAO) {
        // Step 1: Delete subjects first
        String deleteSubjectsSql = """
        DELETE FROM public.patient_neighbors_lack_subject 
        WHERE patient_neighbors_lack_uuid IN (
            SELECT uuid FROM public.patient_neighbors_lack WHERE patient_uuid = ?
        );
    """;
        jdbcTemplate.update(deleteSubjectsSql, patientDAO.getUuid());

        // Step 2: Delete main lack records
        String deleteLackSql = """
        DELETE FROM public.patient_neighbors_lack WHERE patient_uuid = ?;
        """;
        jdbcTemplate.update(deleteLackSql,  patientDAO.getUuid());
    }

    private void deleteOldSchool(PatientDAO patientDAO) {
        String deleteSql = "DELETE FROM public.patient_old_schools WHERE patient_uuid = ?";
        jdbcTemplate.update(deleteSql,patientDAO.getUuid());
    }

    private void saveOldSchool(PatientDAO patientDAO, boolean history) {
        try {
            String sqlQuery = "INSERT INTO public." + ( history ? "patient_old_schools_history " : "patient_old_schools ") +
                    "(patient_uuid, school_uuid, leave_reason,creation_datetime,created_user_uuid,created_user_group_uuid) " +
                    "VALUES (?, ?, ?,?,?,?)";
            jdbcTemplate.batchUpdate(sqlQuery, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    OldSchoolDAO oldSchoolDAO = patientDAO.getOldSchools().get(i);
                    ps.setObject(1, patientDAO.getUuid());
                    ps.setObject(2, oldSchoolDAO.getSchool().getUuid());
                    ps.setString(3, oldSchoolDAO.getLeaveReason());
                    ps.setObject(4, LocalDateTime.now(ZoneId.of("UTC")));
                    ps.setObject(5,patientDAO.getCreatedUserUUID() != null ? patientDAO.getCreatedUserUUID() :  patientDAO.getUpdatedUserUuid());
                    ps.setObject(6,patientDAO.getCreatedGroupUUID() != null ? patientDAO.getCreatedGroupUUID() :  patientDAO.getUpdatedGroupUuid());

                }

                @Override
                public int getBatchSize() {
                    return patientDAO.getOldSchools().size();
                }
            });
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    private void saveLackOfAcademicAchievement(PatientDAO patientDAO, boolean history) {
        String sqlQuery = "INSERT INTO public." + ( history ? "patient_neighbors_lack_history" : "patient_neighbors_lack") +
                " (\"uuid\", relationship_type_uuid, academic_cycle_uuid, patient_uuid, all_subject, creation_datetime,created_user_uuid,created_user_group_uuid) \n" +
                "VALUES(?, ?, ?, ?, ?,?,?,?);";
        jdbcTemplate.batchUpdate(sqlQuery, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                LackOfAcademicAchievementDAO lackOfAcademicAchievementDAO = patientDAO.getLackOfAcademicAchievement().get(i);
                ps.setObject(1, lackOfAcademicAchievementDAO.getUuid());
                ps.setObject(2, lackOfAcademicAchievementDAO.getRelationshipType().getUuid());
                ps.setObject(3, lackOfAcademicAchievementDAO.getAcademicCycle().getUuid());
                ps.setObject(4, patientDAO.getUuid());
                ps.setBoolean(5, false);
                ps.setObject(6, LocalDateTime.now(ZoneId.of("UTC")));
                ps.setObject(7,patientDAO.getCreatedUserUUID() != null ? patientDAO.getCreatedUserUUID() :  patientDAO.getUpdatedUserUuid());
                ps.setObject(8,patientDAO.getCreatedGroupUUID() != null ? patientDAO.getCreatedGroupUUID() :  patientDAO.getUpdatedGroupUuid());
            }

            @Override
            public int getBatchSize() {
                return patientDAO.getLackOfAcademicAchievement().size();
            }
        });
        patientDAO.getLackOfAcademicAchievement().forEach( lack ->{
            String subjectQuery = "INSERT INTO public." + ( history ? "patient_neighbors_lack_subject_history" : "patient_neighbors_lack_subject") +
                    "(patient_neighbors_lack_uuid, subject_uuid,creation_datetime,created_user_uuid,created_user_group_uuid)\n" +
                    "VALUES(?, ?,?,?,?);";
            jdbcTemplate.batchUpdate(subjectQuery, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    LookupDAO subject = lack.getSubject().get(i);
                    ps.setObject(1, lack.getUuid());
                    ps.setObject(2, subject.getUuid());
                    ps.setObject(3, LocalDateTime.now(ZoneId.of("UTC")));
                    ps.setObject(4,patientDAO.getCreatedUserUUID() != null ? patientDAO.getCreatedUserUUID() :  patientDAO.getUpdatedUserUuid());
                    ps.setObject(5,patientDAO.getCreatedGroupUUID() != null ? patientDAO.getCreatedGroupUUID() :  patientDAO.getUpdatedGroupUuid());
                }

                @Override
                public int getBatchSize() {
                    return lack.getSubject().size();
                }
            });
        });

    }

    private void deleteRepeatedClasses(PatientDAO patientDAO) {
        String deleteSql = "DELETE FROM public.patient_failed_classes WHERE patient_uuid = ?";
        jdbcTemplate.update(deleteSql,patientDAO.getUuid());
    }
    private void saveRepeatedClasses(PatientDAO patientDAO, boolean history) {
        try {
            String sqlQuery =
                    "INSERT INTO public." + ( history ? "patient_failed_classes_history " : "patient_failed_classes ") +
                    "(uuid, patient_uuid, class_uuid, repeated_times,creation_datetime,created_user_uuid,created_user_group_uuid) " +
                    "VALUES(?, ?, ?, ?,?,?,?)";
            jdbcTemplate.batchUpdate(sqlQuery, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    RepeatedClassDAO repeatedClassDAO = patientDAO.getRepeatedClasses().get(i);
                    ps.setObject(1, repeatedClassDAO.getUuid());
                    ps.setObject(2, patientDAO.getUuid());
                    ps.setObject(3, repeatedClassDAO.getClasse().getUuid());
                    ps.setInt(4, repeatedClassDAO.getTimes());
                    ps.setObject(5, LocalDateTime.now(ZoneId.of("UTC")));
                    ps.setObject(6,patientDAO.getCreatedUserUUID() != null ? patientDAO.getCreatedUserUUID() :  patientDAO.getUpdatedUserUuid());
                    ps.setObject(7,patientDAO.getCreatedGroupUUID() != null ? patientDAO.getCreatedGroupUUID() :  patientDAO.getUpdatedGroupUuid());

                }

                @Override
                public int getBatchSize() {
                    return patientDAO.getRepeatedClasses().size();
                }
            });
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private int savePatientMainInfo(PatientDAO patient,boolean history) {
        try {
            String sql = "INSERT INTO public." + ( history ? "patients_history" :"patients") +
                    " (uuid, code, individual_uuid,guardian_relation_uuid, class_info, room, " +
                    "general_health_status, early_growth, early_faced_diseases, " +
                    "hearing, sight, motivation_to_school, " +
                    ( history ? "last_update_user_uuid, last_update_group_uuid, last_update_datetime ," : "created_user_uuid, created_group_uuid, creation_date_time,") +
                    "sights_note, hearing_note,early_faced_diseases_note,early_growth_note) " +
                    "VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";

            return jdbcTemplate.update(sql,
                    patient.getUuid(),
                    patient.getCode(),
                    patient.getIndividualUUID() != null ? patient.getIndividualUUID() : null,
                    patient.getGuardianRelationshipUUID(),
                    patient.getClasse() != null ? patient.getClasse().getUuid() : null,
                    patient.getRoom() != null ? patient.getRoom().getUuid() : null,
                    patient.getGeneralHealthStatus() != null ? patient.getGeneralHealthStatus().getUuid() : null,
                    patient.getEarlyGrowth() != null ? patient.getEarlyGrowth().getUuid() : null,
                    patient.getEarlyFacedDiseases() != null ? patient.getEarlyFacedDiseases().getUuid() : null,
                    patient.getHearing() != null ? patient.getHearing().getUuid() : null,
                    patient.getSight() != null ? patient.getSight().getUuid() : null,
                    patient.getMotivationToSchool() != null ? patient.getMotivationToSchool().getUuid() : null,
                    history ? patient.getUpdatedUserUuid() : patient.getCreatedUserUUID(),
                    history ? patient.getUpdatedGroupUuid() : patient.getCreatedGroupUUID(),
                    history ? patient.getLastUpdateDatetime() : patient.getCreationDateTime(),
                    patient.getSight().getNote(),
                    patient.getHearing().getNote(),
                    patient.getEarlyFacedDiseases().getNote(),
                    patient.getEarlyGrowth().getNote()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getPatientCount() {
        try{
            String sqlQuery = "SELECT COUNT(*) FROM patients";
            Optional<Integer> result = Optional.ofNullable(jdbcTemplate.queryForObject(sqlQuery, Integer.class));
            if(result.isPresent()){
                return result.get();
            }
            throw new RuntimeException("Count can't be generated");
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public int updatePatient(PatientDAO patientDAO){
        updatePatientMainInfo(patientDAO);
        savePatientLists(patientDAO,false);
        savePatientMainInfo(patientDAO,true);
        savePatientLists(patientDAO,true);
        return 1;
    }
    public int updatePatientMainInfo(PatientDAO patient) {
        try {
            String sql = """
                    UPDATE public.patients SET 
                    guardian_relation_uuid = ?, 
                    class_info = ?, 
                    room = ?, 
                    general_health_status = ?, 
                    early_growth = ?,
                    early_faced_diseases = ?,
                    hearing = ?, 
                    sight = ?, 
                    motivation_to_school = ?, 
                    last_update_user_uuid = ?, 
                    last_update_group_uuid = ?, 
                    last_update_datetime = ? ,
                    sights_note = ?,
                    hearing_note = ?,
                    early_faced_diseases_note = ?,
                    early_growth_note = ? 
                    WHERE uuid = ?
                    """;
            return jdbcTemplate.update(sql,
                    patient.getGuardianRelationshipUUID(),
                    patient.getClasse() != null ? patient.getClasse().getUuid() : null,
                    patient.getRoom() != null ? patient.getRoom().getUuid() : null,
                    patient.getGeneralHealthStatus() != null ? patient.getGeneralHealthStatus().getUuid() : null,
                    patient.getEarlyGrowth() != null ? patient.getEarlyGrowth().getUuid() : null,
                    patient.getEarlyFacedDiseases() != null ? patient.getEarlyFacedDiseases().getUuid() : null,
                    patient.getHearing() != null ? patient.getHearing().getUuid() : null,
                    patient.getSight() != null ? patient.getSight().getUuid() : null,
                    patient.getMotivationToSchool() != null ? patient.getMotivationToSchool().getUuid() : null,
                    patient.getUpdatedUserUuid(),
                    patient.getUpdatedGroupUuid(),
                    patient.getLastUpdateDatetime(),
                    patient.getSight().getNote(),
                    patient.getHearing().getNote(),
                    patient.getEarlyFacedDiseases().getNote(),
                    patient.getEarlyGrowth().getNote(),
                    patient.getUuid() // WHERE condition
            );
        } catch (Exception e) {
            throw new RuntimeException("Error updating patient with UUID: " + patient.getUuid(), e);
        }
    }

    private PatientDAO getPatientMainInfo(UUID uuid){
        String sqlQuery = "SELECT " +
                "p.\"uuid\" as patient_uuid," +
                "individual_uuid," +
                "created_user_uuid," +
                "created_group_uuid," +
                "creation_date_time," +
                "code, ir.relationship_type_uuid as guardian_relation_type_uuid, " +
                "p.class_info as class_info_uuid," +
                "c.name as class_info_name," +
                "room," +
                "p.general_health_status as general_health_status_uuid," +
                "ghs.name as general_health_status_name," +
                "p.early_growth as early_growth_uuid," +
                "eg.name as early_growth_name," +
                "p.early_faced_diseases as early_faced_diseases_uuid," +
                "efd.name as early_faced_diseases_name," +
                "p.hearing as hearing_uuid," +
                "p.sight as sight_uuid," +
                "h.name as hearing_name," +
                "s.name as sight_name," +
                "sights_note," +
                "hearing_note," +
                "early_faced_diseases_note," +
                "early_growth_note," +
                "p.motivation_to_school as motivation_to_school_uuid, " +
                "mts.name as motivation_to_school_name, " +
                "i.first_name as patient_first_name, " +
                "i.dob as patient_dob, " +
                "i.gender_uuid as gender_uuid, " +
                "g.name as gender_name " +
                "FROM public.patients p " +
                "LEFT JOIN public.individuals i on i.\"uuid\" = p.individual_uuid " +
                "LEFT JOIN public.individual_relationship ir on ir.\"uuid\" = p.guardian_relation_uuid " +
                "LEFT JOIN public.gender g on g.\"uuid\" = i.gender_uuid " +
                "LEFT JOIN public.hearing h on h.\"uuid\" = p.hearing " +
                "LEFT JOIN public.sights s on s.\"uuid\" = p.sight " +
                "LEFT JOIN public.early_faced_diseases efd on efd.\"uuid\" = p.early_faced_diseases " +
                "LEFT JOIN public.motivation_to_school mts on mts.\"uuid\" = p.motivation_to_school " +
                "LEFT JOIN public.general_health_status ghs on ghs.\"uuid\" = p.general_health_status " +
                "LEFT JOIN public.early_growth eg on eg.\"uuid\" = p.early_growth " +
                "LEFT JOIN public.classes c on c.\"uuid\" = p.class_info " +
                "WHERE p.uuid = ?";
        Optional<PatientDAO> queryResult = Optional.ofNullable(jdbcTemplate.queryForObject(sqlQuery, new UUID[]{uuid}, new PatientDAORowMapper()));
        return queryResult.get();
    }
    @Override
    public PatientDAO getPatientByUUID(UUID uuid) {
        try {
            PatientDAO patientDAO = getPatientMainInfo(uuid);
            patientDAO.setOldSchools(getPatientoldSchools(uuid));
            patientDAO.setLackOfAcademicAchievement(getPatientLackOfAchievement(patientDAO));
            patientDAO.setRepeatedClasses(getPatientRepeatedClasses(patientDAO));
            patientDAO.setPhysicalAccident(getPhysicalAccident(patientDAO));
            return patientDAO;
        } catch (Exception e) {
            throw e;
        }
    }

    private List<PhysicalAccidentDAO> getPhysicalAccident(PatientDAO patientDAO) {
        String subjectQuery = """
        SELECT value,note
        FROM public.patient_physical_accident 
        WHERE patient_uuid = ? ;
    """;

        return jdbcTemplate.query(subjectQuery, new Object[]{patientDAO.getUuid()},
                rs -> {
                    List<PhysicalAccidentDAO> physicalAccidentDAOS = new ArrayList<>();
                    while (rs.next()) {
                        PhysicalAccidentDAO physicalAccidentDAO = new PhysicalAccidentDAO();
                        physicalAccidentDAO.setName(rs.getString("value"));
                        physicalAccidentDAO.setNote(rs.getString("note"));
                        physicalAccidentDAOS.add(physicalAccidentDAO);
                    }
                    return physicalAccidentDAOS;
                });
    }

    private List<RepeatedClassDAO> getPatientRepeatedClasses(PatientDAO patientDAO) {
        String subjectQuery = """
        SELECT pfc.uuid, pfc.patient_uuid , pfc.class_uuid, pfc.repeated_times, c.name as className
        FROM public.patient_failed_classes pfc 
        LEFT JOIN public.classes c on c.uuid =  pfc.class_uuid 
        WHERE patient_uuid = ? ;
    """;

        return jdbcTemplate.query(subjectQuery, new Object[]{patientDAO.getUuid()},
                rs -> {
                    List<RepeatedClassDAO> repeatedClassDAOList = new ArrayList<>();
                    while (rs.next()) {
                        RepeatedClassDAO repeatedClassDAO = new RepeatedClassDAO();
                        repeatedClassDAO.setUuid(UUID.fromString(rs.getString("uuid")));
                        repeatedClassDAO.setClasse(new LookupDAO(UUID.fromString(rs.getString("class_uuid")), rs.getString("className")));
                        repeatedClassDAO.setTimes(rs.getInt("repeated_times"));
                        repeatedClassDAOList.add(repeatedClassDAO);
                    }
                    return repeatedClassDAOList;
                });
    }

    private List<LackOfAcademicAchievementDAO> getPatientLackOfAchievement(PatientDAO patientDAO) {
        UUID patientUuid = patientDAO.getUuid();

        // Step 1: Fetch Lack of Achievement records
        List<LackOfAcademicAchievementDAO> lacks = fetchLackOfAchievements(patientUuid);

        if (lacks.isEmpty()) {
            return Collections.emptyList(); // Return empty if no records exist
        }

        // Step 2: Fetch Subjects for Lack Records
        Map<UUID, List<LookupDAO>> subjectsMap = fetchLackSubjects(patientUuid);

        // Step 3: Assign Subjects to Each Lack Record
        lacks.forEach(lack -> lack.setSubject(subjectsMap.getOrDefault(lack.getUuid(), new ArrayList<>())));

        return lacks;
    }

    /**
     * Fetches lack of achievement records for a specific patient.
     */
    private List<LackOfAcademicAchievementDAO> fetchLackOfAchievements(UUID patientUuid) {
        String sqlQuery = """
        SELECT pnl.uuid lack_uuid, pnl.relationship_type_uuid, pnl.academic_cycle_uuid, pnl.patient_uuid, pnl.all_subject, 
        ac.name academic_cycle_name, rt.name relationship_type_name
        FROM public.patient_neighbors_lack pnl 
        LEFT JOIN public.academic_cycle ac on ac.uuid = pnl.academic_cycle_uuid 
        LEFT JOIN public.relationship_type rt on rt.uuid = pnl.relationship_type_uuid 
        WHERE patient_uuid = ?;
    """;

        return jdbcTemplate.query(sqlQuery, new Object[]{patientUuid},
                (rs, rowNum) -> {
                    LackOfAcademicAchievementDAO lack = new LackOfAcademicAchievementDAO();
                    lack.setUuid(UUID.fromString(rs.getString("lack_uuid")));
                    lack.setRelationshipType(new LookupDAO(UUID.fromString(rs.getString("relationship_type_uuid")),rs.getString("relationship_type_name")));
                    lack.setAcademicCycle(new LookupDAO(UUID.fromString(rs.getString("academic_cycle_uuid")),rs.getString("academic_cycle_name")));
//                    lack.set(false);
                    return lack;
                });
    }

    /**
     * Fetches subjects for each lack record and stores them in a map.
     */
    private Map<UUID, List<LookupDAO>> fetchLackSubjects(UUID patientUuid) {
        String subjectQuery = """
        SELECT pnls.patient_neighbors_lack_uuid,pnls.subject_uuid , s.name as subjectName
        FROM public.patient_neighbors_lack_subject pnls 
        LEFT JOIN public.subject s on s.uuid = pnls.subject_uuid 
        WHERE patient_neighbors_lack_uuid IN (
            SELECT uuid FROM public.patient_neighbors_lack WHERE patient_uuid = ?
        );
    """;

        return jdbcTemplate.query(subjectQuery, new Object[]{patientUuid},
                rs -> {
                    Map<UUID, List<LookupDAO>> subjectsMap = new HashMap<>();
                    while (rs.next()) {
                        UUID lackUuid = UUID.fromString(rs.getString("patient_neighbors_lack_uuid"));
                        UUID subjectUuid = UUID.fromString(rs.getString("subject_uuid"));

                        subjectsMap.computeIfAbsent(lackUuid, k -> new ArrayList<>())
                                .add(new LookupDAO(subjectUuid,rs.getString("subjectName")));
                    }
                    return subjectsMap;
                });
    }

    public List<OldSchoolDAO> getPatientoldSchools(UUID patientUuid) {
        String sql = "SELECT pos.school_uuid, pos.leave_reason, s.name school_name FROM public.patient_old_schools pos " +
                "LEFT JOIN public.school s on s.uuid = pos.school_uuid " +
                "WHERE pos.patient_uuid = ?";

        return jdbcTemplate.query(sql, new Object[]{patientUuid}, oldSchoolRowMapper);
    }

    private final RowMapper<OldSchoolDAO> oldSchoolRowMapper = (rs, rowNum) -> {
        OldSchoolDAO oldSchoolDAO = new OldSchoolDAO();
        oldSchoolDAO.setSchool(new LookupDAO((rs.getObject("school_uuid", UUID.class)), rs.getString("school_name")));
        oldSchoolDAO.setLeaveReason(rs.getString("leave_reason"));
        return oldSchoolDAO;
    };

    @Override
    public List<PatientListDAO> getPatientList() {
        try{
            String sqlQuery = "SELECT p.\"uuid\" as patient_uuid,\n" +
                    "creation_date_time,\n" +
                    "code,\n" +
                    "i.father_name as father_name,\n" +
                    "i.last_name  as last_name,\n" +
                    "i.dob as patient_dob,\n" +
                    "rt.\"name\" as guardian_relation_type_name,\n" +
                    "class_info,\n" +
                    "i.first_name as patient_first_name,\n" +
                    "i.dob as patient_dob \n" +
                    "FROM public.patients p \n" +
                    "LEFT JOIN public.individuals i on i.\"uuid\" = p.individual_uuid \n" +
                    "LEFT JOIN public.individual_relationship ir on ir.\"uuid\" = p.guardian_relation_uuid\n" +
                    "Left join public.relationship_type rt on rt.\"uuid\" = ir.relationship_type_uuid ";

            return jdbcTemplate.query(sqlQuery, new PatientListRowMapper());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
