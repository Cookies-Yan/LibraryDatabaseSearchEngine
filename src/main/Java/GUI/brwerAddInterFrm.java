package GUI;

import Dao.BrwerMngDao;
import Model.BrwerMng;
import Util.DbUtil;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;

public class brwerAddInterFrm extends JInternalFrame {
	private JTextField SsnIn_Txt;
	private JTextField BnameIn_Txt;
	private JTextField AddressIn_Txt;
	private JTextField PhoneIn_Txt;
	
	private DbUtil dbUtil = new DbUtil();
	private  BrwerMngDao brwerMngDao = new BrwerMngDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					brwerAddInterFrm frame = new brwerAddInterFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public brwerAddInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Borrower Add");
		setBounds(100, 100, 446, 366);
		
		JLabel SsnIn_lb = new JLabel("Ssn");
		
		JLabel BnameIn_lb = new JLabel("Bname");
		
		JLabel AddressIn_lb = new JLabel("Address");
		
		JLabel PhoneIn_lb = new JLabel("Phone");
		
		JLabel Prompt_lb = new JLabel("Item with * can not be empty.");
		Prompt_lb.setForeground(new Color(255, 0, 0));
		Prompt_lb.setFont(new Font("SimSun", Font.PLAIN, 12));
		
		SsnIn_Txt = new JTextField();
		SsnIn_Txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar=e.getKeyChar();
				if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
					
				} else {
					e.consume();  
				}
				String s = SsnIn_Txt.getText();
				if(s.length() >= 9) e.consume();
			}
		});
		SsnIn_Txt.setColumns(10);
		
		BnameIn_Txt = new JTextField();
		BnameIn_Txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar=e.getKeyChar();
				if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
					e.consume(); 
				} else {}
			}
		});
		BnameIn_Txt.setColumns(10);
		
		AddressIn_Txt = new JTextField();
		AddressIn_Txt.setColumns(10);
		
		PhoneIn_Txt = new JTextField();
		PhoneIn_Txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar=e.getKeyChar();
				if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
					
				} else {
					e.consume();  
				}
				String s = PhoneIn_Txt.getText();
				if(s.length() >= 10) e.consume();
			}
		});
		PhoneIn_Txt.setColumns(10);
		
		JButton Add_Button = new JButton("Add");
		Add_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrowerAddActionPerformed(e);
			}

			
		});
		
		JButton Reset_Button = new JButton("Reset");
		Reset_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		
		JLabel label = new JLabel("*");
		label.setForeground(new Color(255, 0, 0));
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(new Color(255, 0, 0));
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(new Color(255, 0, 0));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(PhoneIn_lb)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(44)
							.addComponent(Add_Button, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(Reset_Button, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(SsnIn_lb)
										.addComponent(BnameIn_lb))
									.addGap(30)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(SsnIn_Txt, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
										.addComponent(BnameIn_Txt)
										.addComponent(AddressIn_Txt)
										.addComponent(PhoneIn_Txt)
										.addComponent(Prompt_lb)))
								.addComponent(AddressIn_lb))
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_2)
								.addComponent(label_1)
								.addComponent(label))))
					.addContainerGap(74, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(SsnIn_lb)
						.addComponent(SsnIn_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(BnameIn_lb)
						.addComponent(BnameIn_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(AddressIn_lb)
						.addComponent(AddressIn_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(PhoneIn_lb)
						.addComponent(PhoneIn_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addComponent(Prompt_lb)
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Reset_Button)
						.addComponent(Add_Button))
					.addGap(43))
		);
		getContentPane().setLayout(groupLayout);

	}
	/**
	 * add borrower event
	 * @param e
	 */
	private void borrowerAddActionPerformed(ActionEvent e) {
	    String Ssn=this.SsnIn_Txt.getText();
	    String Bname=this.BnameIn_Txt.getText();
	    String Address=this.AddressIn_Txt.getText();
	    String Phone=this.PhoneIn_Txt.getText();
	    boolean ssnPass=true;
	    
		if(StringUtils.isEmpty(Ssn)||StringUtils.isEmpty(Bname)||StringUtils.isEmpty(Address)) {
	    	JOptionPane.showMessageDialog(null,"Ssn, Bname or Address may be empty, please check again.", "Warning!", JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
		if(Ssn.length() < 9) {
			JOptionPane.showMessageDialog(null,"Ssn is not invalid.", "Warning!", JOptionPane.ERROR_MESSAGE);
			return;
		}
	    if(StringUtils.isNotEmpty(Phone)){
	    	if(Phone.length() < 10) {
				JOptionPane.showMessageDialog(null,"Phone is invalid.", "Warning!", JOptionPane.ERROR_MESSAGE);
				return;
			}
	    }
	    BrwerMng brwerMng = new BrwerMng(Ssn, Bname, Address, Phone);
	    Connection con =null;
	    try {
	    	con=dbUtil.getCon();
	    	ResultSet rs = brwerMngDao.checkSsn(con);
	    	while(rs.next()) {
				if(Ssn.equals(rs.getString("Ssn"))) {
					JOptionPane.showMessageDialog(null, "Already owned library card, Can't apply another one.", "Warning!",JOptionPane.ERROR_MESSAGE);
					ssnPass=false;
					return;					
				}
			}
	    	if(ssnPass) {
	    		int n = brwerMngDao.add(con,brwerMng);
		        if(n == 1) {
		        	ResultSet re_LoanId = brwerMngDao.checkLoanId(con,brwerMng);
		        	int newId = 0;
		        	while(re_LoanId.next()){
		        		newId = re_LoanId.getInt("Card_id");
		        	}
//                    System.out.println(newId);
		        	JOptionPane.showMessageDialog(null,"Enrollment successful!\n"+" Your Card ID is: "+newId+".");
		        	this.resetValue();
		        }
		        else {
		        	JOptionPane.showMessageDialog(null,"Enrollment failed.","Error!", JOptionPane.ERROR_MESSAGE);
		        } 
	    	}
	    }catch(Exception evt) {
	    	JOptionPane.showMessageDialog(null,"Fail to connect to the library database.","Error!", JOptionPane.ERROR_MESSAGE);
	    }finally {
	        try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	}
	
	/**
	 * reset event 
	 * @param evt
	 */
	private void resetValueActionPerformed(ActionEvent evt) {
        this.resetValue();
	}
	/**
	 * reset all value input
	 */
	private void resetValue() {
		this.SsnIn_Txt.setText("");
		this.BnameIn_Txt.setText("");
		this.AddressIn_Txt.setText("");
		this.PhoneIn_Txt.setText("");
	}
}
