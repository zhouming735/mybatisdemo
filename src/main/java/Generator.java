import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    public static void main(String[] args) throws Exception {
        //MBG生成过程中的告警信息
        List<String> warnings=new ArrayList<String>();
        //当生成代码重复时，覆盖原代码
        boolean overwrite=true;
        //读取MBG配置文件
        InputStream in=Generator.class.getResourceAsStream("/generator/generator-config.xml");
        ConfigurationParser cp=new ConfigurationParser(warnings);
        Configuration config=cp.parseConfiguration(in);

        DefaultShellCallback callback=new DefaultShellCallback(overwrite);
        //创建MBG
        MyBatisGenerator generator=new MyBatisGenerator(config,callback,warnings);
        //执行生成代码
        generator.generate(null);
        //输出告警信息
        for(String warn:warnings){
            System.out.println(warn);
        }
    }
}
