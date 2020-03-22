package repository;

import java.sql.SQLException;
import java.util.List;

import util.Classified;

public interface ClassifiedRepository {

	Classified createClassified(Classified classified) throws SQLException; //create
	
	Classified updateClassified(Classified classified); //update

	List<Classified> getClassifiedList() throws SQLException; //print

	List<Classified> getClassifiedListByUserName(String userName); //print for specific user
}
