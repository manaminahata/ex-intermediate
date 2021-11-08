package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.BaseballTeam;
import com.example.service.BaseballService;

@Controller
@RequestMapping("/teams")
public class BaseballController {
	@Autowired
	private BaseballService baseballService;
	
	@ModelAttribute
	public BaseballForm baseballSetUpForm() {
		return new BaseballForm();
	}
	
	/**
	 * 野球チーム一覧を表示させる
	 * @return
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<BaseballTeam> baseballTeamList = baseballService.showList();
		model.addAttribute("baseballTeamList", baseballTeamList);
		return "team-list";
	}
	
	@RequestMapping("/name")
	public String teamName(Integer id, Model model) {
		// ドメインのインスタンス化
		BaseballTeam baseballTeam = new BaseballTeam();
		
		// ドメインクラスのgetIdを使って該当の野球チームを検索する
		baseballTeam = baseballService.showDetail(id);
		
		// リクエストスコープに格納する
		model.addAttribute("baseballTeam", baseballTeam);
		
		return "name";
	}

}
