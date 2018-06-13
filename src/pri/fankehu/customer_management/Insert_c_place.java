package pri.fankehu.customer_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

/**
 * 客户地区操作
 * @author fanke
 *
 */
public class Insert_c_place {
	private Connection con;//连接
	
	private Statement statement;
	private ResultSet rs;
	/**
	 * 首先查询客户地区
	 * @return
	 * @throws SQLException 
	 */
	
	public List<String[]> check() throws SQLException {
		String sql="select No,place from place order by No";
		statement=con.createStatement();
		rs=statement.executeQuery(sql);
		
		List<String[]> list=new ArrayList<String[]>(); 
		while(rs.next()) {
			String [] ty=new String[2];
			ty[0]=rs.getString("No");
			ty[1]=rs.getString("place");
//			System.out.println(ty[0]+" "+ty[1]);
			list.add(ty);
			
		}
		return list;
	}
	
	/**
	 * 插入方法
	 * @return
	 * @throws SQLException 
	 */
	public void insert(String insertMessage) throws SQLException {

		String sqql="select max(No),count(No) from place;";
		statement=con.createStatement();
		rs=statement.executeQuery(sqql);
		int i=0;
		while(rs.next()) {
//			System.out.println(Integer.valueOf(rs.getString("max(No)"))+1);
			i=Integer.valueOf(rs.getString("count(No)"))>0?(Integer.valueOf(rs.getString("max(No)"))+1):(1);
//			i=Integer.valueOf(rs.getString("max(No)"))+1;
		}	
		String sql="insert into place (No,place) values ("+i+",'"+insertMessage+"');";
		System.out.println(sql);
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		
		preparedStatement.executeUpdate();
//		con.commit();
	}
	/**
	 * 删除方法
	 * @return
	 * @throws SQLException 
	 */
	public void delete(int No) throws SQLException {
		String sql="delete from place where No="+No;
		statement=con.createStatement();
		statement.executeUpdate(sql);
//		con.commit();
	}
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
}
