package com.example.Lovelylawnsbe.LL;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/api/perenual")
public class PerenualApiController {

    private final Logger logger = LoggerFactory.getLogger(PerenualApiController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private PlantService plantService;

    @GetMapping("/getPlantInfoByCommonName/{commonName}")
    public String getPlantInfoByCommonName(@PathVariable("commonName") String commonName, Model model) {
        try {
            return getPlantInfoFromTrefle(commonName, model);
        } catch (Exception ex) {
            logger.error("Error fetching and saving plant information", ex);
            return "error";
        }
    }

    public String getPlantInfoFromTrefle(String commonName, Model model) {
        try {
            String apiKey = "IoF526eus0xDhIcm35_0B_-yixmW5xXQdRYosyjuPa4";
            String apiUrl = "https://trefle.io/api/v1/species?token=" + apiKey + "&filter[common_name]=" + commonName;
            RestTemplate restTemplate = new RestTemplate();
            String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode data = root.path("data").get(0);

            String commonNameFromApi = data.path("common_name").asText();
            String scientificName = data.path("scientific_name").asText();
            String slug = data.path("slug").asText();
            int year = data.path("year").asInt();
            String bibliography = data.path("bibliography").asText();
            String author = data.path("author").asText();
            String status = data.path("status").asText();
            String rank = data.path("rank").asText();
            String familyCommonName = data.path("family_common_name").asText();
            int genusId = data.path("genus_id").asInt();
            String imageUrl = data.path("image_url").asText();
            String genus = data.path("genus").asText();
            String family = data.path("family").asText();
            List<String> synonyms = new ArrayList<>();
            data.path("synonyms").forEach(synonym -> synonyms.add(synonym.asText()));

            model.addAttribute("commonName", commonNameFromApi);
            model.addAttribute("scientificName", scientificName);
            model.addAttribute("slug", slug);
            model.addAttribute("year", year);
            model.addAttribute("bibliography", bibliography);
            model.addAttribute("author", author);
            model.addAttribute("status", status);
            model.addAttribute("rank_value", rank);
            model.addAttribute("familyCommonName", familyCommonName);
            model.addAttribute("genusId", genusId);
            model.addAttribute("imageUrl", imageUrl);
            model.addAttribute("genus", genus);
            model.addAttribute("family", family);
            model.addAttribute("synonyms", synonyms);

            Plant plant = new Plant(commonNameFromApi, scientificName, slug, year, bibliography, author, status, rank, familyCommonName, genusId, imageUrl, genus, family, synonyms);

            Plant savedPlant = plantRepository.save(plant);

            logger.info("Plant information saved: {}", savedPlant);

            return "Plantinfo";
        } catch (Exception ex) {
            logger.error("Error fetching and saving plant information", ex);
            return "error";
        }
    }
}






