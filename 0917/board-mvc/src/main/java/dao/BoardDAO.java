package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import dto.BoardDTO;

public class BoardDAO {
    private DataSource dataSource;
    
    public BoardDAO() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/swlim");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Get total number of records (count)
    public int getCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM board";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    // Get paginated list of boards with startIndex and numOfRecords
    public List<BoardDTO> getBoardList(int startIndex, int numOfRecords) {
        List<BoardDTO> boardList = new ArrayList<>();
        String sql = "SELECT * FROM board ORDER BY bcode DESC LIMIT ?, ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, startIndex);
            pstmt.setInt(2, numOfRecords);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BoardDTO board = new BoardDTO();
                    board.setBcode(rs.getInt("bcode"));
                    board.setSubject(rs.getString("subject"));
                    board.setContent(rs.getString("content"));
                    board.setWriter(rs.getString("writer"));
                    board.setRegdate(rs.getDate("regdate"));
                    boardList.add(board);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boardList;
    }
}