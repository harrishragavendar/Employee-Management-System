import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
public class PaySlip extends JFrame 
{
	Choice c1;
	Font f;
    JTextArea t1;
    JButton b1;
	JLabel l1;
	JScrollPane s;
	JPanel p1;
	public void constructUI()
	{
		l1 = new JLabel("Select ID");
		c1 = new Choice();
		t1 = new JTextArea(45,80);
		s = new JScrollPane(t1);
		b1 = new JButton("Generate Pay Slip",new ImageIcon(ClassLoader.getSystemResource("icons/print.png")));
		p1 = new JPanel();
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        Image ic = icon.getImage();
        setIconImage(ic);
		setSize(800,700);
        setLocation(300,10);
		setVisible(true);
		setResizable(false);
	}
	public void designUI()
	{
		t1.setFont(new Font("TimesRoman",Font.BOLD,14));
		b1.setFont(new Font("Arial",Font.BOLD,22));
        b1.setBackground(new Color(0,204,0));
        b1.setForeground(Color.BLACK);
		p1.add(l1);
		p1.add(c1);
		setLayout(new BorderLayout());
		add(p1,"North");
		add(b1,"South");
        add(s,"Center");
	}
	public void getID()
	{
		try
        {
			Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from salary");
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
                try
                {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from employee where ID="+c1.getSelectedItem());
                    rs.next();
                    String name = rs.getString("Name");
                    rs.close();
                
                    rs = c.s.executeQuery("select * from salary where ID="+c1.getSelectedItem());
                    double gross=0;
                    double net=0;
        
                    Date d1 = new Date();
                    int month = d1.getMonth() + 1;
                    int year = d1.getYear() + 1900;
                    t1.setText("------------------------   PAY SLIP FOR THE MONTH OF "+month+" ," +year+ " ------------------------");
                    t1.append("\n");
                    if(rs.next())
                    {
                        t1.append("\n     Employee ID   : "+rs.getString("ID"));
                        t1.append("\n     Employee Name : "+name);
                        t1.append("\n------------------------------------------------------------------------------------------------------------");
                        t1.append("\n");
                        double hra = rs.getDouble("House_Rent_Allowance");
                        t1.append("\n     House Rent Allowance   : "+hra);
                        double da  = rs.getDouble("Daily_Allowance");
                        t1.append("\n     Daily Allowance        : "+da);
                        double med  = rs.getDouble("Medical_Allowance");
                        t1.append("\n     Medical Allowance      : "+med);
                        double pf  = rs.getDouble("Provident_Fund");
                        t1.append("\n     Provident Fund         : "+pf);
                        double basic = rs.getDouble("Basic_Pay");
                        gross = hra+da+med+pf+basic;
                        net = gross - pf;
                        t1.append("\n     Basic Pay           : "+basic);
                        t1.append("\n------------------------------------------------------------------------------------------------------------");
                        t1.append("\n");
                        t1.append("\n     GROSS SALARY        :   "+gross+"    \n     NET SALARY          : "+net);
                        t1.append("\n     Tax   :   2.1% of GROSS =  "+ (gross*2.1/100));   
                        t1.append("\n------------------------------------------------------------------------------------------------------------");
                        t1.append("\n");
                        t1.append("\n");    
                        t1.append("\n");
                        t1.append("                                                                                         (  Signature  )      ");
                    }
					t1.print();
                }
                catch(Exception e) 
                {
                    e.printStackTrace();
                }
            }
        });
	}
	PaySlip()
    {
        super("Pay Slip");
		constructUI();
		designUI();
		getID();
		handleEvents();
	}
	public static void main(String[] args)
    {
        PaySlip ps = new PaySlip();
        ps.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}