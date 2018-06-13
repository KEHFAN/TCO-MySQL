package pri.fankehu.customer_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class Alter_c {
	private String [] cStrings;
	private Connection con;//连接
	
	//更新
	
	public void Update() throws SQLException {
		Statement statement=(Statement) getCon().createStatement();
		//计算类型编号
		int c_ty=0;
		String sqql="select No from type where type='"+getcStrings()[2]+"';";//未完
//		statement=con.createStatement();
		ResultSet rs=statement.executeQuery(sqql);
		while(rs.next()) {
			c_ty=rs.getInt("No");
		}
		//计算地区编号
		int c_pl=0;
		sqql="select No from place where place='"+getcStrings()[3]+"';";
//		statement=con.createStatement();
		rs=statement.executeQuery(sqql);
		while(rs.next()) {
			c_pl=rs.getInt("No");
		}
		
		
		String sql="update C set (name,Typ_No,Pla_No,co_name,y_name,tel,mail,addres,note,al_date) = "
				+ "('"+getcStrings()[1]+"',"
				+ c_ty+","
				+ c_pl+","
				+ "'"+getcStrings()[4]+"',"
				+ "'"+getcStrings()[5]+"',"
				+ "'"+getcStrings()[6]+"',"
				+ "'"+getcStrings()[7]+"',"
				+ "'"+getcStrings()[10]+"',"
				+ "'"+getcStrings()[11]+"',"
				+ "'"+getcStrings()[9]+"') "
				+ " where No='"+getcStrings()[0]+"';";
//		sql="update C set (name,Typ_No,Pla_No,co_name,y_name,tel,mail,addres,note,al_date)= ('张晓峰',1,2,'李明','默认业务员','10010111111','abc@yeah.net','四川省成都','土豪','2018/4/29')  where No='1000000004';";
		sql="update C set "
				+ "name='"+getcStrings()[1]+"',"
				+ "Typ_No="+c_ty+","
				+ "Pla_No="+ c_pl+","
				+ "co_name="+ "'"+getcStrings()[4]+"',"
				+ "y_name="+ "'"+getcStrings()[5]+"',"
				+ "tel="+ "'"+getcStrings()[6]+"',"
				+ "mail="+ "'"+getcStrings()[7]+"',"
				+ "addres="+ "'"+getcStrings()[10]+"',"
				+ "note="+ "'"+getcStrings()[11]+"',"
				+ "al_date="+ "'"+getcStrings()[9]+"'  "
				+ " where No='"+getcStrings()[0]+"';";
		System.out.println(sql);
		statement.executeUpdate(sql);
		/**
		 * String sql="insert into C (No,name,Typ_No,Pla_No,co_name,y_name,tel,mail,addres,note,in_date,al_date"
				+ ") values ("
				+ "'"+id+"',"
				+ "'"+message[0]+"',"
				+ c_ty+","
				+ c_pl+","
				+ "'"+message[3]+"',"
				+ "'"+message[4]+"',"
				+ "'"+message[5]+"',"
				+ "'"+message[6]+"',"
				+ "'"+message[7]+"',"
				+ "'"+message[8]+"',"
				+ "'"+now_date+"',"
				+ "'"+now_date+"'"
				+ ");";
		 */
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
}
