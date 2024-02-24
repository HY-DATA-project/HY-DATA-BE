package com.hanyang.dataportal.dataset.dto.req;

import com.hanyang.dataportal.dataset.domain.Organization;
import com.hanyang.dataportal.dataset.domain.Theme;
import com.hanyang.dataportal.dataset.domain.Type;
import com.hanyang.dataportal.dataset.utill.DatasetSort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatasetSearchCond {
    private String keyword;
    private List<Organization> organization;
    private List<Theme> theme;
    private List<Type> type;
    private DatasetSort sort = DatasetSort.최신;
    private int page = 0;


}
