package control;

import static org.junit.Assert.*;

import java.util.Random;

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
		String[] userName={1,2,3,4,5,6,7,8};	//1,2,3,4 is normal user , 5,6,7,8 is abnormal
		String[] password={1,2,3,4,5,6,7,8};
		Random ran=new Random();
		int i=0;
		int id=0;
		for(i=0;i<200;i++)
		{
			try{
				id=sdbc.login(userName[ran.nextInt()%8], password[ran.nextInt()%8]);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		fail("Not yet implemented");
	}

	@Test
	public void testLogout() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisconnect() {
		fail("Not yet implemented");
	}
	

}
