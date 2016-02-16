package com.groupeseb.mediaimport.apis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(value = "/test.xml")
public class DCPTest {

//	@Autowired
	private DCP dcp;

//	@Test
	public void fetchTechnique() {
		dcp.getTechnique("TECHNIQUE_106187");
	}

}
