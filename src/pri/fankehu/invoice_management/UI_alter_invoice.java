package pri.fankehu.invoice_management;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class UI_alter_invoice extends Dialog {

	private Connection con;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text text;
	private Text text_1;
	private String [] cStrings=new String[2];
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public UI_alter_invoice(Shell parentShell) {
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
		
		Label label = formToolkit.createLabel(container, "\u4FEE\u6539\u53D1\u7968", SWT.NONE);
		label.setBounds(5, 5, 48, 17);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		Label label_1 = formToolkit.createLabel(container, "\u4FEE\u6539\u53D1\u7968\u53F7\u7801\uFF1A", SWT.NONE);
		label_1.setBounds(5, 49, 84, 17);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		text = formToolkit.createText(container, "New Text", SWT.NONE);
		
		text.setBounds(5, 71, 339, 23);
		text.setText(cStrings[1]);
		
		Label label_2 = formToolkit.createLabel(container, "\u4FEE\u6539\u7EB3\u7A0E\u4EBA\u8BC6\u522B\u53F7\uFF1A", SWT.NONE);
		label_2.setBounds(5, 121, 108, 17);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		text_1 = formToolkit.createText(container, "New Text", SWT.NONE);
		
		text_1.setBounds(5, 143, 339, 23);
		text_1.setText(cStrings[2]);
		
		Label label_3 = formToolkit.createLabel(container, "", SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_3.setBounds(108, 49, 61, 17);
		
		Label label_4 = formToolkit.createLabel(container, "", SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_4.setBounds(119, 121, 61, 17);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				label_3.setText(String.valueOf(text.getText().length())+" / 20");
			}
		});
		text_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				label_4.setText(String.valueOf(text_1.getText().length())+" / 20");
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
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				/**
				 * 点下时更新
				 */
				cStrings[1]=text.getText();
				cStrings[2]=text_1.getText();
				Add_invoice add_invoice=new Add_invoice();
				add_invoice.setCon(getCon());
				try {
					add_invoice.update_invoice(getcStrings());
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
		return new Point(365, 350);
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public String [] getcStrings() {
		return cStrings;
	}

	public void setcStrings(String [] cStrings) {
		this.cStrings = cStrings;
	}
}
