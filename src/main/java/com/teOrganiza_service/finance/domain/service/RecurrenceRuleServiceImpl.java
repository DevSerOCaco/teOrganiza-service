package com.teOrganiza_service.finance.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.teOrganiza_service.finance.domain.model.RecurrenceRule;
import com.teOrganiza_service.finance.domain.model.dto.CreateRecurrenceRuleDto;
import com.teOrganiza_service.finance.domain.model.dto.RecurrenceRuleDto;

@Service
public class RecurrenceRuleServiceImpl implements RecurrenceRuleService {

	@Override
	public RecurrenceRule save(CreateRecurrenceRuleDto createRecurrenceRuleDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecurrenceRule update(RecurrenceRuleDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecurrenceRule findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecurrenceRule> findByUserId(UUID userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
