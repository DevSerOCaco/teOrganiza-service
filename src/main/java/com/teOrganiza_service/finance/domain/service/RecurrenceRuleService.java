package com.teOrganiza_service.finance.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.teOrganiza_service.finance.domain.model.RecurrenceRule;
import com.teOrganiza_service.finance.domain.model.dto.CreateRecurrenceRuleDto;
import com.teOrganiza_service.finance.domain.model.dto.RecurrenceRuleDto;

@Service
public interface RecurrenceRuleService {

	RecurrenceRule save(CreateRecurrenceRuleDto createRecurrenceRuleDto);
	RecurrenceRule update(RecurrenceRuleDto dto);
	RecurrenceRule findById(UUID id);
	List<RecurrenceRule> findByUserId(UUID userId);
	void delete(UUID id);
}
