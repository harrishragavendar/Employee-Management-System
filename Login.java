import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Login extends JFrame
{
	JLabel l1;
	JPanel p1,p2;
    JTextField t1;
    JPasswordField t2;
    JButton b1,b2;
	public void constructUI()
	{
		b1 = new JButton("LOGIN", new ImageIcon(ClassLoader.getSystemResource("icons/ok.png")));
		b2 = new JButton("CANCEL", new ImageIcon(ClassLoader.getSystemResource("icons/delete.png")));
		t1 = new JTextField(10);
        t2 = new JPasswordField(10);
		l1 = new JLabel(new ImageIcon(ClassLoader.getSystemResource("icons/user.png")));
		p1=new JPanel();
        p2=new JPanel();
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        Image ic = icon.getImage();
        setIconImage(ic);
		setLayout(new BorderLayout());
		setSize(400,250);
        setLocation(500,200);	
        setVisible(true);
        setResizable(false);
	}
	public void designUI()
	{
		b1.setBackground(new Color(0,204,0));
        b1.setForeground(Color.BLACK);
		
        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);
		
		p1.setBackground(new Color(204,204,204));
        p2.setBackground(new Color(204,204,204));
		
		p1.add(new JLabel("Username  "));
        p1.add(t1);
        p1.add(new JLabel("Password   "));
        p1.add(t2);
		
		p2.add(b1);
        p2.add(b2);
		
		add(l1,BorderLayout.WEST);
		add(p1,BorderLayout.CENTER);
        add(p2,BorderLayout.SOUTH);
	}
	public void handleEvents()
	{
			b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					try
					{
						Conn c1=new Conn();
						String u=t1.getText();
						String v=t2.getText();
						String q="select * from login where username='"+u+"' and password='"+v+"'";
						ResultSet rs=c1.s.executeQuery(q); 
						if(rs.next())
						{
							new MyEmployeeApp().setVisible(true);
							setVisible(false);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Invalid login");
							setVisible(false);
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
		});
		t2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					Conn c1=new Conn();
					String u=t1.getText();
					String v=t2.getText();
					String q="select * from login where username='"+u+"' and password='"+v+"'";
					ResultSet rs=c1.s.executeQuery(q); 
					if(rs.next())
					{
						new MyEmployeeApp().setVisible(true);
						setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid login");
						System.exit(0);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
        b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					JOptionPane.showMessageDialog(null, "Cancelled");
					System.exit(0);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
    }
	Login()
	{
		super("Login Page");
		constructUI();
		designUI();
		handleEvents();
	}
	public static void main(String[] args)
    {
        Login l = new Login();
        l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}