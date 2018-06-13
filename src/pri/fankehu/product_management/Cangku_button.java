package pri.fankehu.product_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Cangku_button {
	/**
	 * 库存选项所有操作
	 * 增加库存，减少库存
	 */
	private Connection con;//连接
	public void add(String [] add) throws SQLException {
		//首先判断是否存在
		String sqql="select count(no) from P_state where no='"+add[0]+"';";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sqql);
		int count=0;
		while(rs.next()) {
			count=rs.getInt("count(no)");
		}
		if(count>0) {
			//做更新操作
			double k=Double.valueOf(add[2])+Double.valueOf(add[4]);
			double s=Double.valueOf(add[2])+Double.valueOf(add[4]);
			sqql="update P_state set "
					+ "k="+k+","
					+ "s="+s+","
					+ "a="+Double.valueOf(add[3])+","
					+ "c="+Double.valueOf(add[4])
					+" where no='"+add[0]+"'"
							+ ";";
			System.out.println(sqql);
			PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sqql);
			
			preparedStatement.executeUpdate();
		}
		else {
			//做插入操作
			double kk=Double.valueOf(add[1])+Double.valueOf(add[4]);
			double ss=Double.valueOf(add[2])+Double.valueOf(add[4]);
			sqql="insert into P_state (no,k,s,a,c) values ("
					+ "'"+add[0]+"',"
					+ ""+kk+","
					+ ""+ss+","
					+ ""+Double.valueOf(add[3])+","
					+ ""+Double.valueOf(add[4])
					+ ");";
			System.out.println(sqql);
			PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sqql);
			
			preparedStatement.executeUpdate();
		}
	}
	public String [] check_add(String id) throws SQLException {
		//更加ID查询库存状态
		String sql="select k,s,a,c from P_state "
				+ "where P_state.no='"+id+"';";
		System.out.println(sql);
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sql);
		String[] num=new String[5];
		while(rs.next()) {
			num[0]=rs.getString("k");
			num[1]=rs.getString("k");
			num[2]=rs.getString("s");
			num[3]=rs.getString("a");
			num[4]=rs.getString("c");
		}
		if(Double.valueOf(num[1])>0&&Double.valueOf(num[2])>0&&Double.valueOf(num[3])>0) {
			double c=Double.valueOf(num[3])-Double.valueOf(num[2]);
			if(c>0) {
				num[4]=String.valueOf(c);
			}
		}
		return num;
		
	}
	public void out(String [] out) throws SQLException {
		//update
		//做更新操作
		double k=Double.valueOf(out[2])-Double.valueOf(out[4]);
		double s=Double.valueOf(out[2])-Double.valueOf(out[4]);
		String sqql="update P_state set "
				+ "k="+k+","
				+ "s="+s+""
				+" where no='"+out[0]+"'"
				+ ";";
		System.out.println(sqql);
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sqql);
		
		preparedStatement.executeUpdate();
	}
	public String [] check_out(String id) throws SQLException {
		//更加ID查询库存状态
				String sql="select k,s,a,c from P_state "
						+ "where P_state.no='"+id+"';";
				System.out.println(sql);
				Statement statement=(Statement) con.createStatement();
				ResultSet rs=statement.executeQuery(sql);
				String[] num=new String[5];
				while(rs.next()) {
					num[0]=rs.getString("k");//不显示
					num[1]=rs.getString("k");
					num[2]=rs.getString("s");
					num[3]=rs.getString("a");
					num[4]=rs.getString("c");//不显示
				}
				if(Double.valueOf(num[1])>0&&Double.valueOf(num[2])>0&&Double.valueOf(num[3])>0) {
					double c=Double.valueOf(num[3])-Double.valueOf(num[2]);
					if(c>0) {
						num[4]=String.valueOf(c);
					}
				}
				return num;
	}
	/**
	 * 仓库
	 * 增删查
	 * @throws SQLException 
	 */
	public void insert_cangku(String [] cString) throws SQLException {
		String sqql="select max(no),count(no) from P_d;";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sqql);
		int i=0;
		while(rs.next()) {
//			System.out.println(Integer.valueOf(rs.getString("max(No)"))+1);
			i=Integer.valueOf(rs.getString("count(no)"))>0?((Integer.valueOf(rs.getString("max(no)"))+1)):(1);
//			i=Integer.valueOf(rs.getString("max(No)"))+1;
		}	
		String sql="insert into P_d (no,address,size) values ("
				+ ""+i+","					//编号
				+ "'"+cString[0]+"',"		//地址
				+ ""+Double.valueOf(cString[1])+""		//大小
				+ ");";
		System.out.println(sql);
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		
		preparedStatement.executeUpdate();
	}
	public void delete_cangku(int No) throws SQLException {
		String sql="delete from P_d where no="+No;
		Statement statement=(Statement) con.createStatement();
		statement.executeUpdate(sql);
	}
	public List<String[]> check_cangku() throws SQLException {
		String sql="select no,address,size from P_d order by no";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sql);
		
		List<String[]> list=new ArrayList<String[]>(); 
		while(rs.next()) {
			String [] ty=new String[3];
			ty[0]=rs.getString("no");
			ty[1]=rs.getString("address");
			ty[2]=rs.getString("size");				//double类型
//			System.out.println(ty[0]+" "+ty[1]);
			list.add(ty);
			
		}
		return list;
	}
	public int check_cangku_n() throws SQLException {
		String sql="select count(no) from P_d";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sql);
		int i=0;
		while(rs.next()) {
			i=rs.getInt("count(no)");
		}
		return i;
	}
	
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
}
