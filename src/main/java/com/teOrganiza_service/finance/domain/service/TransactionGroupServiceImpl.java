package com.teOrganiza_service.finance.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.teOrganiza_service.finance.domain.model.TransactionGroup;
import com.teOrganiza_service.finance.domain.model.dto.CreateTransactionGroupDto;
import com.teOrganiza_service.finance.domain.model.dto.TransactionGroupDto;

@Service
public class TransactionGroupServiceImpl implements TransactionGroupService {

	@Override
	public TransactionGroup save(CreateTransactionGroupDto createTransactionGroupDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionGroup update(TransactionGroupDto transactionGroupDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionGroup findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionGroup> findByUserId(UUID userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
