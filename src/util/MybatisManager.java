package util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisManager {
	
	private final String XML_CONFIG_PATH = "config/config.xml";
	private static SqlSessionFactory factory;
	
	private MybatisManager(){
		init();
	}
    
    private static class MybatisManagerHelper{
        private static final MybatisManager INSTANCE = new MybatisManager();
    }
    
    public static MybatisManager getInstance(){
        return MybatisManagerHelper.INSTANCE;
    }
    
    private void init(){
    	try {
			Reader r = Resources.getResourceAsReader(XML_CONFIG_PATH);
			factory = new SqlSessionFactoryBuilder().build(r);
			
			if(r != null) {
				r.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public SqlSessionFactory getFactory() {
		return factory;
	}
}
