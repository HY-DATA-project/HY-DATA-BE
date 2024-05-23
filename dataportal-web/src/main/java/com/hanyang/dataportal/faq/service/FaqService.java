package com.hanyang.dataportal.faq.service;


import com.hanyang.dataportal.core.exception.ResourceNotFoundException;
import com.hanyang.dataportal.faq.domain.Faq;
import com.hanyang.dataportal.faq.domain.FaqCategory;
import com.hanyang.dataportal.faq.dto.ReqFaqDto;
import com.hanyang.dataportal.faq.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor

public class FaqService {
    private final FaqRepository faqRepository;
    public Faq create(ReqFaqDto reqFaqDto) {
        Faq faq = reqFaqDto.toEntity();
        return faqRepository.save(faq);
    }
    public Faq update(Long faqId, ReqFaqDto reqFaqDto) {
        Faq faq = faqRepository.findById(faqId).orElseThrow(() -> new ResourceNotFoundException("FAQ 글이 없음"));
        faq.updateFaq(reqFaqDto);
        return faq;

    }
    public Page<Faq> getFaqsByCategory(FaqCategory category) {
        Pageable pageable = PageRequest.of(0, 10);
         return faqRepository.findByFaqCategory(category, pageable);
    }
    public void delete(Long faqId) {
        faqRepository.delete(faqRepository.findById(faqId).orElseThrow(()->new ResourceNotFoundException("삭제할 FAQ가 없습니다.")));
    }
    @Transactional(readOnly = true)
    public Page<Faq> getFaqList(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return faqRepository.findAll(pageable);
    }
}