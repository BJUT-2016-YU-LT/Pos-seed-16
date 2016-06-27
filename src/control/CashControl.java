package control;

import java.util.Vector;

import Domain.Bill;
import Domain.Commodity;



public class CashControl extends CommodityDBControl
{
	private Bill b=null;	//账单
	private Bill presentBill=null;	//赠送商品账单
	//DBControl control;	
	//数据库控制类
	public CashControl() throws Exception
	{
		super();
		b = new Bill();
		//control = new DBControl();
	}
	
	public boolean newBill(){
		b=new Bill();
		return b==null?false:true;
	};
	
	public void cancelBill(){
		//cancel the bill 
		for(int i=0;i<nowBill.shoppingList.size();i++)
		{
			//a function can cancel by commodity
			cancelCommodity(i);
		}
		b.clear();
		b=null;
		
	};
	
	public void finishBill(){
		
		b.clear();
		b=null;
	}
	
	private void priSumUp(){	//sum up all the pri sum up list
		nowBill.priSumList.clear();
		int i=0,j=nowBill.shoppingList.size();
		for(i=0;i<j;i++)
		{
			nowBill.priSumList.add(	  nowBill.shoppingList.index(i).getPrice() 
									* nowBill.shoppingList.index(i).getDiscount()
									* nowBill.QuantityList.index(i));
		}
	//	for(Commodity c:nowBill.shoppingList)
	//		nowBill.priSumList.add(c.getPrice() *c.getDiscount());
		
		
	};
	private void priSumUp(Commodity c,Vector<Float> priPriceList,boolean isAdd,int index){	//sum up the pri sum up list as new in bill
		if(isAdd){
			float price = priPriceList.get(index).floatValue();
			priPriceList.remove(index);
			priPriceList.insertElementAt(new Float(price+c.getPrice()*c.getDiscount()), index);
			//sumPrice += c.getPrice()*c.getDiscount();
			//discountPrice += (1-c.getDiscount())*c.getPrice();
			
			
			nowBill.priSumList.add(	  tempCommodity.getPrice() 
								* tempCommodity.getDiscount()
								* tempNum);
		}
		else{
			priPriceList.addElement(new Float(temp.getPrice()*temp.getDiscount()));
			
			//sumPrice += temp.getPrice()*temp.getDiscount();
			//discountPrice += (1-temp.getDiscount())*temp.getPrice();
			//System.out.println(discountPrice);
		}
		
	};
	public void sumUp(	Vector<Commodity> shoppoingList,
						Vector<Integer> priNumList,
						Vector<Float> priPriceList){
		//sum up all money and discount in bill
		float sumPrice=0;
		for(float priSum:priNumList)
		{
			sumPrice+=priSum;
		}
		System.out.println(sumPrice);
		
		float discountPrice=0;
		int i=0;j=nowBill.shoppingList.size();
		for(i=0;i<j;i++)
		{
			discountPrice+=	  shoppingList.index(i).getPrice() 
							* (1- shoppingList.index(i).getDiscount())
							* priNumList.index(i);
		}
		System.out.println(discountPrice);
		b.setSumPrice(sumPrice);
		b.setDiscountPrice(discountPrice);
	};
		
	
	
	//添加商品，返回商品信息
	public String addCommodity(String barcode,int num) throws Exception
	{
		if(num<1)throw new Exception("new commodity num below to 1!");
		
		//获取账单信息
		Vector<Commodity>	shoppoingList	= b.getShoppoingList();
		Vector<Integer> 	priNumList		= b.getPriNumList();
		Vector<Float> 		priPriceList	= b.getPriPriceList();
		float 				sumPrice 		= b.getSumPrice();
		//float				discountPrice	= b.getDiscountPrice();
		
		Commodity temp =searchCommodityByName(barcode);	//数据库中查找相应商品
		
		//if success
		boolean isAdd = false;
		int index = 0;
		for(Commodity c: shoppoingList)
		{
			if(c.getBarcode().equals(temp.getBarcode()))
			{
				//exist
				isAdd = true;
				int num = priNumList.get(index).intValue();
				priNumList.remove(index);
				priNumList.insertElementAt(new Integer(++num), index);
				
				
				
				
				
				break;
			}
			index++;
		}
		if(!isAdd)
		{
			//not exist
			shoppoingList.addElement(temp);
			priNumList.addElement(new Integer(num));
			
			//priSumUp(tempCommodity);

		}
		
		priSumUp(temp,priPriceList,isAdd,index);
		sumUp(shoppoingList,priNumList,priPriceList);
		
		
		
		return temp.toString();
	}
	
	private void cancelCommodity(int index)throws Exception
	{
		Vector<Commodity>	shoppoingList	= b.getShoppoingList();
		Vector<Integer> 	priNumList		= b.getPriNumList();
		Vector<Float> 		priPriceList	= b.getPriPriceList();
		
		if(priNumList.get(index)<0)throw new Exception("此商品现有小结数量小于0");
		
		changeCommodityNum(shoppoingList.get(index).getBarcode(), 0-priNumList.get(index));
		
		shoppoingList.remove(index);
		priNumList.remove(index);
		priPriceList.remove(index);
		
		
		sumUp(shoppoingList,priNumList,priPriceList);
	}
	
	
	public void modifyBillInf(int index,int num)throws Exception{
		//modify commodity number in bill
		Vector<Commodity>	shoppoingList	= b.getShoppoingList();
		Vector<Integer> 	priNumList		= b.getPriNumList();
		Vector<Float> 		priPriceList	= b.getPriPriceList();
		//DB Stock changed
		
		if(num==0){cancelCommodity(index);return;}
		
		changeCommodityNum(shoppoingList.get(index).getBarcode(), num-priNumList.get(index));
		
		priNumList.remove(index);
		priNumList.insertElementAt(new Integer(num), index);
		
		
		priSumUp(shoppoingList.get(index),priPriceList,true,index);
		sumUp(shoppoingList,priNumList,priPriceList);
		
	};
	public String getSumList()
	{
		return b.toString();
	}
	
	public void end()
	{
		b.clear();
	}
}
