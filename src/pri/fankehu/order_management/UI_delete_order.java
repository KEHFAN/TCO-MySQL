package pri.fankehu.order_management;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

public class UI_delete_order extends Dialog {

	private Connection con;
	private String[] cStrings;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public UI_delete_order(Shell parentShell) {
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
		
		Label label = new Label(container, SWT.NONE);
		label.setBounds(71, 41, 60, 17);
		label.setText("\u786E\u5B9A\u5C06\u8BA2\u5355");
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setBounds(71, 67, 223, 17);
		
		label_1.setText(cStrings[0]);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setBounds(71, 112, 61, 17);
		label_2.setText("\u5220\u9664\uFF1F");

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
				Add_order add_order=new Add_order();
				add_order.setCon(getCon());
				
				
				
				
				try {
					add_order.delete_order(cStrings[0]);
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
		return new Point(450, 300);
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public String[] getcStrings() {
		return cStrings;
	}

	public void setcStrings(String[] cStrings) {
		this.cStrings = cStrings;
	}

}
