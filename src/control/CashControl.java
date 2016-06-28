package control;

import java.util.Vector;

import Domain.Bill;
import Domain.Commodity;
import Domain.VIP;


public class CashControl extends CommodityDBControl
{
	private Bill b=null;	//账单
	private VIP vip=null;
	private VIPDBControl vipdbc=new VIPDBControl();
//	private Commodity tempCommodity;
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
		if(b==null)return;
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
		vipdbc.closeVipStatus(vip);
		
	};
	
	public void finishBill(){
		
		vip.consume((int)b.getSumPrice());
		vipdbc.closeVipStatus(vip);
		b.clear();
		//b=null;
	}
	
	private void addCommodityToPresentBill(Commodity c)throws Exception{
		
		int index=b.getStartIndex();
		int j=b.shoppingList.size();
		Commodity existC;
		boolean isAdd=false;
		int num=1;
		j=b.getStartIndex();
		for(index=0;index<j;index++)
		{
			existC=b.shoppingList.get(index);
			if(existC.getBarcode().equals(c.getBarcode())){
				isAdd=true;
				num=b.priNumList.get(index);
				b.priNumList.remove(index);
				b.priNumList.insertElementAt(new Integer(++num), index);
				
				b.priPriceList.remove(index);
				b.priPriceList.insertElementAt(b.priNumList.get(index)*c.getPrice(), index);
				break;
			}
			
		}
		
		if(!isAdd){
			//not exist
			b.shoppingList.insertElementAt(c,0);
			b.priNumList.insertElementAt(new Integer(num),0);
			b.priPriceList.insertElementAt(c.getPrice(), 0);
			
			b.setShoppingListStart(b.getStartIndex()+1);
		}
		
		try {
			changeCommodityNum(c.getBarcode(), 0-num);
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
		
	}
	
	public boolean formPresentBill()throws Exception{
		
	//	b.presentBill.clear();
		
		int i=b.getStartIndex();
		int j=b.shoppingList.size();
		//clear the stock of present commodity
		for(int k=0;k<i;k++){
			
			changeCommodityNum(b.shoppingList.get(0).getBarcode(), b.priNumList.get(0));

			b.shoppingList.remove(0);
			b.priNumList.remove(0);
			b.priPriceList.remove(0);
		}
		
		i=0;
		b.setShoppingListStart(i);
		
		//judge the shoppoing list of b
		Commodity c;
		for(;i<j;i++)
		{
			c=b.shoppingList.get(i);
			if(c.getPromotion()&&b.priNumList.get(i) >= 2 ) //2 present 1
			{
				
				addCommodityToPresentBill(c);
			
				if(j!=b.shoppingList.size()){
					j=b.shoppingList.size();
					i++;
				}
				
			}
			
		}
		
		//if have no present
		if(b.getStartIndex()==0)return false;
		
		//if present cacul the sum
	//	index=0;
		//b.presentBill.sumPrice=0;
		//for(Commodity c:b.presentBill.shoppingList)
		//{
		//	b.presentBill.priPriceList.add(
	//				c.getPrice()
		//			*b.presentBill.priNumList.get(index));
			
			//b.presentBill.sumPrice+=b.presentBill.priPriceList.get(index);
			
		//	index++;
		//}
		
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

	};
	
	private void priSumUp(Commodity c,boolean isAdd,int index){	//sum up the pri sum up list as new in bill
		if(isAdd){
		//	float price = b.priPriceList.get(index).floatValue();
			b.priPriceList.remove(index);
			b.priPriceList.insertElementAt(new Float(c.getPrice() 
											* c.getDiscount()
											* b.priNumList.get(index)), index);
			//sumPrice += c.getPrice()*c.getDiscount();
			//discountPrice += (1-c.getDiscount())*c.getPrice();
			
			
			//b.priPriceList.add(	  c.getPrice() 
		//						* c.getDiscount()
				//				* b.priNumList.get(index));
		}
		else{
			
			b.priPriceList.addElement(new Float(c.getPrice()
									*c.getDiscount()
									*b.priNumList.get(index)));
			
			//sumPrice += temp.getPrice()*temp.getDiscount();
			//discountPrice += (1-temp.getDiscount())*temp.getPrice();
			//System.out.println(discountPrice);
		}
		
	};
	public void sumUp()throws Exception{
	
		//sum up all money and discount in bill
		float sumPrice=0;
		int i=b.getStartIndex(),j=b.shoppingList.size();
		for(;i<j;i++)
		{
			sumPrice+=b.priPriceList.get(i);
		}
		System.out.println(sumPrice);
		
		float discountPrice=0;
		i=b.getStartIndex();
		j=b.shoppingList.size();
		for(i=b.getStartIndex();i<j;i++)
		{
			discountPrice+=	  b.shoppingList.get(i).getPrice() 
							* (1- b.shoppingList.get(i).getDiscount())
							* b.priNumList.get(i);
		}
		System.out.println(discountPrice);
		
		b.setSumPrice(sumPrice);
		
		if(formPresentBill())
		{
			i=0;j=b.getStartIndex();
			for(i=0;i<j;i++){
				discountPrice+= b.priPriceList.get(i);
			}
			//b.setDiscountPrice(discountPrice + b.presentBill.getSumPrice());
		}
		b.setDiscountPrice(discountPrice);
		
		
		
		
		
		
		
		
		
		
		
		
	};
		
	
	
	//添加商品，返回商品信息
	public String addCommodity(String barcode,int num) throws Exception
	{
		if(num<1)throw new Exception("new commodity num below to 1!");
		
		//获取账单信息
	//	Vector<Commodity>	shoppoingList	= b.shoppingList;
	//	Vector<Integer> 	priNumList		= b.priNumList;
	//	Vector<Float> 		priPriceList	= b.priPriceList;
	//	float 				sumPrice 		= b.getSumPrice();
		//float				discountPrice	= b.getDiscountPrice();
		
		Vector<Commodity> searchResult=searchCommodityByBarcode(barcode);
		if(searchResult.size()==0){throw new Exception("not found");}
			
		Commodity temp =searchResult.get(0);	//数据库中查找相应商品
		
		//if success
		boolean isAdd = false;
		int index;
		int j=b.shoppingList.size();
		Commodity c;
		for(index=b.getStartIndex();index<j;index++)
		{
			c=b.shoppingList.get(index);
			if(c.getBarcode().equals(temp.getBarcode()))
			{
				//exist
				isAdd = true;
				int oldNum = b.priNumList.get(index).intValue();
				
				changeCommodityNum(barcode,0-num);
				
				b.priNumList.remove(index);
				b.priNumList.insertElementAt(new Integer(num+oldNum), index);
				
				
				break;
			}
			
		}
		if(!isAdd)
		{
			//not exist
			b.shoppingList.addElement(temp);
			b.priNumList.addElement(new Integer(num));
			changeCommodityNum(barcode,0-num);
			//priSumUp(tempCommodity);

		}
		
		priSumUp(temp,isAdd,index);
		sumUp();
		
		
		
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
		
		
		sumUp();
	}
	
	
	public void modifyBillInf(int index,int num)throws Exception{
		//modify commodity number in bill
		if(num<0)
		{
			throw new Exception("the num is illegal");
		}
		Vector<Commodity>	shoppoingList	= b.shoppingList;
		Vector<Integer> 	priNumList		= b.priNumList;
	//	Vector<Float> 		priPriceList	= b.priPriceList;
		//DB Stock changed
		
		
		index+=b.getStartIndex();
		
		
		
		if(index>=b.shoppingList.size())throw new Exception("the index you choose is illegal");
		
		if(num==0){cancelCommodity(index);return;}
		
		changeCommodityNum(shoppoingList.get(index).getBarcode(), priNumList.get(index)-num);
		
		priNumList.remove(index);
		priNumList.insertElementAt(new Integer(num), index);
		
		
		priSumUp(shoppoingList.get(index),true,index);
		sumUp();
		
	};
	public String getSumList()
	{
		return b.toString();
	}
	
	public String getCommodityList()
	{
		int i=b.getStartIndex(),j=b.shoppingList.size();
		int k=0;
		String out="";
		for(;i<j;i++)
		{
			k=b.priNumList.get(i);
			while(k-->0)out+="\n"+b.shoppingList.get(i).getBarcode();
			
			i++;
		}
		return out+"\n";
	}
	public String getCommodityListIndex()
	{
		String out="";
		int j=b.shoppingList.size();
		int i=b.getStartIndex();
		for(i=b.getStartIndex();i<j;i++)
		{
			out+=b.shoppingList.get(i).toString();
		}
		return out+"\n";
	}
	
	 public int getNumInBill(int index)
	 {
		 int j=b.priNumList.size()-b.getStartIndex();
		 index-=b.getStartIndex();
		 if(index<0||index+1>j)return (-1);
		return b.priNumList.get(index+b.getStartIndex());
		 
	 }
	 public Commodity getCommodityInBill(int index)throws Exception
	 {
		 int j=b.priNumList.size()-b.getStartIndex();
		 index-=b.getStartIndex();
		 if(index<0||index+1>j)throw new Exception("index error of get Commodity in bill");
			
			return b.shoppingList.get(index+b.getStartIndex());
	 }
	 
	 public int getCommoditySizeInBill(){
		 
		 return b.shoppingList.size()-b.getStartIndex();
	 }
	 
	public void end()
	{
		b.clear();
	}

	public Bill getBill() {

		return b;
	}
	
	public VIP searchVIP(String userID){
		return vipdbc.searchVip(userID);
	}
	
	public boolean activeVIP(VIP vip){
		this.vip=vip;
		b.setVipInBill(vip);
		return vipdbc.activeVipStatus(vip);
	}
	
	public boolean closeVIP(VIP vip){
		this.vip=null;
		b.setVipInBill(null);
		return vipdbc.closeVipStatus(vip);
	}
	public VIP getVIP(){
		return vip;
	}
	
	
	
	
}