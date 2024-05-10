package com.hanyang.dataportal.qna.controller;

import com.hanyang.dataportal.core.response.ApiResponse;
import com.hanyang.dataportal.qna.domain.Answer;
import com.hanyang.dataportal.qna.dto.req.ReqAnswerDto;
import com.hanyang.dataportal.qna.dto.res.ResAnswerDto;
import com.hanyang.dataportal.qna.dto.res.ResAnswerListDto;
import com.hanyang.dataportal.qna.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Tag(name = "답변 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AnswerController {
    private final AnswerService answerService;

    @Operation(summary = "질문에 대한 답변 생성")
    @PostMapping("/answer")
    public ResponseEntity<ApiResponse<ResAnswerDto>> saveAnswer(@RequestBody ReqAnswerDto reqAnswerDto, Long questionId, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Answer answer = answerService.save(reqAnswerDto, questionId, username);
        return ResponseEntity.ok(ApiResponse.ok(new ResAnswerDto(answer)));
    }

    @Operation(summary = "답변 수정")
    @PutMapping( "/answer/{answerId}")
    public ResponseEntity<ApiResponse<?>> update(@RequestParam ReqAnswerDto reqAnswerDto, @PathVariable Long answerId) {
        Answer answer = answerService.update(reqAnswerDto,answerId);
        return ResponseEntity.ok(ApiResponse.ok(new ResAnswerDto(answer)));
    }

    @Operation(summary = "답변 삭제")
    @DeleteMapping( "/answer/{answerId}" )
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable long answerId) {
        answerService.delete(answerId);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "질문에 대한 답변상세 보기")
    @GetMapping("/answer/{answerId}")
    public ResponseEntity<ApiResponse<?>> getDetailAnswer(@PathVariable Long answerId) {
        Answer answer = answerService.findById(answerId);
        return ResponseEntity.ok(ApiResponse.ok(new ResAnswerDto(answer)));
    }

    @Operation(summary = "질문글 리스트조회")
    @GetMapping("/answers")
    public ResponseEntity<ApiResponse<?>> getTodoAnswerList(@RequestParam int page)  {
        Page<Answer> answers = answerService.getAnswerList(page);
        return ResponseEntity.ok(ApiResponse.ok(new ResAnswerListDto(answers)));
    }
}
