package pri.fankehu.order_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Add_order {
	private Connection con;

	
	/**
	 * 新增订单操作
	 * 客户查询
	 * 货物查询
	 * 订单增加
	 * 订单删除
	 * 订单修改
	 * @return
	 */
	
	//客户查
	public List<String[]> check_c() throws SQLException {
		String sql="select No,name from C order by No";
		Statement statement=(Statement) getCon().createStatement();
		ResultSet rs=statement.executeQuery(sql);
		List<String[]> list=new ArrayList<String[]>();
		while(rs.next()) {
			String [] ty=new String[2];
			ty[0]=rs.getString("No");
			ty[1]=rs.getString("name");
			
//			System.out.println(ty[0]+" "+ty[1]);
			list.add(ty);
		}
		return list;
	}
	public int check_c_n() throws SQLException {
		String sql="select count(No) from C";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sql);
		int i=0;
		while(rs.next()) {
			i=rs.getInt("count(No)");
		}
		return i;
	}
	
	//货物查
	public List<String[]> check_p() throws SQLException {
		String sql="select no,name,o_price from P_info order by no";
		Statement statement=(Statement) getCon().createStatement();
		ResultSet rs=statement.executeQuery(sql);
		List<String[]> list=new ArrayList<String[]>();
		while(rs.next()) {
			String [] ty=new String[3];
			ty[0]=rs.getString("no");
			ty[1]=rs.getString("name");
			ty[2]=rs.getString("o_price");
//			System.out.println(ty[0]+" "+ty[1]);
			list.add(ty);
		}
		return list;
	}
	public int check_p_n() throws SQLException {
		String sql="select count(no) from P_info";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sql);
		int i=0;
		while(rs.next()) {
			i=rs.getInt("count(no)");
		}
		return i;
	}
	
	//订单查
	
	//订单增
	public void order_insert(String [] message) throws SQLException {
		//计算订单编号
		
		String sqql="select max(no),count(no) from orde;";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sqql);
		String id=null;
		while(rs.next()) {
			id=Integer.valueOf(rs.getString("count(no)"))>0?String.valueOf((Integer.valueOf(rs.getString("max(no)"))+1)):("1000000001");
		}
				
				
//				System.out.println(id+" "+c_ty+" "+c_pl+" "+message[3]);
//				System.out.println(message[7]);
		String sql="insert into orde (no,pno,cno,num,way,address"
				+ ") values ("
				+ "'"+id+"',"
				+ "'"+message[1]+"',"
				+ "'"+message[0]+"',"
				
				+ ""+Double.valueOf(message[3])+","
				
				+ "'"+message[5]+"',"
				+ "'"+message[6]+"'"
				
				+ ");";
		//查询时自动计算价格
		System.out.println(sql);
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		preparedStatement.executeUpdate();
	}
	//订单删
	public void delete_order(String id) throws SQLException {
		String sql="delete from orde where no='"+id+"';";
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		preparedStatement.executeUpdate();
		
	}
	//订单改
	public void update_order(String [] updateinfo) throws SQLException {
		String sql="update orde set way='"+updateinfo[6]+"',"
				+ "address='"+updateinfo[7]+"' "
						+ "where no='"+updateinfo[0]+"';";
		System.out.println(sql);
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		preparedStatement.executeUpdate();
		
		/**
		 * 可以这样写
		 * String sql2 = "update person set name=? where number=?";  
            PreparedStatement pst = conn.prepareStatement(sql2);  
            pst.setString(1,"1110520218");  
            pst.setInt(2,198);  
            pst.executeUpdate();  
		 */
		
	}
	
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	

}
