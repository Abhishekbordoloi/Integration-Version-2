package repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db_package.*;
import repository.ClassifiedRepository;
import until.enumeration.CategoryType;
import until.enumeration.ClassifiedStatus;
import util.Classified;
import util.MySQLJDBCUtil;

public class ClassifiedRepositoryImpl implements ClassifiedRepository {

	// ***************************print****************************//

	@Override
	public List<Classified> getClassifiedList() throws SQLException{
		List<Classified> classifiedList = new ArrayList<Classified>(100);
		Sql_util_user sqluser=Sql_util_user.InstanceCreation();
		//(Statement st = connection.createStatement();
		ResultSet rs = sqluser.connect_user("SELECT * FROM classified.classified_info"); 
			while (rs.next()) {
				Integer classifiedId = rs.getInt("classified_id");
				// System.out.println(classifiedId);
				String title = rs.getString("title");
				Double price = rs.getDouble("price");
				String description = rs.getString("description");
				CategoryType category = CategoryType.valueOf(rs.getString("category"));
				Date createdAt = rs.getDate("created_at");
				String createdBy = rs.getString("created_by");
				ClassifiedStatus status = ClassifiedStatus.valueOf(rs.getString("status"));
				classifiedList.add(new Classified(classifiedId, title, price, description, category, createdAt,
						createdBy, status));
			}

		return classifiedList;
	}

	
	// ***************************print only for current USER****************************//

	@Override
	public List<Classified> getClassifiedListByUserName(String userName) {
		List<Classified> classifiedList2 = new ArrayList<>();
		Connection connection = MysqlCon.connect().connection;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM classified_info WHERE created_by=?");
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer classifiedId = rs.getInt("classified_id");
				// System.out.println(classifiedId);
				String title = rs.getString("title");
				Double price = rs.getDouble("price");
				String description = rs.getString("description");
				CategoryType category = CategoryType.valueOf(rs.getString("category"));
				Date createdAt = rs.getDate("created_at");
				String createdBy = rs.getString("created_by");
				ClassifiedStatus status = ClassifiedStatus.valueOf(rs.getString("status"));
				classifiedList2.add(new Classified(classifiedId, title, price, description, category, createdAt,
						createdBy, status));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return classifiedList2;
}
	// ************************ create *****************************//

	private static final String INSERT_SQL = "INSERT INTO classified_info(title, price, description, category, created_at, created_by, status) values(?, ?, ?, ?, ?, ?, ?) ";

	@Override
	public Classified createClassified(Classified classified) throws SQLException{
		Connection sqluserconnection=MysqlCon.connect().connection;
		if (classified != null) {
			PreparedStatement ps = sqluserconnection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				// ps.setInt(1, classified.getClassifiedId());
				ps.setString(1, classified.getTitle());
				ps.setDouble(2, classified.getPrice());
				ps.setString(3, classified.getDescription());
				ps.setString(4, classified.getCategory().toString());
				ps.setDate(5, new java.sql.Date(classified.getCreatedAt().getTime()));
				ps.setString(6, classified.getCreatedBy());
				ps.setString(7, classified.getStatus().toString());
				int numRowsAffected = ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					classified.setId(rs.getInt(1));
				}
		}
		return classified;
	}

	// ***************************update******************************//
	private static String sqlUpdate = ("update classified_info set title=?,price=?,description=?,category=? where classified_id=?");

	@Override
	public Classified updateClassified(Classified classified) {
		Connection connection = MysqlCon.connect().connection;
		
		if (classified != null) {
			try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdate)) {
				// data update
/*This part of your 
* function is not 
 * running */	System.out.println("Please edit your classifieds or hit enter if you want to skip the changes *******THIS FUNCTION IS NOT RUNNING*****");
				pstmt.setString(1, classified.getTitle());
				pstmt.setDouble(2, classified.getPrice());
				pstmt.setString(3, classified.getDescription());
				pstmt.setString(4, classified.getCategory().toString());
				pstmt.setInt(5, classified.getClassifiedId());
				System.out.println(
						"Your classified has been updated successfully, but you can see only if this is approved by Admin");
				int rowAffected = pstmt.executeUpdate();
				System.out.println(String.format("Row affected %d", rowAffected));
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return classified;
	}
}
