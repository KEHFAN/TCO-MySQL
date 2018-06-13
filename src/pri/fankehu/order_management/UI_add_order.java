package pri.fankehu.order_management;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class UI_add_order extends Dialog {
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Connection con;
	private String pid;
	private String cid;
	Add_order add_order=new Add_order();
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public UI_add_order(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(2, false));
		
		
		add_order.setCon(getCon());
		
		Label label = formToolkit.createLabel(container, "\u65B0\u589E\u8BA2\u5355", SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		new Label(container, SWT.NONE);
		
		@SuppressWarnings("unused")
		Label label_1 = formToolkit.createLabel(container, "\u5BA2\u6237\u540D\u79F0\uFF1A", SWT.NONE);
		
		Combo combo = new Combo(container, SWT.READ_ONLY);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//计算客户编号
				List<String []> lisc=new ArrayList<String []>();
				try {
					lisc=add_order.check_c();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//计算选中客户编号
				for(String [] attribute : lisc) {
					if(attribute[1].equals(combo.getText())) {
						cid=attribute[0];
						break;
					}
				}
			}
		});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(combo);
		formToolkit.paintBordersFor(combo);
		
		//添加客户下拉
		int i=0;
		String [] items=null;
		try {
			items=new String[add_order.check_c_n()];
			for(String [] attribute : add_order.check_c()) {
				items[i]=attribute[1];
				i++;
			}
			combo.setItems(items);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Label label_2 = formToolkit.createLabel(container, "\u8D27\u7269\u540D\u79F0\uFF1A", SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Combo combo_1 = new Combo(container, SWT.READ_ONLY);
		
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(combo_1);
		formToolkit.paintBordersFor(combo_1);
		
		//添加货物下拉
		i=0;
		items=null;
		try {
			items=new String[add_order.check_p_n()];
			for(String [] attribute : add_order.check_p()) {
				items[i]=attribute[1];
				i++;
			}
			combo_1.setItems(items);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Label label_3 = formToolkit.createLabel(container, "\u4EF7\u683C\uFF1A", SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text = formToolkit.createText(container, "New Text", SWT.READ_ONLY);
		text.setText("");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_4 = formToolkit.createLabel(container, "\u8BA2\u8D2D\u6570\u91CF\uFF1A", SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_1 = formToolkit.createText(container, "New Text", SWT.NONE);
		
		text_1.setText("");
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_5 = formToolkit.createLabel(container, "\u603B\u989D\uFF1A", SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_2 = formToolkit.createText(container, "New Text", SWT.READ_ONLY);
		text_2.setText("");
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		text_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				/**
				 * 当不为空时计算总额
				 */
				if((!text_1.getText().equals(""))&&(!text_1.getText().equals(null))) {
					double sum=Double.valueOf(text_1.getText());//获取个数
					System.out.println("订购数量为："+sum);
					text_2.setText(String.valueOf(sum*Double.valueOf(text.getText())));
				}
			}
		});
		
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//选择某个货物时 将价格等信息输入，自动计算总额
				text_1.setText("");
				text_2.setText("");
				List<String []> lisp=new ArrayList<String []>();
				@SuppressWarnings("unused")
				List<String []> lisc=new ArrayList<String []>();
				try {
//					String pid=null;
//					String cid=null;
					lisp=add_order.check_p();
					//计算选中客户编号
					
					//计算选中货物的编号
					for(String [] attribute : lisp) {
						if(attribute[1].equals(combo_1.getText())) {
							pid=attribute[0];
							text.setText(attribute[2]);
							break;
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		Label label_6 = formToolkit.createLabel(container, "\u4ED8\u6B3E\u65B9\u5F0F\uFF1A", SWT.NONE);
		label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_3 = formToolkit.createText(container, "New Text", SWT.NONE);
		text_3.setText("");
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_7 = formToolkit.createLabel(container, "\u53D1\u8D27\u5730\u5740\uFF1A", SWT.NONE);
		label_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_4 = formToolkit.createText(container, "New Text", SWT.NONE);
		text_4.setText("");
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				/**
				 * 按钮点下时添加
				 */
				String [] cStrings=new String[7];
				//
				cStrings[0]=cid;//客户编号
				cStrings[1]=pid;//货物编号
				cStrings[2]=text.getText();//价格
				cStrings[3]=text_1.getText();//订购数量
				cStrings[4]=text_2.getText();//总额
				cStrings[5]=text_3.getText();//付款方式
				cStrings[6]=text_4.getText();//发货地址
				
				//调用insert方法
				try {
					add_order.order_insert(cStrings);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(322, 336);
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

}
