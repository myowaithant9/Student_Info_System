package StudentInfoSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.sql.*;

public class StudentSearchByEntranceNo extends JFrame implements ActionListener{
	
	JTextField entranceNo = new JTextField(20);
	
	JButton search = new JButton("Search");
	JButton clear = new JButton("Clear");
	JButton cancel = new JButton("Cancel");
	
	JPanel comp,button,mainPanel;
	
	boolean flag = false;
	
	public StudentSearchByEntranceNo()
	{
		super("Search By Entrance");
		comp = new JPanel(new GridLayout(1,2));
		comp.add(new JLabel("Enter Entrance No."));
		comp.add(entranceNo);
		
		button = new JPanel(new GridLayout(1,3));
		button.add(search);
		button.add(clear);
		button.add(cancel);
		
		mainPanel = new JPanel();
		mainPanel.add(comp);
		mainPanel.add(button);
		
		add(mainPanel);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,100);
		
		search.addActionListener(this);
		clear.addActionListener(this);
		cancel.addActionListener(this);	
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == search)
		{
			search();
		}
		else if(e.getSource() == clear)
		{
			entranceNo.setText("");
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
	
	public void search()
	{
		try
		{
			String str = null;
			String query = "Select * from studentInfo where entranceNo = '" + entranceNo.getText() + "' ";
			Connection con = new DBConnection().getDBConnection("studentdb");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				str = "Student Information is" +
						"\nEntrance Number = " + entranceNo.getText() + 
						"\nRollNumber = " + rs.getString("rollNo") +
						"\nStudent Name = " + rs.getString("studentName") +
						"\nGender = " + rs.getString("gender") +
						"\nFather Name = " + rs.getString("fatherName") +
						"\nAddress = " + rs.getString("address") +
						"\nYear = " + rs.getString("year") +
						"\nMajor = " + rs.getString("major") +
						"\nSpecialized Subject = " + rs.getString("specializedSubject");
				flag = true;
			}
			if(flag == false)
			{
				JOptionPane.showMessageDialog(null, "Student Information was not found!","Search By Entrance",JOptionPane.NO_OPTION);
			}
			else
			{
				JOptionPane.showMessageDialog(null, str , "Search By Entrance",JOptionPane.NO_OPTION);
				flag = false;
			}
			
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e,"Error", JOptionPane.NO_OPTION);
		}
		
	}
	
	
}
