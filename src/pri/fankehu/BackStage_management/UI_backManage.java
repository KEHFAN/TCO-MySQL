package pri.fankehu.BackStage_management;

import java.sql.SQLException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.ui.forms.widgets.FormToolkit;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class UI_backManage extends Dialog {
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private java.sql.Connection con;//连接
	private Table table;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private String[] user=new String[2];
	private Users users=new Users();
	private  String[] newuser=new String[2];
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public UI_backManage(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(null);
		
		users.setCon(getCon());
		setNewuser(user);
		
		table = formToolkit.createTable(container, SWT.FULL_SELECTION);
		
		table.setBounds(10, 10, 150, 204);
		formToolkit.paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		Label label = formToolkit.createLabel(container, "\u589E\u52A0\u7528\u6237\uFF1A", SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label.setBounds(166, 10, 61, 17);
		
		Label label_1 = formToolkit.createLabel(container, "\u767B\u9646\u8D26\u53F7\uFF1A", SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_1.setBounds(166, 33, 61, 17);
		
		Label label_2 = formToolkit.createLabel(container, "\u767B\u5F55\u5BC6\u7801\uFF1A", SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_2.setBounds(166, 59, 61, 17);
		
		text = formToolkit.createText(container, "New Text", SWT.NONE);
		text.setText("");
		text.setBounds(233, 27, 117, 23);
		
		text_1 = formToolkit.createText(container, "New Text", SWT.NONE);
		text_1.setText("");
		text_1.setBounds(233, 56, 117, 23);
		
		Button button = new Button(container, SWT.NONE);
		button.setEnabled(false);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!(text.getText().equals("")||text_1.getText().equals(""))){
					String[] adduser=new String[2];
					adduser[0]=text.getText();
					adduser[1]=text_1.getText();
					try {
						users.insert(adduser);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						//清空表格
						for(TableColumn deleteColumn : table.getColumns()) {
							deleteColumn.dispose();
						}
						table.removeAll();
						//添加数据
						String[] tableHeader= {"账号","密码"};
						for(int i=0;i<tableHeader.length;i++) {
							TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
							//tableColumn.setWidth(200);
							tableColumn.setText(tableHeader[i]);
							tableColumn.setMoveable(true);
						}
						TableItem item; 
						
						
						for(String[] attribute : users.check_users()) {
//								System.out.println(attribute[0]+" "+attribute[1]);
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
					text.setText("");
					text_1.setText("");
				}
			}
		});
		button.setBounds(270, 85, 80, 27);
		formToolkit.adapt(button, true, true);
		button.setText("\u65B0\u589E");
		
		Label label_3 = formToolkit.createLabel(container, "\u4FEE\u6539\u4FE1\u606F\uFF1A", SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_3.setBounds(166, 112, 61, 17);
		
		Label label_4 = formToolkit.createLabel(container, "\u4FEE\u6539\u8D26\u53F7\uFF1A", SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_4.setBounds(166, 135, 61, 17);
		
		Label label_5 = formToolkit.createLabel(container, "\u4FEE\u6539\u5BC6\u7801\uFF1A", SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_5.setBounds(166, 161, 61, 17);
		
		text_2 = formToolkit.createText(container, "New Text", SWT.NONE);
		text_2.setText("");
		text_2.setBounds(233, 129, 117, 23);
		
		text_3 = formToolkit.createText(container, "New Text", SWT.NONE);
		text_3.setText("");
		text_3.setBounds(233, 158, 117, 23);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//将所选行信息传进去
				text_2.setText(table.getItem(table.getSelectionIndex()).getText(0));
				text_3.setText(table.getItem(table.getSelectionIndex()).getText(1));
				
				
			}
		});
		
		Button button_1 = formToolkit.createButton(container, "\u4FEE\u6539", SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!(text_2.getText().equals("")||text_3.getText().equals(""))){
					String[] upuser=new String[2];
					upuser[0]=text_2.getText();
					upuser[1]=text_3.getText();
					
					
					if(user[0].equals("system")&&user[1].equals("666666")) {
						//如果是system账户
						
						/**
						 * 刷新users列表
						 */
						try {
							String[] nowuser=new String[2];
							//将所选行信息传进去
							nowuser[0]=table.getItem(table.getSelectionIndex()).getText(0);
							nowuser[1]=table.getItem(table.getSelectionIndex()).getText(1);
							

							users.update(nowuser,upuser);
								//清空表格
								for(TableColumn deleteColumn : table.getColumns()) {
									deleteColumn.dispose();
								}
								table.removeAll();
								//添加数据
								String[] tableHeader= {"账号","密码"};
								for(int i=0;i<tableHeader.length;i++) {
									TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
									//tableColumn.setWidth(200);
									tableColumn.setText(tableHeader[i]);
									tableColumn.setMoveable(true);
								}
								TableItem item; 
								
								
								for(String[] attribute : users.check_users()) {
//										System.out.println(attribute[0]+" "+attribute[1]);
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
					}else {
						//如果不是，判断是否为普通账户
						
						try {
							users.update(user, upuser);
							setUser(upuser);
							setNewuser(upuser);
							//清空表格
							for(TableColumn deleteColumn : table.getColumns()) {
								deleteColumn.dispose();
							}
							table.removeAll();
							//添加数据
							String[] tableHeader= {"账号","密码"};
							for(int i=0;i<tableHeader.length;i++) {
								TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
								//tableColumn.setWidth(200);
								tableColumn.setText(tableHeader[i]);
								tableColumn.setMoveable(true);
							}
							String[] checkuser=new String[2];
							checkuser=users.check_user(user);
							TableItem item; 
							//添加入表格
							item = new TableItem(table, SWT.NONE);  
							item.setText(new String[]{checkuser[0],checkuser[1]});
							
						
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
					text_2.setText("");
					text_3.setText("");
				}
			}
		});
		button_1.setBounds(270, 187, 80, 27);
		
		Button button_2 = formToolkit.createButton(container, "\u5220\u9664\u9009\u4E2D", SWT.NONE);
		button_2.setEnabled(false);
		if(user[0].equals("system")&&user[1].equals("666666")) {
			button.setEnabled(true);
			button_2.setEnabled(true);
		}
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//将所选行信息传进去
				String [] cStrings=new String[2];
				for(int i=0;i<2;i++) {
					cStrings[i]=table.getItem(table.getSelectionIndex()).getText(i);
				}
				try {
					users.delete(cStrings);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					//清空表格
					for(TableColumn deleteColumn : table.getColumns()) {
						deleteColumn.dispose();
					}
					table.removeAll();
					//添加数据
					String[] tableHeader= {"账号","密码"};
					for(int i=0;i<tableHeader.length;i++) {
						TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
						//tableColumn.setWidth(200);
						tableColumn.setText(tableHeader[i]);
						tableColumn.setMoveable(true);
					}
					TableItem item; 
					
					
					for(String[] attribute : users.check_users()) {
//							System.out.println(attribute[0]+" "+attribute[1]);
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
			}
		});
		button_2.setBounds(166, 85, 80, 27);
		
		Label label_6 = formToolkit.createLabel(container, "\u7BA1\u7406\u5458\u6743\u9650", SWT.NONE);
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_6.setBounds(233, 10, 61, 17);

		
		/**
		 * 初始化表格
		 * 区分普通用户
		 * 与system
		 */
		if(user[0].equals("system")&&user[1].equals("666666")) {
			//如果是system账户
			/**
			 * 刷新users列表
			 */
			try {
					//清空表格
					for(TableColumn deleteColumn : table.getColumns()) {
						deleteColumn.dispose();
					}
					table.removeAll();
					//添加数据
					String[] tableHeader= {"账号","密码"};
					for(int i=0;i<tableHeader.length;i++) {
						TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
						//tableColumn.setWidth(200);
						tableColumn.setText(tableHeader[i]);
						tableColumn.setMoveable(true);
					}
					TableItem item; 
					
					
					for(String[] attribute : users.check_users()) {
//							System.out.println(attribute[0]+" "+attribute[1]);
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
		}else {
			//如果不是，判断是否为普通账户
			try {
				//清空表格
				for(TableColumn deleteColumn : table.getColumns()) {
					deleteColumn.dispose();
				}
				table.removeAll();
				//添加数据
				String[] tableHeader= {"账号","密码"};
				for(int i=0;i<tableHeader.length;i++) {
					TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
					//tableColumn.setWidth(200);
					tableColumn.setText(tableHeader[i]);
					tableColumn.setMoveable(true);
				}
				String[] checkuser=new String[2];
				checkuser=users.check_user(user);
				TableItem item; 
				//添加入表格
				item = new TableItem(table, SWT.NONE);  
				item.setText(new String[]{checkuser[0],checkuser[1]});
				
			
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
		
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(376, 326);
	}

	

	public String[] getUser() {
		return user;
	}

	public void setUser(String[] user) {
		this.user = user;
	}

	public java.sql.Connection getCon() {
		return con;
	}

	public void setCon(java.sql.Connection connection) {
		this.con = connection;
	}

	public String[] getNewuser() {
		return newuser;
	}

	public void setNewuser(String[] newuser) {
		this.newuser = newuser;
	}

	
}
