package Domain;

public class VIP {
	
	String userID;	//会员id
	int points=0;	//积分点
	boolean isVIP=false;	//是否是vip
	
	public VIP(){};
	
	//获得用户id
	public String getUserID(){return userID;}
	
	//取得积分
	public int getPoints(){return points;}
	
	//设置用户id
	public void setUserID(String userID){this.userID = userID;}
	
	//设置积分点数
	public boolean setPoints(int point)
	{
		if(point < 0)	//积分点数小于0，不合法，返回false
			return false;
		else
		{
			points = point;
			return true;
		}
	}
	
	//设置是否是超级会员
	public void setIsVip(boolean is){isVIP = is;}
	
	//消费，计算用户获得的积分
	public void consume(int sum) throws Exception
	{
		int increasePointValue = 0;	//每5元积分增长的值
		if(0 <= points && points < 200)
			increasePointValue = 1;
		else if(200 <= points && points < 500)
			increasePointValue = 3;
		else if(500 <= points)
			increasePointValue = 5;
		else
			throw new Exception("积分数量不合法。");
		
		points += (sum/5)*increasePointValue;	
	}
	
	//返回是否是超级会员
	public boolean ifVIP()
	{
		return isVIP;
	}
}
