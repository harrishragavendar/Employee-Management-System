import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MyEmployeeApp extends JFrame
{
	JLabel l1;
	JMenuBar mb;
	JMenu m1,m2,m3,m4;
	JMenuItem m1a,m1b,m1c;
	JMenuItem m2a,m2b,m2c;
	JMenuItem m3a,m3b;
	JMenuItem m4a;
	public void constructUI()
	{
		mb = new JMenuBar();
		
		m1 = new JMenu("Employee's Information");
		
		m1a = new JMenuItem("Add New Employee");
		m1b = new JMenuItem("Salary Entry");
		m1c = new JMenuItem("List of Employees");
		
		m2 = new JMenu("Update / Delete Details");
		
		m2a = new JMenuItem("Update / Delete Employee Details");
		m2b = new JMenuItem("Update / Delete Salary Details of Employee");
		m2c = new JMenuItem("Attendence Entry");
		
		m3 = new JMenu("Employee's Report");
		
		m3a = new JMenuItem("Generate Salary Slip");
		m3b = new JMenuItem("Attendence Record");
		
		m4 = new JMenu("Exit");
		m4a = new JMenuItem("Exit");
		
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        Image i = icon.getImage();
        setIconImage(i);
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/header.png"));
        Image i2 = i1.getImage().getScaledInstance(1280,720,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l1 = new JLabel(i3);
		getContentPane().setBackground(new Color(255,255,153));
	}
	public void designUI()
	{
		setJMenuBar(mb);
		mb.add(Box.createRigidArea(new Dimension(260,40)));
        mb.setBackground(new Color(255,255,153));
		
		m1.setFont(new Font("TimesRoman",Font.BOLD,25));
		m1.setForeground(new Color(0,153,0));
		
		m1a.setFont(new Font("TimesRoman",Font.BOLD,20));
		m1a.setOpaque(true);
		m1a.setBackground(new Color(255,255,0));
        m1a.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        m1a.setIcon(new ImageIcon(ClassLoader.getSystemResource("icons/New.png")));
		
		m1b.setFont(new Font("TimesRoman",Font.BOLD,20));
		m1b.setOpaque(true);
		m1b.setBackground(new Color(255,255,0));
        m1b.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        m1b.setIcon(new ImageIcon(ClassLoader.getSystemResource("icons/schedreport.png")));
		
		m1c.setFont(new Font("TimesRoman",Font.BOLD,20));
		m1c.setOpaque(true);
		m1c.setBackground(new Color(255,255,0));
        m1c.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
        m1c.setIcon(new ImageIcon(ClassLoader.getSystemResource("icons/newinvoice.png")));
		
		m2.setForeground(new Color(255,94,0));
        m2.setFont(new Font("TimesRoman", Font.BOLD, 25));
		
		m2a.setFont(new Font("TimesRoman",Font.BOLD,20));
		m2a.setForeground(Color.WHITE);
		m2a.setOpaque(true);
		m2a.setBackground(new Color(51,153,255));
        m2a.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
        m2a.setIcon(new ImageIcon(ClassLoader.getSystemResource("icons/empreport.png")));
		
		m2b.setFont(new Font("TimesRoman",Font.BOLD,20));
		m2b.setForeground(Color.WHITE);
		m2b.setOpaque(true);
		m2b.setBackground(new Color(51,153,255));
        m2b.setAccelerator(KeyStroke.getKeyStroke("ctrl U"));
        m2b.setIcon(new ImageIcon(ClassLoader.getSystemResource("icons/EditOpen.png")));
		
		m2c.setFont(new Font("TimesRoman",Font.BOLD,20));
		m2c.setForeground(Color.WHITE);
		m2c.setOpaque(true);
		m2c.setBackground(new Color(51,153,255));
        m2c.setAccelerator(KeyStroke.getKeyStroke("ctrl T"));
        m2c.setIcon(new ImageIcon(ClassLoader.getSystemResource("icons/EXPENSE.PNG")));
		
		m3.setFont(new Font("TimesRoman",Font.BOLD,25));
		m3.setForeground(new Color(0,0,204));
		
		m3a.setFont(new Font("TimesRoman",Font.BOLD,20));
		m3a.setForeground(Color.WHITE);
		m3a.setOpaque(true);
		m3a.setBackground(new Color(102,0,153));
        m3a.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
        m3a.setIcon(new ImageIcon(ClassLoader.getSystemResource("icons/payments.png")));
		
		m3b.setFont(new Font("TimesRoman",Font.BOLD,20));
		m3b.setForeground(Color.WHITE);
		m3b.setOpaque(true);
		m3b.setBackground(new Color(102,0,153));
        m3b.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
        m3b.setIcon(new ImageIcon(ClassLoader.getSystemResource("icons/empreport.png")));
		
		m4.setFont(new Font("TimesRoman",Font.BOLD,25));
		m4.setForeground(new Color(255,0,0));
		
		m4a.setFont(new Font("TimesRoman", Font.BOLD, 20));
		m4a.setForeground(Color.WHITE);
		m4a.setOpaque(true);
		m4a.setBackground(new Color(255,0,0));
        m4a.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        m4a.setIcon(new ImageIcon(ClassLoader.getSystemResource("icons/exit.PNG")));
		
		m1.add(m1a);
		m1.add(m1b);
		m1.add(m1c);
		
		m2.add(m2a);
		m2.add(m2b);
		m2.add(m2c);
		
		m3.add(m3a);
		m3.add(m3b);
		
		m4.add(m4a);
		
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		mb.add(m4);
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		add(l1);
	}
	public void handleEvents()
	{
		m1a.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				new NewEmployee().setVisible(true);
			}
		});
		m1b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				new Salary().setVisible(true);
			}
		});
		m1c.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				new ListEmployees().setVisible(true);
			}
		});
		m2a.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				new UpdateEmployee().setVisible(true);
			}
		});
		m2b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				new UpdateSalary().setVisible(true);
			}
		});
		m2c.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				new TakeAttendance().setVisible(true);
			}
		});
		m3a.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				 new PaySlip().setVisible(true);
			}
		});
		m3b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				new ListAttendance().setVisible(true);
			}
		});
		m4a.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				 System.exit(0);
			}
		});
	}
    MyEmployeeApp()
    {
		super("Employee Management System");
		constructUI();
		designUI();
		handleEvents();
	}
	public static void main(String[] args)
    {
        MyEmployeeApp a = new MyEmployeeApp();
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}