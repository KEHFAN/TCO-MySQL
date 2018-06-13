package pri.fankehu.invoice_management;

import java.sql.Connection;
import java.sql.SQLException;

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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class UI_add_invoice extends Dialog {
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private Connection con;
	private Add_invoice add_invoice=new Add_invoice();
	private Combo combo;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public UI_add_invoice(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));
		
		add_invoice.setCon(getCon());
		
		Label label = formToolkit.createLabel(container, "\u8BF7\u9009\u62E9\u8BA2\u5355\uFF1A", SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		@SuppressWarnings("unused")
		String id=null;//订单编号
		combo = new Combo(container, SWT.READ_ONLY);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String [] info=add_invoice.check_info(combo.getText());
					text.setText(info[0]);
					text_1.setText(info[1]);
					text_2.setText(info[2]);
					text_3.setText(info[3]);
					text_4.setText(info[4]);
					text_5.setText(info[5]);
					text_6.setText(info[6]);
					text_7.setText(info[7]);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(combo);
		formToolkit.paintBordersFor(combo);
		
		/**
		 * 添加订单下拉
		 */
		int i=0;
		String [] items=null;
		try {
			items=new String[add_invoice.check_order_n()];
			for(String [] attribute : add_invoice.check_order()) {
				items[i]=attribute[0];
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		combo.setItems(items);
		
		Label label_1 = formToolkit.createLabel(container, "\u8BA2\u5355\u4FE1\u606F\uFF1A", SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		Composite composite = formToolkit.createComposite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		formToolkit.paintBordersFor(composite);
		composite.setLayout(new GridLayout(2, false));
		
		Label label_2 = formToolkit.createLabel(composite, "\u8BA2\u5355\u7F16\u53F7\uFF1A", SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text = formToolkit.createText(composite, "New Text", SWT.READ_ONLY);
		text.setText("");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_3 = formToolkit.createLabel(composite, "\u5BA2\u6237\u7F16\u53F7\uFF1A", SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_1 = formToolkit.createText(composite, "New Text", SWT.READ_ONLY);
		text_1.setText("");
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_4 = formToolkit.createLabel(composite, "\u5BA2\u6237\u540D\u79F0\uFF1A", SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_2 = formToolkit.createText(composite, "New Text", SWT.READ_ONLY);
		text_2.setText("");
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_5 = formToolkit.createLabel(container, "\u53D1\u7968\u6458\u8981\uFF1A", SWT.NONE);
		GridData gd_label_5 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_label_5.widthHint = 368;
		label_5.setLayoutData(gd_label_5);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		Composite composite_1 = formToolkit.createComposite(container, SWT.NONE);
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_composite_1.widthHint = 316;
		composite_1.setLayoutData(gd_composite_1);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		composite_1.setLayout(new GridLayout(2, false));
		formToolkit.paintBordersFor(composite_1);
		
		Label label_12 = formToolkit.createLabel(composite_1, "\u53D1\u7968\u7F16\u53F7\uFF1A", SWT.NONE);
		label_12.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_12.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_3 = formToolkit.createText(composite_1, "New Text", SWT.READ_ONLY);
		text_3.setText("");
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_6 = formToolkit.createLabel(composite_1, "\u53D1\u7968\u91D1\u989D\uFF1A", SWT.NONE);
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_4 = formToolkit.createText(composite_1, "New Text", SWT.READ_ONLY);
		text_4.setText("");
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_7 = formToolkit.createLabel(composite_1, "\u53D1\u7968\u7A0E\u989D\uFF1A", SWT.NONE);
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_5 = formToolkit.createText(composite_1, "New Text", SWT.READ_ONLY);
		text_5.setText("");
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_8 = formToolkit.createLabel(composite_1, "\u4EF7\u7A0E\u5408\u8BA1\uFF1A", SWT.NONE);
		label_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_6 = formToolkit.createText(composite_1, "New Text", SWT.READ_ONLY);
		text_6.setText("");
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_9 = formToolkit.createLabel(composite_1, "\u5F00\u7968\u65E5\u671F\uFF1A", SWT.NONE);
		label_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_9.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_7 = formToolkit.createText(composite_1, "New Text", SWT.READ_ONLY);
		text_7.setText("");
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_10 = formToolkit.createLabel(container, "\u586B\u5199\u4FE1\u606F\uFF1A", SWT.NONE);
		label_10.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		label_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		Composite composite_2 = formToolkit.createComposite(container, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		composite_2.setLayout(new GridLayout(2, false));
		formToolkit.paintBordersFor(composite_2);
		
		Label label_11 = formToolkit.createLabel(composite_2, "\u53D1\u7968\u53F7\u7801\uFF1A", SWT.NONE);
		label_11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_11.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_8 = formToolkit.createText(composite_2, "New Text", SWT.NONE);
		text_8.setText("");
		text_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_13 = formToolkit.createLabel(composite_2, "\u7EB3\u7A0E\u4EBA\u8BC6\u522B\u53F7\uFF1A", SWT.NONE);
		label_13.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_13.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text_9 = formToolkit.createText(composite_2, "New Text", SWT.NONE);
		text_9.setText("");
		text_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

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
				String[] info;
				String[] insertinfo=new String[9];
				try {
					info = add_invoice.check_info(combo.getText());
					
					insertinfo[0]=info[3];
					insertinfo[1]=text_8.getText();
					insertinfo[2]=info[1];
					insertinfo[3]=info[0];
					insertinfo[4]=info[4];
					insertinfo[5]=info[5];
					insertinfo[6]=info[6];
					insertinfo[7]=info[7];
					insertinfo[8]=text_9.getText();
					add_invoice.add_invoice(insertinfo);
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
		return new Point(397, 524);
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
}
