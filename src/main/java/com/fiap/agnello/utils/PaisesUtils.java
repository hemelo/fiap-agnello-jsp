package com.fiap.agnello.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
public class PaisesUtils {

    private static Map<String, String> nameToCodeMap;


    public static String getCountryCode(String countryName) {

        if (nameToCodeMap == null) {

            nameToCodeMap = new HashMap<>();

            ObjectMapper objectMapper = new ObjectMapper();

            try (InputStream inputStream = PaisesUtils.class.getResourceAsStream("/static/data/countries.json")) {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> codeToName = mapper.readValue(inputStream, Map.class);

                nameToCodeMap = new HashMap<>();
                for (Map.Entry<String, String> entry : codeToName.entrySet()) {
                    nameToCodeMap.put(entry.getValue().toLowerCase(), entry.getKey());
                }
            } catch (IOException e) {
                log.error("Erro ao carregar o arquivo JSON de países", e);
                throw new RuntimeException("Erro ao carregar o arquivo JSON de países", e);
            }
        }

        return nameToCodeMap.get(countryName.toLowerCase());
    }
}
