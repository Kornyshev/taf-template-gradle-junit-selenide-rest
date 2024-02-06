package taf.template.be.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {

    private String name;
    @JsonProperty("private")
    private String isPrivate;
    @JsonProperty("html_url")
    private String htmlUrl;

}
