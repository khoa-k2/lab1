/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASMJAVA2.Entity;

import Lab5.LoaiSanPham;
import Lab5.SanPham;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class SQLASM2 {

    String INSERT_SQL = "INSERT INTO STUDENTS ( MaSV, HoTen, Email, SoDT, GioiTinh, DiaChi) VALUES (?, ?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE STUDENTS set  MaSV = ?,Hoten =?, Email = ?, SoDT = ?, Gioitinh = ?, DiaChi = ?  WHERE MaSV = ?";
    String DELETE_SQL = "DELETE FROM STUDENTS WHERE MaSV = ?";
    String SELECT_ALL_SQL = "SELECT * FROM STUDENTS";

    String SELECT_SQL_1 = "SELECT g.Masv, s.Hoten AS HoTen, g.Tienganh, g.Tinhoc, g.GDTC\n"
            + "FROM STUDENTs s \n"
            + "JOIN GRADE g ON s.masv = g.masv\n"
            + "WHERE g.masv IS NOT NULL\n"
            + "ORDER BY (g.tienganh + g.tinhoc + g.gdtc) / 3.0 DESC \n"
            + "LIMIT 3";
    String INSERT_SQL_1 = "INSERT INTO GRADE ( MaSV, Hoten, Tienganh, Tinhoc, GDTC) VALUES (?, ?, ?, ?, ? )";
    String UPDATE_SQL_1 = "UPDATE GRADE set Tienganh =?, Tinhoc = ?, GDTC = ? WHERE MaSV = ?";
    String DELETE_SQL_1 = "DELETE FROM GRADE WHERE MaSV = ?";
    String SELECT_ALL_SQL_1 = "SELECT * FROM GRADE";

    static final String userName = "root";
    static final String password = "123456";
    static final String url = "jdbc:mysql://localhost:3306/studentmanagement?zeroDateTimeBehavior=CONVERT_TO_NULL";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getconnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

    public static void main(String[] args) {
        try (Connection conn = getconnection()) {
            if (conn != null) {
                System.out.println("Kết nối thành công!");
            } else {
                System.out.println("Kết nối thất bại!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối: " + e.getMessage());
        }
    }

    public int insert(Student sp) {
        int rs = 0;
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            PreparedStatement st = conn.prepareStatement(INSERT_SQL);
            st.setString(1, sp.getMaSV());
            st.setString(2, sp.getHoTen());
            st.setString(3, sp.getEmail());
            st.setString(4, sp.getSoDT());
            st.setBoolean(5, sp.getGioiTinh());
            st.setString(6, sp.getDiaChi());

            rs = st.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("loi them");
        }
        return rs;
    }

    public int update(Student sp) {
        int rs = 0;
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            PreparedStatement st = conn.prepareStatement(UPDATE_SQL);
            st.setString(1, sp.getMaSV());
            st.setString(2, sp.getHoTen());
            st.setString(3, sp.getEmail());
            st.setString(4, sp.getSoDT());
            st.setBoolean(5, sp.getGioiTinh());
            st.setString(6, sp.getDiaChi());
            st.setString(7, sp.getMaSV());
            rs = st.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(" loi update");
            System.out.println("Lỗi cập nhật sinh viên: " + e.getMessage());
            e.printStackTrace();

        }
        return rs;
    }

    public int delete(String MaSV) {
        int rs = 0;
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            PreparedStatement st = conn.prepareStatement(DELETE_SQL);
            st.setString(1, MaSV);
            rs = st.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("loi xoa");
        }
        return rs;
    }


    public List<Student> getAllStudent() {
        List<Student> list = new ArrayList<>();
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
         
            conn = DriverManager.getConnection(url, userName, password);

            
            String sql = SELECT_ALL_SQL;
            stm = conn.createStatement();

            rs = stm.executeQuery(sql);

            
            while (rs.next()) {
                Student sp = new Student();
                sp.setMaSV(rs.getString("MaSV"));
                sp.setHoTen(rs.getString("Hoten"));
                sp.setEmail(rs.getString("Email"));
                sp.setSoDT(rs.getString("SoDT"));
                sp.setGioiTinh(rs.getBoolean("GioiTinh"));
                sp.setDiaChi(rs.getString("DiaChi"));
                sp.setHinh(rs.getString("Hinh"));
                list.add(sp);
            }
        } catch (SQLException e) {
            // In ra chi tiết lỗi nếu có
            System.out.println("Lỗi khi truy vấn dữ liệu sinh viên: " + e.getMessage());
        } finally {
            
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }

        return list;
    }

    public List<Grade> getAllGrade() {
        List<Grade> listGrade = new ArrayList<>();
        List<Student> list = new ArrayList<>();
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
           
            conn = DriverManager.getConnection(url, userName, password);

            String sql = SELECT_SQL_1;
            stm = conn.createStatement();

          
            rs = stm.executeQuery(sql);

            
            while (rs.next()) {
                Student st = new Student();
                Grade sp = new Grade();
                sp.setMaSV(rs.getString("MaSV"));
                sp.setHoTen(rs.getString("Hoten"));
                sp.setTiengAnh(rs.getDouble("Tienganh"));
                sp.setTinHoc(rs.getDouble("Tinhoc"));
                sp.setGdtc(rs.getDouble("GDTC"));

                listGrade.add(sp);
                list.add(st);
            }
        } catch (SQLException e) {
            
            System.out.println("Lỗi khi truy vấn dữ liệu sinh viên: " + e.getMessage());
        } finally {
            
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }

        return listGrade;
    }

    public int update(Grade sp) {
        int rs = 0;
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            PreparedStatement st = conn.prepareStatement(UPDATE_SQL_1);

            st.setDouble(1, sp.getTiengAnh());
            st.setDouble(2, sp.getTinHoc());
            st.setDouble(3, sp.getGdtc());
            st.setString(4, sp.getMaSV());
            rs = st.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(" loi update");
            System.out.println("Lỗi cập nhật sinh viên: " + e.getMessage());
            e.printStackTrace();

        }
        return rs;
    }
    

    public Grade findById(String MaSV) {
    
    String sql = "SELECT g.MASV, s.hoten, g.tienganh, g.tinhoc, g.gdtc "
                + "FROM students s "
                + "LEFT JOIN grade g ON s.masv = g.masv "
                + "WHERE s.masv = ?";
    try (
        Connection conn = DriverManager.getConnection(url, userName, password);
            PreparedStatement ps = conn.prepareStatement(sql);
    ) {
        ps.setString(1, MaSV);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            Grade g = new Grade();
            g.setMaSV(rs.getString("maSV"));
            g.setHoTen(rs.getString("hoTen"));
            g.setTiengAnh(rs.getDouble("tiengAnh"));
            g.setTinHoc(rs.getDouble("tinHoc"));
            g.setGdtc(rs.getDouble("gdtc"));
            return g;
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return null;
}


}
