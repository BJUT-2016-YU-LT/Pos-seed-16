package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import Domain.VIP;
import control.VIPDBControl;

public class VIPDBControlTest 
{
	
	VIPDBControl vc;
	
	@Before
	public void setUp()
	{
		try {
			vc = new VIPDBControl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchVipNomal() 
	{
		VIP vip = null;
		try {
			vip = vc.searchVip("TEST3000");
			if(vip != null && !vip.getUserID().equals("TEST3000"))
				fail("’“µΩ¥ÌŒÛ∂‘œÛ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testSearchVipNotExist() 
	{
		VIP vip = null;
		try {
		    vip = vc.searchVip("ÃÃÃÃÃÃ");
		    fail("Œ¥≈◊≥ˆ“Ï≥£");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertEquals("Œ¥’“µΩ∏√VIP",e.getMessage());
		}
	}
	
	@Test
	public void testActiveVipStatus() 
	{
		VIP vip = null;
		try {
			vip = vc.searchVip("TEST3000");
			vc.activeVipStatus(vip);
			if(!vc.isLogin(vip))
				fail("º§ªÓ ß∞‹");
			if(vc.activeVipStatus(vip))
				fail("Œ¥ºÏ≤‚≥ˆ“—µ«¬º");
			if(!vc.closeVipStatus(vip))
				fail("πÿ±’ ß∞‹");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				vc.closeVipStatus(vip);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void testCloseVipStatus() {
		VIP vip = null;
		try {
			vip = vc.searchVip("TEST3000");
			if(!vc.activeVipStatus(vip))
				fail("º§ªÓ ß∞‹");
			if(!vc.closeVipStatus(vip))
				fail("πÿ±’ ß∞‹");
			if(vc.isLogin(vip))
				fail("πÿ±’ ß∞‹");
			if(vc.closeVipStatus(vip))
				fail("Œ¥ºÏ≤‚≥ˆ“—πÿ±’");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		} finally {
			try {
				vc.closeVipStatus(vip);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail(e.getMessage());
			}
		}
	}
}