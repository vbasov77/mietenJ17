package mieten17;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;


public class JacksonTest {
    static final String SQL_UPDATE_PUBLISHED =
            "update objects set published = :published where user_id = :userId";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void updatePublished(/*int published, Long userId*/) {
        int published = 1;
        Long userId = 1L;
        var params = new MapSqlParameterSource();
        params.addValue("published", published);
        params.addValue("user_id", userId);
        System.out.println(params);
        jdbcTemplate.update(SQL_UPDATE_PUBLISHED, params);
    }


}
