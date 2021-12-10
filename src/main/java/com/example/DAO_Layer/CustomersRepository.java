package com.example.DAO_Layer;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Repository
public class CustomersRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String productName = read("src/main/resources/data.sql");

    public CustomersRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws IOException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

    }

    public String read(String scriptNameFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(scriptNameFile))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        }
    }

    public List<String> getProductName(String name) throws IOException {
        return namedParameterJdbcTemplate.queryForList(productName,
                new MapSqlParameterSource("name", name), String.class);
    }
}
