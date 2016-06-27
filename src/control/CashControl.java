package control;

import java.util.Vector;

import Domain.Bill;
import Domain.Commodity;



public class CashControl extends CommodityDBControl
{
	private Bill b=null;	//账单
	
	//DBControl control;	
	//数据库控制类
	public CashControl() throws Exception
	{
		super();
		b = new Bill();
		//control = new DBControl();
	}
	
	public boolean newBill(){
		try {
			b=new Bill();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b==null?false:true;
	};
	
	public void cancelBill(){
		
		//cancel the bill 
		for(int i=0;i<b.shoppingList.size();i++)
		{
			//a function can cancel by commodity
			try {
				cancelCommodity(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(b!=null)b.clear();
		b=null;
		
	};
	
	public void finishBill(){
		
		
		b.clear();
		//b=null;
	}
	
	private void addCommodityToPresentBill(Commodity c,Vector<Commodity> shoppoingList,
			Vector<Integer> priNumList){
		
		int index=0;
		boolean isAdd=false;
		int num=1;
		for(Commodity existC: shoppoingList)
		{
			if(existC.getBarcode().equals(c.getBarcode())){
				isAdd=true;
				num=priNumList.get(index);
				priNumList.remove(index);
				priNumList.insertElementAt(new Integer(++num), index);
				break;
			}
			else index++;
		}
		
		if(!isAdd){
			//not exist
			shoppoingList.addElement(c);
			priNumList.addElement(new Integer(num));
			
		}
		
		
	}
	
	public boolean formPresentBill(Vector<Commodity> shoppoingList,
								Vector<Integer> priNumList,
								Vector<Float> priPriceList){
	
		if(b.presentBill==null)
			try {
				b.presentBill=new Bill();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		b.presentBill.clear();
		int index=0;
		//judge the shoppoing list of b
		for(Commodity c:shoppoingList)
		{
			if(c.getPromotion()&&priNumList.get(index) >= 2 ) //2 present 1
			{
				addCommodityToPresentBill(c,
						b.presentBill.shoppingList, b.presentBill.priNumList);
				
			}
			index++;
		}
		
		//if have no present
		if(b.presentBill.shoppingList.size()==0)return false;
		
		//if present cacul the sum
		index=0;
		b.presentBill.sumPrice=0;
		for(Commodity c:b.presentBill.shoppingList)
		{
			b.presentBill.priPriceList.add(
					c.getPrice()
					*b.presentBill.priNumList.get(index));
			
			b.presentBill.sumPrice+=b.presentBill.priPriceList.get(index);
			
			index++;
		}
		
		return true;
		
	}
	
	private void priSumUp(){	//sum up all the pri sum up list
		b.priPriceList.clear();
		int i=0,j=b.shoppingList.size();
		for(i=0;i<j;i++)
		{
			b.priPriceList.add(	  b.shoppingList.get(i).getPrice() 
									* b.shoppingList.get(i).getDiscount()
									* b.priNumList.get(i));
		}
	//	for(Commodity c:nowBill.shoppingList)
	//		nowBill.priSumList.add(c.getPrice() *c.getDiscount());
		
		
	};
	private void priSumUp(Commodity c,Vector<Float> priPriceList,boolean isAdd,int index){	//sum up the pri sum up list as new in bill
		if(isAdd){
			float price = priPriceList.get(index).floatValue();
			priPriceList.remove(index);
			priPriceList.insertElementAt(new Float(c.getPrice() 
											* c.getDiscount()
											* b.priNumList.get(index)), index);
			//sumPrice += c.getPrice()*c.getDiscount();
			//discountPrice += (1-c.getDiscount())*c.getPrice();
			
			
			//b.priPriceList.add(	  c.getPrice() 
		//						* c.getDiscount()
				//				* b.priNumList.get(index));
		}
		else{
			
			priPriceList.addElement(new Float(c.getPrice()
									*c.getDiscount()
									*b.priNumList.get(index)));
			
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
		for(float priSum:priPriceList)
		{
			sumPrice+=priSum;
		}
		System.out.println(sumPrice);
		
		float discountPrice=0;
		int i=0,j=b.shoppingList.size();
		for(i=0;i<j;i++)
		{
			discountPrice+=	  b.shoppingList.get(i).getPrice() 
							* (1- b.shoppingList.get(i).getDiscount())
							* b.priNumList.get(i);
		}
		System.out.println(discountPrice);
		
		b.setSumPrice(sumPrice);
		
		if(formPresentBill(shoppoingList,priNumList,priPriceList))
			b.setDiscountPrice(discountPrice + b.presentBill.getSumPrice());
		else b.setDiscountPrice(discountPrice);
		
		
		
		
		
		
		
		
		
		
		
		
	};
		
	
	
	//添加商品，返回商品信息
	public String addCommodity(String barcode,int num) throws Exception
	{
		if(num<1)throw new Exception("new commodity num below to 1!");
		
		//获取账单信息
		Vector<Commodity>	shoppoingList	= b.shoppingList;
		Vector<Integer> 	priNumList		= b.priNumList;
		Vector<Float> 		priPriceList	= b.priPriceList;
		float 				sumPrice 		= b.getSumPrice();
		//float				discountPrice	= b.getDiscountPrice();
		
		Vector<Commodity> searchResult=searchCommodityByBarcode(barcode);
		if(searchResult.size()==0){throw new Exception("not found");}
			
		Commodity temp =searchResult.get(0);	//数据库中查找相应商品
		
		//if success
		boolean isAdd = false;
		int index = 0;
		for(Commodity c: shoppoingList)
		{
			if(c.getBarcode().equals(temp.getBarcode()))
			{
				//exist
				isAdd = true;
				int oldNum = priNumList.get(index).intValue();
				
				changeCommodityNum(barcode,0-num);
				
				priNumList.remove(index);
				priNumList.insertElementAt(new Integer(num+oldNum), index);
				
				
				
				
				
				break;
			}
			index++;
		}
		if(!isAdd)
		{
			//not exist
			shoppoingList.addElement(temp);
			priNumList.addElement(new Integer(num));
			changeCommodityNum(barcode,0-num);
			//priSumUp(tempCommodity);

		}
		
		priSumUp(temp,priPriceList,isAdd,index);
		sumUp(shoppoingList,priNumList,priPriceList);
		
		
		
		return temp.toString();
	}

	private void cancelCommodity(int index)throws Exception
	{
		Vector<Commodity>	shoppoingList	= b.shoppingList;
		Vector<Integer> 	priNumList		= b.priNumList;
		Vector<Float> 		priPriceList	= b.priPriceList;
		
		if(priNumList.get(index)<0)throw new Exception("此商品现有小结数量小于0");
		
		changeCommodityNum(shoppoingList.get(index).getBarcode(), priNumList.get(index));
		
		shoppoingList.remove(index);
		priNumList.remove(index);
		priPriceList.remove(index);
		
		
		sumUp(shoppoingList,priNumList,priPriceList);
	}
	
	
	public void modifyBillInf(int index,int num)throws Exception{
		//modify commodity number in bill
		Vector<Commodity>	shoppoingList	= b.shoppingList;
		Vector<Integer> 	priNumList		= b.priNumList;
		Vector<Float> 		priPriceList	= b.priPriceList;
		//DB Stock changed
		
		if(num==0){cancelCommodity(index);return;}
		
		changeCommodityNum(shoppoingList.get(index).getBarcode(), priNumList.get(index)-num);
		
		priNumList.remove(index);
		priNumList.insertElementAt(new Integer(num), index);
		
		
		priSumUp(shoppoingList.get(index),priPriceList,true,index);
		sumUp(shoppoingList,priNumList,priPriceList);
		
	};
	public String getSumList()
	{
		return b.toString();
	}
	
	public String getCommodityList()
	{
		int i=0,j=0;
		String out="";
		for(Commodity c:b.shoppingList)
		{
			j=b.priNumList.get(i);
			while(j-->0)out+="\n"+c.getBarcode();
			
			i++;
		}
		return out+"\n";
	}
	public String getCommodityListIndex()
	{
		String out="";
		for(Commodity c:b.shoppingList)
		{
			out+=c.toString();
		}
		return out+"\n";
	}
	
	 public int getNumInBill(int index)
	 {
		 if(index<0||index+1>b.priNumList.size())return (-1);
		return b.priNumList.get(index);
		 
	 }
	 public Commodity getCommodityInBill(int index)throws Exception
	 {
		 if(index<0||index+1>b.shoppingList.size())throw new Exception("index error of get Commodity in bill");
			
			return b.shoppingList.get(index);
	 }
	 
	 public int getCommoditySizeInBill(){
		 return b.shoppingList.size();
	 }
	 
	public void end()
	{
		b.clear();
	}
}
