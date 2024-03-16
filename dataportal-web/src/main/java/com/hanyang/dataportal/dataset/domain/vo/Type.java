package com.hanyang.dataportal.dataset.domain.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.hanyang.dataportal.core.exception.NullException;

import java.util.stream.Stream;

public enum Type {
    csv, xls, xlsx, pdf, json, docx;
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Type findByType(String type) {
        return Stream.of(Type.values())
                .filter(o -> o.toString().equals(type))
                .findFirst()
                .orElseThrow(() -> new NullException("해당 파일유형은 존재하지 않습니다"));
    }
}
