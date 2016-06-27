<<<<<<< HEAD
package GUI;

=======
>>>>>>> da5390a3df01c39cf7d7cc2d4d60799fb6676934
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

<<<<<<< HEAD
import control.StaffDBControl;

=======
>>>>>>> da5390a3df01c39cf7d7cc2d4d60799fb6676934
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
    
    JRadioButton rdo=new JRadioButton("记住我");
    
    JTextField pnlscanid=new JTextField();     
    JPasswordField password=new JPasswordField();
    
    int check=0;
    int re=1;
    
    ImageIcon background; 
    
<<<<<<< HEAD
    StaffDBControl sdbc;
    int userId=0;
    
=======
>>>>>>> da5390a3df01c39cf7d7cc2d4d60799fb6676934
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
	  	int size=50;
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
<<<<<<< HEAD
	  	
	  	try{
	  		sdbc=new StaffDBControl();
	  	}catch(Exception e){
	  		e.printStackTrace();
	  	}
=======
>>>>>>> da5390a3df01c39cf7d7cc2d4d60799fb6676934
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
<<<<<<< HEAD
    			
    			try {
					userId=sdbc.login(pnlscanid.getText(), password.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(userId);
					JOptionPane.showMessageDialog(null, e.getMessage(), "Wrong", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					return;
				}
    			
    			//Connection con = new Connection(pnlscanid.getText(),password.getText(),check);
    			//check=con.newconnect(pnlscanid.getText(),password.getText());
    		
    			if(rdo.isSelected()){re=1;}
    			else re=0;
    			save.saveFile(re);save.savename(pnlscanid.getText());save.savepassword(password.getText());
    			 NewFrame f=new NewFrame(userId);
    	         f.setLocationRelativeTo(null); 
    	         
    	         dispose();
    		
=======
    			Connection con = new Connection(pnlscanid.getText(),password.getText(),check);
    			check=con.newconnect(pnlscanid.getText(),password.getText());
    		if(check==0){
    			JOptionPane.showMessageDialog(null, "The User Name Error", "The User Name Error", JOptionPane.ERROR_MESSAGE);
    			pnlscanid.setText("");password.setText("");
    		}		
    		if(check==1){
    			JOptionPane.showMessageDialog(null, "The password Error", "The password Error", JOptionPane.ERROR_MESSAGE); 
    			password.setText("");
    		}
    		if(check==2){
    			if(rdo.isSelected()){re=1;}
    			else re=0;
    			save.saveFile(re);save.savename(pnlscanid.getText());save.savepassword(password.getText());
    			 NewFrame f=new NewFrame("");
    	         f.setLocationRelativeTo(null);   
    		}
>>>>>>> da5390a3df01c39cf7d7cc2d4d60799fb6676934

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
