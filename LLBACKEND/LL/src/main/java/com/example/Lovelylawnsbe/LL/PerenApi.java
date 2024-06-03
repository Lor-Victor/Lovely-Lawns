package com.example.Lovelylawnsbe.LL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/perenual")
public class PerenApi {

    private final Logger logger = LoggerFactory.getLogger(PerenApi.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlantRep plantRep;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlantInfo(@PathVariable("id") int id) {
        try {
            String apiKey = "sk-2Nhi665bcf08e8e7c5760";
            String apiUrl = "https://perenual.com/api/species/details/" + id + "?key=" + apiKey;
            RestTemplate restTemplate = new RestTemplate();
            String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

            JsonNode root = objectMapper.readTree(jsonResponse);
            String scientificName = root.get("scientific_name").asText();

            Plant plant = new Plant();
            plant.setScientificName(scientificName);
            Plant savedPlant = plantRep.save(plant);

            logger.info("Plant information saved: {}", savedPlant);

            return ResponseEntity.ok().body(root);
        } catch (JsonProcessingException ex) {
            logger.error("Error processing JSON response", ex);
            return ResponseEntity.status(500).body("Error processing JSON response");
        } catch (Exception ex) {
            logger.error("Error fetching and saving plant information", ex);
            return ResponseEntity.status(500).body("Error fetching and saving plant information");
        }
    }
}

