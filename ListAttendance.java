import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ListAttendance extends JFrame
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
		b1.setBackground(new Color(0,189,0));
        b1.setForeground(Color.BLACK);
		b1.setFont(new Font("Arial",Font.BOLD,22));
		add(b1,"South");
	}
	public void createSpreadSheet()
	{
		try
        {
			String h[]={"Employee ID","Date & Time","Forenoon","Afternoon"};
			String d[][]=new String[100][4];  
			int i=0,j=0;
            String q="select * from attendance";
            Conn c1=new Conn();
            ResultSet rs=c1.s.executeQuery(q);
            while(rs.next())
            {
                d[i][j++]=rs.getString("ID");
                d[i][j++]=rs.getString("Date_and_Time");
                d[i][j++]=rs.getString("Forenoon");
                d[i][j++]=rs.getString("Afternoon");
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
	ListAttendance()
	{
		super("View Employees Attendence");
		constructUI();
		designUI();
		createSpreadSheet();
		handleEvents();
	}
	public static void main(String[] args)
    {
        ListAttendance la = new ListAttendance();
        la.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}