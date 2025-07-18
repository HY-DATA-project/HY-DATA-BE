package com.hanyang.api.dataset.service;

import com.hanyang.api.core.exception.ResourceNotFoundException;
import com.hanyang.api.dataset.domain.Dataset;
import com.hanyang.api.dataset.dto.DataSearch;
import com.hanyang.api.dataset.dto.req.ReqDataSearchDto;
import com.hanyang.api.dataset.dto.res.ResDatasetDetailDto;
import com.hanyang.api.dataset.repository.DatasetRepository;
import com.hanyang.api.dataset.repository.DatasetSearchRepository;
import com.hanyang.api.user.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DatasetService {
    private final DatasetRepository datasetRepository;
    private final DatasetSearchRepository datasetSearchRepository;
    private final ScrapRepository scrapRepository;


    @Transactional(readOnly = true)
    public Page<Dataset> getDatasetList(ReqDataSearchDto reqDataSearchDto) {

        DataSearch dataSearch = DataSearch.builder().keyword(reqDataSearchDto.getKeyword()).
                organization(reqDataSearchDto.getOrganization()).
                theme(reqDataSearchDto.getTheme()).
                page(reqDataSearchDto.getPage()).
                type(reqDataSearchDto.getType()).
                sort(reqDataSearchDto.getSort()).
                build();
        return datasetSearchRepository.searchDatasetList(dataSearch);

    }

    public ResDatasetDetailDto getDatasetDetail(Long datasetId) {
        Dataset dataset = datasetRepository.findByIdWithResourceAndTheme(datasetId).orElseThrow(() -> new ResourceNotFoundException("해당 데이터셋은 존재하지 않습니다"));
        Long scrapCount = scrapRepository.countByDataset(dataset);
        dataset.updateView();
        return new ResDatasetDetailDto(dataset, scrapCount.intValue());
    }


    @Transactional(readOnly = true)
    public List<String> getByKeyword(String keyword) {
        return datasetRepository.findByTitleContaining(keyword).stream().map(Dataset::getTitle).toList();
    }

    public List<Dataset> getPopular() {
        Pageable pageable = PageRequest.of(0, 6);
        return datasetRepository.findOrderByPopular(pageable);
    }

    public List<Dataset> getNew() {
        Pageable pageable = PageRequest.of(0, 6);
        return datasetRepository.findOrderByDateDesc(pageable);
    }
}
