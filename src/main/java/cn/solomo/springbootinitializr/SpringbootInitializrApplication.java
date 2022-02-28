package cn.solomo.springbootinitializr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringbootInitializrApplication {

  public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringbootInitializrApplication.class, args);
  }

}
