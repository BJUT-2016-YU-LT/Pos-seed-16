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
 * ��һ�ε���������
 * cash control �������Ʋ�����
 * 2016-6-24 16:12:49
 *	@author maleiber_passL
 *	@see CashControl
 *	@param cc �������ƶ���
 *	@param testCommodity ���ڲ��ԵĲ�����Ʒ��������
 * 
 *	��Ҫ����д
 *	���˵�������Ʒʱ��ʹ�õĲ�������Ʒ����
 *	����Ҫ�Ĳ�����testGetSumList
 *	����CashControl���˵�������˽�еģ�������Ҫ��һ������hasBill���ص�ǰ�Ƿ����˵�
 *
 *	��Ҫ���ƣ�
 *	testCommodity������
 *	testPriSumUp������except
 *	testSumUp��sumExcept��saveExcept
 *	testGetSumList��exceptSumList
 *
 *	CashControl����hasBill��������boolean��ʾ�Ƿ����˵�������
 *	����CashControl����֧��Commodity��Ϊ�������ݵ�addCommodity����
 */
public class CashControlTest {

	
	CashControl cc=null;	//����������
	
	Commodity c1 = new Commodity("CommodityTestForCash1","TEST100000","��",10,1);
	Commodity c2 = new Commodity("CommodityTestForCash2","TEST100001","��",10,1);
	Commodity c3 = new Commodity("CommodityTestForCash3","TEST100002","��",10,(float)0.9);
	Commodity c4 = new Commodity("CommodityTestForCash4","TEST100003","��",10,(float)0.9);
	
	Commodity[] testCommodity={c1,c2,c3,c4};	
	//c1,c2 �ǲ����۵���Ʒ
	//c3,c4 �Ǵ�����Ʒ
	
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
	public void testPriSumUp(){	//��ƷС�����
		
		//given
		//Commodity[] testCommodity={1,2};	//1 is nodiscount commodity ��ɳ�Ա����
											//2 is discount commodity
		float[] except={10,9,30,27};	//������С���������
								//1 is no discount sum up,
								//2 is dis count sum up
								//3 is several no discount sum up
								//4 is several discount sum up
		
		//�Ƚ�����
		try {
			cc.addCommodity(testCommodity[0].getBarcode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("add unsuccessfully");
			e.printStackTrace();
		}	//�����ֳ���Ʒ��
	
		//then �ж���ƷС����ֵ
		if(Math.abs(cc.getBill().priPriceList.get(0)-except[0]) >0.01)
			fail("��������ƷС��������");
		
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
			fail("������ƷС��������");
		
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
			fail("�����������ƷС��������");

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
			fail("���������ƷС��������");
			
		cc.end();
	}
	
	@Test
	public void testSumUp() throws Exception{	//������ƷС����Թ���
		//given
		float[] sumExcept={10,9,50,45,57};	//1  �ǲ�����һ����Ʒ������ܼ۸�,
									//2  �Ǵ���һ����Ʒ����ļ۸�
									//3 �Ǽ���,���� ��������Ʒ����ļ۸�
									//4 �Ǽ��֣�����������Ʒ����ļ۸�
									//5 �Ǽ��֣��������ۺͲ����۶��е���Ʒ�Ľ���۸�
		float[] saveExcept={0,1,0,5,3};	//�Ż��ܼ�������ܼ�����
		
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
			fail("������һ����Ʒ������ܼ۸����");
		if(Math.abs(cc.getBill().discountPrice-saveExcept[0]) > 0.01)
			fail("������һ����Ʒ��������Żݼ۸����");
		
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
			fail("����һ����Ʒ������ܼ۸����");
		if(Math.abs(cc.getBill().discountPrice-saveExcept[1]) > 0.01)
			fail("����һ����Ʒ��������Żݼ۸����");
		
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
			fail("����,���� ��������Ʒ����ļ۸����");
		if(Math.abs(cc.getBill().discountPrice-saveExcept[2]) > 0.01)
			fail("����,���� ��������Ʒ�Żݵļ۸����");
			
		cc.end();
		
		//when 	
		cc.addCommodity(testCommodity[2].getBarcode());
		cc.addCommodity(testCommodity[2].getBarcode());
		cc.addCommodity(testCommodity[3].getBarcode());
		cc.addCommodity(testCommodity[2].getBarcode());
		cc.addCommodity(testCommodity[3].getBarcode());
		
		//then
		if(Math.abs(cc.getBill().sumPrice-sumExcept[3]) > 0.01)
			fail("����,���� ������Ʒ����ļ۸����");
		if(Math.abs(cc.getBill().discountPrice-saveExcept[3]) > 0.01)
			fail("����,���� ������Ʒ�Żݵļ۸����");
		
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
			fail("���֣��������ۺͲ����۶��е���Ʒ�Ľ���۸����");
		if(Math.abs(cc.getBill().discountPrice-saveExcept[4]) > 0.01)
			fail("���֣��������ۺͲ����۶��е���Ʒ���Żݼ۸����");
		
		cc.end();
	}
	
	@Test
	public void testAddCommodity(){	//������Ʒ�Ĳ���
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
			fail("һ����ƷҲ�Ӳ��ϴ���");
		else if(cc.getBill().shoppingList.size()!=2)
			fail("�ӵ���Ʒ����������");
		
		if(cc.getBill().priNumList.get(0).intValue() != 1)
			fail("һ����Ʒ�м�һ������");
		
		if(cc.getBill().priNumList.get(1).intValue() != 2)
			fail("һ����Ʒ�Ӽ�������");
		
	}
	
	@Test
	public void testGetSumList(){	//�����Ʒ�嵥����	���ǵ�һ�ε�������Ҫ�Ĺ��ܲ���	�����������������Ĺ���
		//given
		
		String[] exceptSumList=new String[3];	//1 is һ�ֲ�������Ʒ�˵�
										//2 is һ�ִ�����Ʒ�˵�
										//3 is ���ִ��ۺͲ�������Ʒ�˵�
		
		exceptSumList[0] = "***�̵깺���嵥***\n"+
							"���ƣ�CommodityTestForCash1��������2�������ۣ�10.0(Ԫ)��С�ƣ�20.00(Ԫ)\n"+
							"----------------------\n"+
							"�ܼƣ�20.0(Ԫ)\n"+
							"**********************\n";
		
		exceptSumList[1] = "***�̵깺���嵥***\n"+
				"���ƣ�CommodityTestForCash4��������3�������ۣ�10.0(Ԫ)��С�ƣ�27.00(Ԫ)"+
				"----------------------\n"+
				"�ܼƣ�27.00(Ԫ)\n"+
				"��ʡ��3.00(Ԫ)\n"+
				"**********************\n";
		
		exceptSumList[2] = "***�̵깺���嵥***\n"+
				"���ƣ�CommodityTestForCash1��������1�������ۣ�10.0(Ԫ)��С�ƣ�10.00(Ԫ)\n"+
				"���ƣ�CommodityTestForCash2��������2�������ۣ�10.0(Ԫ)��С�ƣ�20.00(Ԫ)\n"+
				"���ƣ�CommodityTestForCash3��������3�������ۣ�10.0(Ԫ)��С�ƣ�27.00(Ԫ)\n"+
				"----------------------\n"+
				"�ܼƣ�57.00(Ԫ)\n"+
				"��ʡ��3.00(Ԫ)\n"+
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
			//fail("һ�ֲ�������Ʒ�˵�����");
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
			//fail("һ�ִ�����Ʒ�˵�����");
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
			fail("���ִ��ۺͲ�������Ʒ�˵�");
		}
		cc.end();
	}
}
