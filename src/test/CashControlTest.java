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
 * �ڶ��ε���������
 * cash control �������Ʋ�����
 * 2016-6-24 16:12:49
 *	@author maleiber_passL
 *	@see CashControl
 *	@param cc �������ƶ���
 *	@param testCommodity ���ڲ��ԵĲ�����Ʒ��������
 * 
 *	��Ҫ����д

 */
public class CashControlTest {

	
	CashControl cc=null;	//����������
	
	Commodity c1 = new Commodity("CommodityTestForCash1","TEST100000","��",10,1,false);
	Commodity c2 = new Commodity("CommodityTestForCash2","TEST100001","��",10,1,false);
	Commodity c3 = new Commodity("CommodityTestForCash3","TEST100002","��",10,1,true);
	Commodity c4 = new Commodity("CommodityTestForCash4","TEST100003","��",10,1,true);
	
	Commodity[] testCommodity={c1,c2,c3,c4};	
	//c1,c2 �ǲ����۵���Ʒ
	//c3,c4 ��������Ʒ
	
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
			fail("һ����ƷҲ�Ӳ��ϴ���");
		else if(cc.getBill().shoppingList.size()!=2)
			fail("�ӵ���Ʒ����������");
		
		if(cc.getBill().priNumList.get(0).intValue() != 1)
			fail("һ����Ʒ�м�һ������");
		
		if(cc.getBill().priNumList.get(1).intValue() != 2)
			fail("һ����Ʒ�Ӽ�������");
		
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
			fail("һ����ƷҲ�Ӳ��ϴ���");
		else if(cc.getBill().shoppingList.size()!=2)
			fail("�ӵ���Ʒ����������");
		
		if(cc.getBill().priNumList.get(0).intValue() != 2)
			fail("һ����Ʒ�м�2������");
		
		if(cc.getBill().priNumList.get(1).intValue() != 2)
			fail("һ����Ʒ��2������");

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
	public void testGetSumList(){	//�����Ʒ�嵥����	���ǵڶ��ε�������Ҫ�Ĺ��ܲ���	�����������������Ĺ���ͬʱҲ�Ƕ����ͻ��Ƶļ��
		//given
		
		String[] exceptSumList=new String[3];	//1 is һ�ֲ�����������Ʒ�˵�
										//2 is һ��������Ʒ�˵�
										//3 is ����������Ʒ�˵�
		
		exceptSumList[0] = "***�̵깺���嵥***\n"+
							"���ƣ�CommodityTestForCash1��������2�������ۣ�10.0(Ԫ)��С�ƣ�20.00(Ԫ)\n"+
							"----------------------\n"+
							"�ܼƣ�20.0(Ԫ)\n"+
							"**********************\n";
		
		exceptSumList[1] = "***�̵깺���嵥***\n"+
				"���ƣ�CommodityTestForCash4��������3�������ۣ�10.0(Ԫ)��С�ƣ�27.00(Ԫ)\n"+
				"----------------------\n"+
				"�ܼƣ�27.00(Ԫ)\n"+
				"��ʡ��3.00(Ԫ)\n"+
				"**********************\n";
		
		exceptSumList[2] = "***�̵깺���嵥***\n"+
				"���ƣ�CommodityTestForCash1��������1�������ۣ�10.0(Ԫ)��С�ƣ�10.00(Ԫ)\n"+
				"���ƣ�CommodityTestForCash3��������5�������ۣ�10.0(Ԫ)��С�ƣ�45.00(Ԫ)\n"+
				"----------------------\n"+
				"�ܼƣ�55.00(Ԫ)\n"+
				"��ʡ��5.00(Ԫ)\n"+
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
			//fail("һ�ֲ�������Ʒ�˵�����");
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
			fail("һ�ִ�����Ʒ�˵�����");
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
			fail("���ִ��ۺͲ�������Ʒ�˵�");
		}
		cc.end();
	}
}
