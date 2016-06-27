package GUI;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public class save {
	save(){}	
	public static void savename(String name) {
		try {
			
            FileWriter saveFile=new FileWriter("name.txt", false); 
            PrintWriter savedOut=new PrintWriter(saveFile); 
            savedOut.print(name); 
            savedOut.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void savepassword(String password) {
		try {
			
            FileWriter saveFile=new FileWriter("password.txt", false); 
            PrintWriter savedOut=new PrintWriter(saveFile); 
            savedOut.print(password); 
            savedOut.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveFile(int re) {
		try {
			
            FileWriter saveFile=new FileWriter("re.txt", false); 
            PrintWriter savedOut=new PrintWriter(saveFile); 
            savedOut.print(re); 
            savedOut.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String openFilen(String name) {
		FileReader fis;	
		try { 
			fis = new FileReader("name.txt");
			BufferedReader br = new BufferedReader (fis);
			try {
				while (fis.ready()) {
				name=br.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	
	public static String openFilep(String password) {
		FileReader fis;		
		try { 
			fis = new FileReader("password.txt");
			BufferedReader br = new BufferedReader (fis);
			try {
				while (fis.ready()) {
				password=br.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return password;
	}


public static int openFile() {
	FileReader fis;int re =1;
	try {
		fis = new FileReader("re.txt");
		try {
			while (fis.ready()) {
					re=fis.read();	
				//	re=(char) Integer.parseInt();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
	return re;
}
}
