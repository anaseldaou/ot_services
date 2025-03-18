package com.nahda.ot_services.utils;

public class StringUtils {
    public static String convertToCamelCase(String input) {
        String[] parts = input.split("_");
        StringBuilder camelCaseString = new StringBuilder(parts[0].toLowerCase()); // Start with the first word in lowercase

        for (int i = 1; i < parts.length; i++) {
            // Capitalize the first letter of each subsequent word and append
            camelCaseString.append(parts[i].substring(0, 1).toUpperCase())
                    .append(parts[i].substring(1).toLowerCase());
        }

        return camelCaseString.toString();
    }
}
