package com.nahda.ot_services.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.Map;

@Service
public class PdfService {

    private final TemplateEngine templateEngine;

    // Constructor injection of TemplateEngine
    public PdfService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String parseThymeleafTemplate(String templateName, Map<String, Object> variables) {
        Context context = new Context();
        context.setVariables(variables);
        return this.templateEngine.process(templateName, context); // Pass template name, not file content
    }
}