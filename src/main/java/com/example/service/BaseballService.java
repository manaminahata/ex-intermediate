package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.BaseballTeam;
import com.example.repository.BaseballRepository;

@Service
public class BaseballService {
	@Autowired
	private BaseballRepository baseballRepository;
	
	/**
	 * id検索するメソッドを定義している
	 * @param id
	 * @return 該当のチームをリターンする
	 */
	public BaseballTeam load(Integer id) {
		return baseballRepository.load(id);
	}
}
