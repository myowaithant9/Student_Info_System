package StudentInfoSystem;


import java.sql.*;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import StudentInfoSystem.DBConnection;

public class StudentDelete extends JFrame implements ActionListener{

	JTextField entranceNo = new JTextField(20);
	
	JButton search = new JButton("Search");
	JButton delete = new JButton("Delete");
	JButton cancel = new JButton("Cancel");
	
	JPanel comp,button,mainPanel;
	
	String studentInfo;
	boolean flag = false;
	
	public StudentDelete()
	{
		super("Deletion");
		comp = new JPanel(new GridLayout(1,2));
		comp.add(new JLabel("Entrance No."));
		comp.add(entranceNo);
		
		button = new JPanel(new GridLayout(1,3));
		button.add(search);
		button.add(delete);
		button.add(cancel);
		
		delete.setEnabled(false);
		
		mainPanel = new JPanel();
		mainPanel.add(comp);
		mainPanel.add(button);
		
		add(mainPanel);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,100);
		
		search.addActionListener(this);
		delete.addActionListener(this);
		cancel.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == search)
		{
			search();
		}
		else if(e.getSource() == delete)
		{
			delete();
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
			String query = "Select * from studentInfo where entranceNo = " + entranceNo.getText();
			Connection con = new DBConnection().getDBConnection("studentdb");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				studentInfo = "Student Information is" +
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
				JOptionPane.showMessageDialog(null, "Student Information was not found","Deletion",JOptionPane.NO_OPTION);
			}
			else
			{
				JOptionPane.showMessageDialog(null, studentInfo , "Deletion",JOptionPane.NO_OPTION);
				delete.setEnabled(true);					
				flag = false;
			}
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
	
	}
	public void delete()
	{
		int i = JOptionPane.showConfirmDialog(this, "Are you sure to delete it ? " , "Deletion", JOptionPane.YES_NO_OPTION);
		if(i == JOptionPane.YES_OPTION)
		{
			try
			{
				String query = "Delete from studentInfo where entranceNo = " + entranceNo.getText(); 
				Connection con = new DBConnection().getDBConnection("studentdb");
				Statement stmt = con.createStatement();
				stmt.executeUpdate(query);
				con.close();
				entranceNo.setText("");
				JOptionPane.showMessageDialog(null, "Deletion is Successful", "Deletion",JOptionPane.NO_OPTION);
				
			}catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}
