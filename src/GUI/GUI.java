import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUI {  
	public static void main(String[] args) {  
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
	 JPanel p3 = new JPanel(new BorderLayout(5,5));
	 JPanel p4 = new JPanel();
	 
     JTextArea pnlTest=new JTextArea();                              //声明文本框
     JTextArea pnlTest1=new JTextArea(); 
     JTextArea pnlres=new JTextArea();
     JTextField pnlscanid=new JTextField();                              //定义文本框
     JTextField pnlscanname=new JTextField();
     JTextField pnlscanQ=new JTextField("1");
     
    JPanel pnl1=new JPanel(new FlowLayout());   
    JList<String> list1 = null;                                      //声明列表框
    
    JLabel Labe4=new JLabel("数量");                                  //声明按钮
    JLabel Labe2=new JLabel("商品码");
    JLabel Labe3=new JLabel("商品名");
    JButton btnO=new JButton("添加");
    JButton btnO1=new JButton("搜索");
    JButton btnCH=new JButton("更改");
    JButton btnE=new JButton("创建");
    JButton btnC=new JButton("取消");
    JButton btnCA=new JButton("注销");
    JButton btn=new JButton("注销");
    JButton btnA=new JButton("显示");
    JButton btnD=new JButton("确定");
                    
    ScrollPane jsp=new ScrollPane();                                   //定义滚条
    ScrollPane jsp1=new ScrollPane(); 
    ScrollPane jsp2=new ScrollPane(); 
    ScrollPane jsp3=new ScrollPane(); 
    
    Container contentPane;                                             //主容器
    
    Vector<String> vector = new Vector<String>();
    DefaultListModel dlm = new DefaultListModel();                   
    int open=0;
    int search=0;
    String stmp = "";
    
    NewFrame(String title) {  
  	  super("pos机");                                       
  	  this.setSize(670,550);                                            //设置总大小

  	  contentPane=getContentPane();  
  	  contentPane.setLayout(new FlowLayout());      
  	  contentPane.add(pnlscan);
  	  contentPane.add(pnlDisplayArea);
  	  
  	  
  	  list1 = new JList<String>(vector);	   
  	  pnlscan.add(tab);                                                //布局
  	  pnlscan.add(jsp3); 
  	  pnlscan.add(Labe4);
  	  pnlscan.add(pnlscanQ);
  	  pnlscan.add(btnCH);  	  
  	  pnlscan.add(btnE);
  	  pnlscan.add(btnC);
  	  pnlscan.add(btnCA);
  	  
	  int size=20;                                                      //列表字体大小
	  list1.setFont(new Font("Serif",Font.PLAIN,size));
  	  
  	  p3.add("West", list1);                                            //标签页添加
  	  p4.add(btnA);
  	  p4.add(btnD);
  	  
  	  pnlDisplayArea.add(tab1);                                           
  	  pnlDisplayArea.add(jsp1);
  	  
  	  tab.add(p1,"商品码");                                              //添加标签页
  	  tab.add(p2,"商品名"); 
  	  tab.add(p4,"撤销");
  	  
  	  tab1.add(jsp,"商品");                                              
  	  tab1.add(jsp2,"商品列表文件");
  	  
  	  p1.add(Labe2);                                                    
  	  p1.add(pnlscanid);
  	  p1.add(btnO);
  	  
  	  p2.add(Labe3);
  	  p2.add(pnlscanname);
  	  p2.add(btnO1);
  	  
  	  Border border=new LineBorder(Color.black);                    //定义边界
  	  tab.setBorder(border);	
  	  tab1.setBorder(border);	
  	  p3.setBorder(border);	
  	  
  	  Dimension d=new Dimension(30,600);                           //定义区域大小
  	  d=new Dimension(300,110);
  	  tab.setPreferredSize(d);
      p1.setPreferredSize(d);
  	  p2.setPreferredSize(d);
  	  p4.setPreferredSize(d);  	 
  	  d=new Dimension(270,900);
  	  p3.setPreferredSize(d);	  
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
  	  pnlscanQ.setPreferredSize(new Dimension(199, 30));
  	  
  	  p3.setBackground(Color.white);                                    //设置模块颜色
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
  	  
  	    
  	  pnlres.setBorder(new TitledBorder("bill"));                      //设置文本框标题
      p3.setBorder(new TitledBorder(""));
  	  
  	  jsp .add(pnlTest);                                                //添加滚条
  	  jsp.setSize(280,180);
  	  
  	  jsp1 .add(pnlres);
  	  jsp1.setSize(300,230);
  	  
  	  jsp2 .add(pnlTest1);
  	  jsp.setSize(300,180);
  	  
  	  jsp3.add(p3);
  	  jsp3.setSize(300,300);
  	  
   	  btnO.addMouseListener((MouseListener) new MYListenerO1());        //添加监视器
   	  btnO1.addMouseListener((MouseListener) new MYListenerO());  
   	  btnCH.addMouseListener((MouseListener) new MYListenerCH());     
   	  btnE.addMouseListener((MouseListener) new MYListenerE());
   	  btnC.addMouseListener((MouseListener) new MYListenerC());
      btnCA.addMouseListener((MouseListener) new MYListenerCA());
      btnA.addMouseListener((MouseListener) new MYListenerA());
      btnD.addMouseListener((MouseListener) new MYListenerD());
   	  
   	  pnlscanid.addActionListener(ActionListener());
   	  pnlscanname.addActionListener(ActionListener());
   	  pnlscanQ.addActionListener(ActionListener());
   	  

   	  list1.setModel(dlm);
      list1.addListSelectionListener((ListSelectionListener) this);
  	  setVisible(true);
  	  
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
    }
    
    private ActionListener ActionListener() {                               //文本框监视器
		if(open==0){
			pnlscanid.setEditable(false);
			pnlscanname.setEditable(false);
			pnlscanQ.setEditable(false);
		}
		return null;
	}

	class MYListenerO implements MouseListener{                              //添加商品按钮监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
   	     p3.setBorder(new TitledBorder("相关商品"));
    		if(search==0){
    			if(!pnlscanname.getText().equals("")){
    			btnO1.setText("添加");
    			search=1;
    			dlm.clear();
    			vector.add("1");
    			dlm.addElement(vector.get(0));
    			}
    		}
    		else{
    			search=0;    			
    			if(pnlscanname.getText().equals(""))
    				JOptionPane.showMessageDialog(null, "请输入商品", "请输入商品", JOptionPane.ERROR_MESSAGE);
    			else{
    				if(stmp=="")
    				JOptionPane.showMessageDialog(null, "请选择商品", "请请选择商品", JOptionPane.ERROR_MESSAGE);
    			}
    		if(!pnlscanname.getText().equals("")&&stmp!=""){

    		    pnlTest.append("商品名称:"+ stmp+",数量："+pnlscanQ.getText()+"\n");	
    			pnlscanQ.setText("1");
    			pnlscanQ.setEditable(false);
    			btnO1.setText("搜索");
    			pnlscanname.setText("");
    			dlm.clear();
    			vector.clear();
    			stmp="";
    		}
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
	
	class MYListenerO1 implements MouseListener{                                       //添加商品码按钮监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    	     p3.setBorder(new TitledBorder("商品信息"));
    		if(pnlscanid.getText().equals(""))
    			JOptionPane.showMessageDialog(null, "请输入商品码", "请输入商品码", JOptionPane.ERROR_MESSAGE);
    		if(!pnlscanid.getText().equals("")){
    			pnlTest.append("商品码"+pnlscanid.getText()+","+pnlscanQ.getText()+"\n");
    			pnlscanQ.setText("1");
    			pnlscanQ.setEditable(false);
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
 
	
	class MYListenerC implements MouseListener{                                   //取消按钮监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		open=0;
			btnO.setEnabled(false);
			btnO1.setEnabled(false);
			btnCH.setEnabled(false);
			btnC.setEnabled(false);
			btnE.setText("\n创立");
//			pnlTest.append(pnlscanid.getText());
		    pnlscanid.setEditable(false);
		    pnlscanname.setEditable(false);
		    pnlTest.setText("");
		    pnlres.append("取消账单");
			//cancel()                传进去一个取消信号
    		
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
    	     p3.setBorder(new TitledBorder("当前账单"));
 			vector.add("1");
 			dlm.addElement( vector.get(0));
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
		    pnlTest.append("商品名称:"+ stmp+",数量："+pnlscanQ.getText()+"\n");	
			pnlscanQ.setText("1");
			pnlscanQ.setEditable(false);
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
    			btnD.setEnabled(true);
    			btnE.setText("完成");
    		    pnlTest.append("开始创立\n");      		
    		    pnlscanid.setEditable(true);
    		    pnlscanname.setEditable(true);
    		    pnlres.setText("");
    		    
    	   }
    		else{
    			open=0;
    			btnO.setEnabled(false);
    			btnO1.setEnabled(false);
    			btnCH.setEnabled(false);
    			btnA.setEnabled(false);
    			btnD.setEnabled(false);
    			btnE.setText("创立");
//    			pnlTest.append(pnlscanid.getText());
    		    pnlscanid.setEditable(false);
    		    pnlscanname.setEditable(false);
    		    pnlTest.setText("");
    		    pnlscanid.setText("");
    		    pnlscanname.setText("");
    		    pnlscanQ.setText("1");
    		    pnlres.append("录入完成");
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

 }
 
