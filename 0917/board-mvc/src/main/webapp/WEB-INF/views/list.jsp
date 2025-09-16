<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 목록</title>
    <style>
    	h1 {
    		text-align: center;
    	}
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #f5f5f5;
        }
        .pagination {
            text-align: center;
            margin: 20px 0;
        }
        .pagination a {
            display: inline-block;
            padding: 5px 10px;
            margin: 0 3px;
            border: 1px solid #ddd;
            text-decoration: none;
            color: #333;
        }
        .pagination a.active {
            background-color: #007bff;
            color: white;
            border-color: #007bff;
        }
        .buttons {
            margin-bottom: 20px;
        }
        .buttons a {
            text-decoration: none;
            color: #333;
            margin-right: 15px;
            padding: 5px 15px;
            border: 1px solid #666;
            background-color: #f5f5f5;
            border-radius: 3px;
        }
        .buttons a:hover {
            background-color: #e0e0e0;
        }
        .page-info {
            text-align: right;
            margin-bottom: 10px;
            color: #666;
        }
        tbody tr:hover {
            background-color: #f5f5f5;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>게시판 목록</h1>
    <hr>
    
     <div class="page-info">
        전체 게시물: ${count}개
    </div>

    <table>
        <thead>
            <tr>
                <th>Num</th>
                <th>Subject</th>
                <th>Writer</th>
                <th>RegDate</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${boardList}" var="board">
                <tr>
                    <td>${board.bcode}</td>
                    <td><a href="view.do?bcode=${board.bcode}">${board.subject}</a></td>
                    <td>${board.writer}</td>
                    <td>${board.regdate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <div class="buttons">
        		<a href="list.do">홈으로</a>
        		<a href="write.do">게시글 등록</a>
    		</div>

    <div class="pagination">
        <a href="#" onclick="return checkPrev();">Prev</a>
        
        <c:forEach begin="${startNum}" end="${lastNum}" var="i">
            <c:choose>
                <c:when test="${currentPage == i}">
                    <a href="#" class="active">${i}</a>
                </c:when>
                <c:otherwise>
                    <a href="list.do?page=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        
        <a href="#" onclick="return checkNext();">Next</a>
    </div>
    
    <script>
        var startNum = ${startNum};
        var lastNum = ${lastNum};
        var numOfPages = ${numOfPages};
        var totalPages = ${totalPages};
        
        function checkPrev() {
            if (startNum <= numOfPages) {
                alert("이전 페이지가 없습니다!");
                return false;
            }
            window.location.href = "list.do?page=" + (startNum - 1);
            return false;
        }
        
        function checkNext() {
            if (lastNum >= totalPages) {
                alert("다음 페이지가 없습니다!");
                return false;
            }
            window.location.href = "list.do?page=" + (lastNum + 1);
            return false;
        }
    </script>
</body>
</html>