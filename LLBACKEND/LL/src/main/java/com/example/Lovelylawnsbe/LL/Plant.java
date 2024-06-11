package com.example.Lovelylawnsbe.LL;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String common_name;
    private String scientific_name;
    private String slug;
    private int year;
    private String bibliography;
    private String author;
    private String status;
    private String rank_value; // Renamed to avoid conflict with SQL keyword
    private String family_common_name;
    private int genus_id;
    private String image_url;
    private String genus;
    private String family;

    @ElementCollection
    private List<String> synonyms;

    public Plant() {
    }

    public Plant(String common_name, String scientific_name, String slug, int year, String bibliography, String author, String status, String rank_value, String family_common_name, int genus_id, String image_url, String genus, String family, List<String> synonyms) {
        this.common_name = common_name;
        this.scientific_name = scientific_name;
        this.slug = slug;
        this.year = year;
        this.bibliography = bibliography;
        this.author = author;
        this.status = status;
        this.rank_value = rank_value;
        this.family_common_name = family_common_name;
        this.genus_id = genus_id;
        this.image_url = image_url;
        this.genus = genus;
        this.family = family;
        this.synonyms = synonyms;
    }

    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBibliography() {
        return bibliography;
    }

    public void setBibliography(String bibliography) {
        this.bibliography = bibliography;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRank_value() {
        return rank_value;
    }

    public void setRank_value(String rank_value) {
        this.rank_value = rank_value;
    }

    public String getFamily_common_name() {
        return family_common_name;
    }

    public void setFamily_common_name(String family_common_name) {
        this.family_common_name = family_common_name;
    }

    public int getGenus_id() {
        return genus_id;
    }

    public void setGenus_id(int genus_id) {
        this.genus_id = genus_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }
}







