package com.example.Lovelylawnsbe.LL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//@Controller
//@RequestMapping("/api/perenual")
//public class PerenualApiController {
//
//    private final Logger logger = LoggerFactory.getLogger(PerenualApiController.class);
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private PlantRepository plantRepository;
//
//    @Autowired
//    private PlantService plantService;
//
//    @GetMapping("/getPlantInfoByCommonName/{commonName}")
//    public String getPlantInfoByCommonName(@PathVariable("commonName") String commonName, Model model) {
//        try {
//            int plantId = plantService.findPlantIdByCommonName(commonName);
//
//            if (plantId != -1) {
//                return getPlantInfo(plantId, model);
//            } else {
//                logger.error("Plant ID not found for common name: {}", commonName);
//                return "error";
//            }
//        } catch (Exception ex) {
//            logger.error("Error fetching and saving plant information", ex);
//            return "error";
//        }
//    }
//
//    @GetMapping("/{id}")
//    public String getPlantInfo(@PathVariable("id") int id, Model model) {
//        try {
//            String apiKey = "sk-2Nhi665bcf08e8e7c5760";
//            String apiUrl = "https://perenual.com/api/species/details/" + id + "?key=" + apiKey;
//            RestTemplate restTemplate = new RestTemplate();
//            String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
//
//            JsonNode root = objectMapper.readTree(jsonResponse);
//            String commonName = root.get("common_name").asText();
//            String scientificName = root.path("scientific_name").get(0).asText();
//            String otherNames = root.path("other_name").asText();
//            String sunlight = root.get("sunlight").get(0).asText();
//            String origin = root.path("origin").get(0).asText();
//            String cycle = root.path("cycle").asText();
//            String watering = root.path("watering").asText();
//            String description = root.path("description").asText();
//            String maintenance = root.path("maintenance").asText();
//            String growth_rate = root.path("growth_rate").asText();
//
//            model.addAttribute("commonName", commonName);
//            model.addAttribute("scientificName", scientificName);
//            model.addAttribute("otherNames", otherNames);
//            model.addAttribute("sunlight", sunlight);
//            model.addAttribute("origin", origin);
//            model.addAttribute("cycle", cycle);
//            model.addAttribute("watering", watering);
//            model.addAttribute("description", description);
//            model.addAttribute("maintenance", maintenance);
//            model.addAttribute("growth_rate", growth_rate);
//
//            Plant plant = new Plant();
//            plant.setCommon_name(commonName);
//
//            Plant savedPlant = plantRepository.save(plant);
//
//            logger.info("Plant info saved: {}", savedPlant);
//
//            return "Plantinfo";
//        } catch (Exception ex) {
//            logger.error("Error fetching and saving plant information", ex);
//            return "error";
//        }
//    }
//}






