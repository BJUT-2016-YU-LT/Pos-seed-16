package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import control.StaffDBControl;

public class Loginscreen extends JFrame{
	private static final long serialVersionUID = 1L; 
    Container contentPane;
    JPanel p1=new JPanel(); 
    JPanel p2=new JPanel(); 
    JPanel p3=new JPanel(); 
    
    JLabel Labe=new JLabel("欢迎来到登陆界面");
    JLabel Labe1=new JLabel("账户");
    JLabel Labe2=new JLabel("密码");
    JLabel Labe3=new JLabel("不是自己的电脑不要勾选此项");
    
    JButton btnN=new JButton("登陆");
    JButton btnC=new JButton("清空");
    JButton btnE=new JButton("退出");
    
    JCheckBox rdo=new JCheckBox("记住我");
    
    JTextField pnlscanid=new JTextField();     
    JPasswordField password=new JPasswordField();
    
    int check=0;
    int re=1;
    
    ImageIcon background; 
    
    StaffDBControl sdbc;
    int userId=0;
    
	Loginscreen(String title){
		super("登陆界面");                                       
	  	this.setSize(500,300);                                            //设置总大小

	  	contentPane=getContentPane();  
	  	contentPane.setLayout(new FlowLayout());   
	  	contentPane.add(Labe);
	  	contentPane.add(p1);
	  	contentPane.add(p2);
	  	contentPane.add(p3);
	  	contentPane.add(btnN);
	  	contentPane.add(btnC);
	  	contentPane.add(btnE);
	  	
	  	p1.add(Labe1);
	  	p1.add(pnlscanid);
	  	p2.add(Labe2);
	  	p2.add(password);
	  	p3.add(rdo);
	  	p3.add(Labe3);
/*	  	int size=50;
	  	Labe.setFont(new Font("Serif",Font.PLAIN,size));
	  	Labe.setForeground(Color.blue);  	  		  	  
	  	Dimension d=new Dimension(30,600);          
	  	  //设置区域大小
	  	d=new Dimension(450,40);
	  	p1.setPreferredSize(d);
	  	p2.setPreferredSize(d);
	  	d=new Dimension(450,40);
	  	p3.setPreferredSize(d);
	  	
	  	d=new Dimension(250,30);
	  	password.setPreferredSize(d);
	  	pnlscanid.setPreferredSize(d);
	  	*/
	  	
	  	btnN.addMouseListener((MouseListener) new MYListenerN());    
	  	btnC.addMouseListener((MouseListener) new MYListenerC());    
	  	btnE.addMouseListener((MouseListener) new MYListenerE());    

	  	rdo.addItemListener(new MYListenersup());
	  	
	    save op=null;
	    re=op.openFile();
	    if(re==49){
	    	re=1;
	    	rdo.setSelected(true);
	    }	    
	    else if(re==48){
	    	re=0;
	    	 rdo.setSelected(false);
	    }
	  	setVisible(true);
	  	
	  	try{
	  		sdbc=new StaffDBControl();
	  	}catch(Exception e){
	  		e.printStackTrace();
	  	}
	}
	
	public void paint(Graphics g)
    {
        //窗口改变大小时，textarea1也跟着改变大小
		super.paint(g);
		int size1=this.getHeight()/6;
	  	Labe.setFont(new Font("华文新魏",Font.PLAIN,size1));
	  	Labe.setForeground(Color.blue);  	  
	  	Labe1.setFont(new Font("Serif",Font.PLAIN,size1/4));
	  	Labe1.setForeground(Color.black);  	
	  	Labe2.setFont(new Font("Serif",Font.PLAIN,size1/4));
	  	Labe2.setForeground(Color.black); 
	  	btnN.setFont(new Font("宋体" , Font.BOLD,size1/3));
	  	btnN.setForeground(Color.black); 
	  	btnC.setFont(new Font("宋体" , Font.BOLD,size1/3));
	  	btnC.setForeground(Color.black); 
	  	btnE.setFont(new Font("宋体" , Font.BOLD,size1/3));
	  	btnE.setForeground(Color.black); 
	  	rdo.setFont(new Font("宋体" , Font.PLAIN,size1/3));
	  	rdo.setForeground(Color.black); 
	  	Labe3.setFont(new Font("宋体" , Font.PLAIN,size1/3));
	  	Labe3.setForeground(Color.black); 
	  	pnlscanid.setFont(new Font("宋体" , Font.PLAIN,size1/3));
	  	pnlscanid.setForeground(Color.black); 
	  	password.setFont(new Font("宋体" , Font.PLAIN,size1/3));
	  	password.setForeground(Color.black); 
	  	Dimension d=new Dimension(30,600);          
	  	  //设置区域大小
	//  	d=new Dimension(450,40);
	  	d=new Dimension(this.getWidth()-10,this.getHeight()/7);
	  	p1.setPreferredSize(d);
	  	p2.setPreferredSize(d);
//	  	d=new Dimension(450,40); 
	  	p3.setPreferredSize(d);
	  	
//	  	d=new Dimension(250,30);
	  	d=new Dimension(this.getWidth()*2/3,this.getHeight()/8);	  	
	  	password.setPreferredSize(d);
	  	pnlscanid.setPreferredSize(d);
	  	d=new Dimension(this.getWidth()/10,this.getHeight()/7);	  	
	  	Labe1.setSize(d);
	  	Labe2.setSize(d);
	  	
    }
	
	class MYListenerN implements MouseListener{                                    //添加按钮监视器
    	@SuppressWarnings("deprecation")
		@Override
    	public void mouseClicked(MouseEvent arg0) {
    		if(pnlscanid.getText().equals(""))
    			JOptionPane.showMessageDialog(null, "The User Name Empty", "The User Name Empty", JOptionPane.ERROR_MESSAGE);
    		else if(password.getText().equals(""))
    			JOptionPane.showMessageDialog(null, "The password Empty", "The password Empty", JOptionPane.ERROR_MESSAGE);
    		else{
    			
    			try {
					userId=sdbc.login(pnlscanid.getText(), password.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(userId);
					JOptionPane.showMessageDialog(null, e.getMessage(), "Wrong", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					return;
				}
    		
    			if(rdo.isSelected()){re=1;}
    			else re=0;
    			save.saveFile(re);save.savename(pnlscanid.getText());save.savepassword(password.getText());
    			 NewFrame f=new NewFrame(userId);
    	         f.setLocationRelativeTo(null); 
    	         
    	         dispose();
    		

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
	
	class MYListenerC implements MouseListener{                                    //添加按钮监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		Loginscreen L=new Loginscreen("");
   		    L.setLocationRelativeTo(null);  
    		pnlscanid.setText("");
    		password.setText("");
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
	
	class MYListenerE implements MouseListener{                                    //添加按钮监视器
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		System.exit(0);
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
	
    class MYListenersup implements ItemListener{
   	   public void itemStateChanged(ItemEvent e){
  		  String n = null,p = null;
   		  n=save.openFilen(n);
   		  p=save.openFilep(p);
   		  pnlscanid.setText(n);
  		  password.setText(p);
   		  }
      }
}
