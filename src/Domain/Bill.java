package Domain;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

public class Bill{	
	//shopping list store the commodity and number
	public Vector<Commodity> shoppingList;
	//num list is xiao3 jie2 list 
	public Vector<Integer> priNumList;
	//price list is xiao3 jie2 list 
	public Vector<Float> priPriceList;
	//sum price
	public float sumPrice;	//总价格
	public float discountPrice; //节省价
	
	public Bill presentBill=null;
	
	DecimalFormat df = new DecimalFormat("#.00");	//用于四舍五入，保留两位
	
	public Bill() throws Exception
	{
		shoppingList=new Vector<Commodity>();
		priNumList=new Vector<Integer>();
		priPriceList=new Vector<Float>();
		sumPrice = 0;
		discountPrice = 0;
	}
	
	@Override
	public String toString()
	{
		String s = "";
		s += "***商店购物清单***\n";
		s += new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+"\n";
		s += "----------------------\n";
		int index = 0;
		for(Commodity c: shoppingList)
		{
			s += "名称："+c.getName();
			s += "，数量："+(priNumList.get(index).intValue()+presentBill.numInBill(c.getBarcode()))+c.getUnit()+
					"，单价："+Float.toString(c.getPrice())+"(元)，小计："+df.format(priPriceList.get(index))+"(元)\n";
			index++;
		}
		index = 0;
		if(presentBill != null && !presentBill.shoppingList.isEmpty())
		{
			s += "----------------------\n";
			s += "挥泪赠送商品:\n";
			for(Commodity c: presentBill.shoppingList)
			{
				s += "名称："+c.getName()+"，数量："+presentBill.priNumList.get(index)+c.getUnit()+"\n";
				index++;
			}
		}
		
		s += "----------------------\n";
		s += "总计："+df.format(sumPrice)+"(元)\n";
		if(discountPrice >= 0.01)
			s += "节省："+df.format(discountPrice)+"(元)\n";
		s += "**********************\n";
		return s;
	}
	
	public void setSumPrice(float sum)
	{
		sumPrice = sum;
	}
	
	public float getSumPrice()
	{
		return sumPrice;
	}
	
	public void clear()
	{
		shoppingList.clear();
		priNumList.clear();
		priPriceList.clear();
		sumPrice = 0;
		discountPrice = 0;
	}
	
	public float getDiscountPrice()
	{
		return discountPrice;
	}
	
	public void setDiscountPrice(float discount)
	{
		discountPrice = discount;
	}
	
	public int numInBill(String barcode)
	{
		int index=0;
		for(Commodity c: shoppingList)
		{
			if(c.getBarcode().equals(barcode))
				return priNumList.get(index);
		}
		return 0;
	}
};

