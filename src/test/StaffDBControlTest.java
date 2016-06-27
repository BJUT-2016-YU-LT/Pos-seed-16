package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import control.StaffDBControl;

public class StaffDBControlTest {

	StaffDBControl sdbc;
	
	
	@Before
	public void setUp() throws Exception {
		try {
			sdbc=new StaffDBControl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testStaffDBControl() {
		try{
			sdbc=new StaffDBControl();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("new SDBC fail");
		}
		
	}

	@Test
	public void testLogin() {
		String[] userName={"утр╣б║","лллллл"};	//1 is normal user , 2 is abnormal
		String[] password={"123456","404"};
		
		int id=0;
		
			try{
				//when
				id=sdbc.login(userName[1], password[1]);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//then
			if(id!=0)fail("login error when submit a not existing user");
		
			try{
				//when
				id=sdbc.login(userName[0], password[0]);
			}catch(Exception e)
			{
				e.printStackTrace();
				//then
				fail("login error when submit a exist user");
			}
			try {
				sdbc.logout(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail(e.getMessage());
			}
			
		
	}

	@Test
	public void testLogout() {
		String userName="Цфлз";
		String password="123456";
		int id=0;
		//given
		try{
			
			id=sdbc.login(userName, password);
		}catch(Exception e)
		{
			e.printStackTrace();
			fail("login error when test logout");
		}
		//when
		try{
				sdbc.logout(id);
		
		}catch(Exception e)
		{
			e.printStackTrace();
			fail("logout fail when test logout");
		}
	
	}

	@Test
	public void testDisconnect() {
		try{
			sdbc.disconnect();
		}catch (Exception e)
		{
			e.printStackTrace();
			fail("test disconnect error");
		}
	
	}
	

}
