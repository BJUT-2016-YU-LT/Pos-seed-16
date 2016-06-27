package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ItemSelectable;
import java.awt.ScrollPane;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class GUI {  
	public static void main(String[] args) {  
       // new NewFrame("");
        NewFrame f=new NewFrame("");
        f.setLocationRelativeTo(null);   
    }  
}
  



 class NewFrame extends JFrame{  
  

    private static final long serialVersionUID = 1L;
	private static final String Graphics = null;  

	
    JPanel pnlDisplayArea=new JPanel(new FlowLayout());                //定义模板区域
    JPanel pnlCommandArea=new JPanel(new FlowLayout());
    JPanel pnlscan=new JPanel(new FlowLayout());
    JPanel pnl1=new JPanel(new FlowLayout());
    
   
    
    JTextArea pnlTest=new JTextArea();                        //定义文本区域
    JTextArea pnlres=new JTextArea();


    
    JLabel Label=new JLabel("weclome");                        //定义标签机按钮
    JLabel Labe2=new JLabel("商品码");
    JLabel Labe3=new JLabel("商品名");
    JButton btnO=new JButton("创建");
    JButton btnE=new JButton("结束");
    JButton btnC=new JButton("取消");
    
    JButton btnerror=new JButton("error");
    
    ScrollPane jsp=new ScrollPane();                    //定义滚条
    ScrollPane jsp1=new ScrollPane(); 

    JTextField pnlscanid=new JTextField();                     //定义文本框
    JTextField pnlscanname=new JTextField();
   
//    private ImageIcon background = new ImageIcon("11.jpg");

    Container contentPane;
    
    int open=0;
    
    NewFrame(String title) {  
  	  super("pos机");                                       
  	  this.setSize(900,600);                                            //设置总大小

  	  contentPane=getContentPane();  
  	  contentPane.setLayout(new FlowLayout());                               //总布局

  	  Dimension d=new Dimension(30,600);                                     //设置区域大小
  	  pnlCommandArea.setPreferredSize(d);
  	  d=new Dimension(500,600);
  	  pnlscan.setPreferredSize(d);
  	  d=new Dimension(230,600);    
  	  pnlDisplayArea.setPreferredSize(d);
  	  
  	  pnlTest.setPreferredSize(new Dimension(150, 1500));               //设置模块大小
  	  pnlres.setPreferredSize(new Dimension(150, 3000));
   
      pnl1.setPreferredSize(new Dimension(450, 200));
  	  pnlscanid.setPreferredSize(new Dimension(400, 30));
  	  pnlscanname.setPreferredSize(new Dimension(400, 30));
  	  
  	  Border border=new LineBorder(Color.black);                        //设置区域颜色
  	  pnlscan.setBorder(border);
  	  pnlCommandArea.setBorder(border);
  	  pnlDisplayArea.setBorder(border);
  	  pnlDisplayArea.setBackground(Color.white);
  	  pnlscan.setBackground(Color.white);
  	  pnlCommandArea.setBackground(Color.yellow); 
  	  pnlTest.setBackground(Color.white);
  	  pnlres.setBackground(Color.white);
  	  pnlscanid.setBackground(Color.white);
  	  pnlscanname.setBackground(Color.white); 	  
      pnl1.setBackground(Color.white);   	  
      Label.setBackground(new Color(0x6fffff));
      Label.setForeground(Color.blue);

  	  pnlTest.setEditable(false);                                  //设置文本框不可写
  	  pnlres.setEditable(false);
  	    	
  	  btnE.setEnabled(false);                                      //设置按钮不可读
  	  btnC.setEnabled(false);
  	  
  	  
  	  pnlTest.setBorder(new TitledBorder("display"));                 //设置文本框标题
  	  pnlres.setBorder(new TitledBorder("bill"));
  	  
  	  contentPane.add(pnlCommandArea);                                //在区域内添加模块
  	  contentPane.add(pnlscan);
 	  contentPane.add(pnlDisplayArea);

  	  pnl1.add(Label);                                                 //添加标签

  	  pnlDisplayArea.add( pnlTest );
  	  pnlDisplayArea.add( pnlres );
  	  
  	  jsp .add(pnlTest);                                                    //添加滚条
  	  pnlDisplayArea.add(jsp); 
  	  jsp.setSize(200,250);
  	  
  	  jsp1 .add(pnlres);
  	  pnlDisplayArea.add(jsp1);
  	  jsp1.setSize(200,330);
  

  //	  pnlDisplayArea.setVisible(true);
  	     
  	  pnlCommandArea.add(btnerror);                             //添加按钮
  	  pnlscan.add(pnl1);
  	  pnlscan.add(pnlscanid);
  	  pnlscan.add(Labe2);
  	  pnlscan.add(btnO);
  	  pnlscan.add(btnE);
  	  pnlscan.add(btnC);
  	  
  	  
   	  btnO.addMouseListener((MouseListener) new MYListenerO1());            //添加监视器
   	  btnE.addMouseListener((MouseListener) new MYListenerE());
   	  btnC.addMouseListener((MouseListener) new MYListenerC());
   	  btnerror.addMouseListener((MouseListener) new MYListenerError());
   	  
   	  pnlscanid.addActionListener(ActionListener());


      
      
  	 setVisible(true);
    
    }
    
    private ActionListener ActionListener() {                               //文本框监视器
		if(open==0){
			pnlscanid.setEditable(false);
		}
		return null;
	}

	class MYListenerO1 implements MouseListener{                                    //添加按钮监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		if(open==0){
    			pnlres.setText("");
    			open=1;
    			pnlscanid.setEditable(true);
    			btnE.setEnabled(true);
    			btnC.setEnabled(true);
    			btnO.setText("添加");
    		    pnlTest.append("选择 了创立\n");  
    		    //START();                给一个开始信号
    		     
    	    }
    		if(open==1){
    		//	message=search(pnlscanid.getText());   传进去商品码，传出用字符串表示的商品信息
    		//	pnlTest.append(message+"\n");
    			pnlTest.append(pnlscanid.getText()+"\n");
    			pnlscanid.setText("");    			
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
 
	
	class MYListenerC implements MouseListener{                           //取消按钮监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		btnE.setEnabled(true);
			btnE.setEnabled(false);
			btnO.setEnabled(true);
			btnO.setText("创立");
			pnlTest.append("选择 了结束\n");  
			pnlscanid.setEditable(false);
			pnlTest.append(pnlscanid.getText());
			pnlTest.setText("");
			pnlscanid.setText(""); 	
			open=0;
			btnC.setEnabled(false);
			
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
	

	class MYListenerError implements MouseListener{                         //error按钮监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		  pnlscan.add(pnlscanname);
    		  pnlscan.add(Labe3);
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
 
	
    class MYListenerE implements MouseListener{                      //结束按钮监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		if(open==1){
    			open=0;
    			btnE.setEnabled(true);
    			btnE.setEnabled(false);
    			btnO.setEnabled(true);
    			btnO.setText("创立");
    		pnlTest.append("选择 了结束\n");  
    		pnlscanid.setEditable(false);
    		pnlTest.append(pnlscanid.getText());
    		pnlTest.setText("");
    		pnlscanid.setText("");
    		
    		//res=result(结束)；                                  传进去一个结束信号，传出用字符串表示的商品总码
    		//pnlres.append(res);
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
    

 }
 
