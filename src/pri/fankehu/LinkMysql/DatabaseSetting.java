package pri.fankehu.LinkMysql;

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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class DatabaseSetting extends Dialog {
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtNewText;
	private Text txtNewText_1;
	private Text txtNewText_2;
	private Text txtNewText_3;
	private Text txtNewText_4;
	private boolean Selection;//�жϸ�ѡ���Ƿ�ѡ
	private DataBaseLink dataBaseLink;//��ȡ�������ݿ���󣬸ö����������ڴ�ʱ����
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public DatabaseSetting(Shell parentShell) {
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
		
		Label lblHost = formToolkit.createLabel(container, "host", SWT.NONE);
		lblHost.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblHost.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText.setText("");
		txtNewText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDatabase = formToolkit.createLabel(container, "database", SWT.NONE);
		lblDatabase.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblDatabase.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_1 = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText_1.setText("");
		txtNewText_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel = formToolkit.createLabel(container, "port", SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_2 = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText_2.setText("");
		txtNewText_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblUser = formToolkit.createLabel(container, "user", SWT.NONE);
		lblUser.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblUser.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_3 = formToolkit.createText(container, "New Text", SWT.NONE);
		txtNewText_3.setText("");
		txtNewText_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblPassword = formToolkit.createLabel(container, "password", SWT.NONE);
		lblPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtNewText_4 = formToolkit.createText(container, "New Text", SWT.PASSWORD);
		txtNewText_4.setText("");
		txtNewText_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		

		
		Button button = new Button(container, SWT.CHECK);
		button.setSelection(true);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * ��ѡ��Ĭ�Ϲ�ѡ
				 * �������ݿ�Ĭ������ѡ��
				 */
				if(!button.getSelection()) {
					txtNewText.setText("");
					txtNewText_1.setText("");
					txtNewText_2.setText("");
					txtNewText_3.setText("");
					txtNewText_4.setText("");
					Selection=false;
					//��������
				}
				else {
					Selection=true;
					getDataBaseLink().SettingInfo();
					txtNewText.setText(dataBaseLink.getHost());
					txtNewText_1.setText(dataBaseLink.getDatabase());
					txtNewText_2.setText(String.valueOf(dataBaseLink.getPort()));
					txtNewText_3.setText(dataBaseLink.getUser());
					txtNewText_4.setText(dataBaseLink.getPassword());
				}
			}
		});
		formToolkit.adapt(button, true, true);
		button.setText("\u4F7F\u7528\u9ED8\u8BA4\u4FE1\u606F\u767B\u5F55");
		/**
		 * ��⸴ѡ���Ƿ�ѡ��
		 * ����Ѿ���ѡ������Ĭ��ѡ��
		 */
		if(button.getSelection()) {
			/**
			 * ����Ĭ�����ݿ�������Ϣ
			 */
			
			Selection=true;//Ĭ�Ϲ�ѡ
			txtNewText.setText(dataBaseLink.getHost());
			txtNewText_1.setText(dataBaseLink.getDatabase());
			txtNewText_2.setText(String.valueOf(dataBaseLink.getPort()));
			txtNewText_3.setText(dataBaseLink.getUser());
			txtNewText_4.setText(dataBaseLink.getPassword());
			
		}

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
				if(!Selection) {
					System.out.println("δ��ѡ״̬");
					getDataBaseLink().setHost(txtNewText.getText());
					getDataBaseLink().setDatabase(txtNewText_1.getText());
					getDataBaseLink().setPort(Integer.valueOf(txtNewText_2.getText()));
					getDataBaseLink().setUser(txtNewText_3.getText());
					getDataBaseLink().setPassword(txtNewText_4.getText());
				}
				else {
					System.out.println("��ѡ״̬");
					getDataBaseLink().SettingInfo();
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
		return new Point(282, 300);
	}

	public DataBaseLink getDataBaseLink() {
		return dataBaseLink;
	}

	public void setDataBaseLink(DataBaseLink dataBaseLink) {
		this.dataBaseLink = dataBaseLink;
	}

}
