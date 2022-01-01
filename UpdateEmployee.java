import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
public class UpdateEmployee extends JFrame
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    JTextField t1,t2,t3,t4,t5,t6;
    JButton b1,b2;
    Choice c1,c2;
	JPanel p1;
	public void constructUI()
	{
		l1 = new JLabel("     Select Employee ID");
		l2 = new JLabel("     Name  :  ");
		l3 = new JLabel("     Gender  :  ");
		l4 = new JLabel("     Address  :  ");
		l5 = new JLabel("     State  :  ");
		l6 = new JLabel("     City  :  ");
		l7 = new JLabel("     E-Mail ID  :  ");
		l8 = new JLabel("     Mobile  :  ");
		
		c1 = new Choice();
		c2 = new Choice();
		
		t1 = new JTextField(15);
		t2 = new JTextField(15);
		t3 = new JTextField(15);
		t4 = new JTextField(15);
		t5 = new JTextField(15);
		t6 = new JTextField(15);
		
		b1 =new JButton("UPDATE",new ImageIcon(ClassLoader.getSystemResource("icons/ok.png")));
        b2 = new JButton("DELETE",new ImageIcon(ClassLoader.getSystemResource("icons/delete.png")));
		
		p1 = new JPanel();
		
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        Image ic = icon.getImage();
        setIconImage(ic);
		setSize(600,650);
        setLocation(400,30);
		setVisible(true);
        setResizable(false);
	}
	public void designUI()
	{
		l1.setFont(new Font("TimesRoman",Font.BOLD,22));
		l2.setFont(new Font("TimesRoman",Font.BOLD,22));
		l3.setFont(new Font("TimesRoman",Font.BOLD,22));
		l4.setFont(new Font("TimesRoman",Font.BOLD,22));
		l5.setFont(new Font("TimesRoman",Font.BOLD,22));
		l6.setFont(new Font("TimesRoman",Font.BOLD,22));
		l7.setFont(new Font("TimesRoman",Font.BOLD,22));
		l8.setFont(new Font("TimesRoman",Font.BOLD,22));
		
		c2.add("Male");
        c2.add("Female");
		
		b1.setFont(new Font("Arial",Font.BOLD,16));
        b1.setBackground(new Color(0,204,0));
        b1.setForeground(Color.BLACK);
		
		b2.setFont(new Font("Arial",Font.BOLD,16));
        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);
		
		p1.setBackground(new Color(51,170,255));
        p1.setLayout(new GridLayout(9,2,10,20));
		
		p1.add(l1);
        p1.add(c1);
		p1.add(l2);
        p1.add(t1);
		p1.add(l3);
        p1.add(c2);
		p1.add(l4);
        p1.add(t2);
		p1.add(l5);
        p1.add(t3);
		p1.add(l6);
        p1.add(t4);
		p1.add(l7);
        p1.add(t5);
		p1.add(l8);
        p1.add(t6);
		p1.add(b1);
        p1.add(b2);
		setLayout(new BorderLayout());
        add(p1,"Center");
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
				String g = c2.getSelectedItem();
				String a = t2.getText();
				String s = t3.getText();
				String c = t4.getText();
				String e = t5.getText();
				String p = t6.getText();
				if(n.equals("") || a.equals("") || s.equals("") || c.equals("") || e.equals("") || p.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please fill out all fields.");
				}
				else if(!isValidEmail(e))
				{
					t5.setText("");
					JOptionPane.showMessageDialog(null,"Invalid E-Mail ID.");
				}
				else if(!isValidMobile(p))
				{
					t6.setText("");
					JOptionPane.showMessageDialog(null,"Invalid Mobile Number.");
				}
				else
				{
					String qry = "update employee set Name='"+n+"',Gender='"+g+"',Address='"+a+"',State='"+s+"',City='"+c+"',EMail='"+e+"',Mobile='"+p+"'   where ID="+c1.getSelectedItem();
					System.out.println(qry);
					try
					{
						Conn c3 = new Conn();
						c3.s.executeUpdate(qry);
						JOptionPane.showMessageDialog(null,"Employee Details Updated.");
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
					Conn c3 = new Conn();
					c3.s.executeUpdate("delete from employee where ID="+c1.getSelectedItem());
					c3.s.executeUpdate("delete from salary where ID="+c1.getSelectedItem());
					c3.s.executeUpdate("delete from attendance where ID="+c1.getSelectedItem());
					JOptionPane.showMessageDialog(null,"Employee Deleted.");
					setVisible(false);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	UpdateEmployee()
	{
		super("Update Employee Details");
		constructUI();
		designUI();
		getID();
		handleEvents();
	}
	public static void main(String[] args)
    {
        UpdateEmployee ue = new UpdateEmployee();
        ue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}