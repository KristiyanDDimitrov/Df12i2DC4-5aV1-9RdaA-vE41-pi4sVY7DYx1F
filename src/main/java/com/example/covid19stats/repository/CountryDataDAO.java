package com.example.covid19stats.repository;

import com.example.covid19stats.domain.CountryData;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CountryDataDAO {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CountryDataDAO(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public CountryData getCountryByCountryCode(String countryCode) {
        String sql = """
                SELECT id,
                       country_name,
                       country_code,
                       slug,
                       new_confirmed,
                       total_confirmed,
                       new_deaths,
                       total_deaths,
                       new_recovered,
                       total_recovered,
                       `date`
                 FROM  countries
                 WHERE country_code = :countryCode;
                """;

        SqlParameterSource param = new MapSqlParameterSource("countryCode", countryCode);

        CountryData result = namedParameterJdbcTemplate.query(sql, param, new ResultSetExtractor<CountryData>() {

            @Override
            public CountryData extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    String id = rs.getString("id");
                    String countryName = rs.getString("country_name");
                    String countryCode = rs.getString("country_code");
                    String slug = rs.getString("slug");
                    int newConfirmed = rs.getInt("new_confirmed");
                    int totalConfirmed = rs.getInt("total_confirmed");
                    int newDeaths = rs.getInt("new_deaths");
                    int totalDeaths = rs.getInt("total_deaths");
                    int newRecovered = rs.getInt("new_recovered");
                    int totalRecovered = rs.getInt("total_recovered");
                    LocalDateTime dateTime = LocalDateTime.ofInstant(rs.getTimestamp("date").toInstant(), ZoneId.systemDefault());

                    return new CountryData(
                            id,
                            countryName,
                            countryCode,
                            slug,
                            newConfirmed,
                            totalConfirmed,
                            newDeaths,
                            totalDeaths,
                            newRecovered,
                            totalRecovered,
                            dateTime);
                }

                return null;
            }

        });

        return result;
    }

    public void insertCountryData(CountryData countryData)
    {
        String sql = """
                INSERT INTO countries (id, country_name, country_code, slug, new_confirmed, total_confirmed, new_deaths, total_deaths, new_recovered, total_recovered, `date`)
                VALUES                (:id, :countryName, :countryCode, :slug, :newConfirmed, :totalConfirmed, :newDeaths, :totalDeaths, :newRecovered, :totalRecovered, :date)
                """;

        Map<String, Object> params = new HashMap<>();

        params.put("id", countryData.id());
        params.put("countryName", countryData.countryName());
        params.put("countryCode", countryData.countryCode());
        params.put("slug", countryData.slug());
        params.put("newConfirmed", countryData.newConfirmed());
        params.put("totalConfirmed", countryData.totalConfirmed());
        params.put("newDeaths", countryData.newDeaths());
        params.put("totalDeaths", countryData.totalDeaths());
        params.put("newRecovered", countryData.newRecovered());
        params.put("totalRecovered", countryData.totalRecovered());
        params.put("date", countryData.date());

        namedParameterJdbcTemplate.update(sql, params);
    }

    public void clearTable()
    {
        String sql = "DELETE FROM countries";

        namedParameterJdbcTemplate.update(sql, new HashMap<>());
    }
}
