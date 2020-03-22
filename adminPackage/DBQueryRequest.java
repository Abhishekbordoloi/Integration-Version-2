//package adminPackage;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.LinkedList;
//import java.util.List;
//
//public class DBQueryRequest {
//        private static Connection con;
//        DBQueryRequest() {
//            if(con == null) {
//                DBQueries.MysqlCon obj1 = new DBQueries.MysqlCon();
//                con = obj1.connect();
//            }
//        }
//        public void insert() {
//
//        }
//        public void update() {
//
//        }
//        public List<Requests> find() {
//            List<Requests> list = new LinkedList<>();
//            try {
//                    String sql = "select * from Request_Table";
//                Statement statement = con.createStatement();
//                ResultSet result = statement.executeQuery(sql);
//                while (result.next()){
//                    Requests request = new Requests();
//                    request.setRequest_id(result.getInt(1));
//                    request.setBuyer(result.getString(2));
//                    request.setSeller(result.getString(3));
//                    request.setClassified_id(result.getInt(4));
//                    request.setPriceOffered(result.getInt(5));
//                    request.setStatus(result.getString(6));
//
//                    list.add(request);
////                System.out.print(result.getStri.lng(1));
////                System.out.print(result.getString(2));
////                System.out.print(result.getInt(3));
////                System.out.println();
//                }
//                //System.out.println("Result="+result.getRow());
//                return list;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return list;
//        }
//        public void delete() {
//        }
//    }
//
//    class test1{
//        public static void main(String[] args) {
//            DBQueryRequest d = new DBQueryRequest();
//            List<Requests> list = d.find();
//            System.out.println(list.toString());
//        }
//    }
//}
