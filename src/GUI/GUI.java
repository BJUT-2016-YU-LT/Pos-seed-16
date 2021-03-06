package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Domain.Commodity;
import control.CashControl;
import control.StaffDBControl;

public class GUI {  
	public static void main(String[] args) {  
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Loginscreen L=new Loginscreen("");
		 L.setLocationRelativeTo(null);   
  //      NewFrame f=new NewFrame("");
  //      f.setLocationRelativeTo(null);   
    }
	
}
  



 class NewFrame extends JFrame implements ListSelectionListener{  
	 private static final long serialVersionUID = 1L; 
	 
	 JPanel pnlDisplayArea=new JPanel(new FlowLayout());          //定义模板区域  
     JPanel pnlscan=new JPanel(new FlowLayout());
     
	 JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);             //建立标签页
	 JTabbedPane tab1 = new JTabbedPane(JTabbedPane.TOP); 
	 
	 JPanel p1 = new JPanel();                                       //建立标签
	 JPanel p2 = new JPanel();
//	 JPanel p3 = new JPanel(new BorderLayout(5,5));
	 JPanel p4 = new JPanel();
	 JPanel p5 = new JPanel();
	 JPanel p6 = new JPanel();
	 
     JTextArea pnlTest=new JTextArea();                              //声明文本框
     JTextArea pnlTest1=new JTextArea(); 
     JTextArea pnlres=new JTextArea();
     JTextField pnlscanid=new JTextField();                              //定义文本框
     JTextField pnlscanname=new JTextField();
     JTextField pnlscanQ=new JTextField("1");
     JTextField pnlscanM=new JTextField();
     
    JPanel pnl1=new JPanel(new FlowLayout());   
    JList<String> list1 = null;                                      //声明列表框
    
    JLabel Labe4=new JLabel("数量");                                  //声明按钮
    JLabel Labe2=new JLabel("商品码");
    JLabel Labe3=new JLabel("商品名");
    JLabel Labe5=new JLabel("会员码");
    
    JButton btnO=new JButton("添加");
    JButton btnO1=new JButton("添加");
    JButton btnCH=new JButton("更改");
    JButton btnE=new JButton("创建");
    JButton btnC=new JButton("取消");
    JButton btnCA=new JButton("注销");
    JButton btn=new JButton("注销");
    JButton btnA=new JButton("显示");
    JButton btnD=new JButton("确定");
    JButton btnM=new JButton("确定");
                    
    ScrollPane jsp=new ScrollPane();                                   //定义滚条
    ScrollPane jsp1=new ScrollPane(); 
    ScrollPane jsp2=new ScrollPane(); 
    ScrollPane jsp3=new ScrollPane(); 
    
    Container contentPane;                                             //主容器
    
    CashControl cc;
    Vector<Commodity> commodityResult=new Vector<Commodity>();
    int commodityIndex;
    StaffDBControl sdbc;
    int userId=0;
    
    
    Vector<String> vector = new Vector<String>();
    DefaultListModel dlm = new DefaultListModel();                   
    int open=0;
    int search=0;
    String stmp = "";
    
    NewFrame(int userId) {  
  	  super("pos机");                                       
  	  this.setSize(750,600);                                            //设置总大小

  	  contentPane=getContentPane();  
  	  contentPane.setLayout(new FlowLayout());      
  	  contentPane.add(pnlscan);
  	  contentPane.add(pnlDisplayArea);
  	  
  	  
  	  list1 = new JList<String>(vector);	   
  	  pnlscan.add(tab);                                                //布局
  	  
  	  pnlscan.add(Labe4);
  	  pnlscan.add(pnlscanQ); 
  	  pnlscan.add(Labe5);
  	  pnlscan.add(pnlscanM);
  	  pnlscan.add(btnM); 
  	  pnlscan.add(btnE);
  	  pnlscan.add(btnC);
  	  pnlscan.add(btnCA);
  	  pnlscan.add(jsp3); 
	  int size=20;                                                      //列表字体大小
	  list1.setFont(new Font("Serif",Font.PLAIN,size));
  	                                       //标签页添加
  	  p4.add(btnA);
  	  p4.add(btnD);
  	  
  	  pnlDisplayArea.add(tab1);                                           
  	  pnlDisplayArea.add(jsp1);
  	  
  	  tab.add(p1,"商品码");                                              //添加标签页
  	  tab.add(p2,"商品名"); 
  	  tab.add(p4,"撤销");
  	  
  	  tab1.add(p5,"商品");                                              
  	  tab1.add(p6,"商品列表文件");
  	  p5.add(jsp);
  	  p6.add(jsp2);
  	  
  	  p1.add(Labe2);                                                    
  	  p1.add(pnlscanid);
  	  p1.add(btnO);
  	  
  	  p2.add(Labe3);
  	  p2.add(pnlscanname);
  	  p2.add(btnO1);
  	  
  	  Dimension d=new Dimension(30,600);                           //定义区域大小
  	  d=new Dimension(300,110);
  	  tab.setPreferredSize(d);
      p1.setPreferredSize(d);
  	  p2.setPreferredSize(d);
  	  p4.setPreferredSize(d);  	 
  	  d=new Dimension(270,900);
  	  list1.setPreferredSize(d);	  
  	  d=new Dimension(300,550);
  	  pnlscan.setPreferredSize(d);
  	  d=new Dimension(300,250);
  	  tab1.setPreferredSize(d); 	  
  	  d=new Dimension(300,550);    
  	  pnlDisplayArea.setPreferredSize(d);
  	  
  	  pnlTest.setPreferredSize(new Dimension(270, 1500));               //设置模块大小
  	  pnlTest1.setPreferredSize(new Dimension(270, 1500)); 
  	  pnlres.setPreferredSize(new Dimension(400, 3000));
   
      pnl1.setPreferredSize(new Dimension(450, 200));
  	  pnlscanid.setPreferredSize(new Dimension(230, 30));
  	  pnlscanname.setPreferredSize(new Dimension(230, 30));
  	  pnlscanQ.setPreferredSize(new Dimension(200, 30));
  	  pnlscanM.setPreferredSize(new Dimension(185, 30));	
  	                                    //设置模块颜色
  	  pnlTest.setBackground(Color.white);
  	  pnlTest1.setBackground(Color.white);
  	  
  	  pnlres.setBackground(Color.white);
  	  pnlscanid.setBackground(Color.white);
  	  pnlscanname.setBackground(Color.white); 	 
  	  pnlscanQ.setBackground(Color.white); 	
      pnl1.setBackground(Color.white);   	  

  	  pnlTest.setEditable(false);         
  	  pnlTest1.setEditable(false);                                     //设置文本框不可写 
  	  pnlres.setEditable(false);
  	  
  	  btnO.setEnabled(false);    
  	  btnO1.setEnabled(false);    
  	  btnCH.setEnabled(false);    
  	  btnE.setEnabled(true);                                           //设置按钮不可读
  	  btnC.setEnabled(false);
	  btnA.setEnabled(false);
	  btnD.setEnabled(false);
	  btnM.setEnabled(false);
  	    
  	  pnlres.setBorder(new TitledBorder("bill"));                      //设置文本框标题
  	  
  	  jsp .add(pnlTest);                                                //添加滚条
  	  jsp.setSize(300,180);  	  
  	  jsp1 .add(pnlres);
  	  jsp1.setSize(300,230);  	  
  	  jsp2 .add(pnlTest1);
  	  jsp2.setSize(300,180); 	  
  	  jsp3.add("west",list1);
  	  jsp3.setSize(300,270);
  	  
  	  pnlscanname.addKeyListener(new KeyAdapter(){
  		  public void keyPressed(KeyEvent e)    
  		  {    
  			  if(e.getKeyChar()==KeyEvent.VK_ENTER )   //按回车键执行相应操作; 
          { 
  				  myclick();
          } 
        } 
  	  });   	  	  
  	  
  	  pnlscanid.addKeyListener(new KeyAdapter(){
  		  public void keyPressed(KeyEvent e)    
  		  {    
  			  if(e.getKeyChar()==KeyEvent.VK_ENTER )   //按回车键执行相应操作; 
          { 
  				  myclockO(); 				  
          } 
        } 
  	  }); 
  	  
  	  pnlscanM.addKeyListener(new KeyAdapter(){
  		  public void keyPressed(KeyEvent e)    
  		  {    
  			  if(e.getKeyChar()==KeyEvent.VK_ENTER )   //按回车键执行相应操作; 
          { 
  				  myclockM(); 				  
          } 
        } 
  	  }); 
  	  
   	  btnO.addMouseListener((MouseListener) new MYListenerO1());        //添加监视器
   	  btnO1.addMouseListener((MouseListener) new MYListenerO());  
   	  btnCH.addMouseListener((MouseListener) new MYListenerCH());     
   	  btnE.addMouseListener((MouseListener) new MYListenerE());
   	  btnC.addMouseListener((MouseListener) new MYListenerC());
      btnCA.addMouseListener((MouseListener) new MYListenerCA());
      btnA.addMouseListener((MouseListener) new MYListenerA());
      btnD.addMouseListener((MouseListener) new MYListenerD());
      btnM.addMouseListener((MouseListener) new MYListenerM());
      
      btnE.setMnemonic(KeyEvent.VK_N);
   	  
   	  pnlscanid.addActionListener(ActionListener());
   	  pnlscanname.addActionListener(ActionListener());
   	  pnlscanQ.addActionListener(ActionListener());
   	  

   	  list1.setModel(dlm);
      list1.addListSelectionListener((ListSelectionListener) this);
      
      pnlscanname.getDocument().addDocumentListener (new OnValueChanged()); 
      
      this.addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e)
          {
        	  try {
				sdbc.logout(userId);
				cc.closeVIP(cc.getVIP());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				
				e1.printStackTrace();
			}
          }
      });
      
  	  setVisible(true);
  	  
  	  try{
  		  cc=new CashControl();
  	  }catch(Exception e){
  		  e.printStackTrace();
  	  }
  	  
  	  
  	  try{
  		  sdbc=new StaffDBControl();
  	  }catch(Exception e){
  		  e.printStackTrace();
  	  }
  	  this.userId=userId;
    }
    
    public class OnValueChanged implements DocumentListener {
    	@Override
    	public void changedUpdate(DocumentEvent e) {
    		// TODO Auto-generated method stub
    		 System.out.println("Attribute Changed"+e);
    	}
    	@Override
    	public void insertUpdate(DocumentEvent e) {
    		// TODO Auto-generated method stub
    		try {
    			vector.clear();
       			dlm.clear();
       			
       			commodityResult.clear();
       			commodityIndex=0;
       			try {
    					commodityResult=cc.searchCommodityByName(pnlscanname.getText());
    				} catch (Exception e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
       			
       			if(commodityResult.size()==0){
       				vector.add("(not found)");
       				dlm.addElement(vector.get(0));
       			}
       			
       			for(Commodity c:commodityResult)
       			{
       				vector.add(c.getName()+"  单位："+c.getUnit()+"  价格："+c.getPrice());
       				dlm.addElement(vector.get(vector.size()-1));
       			}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
    	@Override
    	public void removeUpdate(DocumentEvent e) {
    		// TODO Auto-generated method stub
    		try {
    			vector.clear();
       			dlm.clear();
       			
       			commodityResult.clear();
       			commodityIndex=0;
       			try {
    					commodityResult=cc.searchCommodityByName(pnlscanname.getText());
    				} catch (Exception e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
       			
       			if(commodityResult.size()==0){
       				vector.add("(not found)");
       				dlm.addElement(vector.get(0));
       			}
       			
       			for(Commodity c:commodityResult)
       			{
       				vector.add(c.getName()+"  单位："+c.getUnit()+"  价格："+c.getPrice());
       				dlm.addElement(vector.get(vector.size()-1));
       			}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
    }
    
    
    
    
    public void valueChanged(ListSelectionEvent e)
    {
        int tmp = 0;
        int[] index = list1.getSelectedIndices();                         //利用JList类所提供的getSelectedIndices()方法可得到用户所选取的所有 
        for(int i=0; i < index.length ; i++)                              //index值，这些index值由一个int array返回.
        {
            tmp = index[i];
            stmp =vector.get(tmp)+"  ";
            
        }
        
        commodityIndex=tmp;
        
        
    }
    
    private ActionListener ActionListener() {                               //文本框监视器
		if(open==0){
			pnlscanid.setEditable(false);
			pnlscanname.setEditable(false);
		}
		return null;
	}
    
    public void myclick(){
  	     list1.setBorder(new TitledBorder("相关商品"));
   			search=0;    			
   			if(pnlscanname.getText().equals(""))
   				JOptionPane.showMessageDialog(null, "请输入商品", "请输入商品", JOptionPane.ERROR_MESSAGE);
   			else{
   				if(stmp=="")
   				JOptionPane.showMessageDialog(null, "请选择商品", "请请选择商品", JOptionPane.ERROR_MESSAGE);
   			}
   		if(!pnlscanname.getText().equals("")&&stmp!=""){

   			
   			try {
					cc.addCommodity(commodityResult.get(commodityIndex).getBarcode(), Integer.parseInt(pnlscanQ.getText()) );
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   			
   			
   			pnlTest.setText("");
   			pnlTest.append(cc.getCommodityListIndex()+'\n');
   		    //pnlTest.append(commodityResult.get(commodityIndex).toString()+"\n");
   		    
   		    pnlTest1.setText("");
   		    pnlTest1.append(cc.getCommodityList()+'\n');
   		    
   		    
   			pnlscanQ.setText("1");
   			pnlscanQ.setEditable(true);
//  			btnO1.setText("搜索");
   			pnlscanname.setText("");
   			dlm.clear();
   			vector.clear();
   			stmp="";
   		}

    }
   public void myclockM(){
    	    		try {
				cc.activeVIP(
						cc.searchVIP(pnlscanM.getText())
						);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Wrong", e.getMessage(), JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
    }
    
    class MYListenerM implements MouseListener{ 
    	public void mouseClicked(MouseEvent arg0) {							//vip confirm
    		
    		myclockM();
    		pnlscanM.setText("");
    	}
    	@Override
    	public void mouseEntered(MouseEvent arg0) {}
    	@Override
    	public void mouseExited(MouseEvent arg0) {}
    	@Override
    	public void mousePressed(MouseEvent arg0) {}
    	@Override
    	public void mouseReleased(MouseEvent arg0) {}
    }    

	class MYListenerO implements MouseListener{                              //添加商品按钮监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {							//use name to search
    		myclick();
    	}
    	@Override
    	public void mouseEntered(MouseEvent arg0) {}
    	@Override
    	public void mouseExited(MouseEvent arg0) {}
    	@Override
    	public void mousePressed(MouseEvent arg0) {}
    	@Override
    	public void mouseReleased(MouseEvent arg0) {}
    	}
	
	
	public void myclockO(){
	     list1.setBorder(new TitledBorder("商品信息"));
		if(pnlscanid.getText().equals(""))
			JOptionPane.showMessageDialog(null, "请输入商品码", "请输入商品码", JOptionPane.ERROR_MESSAGE);
		if(!pnlscanid.getText().equals("")){
			
			vector.clear();
			dlm.clear();
			
			if(commodityResult!=null)commodityResult.clear();
			commodityIndex=0;
			try {
				commodityResult=cc.searchCommodityByBarcode(pnlscanid.getText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Wrong", e.getMessage(), JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			if(commodityResult.size()==0){
				vector.add("(not found)");
				dlm.addElement(vector.get(0));
			}else
			{
				try {
					cc.addCommodity(commodityResult.get(0).getBarcode(), Integer.parseInt(pnlscanQ.getText()) );
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Wrong", e.getMessage(), JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Wrong", e.getMessage(), JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				
				vector.add(commodityResult.get(0).getName()+"  单位："+
						commodityResult.get(0).getUnit()+"  价格："+
						commodityResult.get(0).getPrice());
				dlm.addElement(vector.get(vector.size()-1));
   			
				pnlTest.setText("");
   			pnlTest.append(cc.getCommodityListIndex()+'\n');
   		    //pnlTest.append(commodityResult.get(commodityIndex).toString()+"\n");
   		    
   		    pnlTest1.setText("");
   		    pnlTest1.append(cc.getCommodityList()+'\n');
   			
   		   // pnlTest.append(commodityResult.get(0).toString()+"\n");
   		    
   		   // pnlTest1.setText("");
   		  //  pnlTest1.append(cc.getCommodityList());
   		    
				//pnlTest.append("商品码"+pnlscanid.getText()+","+pnlscanQ.getText()+"\n");
				pnlscanQ.setText("1");
				pnlscanQ.setEditable(true);
			}	
		}
	}
	
	
	class MYListenerO1 implements MouseListener{                                       //添加商品码按钮监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		myclockO();
    	}
    	@Override
    	public void mouseEntered(MouseEvent arg0) {}
    	@Override
    	public void mouseExited(MouseEvent arg0) {}
    	@Override
    	public void mousePressed(MouseEvent arg0) {}
    	@Override
    	public void mouseReleased(MouseEvent arg0) {}
    	}
 
	
	class MYListenerC implements MouseListener{                                   //取消按钮监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		open=0;
			btnO.setEnabled(false);
			btnO1.setEnabled(false);
			btnCH.setEnabled(false);
			btnC.setEnabled(false);
			btnM.setEnabled(false);
			btnE.setText("\n创立");
//			pnlTest.append(pnlscanid.getText());
		    pnlscanid.setEditable(false);
		    pnlscanname.setEditable(false);
		    pnlTest.setText("");
		    pnlTest1.setText("");
		    pnlres.append("取消账单");
			//cancel()                传进去一个取消信号
    		
		    try {
				cc.cancelBill();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Wrong", e.getMessage(), JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
    	}
    	@Override
    	public void mouseEntered(MouseEvent arg0) {}
    	@Override
    	public void mouseExited(MouseEvent arg0) {}
    	@Override
    	public void mousePressed(MouseEvent arg0) {}
    	@Override
    	public void mouseReleased(MouseEvent arg0) {}
    	}
	
	class MYListenerA implements MouseListener{                                 //显示总帐单监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    	    list1.setBorder(new TitledBorder("当前账单"));
    	     
    	    int j=cc.getCommoditySizeInBill();
    	    vector.clear();
    	    dlm.clear();
    	    for(int i=0;i<j;i++)
    	    {
    	    	Commodity tempCommodity=null;
				try {
					tempCommodity = cc.getCommodityInBill(i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}
    	    	vector.add(tempCommodity.getName()+"  "+
    	    	cc.getNumInBill(i)+tempCommodity.getUnit()+"  "+
    	    	tempCommodity.getPrice()+"RMB");
    	    	dlm.addElement( vector.get(i));
    	    }
    	    	//vector.add("1");
    	    	//dlm.addElement( vector.get(0));
    	    
 			
 			
    	}
    	@Override
    	public void mouseEntered(MouseEvent arg0) {}
    	@Override
    	public void mouseExited(MouseEvent arg0) {}
    	@Override
    	public void mousePressed(MouseEvent arg0) {}
    	@Override
    	public void mouseReleased(MouseEvent arg0) {}
    	}
	
	class MYListenerD implements MouseListener{                                      //取消确认按钮监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    	     if(stmp=="")
 				JOptionPane.showMessageDialog(null, "请选择商品", "请请选择商品", JOptionPane.ERROR_MESSAGE);
		    
    	    //commodityIndex;
    	    try {
				cc.modifyBillInf(commodityIndex, Integer.parseInt(pnlscanQ.getText()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    
    	    //pnlTest.append("商品名称:"+ stmp+",数量："+pnlscanQ.getText()+"\n");	
    	    
    		pnlTest.setText("");
			pnlTest.append(cc.getCommodityListIndex());
		    //pnlTest.append(commodityResult.get(commodityIndex).toString()+"\n");
		    pnlTest1.setText("");
		    pnlTest1.append(cc.getCommodityList());
		    
		    
			pnlscanQ.setText("1");
			dlm.clear();
			vector.clear();
			stmp="";
    	}
    	@Override
    	public void mouseEntered(MouseEvent arg0) {}
    	@Override
    	public void mouseExited(MouseEvent arg0) {}
    	@Override
    	public void mousePressed(MouseEvent arg0) {}
    	@Override
    	public void mouseReleased(MouseEvent arg0) {}
    	}
	
	
	class MYListenerCA implements MouseListener{                           //注销按钮监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		
    		System.out.println("logout"+userId);
    		try {
				sdbc.logout(userId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
    		
    		try {
				cc.cancelBill();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Wrong", e.getMessage(), JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
    		userId=0;
    		
    		Loginscreen lo=new Loginscreen("");
    		lo.setLocationRelativeTo(null);
    		dispose();
    	}
    	@Override
    	public void mouseEntered(MouseEvent arg0) {}
    	@Override
    	public void mouseExited(MouseEvent arg0) {}
    	@Override
    	public void mousePressed(MouseEvent arg0) {}
    	@Override
    	public void mouseReleased(MouseEvent arg0) {}
    	}


	
    class MYListenerE implements MouseListener{                                   //建立结束按钮监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		if(open==0){
    			open=1;
    			btnO.setEnabled(true);
    			btnO1.setEnabled(true);
    			btnCH.setEnabled(true);
    			btnC.setEnabled(true);
    			btnA.setEnabled(true);
    			btnM.setEnabled(true);
    			btnD.setEnabled(true);
    			btnE.setText("完成");
    		    pnlTest.append("开始创立\n");      		
    		    pnlscanid.setEditable(true);
    		    pnlscanname.setEditable(true);
    		    pnlres.setText("");
    		    cc.newBill();
    		   
    		    
    	   }
    		else{
    			open=0;
    			btnO.setEnabled(false);
    			btnO1.setEnabled(false);
    			btnCH.setEnabled(false);
    			btnA.setEnabled(false);
    			btnM.setEnabled(false);
    			btnD.setEnabled(false);
    			btnE.setText("创立");
//    			pnlTest.append(pnlscanid.getText());
    		    pnlscanid.setEditable(false);
    		    pnlscanname.setEditable(false);
    		    pnlTest.setText("");
    		    pnlscanid.setText("");
    		    pnlscanname.setText("");
    		    pnlscanQ.setText("1");
    		    pnlres.append("录入完成\n"+cc.getSumList());
    		    
    		    vector.clear();
    		    dlm.clear();
    		    try {
					cc.finishBill();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Wrong", e.getMessage(), JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
    		    //
    		}
           
    	}
		@Override
    	public void mouseEntered(MouseEvent arg0) {}
    	@Override
    	public void mouseExited(MouseEvent arg0) {}
    	@Override
    	public void mousePressed(MouseEvent arg0) {}
    	@Override
    	public void mouseReleased(MouseEvent arg0) {}
    	}
    
    
    
    class MYListenerCH implements MouseListener{                      //改变数字按钮监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		 pnlscanQ.setEditable(true);
    	}
    	@Override
    	public void mouseEntered(MouseEvent arg0) {}
    	@Override
    	public void mouseExited(MouseEvent arg0) {}
    	@Override
    	public void mousePressed(MouseEvent arg0) {}
    	@Override
    	public void mouseReleased(MouseEvent arg0) {}
    	}
	    @Override
	    protected void finalize()
	    {
	    	if(userId!=0)
	    	{
	    		try {
					sdbc.logout(userId);
					cc.closeVIP(cc.getVIP());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "wrong",e.getMessage(), JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
	    		
	    	}
	    	try {
				sdbc.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
 
 
 
 public void paint(Graphics g)
 {
          super.paint(g);
	  	  Dimension d=new Dimension(30,600);   
	  	  d=new Dimension(this.getWidth()/2-15,this.getHeight());//定义区域大小
	  	  pnlscan.setPreferredSize(d);	  
	  	  pnlDisplayArea.setPreferredSize(d);
	  	  d=new Dimension(this.getWidth()/2-20,this.getHeight()/5);
	  	  tab.setPreferredSize(d);
	      p1.setPreferredSize(d);
	  	  p2.setPreferredSize(d);
	  	  p4.setPreferredSize(d);
	  	  d=new Dimension(this.getWidth()/2-10,this.getHeight()/2-30);
	  	  tab1.setPreferredSize(d);                                           //添加滚条
	  	  p5.setPreferredSize(d); 
	  	  p6.setPreferredSize(d); 
	  	  jsp.setSize(this.getWidth()/2-10,this.getHeight()/2-30);
	  	  jsp2.setSize(this.getWidth()/2-10,this.getHeight()/2-30);
	  	  jsp1.setSize(this.getWidth()/2-20,this.getHeight()/2-30);
	  	  jsp3.setSize(this.getWidth()/2-20,this.getHeight()/2);
	  	  
	  	  pnlTest.setPreferredSize(new Dimension(this.getWidth()/2-10, this.getHeight()*3));               //设置模块大小
	  	  pnlTest1.setPreferredSize(new Dimension(this.getWidth()/2-10, this.getHeight()*3)); 
	  	  pnlres.setPreferredSize(new Dimension(this.getWidth()*3/5,this.getHeight()*6));
	  	  pnlscanid.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()*5/100));
	  	  pnlscanname.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()*5/100));
	  	  pnlscanQ.setPreferredSize(new Dimension(this.getWidth()*4/10, this.getHeight()*5/100));
	  	  pnlscanM.setPreferredSize(new Dimension(this.getWidth()*3/10, this.getHeight()*5/100));
	  	 	  	  
	//  	  p3.setBackground(Color.white);                                    //设置模块颜色
	  	  pnlTest.setBackground(Color.white);
	  	  pnlTest1.setBackground(Color.white);
	  	  
	  	  pnlres.setBackground(Color.white);
	  	  pnlscanid.setBackground(Color.white);
	  	  pnlscanname.setBackground(Color.white); 	 
	  	  pnlscanQ.setBackground(Color.white); 	
	      pnl1.setBackground(Color.white);   
	      
	        int size1=this.getHeight()/10;
	        Labe4.setFont(new Font("宋体" , Font.BOLD,size1/4));
	        Labe4.setForeground(Color.black); 
	        Labe3.setFont(new Font("宋体" , Font.BOLD,size1/4));
	        Labe3.setForeground(Color.black); 
	        Labe2.setFont(new Font("宋体" , Font.BOLD,size1/4));
	        Labe2.setForeground(Color.black); 
	        Labe5.setFont(new Font("宋体" , Font.BOLD,size1/4));
	        Labe5.setForeground(Color.black); 
		  	btnO.setFont(new Font("宋体" , Font.BOLD,size1/4));
		  	btnO.setForeground(Color.black); 
		  	btnO1.setFont(new Font("宋体" , Font.BOLD,size1/4));
		  	btnO1.setForeground(Color.black);
		  	btnE.setFont(new Font("宋体" , Font.BOLD,size1/4));
		  	btnE.setForeground(Color.black); 
		  	btnC.setFont(new Font("宋体" , Font.BOLD,size1/4));
		  	btnC.setForeground(Color.black); 
		  	btnCA.setFont(new Font("宋体" , Font.BOLD,size1/4));
		  	btnCA.setForeground(Color.black); 
		  	btn.setFont(new Font("宋体" , Font.BOLD,size1/4));
		  	btn.setForeground(Color.black); 
		  	btnA.setFont(new Font("宋体" , Font.BOLD,size1/4));
		  	btnA.setForeground(Color.black); 
		  	btnD.setFont(new Font("宋体" , Font.BOLD,size1/4));
		  	btnD.setForeground(Color.black); 
		  	btnM.setFont(new Font("宋体" , Font.BOLD,size1/4));
		  	btnM.setForeground(Color.black); 
		  	btnCH.setFont(new Font("宋体" , Font.BOLD,size1/4));
		  	btnCH.setForeground(Color.black); 
 }
 }
 
