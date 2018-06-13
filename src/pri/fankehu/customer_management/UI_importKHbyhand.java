package pri.fankehu.customer_management;

import java.sql.Connection;
import java.sql.SQLException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class UI_importKHbyhand extends Dialog {
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtNewText;
	private Text txtNewText_1;
	private Text txtNewText_2;
	private Text txtNewText_3;
	private Text txtNewText_4;
	private Text txtNewText_5;
	private Text txtNewText_6;
	private Combo combo;
	private Combo combo_1;
	private Connection con;//连接
	private boolean isOK=false;
	Insert_c_KHbyhand insert_c_KHbyhand;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public UI_importKHbyhand(Shell parentShell) {
		super(parentShell);
	}
	
//	public UI_importKHbyhand return_this() {
//		return this;
//	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 2;
		
		insert_c_KHbyhand=new Insert_c_KHbyhand();
		insert_c_KHbyhand.setCon(getCon());
		
		
		Label label = formToolkit.createLabel(container, "\u5BA2\u6237\u540D\u79F0\uFF1A", SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText.setText("");
		txtNewText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_1 = formToolkit.createLabel(container, "\u5BA2\u6237\u7C7B\u578B\uFF1A", SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		combo = new Combo(container, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(combo);
		formToolkit.paintBordersFor(combo);
		//添加客户类型
		int i=0;
		String[] items = null;
		try {
			items=new String[insert_c_KHbyhand.check_type_n()];
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			for(String[] attribute : insert_c_KHbyhand.check_type()) {
				System.out.println(attribute[1]);
				items[i]=attribute[1];
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		combo.setItems(items);
		
		Label label_2 = formToolkit.createLabel(container, "\u5BA2\u6237\u5730\u533A\uFF1A", SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		combo_1 = new Combo(container, SWT.NONE);
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(combo_1);
		formToolkit.paintBordersFor(combo_1);
		
		//添加客户地区
		i=0;
		String[] items1 = null;
		try {
			items1=new String[insert_c_KHbyhand.check_place_n()];
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			for(String[] attribute : insert_c_KHbyhand.check_place()) {
//				System.out.println(attribute[1]);
				items1[i]=attribute[1];
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		combo_1.setItems(items1);
		
		
		
		Label label_3 = formToolkit.createLabel(container, "\u8054\u7CFB\u4EBA\uFF1A", SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_1 = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText_1.setText("\u9ED8\u8BA4\u8054\u7CFB\u4EBA");
		txtNewText_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_4 = formToolkit.createLabel(container, "\u4E1A\u52A1\u5458\uFF1A", SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_2 = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText_2.setText("\u9ED8\u8BA4\u4E1A\u52A1\u5458");
		txtNewText_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_6 = formToolkit.createLabel(container, "\u7535\u8BDD\u53F7\u7801\uFF1A", SWT.NONE);
		label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_3 = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText_3.setText("10010111111");
		txtNewText_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_7 = formToolkit.createLabel(container, "\u7535\u5B50\u90AE\u7BB1\uFF1A", SWT.NONE);
		label_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_4 = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText_4.setText("abc@yeah.net");
		txtNewText_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_8 = formToolkit.createLabel(container, "\u8054\u7CFB\u5730\u5740\uFF1A", SWT.NONE);
		label_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_5 = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText_5.setText("\u56DB\u5DDD\u7701\u6210\u90FD");
		txtNewText_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_5 = formToolkit.createLabel(container, "\u5907\u6CE8\uFF1A", SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_6 = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText_6.setText("\u5907\u6CE8\u4FE1\u606F");
		txtNewText_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
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
				 * OK按钮点下时插入数据
				 */
				String [] message=new String[9];
				message[0]=txtNewText.getText();	//cname
				message[1]=combo.getText();			//ctype
				message[2]=combo_1.getText();		//cplace
				message[3]=txtNewText_1.getText();	//coname
				message[4]=txtNewText_2.getText();	//yname
				message[5]=txtNewText_3.getText();	//phone
				message[6]=txtNewText_4.getText();	//mail
				message[7]=txtNewText_5.getText();	//address
				message[8]=txtNewText_6.getText();	//note
				//调用插入方法   
				try {
					insert_c_KHbyhand.insert(message);
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
		return new Point(377, 419);
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	public Insert_c_KHbyhand getInsert_c_KHbyhand(){
		return insert_c_KHbyhand;
	}

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}
}
