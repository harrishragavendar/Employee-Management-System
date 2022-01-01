import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TakeAttendance extends JFrame
{
	Choice c1,fn,an;
	JButton b1,b2;
    JTextField t1,t2,t3,t4,t5,t6,t7;
	JLabel l1,l2,l3;
    JPanel p1;
	public void constructUI()
	{
		l1 = new JLabel("    Select Employee ID");
		l2 = new JLabel("    Forenoon");
		l3 = new JLabel("    Afternoon");
		b1 =new JButton("SUBMIT",new ImageIcon(ClassLoader.getSystemResource("icons/ok.png")));
		b2 = new JButton("CANCEL",new ImageIcon(ClassLoader.getSystemResource("icons/delete.png")));
		c1 = new Choice();
		fn = new Choice();
		an = new Choice();
		p1= new JPanel();
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        Image ic = icon.getImage();
        setIconImage(ic);
		setSize(600,450);
        setLocation(400,130);
		setVisible(true);
        setResizable(false);
		getContentPane().setBackground(Color.WHITE);
	}
	public void designUI()
	{
		l1.setFont(new Font("TimesRoman",Font.BOLD,22));
		l2.setFont(new Font("TimesRoman",Font.BOLD,22));
		l3.setFont(new Font("TimesRoman",Font.BOLD,22));
		
		fn.add("Present");
        fn.add("Absent");
        fn.add("Leave");
		
		an.add("Present");
        an.add("Absent");
        an.add("Leave");
		
		b1.setFont(new Font("Arial",Font.BOLD,21));
        b1.setBackground(new Color(0,204,0));
        b1.setForeground(Color.BLACK);
		
        b2.setFont(new Font("Arial",Font.BOLD,21));
        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);
		
		p1.add(l1);
        p1.add(c1);
        p1.add(l2);
        p1.add(fn);
        p1.add(l3);
        p1.add(an);
		p1.setLayout(new GridLayout(4,2,10,20));
		p1.setBackground(new Color(51,170,255));
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
				String f = fn.getSelectedItem();
				String s = an.getSelectedItem();
				String dt = new java.util.Date().toString();
				String qry = "insert into attendance values("+ id +",'"+dt+"','"+f+"','"+s+"')";
				System.out.println(qry);
				try
				{
					Conn c1 = new Conn();
					c1.s.executeUpdate(qry);
					JOptionPane.showMessageDialog(null,"Attendence Entered");
					setVisible(false);
				}
				catch(Exception ee)
				{
					ee.printStackTrace();
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
	TakeAttendance()
    {
		super("Attendance");
		constructUI();
		designUI();
		getID();
		handleEvents();
	}
	public static void main(String[] args)
    {
        TakeAttendance ta = new TakeAttendance();
        ta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}