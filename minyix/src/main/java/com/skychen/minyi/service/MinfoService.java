package com.skychen.minyi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skychen.minyi.repository.Minfo;
import com.skychen.minyi.repository.MinfoRepository;

@Service
public class MinfoService {
	
	private MinfoRepository minfoRepo;
	
	public Minfo findMinfo(){
		return minyiRepo.findLastestMinfo();
	}
	
}
