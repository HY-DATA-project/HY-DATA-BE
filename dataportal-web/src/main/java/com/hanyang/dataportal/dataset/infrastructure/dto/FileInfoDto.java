package com.hanyang.dataportal.dataset.infrastructure.dto;

import com.hanyang.dataportal.dataset.domain.vo.Type;
import lombok.Data;

@Data
public class FileInfoDto {
    private Type type;
    private String url;
}
