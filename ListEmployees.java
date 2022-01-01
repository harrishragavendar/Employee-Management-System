import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ListEmployees extends JFrame
{
	JTable t1;
    JButton b1;
	JScrollPane s1;
	public void constructUI()
	{
		b1=new JButton("Print",new ImageIcon(ClassLoader.getSystemResource("icons/print.png")));
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        Image ic = icon.getImage();
        setIconImage(ic);
        setLocation(200,100);
		setSize(1000,400);
		setVisible(true);
	}
	public void designUI()
	{
		b1.setFont(new Font("Arial",Font.BOLD,22));
        b1.setBackground(Color.GREEN);
        b1.setForeground(Color.BLACK);
		add(b1,"South");
	}
	public void createSpreadSheet()
	{
		try
        {
			String h[]={"Employee ID","Name","Gender","Address","State","City","E-Mail ID","Mobile"};
			String d[][]=new String[20][8];  
			int i=0,j=0;
            String q="select * from employee";
            Conn c1 = new Conn();
            ResultSet rs=c1.s.executeQuery(q);
            while(rs.next())
            {
                d[i][j++]=rs.getString("ID");
                d[i][j++]=rs.getString("Name");
                d[i][j++]=rs.getString("Gender");
                d[i][j++]=rs.getString("Address");
                d[i][j++]=rs.getString("State");
                d[i][j++]=rs.getString("City");
                d[i][j++]=rs.getString("EMail");
                d[i][j++]=rs.getString("Mobile");
                i++;
                j=0;
            }
            t1=new JTable(d,h); 
			s1=new JScrollPane(t1);
			add(s1);
        }
        catch(Exception e){}
	}
	public void handleEvents()
	{
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
				   t1.print();
				}
				catch(Exception e){}
			}
		});
	}
	ListEmployees()
	{
		super("EMPLOYEE DETAILS");
		constructUI();
		designUI();
		createSpreadSheet();
		handleEvents();
	}
	public static void main(String[] args)
    {
        ListEmployees le = new ListEmployees();
        le.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}