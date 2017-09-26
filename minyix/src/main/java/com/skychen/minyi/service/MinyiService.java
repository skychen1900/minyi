package com.skychen.minyi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skychen.minyi.repository.Minyi;
import com.skychen.minyi.repository.MinyiRepository;

@Service
public class MinyiService {
	
	@Autowired(required=true)
	private MinyiRepository minyiRepo;
	
	public Page<Minyi> getAllMinyi(Pageable pageable) {
        return minyiRepo.findAll(pageable);
    }
	
	public Minyi getMinyi(Integer id){
		return minyiRepo.findOne(id);
	}
	
	public Minyi findOneMinyi(Integer id){
		return minyiRepo.findOneMinyi(id);
	}
	
	public Page<Minyi> findMinyis(Pageable pageable, String keyword) {
        return minyiRepo.findMinyis(pageable, keyword);
    }
	
	public void save(Minyi minyi) {
		minyiRepo.saveAndFlush(minyi);
	}
	
	public Integer count(int id) {
		return minyiRepo.countId(id);
	}
	
	public Integer statusNum(String status){
		return minyiRepo.getStatusNum(status);
	}
	
	public Integer statusDateNum(String status, Timestamp t){
		return minyiRepo.getStatusDateNum(status, t);
	}
}
