package com.nowcoder.community;

import com.nowcoder.community.DAO.AlphaDAO;
import com.nowcoder.community.config.AlphaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class CommunityApplicationTests implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);

		AlphaDAO alphaDAO = applicationContext.getBean(AlphaDAO.class);
		System.out.println(alphaDAO.select());

		alphaDAO = applicationContext.getBean("alphaHi",AlphaDAO.class);
		System.out.println(alphaDAO.select());

	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired

	@Qualifier("alphaHi")
	private AlphaDAO alphaDAO;

	@Autowired
	private AlphaConfig alphaConfig;

	@Test
	public void testDI(){
		System.out.println(alphaDAO);
		System.out.println(alphaConfig);
	}
}
