package cn.edu.bfsu.exmail;

import org.junit.Test;

public class MailUtilTest {

	
	
	@Test
	public void testGetUnreadCount() {
		System.out.println(MailUtil.getUnreadCount("hexin@bfsu.edu.cn"));
	}

	@Test
	public void testGetSSOUrl() {
		System.out.println(MailUtil.getSSOUrl("hexin@bfsu.edu.cn"));
	}

	@Test
	public void testValidate() {
		System.out.println(new POP3().validate("pop.bfsu.edu.cn", "hexin@bfsu.edu.cn", ""));
	}

}
