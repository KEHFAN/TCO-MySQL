package pri.fankehu.BackStage_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Users {
	/**
	 * �ڱ����п���Ȩ��
	 * ֻ���ж�
	 * �˺ţ�system
	 * ���룺666666
	 * �ŷ��ز�ѯ�����˺���Ϣ
	 * ��ͨ�û������ظ����˺�����
	 */
	private Connection con;
	//��ѯ��ͨ�û�
	public String [] check_user(String [] user) throws SQLException {
		String sql="select acount,password from users "
				+ "where acount='"+user[0]+"' "
						+ "and password='"+user[1]+"';";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sql);
		String [] ty=new String[2];
		while(rs.next()) {
			ty[0]=rs.getString("acount");
			ty[1]=rs.getString("password");
		}
		return ty;
	}
	//��ѯsystem�����˻�
	public List<String []> check_users() throws SQLException{
		String sql="select acount,password from users";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sql);
		
		List<String[]> list=new ArrayList<String[]>(); 
		while(rs.next()) {
			String [] ty=new String[2];
			ty[0]=rs.getString("acount");
			ty[1]=rs.getString("password");
//			System.out.println(ty[0]+" "+ty[1]);
			list.add(ty);
			
		}
		return list;
	}
	//�޸��˻���Ϣ
	public void update(String [] user,String[] upuser) throws SQLException {
		String sql="update users set acount='"+upuser[0]+"',password='"+upuser[1]+"' "
				+ "where acount='"+user[0]+"' "
				+ "and password='"+user[1]+"';";
//		System.out.println(sql);
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		preparedStatement.executeUpdate();
	}
	//�޸��˻���Ϣ
		public void updates(String[] upuser) throws SQLException {
			String sql="update users set acount='"+upuser[0]+"',password='"+upuser[1]+"' "
					+ "where acount='"+upuser[0]+"' "
					+ "and password='"+upuser[1]+"';";
//			System.out.println(sql);
			PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
			preparedStatement.executeUpdate();
		}
	//ɾ���˻���Ϣ
	public void delete(String [] user) throws SQLException {
		String sql="delete from users "
				+ "where acount='"+user[0]+"' "
				+ "and password='"+user[1]+"';";
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		preparedStatement.executeUpdate();
	}
	//�����˻���Ϣ
	public void insert(String [] user) throws SQLException {
		String sql="insert into users (acount,password) values ('"+user[0]+"','"+user[1]+"');";
//		System.out.println(sql);
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		preparedStatement.executeUpdate();
	}
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
}
