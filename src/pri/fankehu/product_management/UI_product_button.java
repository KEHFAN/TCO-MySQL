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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;

import com.mysql.jdbc.Statement;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class UI_product_button extends Dialog {

	private Connection con;//连接
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public UI_product_button(Shell parentShell) {
		super(parentShell);
	}

	//创建对象
	Product_button product_button=new Product_button();
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Table table;
	private Text txtNewText;
	private Text txtNewText_1;
	private Text txtNewText_2;
	private Text txtNewText_3;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private String flattable=null;
	Menu menu;
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		product_button.setCon(getCon());
		
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FormLayout());
		
		table = formToolkit.createTable(container, SWT.NONE);
		
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100, -13);
		fd_table.right = new FormAttachment(100);
		fd_table.top = new FormAttachment(0, 10);
		table.setLayoutData(fd_table);
		formToolkit.paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		Button button = formToolkit.createButton(container, "\u6DFB\u52A0\u8D27\u7269\u5206\u7C7B", SWT.NONE);
		
		FormData fd_button = new FormData();
		fd_button.right = new FormAttachment(0, 90);
		fd_button.top = new FormAttachment(0, 10);
		fd_button.left = new FormAttachment(0, 10);
		button.setLayoutData(fd_button);
		
		Button button_1 = formToolkit.createButton(container, "\u6DFB\u52A0\u4F9B\u5E94\u5546", SWT.NONE);
		
		FormData fd_button_1 = new FormData();
		fd_button_1.top = new FormAttachment(button, 3);
		fd_button_1.left = new FormAttachment(button, 0, SWT.LEFT);
		fd_button_1.right = new FormAttachment(0, 90);
		button_1.setLayoutData(fd_button_1);
		
		Button button_2 = formToolkit.createButton(container, "\u6DFB\u52A0\u5355\u4F4D", SWT.NONE);
		
		fd_table.left = new FormAttachment(button_2, 16);
		FormData fd_button_2 = new FormData();
		fd_button_2.top = new FormAttachment(button, 0, SWT.TOP);
		fd_button_2.left = new FormAttachment(button, 6);
		fd_button_2.right = new FormAttachment(0, 176);
		button_2.setLayoutData(fd_button_2);
		
		Button button_3 = formToolkit.createButton(container, "\u6DFB\u52A0\u8D27\u7269\u4FE1\u606F", SWT.NONE);
		
		FormData fd_button_3 = new FormData();
		fd_button_3.top = new FormAttachment(button_1, 0, SWT.TOP);
		fd_button_3.left = new FormAttachment(button_1, 6);
		fd_button_3.right = new FormAttachment(0, 176);
		button_3.setLayoutData(fd_button_3);
		
		Composite composite = formToolkit.createComposite(container, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		composite.setLayout(new StackLayout());
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(button_1, 6);
		fd_composite.right = new FormAttachment(button_2, 0, SWT.RIGHT);
		fd_composite.left = new FormAttachment(0, 10);
		fd_composite.bottom = new FormAttachment(100, -6);
		composite.setLayoutData(fd_composite);
		formToolkit.paintBordersFor(composite);
		
		Composite composite_1 = formToolkit.createComposite(composite, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		formToolkit.paintBordersFor(composite_1);
		composite_1.setLayout(new GridLayout(1, false));
		new Label(composite_1, SWT.NONE);
		
		Label label_12 = formToolkit.createLabel(composite_1, "\u8F93\u5165\u65B0\u5206\u7C7B\uFF1A", SWT.NONE);
		label_12.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		text_4 = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		text_4.setText("");
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Button btnNewButton_2 = formToolkit.createButton(composite_1, "\u786E\u5B9A", SWT.NONE);
		
		btnNewButton_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Composite composite_2 = formToolkit.createComposite(composite, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		formToolkit.paintBordersFor(composite_2);
		composite_2.setLayout(new GridLayout(1, false));
		
		Label label_8 = formToolkit.createLabel(composite_2, "\u8F93\u5165\u4F9B\u5E94\u5546\u540D\u79F0\uFF1A", SWT.NONE);
		label_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		text = formToolkit.createText(composite_2, "New Text", SWT.NONE);
		text.setText("");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_9 = formToolkit.createLabel(composite_2, "\u8054\u7CFB\u4EBA\uFF1A", SWT.NONE);
		label_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		text_1 = formToolkit.createText(composite_2, "New Text", SWT.NONE);
		text_1.setText("");
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_10 = formToolkit.createLabel(composite_2, "\u624B\u673A\u7535\u8BDD\uFF1A", SWT.NONE);
		label_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		text_2 = formToolkit.createText(composite_2, "New Text", SWT.NONE);
		text_2.setText("");
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_11 = formToolkit.createLabel(composite_2, "\u8054\u7CFB\u5730\u5740\uFF1A", SWT.NONE);
		label_11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		text_3 = formToolkit.createText(composite_2, "New Text", SWT.NONE);
		text_3.setText("");
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton_1 = formToolkit.createButton(composite_2, "\u786E\u5B9A", SWT.NONE);
		
		btnNewButton_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Composite composite_3 = formToolkit.createComposite(composite, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		formToolkit.paintBordersFor(composite_3);
		composite_3.setLayout(new GridLayout(1, false));
		new Label(composite_3, SWT.NONE);
		
		Label label_7 = formToolkit.createLabel(composite_3, "\u8F93\u5165\u5355\u4F4D\uFF1A", SWT.NONE);
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		txtNewText_3 = formToolkit.createText(composite_3, "New Text", SWT.NONE);
		txtNewText_3.setText("");
		txtNewText_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_3, SWT.NONE);
		new Label(composite_3, SWT.NONE);
		
		Button btnNewButton = formToolkit.createButton(composite_3, "\u786E\u5B9A", SWT.NONE);
		
		btnNewButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Composite composite_4 = formToolkit.createComposite(composite, SWT.NONE);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		formToolkit.paintBordersFor(composite_4);
		composite_4.setLayout(new GridLayout(2, false));
		
		Label label = formToolkit.createLabel(composite_4, "\u8D27\u7269\u540D\u79F0\uFF1A", SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText = formToolkit.createText(composite_4, "New Text", SWT.NONE);
		txtNewText.setText("");
		txtNewText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_1 = formToolkit.createLabel(composite_4, "\u5206\u7C7B\uFF1A", SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Combo combo = new Combo(composite_4, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(combo);
		formToolkit.paintBordersFor(combo);
		
		Label label_2 = formToolkit.createLabel(composite_4, "\u5355\u4F4D\uFF1A", SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Combo combo_1 = new Combo(composite_4, SWT.READ_ONLY);
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(combo_1);
		formToolkit.paintBordersFor(combo_1);
		
		Label label_3 = formToolkit.createLabel(composite_4, "\u4ED3\u5E93\uFF1A", SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Combo combo_2 = new Combo(composite_4, SWT.READ_ONLY);
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(combo_2);
		formToolkit.paintBordersFor(combo_2);
		
		Label label_4 = formToolkit.createLabel(composite_4, "\u4F9B\u5E94\u5546\uFF1A", SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Combo combo_3 = new Combo(composite_4, SWT.READ_ONLY);
		combo_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(combo_3);
		formToolkit.paintBordersFor(combo_3);
		
		Label label_5 = formToolkit.createLabel(composite_4, "\u8BA2\u8D27\u4EF7\uFF1A", SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_1 = formToolkit.createText(composite_4, "New Text", SWT.NONE);
		txtNewText_1.setText("");
		txtNewText_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_6 = formToolkit.createLabel(composite_4, "\u5E02\u573A\u4EF7\uFF1A", SWT.NONE);
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_2 = formToolkit.createText(composite_4, "New Text", SWT.NONE);
		txtNewText_2.setText("");
		txtNewText_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_13 = formToolkit.createLabel(composite_4, "\u53F3\u952E\u83DC\u5355", SWT.NONE);
		label_13.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_13.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		Button button_4 = formToolkit.createButton(composite_4, "\u786E\u5B9A", SWT.NONE);
		
		button_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

		/**
		 * 刷新货物
		 */
		//刷新表格
		//清空表格
		for(TableColumn deleteColumn : table.getColumns()) {
			deleteColumn.dispose();
		}
		table.removeAll();
		//添加数据
		String[] tableHeader= {"货物编号","货物名称","货物类型","单位","仓库","供应商","进价","市场价"};
		for(int i=0;i<tableHeader.length;i++) {
			TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
			//tableColumn.setWidth(200);
			tableColumn.setText(tableHeader[i]);
			tableColumn.setMoveable(true);
		}
		TableItem item; 
		
		
		try {
			for(String[] attribute : product_button.check_product()) {
//					System.out.println(attribute[0]+" "+attribute[1]);
				//添加入表格
				item = new TableItem(table, SWT.NONE);  
				item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7]});
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
		menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("\u4FEE\u6539(\u5DF2\u7981\u7528)");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * 调用删除
				 */
				System.out.println(flattable);
				if(flattable.equals("rightmenu")) {
					//执行删除操作
				}
			}
		});
		menuItem_1.setText("\u5220\u9664(\u5DF2\u7981\u7528)");
		/**
		 * button
		 */
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				flattable="";
				menu.setVisible(false);
				composite_1.setVisible(true);
				composite_2.setVisible(false);
				composite_3.setVisible(false);
				composite_4.setVisible(false);
				/**
				 * 添加分类
				 * 调用方法
				 */
				//刷新表格
				//清空表格
				for(TableColumn deleteColumn : table.getColumns()) {
					deleteColumn.dispose();
				}
				table.removeAll();
				//添加数据
				String[] tableHeader= {"类别编号","类别名称"};
				for(int i=0;i<tableHeader.length;i++) {
					TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
					//tableColumn.setWidth(200);
					tableColumn.setText(tableHeader[i]);
					tableColumn.setMoveable(true);
				}
				TableItem item; 
				
				
				try {
					for(String[] attribute : product_button.check_type()) {
//							System.out.println(attribute[0]+" "+attribute[1]);
						//添加入表格
						item = new TableItem(table, SWT.NONE);  
						item.setText(new String[]{attribute[0],attribute[1]});
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
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				flattable="";
				menu.setVisible(false);
				composite_1.setVisible(false);
				composite_2.setVisible(true);
				composite_3.setVisible(false);
				composite_4.setVisible(false);
				/**
				 * 刷新供应商
				 */
				//刷新表格
				//清空表格
				for(TableColumn deleteColumn : table.getColumns()) {
					deleteColumn.dispose();
				}
				table.removeAll();
				//添加数据
				String[] tableHeader= {"供应商编号","供应商名称","联系人","手机电话","联系地址"};
				for(int i=0;i<tableHeader.length;i++) {
					TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
					//tableColumn.setWidth(200);
					tableColumn.setText(tableHeader[i]);
					tableColumn.setMoveable(true);
				}
				TableItem item; 
				
				
				try {
					for(String[] attribute : product_button.check_p()) {
//							System.out.println(attribute[0]+" "+attribute[1]);
						//添加入表格
						item = new TableItem(table, SWT.NONE);  
						item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4]});
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
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				flattable="";
				menu.setVisible(false);
				composite_1.setVisible(false);
				composite_2.setVisible(false);
				composite_3.setVisible(true);
				composite_4.setVisible(false);
				//单位
				//刷新表格
				//清空表格
				for(TableColumn deleteColumn : table.getColumns()) {
					deleteColumn.dispose();
				}
				table.removeAll();
				//添加数据
				String[] tableHeader= {"单位编号","单位名称"};
				for(int i=0;i<tableHeader.length;i++) {
					TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
					//tableColumn.setWidth(200);
					tableColumn.setText(tableHeader[i]);
					tableColumn.setMoveable(true);
				}
				TableItem item; 
				
				
				try {
					for(String[] attribute : product_button.check_unit()) {
//							System.out.println(attribute[0]+" "+attribute[1]);
						//添加入表格
						item = new TableItem(table, SWT.NONE);  
						item.setText(new String[]{attribute[0],attribute[1]});
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
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				flattable="rightmenu";
				composite_1.setVisible(false);
				composite_2.setVisible(false);
				composite_3.setVisible(false);
				composite_4.setVisible(true);
				
				txtNewText_1.setText("45");
				txtNewText_2.setText("70");
				
				//添加分类下拉
				int i=0;
				String[] items = null;
				try {
					items=new String[product_button.check_type_n()];
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					for(String[] attribute : product_button.check_type()) {
						System.out.println(attribute[1]);
						items[i]=attribute[1];
						i++;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				combo.setItems(items);
				//添加单位下拉
				i=0;
				items = null;
				try {
					items=new String[product_button.check_unit_n()];
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					for(String[] attribute : product_button.check_unit()) {
						System.out.println(attribute[1]);
						items[i]=attribute[1];
						i++;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				combo_1.setItems(items);
				//添加仓库下拉
				//查询仓库begin
				String sql="select count(no) from P_d";
				Statement statement;
				int ii=0;
				List<String[]> listi=new ArrayList<String[]>(); 
				try {
					statement = (Statement) con.createStatement();
					ResultSet rs=statement.executeQuery(sql);
					
					while(rs.next()) {
						ii=rs.getInt("count(no)");
					}
					
					
					
					sql="select no,address,size from P_d order by no";
					statement=(Statement) con.createStatement();
					rs=statement.executeQuery(sql);
					
					
					while(rs.next()) {
						String [] ty=new String[3];
						ty[0]=rs.getString("no");
						ty[1]=rs.getString("address");
						ty[2]=rs.getString("size");				//double类型
//						System.out.println(ty[0]+" "+ty[1]);
						listi.add(ty);
						
					}
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
				
				//查询仓库end
				i=0;
				items = null;
				items=new String[ii];
				for(String[] attribute : listi) {
					System.out.println(attribute[1]);
					items[i]=attribute[1];
					i++;
				}
				combo_2.setItems(items);
				//添加供应商下拉
				i=0;
				items = null;
				try {
					items=new String[product_button.check_p_n()];
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					for(String[] attribute : product_button.check_p()) {
						System.out.println(attribute[1]);
						items[i]=attribute[1];
						i++;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				combo_3.setItems(items);
				
				/**
				 * 刷新货物
				 */
				//刷新表格
				//清空表格
				for(TableColumn deleteColumn : table.getColumns()) {
					deleteColumn.dispose();
				}
				table.removeAll();
				//添加数据
				String[] tableHeader= {"货物编号","货物名称","货物类型","单位","仓库","供应商","进价","市场价"};
				for(i=0;i<tableHeader.length;i++) {
					TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
					//tableColumn.setWidth(200);
					tableColumn.setText(tableHeader[i]);
					tableColumn.setMoveable(true);
				}
				TableItem item; 
				
				
				try {
					for(String[] attribute : product_button.check_product()) {
//							System.out.println(attribute[0]+" "+attribute[1]);
						//添加入表格
						item = new TableItem(table, SWT.NONE);  
						item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7]});
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// 重新布局表格  
				for (i = 0; i < tableHeader.length; i++)  
				{  
				    table.getColumn(i).pack();  
				}
				formToolkit.paintBordersFor(table);
			}
		});
		//确定按钮
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * 添加分类
				 */
				try {
					product_button.insert_type(text_4.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//刷新表格
				//清空表格
				for(TableColumn deleteColumn : table.getColumns()) {
					deleteColumn.dispose();
				}
				table.removeAll();
				//添加数据
				String[] tableHeader= {"类别编号","类别名称"};
				for(int i=0;i<tableHeader.length;i++) {
					TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
					//tableColumn.setWidth(200);
					tableColumn.setText(tableHeader[i]);
					tableColumn.setMoveable(true);
				}
				TableItem item; 
				
				
				try {
					for(String[] attribute : product_button.check_type()) {
//							System.out.println(attribute[0]+" "+attribute[1]);
						//添加入表格
						item = new TableItem(table, SWT.NONE);  
						item.setText(new String[]{attribute[0],attribute[1]});
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
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				try {
					product_button.insert_unit(txtNewText_3.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				//刷新表格
				//清空表格
				for(TableColumn deleteColumn : table.getColumns()) {
					deleteColumn.dispose();
				}
				table.removeAll();
				//添加数据
				String[] tableHeader= {"单位编号","单位名称"};
				for(int i=0;i<tableHeader.length;i++) {
					TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
					//tableColumn.setWidth(200);
					tableColumn.setText(tableHeader[i]);
					tableColumn.setMoveable(true);
				}
				TableItem item; 
				
				
				try {
					for(String[] attribute : product_button.check_unit()) {
//							System.out.println(attribute[0]+" "+attribute[1]);
						//添加入表格
						item = new TableItem(table, SWT.NONE);  
						item.setText(new String[]{attribute[0],attribute[1]});
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
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//添加供应商
				
				String [] cStrings=new String[4];
				cStrings[0]=text.getText();
				cStrings[1]=text_1.getText();
				cStrings[2]=text_2.getText();
				cStrings[3]=text_3.getText();
				try {
					product_button.insert_p(cStrings);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				/**
				 * 刷新供应商
				 */
				//刷新表格
				//清空表格
				for(TableColumn deleteColumn : table.getColumns()) {
					deleteColumn.dispose();
				}
				table.removeAll();
				//添加数据
				String[] tableHeader= {"供应商编号","供应商名称","联系人","手机电话","联系地址"};
				for(int i=0;i<tableHeader.length;i++) {
					TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
					//tableColumn.setWidth(200);
					tableColumn.setText(tableHeader[i]);
					tableColumn.setMoveable(true);
				}
				TableItem item; 
				
				
				try {
					for(String[] attribute : product_button.check_p()) {
//							System.out.println(attribute[0]+" "+attribute[1]);
						//添加入表格
						item = new TableItem(table, SWT.NONE);  
						item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4]});
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
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//添加
				//获取UI数据
				String[] cStrings=new String[7];
				cStrings[0]=txtNewText.getText();			//货物名称
				cStrings[1]=combo.getText();				//分类
				cStrings[2]=combo_1.getText();				//单位
				cStrings[3]=combo_2.getText();				//仓库
				cStrings[4]=combo_3.getText();				//供应商
				cStrings[5]=txtNewText_1.getText();			//订货价
				cStrings[6]=txtNewText_2.getText();			//市场价
				//调用插入方法
				try {
					product_button.insert_product(cStrings);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				/**
				 * 刷新货物
				 */
				//刷新表格
				//清空表格
				for(TableColumn deleteColumn : table.getColumns()) {
					deleteColumn.dispose();
				}
				table.removeAll();
				//添加数据
				String[] tableHeader= {"货物编号","货物名称","货物类型","单位","仓库","供应商","进价","市场价"};
				for(int i=0;i<tableHeader.length;i++) {
					TableColumn tableColumn=new TableColumn(table,SWT.CENTER);
					//tableColumn.setWidth(200);
					tableColumn.setText(tableHeader[i]);
					tableColumn.setMoveable(true);
				}
				TableItem item; 
				
				
				try {
					for(String[] attribute : product_button.check_product()) {
//							System.out.println(attribute[0]+" "+attribute[1]);
						//添加入表格
						item = new TableItem(table, SWT.NONE);  
						item.setText(new String[]{attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5],attribute[6],attribute[7]});
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
		
		//表格右键菜单
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//如果时鼠标右键
				if(e.button==java.awt.event.MouseEvent.BUTTON3&&flattable.equals("rightmenu")) {
					/**
					 * 只有当点击货物表格时才调用右键菜单
					 */
					if(flattable.equals("rightmenu")) {
						System.out.println("右键点击");
						menu.setVisible(true);
					}
					
					
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
		return new Point(581, 417);
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
}
