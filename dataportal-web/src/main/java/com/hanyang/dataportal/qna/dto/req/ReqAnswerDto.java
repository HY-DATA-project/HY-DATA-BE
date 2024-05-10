package com.hanyang.dataportal.qna.dto.req;

import com.hanyang.dataportal.qna.domain.Answer;
import com.hanyang.dataportal.qna.domain.Question;
import com.hanyang.dataportal.user.domain.User;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter

public class ReqAnswerDto {
    private Long questionId;
    private String title;
    private String content;

    public Answer toEntity(){
        return Answer.builder()
                .title(title)
                .content(content)
                .build();
    }
}