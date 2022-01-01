import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
public class NewEmployee extends JFrame
{
	Choice c1;
	JButton b1,b2;
	JLabel l1,l2,l3,l4,l5,l6,l7;
    JTextField t1,t3,t4,t5,t6,t7;
    JPanel p1;
	public void constructUI()
	{
		l1 = new JLabel("     Name  :  ");
		l2 = new JLabel("     Gender  :  ");
		l3 = new JLabel("     Address  :  ");
		l4 = new JLabel("     State  :  ");
		l5 = new JLabel("     City  :  ");
		l6 = new JLabel("     E-Mail ID  :  ");
		l7 = new JLabel("     Mobile  :  ");
		t1 = new JTextField(15);
		c1 = new Choice();
		t3 = new JTextField(15);
		t4 = new JTextField(15);
		t5 = new JTextField(15);
		t6 = new JTextField(15);
		t7 = new JTextField(15);
		b1 =new JButton("SUBMIT", new ImageIcon(ClassLoader.getSystemResource("icons/ok.png")));
		b2 = new JButton("CANCEL", new ImageIcon(ClassLoader.getSystemResource("icons/delete.png")));
		p1 = new JPanel();
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        Image ic = icon.getImage();
        setIconImage(ic);
		setSize(600,650);
        setLocation(400,30);
		setVisible(true);
        setResizable(false);
		getContentPane().setBackground(Color.WHITE);
	}
	public void designUI()
	{
		c1.add("Male");
        c1.add("Female");
		l1.setFont(new Font("TimesRoman",Font.BOLD,22));
		l2.setFont(new Font("TimesRoman",Font.BOLD,22));
		l3.setFont(new Font("TimesRoman",Font.BOLD,22));
		l4.setFont(new Font("TimesRoman",Font.BOLD,22));
		l5.setFont(new Font("TimesRoman",Font.BOLD,22));
		l6.setFont(new Font("TimesRoman",Font.BOLD,22));
		l7.setFont(new Font("TimesRoman",Font.BOLD,22));
		b1.setFont(new Font("Arial",Font.BOLD,16));
        b1.setBackground(new Color(0,204,0));
        b1.setForeground(Color.BLACK);
		b2.setFont(new Font("Arial",Font.BOLD,16));
        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);
		p1.setBackground(new Color(255,255,153));
        p1.setLayout(new GridLayout(8,2,10,20));
		p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(c1);
        p1.add(l3);
        p1.add(t3);
        p1.add(l4);
        p1.add(t4); 
        p1.add(l5);
        p1.add(t5);
        p1.add(l6);
        p1.add(t6);
        p1.add(l7);
        p1.add(t7);
		p1.add(b1);
        p1.add(b2);
		setLayout(new BorderLayout());
        add(p1,"Center");
	}
	boolean isValidMobile(String str)
	{
		Pattern ptrn = Pattern.compile("(0/91)?[6-9][0-9]{9}");  
		Matcher match = ptrn.matcher(str); 
		return (match.find() && match.group().equals(str)); 
	}
	boolean isValidEmail(String email) 
	{
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		return pattern.matcher(email).matches();
   }
	public void handleEvents()
	{
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				String n = t1.getText();
				String g = c1.getSelectedItem();
				String a = t3.getText();
				String s = t4.getText();
				String c = t5.getText();
				String e = t6.getText();
				String p = t7.getText();
				if(n.equals("") || a.equals("") || s.equals("") || c.equals("") || e.equals("") || p.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please fill out all fields.");
				}
				else if(!isValidEmail(e))
				{
					t6.setText("");
					JOptionPane.showMessageDialog(null,"Invalid E-Mail ID.");
				}
				else if(!isValidMobile(p))
				{
					t7.setText("");
					JOptionPane.showMessageDialog(null,"Invalid Mobile Number.");
				}
				else
				{
					String qry = "insert into employee values(null,'"+n+"','"+g+"','"+a+"','"+s+"','"+c+"','"+e+"','"+p+"')";
					System.out.println(qry);
					try
					{
						Conn c1 = new Conn();
						c1.s.executeUpdate(qry);
						JOptionPane.showMessageDialog(null,"Employee Created");
						setVisible(false);  
					}
					catch(Exception ee)
					{
						ee.printStackTrace();
					}
				}
			}
		});
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					JOptionPane.showMessageDialog(null, "Cancelled");
					setVisible(false);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	NewEmployee()
    {
		super("New Employee");
		constructUI();
		designUI();
		handleEvents();
	}
	public static void main(String s[])
    {
        NewEmployee ne = new NewEmployee();
        ne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}