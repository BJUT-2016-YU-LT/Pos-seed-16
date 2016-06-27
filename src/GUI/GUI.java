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

	
    JPanel pnlDisplayArea=new JPanel(new FlowLayout());                //����ģ������
    JPanel pnlCommandArea=new JPanel(new FlowLayout());
    JPanel pnlscan=new JPanel(new FlowLayout());
    JPanel pnl1=new JPanel(new FlowLayout());
    
   
    
    JTextArea pnlTest=new JTextArea();                        //�����ı�����
    JTextArea pnlres=new JTextArea();


    
    JLabel Label=new JLabel("weclome");                        //�����ǩ����ť
    JLabel Labe2=new JLabel("��Ʒ��");
    JLabel Labe3=new JLabel("��Ʒ��");
    JButton btnO=new JButton("����");
    JButton btnE=new JButton("����");
    JButton btnC=new JButton("ȡ��");
    
    JButton btnerror=new JButton("error");
    
    ScrollPane jsp=new ScrollPane();                    //�������
    ScrollPane jsp1=new ScrollPane(); 

    JTextField pnlscanid=new JTextField();                     //�����ı���
    JTextField pnlscanname=new JTextField();
   
//    private ImageIcon background = new ImageIcon("11.jpg");

    Container contentPane;
    
    int open=0;
    
    NewFrame(String title) {  
  	  super("pos��");                                       
  	  this.setSize(900,600);                                            //�����ܴ�С

  	  contentPane=getContentPane();  
  	  contentPane.setLayout(new FlowLayout());                               //�ܲ���

  	  Dimension d=new Dimension(30,600);                                     //���������С
  	  pnlCommandArea.setPreferredSize(d);
  	  d=new Dimension(500,600);
  	  pnlscan.setPreferredSize(d);
  	  d=new Dimension(230,600);    
  	  pnlDisplayArea.setPreferredSize(d);
  	  
  	  pnlTest.setPreferredSize(new Dimension(150, 1500));               //����ģ���С
  	  pnlres.setPreferredSize(new Dimension(150, 3000));
   
      pnl1.setPreferredSize(new Dimension(450, 200));
  	  pnlscanid.setPreferredSize(new Dimension(400, 30));
  	  pnlscanname.setPreferredSize(new Dimension(400, 30));
  	  
  	  Border border=new LineBorder(Color.black);                        //����������ɫ
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

  	  pnlTest.setEditable(false);                                  //�����ı��򲻿�д
  	  pnlres.setEditable(false);
  	    	
  	  btnE.setEnabled(false);                                      //���ð�ť���ɶ�
  	  btnC.setEnabled(false);
  	  
  	  
  	  pnlTest.setBorder(new TitledBorder("display"));                 //�����ı������
  	  pnlres.setBorder(new TitledBorder("bill"));
  	  
  	  contentPane.add(pnlCommandArea);                                //�����������ģ��
  	  contentPane.add(pnlscan);
 	  contentPane.add(pnlDisplayArea);

  	  pnl1.add(Label);                                                 //��ӱ�ǩ

  	  pnlDisplayArea.add( pnlTest );
  	  pnlDisplayArea.add( pnlres );
  	  
  	  jsp .add(pnlTest);                                                    //��ӹ���
  	  pnlDisplayArea.add(jsp); 
  	  jsp.setSize(200,250);
  	  
  	  jsp1 .add(pnlres);
  	  pnlDisplayArea.add(jsp1);
  	  jsp1.setSize(200,330);
  

  //	  pnlDisplayArea.setVisible(true);
  	     
  	  pnlCommandArea.add(btnerror);                             //��Ӱ�ť
  	  pnlscan.add(pnl1);
  	  pnlscan.add(pnlscanid);
  	  pnlscan.add(Labe2);
  	  pnlscan.add(btnO);
  	  pnlscan.add(btnE);
  	  pnlscan.add(btnC);
  	  
  	  
   	  btnO.addMouseListener((MouseListener) new MYListenerO1());            //��Ӽ�����
   	  btnE.addMouseListener((MouseListener) new MYListenerE());
   	  btnC.addMouseListener((MouseListener) new MYListenerC());
   	  btnerror.addMouseListener((MouseListener) new MYListenerError());
   	  
   	  pnlscanid.addActionListener(ActionListener());


      
      
  	 setVisible(true);
    
    }
    
    private ActionListener ActionListener() {                               //�ı��������
		if(open==0){
			pnlscanid.setEditable(false);
		}
		return null;
	}

	class MYListenerO1 implements MouseListener{                                    //��Ӱ�ť������
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		if(open==0){
    			pnlres.setText("");
    			open=1;
    			pnlscanid.setEditable(true);
    			btnE.setEnabled(true);
    			btnC.setEnabled(true);
    			btnO.setText("���");
    		    pnlTest.append("ѡ�� �˴���\n");  
    		    //START();                ��һ����ʼ�ź�
    		     
    	    }
    		if(open==1){
    		//	message=search(pnlscanid.getText());   ����ȥ��Ʒ�룬�������ַ�����ʾ����Ʒ��Ϣ
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
 
	
	class MYListenerC implements MouseListener{                           //ȡ����ť������
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		btnE.setEnabled(true);
			btnE.setEnabled(false);
			btnO.setEnabled(true);
			btnO.setText("����");
			pnlTest.append("ѡ�� �˽���\n");  
			pnlscanid.setEditable(false);
			pnlTest.append(pnlscanid.getText());
			pnlTest.setText("");
			pnlscanid.setText(""); 	
			open=0;
			btnC.setEnabled(false);
			
			//cancel()                ����ȥһ��ȡ���ź�
    		
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
	

	class MYListenerError implements MouseListener{                         //error��ť������
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
 
	
    class MYListenerE implements MouseListener{                      //������ť������
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		if(open==1){
    			open=0;
    			btnE.setEnabled(true);
    			btnE.setEnabled(false);
    			btnO.setEnabled(true);
    			btnO.setText("����");
    		pnlTest.append("ѡ�� �˽���\n");  
    		pnlscanid.setEditable(false);
    		pnlTest.append(pnlscanid.getText());
    		pnlTest.setText("");
    		pnlscanid.setText("");
    		
    		//res=result(����)��                                  ����ȥһ�������źţ��������ַ�����ʾ����Ʒ����
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
 
