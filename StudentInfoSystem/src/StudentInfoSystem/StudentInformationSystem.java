package StudentInfoSystem;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class StudentInformationSystem extends JFrame implements ActionListener{
	JMenu studentinfo,transcation,help;
	JMenuBar menuBar;
	JMenuItem create_acc,del_acc,quitItem,searcEntNo,searname,update,about;
	public StudentInformationSystem() {
		studentinfo=new JMenu("Student Information");
		studentinfo.setMnemonic(KeyEvent.VK_R);
		create_acc=new JMenuItem("Registration");
		studentinfo.add(create_acc);
		create_acc.addActionListener(this);
		del_acc=new JMenuItem("Deletion");
		del_acc.setMnemonic(KeyEvent.VK_D);
		studentinfo.add(del_acc);
		del_acc.addActionListener(this);
		studentinfo.addSeparator();
		quitItem = new JMenuItem("Exit");
		quitItem.setMnemonic(KeyEvent.VK_E);
		quitItem.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK));
				quitItem.addActionListener(this);
				studentinfo.add(quitItem);	
		transcation=new JMenu("Transcation");
		transcation.setMnemonic(KeyEvent.VK_T);	
		searcEntNo=new JMenuItem("Search By Student Entrance No.");
		transcation.add(searcEntNo);
		searcEntNo.setMnemonic(KeyEvent.VK_N);
		searcEntNo.addActionListener(this);
		searname=new JMenuItem("Search By Student Name");
		transcation.add(searname);
		searname.setMnemonic(KeyEvent.VK_S);
		searname.addActionListener(this);
		transcation.addSeparator();
		update=new JMenuItem("Update Student Information");
		transcation.add(update);
		update.setMnemonic(KeyEvent.VK_U);
		update.addActionListener(this);
				
		help=new JMenu("Help");	
		help.setMnemonic(KeyEvent.VK_H);		
		about=new JMenuItem("About The System");
		help.add(about);
		about.setMnemonic(KeyEvent.VK_A);	
			
		menuBar = new JMenuBar();
		menuBar.add(studentinfo);
		menuBar.add(transcation);
		menuBar.add(help);
		setJMenuBar(menuBar);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		setSize(getMaximumSize());
		setVisible(true);
	}

	public static void main(String[] args) {
		new StudentInformationSystem();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==quitItem)
		    System.exit(0);
		if(e.getSource()==create_acc){
			new StudentRegistration();
		}
		if(e.getSource()==del_acc){
			new StudentDelete();
		}
		if(e.getSource()==update){
			new StudentUpdate();
		}
		if(e.getSource()==searcEntNo){
			new StudentSearchByEntranceNo();
		}
		if(e.getSource()==searname){
			new SearchByName();
		}
	}

}
