import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class UpdateSalary extends JFrame
{
	 Choice c1;
	JButton b1,b2;
	JTextField t2,t3,t4,t5,t6;
	JLabel l1,l2,l3,l4,l5,l6;
	JPanel p1;
	public void constructUI()
	{
		l1 = new JLabel("  Select Employee ID");
		l2 = new JLabel("  House Rent Allowance : ");
		l3 = new JLabel("  Daily Allowance : ");
		l4 = new JLabel("  Medical Allowance : ");
		l5 = new JLabel("  Provident Fund : ");
		l6 = new JLabel("  Basic Salary : ");
		c1 = new Choice();
		t2 = new JTextField(15);
		t3 = new JTextField(15);
		t4 = new JTextField(15);
		t5 = new JTextField(15);
		t6 = new JTextField(15);
		b1 =new JButton("UPDATE",new ImageIcon(ClassLoader.getSystemResource("icons/ok.png")));
		b2 = new JButton("DELETE",new ImageIcon(ClassLoader.getSystemResource("icons/delete.png")));
		p1= new JPanel();
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        Image ic = icon.getImage();
        setIconImage(ic);
        setVisible(true);
		setLocation(400,50);
		setSize(600,550);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
	}
	public void designUI()
	{
		l1.setFont(new Font("TimesRoman",Font.BOLD,22));
		l2.setFont(new Font("TimesRoman",Font.BOLD,22));
		l3.setFont(new Font("TimesRoman",Font.BOLD,22));
		l4.setFont(new Font("TimesRoman",Font.BOLD,22));
		l5.setFont(new Font("TimesRoman",Font.BOLD,22));
		l6.setFont(new Font("TimesRoman",Font.BOLD,22));
		
		b1.setFont(new Font("Arial",Font.BOLD,16));
        b1.setBackground(new Color(0,204,0));
        b1.setForeground(Color.BLACK);
		
		b2.setFont(new Font("Arial",Font.BOLD,16));
        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);
		
        p1.setLayout(new GridLayout(7,2,10,20));
		p1.setBackground(new Color(51,170,255));
		
		p1.add(l1);
        p1.add(c1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(t3);
        p1.add(l4);
        p1.add(t4); 
        p1.add(l5);
        p1.add(t5);
        p1.add(l6);
        p1.add(t6);
        p1.add(b1);
        p1.add(b2);
		add(p1);
	}
	public void getID()
	{
		try
        {
			Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            while(rs.next())
            {
                c1.add(rs.getString("ID"));    
            }
        }
        catch(Exception e){}
	}
	public void handleEvents()
	{
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				String id = c1.getSelectedItem();
				String hra = t2.getText();
				String da = t3.getText();
				String med = t4.getText();
				String pf = t5.getText();
				String basic = t6.getText();
				if(hra.equals("") || da.equals("") || med.equals("") || pf.equals("") || basic.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please fill out all fields.");
				}
				else
				{
					String qry = "update salary set House_Rent_Allowance="+hra+",Daily_Allowance="+da+",Medical_Allowance="+med+",Provident_Fund="+pf+",Basic_Pay="+basic+"  where ID="+id;
					System.out.println(qry);
					try
					{
						Conn c = new Conn();
						c.s.executeUpdate(qry);
						JOptionPane.showMessageDialog(null,"Salary Updated.");
						setVisible(false);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		});
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					Conn c = new Conn();
					c.s.executeUpdate("delete from salary where ID="+c1.getSelectedItem());
					JOptionPane.showMessageDialog(null,"Salary Deleted.");
					setVisible(false);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	UpdateSalary()
	{
		super("Update Salary");
		constructUI();
		designUI();
		getID();
		handleEvents();
	}
	public static void main(String[] args)
	{
        UpdateSalary us = new UpdateSalary();
        us.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}