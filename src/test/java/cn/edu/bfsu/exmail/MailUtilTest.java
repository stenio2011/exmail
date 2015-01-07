package cn.edu.bfsu.exmail;

import org.junit.Test;

public class MailUtilTest {

	
	
	@Test
	public void testGetUnreadCount() {
		MailUtil.getUnreadCount("hexin@bfsu.edu.cn");
	}

	@Test
	public void testGetSSOUrl() {
		MailUtil.getSSOUrl("hexin@bfsu.edu.cn");
	}

	@Test
	public void testValidate() {
		MailUtil.validate("xxx", "xxx");
	}

}
