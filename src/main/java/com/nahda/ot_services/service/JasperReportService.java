package com.nahda.ot_services.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

@Service
public class JasperReportService {

    public JasperPrint generateReport(String templatePath, Map<String, Object> parameters) throws JRException {
        try {
            // Load and compile the Jasper template
            InputStream reportStream = getClass().getClassLoader().getResourceAsStream(templatePath);
            if (reportStream == null) {
                throw new FileNotFoundException("Jasper report file not found: " + templatePath);
            }
//            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);

            // Fill the report with data (no database connection required)
            return JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            // Fill the report with data (empty datasource for now)
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,new JREmptyDataSource());
//            return jasperPrint;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] exportReportToPdf(JasperPrint jasperPrint) throws JRException {
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}