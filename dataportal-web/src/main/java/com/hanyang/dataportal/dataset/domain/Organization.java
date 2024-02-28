package com.hanyang.dataportal.dataset.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum Organization {
    소프트융합대학,
    공과대학,
    경상대학,
    과학기술융합대학,
    국제문화대학,
    디자인대학,
    약학대학,
    언론정보대학,
    예체능대학,
    입학처;
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Organization findByOrganization(String organization) {
        return Stream.of(Organization.values())
                .filter(o -> o.toString().equals(organization))
                .findFirst()
                .orElse(null);
    }
}
