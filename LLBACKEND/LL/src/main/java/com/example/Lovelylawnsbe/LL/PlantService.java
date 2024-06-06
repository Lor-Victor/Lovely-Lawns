package com.example.Lovelylawnsbe.LL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class PlantService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public int findPlantIdByCommonName(String commonName) {
        String filePath = "perenual_species_list.txt";

        try (InputStream inputStream = new ClassPathResource(filePath).getInputStream()) {
            JsonNode rootNode = objectMapper.readTree(inputStream);
            JsonNode dataNode = rootNode.get("data");

            for (JsonNode plantNode : dataNode) {
                String plantCommonName = plantNode.get("common_name").asText();
                if (plantCommonName.equalsIgnoreCase(commonName)) {
                    return plantNode.get("id").asInt();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}






