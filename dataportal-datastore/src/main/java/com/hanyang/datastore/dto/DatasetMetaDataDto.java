package com.hanyang.datastore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DatasetMetaDataDto {
    @JsonProperty("datasetId")
    private String datasetId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
}
