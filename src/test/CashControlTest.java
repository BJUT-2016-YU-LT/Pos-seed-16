package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Domain.Commodity;
import Domain.VIP;
import control.CashControl;
import control.VIPDBControl;

/**
 * �����ε���������
 * cash control �������Ʋ�����
 * 2016-7- 1:19:49
 *	@author maleiber_passL
 *	@see CashControl
 *	@param cc �������ƶ���
 *	@param CashControl ���ڲ��ԵĲ�����Ʒ�������Ʒ������
 *	@vip1 ��ͨvip
 *	@vip2 ����vip
 * 
 *	��Ҫ����д

 */
public class CashControlTest {
	CashControl cc=null;	//����������
	
	String[] barcode = {"TEST300000","TEST300001","TEST300002"};
	String[] user_ID = {"TEST3000","TEST3001"};
	//Commodity c1,c2,c3;
	VIP vip1,vip2; 
	
	@Before
	public void setUp() throws Exception {
		try {
			cc = new CashControl();
			VIPDBControl vc = new VIPDBControl();
			vip1 = vc.searchVip(user_ID[0]);
			vip2 = vc.searchVip(user_ID[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testIsBillRight1()
	{
		//given
		cc.newBill();
		String result = "***�̵깺���嵥***\n"+
						"VipID:TEST3000 Vip����:50\n"+
						"----------------------\n"+
						"----------------------\n"+
						"���ƣ�test1��������1�������ۣ�10.0(Ԫ)��С�ƣ�10.00(Ԫ)\n"+
						"----------------------\n"+
						"�ܼƣ�10.00(Ԫ)\n"+
						"**********************\n"; 
		//when
		try {
			cc.activeVIP(vip1);
			cc.addCommodity(barcode[0], 1);
			assertEquals(result,cc.getBill().toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail(e1.getMessage());
		} finally {
			try {
				cc.closeVIP(vip1);
				cc.end();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsBillRight2()
	{
		//given
		cc.newBill();
		String result = "***�̵깺���嵥***\n"+
						"VipID:TEST3000 Vip����:50\n"+
						"----------------------\n"+
						"----------------------\n"+
						"���ƣ�test2��������1�������ۣ�10.0(Ԫ)��С�ƣ�9.00(Ԫ)\n"+
						"----------------------\n"+
						"�ܼƣ�9.00(Ԫ)\n"+
						"��ʡ��1.00(Ԫ)\n"+
						"**********************\n"; 
		//when
		try {
			cc.activeVIP(vip1);
			cc.addCommodity(barcode[1], 1);
			assertEquals(result,cc.getBill().toString());
			//System.out.println(cc.getBill().toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail(e1.getMessage());
		} finally {
			try {
				cc.closeVIP(vip1);
				cc.end();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsBillRight3()
	{
		//given
		cc.newBill();
		String result = "***�̵깺���嵥***\n"+
						"VipID:TEST3000 Vip����:50\n"+
						"----------------------\n"+
						"----------------------\n"+
						"���ƣ�test3��������1�������ۣ�10.0(Ԫ)��С�ƣ�9.00(Ԫ)\n"+
						"----------------------\n"+
						"�ܼƣ�9.00(Ԫ)\n"+
						"��ʡ��1.00(Ԫ)\n"+
						"**********************\n"; 
		//when
		try {
			cc.activeVIP(vip1);
			cc.addCommodity(barcode[2], 1);
			assertEquals(result,cc.getBill().toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail(e1.getMessage());
		} finally {
			try {
				cc.closeVIP(vip1);
				cc.end();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsBillRight4()
	{
		//given
		cc.newBill();
		String result = "***�̵깺���嵥***\n"+
						"VipID:TEST3001 Vip����:50\n"+
						"----------------------\n"+
						"----------------------\n"+
						"���ƣ�test1��������1�������ۣ�10.0(Ԫ)��С�ƣ�10.00(Ԫ)\n"+
						"----------------------\n"+
						"�ܼƣ�10.00(Ԫ)\n"+
						"**********************\n"; 
		//when
		try {
			cc.activeVIP(vip2);
			cc.addCommodity(barcode[0], 1);
			assertEquals(result,cc.getBill().toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail(e1.getMessage());
		} finally {
			try {
				cc.closeVIP(vip2);
				cc.end();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsBillRight5()
	{
		//given
		cc.newBill();
		String result = "***�̵깺���嵥***\n"+
						"VipID:TEST3001 Vip����:50\n"+
						"----------------------\n"+
						"----------------------\n"+
						"���ƣ�test2��������1�������ۣ�10.0(Ԫ)��С�ƣ�9.00(Ԫ)\n"+
						"----------------------\n"+
						"�ܼƣ�9.00(Ԫ)\n"+
						"��ʡ��1.00(Ԫ)\n"+
						"**********************\n"; 
		//when
		try {
			cc.activeVIP(vip2);
			cc.addCommodity(barcode[1], 1);
			assertEquals(result,cc.getBill().toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail(e1.getMessage());
		} finally {
			try {
				cc.closeVIP(vip2);
				cc.end();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsBillRight6()
	{
		//given
		cc.newBill();
		String result = "***�̵깺���嵥***\n"+
						"VipID:TEST3001 Vip����:50\n"+
						"----------------------\n"+
						"----------------------\n"+
						"���ƣ�test3��������1�������ۣ�10.0(Ԫ)��С�ƣ�8.10(Ԫ)\n"+
						"----------------------\n"+
						"�ܼƣ�8.10(Ԫ)\n"+
						"��ʡ��1.90(Ԫ)\n"+
						"**********************\n"; 
		//when
		try {
			cc.activeVIP(vip2);
			cc.addCommodity(barcode[2], 1);
			assertEquals(result,cc.getBill().toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail(e1.getMessage());
		} finally {
			try {
				cc.closeVIP(vip2);
				cc.end();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail(e.getMessage());
			}
		}
	}
	
	public static int d(String s1,String s2)
	{
		for(int i = 0;i < s1.length();i++)
		{
			if(s1.charAt(i) != s2.charAt(i))
			{
				return i;
			}
		}
		return -1;
	}
}
