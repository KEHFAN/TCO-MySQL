package pri.fankehu.BackStage_management;

import java.sql.Connection;

class BackManage {
	/**
	 * ��̨������
	 * ��Ҫ����������������㷽ʽ���ͻ����͵ȵ�����޸�
	 */
	private Connection con;//����

	
	/**
	 * ��ȡ�ͻ����ͷ���
	 * ���ز�ѯ�õ��Ķ���
	 * @return
	 */
	public void Check_Type() {
		//��ѯ�ͻ�����
	}
	public void Check_Place() {
		//��ѯ������
	}
	public void Check_Mon() {
		//��ѯ���㷽ʽ
	}
	/**
	 * ���²���
	 * @return
	 */
	public void Update_Type() {
		
	}
	public void Update_Place() {
		
	}
	public void Update_Mon() {
		
	}
	/**
	 * ɾ������
	 * @return
	 */
	public void Delete_Type() {
		
	}
	public void Delete_Place() {
		
	}
	public void Delete_Mon() {
		
	}
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
}
