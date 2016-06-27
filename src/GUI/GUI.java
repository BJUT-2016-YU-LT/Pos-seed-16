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
    JButton btnO1=new JButton("����");
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
    
    Vector<String> vector = new Vector<String>();
    DefaultListModel dlm = new DefaultListModel();                   
    int open=0;
    int search=0;
    String stmp = "";
    
    NewFrame(String title) {  
  	  super("pos��");                                       
  	  this.setSize(670,550);                                            //�����ܴ�С

  	  contentPane=getContentPane();  
  	  contentPane.setLayout(new FlowLayout());      
  	  contentPane.add(pnlscan);
  	  contentPane.add(pnlDisplayArea);
  	  
  	  
  	  list1 = new JList<String>(vector);	   
  	  pnlscan.add(tab);                                                //����
  	  pnlscan.add(jsp3); 
  	  pnlscan.add(Labe4);
  	  pnlscan.add(pnlscanQ);
  	  pnlscan.add(btnCH);  	  
  	  pnlscan.add(btnE);
  	  pnlscan.add(btnC);
  	  pnlscan.add(btnCA);
  	  
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
  	  
  	  Border border=new LineBorder(Color.black);                    //����߽�
  	  tab.setBorder(border);	
  	  tab1.setBorder(border);	
  	  p3.setBorder(border);	
  	  
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
  	  pnlscanQ.setPreferredSize(new Dimension(199, 30));
  	  
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
  	  setVisible(true);
  	  
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
    }
    
    private ActionListener ActionListener() {                               //�ı��������
		if(open==0){
			pnlscanid.setEditable(false);
			pnlscanname.setEditable(false);
			pnlscanQ.setEditable(false);
		}
		return null;
	}

	class MYListenerO implements MouseListener{                              //�����Ʒ��ť������
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
   	     p3.setBorder(new TitledBorder("�����Ʒ"));
    		if(search==0){
    			if(!pnlscanname.getText().equals("")){
    			btnO1.setText("���");
    			search=1;
    			dlm.clear();
    			vector.add("1");
    			dlm.addElement(vector.get(0));
    			}
    		}
    		else{
    			search=0;    			
    			if(pnlscanname.getText().equals(""))
    				JOptionPane.showMessageDialog(null, "��������Ʒ", "��������Ʒ", JOptionPane.ERROR_MESSAGE);
    			else{
    				if(stmp=="")
    				JOptionPane.showMessageDialog(null, "��ѡ����Ʒ", "����ѡ����Ʒ", JOptionPane.ERROR_MESSAGE);
    			}
    		if(!pnlscanname.getText().equals("")&&stmp!=""){

    		    pnlTest.append("��Ʒ����:"+ stmp+",������"+pnlscanQ.getText()+"\n");	
    			pnlscanQ.setText("1");
    			pnlscanQ.setEditable(false);
    			btnO1.setText("����");
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
	
	class MYListenerO1 implements MouseListener{                                       //�����Ʒ�밴ť������
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    	     p3.setBorder(new TitledBorder("��Ʒ��Ϣ"));
    		if(pnlscanid.getText().equals(""))
    			JOptionPane.showMessageDialog(null, "��������Ʒ��", "��������Ʒ��", JOptionPane.ERROR_MESSAGE);
    		if(!pnlscanid.getText().equals("")){
    			pnlTest.append("��Ʒ��"+pnlscanid.getText()+","+pnlscanQ.getText()+"\n");
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
		    pnlres.append("ȡ���˵�");
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
	
	class MYListenerA implements MouseListener{                                 //��ʾ���ʵ�������
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    	     p3.setBorder(new TitledBorder("��ǰ�˵�"));
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
	
	class MYListenerD implements MouseListener{                                      //ȡ��ȷ�ϰ�ť������
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    	     if(stmp=="")
 				JOptionPane.showMessageDialog(null, "��ѡ����Ʒ", "����ѡ����Ʒ", JOptionPane.ERROR_MESSAGE);
		    pnlTest.append("��Ʒ����:"+ stmp+",������"+pnlscanQ.getText()+"\n");	
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
	
	
	class MYListenerCA implements MouseListener{                           //ע����ť������
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


	
    class MYListenerE implements MouseListener{                                   //����������ť������
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
    			btnE.setText("���");
    		    pnlTest.append("��ʼ����\n");      		
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
    			btnE.setText("����");
//    			pnlTest.append(pnlscanid.getText());
    		    pnlscanid.setEditable(false);
    		    pnlscanname.setEditable(false);
    		    pnlTest.setText("");
    		    pnlscanid.setText("");
    		    pnlscanname.setText("");
    		    pnlscanQ.setText("1");
    		    pnlres.append("¼�����");
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

 }
 
