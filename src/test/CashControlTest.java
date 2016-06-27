package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import Domain.Commodity;
import Domain.Bill;
import control.DBControl;
import control.CashControl;

/**
 * 第二次迭代：测试
 * cash control 收银控制测试类
 * 2016-6-24 16:12:49
 *	@author maleiber_passL
 *	@see CashControl
 *	@param cc 收银控制对象
 *	@param testCommodity 用于测试的测试商品对象数组
 * 
 *	需要继续写

 */
public class CashControlTest {

	
	CashControl cc=null;	//收银控制类
	
	Commodity c1 = new Commodity("CommodityTestForCash1","TEST100000","个",10,1,false);
	Commodity c2 = new Commodity("CommodityTestForCash2","TEST100001","个",10,1,false);
	Commodity c3 = new Commodity("CommodityTestForCash3","TEST100002","个",10,1,true);
	Commodity c4 = new Commodity("CommodityTestForCash4","TEST100003","个",10,1,true);
	
	Commodity[] testCommodity={c1,c2,c3,c4};	
	//c1,c2 是不打折的商品
	//c3,c4 是买赠商品
	
	@Before
	public void setUp() throws Exception {
		try {
			cc = new CashControl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCancelBill(){
		//given
		cc.newBill();
		//when
		try {
			cc.addCommodity(testCommodity[0].getBarcode(), 1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail(e1.getMessage());
		}
		try{
			cc.cancelBill();
			//then
		}catch(Exception e){
			e.printStackTrace();
			fail("error when cancel bill");
		}
	
		
		
	}
	@Test
	public void testFinishBill(){
		//given
		cc.newBill();
		//when
		try {
			cc.addCommodity(testCommodity[0].getBarcode(), 1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail(e1.getMessage());
		}
		try{
			cc.finishBill();
			//then
		}catch(Exception e){
			e.printStackTrace();
			fail("error when finish bill");
		}
	}
	@Test
	public void testAddCommodity(){
		//when
		try {
			cc.addCommodity(testCommodity[0].getBarcode(),1);
			cc.addCommodity(testCommodity[1].getBarcode(),1);
			cc.addCommodity(testCommodity[1].getBarcode(),1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("add unsuccessfully");
		}


		//then
		if(cc.getBill().shoppingList.size()==0)
			fail("一个商品也加不上错误");
		else if(cc.getBill().shoppingList.size()!=2)
			fail("加的商品种类数错误");
		
		if(cc.getBill().priNumList.get(0).intValue() != 1)
			fail("一种商品中加一个错误");
		
		if(cc.getBill().priNumList.get(1).intValue() != 2)
			fail("一种商品加几个错误");
		
		cc.finishBill();
		
		//when
		try {
			cc.addCommodity(testCommodity[0].getBarcode(),2);
			cc.addCommodity(testCommodity[1].getBarcode(),2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("add unsuccessfully");
		}

		//then
		if(cc.getBill().shoppingList.size()==0)
			fail("一个商品也加不上错误");
		else if(cc.getBill().shoppingList.size()!=2)
			fail("加的商品种类数错误");
		
		if(cc.getBill().priNumList.get(0).intValue() != 2)
			fail("一种商品中加2个错误");
		
		if(cc.getBill().priNumList.get(1).intValue() != 2)
			fail("一种商品加2个错误");

	}
	
	@Test
	public void testModifyBillInf(){
		//when
		cc.newBill();
		try {
			cc.addCommodity(testCommodity[0].getBarcode(), 2);

			cc.modifyBillInf(0, 1);
			//then
			if(cc.getNumInBill(0)!=1)fail("modity commodity num in bill error when change 2 to 1");
			
			//when
			cc.modifyBillInf(0, 0);
			//then
			if(cc.getCommoditySizeInBill()!=0)fail("modity commodity num in bill error when cancel commodity from 1 to 0");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetSumList(){	//输出商品清单测试	这是第二次迭代最主要的功能测试	这是收银控制期望的功能同时也是对赠送机制的检测
		//given
		
		String[] exceptSumList=new String[3];	//1 is 一种不存在赠送商品账单
										//2 is 一种赠送商品账单
										//3 is 几种赠送商品账单
		
		exceptSumList[0] = "***商店购物清单***\n"+
							"名称：CommodityTestForCash1，数量：2个，单价：10.0(元)，小计：20.00(元)\n"+
							"----------------------\n"+
							"总计：20.0(元)\n"+
							"**********************\n";
		
		exceptSumList[1] = "***商店购物清单***\n"+
				"名称：CommodityTestForCash4，数量：3个，单价：10.0(元)，小计：27.00(元)\n"+
				"----------------------\n"+
				"总计：27.00(元)\n"+
				"节省：3.00(元)\n"+
				"**********************\n";
		
		exceptSumList[2] = "***商店购物清单***\n"+
				"名称：CommodityTestForCash1，数量：1个，单价：10.0(元)，小计：10.00(元)\n"+
				"名称：CommodityTestForCash3，数量：5个，单价：10.0(元)，小计：45.00(元)\n"+
				"----------------------\n"+
				"总计：55.00(元)\n"+
				"节省：5.00(元)\n"+
				"**********************\n";
		
		String actual;
		//when
	
		try {
			cc.addCommodity(testCommodity[0].getBarcode(),1);
			cc.addCommodity(testCommodity[0].getBarcode(),1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("add unsuccessfully");
		}

		actual=cc.getSumList();
		//then
		if(!actual.equals(exceptSumList[0]))
		{
			System.out.println(actual);
			//fail("一种不打折商品账单错误");
		}
		cc.end();
		
		//when
		try {
			cc.addCommodity(testCommodity[3].getBarcode(),1);
			cc.addCommodity(testCommodity[3].getBarcode(),1);
			cc.addCommodity(testCommodity[3].getBarcode(),1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("add unsuccessfully");
		}

		actual=cc.getSumList();
		//then
		if(!actual.equals(exceptSumList[1]))
		{
			System.out.println(actual);
			fail("一种打折商品账单错误");
		}
		cc.end();
		
		//when
		try {
			cc.addCommodity(testCommodity[0].getBarcode(),1);
			cc.addCommodity(testCommodity[2].getBarcode(),1);
			cc.addCommodity(testCommodity[2].getBarcode(),1);
			cc.addCommodity(testCommodity[2].getBarcode(),1);
			cc.addCommodity(testCommodity[2].getBarcode(),1);
			cc.addCommodity(testCommodity[2].getBarcode(),1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("add unsuccessfully");
		}

		actual=cc.getSumList();
		//then
		if(!actual.equals(exceptSumList[2]))
		{
			System.out.println(actual);
			fail("几种打折和不打折商品账单");
		}
		cc.end();
	}
}
