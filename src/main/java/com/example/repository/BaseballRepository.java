package com.example.repository;

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
			baseballTeam.setLeagueName(rs.getString("leagueName"));
			baseballTeam.setTeamName(rs.getString("teamName"));
			baseballTeam.setHeadquarters(rs.getString("headquarters"));
			baseballTeam.setInaguration(rs.getString("inaguration"));
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
			String loadSql = "SELECT team_name, headquarters, inaguration, history "
					+ " FROM teams WHERE id=:id";
			
			// プレースホルダに値を代入
			SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
			
			// SQLの実行
			BaseballTeam baseballTeam = template.queryForObject(loadSql, param, BASEBALL_TEAM_ROW_MAPPER);
			
			return baseballTeam;
		}
}
