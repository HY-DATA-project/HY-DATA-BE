package com.hanyang.dataportal.user.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResLoginDto {
    private String grantType;
    private String accessToken;
}
