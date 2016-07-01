package Domain;

public class VIP {
	
	String userID;	//��Աid
	int points=0;	//���ֵ�
	boolean isVIP=false;	//�Ƿ���vip
	
	public VIP(){};
	
	//����û�id
	public String getUserID(){return userID;}
	
	//ȡ�û���
	public int getPoints(){return points;}
	
	//�����û�id
	public void setUserID(String userID){this.userID = userID;}
	
	//���û��ֵ���
	public boolean setPoints(int point)
	{
		if(point < 0)	//���ֵ���С��0�����Ϸ�������false
			return false;
		else
		{
			points = point;
			return true;
		}
	}
	
	//�����Ƿ��ǳ�����Ա
	public void setIsVip(boolean is){isVIP = is;}
	
	//���ѣ������û���õĻ���
	public void consume(int sum) throws Exception
	{
		int increasePointValue = 0;	//ÿ5Ԫ����������ֵ
		if(0 <= points && points < 200)
			increasePointValue = 1;
		else if(200 <= points && points < 500)
			increasePointValue = 3;
		else if(500 <= points)
			increasePointValue = 5;
		else
			throw new Exception("�����������Ϸ���");
		
		points += (sum/5)*increasePointValue;	
	}
	
	//�����Ƿ��ǳ�����Ա
	public boolean ifVIP()
	{
		return isVIP;
	}
}
