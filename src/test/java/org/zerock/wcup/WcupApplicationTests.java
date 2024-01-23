package org.zerock.wcup;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.UUID;

@SpringBootTest
@Log4j2
class WcupApplicationTests {


    @Autowired
    DataSource dataSource;

    @Test
    public void testUUID(){

        for (int i = 0; i <10 ; i++) {
            log.info(UUID.randomUUID());
        }

    }

    @Test
    public void testConnection() throws Exception {

        String sql = "select now()";

        try (var con = dataSource.getConnection();
             var pstmt = con.prepareStatement(sql);
             var rs = pstmt.executeQuery()) {

            if (rs.next()) {
                log.info(rs.getString(1));
            }
        }//end tr

    }

    @Test
    void contextLoads() {
    }

}
