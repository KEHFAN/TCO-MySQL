package pri.fankehu.product_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import com.mysql.jdbc.Statement;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;

public class UI_cangku_button extends Dialog {

	private Connection con;//连接
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Table table;
	private Text txtNewText;
	private Text txtNewText_1;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public UI_cangku_button(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		
		Cangku_button cangku_button=new Cangku_button();
		cangku_button.setCon(getCon());
		
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FormLayout());
		
		Button button_3 = formToolkit.createButton(container, "\u4ED3\u5E93\u5217\u8868", SWT.NONE);
		
		FormData fd_button_3 = new FormData();
		fd_button_3.top = new FormAttachment(0, 25);
		fd_button_3.left = new FormAttachment(0, 11);
		button_3.setLayoutData(fd_button_3);
		
		Composite composite = formToolkit.createComposite(container, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 194);
		fd_composite.right = new FormAttachment(0, 424);
		fd_composite.top = new FormAttachment(0, 26);
		fd_composite.left = new FormAttachment(0, 90);
		composite.setLayoutData(fd_composite);
		formToolkit.paintBordersFor(composite);
		composite.setLayout(new StackLayout());
		
		Composite composite_1 = formToolkit.createComposite(composite, SWT.NONE);
		formToolkit.paintBordersFor(composite_1);
		
		table = formToolkit.createTable(composite_1, SWT.NONE);
		table.setBounds(0, 0, 334, 168);
		formToolkit.paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		Composite composite_2 = formToolkit.createComposite(composite, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		formToolkit.paintBordersFor(composite_2);
		composite_2.setLayout(new GridLayout(1, false));
		
		Label label = formToolkit.createLabel(composite_2, "\u4ED3\u5E93\u5927\u5C0F\uFF1A", SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		txtNewText = formToolkit.createText(composite_2, "New Text", SWT.NONE);
		txtNewText.setText("");
		txtNewText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_1 = formToolkit.createLabel(composite_2, "\u4ED3\u5E93\u5730\u5740\uFF1A", SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		txtNewText_1 = formToolkit.createText(composite_2, "New Text", SWT.NONE);
		txtNewText_1.setText("");
		txtNewText_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = formToolkit.createButton(composite_2, "\u786E\u5B9A", SWT.NONE);
		
		btnNewButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Composite composite_3 = formToolkit.createComposite(composite, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		formToolkit.paintBordersFor(composite_3);
		composite_3.setLayout(new GridLayout(2, false));
		
		Label label_2 = new Label(composite_3, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(label_2, true, true);
		label_2.setText("\u8D27\u7269\u540D\u79F0\uFF1A");
		
		Combo combo = new Combo(composite_3, SWT.READ_ONLY);
		
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(combo);
		formToolkit.paintBordersFor(combo);
		
		Label label_3 = new Label(composite_3, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(label_3, true, true);
		label_3.setText("\u53EF\u552E\u5E93\u5B58\uFF1A");
		
		text = formToolkit.createText(composite_3, "New Text", SWT.NONE);
		text.setText("");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_4 = new Label(composite_3, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(label_4, true, true);
		label_4.setText("\u5B9E\u9645\u5E93\u5B58\uFF1A");
		
		text_1 = formToolkit.createText(composite_3, "New Text", SWT.NONE);
		text_1.setText("");
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_5 = new Label(composite_3, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(label_5, true, true);
		label_5.setText("\u5B89\u5168\u5E93\u5B58\uFF1A");
		
		text_2 = formToolkit.createText(composite_3, "New Text", SWT.NONE);
		text_2.setText("");
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_6 = new Label(composite_3, SWT.NONE);
		label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(label_6, true, true);
		label_6.setText("\u53C2\u8003\u8865\u8D27\uFF1A");
		
		text_3 = formToolkit.createText(composite_3, "New Text", SWT.NONE);
		text_3.setText("");
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_3, SWT.NONE);
		
		Button button_4 = new Button(composite_3, SWT.NONE);
		
		button_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(button_4, true, true);
		button_4.setText("   \u786E  \u5B9A   ");
		
		Composite composite_4 = formToolkit.createComposite(composite, SWT.NONE);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		formToolkit.paintBordersFor(composite_4);
		composite_4.setLayout(new GridLayout(2, false));
		
		Label label_7 = formToolkit.createLabel(composite_4, "\u8D27\u7269\u540D\u79F0\uFF1A", SWT.NONE);
		label_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Combo combo_1 = new Combo(composite_4, SWT.READ_ONLY);
		
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(combo_1);
		formToolkit.paintBordersFor(combo_1);
		
		Label label_8 = formToolkit.createLabel(composite_4, "\u53EF\u552E\u5E93\u5B58\uFF1A", SWT.NONE);
		label_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_4 = formToolkit.createText(composite_4, "New Text", SWT.NONE);
		text_4.setText("");
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_9 = formToolkit.createLabel(composite_4, "\u5B9E\u9645\u5E93\u5B58\uFF1A", SWT.NONE);
		label_9.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_5 = formToolkit.createText(composite_4, "New Text", SWT.NONE);
		text_5.setText("");
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_10 = formToolkit.createLabel(composite_4, "\u5B89\u5168\u5E93\u5B58\uFF1A", SWT.NONE);
		label_10.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_6 = formToolkit.createText(composite_4, "New Text", SWT.NONE);
		text_6.setText("");
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_11 = formToolkit.createLabel(composite_4, "\u51FA\u5E93\u6570\u91CF\uFF1A", SWT.NONE);
		label_11.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_7 = formToolkit.createText(composite_4, "New Text", SWT.NONE);
		text_7.setText("");
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_4, SWT.NONE);
		
		Button button_5 = formToolkit.createButton(composite_4, "   \u786E  \u5B9A   ", SWT.NONE);
		
		button_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Button button = formToolkit.createButton(container, "\u6DFB\u52A0\u4ED3\u5E93", SWT.NONE);
		
		FormData fd_button = new FormData();
		fd_button.top = new FormAttachment(0, 70);
		fd_button.left = new FormAttachment(0, 11);
		button.setLayoutData(fd_button);
		
		Button button_1 = formToolkit.createButton(container, "\u65B0\u589E\u5165\u5E93", SWT.NONE);
		
		FormData fd_button_1 = new FormData();
		fd_button_1.top = new FormAttachment(0, 118);
		fd_button_1.left = new FormAttachment(0, 11);
		button_1.setLayoutData(fd_button_1);
		
		Button button_2 = formToolkit.createButton(container, "\u65B0\u589E\u51FA\u5E93", SWT.NONE);
		
		FormData fd_button_2 = new FormData();
		fd_button_2.top = new FormAttachment(0, 167);
		fd_button_2.left = new FormAttachment(0, 11);
		button_2.setLayoutData(fd_button_2);
		
		
		/**
		 * 按钮事件
		 */
		//仓库列表按钮
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				composite_1.setVisible(true);
				composite_2.setVisible(false);
				composite_3.setVisible(false);
				composite_4.setVisible(false);
				
			
			
				/**
				 * 展示仓库列表
				 */
				//刷新表格
				//清空表格
				for(TableColumn deleteColumn : table.getColumns()) {
					deleteColumn.dispose();
				}
				table.removeAll();
				//添加数据
				String[] tableHeader= {"仓库编号","仓库地址","仓库大小"};
				for(int i=0;i<tableHeader.length;i++) {
					TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
					//tableColumn.setWidth(200);
					tableColumn.setText(tableHeader[i]);
					tableColumn.setMoveable(true);
				}
				TableItem item; 
				
				
				try {
					for(String[] attribute : cangku_button.check_cangku()) {
//							System.out.println(attribute[0]+" "+attribute[1]);
						//添加入表格
						item = new TableItem(table, SWT.NONE);  
						item.setText(new String[]{attribute[0],attribute[1],attribute[2]});
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// 重新布局表格  
				for (int i = 0; i < tableHeader.length; i++)  
				{  
				    table.getColumn(i).pack();  
				}
				formToolkit.paintBordersFor(table);
			}
		});
		//添加仓库按钮
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				composite_1.setVisible(false);
				composite_2.setVisible(true);
				composite_3.setVisible(false);
				composite_4.setVisible(false);
				
				
			}
		});
		//新增入库
		List<String[]> listiadd=new ArrayList<String[]>(); 
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				composite_1.setVisible(false);
				composite_2.setVisible(false);
				composite_3.setVisible(true);
				composite_4.setVisible(false);
				
				//清空list
				listiadd.clear();
				combo.setText("");
				text.setText("");
				text_1.setText("");
				text_2.setText("");
				text_3.setText("");
				
				
				//将商品名称导入combo
				//添加商品下拉
				//查询仓库begin
				String sql="select count(no) from P_info";
				Statement statement;
				int ii=0;
//				List<String[]> listi=new ArrayList<String[]>(); 
				try {
					statement = (Statement) con.createStatement();
					ResultSet rs=statement.executeQuery(sql);
					
					while(rs.next()) {
						ii=rs.getInt("count(no)");
					}
					
					
					
					sql="select no,name from P_info order by no";
					statement=(Statement) con.createStatement();
					rs=statement.executeQuery(sql);
					
					
					while(rs.next()) {
						String ty[]=new String[2];
						ty[0]=rs.getString("no");
						ty[1]=rs.getString("name");
										//double类型
//						System.out.println(ty[0]+" "+ty[1]);
						listiadd.add(ty);
						
					}
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
				
				//查询仓库end
				int i=0;
				String [] items = null;
				items=new String[ii];
				for(String[] attribute : listiadd) {
					System.out.println(attribute);
					items[i]=attribute[1];
					i++;
				}
				combo.setItems(items);
				
				
				
				
			}
		});
		//新增出库
		List<String[]> listiout=new ArrayList<String[]>(); 
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				composite_1.setVisible(false);
				composite_2.setVisible(false);
				composite_3.setVisible(false);
				composite_4.setVisible(true);
				
				//清空list
				listiout.clear();
				combo_1.setText("");
				text_4.setText("");
				text_5.setText("");
				text_6.setText("");
				text_7.setText("");
				
				
				//将商品名称导入combo
				//添加商品下拉
				//查询仓库begin
				String sql="select count(no) from P_info";
				Statement statement;
				int ii=0;
//				List<String[]> listi=new ArrayList<String[]>(); 
				try {
					statement = (Statement) con.createStatement();
					ResultSet rs=statement.executeQuery(sql);
					
					while(rs.next()) {
						ii=rs.getInt("count(no)");
					}
					
					
					
					sql="select no,name from P_info order by no";
					statement=(Statement) con.createStatement();
					rs=statement.executeQuery(sql);
					
					
					while(rs.next()) {
						String ty[]=new String[2];
						ty[0]=rs.getString("no");
						ty[1]=rs.getString("name");
										//double类型
//						System.out.println(ty[0]+" "+ty[1]);
						listiout.add(ty);
						
					}
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
				
				//查询仓库end
				int i=0;
				String [] items = null;
				items=new String[ii];
				for(String[] attribute : listiout) {
					System.out.println(attribute);
					items[i]=attribute[1];
					i++;
				}
				combo_1.setItems(items);
			}
		});
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				/**
				 * 调用添加事件
				 * 点击确定添加按钮后 跳转到仓库列表
				 */
				String [] cStrings=new String[2];
				cStrings[0]=txtNewText_1.getText();
				cStrings[1]=txtNewText.getText();
				try {
					cangku_button.insert_cangku(cStrings);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
				
				
				/**
				 * 展示仓库列表
				 */
				//刷新表格
				//清空表格
				for(TableColumn deleteColumn : table.getColumns()) {
					deleteColumn.dispose();
				}
				table.removeAll();
				//添加数据
				String[] tableHeader= {"仓库编号","仓库地址","仓库大小"};
				for(int i=0;i<tableHeader.length;i++) {
					TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
					//tableColumn.setWidth(200);
					tableColumn.setText(tableHeader[i]);
					tableColumn.setMoveable(true);
				}
				TableItem item; 
				
				
				try {
					for(String[] attribute : cangku_button.check_cangku()) {
//							System.out.println(attribute[0]+" "+attribute[1]);
						//添加入表格
						item = new TableItem(table, SWT.NONE);  
						item.setText(new String[]{attribute[0],attribute[1],attribute[2]});
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// 重新布局表格  
				for (int i = 0; i < tableHeader.length; i++)  
				{  
				    table.getColumn(i).pack();  
				}
				formToolkit.paintBordersFor(table);
				
				composite_1.setVisible(true);
				composite_2.setVisible(false);
				composite_3.setVisible(false);
				composite_4.setVisible(false);
			}
		});
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("选择的商品为："+combo.getText());
				//查询 将结果展示在
				String[] cc=new String[5];
				String id=null;
				for(String[] attribute : listiadd) {
					if(attribute[1].equals(combo.getText())) {
						id=attribute[0];
						break;
					}
				}
				try {
					cc=cangku_button.check_add(id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				text.setText(cc[1]);
				text_1.setText(cc[2]);
				text_2.setText(cc[3]);
				text_3.setText(cc[4]);
			}
		});
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * 将UI数据传入
				 * 确认按钮时
				 */
				String [] cStrings=new String[5];
				for(String[] attribute : listiadd) {
					if(attribute[1].equals(combo.getText())) {
						cStrings[0]=attribute[0];
						break;
					}
				}
				cStrings[1]=text.getText();
				cStrings[2]=text_1.getText();
				cStrings[3]=text_2.getText();
				cStrings[4]=text_3.getText();
				try {
					cangku_button.add(cStrings);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//查询 将结果展示在
				String[] cc=new String[5];
				String id=null;
				for(String[] attribute : listiout) {
					if(attribute[1].equals(combo_1.getText())) {
						id=attribute[0];
						break;
					}
				}
				try {
					cc=cangku_button.check_out(id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				text_4.setText(cc[1]);
				text_5.setText(cc[2]);
				text_6.setText(cc[3]);
//				text_3.setText(cc[4]);
				//以上都要改
			}
		});
		//出库确认按钮
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * 将UI数据传入
				 * 确认按钮时
				 */
				String [] cStrings=new String[5];
				for(String[] attribute : listiout) {
					if(attribute[1].equals(combo_1.getText())) {
						cStrings[0]=attribute[0];
						break;
					}
				}
				cStrings[1]=text_4.getText();
				cStrings[2]=text_5.getText();
				cStrings[3]=text_6.getText();
				cStrings[4]=text_7.getText();
				try {
					cangku_button.out(cStrings);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
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
		return new Point(450, 300);
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
}
