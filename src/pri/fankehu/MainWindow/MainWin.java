package pri.fankehu.MainWindow;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ibm.icu.impl.Row;
import com.ibm.icu.util.BytesTrie.Iterator;
import com.mysql.jdbc.Connection;
//import com.sun.java.swing.plaf.windows.resources.windows;
import com.mysql.jdbc.Statement;

import javafx.geometry.Orientation;
import javafx.scene.control.Cell;
//import com.sun.java.util.jar.pack.Package.File;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import pri.fankehu.BackStage_management.UI_backManage;
import pri.fankehu.LinkMysql.DataBaseLink;
import pri.fankehu.LinkMysql.DatabaseSetting;
import pri.fankehu.analysis.Chart_count;
import pri.fankehu.customer_management.Alter_c;
import pri.fankehu.customer_management.Insert_c_KHbyhand;
import pri.fankehu.customer_management.Insert_c_place;
import pri.fankehu.customer_management.Insert_c_type;
import pri.fankehu.customer_management.UI_Alter_c;
import pri.fankehu.customer_management.UI_Insert_c_place;
import pri.fankehu.customer_management.UI_Insert_c_type;
import pri.fankehu.customer_management.UI_importKHbyhand;
import pri.fankehu.invoice_management.UI_add_invoice;
import pri.fankehu.invoice_management.UI_alter_invoice;
import pri.fankehu.invoice_management.UI_delete_invoice;
import pri.fankehu.order_management.UI_add_order;
import pri.fankehu.order_management.UI_alter_order;
import pri.fankehu.order_management.UI_delete_order;
import pri.fankehu.product_management.UI_alter_pro;
import pri.fankehu.product_management.UI_cangku_button;
import pri.fankehu.product_management.UI_delete_pro;
import pri.fankehu.product_management.UI_product_button;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
//import org.apache.poi.hssf.usermodel;

@SuppressWarnings("unused")
public class MainWin {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private DataBaseLink dataBaseLink;//数据库连接对象
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Table table;
	private Text txtNewText;
	private Text txtNewText_1;
	private Text txtNewText_2;
	private String InsertMessage;
	private String tableflag;
	private Table table_1;
	private Table table_2;
	String[] user=new String[2];
	
	UI_product_button ui_product_button=new UI_product_button(shell);
	UI_cangku_button ui_cangku_button=new UI_cangku_button(shell);
	UI_add_order ui_add_order=new UI_add_order(shell);
	UI_add_invoice ui_add_invoice=new UI_add_invoice(shell);
	UI_alter_invoice ui_alter_invoice=new UI_alter_invoice(shell);
	UI_delete_invoice ui_delete_invoice=new UI_delete_invoice(shell);
	UI_alter_order ui_alter_order=new UI_alter_order(shell);
	UI_delete_order ui_delete_order =new UI_delete_order(shell);
	UI_alter_pro ui_alter_pro=new UI_alter_pro(shell);
	UI_delete_pro ui_delete_pro=new UI_delete_pro(shell);
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Table table_3;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private Table table_4;
	private Table table_5;
	private boolean linkOK=false;//判断是否链接数据库成功
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWin window = new MainWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void opencomposite(Composite com) {
		com.setVisible(true);
	}

	/**
	 * Create contents of the window.
	 * @throws SQLException 
	 */
	protected void createContents() throws SQLException {
		shell = new Shell(SWT.MIN);
		shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				/**
				 * 加判断
				 * 如果连接成功
				 * 则关闭时调用close()
				 */
				if(linkOK) {
					try {
						getDataBaseLink().getCon().close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		shell.setImage(SWTResourceManager.getImage(MainWin.class, "/pri/fankehu/sams/timg.jpg"));
		shell.setSize(1119, 668);
		shell.setText("\u5BA2\u6237\u8BA2\u8D2D\u7BA1\u7406\u7CFB\u7EDF");
		shell.setLayout(new FormLayout());
		
		/**
		 * 设置数据库默认信息
		 */
		setDataBaseLink(new DataBaseLink());
		getDataBaseLink().SettingInfo();
		
		
		
		Composite composite = formToolkit.createComposite(shell, SWT.NONE);
		composite.setLayout(new FormLayout());
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 629);
		fd_composite.right = new FormAttachment(0, 1103);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		composite.setLayoutData(fd_composite);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		composite.setVisible(false);
		formToolkit.paintBordersFor(composite);
		
		Composite composite_9 = formToolkit.createComposite(composite, SWT.NONE);
		FormData fd_composite_9 = new FormData();
		fd_composite_9.right = new FormAttachment(0, 70);
		fd_composite_9.top = new FormAttachment(0, 10);
		fd_composite_9.left = new FormAttachment(0);
		composite_9.setLayoutData(fd_composite_9);
		formToolkit.paintBordersFor(composite_9);
		composite_9.setLayout(new GridLayout(1, false));
		
		Button btnNewButton_1 = new Button(composite_9, SWT.NONE);
		
		formToolkit.adapt(btnNewButton_1, true, true);
		btnNewButton_1.setText("\u5BA2\u6237\u7BA1\u7406");
		
		Button btnNewButton = new Button(composite_9, SWT.NONE);
		
		formToolkit.adapt(btnNewButton, true, true);
		btnNewButton.setText("\u8BA2\u5355\u7BA1\u7406");
		
		Button btnNewButton_2 = new Button(composite_9, SWT.NONE);
		
		formToolkit.adapt(btnNewButton_2, true, true);
		btnNewButton_2.setText("\u53D1\u7968\u7BA1\u7406");
		
		Button btnNewButton_3 = new Button(composite_9, SWT.NONE);
		
		formToolkit.adapt(btnNewButton_3, true, true);
		btnNewButton_3.setText("\u8D27\u7269\u7BA1\u7406");
		
		Button btnNewButton_4 = new Button(composite_9, SWT.NONE);
		
		formToolkit.adapt(btnNewButton_4, true, true);
		btnNewButton_4.setText("\u7EDF\u8BA1\u5206\u6790");
		
		
		
		Composite composite_10 = formToolkit.createComposite(composite, SWT.NONE);
		FormData fd_composite_10 = new FormData();
		fd_composite_10.bottom = new FormAttachment(0, 619);
		fd_composite_10.right = new FormAttachment(0, 1093);
		fd_composite_10.top = new FormAttachment(0, 10);
		fd_composite_10.left = new FormAttachment(0, 76);
		composite_10.setLayoutData(fd_composite_10);
		formToolkit.paintBordersFor(composite_10);
		
		Label lblNewLabel = new Label(composite_10, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNewLabel.setLocation(10, 10);
		lblNewLabel.setSize(61, 17);
		formToolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("\u5BA2\u6237\u5217\u8868");
		
		Composite composite_2 = formToolkit.createComposite(composite, SWT.NONE);
		FormData fd_composite_2 = new FormData();
		fd_composite_2.bottom = new FormAttachment(0, 619);
		fd_composite_2.right = new FormAttachment(0, 1093);
		fd_composite_2.top = new FormAttachment(0, 10);
		fd_composite_2.left = new FormAttachment(0, 76);
		composite_2.setLayoutData(fd_composite_2);
		formToolkit.paintBordersFor(composite_2);
		composite_2.setLayout(new StackLayout());
		
		Composite composite_6 = new Composite(composite_2, SWT.NONE);
		formToolkit.adapt(composite_6);
		formToolkit.paintBordersFor(composite_6);
		composite_6.setVisible(false);
		
		Label label_4 = new Label(composite_6, SWT.NONE);
		label_4.setBounds(246, 41, 61, 17);
		formToolkit.adapt(label_4, true, true);
		label_4.setText("\u53D1\u7968\u7BA1\u7406\u9875");
		
		table_3 = formToolkit.createTable(composite_6, SWT.FULL_SELECTION);
		
		table_3.setBounds(10, 127, 997, 472);
		formToolkit.paintBordersFor(table_3);
		table_3.setHeaderVisible(true);
		table_3.setLinesVisible(true);
		
		Menu menu_2 = new Menu(table_3);
		table_3.setMenu(menu_2);
		
		MenuItem mntmNewItem_2 = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * 发票右键修改
				 */
				ui_alter_invoice.setCon(getDataBaseLink().getCon());
				/**
				 * 将所选行值传入UI
				 */
				//将所选行信息传进去
				String [] cStrings=new String[3];
				
				cStrings[0]=table_3.getItem(table_3.getSelectionIndex()).getText(0);
				cStrings[1]=table_3.getItem(table_3.getSelectionIndex()).getText(1);
				cStrings[2]=table_3.getItem(table_3.getSelectionIndex()).getText(10);
				
				for(String ss1 : cStrings) {
					System.out.println(ss1);
					//将数据传入ui
					}
				ui_alter_invoice.setcStrings(cStrings);
				ui_alter_invoice.open();
				/**
				 * 更新表格
				 */
				/**
				 * 刷新发票列表
				 */
				String sql="select no,ino,cno,cname,ono,name,money,tax,ptax,date,taxerNo"
						
						+ " from invoice_view";
				System.out.println(sql);
				Statement statement;
				try {
					statement = (Statement) getDataBaseLink().getCon().createStatement();
					ResultSet rs = statement.executeQuery(sql);
					List<String[]> list;
					list=new ArrayList<String[]>();
					while(rs.next()) {
						String [] cStrings1=new String[11];
						cStrings1[0]=rs.getString("no");//发票编号
						cStrings1[1]=rs.getString("ino");//发票号码
						cStrings1[2]=rs.getString("cno");//客户编号
						cStrings1[3]=rs.getString("cname");//客户名称
						cStrings1[4]=rs.getString("ono");//订单编号
						cStrings1[5]=rs.getString("name");//开票方名称
						cStrings1[6]=rs.getString("money");//发票金额
						cStrings1[7]=rs.getString("tax");//发票税额
						cStrings1[8]=rs.getString("ptax");//价税合计
						cStrings1[9]=rs.getString("date");//开票日期
						cStrings1[10]=rs.getString("taxerNo");//纳税人识别号
						
						list.add(cStrings1);
													}
						//清空表格
						for(TableColumn deleteColumn : table_3.getColumns()) {
							deleteColumn.dispose();
						}
						table_3.removeAll();
						//添加数据
						String[] tableHeader= {"发票编号","发票号码","客户编号","客户名称","订单编号","开票方名称","发票金额","发票税额","价税合计","开票日期","纳税人识别号"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table_3,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : list) {
	//							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table_3, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
							table_3.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table_3);
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmNewItem_2.setText("\u4FEE\u6539");
		
		MenuItem mntmNewItem_3 = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * 发票右键删除
				 */
				/**
				 * 将所选行值传入UI
				 */
				//将所选行信息传进去
				String  id=null;
				
				id=table_3.getItem(table_3.getSelectionIndex()).getText(0);
				
				
//				ui_alter_invoice.setcStrings(id);
				ui_delete_invoice.setId(id);
				ui_delete_invoice.setCon(getDataBaseLink().getCon());
				ui_delete_invoice.open();
				
				/**
				 * 刷新发票列表
				 */
				String sql="select no,ino,cno,cname,ono,name,money,tax,ptax,date,taxerNo"
						
						+ " from invoice_view";
				System.out.println(sql);
				Statement statement;
				try {
					statement = (Statement) getDataBaseLink().getCon().createStatement();
					ResultSet rs = statement.executeQuery(sql);
					List<String[]> list;
					list=new ArrayList<String[]>();
					while(rs.next()) {
						String [] cStrings1=new String[11];
						cStrings1[0]=rs.getString("no");//发票编号
						cStrings1[1]=rs.getString("ino");//发票号码
						cStrings1[2]=rs.getString("cno");//客户编号
						cStrings1[3]=rs.getString("cname");//客户名称
						cStrings1[4]=rs.getString("ono");//订单编号
						cStrings1[5]=rs.getString("name");//开票方名称
						cStrings1[6]=rs.getString("money");//发票金额
						cStrings1[7]=rs.getString("tax");//发票税额
						cStrings1[8]=rs.getString("ptax");//价税合计
						cStrings1[9]=rs.getString("date");//开票日期
						cStrings1[10]=rs.getString("taxerNo");//纳税人识别号
						
						list.add(cStrings1);
													}
						//清空表格
						for(TableColumn deleteColumn : table_3.getColumns()) {
							deleteColumn.dispose();
						}
						table_3.removeAll();
						//添加数据
						String[] tableHeader= {"发票编号","发票号码","客户编号","客户名称","订单编号","开票方名称","发票金额","发票税额","价税合计","开票日期","纳税人识别号"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table_3,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : list) {
	//							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table_3, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
							table_3.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table_3);
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmNewItem_3.setText("\u5220\u9664");
		
		//发票表右键菜单
		table_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(e.button==java.awt.event.MouseEvent.BUTTON3) {
//					System.out.println("右键点击");
					menu_2.setVisible(true);
					
				}
			}
		});
		
		Label label_16 = formToolkit.createLabel(composite_6, "\u6309\u5BA2\u6237\u540D\u67E5\uFF1A", SWT.NONE);
		label_16.setBounds(10, 100, 75, 17);
		
		text_7 = formToolkit.createText(composite_6, "New Text", SWT.NONE);
		text_7.setText("");
		text_7.setBounds(96, 96, 80, 23);
		
		Label label_17 = formToolkit.createLabel(composite_6, "\u6309\u53D1\u7968\u91D1\u989D\u67E5\uFF1A", SWT.NONE);
		label_17.setBounds(190, 104, 90, 17);
		
		text_8 = formToolkit.createText(composite_6, "New Text", SWT.NONE);
		text_8.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
		});
		text_8.setText("");
		text_8.setBounds(292, 101, 63, 23);
		
		Label label_18 = formToolkit.createLabel(composite_6, "~", SWT.NONE);
		label_18.setBounds(363, 108, 15, 17);
		
		text_9 = formToolkit.createText(composite_6, "New Text", SWT.NONE);
		text_9.setText("");
		text_9.setBounds(384, 101, 63, 23);
		
		Label label_19 = formToolkit.createLabel(composite_6, "\u5143", SWT.NONE);
		label_19.setBounds(453, 104, 20, 17);
		
		Button button_16 = formToolkit.createButton(composite_6, "\u5F00\u59CB\u67E5\u8BE2", SWT.NONE);
		button_16.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * 发票查询
				 */
				/**
				 * 当且仅当两个都不为空时进行查询
				 */
				String sql=null;
				if((!text_7.getText().equals(""))) {
					System.out.println("按客户名查");
					sql="select no,ino,cno,cname,ono,name,money,tax,ptax,date,taxerNo from invoice_view where cname like '%"
							+ text_7.getText()+"%';";
				}
				else if(!text_9.getText().equals("")&&(Double.valueOf(text_9.getText())>Double.valueOf(text_8.getText()))) {
					System.out.println("按发票金额查");
					sql="select no,ino,cno,cname,ono,name,money,tax,ptax,date,taxerNo from invoice_view where money "
							+ ">"+Double.valueOf(text_8.getText())+" and money < "+Double.valueOf(text_9.getText())+";";
				}
				
				
				if(!(text_7.getText().equals("")&&text_8.getText().equals("")&&text_9.getText().equals(""))) {
					System.out.println(sql);
					Statement statement;
					try {
						statement = (Statement) getDataBaseLink().getCon().createStatement();
						ResultSet rs = statement.executeQuery(sql);
						List<String[]> list;
						list=new ArrayList<String[]>();
						while(rs.next()) {
							String [] cStrings=new String[11];
							cStrings[0]=rs.getString("no");//发票编号
							cStrings[1]=rs.getString("ino");//发票号码
							cStrings[2]=rs.getString("cno");//客户编号
							cStrings[3]=rs.getString("cname");//客户名称
							cStrings[4]=rs.getString("ono");//订单编号
							cStrings[5]=rs.getString("name");//开票方名称
							cStrings[6]=rs.getString("money");//发票金额
							cStrings[7]=rs.getString("tax");//发票税额
							cStrings[8]=rs.getString("ptax");//价税合计
							cStrings[9]=rs.getString("date");//开票日期
							cStrings[10]=rs.getString("taxerNo");//纳税人识别号
							
							list.add(cStrings);
														}
							//清空表格
							for(TableColumn deleteColumn : table_3.getColumns()) {
								deleteColumn.dispose();
							}
							table_3.removeAll();
							//添加数据
							String[] tableHeader= {"发票编号","发票号码","客户编号","客户名称","订单编号","开票方名称","发票金额","发票税额","价税合计","开票日期","纳税人识别号"};
							for(int i=0;i<tableHeader.length;i++) {
								TableColumn tableColumn=new TableColumn(table_3,SWT.CENTER);
								//tableColumn.setWidth(200);
								tableColumn.setText(tableHeader[i]);
								tableColumn.setMoveable(true);
							}
							TableItem item; 
							
							
							for(String[] attribute : list) {
		//							System.out.println(attribute[0]+" "+attribute[1]);
								//添加入表格
								item = new TableItem(table_3, SWT.NONE);  
								item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10]});
							}
							// 重新布局表格  
							for (int i = 0; i < tableHeader.length; i++)  
							{  
								table_3.getColumn(i).pack();  
							}
							formToolkit.paintBordersFor(table_3);
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		button_16.setBounds(479, 97, 80, 27);
		
		Button button_17 = formToolkit.createButton(composite_6, "\u6E05\u7A7A/\u5237\u65B0", SWT.NONE);
		button_17.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_7.setText("");
				text_8.setText("0");
				text_9.setText("");
				
				
				/**
				 * 刷新发票列表
				 */
				String sql="select no,ino,cno,cname,ono,name,money,tax,ptax,date,taxerNo"
						
						+ " from invoice_view";
				System.out.println(sql);
				Statement statement;
				try {
					statement = (Statement) getDataBaseLink().getCon().createStatement();
					ResultSet rs = statement.executeQuery(sql);
					List<String[]> list;
					list=new ArrayList<String[]>();
					while(rs.next()) {
						String [] cStrings=new String[11];
						cStrings[0]=rs.getString("no");//发票编号
						cStrings[1]=rs.getString("ino");//发票号码
						cStrings[2]=rs.getString("cno");//客户编号
						cStrings[3]=rs.getString("cname");//客户名称
						cStrings[4]=rs.getString("ono");//订单编号
						cStrings[5]=rs.getString("name");//开票方名称
						cStrings[6]=rs.getString("money");//发票金额
						cStrings[7]=rs.getString("tax");//发票税额
						cStrings[8]=rs.getString("ptax");//价税合计
						cStrings[9]=rs.getString("date");//开票日期
						cStrings[10]=rs.getString("taxerNo");//纳税人识别号
						
						list.add(cStrings);
													}
						//清空表格
						for(TableColumn deleteColumn : table_3.getColumns()) {
							deleteColumn.dispose();
						}
						table_3.removeAll();
						//添加数据
						String[] tableHeader= {"发票编号","发票号码","客户编号","客户名称","订单编号","开票方名称","发票金额","发票税额","价税合计","开票日期","纳税人识别号"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table_3,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : list) {
	//							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table_3, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
							table_3.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table_3);
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_17.setBounds(565, 94, 80, 27);
		
		Button button_18 = formToolkit.createButton(composite_6, "\u65B0\u589E\u53D1\u7968", SWT.NONE);
		button_18.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ui_add_invoice.setCon(getDataBaseLink().getCon());
				ui_add_invoice.open();
			}
		});
		button_18.setBounds(927, 96, 80, 27);
		
		Label lblNewLabel_4 = formToolkit.createLabel(composite_6, "\u8868\u683C\u53F3\u952E\u83DC\u5355", SWT.NONE);
		lblNewLabel_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_4.setBounds(690, 100, 100, 17);
		
		Composite composite_8 = new Composite(composite_2, SWT.NONE);
		formToolkit.adapt(composite_8);
		formToolkit.paintBordersFor(composite_8);
		composite_8.setVisible(false);
		
		Label label_6 = new Label(composite_8, SWT.NONE);
		label_6.setBounds(0, 0, 61, 17);
		formToolkit.adapt(label_6, true, true);
		label_6.setText("\u7EDF\u8BA1\u5206\u6790\u9875");
		
		table_4 = formToolkit.createTable(composite_8, SWT.NONE);
		table_4.setBounds(10, 150, 375, 449);
		formToolkit.paintBordersFor(table_4);
		table_4.setHeaderVisible(true);
		table_4.setLinesVisible(true);
		
		table_5 = formToolkit.createTable(composite_8, SWT.NONE);
		table_5.setBounds(460, 150, 547, 449);
		formToolkit.paintBordersFor(table_5);
		table_5.setHeaderVisible(true);
		table_5.setLinesVisible(true);
		
		Button button_19 = formToolkit.createButton(composite_8, "\u7EDF\u8BA1\u56FE", SWT.NONE);
		button_19.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//统计图
				Chart_count chart_count=new Chart_count();
				/**
				 * 传入list
				 */
				//客户订单报表
				String sql="select cname,order_sum,money_sum"
						
						+ " from c_statement "
						+ "where order_sum>0"
						+ " order by money_sum DESC";
				Statement statement;
				try {
					statement = (Statement) getDataBaseLink().getCon().createStatement();
					ResultSet rs = statement.executeQuery(sql);
					List<String[]> list;
					list=new ArrayList<String[]>();
					while(rs.next()) {
						String [] cStrings=new String[3];
						cStrings[0]=rs.getString("cname");
						cStrings[1]=rs.getString("order_sum");
						cStrings[2]=rs.getString("money_sum");
						
						list.add(cStrings);
						}

						chart_count.setList(list);
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				chart_count.chart();
			}
		});
		button_19.setBounds(10, 111, 80, 27);
		
		
		
		Composite composite_5 = new Composite(composite_2, SWT.NONE);
		formToolkit.adapt(composite_5);
		formToolkit.paintBordersFor(composite_5);
		composite_5.setVisible(false);
		
		Label label_3 = new Label(composite_5, SWT.NONE);
		label_3.setBounds(221, 25, 109, 17);
		formToolkit.adapt(label_3, true, true);
		label_3.setText("\u8BA2\u5355\u7BA1\u7406\u9875");
		
		
//		Menu menu_1 = new Menu(table_2);
		
		table_2 = formToolkit.createTable(composite_5, SWT.FULL_SELECTION | SWT.MULTI);
		
		table_2.setBounds(10, 127, 997, 472);
		formToolkit.paintBordersFor(table_2);
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);
		
		Menu menu_1 = new Menu(table_2);
		table_2.setMenu(menu_1);
		
		MenuItem mntmNewItem = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ui_alter_order.setCon(getDataBaseLink().getCon());
				//将所选行信息传进去
				String [] cStrings=new String[8];
				
				cStrings[0]=table_2.getItem(table_2.getSelectionIndex()).getText(0);
				cStrings[1]=table_2.getItem(table_2.getSelectionIndex()).getText(1);
				cStrings[2]=table_2.getItem(table_2.getSelectionIndex()).getText(2);
				cStrings[3]=table_2.getItem(table_2.getSelectionIndex()).getText(3);
				cStrings[4]=table_2.getItem(table_2.getSelectionIndex()).getText(4);
				cStrings[5]=table_2.getItem(table_2.getSelectionIndex()).getText(5);
				cStrings[6]=table_2.getItem(table_2.getSelectionIndex()).getText(6);
				cStrings[7]=table_2.getItem(table_2.getSelectionIndex()).getText(7);
				
				
				ui_alter_order.setcStrings(cStrings);
				ui_alter_order.open();
				/**
				 * 刷新订单表
				 */
				
				/**
				 * 刷新订单列表
				 */
				String sql="select no,pno,cno,num,way,address,pname,price,cname,money"
						
						+ " from order_view";
				System.out.println(sql);
				Statement statement;
				try {
					statement = (Statement) getDataBaseLink().getCon().createStatement();
					ResultSet rs = statement.executeQuery(sql);
					List<String[]> list;
					list=new ArrayList<String[]>();
					while(rs.next()) {
						String [] cStrings1=new String[8];
						cStrings1[0]=rs.getString("no");//订单编号
						cStrings1[1]=rs.getString("cname");//客户名
						cStrings1[2]=rs.getString("pname");//货物名
						cStrings1[3]=rs.getString("price");//价格
						cStrings1[4]=rs.getString("num");//订购数量
						cStrings1[5]=rs.getString("money");//总额
						cStrings1[6]=rs.getString("way");//付款方式
						cStrings1[7]=rs.getString("address");//发货地址
						
						list.add(cStrings1);
													}
						//清空表格
						for(TableColumn deleteColumn : table_2.getColumns()) {
							deleteColumn.dispose();
						}
						table_2.removeAll();
						//添加数据
						String[] tableHeader= {"订单编号","客户名称","货物名称","价格","订购数量","总计","付款方式","发货地址"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table_2,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : list) {
	//							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table_2, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
							table_2.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table_2);
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmNewItem.setText("\u4FEE\u6539");
		
		MenuItem mntmNewItem_1 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ui_delete_order.setCon(getDataBaseLink().getCon());
				
				//将所选行信息传进去
				String [] cStrings=new String[8];
				
				cStrings[0]=table_2.getItem(table_2.getSelectionIndex()).getText(0);
				cStrings[1]=table_2.getItem(table_2.getSelectionIndex()).getText(1);
				cStrings[2]=table_2.getItem(table_2.getSelectionIndex()).getText(2);
				cStrings[3]=table_2.getItem(table_2.getSelectionIndex()).getText(3);
				cStrings[4]=table_2.getItem(table_2.getSelectionIndex()).getText(4);
				cStrings[5]=table_2.getItem(table_2.getSelectionIndex()).getText(5);
				cStrings[6]=table_2.getItem(table_2.getSelectionIndex()).getText(6);
				cStrings[7]=table_2.getItem(table_2.getSelectionIndex()).getText(7);
				
				ui_delete_order.setcStrings(cStrings);
				ui_delete_order.open();
				
				
				
				/**
				 * 刷新订单列表
				 */
				String sql="select no,pno,cno,num,way,address,pname,price,cname,money"
						
						+ " from order_view";
				System.out.println(sql);
				Statement statement;
				try {
					statement = (Statement) getDataBaseLink().getCon().createStatement();
					ResultSet rs = statement.executeQuery(sql);
					List<String[]> list;
					list=new ArrayList<String[]>();
					while(rs.next()) {
						String [] cStrings1=new String[8];
						cStrings1[0]=rs.getString("no");//订单编号
						cStrings1[1]=rs.getString("cname");//客户名
						cStrings1[2]=rs.getString("pname");//货物名
						cStrings1[3]=rs.getString("price");//价格
						cStrings1[4]=rs.getString("num");//订购数量
						cStrings1[5]=rs.getString("money");//总额
						cStrings1[6]=rs.getString("way");//付款方式
						cStrings1[7]=rs.getString("address");//发货地址
						
						list.add(cStrings1);
													}
						//清空表格
						for(TableColumn deleteColumn : table_2.getColumns()) {
							deleteColumn.dispose();
						}
						table_2.removeAll();
						//添加数据
						String[] tableHeader= {"订单编号","客户名称","货物名称","价格","订购数量","总计","付款方式","发货地址"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table_2,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : list) {
	//							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table_2, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
							table_2.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table_2);
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmNewItem_1.setText("\u5220\u9664");
		
		Button button_13 = formToolkit.createButton(composite_5, "\u65B0\u589E\u8BA2\u5355", SWT.NONE);
		button_13.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//传入con
				ui_add_order.setCon(getDataBaseLink().getCon());
				ui_add_order.open();
			}
		});
		button_13.setBounds(927, 94, 80, 27);
		
		//订单表
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				/**
				 * 订单列表
				 * 右键菜单
				 * 修改-弹框
				 * 删除-弹框确认
				 * 参见1163
				 */
				if(e.button==java.awt.event.MouseEvent.BUTTON3) {
//					System.out.println("右键点击");
					menu_1.setVisible(true);
					
				}
			}
		});
		
		Label label_14 = formToolkit.createLabel(composite_5, "\u6309\u5BA2\u6237\u67E5\uFF1A", SWT.NONE);
		label_14.setBounds(10, 104, 61, 17);
		
		text_5 = formToolkit.createText(composite_5, "New Text", SWT.NONE);
		text_5.setText("");
		text_5.setBounds(77, 98, 80, 23);
		
		Label label_15 = formToolkit.createLabel(composite_5, "\u6309\u8D27\u7269\u67E5\uFF1A", SWT.NONE);
		label_15.setBounds(163, 104, 61, 17);
		
		text_6 = formToolkit.createText(composite_5, "New Text", SWT.NONE);
		text_6.setText("");
		text_6.setBounds(234, 98, 80, 23);
		
		Button button_14 = formToolkit.createButton(composite_5, "\u5F00\u59CB\u67E5\u8BE2", SWT.NONE);
		button_14.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * 当且仅当两个都不为空时进行查询
				 */
				String sql=null;
				if((!text_5.getText().equals(""))) {
					System.out.println("按客户名查");
					sql="select no,pno,cno,num,way,address,pname,price,cname,money from order_view where cname like '%"
							+ text_5.getText()+"%';";
				}
				else if(!text_6.getText().equals("")) {
					System.out.println("按货物查");
					sql="select no,pno,cno,num,way,address,pname,price,cname,money from order_view where pname like '%"
							+ text_6.getText()+"%';";
				}
				
				
				if(!(text_5.getText().equals("")&&text_6.getText().equals(""))) {
					System.out.println(sql);
					Statement statement;
					try {
						statement = (Statement) getDataBaseLink().getCon().createStatement();
						ResultSet rs = statement.executeQuery(sql);
						List<String[]> list;
						list=new ArrayList<String[]>();
						while(rs.next()) {
							String [] cStrings=new String[8];
							cStrings[0]=rs.getString("no");//订单编号
							cStrings[1]=rs.getString("cname");//客户名
							cStrings[2]=rs.getString("pname");//货物名
							cStrings[3]=rs.getString("price");//价格
							cStrings[4]=rs.getString("num");//订购数量
							cStrings[5]=rs.getString("money");//总额
							cStrings[6]=rs.getString("way");//付款方式
							cStrings[7]=rs.getString("address");//发货地址
							
							list.add(cStrings);
														}
							//清空表格
							for(TableColumn deleteColumn : table_2.getColumns()) {
								deleteColumn.dispose();
							}
							table_2.removeAll();
							//添加数据
							String[] tableHeader= {"订单编号","客户名称","货物名称","价格","订购数量","总计","付款方式","发货地址"};
							for(int i=0;i<tableHeader.length;i++) {
								TableColumn tableColumn=new TableColumn(table_2,SWT.CENTER);
								//tableColumn.setWidth(200);
								tableColumn.setText(tableHeader[i]);
								tableColumn.setMoveable(true);
							}
							TableItem item; 
							
							
							for(String[] attribute : list) {
		//							System.out.println(attribute[0]+" "+attribute[1]);
								//添加入表格
								item = new TableItem(table_2, SWT.NONE);  
								item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7]});
							}
							// 重新布局表格  
							for (int i = 0; i < tableHeader.length; i++)  
							{  
								table_2.getColumn(i).pack();  
							}
							formToolkit.paintBordersFor(table_2);
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		button_14.setBounds(320, 94, 80, 27);
		
		Button button_15 = formToolkit.createButton(composite_5, "\u6E05\u7A7A/\u5237\u65B0", SWT.NONE);
		button_15.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				text_5.setText("");
				text_6.setText("");
				
				
				/**
				 * 刷新数据
				 */
				String sql="select no,pno,cno,num,way,address,pname,price,cname,money"
						
						+ " from order_view";
				Statement statement;
				try {
					statement = (Statement) getDataBaseLink().getCon().createStatement();
					ResultSet rs = statement.executeQuery(sql);
					List<String[]> list;
					list=new ArrayList<String[]>();
					while(rs.next()) {
						String [] cStrings=new String[8];
						cStrings[0]=rs.getString("no");//订单编号
						cStrings[1]=rs.getString("cname");//客户名
						cStrings[2]=rs.getString("pname");//货物名
						cStrings[3]=rs.getString("price");//价格
						cStrings[4]=rs.getString("num");//订购数量
						cStrings[5]=rs.getString("money");//总额
						cStrings[6]=rs.getString("way");//付款方式
						cStrings[7]=rs.getString("address");//发货地址
						
						list.add(cStrings);
													}
						//清空表格
						for(TableColumn deleteColumn : table_2.getColumns()) {
							deleteColumn.dispose();
						}
						table_2.removeAll();
						//添加数据
						String[] tableHeader= {"订单编号","客户名称","货物名称","价格","订购数量","总计","付款方式","发货地址"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table_2,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : list) {
	//							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table_2, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
							table_2.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table_2);
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_15.setBounds(406, 94, 80, 27);
		
		Label lblNewLabel_2 = formToolkit.createLabel(composite_5, "\u8868\u683C\u53F3\u952E\u83DC\u5355", SWT.NONE);
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setBounds(521, 100, 130, 17);
		
		Composite composite_7 = new Composite(composite_2, SWT.NONE);
		formToolkit.adapt(composite_7);
		formToolkit.paintBordersFor(composite_7);
		composite_7.setVisible(false);
		
		Label label_5 = new Label(composite_7, SWT.NONE);
		label_5.setBounds(709, 10, 61, 17);
		formToolkit.adapt(label_5, true, true);
		label_5.setText("\u8D27\u7269\u7BA1\u7406\u9875");
		
		table_1 = formToolkit.createTable(composite_7, SWT.FULL_SELECTION);
		
		table_1.setBounds(10, 127, 997, 472);
		formToolkit.paintBordersFor(table_1);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		Menu menu_3 = new Menu(table_1);
		table_1.setMenu(menu_3);
		
		MenuItem mntmNewItem_4 = new MenuItem(menu_3, SWT.NONE);
		mntmNewItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * 货物右键菜单修改
				 * 货物编号
				 * 
				 */
				ui_alter_pro.setCon(getDataBaseLink().getCon());
				//将所选行信息传进去
				String [] cStrings=new String[4];
				
				cStrings[0]=table_1.getItem(table_1.getSelectionIndex()).getText(0);
				cStrings[1]=table_1.getItem(table_1.getSelectionIndex()).getText(1);
				cStrings[2]=table_1.getItem(table_1.getSelectionIndex()).getText(3);
				cStrings[3]=table_1.getItem(table_1.getSelectionIndex()).getText(4);
				ui_alter_pro.setcStrings(cStrings);
				ui_alter_pro.open();
				
				//更新数据
				String sql="select no,name,type,inprice,outprice,dno,unit,k,s,a,c,pname"
						
						+ " from p_view";
				Statement statement;
				try {
					statement = (Statement) getDataBaseLink().getCon().createStatement();
					ResultSet rs = statement.executeQuery(sql);
					List<String[]> list;
					list=new ArrayList<String[]>();
					while(rs.next()) {
						String [] cStrings1=new String[12];
						cStrings1[0]=rs.getString("no");
						cStrings1[1]=rs.getString("name");
						cStrings1[2]=rs.getString("type");
						cStrings1[3]=rs.getString("inprice");
						cStrings1[4]=rs.getString("outprice");
						cStrings1[5]=rs.getString("dno");
						cStrings1[6]=rs.getString("unit");
						cStrings1[7]=rs.getString("k");
						cStrings1[8]=rs.getString("s");
						cStrings1[9]=rs.getString("a");
						cStrings1[10]=rs.getString("c");
						cStrings1[11]=rs.getString("pname");
						list.add(cStrings1);
													}
						//清空表格
						for(TableColumn deleteColumn : table_1.getColumns()) {
							deleteColumn.dispose();
						}
						table_1.removeAll();
						//添加数据
						String[] tableHeader= {"货物编号","货物名称","货物类型","订购价","市场价","仓库编号","单位","可售库存","实际库存","安全库存","参考补货","供应商"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table_1,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : list) {
	//							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table_1, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10],attribute[11]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
							table_1.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table_1);
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmNewItem_4.setText("\u4FEE\u6539");
		
		MenuItem mntmNewItem_5 = new MenuItem(menu_3, SWT.NONE);
		mntmNewItem_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * 货物右键删除
				 */
				ui_delete_pro.setCon(getDataBaseLink().getCon());
				//将所选行信息传进去
				String [] cStrings=new String[4];
				
				cStrings[0]=table_1.getItem(table_1.getSelectionIndex()).getText(0);
				cStrings[1]=table_1.getItem(table_1.getSelectionIndex()).getText(1);
				cStrings[2]=table_1.getItem(table_1.getSelectionIndex()).getText(3);
				cStrings[3]=table_1.getItem(table_1.getSelectionIndex()).getText(4);
				ui_delete_pro.setId(cStrings[0]);
				ui_delete_pro.open();
				//更新数据
				String sql="select no,name,type,inprice,outprice,dno,unit,k,s,a,c,pname"
						
						+ " from p_view";
				Statement statement;
				try {
					statement = (Statement) getDataBaseLink().getCon().createStatement();
					ResultSet rs = statement.executeQuery(sql);
					List<String[]> list;
					list=new ArrayList<String[]>();
					while(rs.next()) {
						String [] cStrings1=new String[12];
						cStrings1[0]=rs.getString("no");
						cStrings1[1]=rs.getString("name");
						cStrings1[2]=rs.getString("type");
						cStrings1[3]=rs.getString("inprice");
						cStrings1[4]=rs.getString("outprice");
						cStrings1[5]=rs.getString("dno");
						cStrings1[6]=rs.getString("unit");
						cStrings1[7]=rs.getString("k");
						cStrings1[8]=rs.getString("s");
						cStrings1[9]=rs.getString("a");
						cStrings1[10]=rs.getString("c");
						cStrings1[11]=rs.getString("pname");
						list.add(cStrings1);
													}
						//清空表格
						for(TableColumn deleteColumn : table_1.getColumns()) {
							deleteColumn.dispose();
						}
						table_1.removeAll();
						//添加数据
						String[] tableHeader= {"货物编号","货物名称","货物类型","订购价","市场价","仓库编号","单位","可售库存","实际库存","安全库存","参考补货","供应商"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table_1,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : list) {
	//							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table_1, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10],attribute[11]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
							table_1.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table_1);
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmNewItem_5.setText("\u5220\u9664");
		
		//货物表右键菜单
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(e.button==java.awt.event.MouseEvent.BUTTON3) {
//					System.out.println("右键点击");
					menu_3.setVisible(true);
					
				}
			}
		});
		
		
		Button button_9 = formToolkit.createButton(composite_7, "\u5546\u54C1\u9009\u9879", SWT.NONE);
		button_9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * 商品选项
				 */
				ui_product_button.setCon(getDataBaseLink().getCon());
				ui_product_button.open();
				
			}
		});
		button_9.setBounds(780, 94, 80, 27);
		
		Button button_10 = formToolkit.createButton(composite_7, "\u5E93\u5B58\u9009\u9879", SWT.NONE);
		button_10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * 仓库选项
				 */
				ui_cangku_button.setCon(getDataBaseLink().getCon());
				ui_cangku_button.open();
				
			}
		});
		button_10.setBounds(866, 94, 80, 27);
		
		Label label_11 = formToolkit.createLabel(composite_7, "\u6309\u8D27\u7269\u540D\u67E5\uFF1A", SWT.NONE);
		label_11.setBounds(10, 94, 72, 17);
		
		text_2 = formToolkit.createText(composite_7, "New Text", SWT.NONE);
		text_2.setText("");
		text_2.setBounds(88, 88, 80, 23);
		
		Label label_12 = formToolkit.createLabel(composite_7, "\u6309\u7C7B\u578B\u67E5\uFF1A", SWT.NONE);
		label_12.setBounds(174, 94, 61, 17);
		
		text_3 = formToolkit.createText(composite_7, "New Text", SWT.NONE);
		text_3.setText("");
		text_3.setBounds(241, 88, 80, 23);
		
		Label label_13 = formToolkit.createLabel(composite_7, "\u6309\u4F9B\u5E94\u5546\u67E5\uFF1A", SWT.NONE);
		label_13.setBounds(327, 94, 73, 17);
		
		text_4 = formToolkit.createText(composite_7, "New Text", SWT.NONE);
		text_4.setText("");
		text_4.setBounds(401, 90, 80, 23);
		
		Button button_11 = formToolkit.createButton(composite_7, "\u5F00\u59CB\u67E5\u8BE2", SWT.NONE);
		button_11.addSelectionListener(new SelectionAdapter() {
			/**
			 * 客户查询1007
			 * 货物管理
			 * 查询按钮
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * 当且仅当三个都不为空时进行查询
				 * String sql="select no,name,type,inprice,outprice,dno,unit,k,s,a,c,pname"
						
						+ " from p_view";
				 */
				String sql=null;
				if((!text_2.getText().equals(""))) {
					System.out.println("按名查");
					sql="select no,name,type,inprice,outprice,dno,unit,k,s,a,c,pname from p_view where name like '%"
							+ text_2.getText()+"%';";
				}
				else if(!text_3.getText().equals("")) {
					System.out.println("按类型查");
					sql="select no,name,type,inprice,outprice,dno,unit,k,s,a,c,pname from p_view where type like '%"
							+ text_3.getText()+"%';";
				}
				else if(!text_4.getText().equals("")) {
					System.out.println("按供应商区查");
					sql="select no,name,type,inprice,outprice,dno,unit,k,s,a,c,pname from p_view where pname like '%"
							+ text_4.getText()+"%';";
				}
				
				if(!(text_2.getText().equals("")&&text_3.getText().equals("")&&text_4.getText().equals(""))) {
					System.out.println(sql);
//					tableflag="cccc";
					Statement statement;
					try {
						statement = (Statement) getDataBaseLink().getCon().createStatement();
						ResultSet rs = statement.executeQuery(sql);
						List<String[]> list;
						list=new ArrayList<String[]>();
						while(rs.next()) {
							String [] cStrings=new String[12];
							cStrings[0]=rs.getString("no");
							cStrings[1]=rs.getString("name");
							cStrings[2]=rs.getString("type");
							cStrings[3]=rs.getString("inprice");
							cStrings[4]=rs.getString("outprice");
							cStrings[5]=rs.getString("dno");
							cStrings[6]=rs.getString("unit");
							cStrings[7]=rs.getString("k");
							cStrings[8]=rs.getString("s");
							cStrings[9]=rs.getString("a");
							cStrings[10]=rs.getString("c");
							cStrings[11]=rs.getString("pname");
							list.add(cStrings);
														}
							//清空表格
							for(TableColumn deleteColumn : table_1.getColumns()) {
								deleteColumn.dispose();
							}
							table_1.removeAll();
							//添加数据
							String[] tableHeader= {"货物编号","货物名称","货物类型","订购价","市场价","仓库编号","单位","可售库存","实际库存","安全库存","参考补货","供应商"};
							for(int i=0;i<tableHeader.length;i++) {
								TableColumn tableColumn=new TableColumn(table_1,SWT.CENTER);
								//tableColumn.setWidth(200);
								tableColumn.setText(tableHeader[i]);
								tableColumn.setMoveable(true);
							}
							TableItem item; 
							
							
							for(String[] attribute : list) {
		//							System.out.println(attribute[0]+" "+attribute[1]);
								//添加入表格
								item = new TableItem(table_1, SWT.NONE);  
								item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10],attribute[11]});
							}
							// 重新布局表格  
							for (int i = 0; i < tableHeader.length; i++)  
							{  
								table_1.getColumn(i).pack();  
							}
							formToolkit.paintBordersFor(table_1);
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		button_11.setBounds(494, 89, 80, 27);
		
		Button button_12 = formToolkit.createButton(composite_7, "\u6E05\u7A7A/\u5237\u65B0", SWT.NONE);
		
		button_12.setBounds(587, 89, 80, 27);
		
		Label lblNewLabel_3 = formToolkit.createLabel(composite_7, "\u8868\u683C\u53F3\u952E\u83DC\u5355", SWT.NONE);
		lblNewLabel_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_3.setBounds(690, 100, 100, 17);
		
		button_12.addSelectionListener(new SelectionAdapter() {
			/**
			 * 货物管理
			 * 清空查询按钮
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				text_2.setText("");
				text_3.setText("");
				text_4.setText("");
				
				
				//返回列表
				String sql="select no,name,type,inprice,outprice,dno,unit,k,s,a,c,pname"
						
						+ " from p_view";
				Statement statement;
				try {
					statement = (Statement) getDataBaseLink().getCon().createStatement();
					ResultSet rs = statement.executeQuery(sql);
					List<String[]> list;
					list=new ArrayList<String[]>();
					while(rs.next()) {
						String [] cStrings=new String[12];
						cStrings[0]=rs.getString("no");
						cStrings[1]=rs.getString("name");
						cStrings[2]=rs.getString("type");
						cStrings[3]=rs.getString("inprice");
						cStrings[4]=rs.getString("outprice");
						cStrings[5]=rs.getString("dno");
						cStrings[6]=rs.getString("unit");
						cStrings[7]=rs.getString("k");
						cStrings[8]=rs.getString("s");
						cStrings[9]=rs.getString("a");
						cStrings[10]=rs.getString("c");
						cStrings[11]=rs.getString("pname");
						list.add(cStrings);
													}
						//清空表格
						for(TableColumn deleteColumn : table_1.getColumns()) {
							deleteColumn.dispose();
						}
						table_1.removeAll();
						//添加数据
						String[] tableHeader= {"货物编号","货物名称","货物类型","订购价","市场价","仓库编号","单位","可售库存","实际库存","安全库存","参考补货","供应商"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table_1,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : list) {
	//							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table_1, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10],attribute[11]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
							table_1.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table_1);
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		Composite composite_1 = formToolkit.createComposite(composite, SWT.NONE);
		fd_composite_9.bottom = new FormAttachment(composite_1, -6);
		
		Button button_4 = formToolkit.createButton(composite_9, "\u540E\u53F0\u7BA1\u7406", SWT.NONE);
		
		FormData fd_composite_1 = new FormData();
		fd_composite_1.top = new FormAttachment(0, 210);
		fd_composite_1.bottom = new FormAttachment(100, -10);
		fd_composite_1.right = new FormAttachment(composite_10, -6);
		fd_composite_1.left = new FormAttachment(0);
		
		
		
		UI_importKHbyhand ui_importKHbyhand=new UI_importKHbyhand(shell);
		
		Button button_2 = formToolkit.createButton(composite_10, "\u6DFB\u52A0\u5BA2\u6237", SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//添加客户
				tableflag="cccc";
//				UI_importKHbyhand ui_importKHbyhand=new UI_importKHbyhand(shell);
				ui_importKHbyhand.setCon(getDataBaseLink().getCon());
				ui_importKHbyhand.open();
				//刷新客户信息
				//清空表格
				for(TableColumn deleteColumn : table.getColumns()) {
					deleteColumn.dispose();
				}
				table.removeAll();
				//添加数据
				String[] tableHeader= {"客户编号","客户名称","客户类型","客户地区","联系人","业务员","联系电话","邮箱","录入日期","修改日期","地址","备注"};
				for(int i=0;i<tableHeader.length;i++) {
					TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
					//tableColumn.setWidth(200);
					tableColumn.setText(tableHeader[i]);
					tableColumn.setMoveable(true);
				}
				TableItem item; 
				
				try {
					for(String[] attribute : ui_importKHbyhand.getInsert_c_KHbyhand().check_c()) {
//						System.out.println(attribute[0]+" "+attribute[1]);
						//添加入表格
						item = new TableItem(table, SWT.NONE);  
						item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10],attribute[11]});
					}
					// 重新布局表格  
					for (int i = 0; i < tableHeader.length; i++)  
					{  
					    table.getColumn(i).pack();  
					}
					formToolkit.paintBordersFor(table);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_2.setBounds(927, 78, 80, 27);
		
		Button button_3 = formToolkit.createButton(composite_10, "\u6279\u91CF\u5BFC\u5165\u5BA2\u6237", SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void widgetSelected(SelectionEvent e) {
				Insert_c_KHbyhand insert_c_KHbyhand=new Insert_c_KHbyhand();
				insert_c_KHbyhand.setCon(getDataBaseLink().getCon());
				FileDialog filedialog=new FileDialog(shell,SWT.OPEN);
				filedialog.setText("选择文件");
				filedialog.setFilterPath("SystemRoot");
				String Selectpath=filedialog.open();
				System.out.println("选择路径为："+Selectpath);
				/**
				 * 读取excel测试
				 * 载入第三方jar包后
				 * 一定要再lib设置中add才能引用
				 */
//				List<String[]> insertList=new ArrayList<String[]>();
				String [] insertString=new String[9];
				FileInputStream inputStream = null;
				HSSFWorkbook workbook = null;
				try {
					inputStream = new FileInputStream(Selectpath);
					workbook=new HSSFWorkbook(inputStream);
					//第一张工作表
					HSSFSheet sheet=workbook.getSheetAt(0);
					//
					
					HSSFRow row;//=sheet.getRow(0);
					HSSFCell cell = null;//=row.getCell(0);
//					System.out.println(cell.getStringCellValue());
					System.out.println("总行数："+sheet.getPhysicalNumberOfRows());
					for(int j=1;j<sheet.getPhysicalNumberOfRows();j++) {
						row=sheet.getRow(j);
						int x=0;
						System.out.println("第"+j+"行第一个各自长度"+row.getCell(0).getStringCellValue().length());
						if(row.getCell(0).getStringCellValue().length()>0)
						for(int k=0;k<row.getPhysicalNumberOfCells();k++) {
							cell=row.getCell(k);
//							System.out.print(cell.getStringCellValue()+"  ");
							
							switch(cell.getCellType()) {
								case HSSFCell.CELL_TYPE_NUMERIC: //数字
								
//								System.out.print(String.valueOf((long )cell.getNumericCellValue())+"  ");
								insertString[x]=String.valueOf((long )cell.getNumericCellValue());
								x++;
								break;
								case HSSFCell.CELL_TYPE_STRING: // 字符串    
//								System.out.print((cell.getStringCellValue())+"  ");
								
								insertString[x]=cell.getStringCellValue();
								x++;
	                            break;   
							}
//							
							
					
						}
//						System.out.println(insertString);
						for(String ite : insertString) {
							System.out.print(ite);
						}
						System.out.println();
						//调用插入方法
						
						try {
							insert_c_KHbyhand.insert(insertString);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("插入测试100号");
					}
					
					System.out.println("插入结束");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					workbook.close();
					inputStream.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				/**
				 * 测试结束
				 */
			}
		});
		button_3.setBounds(841, 78, 80, 27);
		
		Insert_c_type insert_c_type=new Insert_c_type();
		Insert_c_place insert_c_place=new Insert_c_place();
		
		
		CheckboxTableViewer checkboxTableViewer = CheckboxTableViewer.newCheckList(composite_10, SWT.BORDER | SWT.FULL_SELECTION);
		table = checkboxTableViewer.getTable();
		
		/**
		 * 增加一个变量 记录当前展示的表格是哪一个
		 */
		
		
		
		/**
		 * 添加table鼠标右键菜单
		 * 开始
		 */
		//创建菜单
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		
		menuItem.setText("\u4FEE\u6539");
		/**
		 * 结束
		 * 添加table鼠标右键菜单
		 */
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				//删除客户类型
				table.getSelection();
				System.out.println(table.getSelection()[0].getText());
				
				if(tableflag.equals("c_type"))
				try {
					insert_c_type.delete(Integer.valueOf(table.getSelection()[0].getText()));
					//刷新数据
					//清空表格
					for(TableColumn deleteColumn : table.getColumns()) {
						deleteColumn.dispose();
					}
					table.removeAll();
					//添加数据
					String[] tableHeader= {"类型编号","类型名称"};
					for(int i=0;i<tableHeader.length;i++) {
						TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
						//tableColumn.setWidth(200);
						tableColumn.setText(tableHeader[i]);
						tableColumn.setMoveable(true);
					}
					TableItem item; 
					
					try {
						for(String[] attribute : insert_c_type.check()) {
							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
						    table.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//删除地区
				if(tableflag.equals("c_place"))
				try {
					insert_c_place.delete(Integer.valueOf(table.getSelection()[0].getText()));
					//清空表格
					for(TableColumn deleteColumn : table.getColumns()) {
						deleteColumn.dispose();
					}
					table.removeAll();
					//添加数据
					String[] tableHeader= {"地区编号","地区名称"};
					for(int i=0;i<tableHeader.length;i++) {
						TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
						//tableColumn.setWidth(200);
						tableColumn.setText(tableHeader[i]);
						tableColumn.setMoveable(true);
					}
					TableItem item; 
					
					try {
						for(String[] attribute : insert_c_place.check()) {
							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
						    table.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (NumberFormatException | SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				//删除客户
				if(tableflag.equals("cccc")) {
					Statement statement;
					String sql="delete from C where No='"
							+ table.getSelection()[0].getText()+"';";
					try {
						statement = (Statement) getDataBaseLink().getCon().createStatement();
						statement.executeUpdate(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//返回客户列表
					sql="select No,name,type,place,co_name,y_name,tel,mail,"
							+ "in_date,al_date,addres,"
							+ "note from C_view";
//					Statement statement;
					try {
						statement = (Statement) getDataBaseLink().getCon().createStatement();
						ResultSet rs = statement.executeQuery(sql);
						List<String[]> list;
						list=new ArrayList<String[]>();
						while(rs.next()) {
							String [] cStrings=new String[12];
							cStrings[0]=rs.getString("No");
							cStrings[1]=rs.getString("name");
							cStrings[2]=rs.getString("type");
							cStrings[3]=rs.getString("place");
							cStrings[4]=rs.getString("co_name");
							cStrings[5]=rs.getString("y_name");
							cStrings[6]=rs.getString("tel");
							cStrings[7]=rs.getString("mail");
							cStrings[8]=rs.getString("in_date");
							cStrings[9]=rs.getString("al_date");
							cStrings[10]=rs.getString("addres");
							cStrings[11]=rs.getString("note");
							list.add(cStrings);
														}
							//清空表格
							for(TableColumn deleteColumn : table.getColumns()) {
								deleteColumn.dispose();
							}
							table.removeAll();
							//添加数据
							String[] tableHeader= {"客户编号","客户名称","客户类型","客户地区","联系人","业务员","联系电话","邮箱","录入日期","修改日期","地址","备注"};
							for(int i=0;i<tableHeader.length;i++) {
								TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
								//tableColumn.setWidth(200);
								tableColumn.setText(tableHeader[i]);
								tableColumn.setMoveable(true);
							}
							TableItem item; 
							
							
							for(String[] attribute : list) {
		//							System.out.println(attribute[0]+" "+attribute[1]);
								//添加入表格
								item = new TableItem(table, SWT.NONE);  
								item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10],attribute[11]});
							}
							// 重新布局表格  
							for (int i = 0; i < tableHeader.length; i++)  
							{  
							    table.getColumn(i).pack();  
							}
							formToolkit.paintBordersFor(table);
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
//					System.out.println("调用删除客户方法删除客户");
					//直接删
					
					
				
			}
		
			/**
			 * 添加右键菜单
			 */
			@Override
			public void mouseDown(MouseEvent e) {
				//如果时鼠标右键
				if(e.button==java.awt.event.MouseEvent.BUTTON3) {
					System.out.println("右键点击");
					menu.setVisible(true);
					
				}
			}
		});
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 134, 997, 465);
		
		//右键菜单事件
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				System.out.println("执行修改");
				
//				System.out.println(table.getSelectionIndex());
				UI_Alter_c ui_Alter_c=new UI_Alter_c(shell);
				
				//将所选行信息传进去
				String [] cStrings=new String[12];
//				table.getItem(table.getSelectionIndex()).getText(1))
//				System.out.println(table.getItem(table.getSelectionIndex()).getText(3));
				for(int i=0;i<12;i++) {
					cStrings[i]=table.getItem(table.getSelectionIndex()).getText(i);
//					System.out.println(table.getSelection()[i].getText());
				}
				for(String ss1 : cStrings) {
					System.out.println(ss1);
					//将数据传入ui
					}
				ui_Alter_c.setcStrings(cStrings);
				ui_Alter_c.setCon(getDataBaseLink().getCon());
				//修改完点击OK在传出
				ui_Alter_c.open();
				if(ui_Alter_c.isOK()) {
					cStrings=ui_Alter_c.getcStrings();
					//调用客户更新
					Alter_c alter_c=new Alter_c();
					alter_c.setCon(getDataBaseLink().getCon());
					alter_c.setcStrings(cStrings);
					try {
						alter_c.Update();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					//刷新数据
					System.out.println("开始刷新数据");
					//返回客户列表
					String sql="select No,name,type,place,co_name,y_name,tel,mail,"
							+ "in_date,al_date,addres,"
							+ "note from C_view";
//					Statement statement;
					try {
						Statement statement = (Statement) getDataBaseLink().getCon().createStatement();
						ResultSet rs = statement.executeQuery(sql);
						List<String[]> list;
						list=new ArrayList<String[]>();
						while(rs.next()) {
							String [] cStrings1=new String[12];
							cStrings1[0]=rs.getString("No");
							cStrings1[1]=rs.getString("name");
							cStrings1[2]=rs.getString("type");
							cStrings1[3]=rs.getString("place");
							cStrings1[4]=rs.getString("co_name");
							cStrings1[5]=rs.getString("y_name");
							cStrings1[6]=rs.getString("tel");
							cStrings1[7]=rs.getString("mail");
							cStrings1[8]=rs.getString("in_date");
							cStrings1[9]=rs.getString("al_date");
							cStrings1[10]=rs.getString("addres");
							cStrings1[11]=rs.getString("note");
							list.add(cStrings1);
														}
							//清空表格
							for(TableColumn deleteColumn : table.getColumns()) {
								deleteColumn.dispose();
							}
							table.removeAll();
							//添加数据
							String[] tableHeader= {"客户编号","客户名称","客户类型","客户地区","联系人","业务员","联系电话","邮箱","录入日期","修改日期","地址","备注"};
							for(int i=0;i<tableHeader.length;i++) {
								TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
								//tableColumn.setWidth(200);
								tableColumn.setText(tableHeader[i]);
								tableColumn.setMoveable(true);
							}
							TableItem item; 
							
							
							for(String[] attribute : list) {
		//							System.out.println(attribute[0]+" "+attribute[1]);
								//添加入表格
								item = new TableItem(table, SWT.NONE);  
								item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10],attribute[11]});
							}
							// 重新布局表格  
							for (int i = 0; i < tableHeader.length; i++)  
							{  
							    table.getColumn(i).pack();  
							}
							formToolkit.paintBordersFor(table);
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		formToolkit.paintBordersFor(table);
		
		
		
		Button button_5 = formToolkit.createButton(composite_10, "\u6DFB\u52A0\u7C7B\u578B", SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableflag="c_type";
//				menu.setVisible(false);
				insert_c_type.setCon(getDataBaseLink().getCon());
				
				//清空表格
				for(TableColumn deleteColumn : table.getColumns()) {
					deleteColumn.dispose();
				}
				table.removeAll();
				//添加数据
				String[] tableHeader= {"类型编号","类型名称"};
				for(int i=0;i<tableHeader.length;i++) {
					TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
					//tableColumn.setWidth(200);
					tableColumn.setText(tableHeader[i]);
					tableColumn.setMoveable(true);
				}
				TableItem item; 
				
				try {
					for(String[] attribute : insert_c_type.check()) {
						System.out.println(attribute[0]+" "+attribute[1]);
						//添加入表格
						item = new TableItem(table, SWT.NONE);  
						item.setText(new String[]{attribute[0],attribute[1]});
					}
					// 重新布局表格  
					for (int i = 0; i < tableHeader.length; i++)  
					{  
					    table.getColumn(i).pack();  
					}
					formToolkit.paintBordersFor(table);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				UI_Insert_c_type ui_Insert_c_type=new UI_Insert_c_type(shell);
				ui_Insert_c_type.open();
				System.out.println(ui_Insert_c_type.getMessage());
				//调用插入
				if(ui_Insert_c_type.isOK()) {
					try {
						insert_c_type.insert(ui_Insert_c_type.getMessage());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				//刷新
				//清空表格
				for(TableColumn deleteColumn : table.getColumns()) {
					deleteColumn.dispose();
				}
				table.removeAll();
				//添加数据
				String[] tableHeader1= {"类型编号","类型名称"};
				for(int i=0;i<tableHeader1.length;i++) {
					TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
					//tableColumn.setWidth(200);
					tableColumn.setText(tableHeader1[i]);
					tableColumn.setMoveable(true);
				}
				TableItem item1; 
				
				try {
					for(String[] attribute : insert_c_type.check()) {
						System.out.println(attribute[0]+" "+attribute[1]);
						//添加入表格
						item1 = new TableItem(table, SWT.NONE);  
						item1.setText(new String[]{attribute[0],attribute[1]});
					}
					// 重新布局表格  
					for (int i = 0; i < tableHeader1.length; i++)  
					{  
					    table.getColumn(i).pack();  
					}
					formToolkit.paintBordersFor(table);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		button_5.setBounds(755, 78, 80, 27);
		
		Button button_6 = formToolkit.createButton(composite_10, "\u6DFB\u52A0\u5730\u533A", SWT.NONE);
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableflag="c_place";
				//添加地区按钮
				insert_c_place.setCon(getDataBaseLink().getCon());
				
				//清空表格
				for(TableColumn deleteColumn : table.getColumns()) {
					deleteColumn.dispose();
				}
				table.removeAll();
				//添加数据
				String[] tableHeader= {"地区编号","地区名称"};
				for(int i=0;i<tableHeader.length;i++) {
					TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
					//tableColumn.setWidth(200);
					tableColumn.setText(tableHeader[i]);
					tableColumn.setMoveable(true);
				}
				TableItem item; 
				
				try {
					for(String[] attribute : insert_c_place.check()) {
						System.out.println(attribute[0]+" "+attribute[1]);
						//添加入表格
						item = new TableItem(table, SWT.NONE);  
						item.setText(new String[]{attribute[0],attribute[1]});
					}
					// 重新布局表格  
					for (int i = 0; i < tableHeader.length; i++)  
					{  
					    table.getColumn(i).pack();  
					}
					formToolkit.paintBordersFor(table);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				UI_Insert_c_place ui_Insert_c_place=new UI_Insert_c_place(shell);
				ui_Insert_c_place.open();
				System.out.println(ui_Insert_c_place.getMessage());
				//调用插入
				if(ui_Insert_c_place.isOK()) {
					try {
						insert_c_place.insert(ui_Insert_c_place.getMessage());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				//刷新
				//清空表格
				for(TableColumn deleteColumn : table.getColumns()) {
					deleteColumn.dispose();
				}
				table.removeAll();
				//添加数据
				String[] tableHeader1= {"地区编号","地区名称"};
				for(int i=0;i<tableHeader1.length;i++) {
					TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
					//tableColumn.setWidth(200);
					tableColumn.setText(tableHeader1[i]);
					tableColumn.setMoveable(true);
				}
				TableItem item1; 
				
				try {
					for(String[] attribute : insert_c_place.check()) {
						System.out.println(attribute[0]+" "+attribute[1]);
						//添加入表格
						item1 = new TableItem(table, SWT.NONE);  
						item1.setText(new String[]{attribute[0],attribute[1]});
					}
					// 重新布局表格  
					for (int i = 0; i < tableHeader1.length; i++)  
					{  
					    table.getColumn(i).pack();  
					}
					formToolkit.paintBordersFor(table);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_6.setBounds(669, 78, 80, 27);
		
		txtNewText = formToolkit.createText(composite_10, "New Text", SWT.NONE);
		txtNewText.setText("");
		txtNewText.setBounds(10, 82, 100, 23);
		
		txtNewText_1 = formToolkit.createText(composite_10, "New Text", SWT.NONE);
		txtNewText_1.setText("");
		txtNewText_1.setBounds(116, 82, 100, 23);
		
		Label label_7 = formToolkit.createLabel(composite_10, "\u6309\u5BA2\u6237\u540D\u67E5", SWT.NONE);
		label_7.setBounds(10, 59, 61, 17);
		
		Label label_8 = formToolkit.createLabel(composite_10, "\u6309\u5BA2\u6237\u7C7B\u578B\u67E5", SWT.NONE);
		label_8.setBounds(116, 59, 80, 17);
		
		txtNewText_2 = formToolkit.createText(composite_10, "New Text", SWT.NONE);
		txtNewText_2.setText("");
		txtNewText_2.setBounds(222, 82, 100, 23);
		
		Label label_9 = formToolkit.createLabel(composite_10, "\u6309\u5730\u533A\u67E5", SWT.NONE);
		label_9.setBounds(222, 59, 61, 17);
		
		Button button_7 = formToolkit.createButton(composite_10, "\u5F00\u59CB\u67E5\u8BE2", SWT.NONE);
		button_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * 当且仅当三个都不为空时进行查询
				 */
				String sql=null;
				if((!txtNewText.getText().equals(""))) {
					System.out.println("按客户名查");
					sql="select No,name,type,place,co_name,y_name,tel,mail,in_date,al_date,addres,note from C_view where name like '%"
							+ txtNewText.getText()+"%';";
				}
				else if(!txtNewText_1.getText().equals("")) {
					System.out.println("按客户类型查");
					sql="select No,name,type,place,co_name,y_name,tel,mail,in_date,al_date,addres,note from C_view where type like '%"
							+ txtNewText_1.getText()+"%';";
				}
				else if(!txtNewText_2.getText().equals("")) {
					System.out.println("按客户地区查");
					sql="select No,name,type,place,co_name,y_name,tel,mail,in_date,al_date,addres,note from C_view where place like '%"
							+ txtNewText_2.getText()+"%';";
				}
				
				if(!(txtNewText.getText().equals("")&&txtNewText_1.getText().equals("")&&txtNewText_2.getText().equals(""))) {
					System.out.println(sql);
					tableflag="cccc";
					Statement statement;
					try {
						statement = (Statement) getDataBaseLink().getCon().createStatement();
						ResultSet rs = statement.executeQuery(sql);
						List<String[]> list;
						list=new ArrayList<String[]>();
						while(rs.next()) {
							String [] cStrings=new String[12];
							cStrings[0]=rs.getString("No");
							cStrings[1]=rs.getString("name");
							cStrings[2]=rs.getString("type");
							cStrings[3]=rs.getString("place");
							cStrings[4]=rs.getString("co_name");
							cStrings[5]=rs.getString("y_name");
							cStrings[6]=rs.getString("tel");
							cStrings[7]=rs.getString("mail");
							cStrings[8]=rs.getString("in_date");
							cStrings[9]=rs.getString("al_date");
							cStrings[10]=rs.getString("addres");
							cStrings[11]=rs.getString("note");
							list.add(cStrings);
														}
							//清空表格
							for(TableColumn deleteColumn : table.getColumns()) {
								deleteColumn.dispose();
							}
							table.removeAll();
							//添加数据
							String[] tableHeader= {"客户编号","客户名称","客户类型","客户地区","联系人","业务员","联系电话","邮箱","录入日期","修改日期","地址","备注"};
							for(int i=0;i<tableHeader.length;i++) {
								TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
								//tableColumn.setWidth(200);
								tableColumn.setText(tableHeader[i]);
								tableColumn.setMoveable(true);
							}
							TableItem item; 
							
							
							for(String[] attribute : list) {
		//							System.out.println(attribute[0]+" "+attribute[1]);
								//添加入表格
								item = new TableItem(table, SWT.NONE);  
								item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10],attribute[11]});
							}
							// 重新布局表格  
							for (int i = 0; i < tableHeader.length; i++)  
							{  
							    table.getColumn(i).pack();  
							}
							formToolkit.paintBordersFor(table);
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		button_7.setBounds(328, 82, 61, 23);
		
		Button button_8 = formToolkit.createButton(composite_10, "\u6E05\u7A7A/\u5237\u65B0", SWT.NONE);
		button_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtNewText.setText("");
				txtNewText_1.setText("");
				txtNewText_2.setText("");
				tableflag="cccc";
				//返回客户列表
				String sql="select No,name,type,place,co_name,y_name,tel,mail,"
						+ "in_date,al_date,addres,"
						+ "note from C_view";
				Statement statement;
				try {
					statement = (Statement) getDataBaseLink().getCon().createStatement();
					ResultSet rs = statement.executeQuery(sql);
					List<String[]> list;
					list=new ArrayList<String[]>();
					while(rs.next()) {
						String [] cStrings=new String[12];
						cStrings[0]=rs.getString("No");
						cStrings[1]=rs.getString("name");
						cStrings[2]=rs.getString("type");
						cStrings[3]=rs.getString("place");
						cStrings[4]=rs.getString("co_name");
						cStrings[5]=rs.getString("y_name");
						cStrings[6]=rs.getString("tel");
						cStrings[7]=rs.getString("mail");
						cStrings[8]=rs.getString("in_date");
						cStrings[9]=rs.getString("al_date");
						cStrings[10]=rs.getString("addres");
						cStrings[11]=rs.getString("note");
						list.add(cStrings);
													}
						//清空表格
						for(TableColumn deleteColumn : table.getColumns()) {
							deleteColumn.dispose();
						}
						table.removeAll();
						//添加数据
						String[] tableHeader= {"客户编号","客户名称","客户类型","客户地区","联系人","业务员","联系电话","邮箱","录入日期","修改日期","地址","备注"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : list) {
	//							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10],attribute[11]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
						    table.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table);
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		button_8.setBounds(395, 82, 61, 23);
		
		Label label_2 = formToolkit.createLabel(composite_10, "\u53CC\u51FB\u5220\u9664", SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setBounds(755, 111, 61, 17);
		
		Label label_10 = formToolkit.createLabel(composite_10, "\u53F3\u952E\u4FEE\u6539", SWT.NONE);
		label_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_10.setBounds(840, 111, 61, 17);
		composite_1.setLayoutData(fd_composite_1);
		formToolkit.paintBordersFor(composite_1);
		
		StyledText styledText = new StyledText(composite_1, SWT.BORDER);
		styledText.setText("\u5C55\u793A\u5176\u4ED6\r\n\u4E1C\u897F \r\n\u56FE\u7247\u4E4B\u7C7B");
		styledText.setBounds(0, 0, 69, 118);
		formToolkit.adapt(styledText);
		formToolkit.paintBordersFor(styledText);
		
		Composite composite_3 = formToolkit.createComposite(shell, SWT.NONE);
		FormData fd_composite_3 = new FormData();
		fd_composite_3.bottom = new FormAttachment(0, 216);
		fd_composite_3.right = new FormAttachment(0, 1023);
		fd_composite_3.top = new FormAttachment(0, 69);
		fd_composite_3.left = new FormAttachment(0, 704);
		composite_3.setLayoutData(fd_composite_3);
		composite_3.setLayout(null);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		formToolkit.paintBordersFor(composite_3);
		
		
		//按钮菜单事件
		
		//客户
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				composite_10.setVisible(true);
				composite_5.setVisible(false);
				composite_6.setVisible(false);
				composite_7.setVisible(false);
				composite_8.setVisible(false);
			}
		});
		//统计分析
		btnNewButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				composite_10.setVisible(false);
				composite_5.setVisible(false);
				composite_6.setVisible(false);
				composite_7.setVisible(false);
				composite_8.setVisible(true);
				
				/**
				 * table_4客户订单报表
				 * table_5订单报表
				 */
				//客户订单报表
				String sql="select cname,order_sum,money_sum"
						
						+ " from c_statement order by money_sum DESC";
				Statement statement;
				try {
					statement = (Statement) getDataBaseLink().getCon().createStatement();
					ResultSet rs = statement.executeQuery(sql);
					List<String[]> list;
					list=new ArrayList<String[]>();
					while(rs.next()) {
						String [] cStrings=new String[3];
						cStrings[0]=rs.getString("cname");
						cStrings[1]=rs.getString("order_sum");
						cStrings[2]=rs.getString("money_sum");
						
						list.add(cStrings);
													}
						//清空表格
						for(TableColumn deleteColumn : table_4.getColumns()) {
							deleteColumn.dispose();
						}
						table_4.removeAll();
						//添加数据
						String[] tableHeader= {"客户名称","订单数量","订单金额"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table_4,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : list) {
	//							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table_4, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1],attribute[2]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
							table_4.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table_4);
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//订单数量报表
				sql="select o_sum,money_sum"
						
						+ " from order_statement order by money_sum DESC";
//				Statement statement;
				try {
					statement = (Statement) getDataBaseLink().getCon().createStatement();
					ResultSet rs = statement.executeQuery(sql);
					List<String[]> list;
					list=new ArrayList<String[]>();
					while(rs.next()) {
						String [] cStrings=new String[2];
						cStrings[0]=rs.getString("o_sum");
//						cStrings[1]=rs.getString("order_sum");
						cStrings[1]=rs.getString("money_sum");
						
						list.add(cStrings);
													}
						//清空表格
						for(TableColumn deleteColumn : table_5.getColumns()) {
							deleteColumn.dispose();
						}
						table_5.removeAll();
						//添加数据
						String[] tableHeader= {"订单总量","订单总额"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table_5,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : list) {
	//							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table_5, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
							table_5.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table_5);
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		//货物管理
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				composite_10.setVisible(false);
				composite_5.setVisible(false);
				composite_6.setVisible(false);
				composite_7.setVisible(true);
				composite_8.setVisible(false);
				
				/**
				 * 点击时进行货物信息查询
				 * ！稍后更改
				 */
				//返回列表
				String sql="select no,name,type,inprice,outprice,dno,unit,k,s,a,c,pname"
						
						+ " from p_view";
				Statement statement;
				try {
					statement = (Statement) getDataBaseLink().getCon().createStatement();
					ResultSet rs = statement.executeQuery(sql);
					List<String[]> list;
					list=new ArrayList<String[]>();
					while(rs.next()) {
						String [] cStrings=new String[12];
						cStrings[0]=rs.getString("no");
						cStrings[1]=rs.getString("name");
						cStrings[2]=rs.getString("type");
						cStrings[3]=rs.getString("inprice");
						cStrings[4]=rs.getString("outprice");
						cStrings[5]=rs.getString("dno");
						cStrings[6]=rs.getString("unit");
						cStrings[7]=rs.getString("k");
						cStrings[8]=rs.getString("s");
						cStrings[9]=rs.getString("a");
						cStrings[10]=rs.getString("c");
						cStrings[11]=rs.getString("pname");
						list.add(cStrings);
													}
						//清空表格
						for(TableColumn deleteColumn : table_1.getColumns()) {
							deleteColumn.dispose();
						}
						table_1.removeAll();
						//添加数据
						String[] tableHeader= {"货物编号","货物名称","货物类型","订购价","市场价","仓库编号","单位","可售库存","实际库存","安全库存","参考补货","供应商"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table_1,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : list) {
	//							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table_1, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10],attribute[11]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
							table_1.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table_1);
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		//发票
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				composite_10.setVisible(false);
				composite_5.setVisible(false);
				composite_6.setVisible(true);
				composite_7.setVisible(false);
				composite_8.setVisible(false);
				
				
				/**
				 * 刷新发票列表
				 */
				String sql="select no,ino,cno,cname,ono,name,money,tax,ptax,date,taxerNo"
						
						+ " from invoice_view";
				System.out.println(sql);
				Statement statement;
				try {
					statement = (Statement) getDataBaseLink().getCon().createStatement();
					ResultSet rs = statement.executeQuery(sql);
					List<String[]> list;
					list=new ArrayList<String[]>();
					while(rs.next()) {
						String [] cStrings=new String[11];
						cStrings[0]=rs.getString("no");//发票编号
						cStrings[1]=rs.getString("ino");//发票号码
						cStrings[2]=rs.getString("cno");//客户编号
						cStrings[3]=rs.getString("cname");//客户名称
						cStrings[4]=rs.getString("ono");//订单编号
						cStrings[5]=rs.getString("name");//开票方名称
						cStrings[6]=rs.getString("money");//发票金额
						cStrings[7]=rs.getString("tax");//发票税额
						cStrings[8]=rs.getString("ptax");//价税合计
						cStrings[9]=rs.getString("date");//开票日期
						cStrings[10]=rs.getString("taxerNo");//纳税人识别号
						
						list.add(cStrings);
													}
						//清空表格
						for(TableColumn deleteColumn : table_3.getColumns()) {
							deleteColumn.dispose();
						}
						table_3.removeAll();
						//添加数据
						String[] tableHeader= {"发票编号","发票号码","客户编号","客户名称","订单编号","开票方名称","发票金额","发票税额","价税合计","开票日期","纳税人识别号"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table_3,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : list) {
	//							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table_3, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
							table_3.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table_3);
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		//订单
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				composite_10.setVisible(false);
				composite_5.setVisible(true);
				composite_6.setVisible(false);
				composite_7.setVisible(false);
				composite_8.setVisible(false);
				
				
				/**
				 * 刷新订单列表
				 */
				String sql="select no,pno,cno,num,way,address,pname,price,cname,money"
						
						+ " from order_view";
				System.out.println(sql);
				Statement statement;
				try {
					statement = (Statement) getDataBaseLink().getCon().createStatement();
					ResultSet rs = statement.executeQuery(sql);
					List<String[]> list;
					list=new ArrayList<String[]>();
					while(rs.next()) {
						String [] cStrings=new String[8];
						cStrings[0]=rs.getString("no");//订单编号
						cStrings[1]=rs.getString("cname");//客户名
						cStrings[2]=rs.getString("pname");//货物名
						cStrings[3]=rs.getString("price");//价格
						cStrings[4]=rs.getString("num");//订购数量
						cStrings[5]=rs.getString("money");//总额
						cStrings[6]=rs.getString("way");//付款方式
						cStrings[7]=rs.getString("address");//发货地址
						
						list.add(cStrings);
													}
						//清空表格
						for(TableColumn deleteColumn : table_2.getColumns()) {
							deleteColumn.dispose();
						}
						table_2.removeAll();
						//添加数据
						String[] tableHeader= {"订单编号","客户名称","货物名称","价格","订购数量","总计","付款方式","发货地址"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table_2,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : list) {
	//							System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table_2, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
							table_2.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table_2);
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		//后台管理按钮
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UI_backManage ui_backManage=new UI_backManage(shell);
				//ui_backManage.setCon(dataBaseLink.getCon());
//				ui_backManage.setCon((Connection) getDataBaseLink().getCon());
				ui_backManage.setCon(getDataBaseLink().getCon());
				
				
				
				ui_backManage.setUser(user);
				ui_backManage.open();
				//关闭之前再将更新的值传出
				user=ui_backManage.getNewuser();
			}
		});
		
		
		
		
		
		Label label = new Label(composite_3, SWT.NONE);
		label.setBounds(6, 7, 80, 32);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
		label.setText("\u7528\u6237\u540D\uFF1A");
		
		text_1 = new Text(composite_3, SWT.BORDER);
		text_1.setBounds(107, 12, 190, 23);
		
		Label label_1 = new Label(composite_3, SWT.NONE);
		label_1.setBounds(8, 42, 80, 27);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
		label_1.setText("\u5BC6   \u7801\uFF1A");
		
		text = new Text(composite_3, SWT.BORDER | SWT.PASSWORD);
		text.setBounds(106, 46, 190, 23);
		
		Button button_1 = new Button(composite_3, SWT.NONE);
		button_1.setBounds(85, 96, 72, 27);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DatabaseSetting databaseSetting=new DatabaseSetting(shell);
				databaseSetting.setDataBaseLink(getDataBaseLink());
				databaseSetting.open();
				
			}
		});
		button_1.setText("\u6570\u636E\u5E93\u914D\u7F6E");
		
		Button button = new Button(composite_3, SWT.NONE);
		button.setBounds(223, 97, 74, 27);
		button.setText("\u767B\u5F55");
		
		Label lblsystem = formToolkit.createLabel(composite_3, "\u7BA1\u7406\u5458system \u5BC6\u7801666666  \u5176\u4ED6\u8D26\u53F7\u767B\u9646\u540E\u53F0\u67E5\u770B", SWT.NONE);
		lblsystem.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblsystem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblsystem.setLocation(10, 70);
		lblsystem.setSize(300, 17);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				/**
				 * 账号：system
				 * 密码：666666
				 */
				if(text_1.getText().equals("system")&&text.getText().equals("666666")) {
					String[] sysuser=new String[2];
					sysuser[0]="system";
					sysuser[1]="666666";
					user=sysuser;
					
					/**
					 * 调用连接数据库方法
					 */
					UI_waring ui_waring;
					try {
						getDataBaseLink().LinkDatabase();
						System.out.println("连接成功");
						linkOK=true;
						composite.setVisible(true);
						composite_3.dispose();
						btnNewButton_1.setVisible(true);
						
						
						
						
						//登录成功更新表
						/**
						 * 登录初始表格
						 */
						tableflag="cccc";
						String sql="select No,name,type,place,co_name,y_name,tel,mail,"
								+ "in_date,al_date,addres,"
								+ "note from C_view";
						Statement statement=(Statement) getDataBaseLink().getCon().createStatement();
						ResultSet rs=statement.executeQuery(sql);
						List<String[]> list;
						list=new ArrayList<String[]>();
						while(rs.next()) {
							String [] cStrings=new String[12];
							cStrings[0]=rs.getString("No");
							cStrings[1]=rs.getString("name");
							cStrings[2]=rs.getString("type");
							cStrings[3]=rs.getString("place");
							cStrings[4]=rs.getString("co_name");
							cStrings[5]=rs.getString("y_name");
							cStrings[6]=rs.getString("tel");
							cStrings[7]=rs.getString("mail");
							cStrings[8]=rs.getString("in_date");
							cStrings[9]=rs.getString("al_date");
							cStrings[10]=rs.getString("addres");
							cStrings[11]=rs.getString("note");
							list.add(cStrings);
						}
						//添加数据
						String[] tableHeader= {"客户编号","客户名称","客户类型","客户地区","联系人","业务员","联系电话","邮箱","录入日期","修改日期","地址","备注"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : list) {
//								System.out.println(attribute[0]+" "+attribute[1]);
							//添加入表格
							item = new TableItem(table, SWT.NONE);  
							item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10],attribute[11]});
						}
						// 重新布局表格  
						for (int i = 0; i < tableHeader.length; i++)  
						{  
						    table.getColumn(i).pack();  
						}
						formToolkit.paintBordersFor(table);
						
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						System.out.println("未安装数据库驱动");
						ui_waring=new UI_waring(shell,"未安装数据库驱动\n"
								+ "请安装数据库驱动");
						ui_waring.open();
						//e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println("连接数据库失败");
						ui_waring=new UI_waring(shell,"连接数据库失败\n"
								+ "请检查数据库配置或重新勾选默认");
						ui_waring.open();
						//e1.printStackTrace();
					}
				}else {
					System.out.println("不是管理员");
					UI_waring ui_waring;
					try {
						getDataBaseLink().LinkDatabase();
						linkOK=true;
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						System.out.println("未安装数据库驱动");
						ui_waring=new UI_waring(shell,"未安装数据库驱动\n"
								+ "请安装数据库驱动");
						ui_waring.open();
						e2.printStackTrace();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						System.out.println("连接数据库失败");
						ui_waring=new UI_waring(shell,"连接数据库失败\n"
								+ "请检查数据库配置或重新勾选默认");
						ui_waring.open();
						e2.printStackTrace();
					}
					/**
					 * 如果不是管理员
					 * 检查是否为普通用户
					 * text_1.getText().equals("system")&&text.getText().equals("666666")
					 * 应该先连接数据库
					 * 才能使用con查询语句
					 */
					String sql="select count(acount) from users "
							+ "where acount='"+text_1.getText()+"' and "
									+ "password='"+text.getText()+"';";
					System.out.println(sql);
//					Statement statement;
					try {
						Statement statement=(Statement)(getDataBaseLink().getCon()).createStatement();
						ResultSet rs=statement.executeQuery(sql);
						
						while(rs.next()) {
							if(Integer.valueOf(rs.getString("count(acount)"))==0) {
								/**
								 * 提示账号或密码错误
								 */
								ui_waring=new UI_waring(shell, "账号或密码错误");
								ui_waring.open();
								break;
							}
							else if(Integer.valueOf(rs.getString("count(acount)"))>0){
								
								user[0]=text_1.getText();
								user[1]=text.getText();
								
								
								/**
								 * 调用连接数据库方法
								 */
								
								try {
//									getDataBaseLink().LinkDatabase();
									System.out.println("连接成功");
									composite.setVisible(true);
									composite_3.dispose();
									btnNewButton_1.setVisible(true);
									
									
									
									
									//登录成功更新表
									/**
									 * 登录初始表格
									 */
									tableflag="cccc";
									String sql1="select No,name,type,place,co_name,y_name,tel,mail,"
											+ "in_date,al_date,addres,"
											+ "note from C_view";
									Statement statement1=(Statement) getDataBaseLink().getCon().createStatement();
									ResultSet rs1=statement1.executeQuery(sql1);
									List<String[]> list;
									list=new ArrayList<String[]>();
									while(rs1.next()) {
										String [] cStrings=new String[12];
										cStrings[0]=rs1.getString("No");
										cStrings[1]=rs1.getString("name");
										cStrings[2]=rs1.getString("type");
										cStrings[3]=rs1.getString("place");
										cStrings[4]=rs1.getString("co_name");
										cStrings[5]=rs1.getString("y_name");
										cStrings[6]=rs1.getString("tel");
										cStrings[7]=rs1.getString("mail");
										cStrings[8]=rs1.getString("in_date");
										cStrings[9]=rs1.getString("al_date");
										cStrings[10]=rs1.getString("addres");
										cStrings[11]=rs1.getString("note");
										list.add(cStrings);
									}
									//添加数据
									String[] tableHeader= {"客户编号","客户名称","客户类型","客户地区","联系人","业务员","联系电话","邮箱","录入日期","修改日期","地址","备注"};
									for(int i=0;i<tableHeader.length;i++) {
										TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
										//tableColumn.setWidth(200);
										tableColumn.setText(tableHeader[i]);
										tableColumn.setMoveable(true);
									}
									TableItem item; 
									
									
									for(String[] attribute : list) {
//											System.out.println(attribute[0]+" "+attribute[1]);
										//添加入表格
										item = new TableItem(table, SWT.NONE);  
										item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7],attribute[8],attribute[9],attribute[10],attribute[11]});
									}
									// 重新布局表格  
									for (int i = 0; i < tableHeader.length; i++)  
									{  
									    table.getColumn(i).pack();  
									}
									formToolkit.paintBordersFor(table);
									
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									System.out.println("连接数据库失败");
									ui_waring=new UI_waring(shell,"连接数据库失败\n"
											+ "请检查数据库配置或重新勾选默认");
									ui_waring.open();
									//e1.printStackTrace();
								}
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}

				
			}
		});
		//背景图片
		
		
		//end
		/**
		 * 登录按钮，登录后跳转页面，按钮与标签释放
		 */

	}
	
	
	/**
	 * Open the window.
	 * @throws SQLException 
	 */
	public void open() throws SQLException {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public DataBaseLink getDataBaseLink() {
		return dataBaseLink;
	}

	public void setDataBaseLink(DataBaseLink dataBaseLink) {
		this.dataBaseLink = dataBaseLink;
	}

	public String getInsertMessage() {
		return InsertMessage;
	}

	public void setInsertMessage(String insertMessage) {
		InsertMessage = insertMessage;
	}

	public String getTableflag() {
		return tableflag;
	}

	public void setTableflag(String tableflag) {
		this.tableflag = tableflag;
	}
}
