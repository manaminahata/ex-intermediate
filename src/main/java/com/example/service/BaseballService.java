package com.example.service;

import java.util.List;

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
	public BaseballTeam showDetail(Integer id) {
		return baseballRepository.load(id);
	}
	
	/**
	 * 全件検索するメソッドを定義
	 * @return
	 */
	public List<BaseballTeam> showList() {
		return baseballRepository.findAll();
	}
}
