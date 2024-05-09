package com.hanyang.dataportal.dataset.dto.res;

import com.hanyang.dataportal.dataset.domain.Dataset;
import com.hanyang.dataportal.dataset.domain.vo.Organization;
import com.hanyang.dataportal.dataset.domain.vo.Type;
import lombok.Data;

import java.util.List;

@Data
public class ResDatasetMainDto {

    private List<SimpleDatasetMain> dataset;

    public ResDatasetMainDto(List<Dataset> dataset) {
        this.dataset = dataset.stream().map(SimpleDatasetMain::new).toList();
    }

    @Data
    public static class SimpleDatasetMain{
        private Long datasetId;
        private String title;
        private Type type;
        private Organization organization;

        public SimpleDatasetMain(Dataset dataset) {
            this.datasetId = dataset.getDatasetId();
            this.title = dataset.getTitle();
            if(dataset.getResource() != null){
                this.type = dataset.getResource().getType();
            }
            this.organization = dataset.getOrganization();
        }
    }
}
