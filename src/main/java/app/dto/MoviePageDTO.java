package app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MoviePageDTO(
        int page,
        List<MovieDTO> results,
        int total_pages
) {
}