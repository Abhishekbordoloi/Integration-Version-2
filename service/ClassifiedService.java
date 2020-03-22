package service;

import java.sql.SQLException;
import java.util.List;

import util.Classified;

public interface ClassifiedService {
	Classified updateClassified(Classified classified);  //update
	List<Classified> getClassifiedList() throws SQLException;      //print
	List<Classified> getClassifiedListByUserName(String userName);     //print for user
	Classified createClassified (Classified classified) throws SQLException;  //create

}

