package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.BaseballTeam;

@Repository
public class BaseballRepository {
	// DBに接続するためのアノテーション
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<BaseballTeam> BASEBALL_TEAM_ROW_MAPPER
		= (rs, i) -> {
			BaseballTeam baseballTeam = new BaseballTeam();
			baseballTeam.setId(rs.getInt("id"));
			baseballTeam.setLeagueName(rs.getString("league_name"));
			baseballTeam.setTeamName(rs.getString("team_name"));
			baseballTeam.setHeadquarters(rs.getString("headquarters"));
			baseballTeam.setInaguration(rs.getString("inauguration"));
			baseballTeam.setHistory(rs.getString("history"));
			return baseballTeam;
		};
		
		/**
		 * idをもとに検索
		 * @param id
		 * @return 該当のbaseballTeamオブジェクトをリターン
		 */
		public BaseballTeam load(Integer id) {
			// SQL文の作成
			// html上で表示させたい情報は球団名・本拠地・発足・歴史のみだが
			// ResultSetを使っているときは全ての情報をSELECTしなければならない
			String loadSql = "SELECT * "
					+ " FROM teams WHERE id=:id";
			
			// プレースホルダに値を代入
			// 今回は"id"に引数のidをもらう
			SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
			
			// SQLの実行
			// 1つのオブジェクトのみの検索のため、「queryForObject」を使う
			// baseballTeamオブジェクトに検索結果を代入する
			BaseballTeam baseballTeam = template.queryForObject(loadSql, param, BASEBALL_TEAM_ROW_MAPPER);
			
			// baseballTeamオブジェクトにSQLの実行結果をリターンする
			// baseballTeamオブジェクトはRowMapperで定義されているもの？
			return baseballTeam;
		}
		
		/**
		 * 全件検索する
		 * @return
		 */
		public List<BaseballTeam> findAll() {
			// SQLの定義
			// ORDER BY　idでidの昇順に表示されるようにする
			String findAllSql = "SELECT * FROM teams ORDER BY id";
			
			// SQlの実行
			// 全検索のため、2つ以上の情報となるので「query」
			// 全件検索でプレースホルダに代入する必要がないため、SqlParameterSourceは必要ない
			List<BaseballTeam> teamList = template.query(findAllSql, BASEBALL_TEAM_ROW_MAPPER);
			
			return teamList;
		}
		
	
}
