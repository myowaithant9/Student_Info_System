package StudentInfoSystem;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;

public class StudentUpdate extends JFrame implements ActionListener,FocusListener{

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
	JComboBox subject;
	
	JButton update = new JButton("Update");
	JButton clear = new JButton("Clear");
	JButton cancel = new JButton("Cancel");
	
	JPanel c,radio,p,button,main;
	
	String gender;
	String Gender;
	Boolean flag = false;
	
	public StudentUpdate()
	{
		super("Student Update Information");
		
		String[] year = {"First Year","Second Year","Third Year","Fourth Year","Fifth Year"};
		String[] subj = {"Embedded System","Networking","Knowledge Engineering","Business Information System","Software Engineering","High Performance Computing"};
		String[] majorArray = {"Computer Science","Computer Technology"};
		
		classYear = new JComboBox(year);
		subject = new JComboBox(subj);
		major = new JComboBox(majorArray);
		
		bg.add(male);
		bg.add(female);
		
		radio = new JPanel(new GridLayout(1,2));
		radio.add(male);
		radio.add(female);
		
		c = new JPanel(new GridLayout(9,2));
		c.add(new JLabel("Entrance No."));
		c.add(entranceNo);
		c.add(new JLabel("Roll No."));
		c.add(rollNo);
		c.add(new JLabel("Student Name"));
		c.add(studentName);
		c.add(new JLabel("Gender"));
		c.add(radio);
		c.add(new JLabel("Father Name"));
		c.add(fatherName);
		c.add(new JLabel("Address"));
		c.add(address);
		c.add(new JLabel("Class Year"));
		c.add(classYear);
		c.add(new JLabel("Major"));
		c.add(major);
		
		p = new JPanel(new GridLayout(1,2));
		p.add(new JLabel("Specialized Subjects"));
		p.add(new JScrollPane(subject));
		
		button = new JPanel(new GridLayout(1,3));
		button.add(update);
		button.add(clear);
		button.add(cancel);
		
		main = new JPanel(new FlowLayout());
		main.add(c);
		main.add(p);
		main.add(button);
		
		add(main);
		setVisible(true);
		setSize(500,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		studentName.setEditable(false);
		rollNo.setEditable(false);
		male.setEnabled(false);
		female.setEnabled(false);
		fatherName.setEditable(false);
		classYear.setEnabled(false);
		major.setEnabled(false);
		subject.setEnabled(false);
		update.setEnabled(false);
		address.setEditable(false);
		
		update.addActionListener(this);
		clear.addActionListener(this);
		cancel.addActionListener(this);
		entranceNo.addFocusListener(this);
	}

	public void focusGained(FocusEvent e) {
		
	}

	public void focusLost(FocusEvent e) {
		if(e.getSource() == entranceNo)
		{
			getData();	
		}
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == update)
		{
			try{
				String str = JOptionPane.showInputDialog("Please enter new address");
				Connection con = new DBConnection().getDBConnection("studentdb");
				Statement stmt = con.createStatement();
				stmt.executeUpdate("Update studentInfo set address = '" + str + "' where entranceNo = '"+ entranceNo.getText() +"'");
				JOptionPane.showMessageDialog(null, "Address update is successful!","Student Update Information",JOptionPane.NO_OPTION);
				con.close();
				address.setText(str);
			}catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex, "Error" , JOptionPane.NO_OPTION);
			}
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
	
	public void getData()
	{
		try
		{
			Connection con = new DBConnection().getDBConnection("studentdb");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * From studentInfo where entranceNo = '" + entranceNo.getText() + "' ");
			while(rs.next())
			{
				rollNo.setText(rs.getString("rollNo"));
				studentName.setText(rs.getString("studentName"));
				Gender = rs.getString("gender");
				if(Gender.equalsIgnoreCase("Male"))
				{
					male.setSelected(true);
				}
				else
				{
					female.setSelected(true);
				}
				fatherName.setText(rs.getString("fatherName"));
				address.setText(rs.getString("address"));
				classYear.setSelectedItem(rs.getString("year"));
				major.setSelectedItem(rs.getString("major"));
				subject.setSelectedItem(rs.getString("specializedSubject"));
				flag = true;
			}
			if(flag == false)
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
				update.setEnabled(false);
				JOptionPane.showMessageDialog(null, "Student Information was not found" , "Student Update Information", JOptionPane.NO_OPTION);
			}
			else
			{
				update.setEnabled(true);
				flag = false;
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
