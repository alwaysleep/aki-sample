package com.aki.sample.mapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import com.aki.sample.domain.OnlyForTest;
import com.google.common.io.Files;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlyForTestMapperTest {
	
	@Autowired
	private OnlyForTestMapper onlyForTestMapper;

	@Test
	public void case00() throws IOException {
		OnlyForTest entity = new OnlyForTest();
		entity.setName("Aki");
		entity.setAge(2018);
		entity.setAddress("GZ");
		entity.setSalary(2018.0901);
		File img = ResourceUtils.getFile("classpath:static/system.jpg");
		entity.setImage(Files.toByteArray(img));
		this.onlyForTestMapper.insert(entity);
		List<OnlyForTest> list = this.onlyForTestMapper.query(new OnlyForTest());
		for(OnlyForTest t : list) {
			File file = new File("temp/"+t.getId()+".jpg");
			Files.createParentDirs(file);
			Files.write(t.getImage(), file);
		}
		this.onlyForTestMapper.delete(entity.getId());
	}
}
