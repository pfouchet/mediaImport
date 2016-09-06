package com.groupeseb.mediaimport.apis;

import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/test.xml")
public class DCPTest {

	@Autowired
	private DCP dcp;

//	@Test
	public void fetchTechnique() {
		dcp.getTechnique("TECHNIQUE_106187");
	}

}
