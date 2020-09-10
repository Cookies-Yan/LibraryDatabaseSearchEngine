package GUI;

import Dao.BookMngDao;
import Dao.LoanMngDao;
import Model.BookMng;
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
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class bookSearchInterFrm extends JInternalFrame {
	private JTable Book_Table;
	private JTextField Search_Txt;
	private JTextField IsbnShowTxt;
	private JTextField AuthorShowTxt;
	private JTextField TitleShowTxt;
	private JTextField idInputTxt;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	private DbUtil dbUtil = new DbUtil();
	private  BookMngDao bookMngDao = new BookMngDao();
	private  LoanMngDao loanMngDao = new LoanMngDao();
	private JTextField txtNoResult;
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bookSearchInterFrm frame = new bookSearchInterFrm();
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
	public bookSearchInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Check out");
		setBounds(100, 100, 713, 669);
		
		JScrollPane scrollPane = new JScrollPane();
		
		Search_Txt = new JTextField();
		Search_Txt.setColumns(10);
		
		JButton Search_button = new JButton("Search");
		Search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSearchActionPerformed(e);
			}
			
		});
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Book Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblSearchAndChoose = new JLabel("Search and choose a book to check out.");
		
		txtNoResult = new JTextField();
		txtNoResult.setForeground(new Color(255, 0, 0));
		txtNoResult.setText("");
		txtNoResult.setEditable(false);
		txtNoResult.setBackground(UIManager.getColor("CheckBox.background"));
		txtNoResult.setColumns(10);
		txtNoResult.setBorder(new EmptyBorder(0,0,0,0));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSearchAndChoose)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(Search_Txt, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Search_button, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
								.addComponent(txtNoResult, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(46))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(lblSearchAndChoose)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Search_Txt, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(Search_button))
					.addGap(4)
					.addComponent(txtNoResult, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
					.addGap(62))
		);
		
		JLabel lblNewLabel = new JLabel("ISBN :");
		
		IsbnShowTxt = new JTextField();
		IsbnShowTxt.setEditable(false);
		IsbnShowTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Author(s):");
		
		AuthorShowTxt = new JTextField();
		AuthorShowTxt.setEditable(false);
		AuthorShowTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Title:");
		
		TitleShowTxt = new JTextField();
		TitleShowTxt.setEditable(false);
		TitleShowTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Card_id:");
		
		idInputTxt = new JTextField();
		idInputTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar=e.getKeyChar();
				if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
					
				} else {
					e.consume();  
				}
				String s = idInputTxt.getText();
				if(s.length() >= 10) e.consume();
			}
		});
		idInputTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("Check Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkOutActionPerformed(e);
			}
		});
		
		JLabel lblrequaired = new JLabel("*requaired");
		lblrequaired.setForeground(new Color(255, 0, 0));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(57)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel)
							.addComponent(lblNewLabel_2))
						.addComponent(lblNewLabel_3))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addComponent(idInputTxt, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblrequaired)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(TitleShowTxt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(IsbnShowTxt, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addGap(44)
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(AuthorShowTxt, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
							.addGap(59))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(IsbnShowTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(AuthorShowTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2)
						.addComponent(TitleShowTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(idInputTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblrequaired))
					.addGap(18)
					.addComponent(btnNewButton)
					.addGap(23))
		);
		panel.setLayout(gl_panel);
		
		Book_Table = new JTable();
//		TableColumn column12 = Book_Table.getColumnModel().getColumn(3);
//		column12.setCellEditor(Book_Table.getDefaultEditor(Boolean.class));
//		column12.setCellRenderer(Book_Table.getDefaultRenderer(Boolean.class));
		
		Book_Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				Book_TableMousePressed(me);
			}
		});
		Book_Table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ISBN", "Title", "Author", "Availability"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		Book_Table.getColumnModel().getColumn(1).setPreferredWidth(250);
		Book_Table.getColumnModel().getColumn(2).setPreferredWidth(160);
		Book_Table.getColumnModel().getColumn(3).setPreferredWidth(110);
		scrollPane.setViewportView(Book_Table);
		getContentPane().setLayout(groupLayout);
		
	}
	
    /**
     * table click event
     * @param me
     */
	private void Book_TableMousePressed(MouseEvent me) {
		int row=this.Book_Table.getSelectedRow();
		this.IsbnShowTxt.setText((String)(Book_Table.getValueAt(row,0)));
		this.TitleShowTxt.setText((String)(Book_Table.getValueAt(row,1)));
		this.AuthorShowTxt.setText((String)(Book_Table.getValueAt(row,2)));
	}

    /**
     * check out event(check in button pressed)
     * @param e
     */
	private void checkOutActionPerformed(ActionEvent e) {
		String isbn=this.IsbnShowTxt.getText();
	    String card_id=this.idInputTxt.getText();
	    Calendar cal = Calendar.getInstance();	
	    Date date_out = cal.getTime();
	    String dateOut=df.format(date_out);
	    cal.add(Calendar.DATE, 14);
	    Date due_date= (Date) cal.getTime();
	    String dueDate=df.format(due_date);
	    boolean countPass=true,availPass=true;
	    int idExit=0;
	    
	    if(StringUtils.isEmpty(isbn)) {
	    	JOptionPane.showMessageDialog(null,"Please select a book", "Warning!",JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    if(StringUtils.isEmpty(card_id)) {
	    	JOptionPane.showMessageDialog(null,"Card_id is requaired, please input again!", "Warning!",JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    LoanMng loanMng = new LoanMng(isbn, Integer.parseInt(card_id), dateOut, dueDate);
	    Connection con =null;
	    try {
	    	con=dbUtil.getCon();
	    	ResultSet rs_id = loanMngDao.checkId(con,loanMng);
	    	while(rs_id.next()) {	 
	    		idExit++;
			}
	    	if(idExit==0){
	    		JOptionPane.showMessageDialog(null,"Not an available card_id, please check again!", "Error!",JOptionPane.ERROR_MESSAGE);
	    	}
	    	else {
	    		ResultSet rs_count = loanMngDao.checkCount(con, loanMng);
		    	while(rs_count.next()) {
					if(rs_count.getInt("count(*)") >= 3) {
						JOptionPane.showMessageDialog(null,"You have already 3 loans, cann't borrow another book!", "Warning!", JOptionPane.ERROR_MESSAGE);
						countPass=false;
						break;					
					}
				}
		    	if(countPass) {
		    		ResultSet rs_avail = loanMngDao.checkAvail(con, loanMng);
			    	while(rs_avail.next()) {
						if(rs_avail.getInt("Avail")==1) {
							JOptionPane.showMessageDialog(null, "This book has been checked out!", "Warning!", JOptionPane.ERROR_MESSAGE);
							availPass=false;	
							break;					
						}
						if(rs_avail.getInt("avail")==0) {
							availPass=true;
						}
			    	}
		    		if(availPass) {
		    			int n = loanMngDao.addLoan(con,loanMng);
				        if( n == 1 ) {
				        	ResultSet rs_LoanId = loanMngDao.getLoan_id(con,loanMng);
				        	int ID = 0;
//							System.out.println(rs_LoanId);
				        	while(rs_LoanId.next()){
				        		ID = rs_LoanId.getInt("Loan_id") + 10000;
//								System.out.println(ID);
				        	}
				        	loanMngDao.addFine(con,ID);
				        	JOptionPane.showMessageDialog(null,"Check out successful.");
				        	this.resetValue();
				        	this.searchBook();
				        }
				        else {
				        	JOptionPane.showMessageDialog(null,"Check out failed.", "Error!",JOptionPane.ERROR_MESSAGE);
				        }
		    		} 
		    	}
	    	}   
	    }catch(Exception evt) {
	    	evt.printStackTrace();
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
	 * Search event
	 * @param evt
	 */
	private void bookSearchActionPerformed(ActionEvent evt) {
		this.resetValue();
		this.searchBook();
		
	}
	
	private void searchBook() {
		this.txtNoResult.setText("");
		String search=this.Search_Txt.getText();
		BookMng bookMng=new BookMng();		
		bookMng.setSearchTxt(search);
		this.fillTable(bookMng);
	}

	/**
	 * fill result table
	 * @param bookMng
	 */
	private void fillTable(BookMng bookMng) {
	    DefaultTableModel dtm=(DefaultTableModel) Book_Table.getModel();
	    dtm.setRowCount(0);
	    Connection con =null;
	    int i =0;
	    try {
	    	con=dbUtil.getCon();
	    	ResultSet rs=bookMngDao.list(con,bookMng);	
	    	while(rs.next()) {
	    		i++;
	    		Vector<String> v=new Vector();
	    		v.add(rs.getString("Isbn")); 		
	    		v.add(rs.getString("Title"));
	    		v.add(rs.getString("a"));
	    		if(rs.getInt("Avail")==1) {
	    			v.add("Check Out");
	    		}else {
	    			
	    		}
	    		dtm.addRow(v);
	    	}
	    	if(i == 0) {
	    		this.txtNoResult.setText("No result !");
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
	private void resetValue() {
		this.IsbnShowTxt.setText("");
		this.AuthorShowTxt.setText("");
		this.TitleShowTxt.setText("");
		this.idInputTxt.setText("");
	}
}
