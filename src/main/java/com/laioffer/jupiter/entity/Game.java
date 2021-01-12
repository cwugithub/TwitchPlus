package com.laioffer.jupiter.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
/*
@JsonIgnoreProperties(ignoreUnknown = true) indicates that other fields in the response can be safely ignored.
                                            Without this, you’ll get an exception at runtime.
@JsonInclude(JsonInclude.Include.NON_NULL) indicates that null fields can be skipped and not included.
@JsonDeserialize indicates that Jackson needs to use Game.Builder when constructing a Game object from JSON strings.
*/
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Game.Builder.class)
public class Game {
    // Lesson 4
    /*
    @JsonProperty("name")
    public String name;

    @JsonProperty("developer")
    public String developer;

    @JsonProperty("release_time")
    public String releaseTime;

    @JsonProperty("website")
    public String website;

    @JsonProperty("price")
    public double price;

    public Game(String name, String developer, String releaseTime, String website, double price) {
        this.name = name;
        this.developer = developer;
        this.releaseTime = releaseTime;
        this.website = website;
        this.price = price;
    }
    */
    // Lesson 5
    // change this class to match Twitch response
    // How do we know Twitch response format?
    // In postman, do https://api.twitch.tv/helix/games?name=warcraft%20III
    // we can the response as following:
    // "id": "12924",
    // "name": "Warcraft III",
    // "box_art_url": "https://static-cdn.jtvnw.net/ttv-boxart/Warcraft%20III-{width}x{height}.jpg"
    @JsonProperty("id")
    private final String id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("box_art_url")
    private final String boxArtUrl;

    private Game(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.boxArtUrl = builder.boxArtUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBoxArtUrl() {
        return boxArtUrl;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Builder {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("box_art_url")
        private String boxArtUrl;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder boxArtUrl(String boxArtUrl) {
            this.boxArtUrl = boxArtUrl;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }

}
