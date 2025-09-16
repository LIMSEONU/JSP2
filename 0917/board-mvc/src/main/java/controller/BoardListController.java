package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.BoardDAO;
import dto.BoardDTO;

@WebServlet("/list.do")
public class BoardListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int numOfRecords = 10; // 한 화면에 출력되는 레코드 수
    private static final int numOfPages = 5;    // 한 화면에 보여지는 페이지 번호 개수
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        BoardDAO dao = new BoardDAO();
        
        // 전체 레코드의 개수 (count)
        int count = dao.getCount();
        
        // 현재 페이지 번호
        int currentPage = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null && !pageStr.isEmpty()) {
            currentPage = Integer.parseInt(pageStr);
        }
        
        // 시작 페이지 번호 계산 (startNum)
        int startNum = ((currentPage - 1) / numOfPages) * numOfPages + 1;
        
        // 마지막 페이지 번호 계산 (lastNum)
        int totalPages = (count + numOfRecords - 1) / numOfRecords;
        int lastNum = Math.min(startNum + numOfPages - 1, totalPages);
        
        // 시작 레코드 인덱스 계산
        int startIndex = (currentPage - 1) * numOfRecords;
        
        // 게시물 목록 조회
        List<BoardDTO> boardList = dao.getBoardList(startIndex, numOfRecords);
        
        // JSP에 필요한 데이터 설정
        request.setAttribute("boardList", boardList);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("count", count);
        request.setAttribute("numOfRecords", numOfRecords);
        request.setAttribute("numOfPages", numOfPages);
        request.setAttribute("startNum", startNum);
        request.setAttribute("lastNum", lastNum);
        request.setAttribute("totalPages", totalPages);
        
        // Forward to JSP
        request.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(request, response);
    }
}