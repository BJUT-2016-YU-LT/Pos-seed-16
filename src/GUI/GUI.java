package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
	 
	 JPanel pnlDisplayArea=new JPanel(new FlowLayout());          //����ģ������  
     JPanel pnlscan=new JPanel(new FlowLayout());
     
	 JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);             //������ǩҳ
	 JTabbedPane tab1 = new JTabbedPane(JTabbedPane.TOP); 
	 
	 JPanel p1 = new JPanel();                                       //������ǩ
	 JPanel p2 = new JPanel();
	 JPanel p3 = new JPanel(new BorderLayout(5,5));
	 JPanel p4 = new JPanel();
	 
     JTextArea pnlTest=new JTextArea();                              //�����ı���
     JTextArea pnlTest1=new JTextArea(); 
     JTextArea pnlres=new JTextArea();
     JTextField pnlscanid=new JTextField();                              //�����ı���
     JTextField pnlscanname=new JTextField();
     JTextField pnlscanQ=new JTextField("1");
     
    JPanel pnl1=new JPanel(new FlowLayout());   
    JList<String> list1 = null;                                      //�����б��
    
    JLabel Labe4=new JLabel("����");                                  //������ť
    JLabel Labe2=new JLabel("��Ʒ��");
    JLabel Labe3=new JLabel("��Ʒ��");
    JButton btnO=new JButton("���");
    JButton btnO1=new JButton("���");
    JButton btnCH=new JButton("����");
    JButton btnE=new JButton("����");
    JButton btnC=new JButton("ȡ��");
    JButton btnCA=new JButton("ע��");
    JButton btn=new JButton("ע��");
    JButton btnA=new JButton("��ʾ");
    JButton btnD=new JButton("ȷ��");
                    
    ScrollPane jsp=new ScrollPane();                                   //�������
    ScrollPane jsp1=new ScrollPane(); 
    ScrollPane jsp2=new ScrollPane(); 
    ScrollPane jsp3=new ScrollPane(); 
    
    Container contentPane;                                             //������
    
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
  	  super("pos��");                                                                                  //�����ܴ�С
	  setMaximumSize(new Dimension(670,550));
	  setMinimumSize(new Dimension(670,550));
	  setResizable(false);
  	  contentPane=getContentPane();  
  	  contentPane.setLayout(new FlowLayout());      
  	  contentPane.add(pnlscan);
  	  contentPane.add(pnlDisplayArea);
  	  
  	  
  	  list1 = new JList<String>(vector);	   
  	  pnlscan.add(tab);                                                //����
  	  
  	  pnlscan.add(Labe4);
  	  pnlscan.add(pnlscanQ);
  //	  pnlscan.add(btnCH);  	  
  	  pnlscan.add(btnE);
  	  pnlscan.add(btnC);
  	  pnlscan.add(btnCA);
  	  pnlscan.add(jsp3); 
	  int size=20;                                                      //�б������С
	  list1.setFont(new Font("Serif",Font.PLAIN,size));
  	  
  	  p3.add("West", list1);                                            //��ǩҳ���
  	  p4.add(btnA);
  	  p4.add(btnD);
  	  
  	  pnlDisplayArea.add(tab1);                                           
  	  pnlDisplayArea.add(jsp1);
  	  
  	  tab.add(p1,"��Ʒ��");                                              //��ӱ�ǩҳ
  	  tab.add(p2,"��Ʒ��"); 
  	  tab.add(p4,"����");
  	  
  	  tab1.add(jsp,"��Ʒ");                                              
  	  tab1.add(jsp2,"��Ʒ�б��ļ�");
  	  
  	  p1.add(Labe2);                                                    
  	  p1.add(pnlscanid);
  	  p1.add(btnO);
  	  
  	  p2.add(Labe3);
  	  p2.add(pnlscanname);
  	  p2.add(btnO1);
  	  
  	  //Border border=new LineBorder(Color.black);                    //����߽�
  	  //tab.setBorder(border);	
  	  //tab1.setBorder(border);	
  	  //p3.setBorder(border);	
  	  
  	  Dimension d=new Dimension(30,600);                           //���������С
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
  	  
  	  pnlTest.setPreferredSize(new Dimension(270, 1500));               //����ģ���С
  	  pnlTest1.setPreferredSize(new Dimension(270, 1500)); 
  	  pnlres.setPreferredSize(new Dimension(400, 3000));
   
      pnl1.setPreferredSize(new Dimension(450, 200));
  	  pnlscanid.setPreferredSize(new Dimension(230, 30));
  	  pnlscanname.setPreferredSize(new Dimension(230, 30));
  	  pnlscanQ.setPreferredSize(new Dimension(230, 30));
  	  
  	  p3.setBackground(Color.white);                                    //����ģ����ɫ
  	  pnlTest.setBackground(Color.white);
  	  pnlTest1.setBackground(Color.white);
  	  
  	  pnlres.setBackground(Color.white);
  	  pnlscanid.setBackground(Color.white);
  	  pnlscanname.setBackground(Color.white); 	 
  	  pnlscanQ.setBackground(Color.white); 	
      pnl1.setBackground(Color.white);   	  

  	  pnlTest.setEditable(false);         
  	  pnlTest1.setEditable(false);                                     //�����ı��򲻿�д 
  	  pnlres.setEditable(false);
  	  
  	  btnO.setEnabled(false);    
  	  btnO1.setEnabled(false);    
  	  btnCH.setEnabled(false);    
  	  btnE.setEnabled(true);                                           //���ð�ť���ɶ�
  	  btnC.setEnabled(false);
	  btnA.setEnabled(false);
	  btnD.setEnabled(false);
  	  
  	    
  	  pnlres.setBorder(new TitledBorder("bill"));                      //�����ı������
      p3.setBorder(new TitledBorder(""));
  	  
  	  jsp .add(pnlTest);                                                //��ӹ���
  	  jsp.setSize(280,180);
  	  
  	  jsp1 .add(pnlres);
  	  jsp1.setSize(300,230);
  	  
  	  jsp2 .add(pnlTest1);
  	  jsp.setSize(300,180);
  	  
  	  jsp3.add(p3);
  	  jsp3.setSize(300,300);
  	  
  	  pnlscanname.addKeyListener(new KeyAdapter(){
  		  public void keyPressed(KeyEvent e)    
  		  {    
  			  if(e.getKeyChar()==KeyEvent.VK_ENTER )   //���س���ִ����Ӧ����; 
          { 
  				  myclick();
          } 
        } 
  	  });   	  	  
  	  
  	  pnlscanid.addKeyListener(new KeyAdapter(){
  		  public void keyPressed(KeyEvent e)    
  		  {    
  			  if(e.getKeyChar()==KeyEvent.VK_ENTER )   //���س���ִ����Ӧ����; 
          { 
  				  myclockO(); 				  
          } 
        } 
  	  }); 
  	  
   	  btnO.addMouseListener((MouseListener) new MYListenerO1());        //��Ӽ�����
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
      
      pnlscanname.getDocument().addDocumentListener (new OnValueChanged()); 
      
      this.addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e)
          {
        	  try {
				sdbc.logout(userId);
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
       				vector.add(c.getName()+"  ��λ��"+c.getUnit()+"  �۸�"+c.getPrice());
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
       				vector.add(c.getName()+"  ��λ��"+c.getUnit()+"  �۸�"+c.getPrice());
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
        int[] index = list1.getSelectedIndices();                         //����JList�����ṩ��getSelectedIndices()�����ɵõ��û���ѡȡ������ 
        for(int i=0; i < index.length ; i++)                              //indexֵ����Щindexֵ��һ��int array����.
        {
            tmp = index[i];
            stmp =vector.get(tmp)+"  ";
            
        }
        
        commodityIndex=tmp;
        
        
    }
    
    private ActionListener ActionListener() {                               //�ı��������
		if(open==0){
			pnlscanid.setEditable(false);
			pnlscanname.setEditable(false);
		}
		return null;
	}
    
    public void myclick(){
  	     p3.setBorder(new TitledBorder("�����Ʒ"));
 /*  		if(search==0){
   			if(!pnlscanname.getText().equals("")){
   			btnO1.setText("���");
   			search=1;
   			dlm.clear();
   			
   			commodityResult.clear();
   			commodityIndex=0;
   			try {
					commodityResult=cc.searchCommodityByName(pnlscanname.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   			
   			if(commodityResult.size()==0){
   				vector.add("(not found)");
   				dlm.addElement(vector.get(0));
   			}
   			
   			for(Commodity c:commodityResult)
   			{
   				vector.add(c.getName()+"  ��λ��"+c.getUnit()+"  �۸�"+c.getPrice());
   				dlm.addElement(vector.get(vector.size()-1));
   			}
   	//b		vector.add("1");
   	//		dlm.addElement(vector.get(0));
   			}
   		}*/
   			search=0;    			
   			if(pnlscanname.getText().equals(""))
   				JOptionPane.showMessageDialog(null, "��������Ʒ", "��������Ʒ", JOptionPane.ERROR_MESSAGE);
   			else{
   				if(stmp=="")
   				JOptionPane.showMessageDialog(null, "��ѡ����Ʒ", "����ѡ����Ʒ", JOptionPane.ERROR_MESSAGE);
   			}
   		if(!pnlscanname.getText().equals("")&&stmp!=""){

   			
   			try {
					cc.addCommodity(commodityResult.get(commodityIndex).getBarcode(), Integer.parseInt(pnlscanQ.getText()) );
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Wrong", "The num is illegal", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					
					dlm.clear();
		   			vector.clear();
					return;
				}
   			
   			
   			pnlTest.setText("");
   			pnlTest.append(cc.getCommodityListIndex()+'\n');
   		    //pnlTest.append(commodityResult.get(commodityIndex).toString()+"\n");
   		    
   		    pnlTest1.setText("");
   		    pnlTest1.append(cc.getCommodityList()+'\n');
   		    
   		    
   			pnlscanQ.setText("1");
   			pnlscanQ.setEditable(true);
//  			btnO1.setText("����");
   			pnlscanname.setText("");
   			dlm.clear();
   			vector.clear();
   			stmp="";
   		}

    }

	class MYListenerO implements MouseListener{                              //�����Ʒ��ť������
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
	     p3.setBorder(new TitledBorder("��Ʒ��Ϣ"));
		if(pnlscanid.getText().equals(""))
			JOptionPane.showMessageDialog(null, "��������Ʒ��", "��������Ʒ��", JOptionPane.ERROR_MESSAGE);
		if(!pnlscanid.getText().equals("")){
			
			vector.clear();
			dlm.clear();
			
			if(commodityResult!=null)commodityResult.clear();
			commodityIndex=0;
			try {
				commodityResult=cc.searchCommodityByBarcode(pnlscanid.getText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(commodityResult.size()==0){
				vector.add("(not found)");
				dlm.addElement(vector.get(0));
			}else
			{
				boolean status=false;
				try {
					cc.addCommodity(commodityResult.get(0).getBarcode(), Integer.parseInt(pnlscanQ.getText()) );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					status=true;
					JOptionPane.showMessageDialog(null, e.getMessage(), "wrong", JOptionPane.ERROR_MESSAGE);
					
					vector.clear();
					dlm.clear();
					return;
					
				}
				pnlres.setText("");
				pnlres.append("¼�����\n"+cc.getSumList());
				
				vector.add(commodityResult.get(0).getName()+"  ��λ��"+
						commodityResult.get(0).getUnit()+"  �۸�"+
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
   		    
				//pnlTest.append("��Ʒ��"+pnlscanid.getText()+","+pnlscanQ.getText()+"\n");
				pnlscanQ.setText("1");
				pnlscanQ.setEditable(true);
			}	
		}
	}
	
	
	class MYListenerO1 implements MouseListener{                                       //�����Ʒ�밴ť������
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
 
	
	class MYListenerC implements MouseListener{                                   //ȡ����ť������
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		open=0;
			btnO.setEnabled(false);
			btnO1.setEnabled(false);
			btnCH.setEnabled(false);
			btnC.setEnabled(false);
			btnE.setText("\n����");
//			pnlTest.append(pnlscanid.getText());
		    pnlscanid.setEditable(false);
		    pnlscanname.setEditable(false);
		    pnlTest.setText("");
		    pnlTest1.setText("");
		    pnlres.append("ȡ���˵�");
			//cancel()                ����ȥһ��ȡ���ź�
    		
		    cc.cancelBill();
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
	
	class MYListenerA implements MouseListener{                                 //��ʾ���ʵ�������
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    	    p3.setBorder(new TitledBorder("��ǰ�˵�"));
    	     
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
	
	class MYListenerD implements MouseListener{                                      //ȡ��ȷ�ϰ�ť������
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    	     if(stmp=="")
 				JOptionPane.showMessageDialog(null, "��ѡ����Ʒ", "����ѡ����Ʒ", JOptionPane.ERROR_MESSAGE);
		    
    	    //commodityIndex;
    	    try {
				cc.modifyBillInf(commodityIndex, Integer.parseInt(pnlscanQ.getText()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage(), "Wrong", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
    	    pnlres.setText("");
    	    pnlres.append("¼�����\n"+cc.getSumList());
    	    
    	    //pnlTest.append("��Ʒ����:"+ stmp+",������"+pnlscanQ.getText()+"\n");	
    	    
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
	
	
	class MYListenerCA implements MouseListener{                           //ע����ť������
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
    		
    		cc.cancelBill();
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


	
    class MYListenerE implements MouseListener{                                   //����������ť������
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		if(open==0){
    			open=1;
    			btnO.setEnabled(true);
    			btnO1.setEnabled(true);
    			btnCH.setEnabled(true);
    			btnC.setEnabled(true);
    			btnD.setEnabled(true);
    			btnE.setText("���");
    		    pnlTest.append("��ʼ����\n");      		
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
    			btnD.setEnabled(false);
    			btnE.setText("����");
//    			pnlTest.append(pnlscanid.getText());
    		    pnlscanid.setEditable(false);
    		    pnlscanname.setEditable(false);
    		    pnlTest.setText("");
    		    pnlscanid.setText("");
    		    pnlscanname.setText("");
    		    pnlscanQ.setText("1");
    		    pnlres.append("¼�����\n"+cc.getSumList());
    		    pnlTest.setText("");
    		    pnlTest1.setText("");
    		    vector.clear();
    		    dlm.clear();
    		    cc.finishBill();
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
    
    
    
    class MYListenerCH implements MouseListener{                      //�ı����ְ�ť������
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
 }
 
