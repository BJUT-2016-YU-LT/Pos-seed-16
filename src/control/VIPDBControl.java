package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Domain.VIP;

public class VIPDBControl 
{
	VIP vip;
	
	private String driver = "com.mysql.jdbc.Driver";
    // URL指向要访问的数据库名Shop_Stock
    private String url = "jdbc:mysql://127.0.0.1:3306/shop";
    // MySQL配置时的用户名
    private String user = "root"; 

    // MySQL配置时的密码
    private String password = "123456";
    
    private Connection conn = null;		//与商品的连接
	public VIPDBControl() throws Exception{
    	try{
            // 加载驱动程序
            Class.forName(driver);
            // 连续数据库
            conn = DriverManager.getConnection(url, user, password);
            // statement用来执行SQL语句
            vip = new VIP();
    	} catch (Exception e) {
    		if(conn != null)
    			conn.close();
    		throw e;
    	}
	}
	
	public VIP searchVip(String userID) throws Exception
	{
		String sql = "SELECT * FROM vip WHERE userID=\'"+userID+"\'";
    	
    	ResultSet rs = null;
    	Statement statement = null;
    	VIP vip = new VIP();
    	
    	try {
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			if(rs.next())
			{
				int is_login;
				if((is_login = rs.getInt("is_login")) == 1)
					throw new Exception("该会员的会员号正在被使用！");
				else if(is_login != 0)
					throw new Exception("数据库中会员"+userID+"的信息出现错误，请联系相关技术人员。");
				
				vip.setUserID(userID);
				vip.setPoints(rs.getInt("points"));
				
				int isVip;
				if((isVip = rs.getInt("is_SVIP")) == 1)
				{
					vip.setIsVip(true);
				}	
				else if(isVip == 0)
					vip.setIsVip(false);
				else
					throw new Exception("数据库中会员"+userID+"的信息出现错误，请联系相关技术人员。");
			}
			else
				throw new Exception("未找到该VIP");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			rs.close();
			statement.close();
		}
    	
    	return vip;
	}
	
	public boolean setVip(VIP vip) throws SQLException
	{
		String is_SVIP;
		if(vip.ifVIP())
			is_SVIP = "1";
		else
			is_SVIP = "0";
		
		String sql = "UPDATE vip SET points="+
				Integer.toString(vip.getPoints())+",is_SVIP="+
				is_SVIP+" WHERE userID=\'"+vip.getUserID()+"\'";
		Statement statement = null;
		try {
			statement = conn.createStatement();
			if(statement.executeUpdate(sql) == 0)
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			statement.close();
		}
		return true;
	}
	
	public boolean activeVipStatus(VIP vip) throws Exception
	{
		Statement statement = null;
		
		try {
			if(isLogin(vip))
				return false;
			statement = conn.createStatement();	//搜索

			//更新状态
			String sql = "UPDATE vip SET is_login=1 WHERE userID=\'"+vip.getUserID()+"\'";
			if(statement.executeUpdate(sql) == 0)	//executeUpdate(sql)返回更新的行数，若行数为0，则激活失败
				throw new Exception("激活失败，请联系相关技术负责人。");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			if(statement != null)
				statement.close();
		}
		this.vip = vip;
		return true;
	}
	
	public boolean closeVipStatus(VIP vip) throws Exception
	{
		Statement statement = null;
		
		try {
			if(!isLogin(vip))
				return false; 
			statement = conn.createStatement();
			String sql = "UPDATE vip SET is_login=0 WHERE userID=\'"+vip.getUserID()+"\'";
			if(statement.executeUpdate(sql) == 0)
				throw new Exception("激活失败，请联系相关技术负责人。");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			if(statement != null)
				statement.close();
		}
		return true;
	}
	
	public boolean isLogin(VIP vip) throws Exception
	{
		String sql = "SELECT is_login FROM vip WHERE userID=\'"+vip.getUserID()+"\'";
		Statement statement = null;
		ResultSet rs = null;
		int is_login;
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			if(rs.next())
				is_login = rs.getInt("is_login");
			else
				throw new Exception("未找到该VIP。");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			if(rs != null)
				rs.close();
			if(statement != null)
				statement.close();
		}
		if(is_login == 0)
			return false;
		else if(is_login == 1)
			return true;
		else
			throw new Exception("数据库中会员"+vip.getUserID()+"的信息出现错误，请联系相关技术人员。");
	}
	
	public void disconnect() throws SQLException
	{
		conn.close();
	}
}
