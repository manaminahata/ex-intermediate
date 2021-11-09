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
	// サービスクラスをオブジェクト化せず使えるようにDIで定義
	// なぜ直接リポジトリと繋がない？
	// 現在のリポジトリクラスはDBから値を受け取ったり追加や更新をするだけだが、
	// 業務処理が追加されたときサービスを使うためサービスクラスを介してコントローラーが処理をしている？
	@Autowired
	private BaseballService baseballService;
	
	/**
	 * team-list.htmlで野球チーム一覧を表示させる
	 * 
	 * URLにidを直書きしてリンク先を識別することができるが
	 * データが増えた際にいちいち追加することは不便なので
	 * 繰り返し処理ができるようにListをリクエストスコープに格納する
	 * 
	 * @return team-list.html
	 */
	@RequestMapping("")
	public String index(Model model) {
		// baseballTeamListに全件検索結果（テーブル全ての情報）を代入
		List<BaseballTeam> baseballTeamList = baseballService.showList();
		
		// リクエストスコープにbaseballTeamListを格納
		model.addAttribute("baseballTeamList", baseballTeamList);
		
		// チーム一覧を表示させるHTMLをリターンする
		return "team-list";
	}
	
	/**
	 * 受け取ったidでチームを識別し、詳細情報を表示するメソッド
	 * @param id
	 * @param model
	 * @return name.htmlの表示
	 */
	@RequestMapping("/name")
	public String teamName(Integer id, Model model) {
		// ドメインクラスのインスタンス化
		BaseballTeam baseballTeam = baseballService.showDetail(id);
		
		// 引数のidを使ってリンク先のデータを識別する
		// リンクを開くことでshowDetailに引数のidが代入される
	
		
		// そのまま値の受け渡しをすることができないため、
		//　リクエストスコープに格納する
		model.addAttribute("baseballTeam", baseballTeam);
		
		// チームの詳細情報を表示させるHTMLをリターンする
		return "name";
	}

}
