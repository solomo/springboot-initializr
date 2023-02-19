package cn.solomo.springbootinitializr.builder;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Service
public class BaseBuilder {

  private static String ENCODING = "UTF-8";
	
	@Autowired
	FreeMarkerConfigurer freeMarkerConfigurer;

  protected Template getTemplate(String ftl) throws IOException {
		return freeMarkerConfigurer.getConfiguration().getTemplate(ftl);
  }

  protected void writeFile(File file, String ftl, Object dataModel) throws IOException, TemplateException {
    if (!file.exists()) {
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      file.createNewFile();
    }
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
    try {
      getTemplate(ftl).process(dataModel, outputStreamWriter);
    } finally {
      outputStreamWriter.flush();
      outputStreamWriter.close();
    }
  }

}
