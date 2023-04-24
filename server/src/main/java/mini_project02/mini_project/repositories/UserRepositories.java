package mini_project02.mini_project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import static mini_project02.mini_project.repositories.Queries.*;

@Repository
public class UserRepositories {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer createUser(String username, String dob) {
        return jdbcTemplate.update(SQL_CREATE_USER, username, dob);
    }
    
}
