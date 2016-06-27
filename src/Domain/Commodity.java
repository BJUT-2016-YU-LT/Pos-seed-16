package Domain;

public class Commodity {
	private String name;	//name
	private String barcode;	//barcode shi tiao xing ma
	private String unit;	//is liang ci
	private float price;	// shou jia
	private float discount = 1;	//zhe kou
	private boolean promotion = false;
	
	private int promotionCondition;
	private int promotionNum;
	
	public void setName(String name){this.name=name;};
	public String getName(){return name;};
	public void setBarcode(String barcode){this.barcode=barcode;};
	public String getBarcode(){return barcode;};
	public void setUnit(String unit){this.unit=unit;};
	public String getUnit(){return unit;};
	public void setPrice(Float price){this.price=price;};
	public float getPrice(){return price;};
	public void setDiscount(Float discount){this.discount=discount;};
	public float getDiscount(){return discount;};
	public boolean getPromotion(){return promotion;};
	
	public int getPromotionCondition(){return promotionCondition;}
	public int getPromotionNum(){return promotionNum;};
	
	public Commodity()
	{}
	
	public Commodity(String name,String barcode,String unit,float price,float discount,boolean promotion)
	{
		this.name = name;
		this.barcode = barcode;
		this.unit = unit;
		this.price = price;
		this.discount = discount;
		this.promotion = promotion;
	}
	
	@Override
	public String toString()
	{
		String s = "";
		s += "barcode: \'"+barcode+"\',\n";
		s += "name: \'"+name+"\',\n";
		s += "unit: \'"+unit+"\',\n";
		s += "price:\'"+Float.toString(price)+"\',\n";
        if(discount != 1)
        	s += "discount:\'"+Float.toString(discount)+"\',\n";
        s += "promotion\'"+Boolean.toString(promotion)+"\',";
        return s;
	}
};
