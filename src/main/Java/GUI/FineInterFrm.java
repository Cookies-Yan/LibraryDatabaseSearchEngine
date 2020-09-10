package GUI;

import Dao.FineMngDao;
import Model.FineMng;
import Util.DbUtil;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class FineInterFrm extends JInternalFrame {
	private JTable FineShowTable;
	private JTextField CardIdTXT;
	private JTextField BnameTXT;
	private JTable LoanShowTable;

	private DbUtil dbUtil = new DbUtil();
	private  FineMngDao fineMngDao = new FineMngDao();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField CardIdSrhTxt;
	private JTextField txtNoResult;
	private JTextField LoanIdTXT;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FineInterFrm frame = new FineInterFrm();
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
	public FineInterFrm() {
		setTitle("Fines");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 678, 590);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton PayBtn = new JButton("Pay");
		PayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				payActionPerformed(e);
			}
		});
		
		JLabel lblCardid = new JLabel("Card ID");
		
		CardIdTXT = new JTextField();
		CardIdTXT.setEditable(false);
		CardIdTXT.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("User Name");
		
		BnameTXT = new JTextField();
		BnameTXT.setEditable(false);
		BnameTXT.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton RefreshBtn = new JButton("Refresh");
		RefreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshActionPerformed(e);
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Card ID");
		
		CardIdSrhTxt = new JTextField();
		CardIdSrhTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar=e.getKeyChar();
				if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
					
				} else {
					e.consume();  
				}
				String s = CardIdSrhTxt.getText();
				if(s.length() >= 9) e.consume();
			}
		});
		CardIdSrhTxt.setColumns(10);
		
		JButton CardIdSrhBtn = new JButton("OK");
		CardIdSrhBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardIdSrhActionPerformed(e);
			}
		});
		
		txtNoResult = new JTextField();
		txtNoResult.setForeground(new Color(255, 0, 0));
		txtNoResult.setEditable(false);
		txtNoResult.setText("");
		txtNoResult.setBackground(UIManager.getColor("CheckBox.background"));
		txtNoResult.setColumns(10);
		txtNoResult.setBorder(new EmptyBorder(0,0,0,0));
		
		LoanIdTXT = new JTextField();
		LoanIdTXT.setEditable(false);
		LoanIdTXT.setColumns(10);
		
		JLabel lblLoanId = new JLabel("Loan ID");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(78)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCardid)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(62)
									.addComponent(CardIdTXT, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(BnameTXT, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblLoanId)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(LoanIdTXT, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtNoResult, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(CardIdSrhTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(CardIdSrhBtn)
											.addPreferredGap(ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
											.addComponent(RefreshBtn)
											.addGap(18)
											.addComponent(PayBtn, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))))
							.addGap(76))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(63)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCardid)
						.addComponent(CardIdTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(BnameTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(LoanIdTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(lblLoanId))
					.addGap(13)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(PayBtn)
						.addComponent(RefreshBtn)
						.addComponent(lblNewLabel_1)
						.addComponent(CardIdSrhTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(CardIdSrhBtn))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtNoResult, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);
		
		LoanShowTable = new JTable();
		LoanShowTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				loanTableclickActionPerformed(e);
			}
		});
		LoanShowTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Loan ID", "ISBN", "Date Out", "Due Date", "Date In", "Fine amt"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		LoanShowTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		scrollPane_1.setViewportView(LoanShowTable);
		
		FineShowTable = new JTable();
		FineShowTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				fineTableclickActionPerformed(e);
			}
		});
		FineShowTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Card ID", "User Name", "Fine Amount"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(FineShowTable);
		getContentPane().setLayout(groupLayout);
		this.refresh();
	}
	
	/**
	 * refresh button click event
	 * @param evt refresh button
	 */
	private void refreshActionPerformed(ActionEvent evt) {
		this.refresh();
		this.fillLoanTable();
	}
	
    /**
     *  update bookmanagement.fines and FineShowTable
     */
	private void refresh() {
		this.updateFine();
		this.fillTable();
	}
	
	/**
	 * update database table bookmanagement.fines
	 */
	private void updateFine() {
	    Connection con =null;
	    try {
	    	con=dbUtil.getCon(); 	
	    	ResultSet rs=fineMngDao.list_date(con);
	    	while(rs.next()) {
	    		Calendar cal = Calendar.getInstance();	
	    	    Date now = cal.getTime(); 
	    		if(StringUtils.isEmpty(rs.getString("Date_in"))) {
	    			if(fineMngDao.checkLate(con,df.format(now), rs.getString("Date_out"))) {
	    				FineMng fineMng=new FineMng(rs.getInt("Loan_id"),
	    						0.25f*fineMngDao.checkDiff(con,df.format(now), rs.getString("Due_date")));
	    				fineMngDao.updateFine(con, fineMng);
	    			}
	    			else {
	    				FineMng fineMng=new FineMng(rs.getInt("Loan_id"),0.00f);
	    				fineMngDao.updateFine(con, fineMng);
	    			}
	    		}
	    		else {
	    			if(fineMngDao.checkLate(con,rs.getString("Date_in"), rs.getString("Date_out"))) {
	    				FineMng fineMng=new FineMng(rs.getInt("Loan_id"),
	    						0.25f*fineMngDao.checkDiff(con, rs.getString("Date_in"), rs.getString("Due_date")));
	    				fineMngDao.updateFine(con, fineMng);
	    			}
	    			else {
	    				FineMng fineMng=new FineMng(rs.getInt("Loan_id"),0.00f);
	    				fineMngDao.updateFine_ReturnNoLate(con, fineMng);
	    			}
	    		}
	    	}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	private void fillTable() {
	    DefaultTableModel dtm=(DefaultTableModel) FineShowTable.getModel();
	    dtm.setRowCount(0);
	    Connection con =null;
	    try {
	    	con=dbUtil.getCon();
	    	ResultSet rs=fineMngDao.listFineAmt(con);
	    	while(rs.next()) {
	    		Vector v=new Vector();
	    		v.add(rs.getInt("card_id")); 		
	    		v.add(rs.getString("Bname"));
	    		v.add(rs.getFloat("sum"));
	    		dtm.addRow(v);
	    	}
	    }catch(Exception e) {
	    	
	    }finally {
	    	try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	/**
	 * click the users in fine table and show their loans bellow
	 * @param e fine Table click
	 */
    private void fineTableclickActionPerformed(MouseEvent e) {
    	this.LoanIdTXT.setText("");
    	int row=this.FineShowTable.getSelectedRow();
    	FineMng fineMng = new FineMng();
    	String cId =FineShowTable.getValueAt(row,0).toString();
    	fineMng.setCard_id(Integer.valueOf(cId));
		this.CardIdTXT.setText((String)(cId));
		this.BnameTXT.setText((String)(FineShowTable.getValueAt(row,1)));
		this.fillLoanTable(fineMng);
    }
    
    private void fillLoanTable() {
    	FineMng fineMng = new FineMng();
    	String cId =CardIdTXT.getText();
    	fineMng.setCard_id(Integer.valueOf(cId));
    	this.fillLoanTable(fineMng);
	}
    private void fillLoanTable(FineMng fineMng) {
		DefaultTableModel dtm=(DefaultTableModel) LoanShowTable.getModel();
	    dtm.setRowCount(0);
	    Connection con =null;
	    try {
	    	con=dbUtil.getCon();
	    	ResultSet rs=fineMngDao.list_Loan(con,fineMng);
	    	while(rs.next()) {
	    		Vector v=new Vector();
	    		v.add(rs.getInt("loan_id"));
	    		v.add(rs.getString("isbn"));
	    		v.add(rs.getString("date_out"));
	    		v.add(rs.getString("due_date"));
	    		v.add(rs.getString("date_in"));	
	    		v.add(rs.getFloat("fine_amt"));
	    		dtm.addRow(v);
	    	}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
    } 

	private void loanTableclickActionPerformed(MouseEvent e) {
		int row_1=this.LoanShowTable.getSelectedRow();
		this.LoanIdTXT.setText(LoanShowTable.getValueAt(row_1,0).toString());
	}
	
	private void payActionPerformed(ActionEvent e) {
		int row=this.LoanShowTable.getSelectedRow();
    	FineMng fineMng = new FineMng();
    	if(StringUtils.isEmpty(LoanIdTXT.getText())) {
   	    	JOptionPane.showMessageDialog(null,"Please select a loan", "Warning!",JOptionPane.ERROR_MESSAGE);
   	    	return;
   	    }
    	String lId="";
    	lId=LoanShowTable.getValueAt(row,0).toString();
    	fineMng.setLoan_id(Integer.valueOf(lId));
	    boolean payPass= false;
	    Connection con =null;
	    try {
	    	con=dbUtil.getCon();
	    	ResultSet rs_paid =fineMngDao.checkPaid(con,fineMng);
	    	while (rs_paid.next()) {
	    		if(rs_paid.getInt("paid") == 0) {
	    			if(StringUtils.isNotEmpty(rs_paid.getString("date_in"))){
	    				payPass=true;
	    			}
	    			else {
	    	        	JOptionPane.showMessageDialog(null,"Book has not been checked in!","Error!", JOptionPane.ERROR_MESSAGE);
	    	        } 
	    		}
	    		else {
	    			JOptionPane.showMessageDialog(null,"Loan already paid!","Warning!", JOptionPane.ERROR_MESSAGE);
	    		}
	    	}   
	    	if(payPass) {
	    		fineMngDao.payFine(con,fineMng);
	    		JOptionPane.showMessageDialog(null,"Payment successful!");
	    		this.refresh();
	    		this.fillLoanTable(fineMng);
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

    
	private void cardIdSrhActionPerformed(ActionEvent e) {
		this.LoanIdTXT.setText("");
		this.txtNoResult.setText("");
    	FineMng fineMng = new FineMng();
    	String cId =CardIdSrhTxt.getText();                 
    	if(StringUtils.isEmpty(cId)) {
    		JOptionPane.showMessageDialog(null,"No Card ID input!","Warning!", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	fineMng.setCard_id(Integer.valueOf(cId));
		this.CardIdTXT.setText((String)(cId));
		this.fillBname(fineMng);
		this.fillLoanTable(fineMng);
	}

	private void fillBname(FineMng fineMng) {
		Connection con =null;
	    try {
	    	con=dbUtil.getCon();
	    	int i=0;
	    	ResultSet rs=fineMngDao.checkBname(con,fineMng);
	    	String name=null;
	    	while(rs.next()) {
	    		i++;
	    		name=rs.getString("Bname");                   
	    	}
	    	this.BnameTXT.setText(name);
	    	if(i==0) {
	    		this.txtNoResult.setText("No result !");
	    	}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}
}


