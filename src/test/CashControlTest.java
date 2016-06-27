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
 * 第一次迭代：测试
 * cash control 收银控制测试类
 * 2016-6-24 16:12:49
 *	@author maleiber_passL
 *	@see CashControl
 *	@param cc 收银控制对象
 *	@param testCommodity 用于测试的测试商品对象数组
 * 
 *	需要继续写
 *	向账单加入商品时，使用的参数是商品对象
 *	最主要的测试是testGetSumList
 *	由于CashControl的账单对象是私有的，所以需要加一个函数hasBill返回当前是否有账单
 *
 *	需要完善：
 *	testCommodity的内容
 *	testPriSumUp方法的except
 *	testSumUp的sumExcept和saveExcept
 *	testGetSumList的exceptSumList
 *
 *	CashControl增加hasBill方法返回boolean标示是否有账单正处理
 *	可以CashControl增加支持Commodity作为参数传递的addCommodity方法
 */
public class CashControlTest {

	
	CashControl cc=null;	//收银控制类
	
	Commodity c1 = new Commodity("CommodityTestForCash1","TEST100000","个",10,1);
	Commodity c2 = new Commodity("CommodityTestForCash2","TEST100001","个",10,1);
	Commodity c3 = new Commodity("CommodityTestForCash3","TEST100002","个",10,(float)0.9);
	Commodity c4 = new Commodity("CommodityTestForCash4","TEST100003","个",10,(float)0.9);
	
	Commodity[] testCommodity={c1,c2,c3,c4};	
	//c1,c2 是不打折的商品
	//c3,c4 是打折商品
	
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
	public void testNewCashControl()
	{
		try {
			cc = new CashControl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotEquals(cc,null);
	}
	
	@Test
	public void testNewBill() {
		
		try {
			cc.newBill();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("new bill error");
		}
	}
	
	@Test
	public void testEnd(){
		
		if(!cc.hasBill())
		{
			fail("dont build bill");
		}
		
		try {
			cc.end();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("cancel bill error");
		}
		assertNotEquals(cc.isBillEmpty(),false);		
	}
	
	@Test
	public void testPriSumUp(){	//商品小结测试
		
		//given
		//Commodity[] testCommodity={1,2};	//1 is nodiscount commodity 变成成员变量
											//2 is discount commodity
		float[] except={10,9,30,27};	//期望的小结测试数据
								//1 is no discount sum up,
								//2 is dis count sum up
								//3 is several no discount sum up
								//4 is several discount sum up
		
		//先建订单
		try {
			cc.addCommodity(testCommodity[0].getBarcode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("add unsuccessfully");
			e.printStackTrace();
		}	//加入现成商品类
	
		//then 判断商品小结数值
		if(Math.abs(cc.getBill().priPriceList.get(0)-except[0]) >0.01)
			fail("不打折商品小结计算错误");
		
		//when
		try {
			cc.addCommodity(testCommodity[2].getBarcode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("add unsuccessfully");
		}
				
		//then
		if(Math.abs(cc.getBill().priPriceList.get(1)-except[1]) >0.01)
			fail("打扎商品小结计算错误");
		
		//after 2 test del the bill
		cc.end();
				
		//when		
		try {
			cc.addCommodity(testCommodity[0].getBarcode());
			cc.addCommodity(testCommodity[0].getBarcode());
		cc.addCommodity(testCommodity[0].getBarcode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("add unsuccessfully");
		}

		//then
		if(Math.abs(cc.getBill().priPriceList.get(0)-except[2]) >0.01)
			fail("多个不打折商品小结计算错误");

		//when
		try {
			cc.addCommodity(testCommodity[2].getBarcode());
			cc.addCommodity(testCommodity[2].getBarcode());
			cc.addCommodity(testCommodity[2].getBarcode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("add unsuccessfully");
		}

		//then
		if(Math.abs(cc.getBill().priPriceList.get(1)-except[3]) >0.01)
			fail("多个打折商品小结计算错误");
			
		cc.end();
	}
	
	@Test
	public void testSumUp() throws Exception{	//类似商品小结测试过程
		//given
		float[] sumExcept={10,9,50,45,57};	//1  是不打折一个商品结算的总价格,
									//2  是打折一个商品结算的价格
									//3 是几种,几个 不打折商品结算的价格
									//4 是几种，几个打折商品结算的价格
									//5 是几种，几个打折和不打折都有的商品的结算价格
		float[] saveExcept={0,1,0,5,3};	//优惠总价与结算总价类似
		
		//when 
		try {
			cc.addCommodity(testCommodity[0].getBarcode());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail("add unsuccessfully");
		}
		
		//then
		if(Math.abs(cc.getBill().sumPrice-sumExcept[0]) > 0.01)
			fail("不打折一个商品结算的总价格错误");
		if(Math.abs(cc.getBill().discountPrice-saveExcept[0]) > 0.01)
			fail("不打折一个商品结算的总优惠价格错误");
		
		cc.end();
		
		//when 
		try {
			cc.addCommodity(testCommodity[2].getBarcode());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail("add unsuccessfully");
		}

		//then
		if(Math.abs(cc.getBill().sumPrice-sumExcept[1]) > 0.01)
			fail("打折一个商品结算的总价格错误");
		if(Math.abs(cc.getBill().discountPrice-saveExcept[1]) > 0.01)
			fail("打折一个商品结算的总优惠价格错误");
		
		cc.end();
		
		//when 
		
		try {
			cc.addCommodity(testCommodity[0].getBarcode());
			cc.addCommodity(testCommodity[0].getBarcode());
			cc.addCommodity(testCommodity[1].getBarcode());
			cc.addCommodity(testCommodity[0].getBarcode());
			cc.addCommodity(testCommodity[1].getBarcode());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail("add unsuccessfully");
		}

		//then
		if(Math.abs(cc.getBill().sumPrice-sumExcept[2]) > 0.01)
			fail("几种,几个 不打折商品结算的价格错误");
		if(Math.abs(cc.getBill().discountPrice-saveExcept[2]) > 0.01)
			fail("几种,几个 不打折商品优惠的价格错误");
			
		cc.end();
		
		//when 	
		cc.addCommodity(testCommodity[2].getBarcode());
		cc.addCommodity(testCommodity[2].getBarcode());
		cc.addCommodity(testCommodity[3].getBarcode());
		cc.addCommodity(testCommodity[2].getBarcode());
		cc.addCommodity(testCommodity[3].getBarcode());
		
		//then
		if(Math.abs(cc.getBill().sumPrice-sumExcept[3]) > 0.01)
			fail("几种,几个 打折商品结算的价格错误");
		if(Math.abs(cc.getBill().discountPrice-saveExcept[3]) > 0.01)
			fail("几种,几个 打折商品优惠的价格错误");
		
		cc.end();
		
		//when 
		
		try {
			cc.addCommodity(testCommodity[0].getBarcode());
			cc.addCommodity(testCommodity[1].getBarcode());
			cc.addCommodity(testCommodity[1].getBarcode());
			cc.addCommodity(testCommodity[2].getBarcode());
			cc.addCommodity(testCommodity[3].getBarcode());
			cc.addCommodity(testCommodity[3].getBarcode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("add unsuccessfully");
		}

		//then
		if(Math.abs(cc.getBill().sumPrice-sumExcept[4]) > 0.01)
			fail("几种，几个打折和不打折都有的商品的结算价格错误");
		if(Math.abs(cc.getBill().discountPrice-saveExcept[4]) > 0.01)
			fail("几种，几个打折和不打折都有的商品的优惠价格错误");
		
		cc.end();
	}
	
	@Test
	public void testAddCommodity(){	//增加商品的测试
		//when
		try {
			cc.addCommodity(testCommodity[0].getBarcode());
			cc.addCommodity(testCommodity[1].getBarcode());
			cc.addCommodity(testCommodity[1].getBarcode());
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
		
	}
	
	@Test
	public void testGetSumList(){	//输出商品清单测试	这是第一次迭代最主要的功能测试	这是收银控制期望的功能
		//given
		
		String[] exceptSumList=new String[3];	//1 is 一种不打折商品账单
										//2 is 一种打折商品账单
										//3 is 几种打折和不打折商品账单
		
		exceptSumList[0] = "***商店购物清单***\n"+
							"名称：CommodityTestForCash1，数量：2个，单价：10.0(元)，小计：20.00(元)\n"+
							"----------------------\n"+
							"总计：20.0(元)\n"+
							"**********************\n";
		
		exceptSumList[1] = "***商店购物清单***\n"+
				"名称：CommodityTestForCash4，数量：3个，单价：10.0(元)，小计：27.00(元)"+
				"----------------------\n"+
				"总计：27.00(元)\n"+
				"节省：3.00(元)\n"+
				"**********************\n";
		
		exceptSumList[2] = "***商店购物清单***\n"+
				"名称：CommodityTestForCash1，数量：1个，单价：10.0(元)，小计：10.00(元)\n"+
				"名称：CommodityTestForCash2，数量：2个，单价：10.0(元)，小计：20.00(元)\n"+
				"名称：CommodityTestForCash3，数量：3个，单价：10.0(元)，小计：27.00(元)\n"+
				"----------------------\n"+
				"总计：57.00(元)\n"+
				"节省：3.00(元)\n"+
				"**********************\n";
		
		String actual;
		//when
	
		try {
			cc.addCommodity(testCommodity[0].getBarcode());
			cc.addCommodity(testCommodity[0].getBarcode());
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
			cc.addCommodity(testCommodity[3].getBarcode());
			cc.addCommodity(testCommodity[3].getBarcode());
			cc.addCommodity(testCommodity[3].getBarcode());
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
			//fail("一种打折商品账单错误");
		}
		cc.end();
		
		//when
		try {
			cc.addCommodity(testCommodity[0].getBarcode());
			cc.addCommodity(testCommodity[1].getBarcode());
			cc.addCommodity(testCommodity[1].getBarcode());
			cc.addCommodity(testCommodity[2].getBarcode());
			cc.addCommodity(testCommodity[2].getBarcode());
			cc.addCommodity(testCommodity[2].getBarcode());
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
