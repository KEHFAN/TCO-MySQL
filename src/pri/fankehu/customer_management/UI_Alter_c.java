package pri.fankehu.customer_management;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ibm.icu.util.Calendar;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class UI_Alter_c extends Dialog {
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtNewText;
	private Text txtNewText_1;
	private Text txtNewText_2;
	private Text txtNewText_3;
	private Text txtNewText_4;
	private Text txtNewText_5;
	private Text txtNewText_6;
	private Combo combo;
	private Combo combo_1 ;
//	priavte String [] cString;
	private String [] cStrings;
	private Connection con;//连接
	private boolean isOK=false;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public UI_Alter_c(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 2;
		
		Label label = formToolkit.createLabel(container, "\u4FEE\u6539\u4FE1\u606F", SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		new Label(container, SWT.NONE);
		
		Label label_1 = formToolkit.createLabel(container, "\u5BA2\u6237\u540D\u79F0\uFF1A", SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtNewText.setText(getcStrings()[1]);
		
		Label label_2 = formToolkit.createLabel(container, "\u5BA2\u6237\u7C7B\u578B\uFF1A", SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		combo = new Combo(container, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(combo);
		formToolkit.paintBordersFor(combo);
		//添加客户类型
		Insert_c_KHbyhand insert_c_KHbyhand=new Insert_c_KHbyhand();
		//传入con
		insert_c_KHbyhand.setCon(getCon());
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
		combo.setText(getcStrings()[2]);
		
		
		Label label_3 = formToolkit.createLabel(container, "\u5BA2\u6237\u5730\u533A\uFF1A", SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
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
		combo_1.setText(getcStrings()[3]);
		
		Label label_4 = formToolkit.createLabel(container, "\u8054\u7CFB\u4EBA\uFF1A", SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_1 = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtNewText_1.setText(getcStrings()[4]);
		
		Label label_5 = formToolkit.createLabel(container, "\u4E1A\u52A1\u5458\uFF1A", SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_2 = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtNewText_2.setText(getcStrings()[5]);
		
		Label label_6 = formToolkit.createLabel(container, "\u7535\u8BDD\u53F7\u7801\uFF1A", SWT.NONE);
		label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_3 = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtNewText_3.setText(getcStrings()[6]);
		
		Label label_7 = formToolkit.createLabel(container, "\u7535\u5B50\u90AE\u7BB1\uFF1A", SWT.NONE);
		label_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_4 = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtNewText_4.setText(getcStrings()[7]);
		
		Label label_8 = formToolkit.createLabel(container, "\u8054\u7CFB\u5730\u5740\uFF1A", SWT.NONE);
		label_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_5 = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtNewText_5.setText(getcStrings()[10]);
		
		Label label_9 = formToolkit.createLabel(container, "\u5907\u6CE8\uFF1A", SWT.NONE);
		label_9.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_6 = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtNewText_6.setText(getcStrings()[11]);
		
		for(String ss1 : getcStrings()) {
			System.out.println(ss1);}
		
		
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
				//当OK点击时将数据传出
				cStrings[1]=txtNewText.getText();
				cStrings[2]=combo.getText();
				cStrings[3]=combo_1.getText();
				cStrings[4]=txtNewText_1.getText();
				cStrings[5]=txtNewText_2.getText();
				cStrings[6]=txtNewText_3.getText();
				cStrings[7]=txtNewText_4.getText();
				
				//获取当前日期
				//计算当前日期
				Calendar now=Calendar.getInstance();
				String now_date;
				now_date=String.valueOf(now.get(Calendar.YEAR))+"/"+String.valueOf(now.get(Calendar.MONTH))+"/"+String.valueOf(now.get(Calendar.DAY_OF_MONTH));
				
				cStrings[9]=now_date;
				cStrings[10]=txtNewText_5.getText();
				cStrings[11]=txtNewText_6.getText();
				setOK(true);
			}
		});
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(378, 430);
	}

	public String [] getcStrings() {
		return cStrings;
	}

	public void setcStrings(String [] cStrings) {
		this.cStrings = cStrings;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

}
