package GUI;

import Dao.LoanMngDao;
import Model.LoanMng;
import Util.DbUtil;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Pattern;

public class ckInInterFrm extends JInternalFrame {
	private JTextField LoanSrhTxt;
	private JTable Loan_Table;
	private JTextField LoanIdShowTxt;
	private JTextField IsbnShowTxt;
	private JTextField CardIdShowTxt;
	private JTextField DateOutShowTxt;
	private JTextField DueDateShowTxt;
	private JTextField DateInShowTxt;
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	Calendar cal = Calendar.getInstance();	
    Date date_in = cal.getTime();
    String dateIn=df.format(date_in);
    
	private DbUtil dbUtil = new DbUtil();
	private LoanMngDao loanMngDao = new LoanMngDao();
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ckInInterFrm frame = new ckInInterFrm();
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
	public ckInInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Check In");
		setBounds(100, 100, 713, 669);
		
		JLabel lblNewLabel = new JLabel("Input ISBN, Card ID or User Name to search.");
		
		LoanSrhTxt = new JTextField();
		LoanSrhTxt.setColumns(10);
		
		JButton Srh_Button = new JButton("Search");
		Srh_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoanSearchActionPerformed(e);			
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Loan Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		textField = new JTextField();
		textField.setForeground(new Color(255, 0, 0));
		textField.setEditable(false);
		textField.setBackground(UIManager.getColor("CheckBox.background"));
		textField.setColumns(10);
		textField.setBorder(new EmptyBorder(0,0,0,0));
		textField.setText("");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(LoanSrhTxt, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Srh_Button)))
							.addGap(62))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(LoanSrhTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Srh_Button))
					.addGap(2)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_1 = new JLabel("Loan_id");
		
		LoanIdShowTxt = new JTextField();
		LoanIdShowTxt.setEditable(false);
		LoanIdShowTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("ISBN");
		
		IsbnShowTxt = new JTextField();
		IsbnShowTxt.setEditable(false);
		IsbnShowTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Card_id");
		
		CardIdShowTxt = new JTextField();
		CardIdShowTxt.setEditable(false);
		CardIdShowTxt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Date_out");
		
		DateOutShowTxt = new JTextField();
		DateOutShowTxt.setEditable(false);
		DateOutShowTxt.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Due_date");
		
		DueDateShowTxt = new JTextField();
		DueDateShowTxt.setEditable(false);
		DueDateShowTxt.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Today");
		
		DateInShowTxt = new JTextField();
		DateInShowTxt.setEditable(false);
		DateInShowTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("Check In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkInActionPerformed(e);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_4)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(DateOutShowTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblNewLabel_5)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(DueDateShowTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblNewLabel_6)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(DateInShowTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_1)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(LoanIdShowTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(IsbnShowTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblNewLabel_3)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(CardIdShowTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(LoanIdShowTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(CardIdShowTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(IsbnShowTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addGap(43)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(DateOutShowTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(DueDateShowTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6)
						.addComponent(DateInShowTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(21))
		);
		panel.setLayout(gl_panel);
		
		Loan_Table = new JTable();
		Loan_Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				Loan_TableMousePressed(me);
			}
		});
		Loan_Table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Loan_id", "ISBN", "Card_id", "Date_out", "Due_date"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(Loan_Table);
		getContentPane().setLayout(groupLayout);

	}

	/**
	 * table click event
	 * @param me
	 */
	private void Loan_TableMousePressed(MouseEvent me) {
		int row=this.Loan_Table.getSelectedRow();
		this.LoanIdShowTxt.setText((String)(Loan_Table.getValueAt(row,0)));
		this.IsbnShowTxt.setText((String)(Loan_Table.getValueAt(row,1)));
		this.CardIdShowTxt.setText((String)(Loan_Table.getValueAt(row,2)));
		this.DateOutShowTxt.setText((String)(Loan_Table.getValueAt(row,3)));
		this.DueDateShowTxt.setText((String)(Loan_Table.getValueAt(row,4)));
		this.DateInShowTxt.setText(dateIn);
    }
	
   
	private void checkInActionPerformed(ActionEvent e) {
		String isbn=this.IsbnShowTxt.getText();
	    String card_id=this.CardIdShowTxt.getText();
	    
	    
	    if(StringUtils.isEmpty(isbn)) {
	    	JOptionPane.showMessageDialog(null,"Please select a loan", "Warning!",JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    LoanMng loanMng = new LoanMng(isbn,Integer.valueOf(card_id),dateIn);
	    Connection con =null;
	    try {
	    	con = dbUtil.getCon();
	    	int n = loanMngDao.updateLoan(con,loanMng);
	        if(n == 1) {
	        	JOptionPane.showMessageDialog(null,"Check in successful.");
	        	this.resetValue();
	        	this.refresh(loanMng);
	        }
	        else {
	        	JOptionPane.showMessageDialog(null,"Check in failed.","Error!", JOptionPane.ERROR_MESSAGE);
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
	
    private void refresh(LoanMng loanMng) {	
    	this.fillTable(loanMng);
	}

	/**
     * search loan information event
     * @param e
     */
	private void LoanSearchActionPerformed(ActionEvent e) {
		this.textField.setText("");
		String search=this.LoanSrhTxt.getText();
		LoanMng loanMng=new LoanMng();	
		loanMng.setSearchTxt(search);	
		this.fillTable(loanMng);
		this.resetValue();
	}

	/**
	 * fill result table
	 * @param loanMng
	 */
	private void fillTable(LoanMng loanMng) {
		String CIsearch = loanMng.getSearchTxt();
		DefaultTableModel dtm=(DefaultTableModel) Loan_Table.getModel();
	    dtm.setRowCount(0);
	    Connection con =null;
	    int i =0;
	    try {
	    	con = dbUtil.getCon();
	    	ResultSet rs_s = loanMngDao.list(con,loanMng);
//			System.out.println(1);
	    	while(rs_s.next()) {
//				System.out.println(2);
	    		Vector<String> v = new Vector<String>();
	    		v.add(Integer.toString(rs_s.getInt("Loan_id")));
	    		v.add(rs_s.getString("Isbn"));
	    		v.add(Integer.toString(rs_s.getInt("Card_id")));
	    		v.add(rs_s.getString("Date_out"));
	    		v.add(rs_s.getString("Due_date"));
	    		v.add(rs_s.getString("Date_in"));
	    		dtm.addRow(v);
	    		i++;
	    	}  
	    	if(i == 0) {
	    		this.textField.setText("No result !");
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
	public static boolean isInteger(String str) {  
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        return pattern.matcher(str).matches();  
  }
	private void resetValue() {
		this.LoanIdShowTxt.setText("");
		this.IsbnShowTxt.setText("");
		this.CardIdShowTxt.setText("");
		this.DateOutShowTxt.setText("");
		this.DueDateShowTxt.setText("");
		this.DateInShowTxt.setText("");
	}
}
