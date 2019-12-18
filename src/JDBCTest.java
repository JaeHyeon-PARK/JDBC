import java.sql.*;
import java.util.Scanner;

public class JDBCTest {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException : " + e.getMessage());
		}
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@dbserver.yu.ac.kr:1521:XE", "student139", "lunasnow");
			Statement stmt = conn.createStatement();
			
			while(true) {
				System.out.println("Menu(1: �л� �߰�, 2: �л� ����, 3: �л� ����, 4: �л� �˻�, 5: �а� ���, 6: ����)");
				Scanner sc = new Scanner(System.in);
				int sel = sc.nextInt();
				
				if(sel == 1) {
					PreparedStatement query = conn.prepareStatement("insert into student values(?, ?, ?, ?, ?, ?, ?, ?)");
					System.out.print("�й�(�⺻Ű)? "); int sid = sc.nextInt(); sc.nextLine();
					System.out.print("�̸�(0 = NULL)? "); String sname = sc.nextLine();
					System.out.print("�а���ȣ(0 = NULL)? "); int deptno = sc.nextInt(); sc.nextLine();
					System.out.print("��米��(0 = NULL)? "); int advisor = sc.nextInt(); sc.nextLine();
					System.out.print("����(0 = NULL)? "); String gen = sc.nextLine();
					System.out.print("�ּ�(0 = NULL)? "); String addr = sc.nextLine();
					System.out.print("����(YYYY-MM-DD �������� �Է�, 0 = NULL)? "); String birthdate = sc.nextLine();
					System.out.print("����(�Ҽ��� ��°�ڸ����� �Է�, 0 = NULL)? "); double grade = sc.nextDouble(); sc.nextLine();
					
					query.setInt(1, sid);
					
					if(sname.equals("0")) query.setNull(2, Types.VARCHAR);
					else query.setString(2, sname);
					
					if(deptno == 0) query.setNull(3, Types.INTEGER);
					else query.setInt(3, deptno);
					
					if(advisor == 0) query.setNull(4, Types.INTEGER);
					else query.setInt(4, advisor);
					
					if(gen.equals("0")) query.setNull(5, Types.VARCHAR);
					else query.setString(5, gen);
					
					if(addr.equals("0")) query.setNull(6, Types.VARCHAR);
					else query.setString(6, addr);
					
					if(birthdate.equals("0")) query.setNull(7, Types.DATE);
					else query.setDate(7, Date.valueOf(birthdate));
					
					if(grade == 0) query.setNull(8, Types.DOUBLE);
					else query.setDouble(8, grade);
					
					int cnt = query.executeUpdate();
					System.out.println(cnt + "���� ���ڵ尡 �ԷµǾ����ϴ�.");
				}
				else if(sel == 2) {
					PreparedStatement query = conn.prepareStatement("delete from student where sid = ?");
					System.out.print("������ �й�? "); int sid = sc.nextInt(); sc.nextLine();
					query.setInt(1, sid);
					
					int cnt = query.executeUpdate();
					System.out.println(cnt + "���� ���ڵ尡 �����Ǿ����ϴ�.");
				}
				else if(sel == 3) {
					PreparedStatement query1 = conn.prepareStatement("select * from student where sid = ?");
					System.out.print("������ �й�? "); int sid = sc.nextInt(); sc.nextLine();
					query1.setInt(1, sid);
					ResultSet rs = query1.executeQuery();
					
					while(rs.next()) {
						System.out.println("SID: " + rs.getInt(1) + ", SNAME: " + rs.getString(2) + ", DEPTNO: " + rs.getInt(3) + ", ADVISOR: " + rs.getInt(4)
											 + ", GEN: " + rs.getString(5) + ", ADDR: " + rs.getString(6) + ", BIRTHDATE: " + rs.getDate(7) + ", GRADE: " + rs.getDouble(8));
					}
					
					PreparedStatement query2 = conn.prepareStatement("update student set sname = ?, deptno = ?, advisor = ?,gen = ?, addr = ?, birthdate = ?, grade = ?");
					System.out.print("�̸�(0 = NULL)? "); String sname = sc.nextLine();
					System.out.print("�а���ȣ(0 = NULL)? "); int deptno = sc.nextInt(); sc.nextLine();
					System.out.print("��米��(0 = NULL)? "); int advisor = sc.nextInt(); sc.nextLine();
					System.out.print("����(0 = NULL)? "); String gen = sc.nextLine();
					System.out.print("�ּ�(0 = NULL)? "); String addr = sc.nextLine();
					System.out.print("����(YYYY-MM-DD �������� �Է�, 0 = NULL)? "); String birthdate = sc.nextLine();
					System.out.print("����(�Ҽ��� ��°�ڸ����� �Է�, 0 = NULL)? "); double grade = sc.nextDouble(); sc.nextLine();
					
					if(sname.equals("0")) query2.setNull(2, Types.VARCHAR);
					else query2.setString(2, sname);
					
					if(deptno == 0) query2.setNull(3, Types.INTEGER);
					else query2.setInt(3, deptno);
					
					if(advisor == 0) query2.setNull(4, Types.INTEGER);
					else query2.setInt(4, advisor);
					
					if(gen.equals("0")) query2.setNull(5, Types.VARCHAR);
					else query2.setString(5, gen);
					
					if(addr.equals("0")) query2.setNull(6, Types.VARCHAR);
					else query2.setString(6, addr);
					
					if(birthdate.equals("0")) query2.setNull(7, Types.DATE);
					else query2.setDate(7, Date.valueOf(birthdate));
					
					if(grade == 0) query2.setNull(8, Types.DOUBLE);
					else query2.setDouble(8, grade);
					
					int cnt = query2.executeUpdate();
					System.out.println(cnt + "���� ���ڵ尡 �����Ǿ����ϴ�.");
				}
				else if(sel == 4) {
					PreparedStatement query = conn.prepareStatement("select * from student where sid = ?");
					System.out.print("�˻��� �й�? "); int sid = sc.nextInt(); sc.nextLine();
					query.setInt(1, sid);
					ResultSet rs = query.executeQuery();
					
					while(rs.next()) {
						System.out.println("SID: " + rs.getInt(1) + ", SNAME: " + rs.getString(2) + ", DEPTNO: " + rs.getInt(3) + ", ADVISOR: " + rs.getInt(4)
											 + ", GEN: " + rs.getString(5) + ", ADDR: " + rs.getString(6) + ", BIRTHDATE: " + rs.getDate(7) + ", GRADE: " + rs.getDouble(8));
					}
				}
				else if(sel == 5) {
					PreparedStatement query = conn.prepareStatement("select s.sid, s.sname, d.dname, p.pname from student s, department d, professor p where s.deptno = d.deptno and s.advisor = p.pid where s.deptno = ?");
					System.out.print("�˻��� �а���ȣ? "); int deptno = sc.nextInt(); sc.nextLine();
					query.setInt(1, deptno);
					ResultSet rs = query.executeQuery();
					
					while(rs.next()) {
						System.out.println("SID: " + rs.getInt(1) + ", SNAME: " + rs.getString(2) + ", DNAME: " + rs.getString(3) + ", PNAME: " + rs.getString(4));
					}
				}
				else break;
			}
		} catch (SQLException sqle) {
			System.err.println("SQLException : " + sqle);
		}
	}
}
