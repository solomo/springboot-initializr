package cn.solomo.springbootinitializr.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName ExecuteSQLService
 * @Description
 * @Version 1.0.0
 * @create 2022-02-22 17:37
 */
@Slf4j
@Service
public class ExecuteSQLService {

  public void executeSqlByFile(String dbUrl, String dbUserName, String dbPassword,
      String sqlFileName) {
    Connection connection = null;
    try {
      String driverClassName = "com.mysql.cj.jdbc.Driver";
      Class.forName(driverClassName);
      connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
      ScriptRunner runner = new ScriptRunner(connection);
      runner.setAutoCommit(false);
      runner.setStopOnError(true);
      runner.setSendFullScript(false);
      runner.setDelimiter(";");
      runner.setFullLineDelimiter(false);
      runner.setLogWriter(null);
      File file = new File(sqlFileName);
      runner.runScript(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
      connection.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
      } catch (Exception e) {
        if (connection != null) {
          connection = null;
        }
      }
    }
  }
}
