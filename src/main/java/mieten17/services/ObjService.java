package mieten17.services;

import mieten17.models.Obj;
import mieten17.repositories.ObjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjService {

    static final String SQL_UPDATE_PUBLISHED =
            "update objects set published = :published where user_id = :userId";

    @Autowired
    private ObjRepository objRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Obj obj) {
        objRepository.save(obj);
    }



}
