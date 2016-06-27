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
	public float sumPrice;	//�ܼ۸�
	public float discountPrice; //��ʡ��
	
	public Bill presentBill=null;
	
	DecimalFormat df = new DecimalFormat("#.00");	//�����������룬������λ
	
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
		s += "***�̵깺���嵥***\n";
		s += new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+"\n";
		s += "----------------------\n";
		int index = 0;
		for(Commodity c: shoppingList)
		{
			s += "���ƣ�"+c.getName();
			s += "��������"+(priNumList.get(index).intValue()+presentBill.numInBill(c.getBarcode()))+c.getUnit()+
					"�����ۣ�"+Float.toString(c.getPrice())+"(Ԫ)��С�ƣ�"+df.format(priPriceList.get(index))+"(Ԫ)\n";
			index++;
		}
		index = 0;
		if(presentBill != null && !presentBill.shoppingList.isEmpty())
		{
			s += "----------------------\n";
			s += "����������Ʒ:\n";
			for(Commodity c: presentBill.shoppingList)
			{
				s += "���ƣ�"+c.getName()+"��������"+presentBill.priNumList.get(index)+c.getUnit()+"\n";
				index++;
			}
		}
		
		s += "----------------------\n";
		s += "�ܼƣ�"+df.format(sumPrice)+"(Ԫ)\n";
		if(discountPrice >= 0.01)
			s += "��ʡ��"+df.format(discountPrice)+"(Ԫ)\n";
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

