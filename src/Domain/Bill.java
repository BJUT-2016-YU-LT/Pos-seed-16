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
	
	private int startIndexOfShoppoingList=0;
	
	private VIP vip=null;
	
	//public Bill presentBill=null;
	
	DecimalFormat df = new DecimalFormat("#.00");	//�����������룬������λ
	
	public Bill() throws Exception
	{
		shoppingList=new Vector<Commodity>();
		priNumList=new Vector<Integer>();
		priPriceList=new Vector<Float>();
		sumPrice = 0;
		discountPrice = 0;
	}
	
	private int searchPromotionNumInBill(String barcode){
		if(startIndexOfShoppoingList<1)return 0;
		
		for(int i=0;i<startIndexOfShoppoingList;i++)
		{
			if(barcode.equals(shoppingList.get(i).getBarcode())){
				return priNumList.get(i);
				}
			
		}
		return 0;
	}
	
	
	
	@Override
	public String toString()
	{
		String s = "";
		s += "***�̵깺���嵥***\n";
		
		if(vip!=null){
			s+="VipID:"+vip.getUserID()+" Vip����:"+vip.getPoints()+"\n";
			s += "----------------------\n";
		}
		
		
//		s +="��ӡʱ��:";
//		s += new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+"\n";
		s += "----------------------\n";
		int index = startIndexOfShoppoingList;
		int j=shoppingList.size();
		
		Commodity c;
		//for(;index<j;index++)
		//for(Commodity c:shoppingList)
		for(;index<j;index++)
		{
			
			
			c=shoppingList.get(index);
			s += "���ƣ�"+c.getName();
			s += "��������"+(priNumList.get(index).intValue()+
					searchPromotionNumInBill(c.getBarcode())+
					//presentBill.numInBill(c.getBarcode()))+
					c.getUnit()+
					"�����ۣ�"+Float.toString(c.getPrice()));
			if(priPriceList.get(index) >= 1)
				s += "(Ԫ)��С�ƣ�"+df.format(priPriceList.get(index))+"(Ԫ)\n";
			else
				s += "(Ԫ)��С�ƣ�"+"0"+df.format(priPriceList.get(index))+"(Ԫ)\n";
			//index++;
		}
		
		index = 0;
		
		if(startIndexOfShoppoingList!=0)
		{
			s += "----------------------\n";
			s += "����������Ʒ:\n";
			for(;index< startIndexOfShoppoingList;index++)
			{
				c=shoppingList.get(index);
				
				s += "���ƣ�"+c.getName()+"��������"+priNumList.get(index)+c.getUnit()+"\n";
				
			}
		}
		
		s += "----------------------\n";
		if(sumPrice >= 0)
			s += "�ܼƣ�"+df.format(sumPrice)+"(Ԫ)\n";
		else
			s += "�ܼƣ�"+"0"+df.format(sumPrice)+"(Ԫ)\n";
		if(discountPrice >= 0.01)
			if(discountPrice >= 1)
				s += "��ʡ��"+df.format(discountPrice)+"(Ԫ)\n";
			else
				s += "��ʡ��"+"0"+df.format(discountPrice)+"(Ԫ)\n";
		s += "**********************\n";
		return s;
	}
	
	
	public void setShoppingListStart(int num){
		startIndexOfShoppoingList=num;
	}
	
	public VIP getVipOfBill(){
		return vip;
	}
	
	public boolean ifSuperVIP(){
		if(vip==null)return false;
		
		return vip.ifVIP();
	}
	public void setVipInBill(VIP v){
		vip=v;
	//	System.out.println(vip.ifVIP());
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
	
	public int getStartIndex(){
		return startIndexOfShoppoingList;
	}
};