package dao;

import common.DBConnection;
import dto.HistoryDto;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryDao {
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public void insert(HistoryDto dto) {
        con = null;
        ps = null;
        rs = null;

        try {
            con = DBConnection.getConnect();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = sdf.format(new Date());
            String sql = "insert into history_wifi"
                    + " (lat, lnt, searchDate)"
                    + " values (?, ?, ?)";

            ps = con.prepareStatement(sql);

            ps.setString(1, dto.getLat());
            ps.setString(2, dto.getLnt());
            ps.setString(3, dateTime);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, con);
        }
    }

    public int count() {
        con = null;
        ps = null;
        rs = null;

        int cnt = 0;

        try {
            con = DBConnection.getConnect();

            String sql = "select count(*) from history_wifi";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                cnt = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, con);
        }

        return cnt;
    }

    public void delete(int id) {
        con = null;
        ps = null;
        rs = null;

        try {
            con = DBConnection.getConnect();

            String sql = "delete from history_wifi where id = ?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, con);
        }
    }

    public List<HistoryDto> historyList() {
        con = null;
        ps = null;
        rs = null;

        List<HistoryDto> dtos = new ArrayList<>();

        try {
            con = DBConnection.getConnect();
            String sql = "select * from history_wifi order by id desc";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                HistoryDto dto = HistoryDto.builder()
                        .id(rs.getInt("id"))
                        .lat(rs.getString("lat"))
                        .lnt(rs.getString("lnt"))
                        .searchDate(rs.getString("searchDate"))
                        .build();

                dtos.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, con);
        }

        return dtos;
    }
}
