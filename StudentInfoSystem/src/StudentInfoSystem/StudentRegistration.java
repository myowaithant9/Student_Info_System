package StudentInfoSystem;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;

public class StudentRegistration extends JFrame implements ActionListener{

	JTextField entranceNo = new JTextField(20);
	JTextField rollNo = new JTextField(20);
	JTextField studentName = new JTextField(20);
	JTextField fatherName = new JTextField(20);
	JTextField address = new JTextField(20);
	
	JRadioButton male = new JRadioButton("Male",true);
	JRadioButton female = new JRadioButton("Female");
	
	ButtonGroup bg = new ButtonGroup();
	
	JComboBox major;
	JComboBox classYear;
	
	JList subject;
	
	JButton register = new JButton("Register");
	JButton clear = new JButton("Clear");
	JButton cancel = new JButton("Cancel");
	
	JPanel compo,radio,p,button,mainPanel;
	
	int EntranceNo = 0;
	String gender;
	
	public StudentRegistration()
	{
		super("Student Information");
	
		getEntranceNo(); 
		
		entranceNo.setText(String.valueOf(this.EntranceNo));
		
		String[] year = {"First Year","Second Year","Third Year","Fourth Year","Fifth Year"};
		String[] subj = {"Embedded System","Networking","Knowledge Engineering","Business","Software Engineering","High Performance Computing"};
		String[] majorArray = {"Computer Science","Computer Techniques"};
		
		classYear = new JComboBox(year);
		subject = new JList(subj);
		major = new JComboBox(majorArray);
		
		subject.setVisibleRowCount(3);
		
		bg.add(male);
		bg.add(female);
		
		radio = new JPanel(new GridLayout(1,2));
		radio.add(male);
		radio.add(female);
		
		compo = new JPanel(new GridLayout(9,2));
		compo.add(new JLabel("Entrance No."));
		compo.add(entranceNo);
		compo.add(new JLabel("Roll No."));
		compo.add(rollNo);
		compo.add(new JLabel("Student Name"));
		compo.add(studentName);
		compo.add(new JLabel("Gender"));
		compo.add(radio);
		compo.add(new JLabel("Father Name"));
		compo.add(fatherName);
		compo.add(new JLabel("Address"));
		compo.add(address);
		compo.add(new JLabel("Class Year"));
		compo.add(classYear);
		compo.add(new JLabel("Major"));
		compo.add(major);
		
		p = new JPanel(new GridLayout(1,2));
		p.add(new JLabel("Specialized Subjects"));
		p.add(new JScrollPane(subject));
		
		button = new JPanel(new GridLayout(1,3));
		button.add(register);
		button.add(clear);
		button.add(cancel);
		
		mainPanel = new JPanel(new FlowLayout());
		mainPanel.add(compo);
		mainPanel.add(p);
		mainPanel.add(button);
		
		add(mainPanel);
		setVisible(true);
		setSize(500,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		male.addActionListener(this);
		female.addActionListener(this);
		register.addActionListener(this);
		clear.addActionListener(this);
		cancel.addActionListener(this);
	}
	
	public void getEntranceNo()
	{
		try
		{
			int ent = 0;
			Connection con = new DBConnection().getDBConnection("studentdb");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select EntranceNo From studentInfo");
			while(rs.next())
			{
				ent++;
			}
			this.EntranceNo = ent + 1;
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == male)
		{
			gender = male.getLabel();
		}
		else if(e.getSource() == female)
		{
			gender = female.getLabel();
		}
		if(e.getSource() == register)
		{
			register();
		}
		else if(e.getSource() == clear)
		{
			entranceNo.setText("");
			rollNo.setText("");
			studentName.setText("");
			fatherName.setText("");
			male.setSelected(true);
			fatherName.setText("");
			address.setText("");
			classYear.setSelectedIndex(0);
			major.setSelectedIndex(0);
			subject.setSelectedIndex(0);
		}
		else if(e.getSource() == cancel)
		{
			int i = JOptionPane.showConfirmDialog(null,"Are you sure to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION)
			{
				setVisible(false);
			}
		}
	}
	public void register()
	{
		try
		{	
			String query = "Insert Into studentInfo Values('"
					+ Integer.parseInt(entranceNo.getText()) + "','" + Integer.parseInt(rollNo.getText()) + "','"
					+ studentName.getText() + "', '" + gender + "' , '"
					+ fatherName.getText() + "' , '" + address.getText()
					+ "' , '" + classYear.getSelectedItem() + "' , '"
					+ major.getSelectedItem() + "' , '"
					+ subject.getSelectedValue() + "')";
			Connection con = new DBConnection().getDBConnection("studentdb");
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null, "Student Registration is Successful!","Student Information", JOptionPane.NO_OPTION);
			entranceNo.setText("");
			rollNo.setText("");
			studentName.setText("");
			fatherName.setText("");
			male.setSelected(true);
			fatherName.setText("");
			address.setText("");
			classYear.setSelectedIndex(0);
			major.setSelectedIndex(0);
			subject.setSelectedIndex(0);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e ,"Error", JOptionPane.NO_OPTION);
		}
	}
	public static void main(String args[])
	{
		new StudentRegistration();
	}
}
