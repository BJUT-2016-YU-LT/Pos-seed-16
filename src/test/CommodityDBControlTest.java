package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import Domain.Commodity;
import control.CommodityDBControl;

public class CommodityDBControlTest {

	CommodityDBControl cdbc=null;
	String[] CommodityBarcode={"TEST200000","TEST200001","TEST200002","ÃÃÃÃÃÃ"};	//1,2,3 is exist commodity 4 is not exist
																		// 3 is can promotion commodity
	
	String[] CommodityName={"—©±Ã","µÁ≥ÿ","ø…¿÷","∑“¥Ô"};		//1,2 is exist name, 3 is relative name,4 is not exist name
	
	@Before
	public void setUp() throws Exception{
		try {
			cdbc = new CommodityDBControl();
			assertNotEquals(cdbc,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchCommodityByBarcode(){
		Vector<Commodity> result=null;
		String testStatus;
		//when
		testStatus=" when searching 1 normal Commodity by Barcode";
		try {
			result=cdbc.searchCommodityByBarcode(CommodityBarcode[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		//then
		if(result==null)fail("cannot finish search"+testStatus);
		else if(result.size()==0)fail("cannot search this Commodity"+testStatus);
		else if(result.size()!=1)fail("search several Commodity error"+testStatus);
		else if(!result.get(0).getBarcode().equals(CommodityBarcode[0])) {fail("search wrong Commodity"+testStatus);}
		
		result.clear();
		
		//when
		testStatus=" when searching 1 not exist Commodity by Barcode";
		try {
			result=cdbc.searchCommodityByBarcode(CommodityBarcode[3]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		//then
		if(result==null)fail("cannot finish search"+testStatus);
		else if(result.size()!=0)fail("search Commodity"+testStatus);	
		result.clear();
		
		
	}
	
	@Test
	public void testSearchCommodityByName(){
		Vector<Commodity> result=null;
		String testStatus;
		
		//when
		testStatus=" when searching 1 normal Commodity 1 by name";
		try {
			result=cdbc.searchCommodityByName(CommodityName[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		//then
		if(result==null)fail("cannot finish search"+testStatus);
		else if(result.size()==0)fail(" cannot search Commodity"+testStatus);
		result.clear();
		
		//when
		testStatus=" when searching 1 normal Commodity 2 by name";
		try {
			result=cdbc.searchCommodityByName(CommodityName[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		//then
		if(result==null)fail("cannot finish search"+testStatus);
		else if(result.size()==0)fail(" cannot search Commodity"+testStatus);	
		result.clear();
		
		//when
		testStatus=" when searching relative Commodity by name";
		try {
			result=cdbc.searchCommodityByName(CommodityName[2]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		//then
		if(result==null)fail("cannot finish search"+testStatus);
		else if(result.size()==0)fail(" cannot search Commodity"+testStatus);	
		result.clear();
		
		//when 
		testStatus=" when searching not exist Commodity by name";
		try {
			result=cdbc.searchCommodityByName(CommodityName[3]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		//then
		if(result==null)fail("cannot finish search"+testStatus);
		else if(result.size()!=0)fail("searched Commodity"+testStatus);
		result.clear();
	}
	
	@Test
	public void testGetNumInStock(){
		int num=0;
		
		//when
		try {
			num=cdbc.getNumInStock("TEST000000");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		//then
		if(num!=0)fail("get Commodity Stock wrong. it should be 0 ,but not.");
		
		//when
		try {
			num=cdbc.getNumInStock("TEST000001");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		//then
		if(num!=1)fail("get Commodity Stock wrong. it should be 1 ,but not.");
		
		//when
		try {
			num=cdbc.getNumInStock("TEST000002");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		//then
		if(num!=2)fail("get Commodity Stock wrong. it should be 2 ,but not.");
		
		//when
		try {
			num=cdbc.getNumInStock(CommodityBarcode[3]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		if(num!=0)fail("get not exist Commodity Stock wrong. it should be 0 ,but not.");
		//then
	}
	
	@Test
	public void testSetNum(){
		
		int stock_num = 0;
		
		//when
		try {
			stock_num=cdbc.getNumInStock(CommodityBarcode[0]);
			cdbc.setNum(CommodityBarcode[0], 1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
			e1.printStackTrace();
		}
		
	
		//then
		try {
			if(cdbc.getNumInStock(CommodityBarcode[0])!=1)
				fail("set num of"+CommodityBarcode[0]+"wrong, it should be 1");
			cdbc.setNum(CommodityBarcode[0], stock_num);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail(e1.getMessage());
		}
		
		
		
	
		//when
		try{
			cdbc.setNum(CommodityBarcode[3], 1);
		//then	
		}catch (SQLException e){
			fail("DB error");
		}catch (Exception e){
			return;
		}
		fail("set num of not exist commodity error");
		
		
	
	}
	
	@Test
	public void testChangeCommodityNum(){
		int oldStock=0,newStock=0;
		
		//when add num
		try {
			oldStock=cdbc.getNumInStock(CommodityBarcode[0]);

			newStock=oldStock-2;
			cdbc.changeCommodityNum(CommodityBarcode[0], newStock-oldStock);
			//then
			if(cdbc.getNumInStock(CommodityBarcode[0])!=newStock)
				fail("stock add wrong");
		
			//when sub num
			oldStock=cdbc.getNumInStock(CommodityBarcode[1]);
			newStock=oldStock+1;
			cdbc.changeCommodityNum(CommodityBarcode[1], newStock-oldStock);
			//then
			if(cdbc.getNumInStock(CommodityBarcode[1])!=newStock)
				fail("stock sub wrong");
			
			//when change 0
			oldStock=cdbc.getNumInStock(CommodityBarcode[2]);
			cdbc.changeCommodityNum(CommodityBarcode[2], 0);
			//then
			if(cdbc.getNumInStock(CommodityBarcode[2])!=oldStock)
				fail("stock change 0 wrong");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println(CommodityBarcode[0]);
			fail(e1.getMessage());
		}
		//when add exceed num
		try {
			oldStock=cdbc.getNumInStock(CommodityBarcode[0]);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail(e1.getMessage());
		}
		boolean status=false;
		try{
			cdbc.changeCommodityNum(CommodityBarcode[0], -99999);
			//then
		}catch (Exception e){
			status=true;
		}
		if(!status)fail("stock add exceed num wrong");
		
		//when add not exist commodity
		try{
			oldStock=cdbc.getNumInStock(CommodityBarcode[3]);
		}catch(Exception e){
			fail("DB error when add not exist commodity");
		}
		
		status=false;
		try{
			cdbc.changeCommodityNum(CommodityBarcode[3], 1);
		}catch(Exception e){
			if(!e.getMessage().equals("…Ã∆∑¬Î¥ÌŒÛ£°"))
				fail("stock change error when change a not exist commodity but not wrong as …Ã∆∑¬Î¥ÌŒÛ£°");
			status=true;
		}
		if(!status)fail("stock change error when change a not exist commodity but succedded");
		
		
	}
	
	@Test
	public void testGetPromotion(){
		boolean status=false;
		//when
		try {
			status=cdbc.getPromotion(CommodityBarcode[0]);

			//then
			if(status==true)fail("get promotion wrong for not promotion commodity have a promotion");
			
			//when
			status=cdbc.getPromotion(CommodityBarcode[3]);
			//then
			if(status==true)fail("get promotion wrong for not exist commodity have a promotion");
			
			//when
			status=cdbc.getPromotion(CommodityBarcode[2]);
			//then
			if(status==false)fail("get promotion wrong for promotion commodity have no promotion");
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
