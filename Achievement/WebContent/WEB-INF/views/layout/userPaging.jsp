<%@page import="util.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	Paging paging = (Paging) request.getAttribute("paging"); %>

<div class="text-center">
	<ul class="pagination pagination-sm">
	
		<!-- 첫 페이지로 이동 -->
		<%	if(paging.getCurPage() != 1) { //첫 페이지가 아닐 때 보임 %>
		<li><a href="?grade=<%=request.getParameter("grade") %>&field=<%=request.getParameter("field") %>&search=<%=request.getParameter("search") %>">&larr;</a></li>
		<%	} %>
		
		<!-- 이전 페이징 리스트로 가기 -->
		<%	if(paging.getStartPage() > paging.getPageCount()) { %>
		<li><a href="?curPage=<%=paging.getStartPage() - paging.getPageCount() %>&grade=<%=request.getParameter("grade") %>&field=<%=request.getParameter("field") %>&search=<%=request.getParameter("search") %>">&laquo;</a></li>
		<%	} else { %>
		<li class="disabled"><a>&laquo;</a></li>
		<%	} %>
		
		<!-- 이전 페이지로 가기 -->
		<%	if(paging.getCurPage() != 1) { %>
		<li><a href="?curPage=<%=paging.getCurPage() - 1 %>&grade=<%=request.getParameter("grade") %>&field=<%=request.getParameter("field") %>&search=<%=request.getParameter("search") %>">&lt;</a></li>
		<%	} %>
		
		
		
		
		<!-- 페이징 리스트 -->
		<%	for(int i=paging.getStartPage(); i<=paging.getEndPage(); i++) { %>
		<%		if( i == paging.getCurPage() ) { %>
		<li class="active"><a href="?curPage=<%=i %>&grade=<%=request.getParameter("grade") %>&field=<%=request.getParameter("field") %>&search=<%=request.getParameter("search") %>"><%=i %></a></li>
		<%		} else { %>
		<li><a href="?curPage=<%=i %>&grade=<%=request.getParameter("grade") %>&field=<%=request.getParameter("field") %>&search=<%=request.getParameter("search") %>"><%=i %></a></li>
		<%		} %>
		<%	} %>
		



		<!-- 다음 페이지로 가기 -->
		<%	if(paging.getCurPage() != paging.getTotalPage()) { %>
		<li><a href="?curPage=<%=paging.getCurPage() + 1 %>&grade=<%=request.getParameter("grade") %>&field=<%=request.getParameter("field") %>&search=<%=request.getParameter("search") %>">&gt;</a></li>
		<%	} %>




		<!-- 다음 페이징 리스트로 가기 -->
		<%	if(paging.getEndPage() != paging.getTotalPage()) { %>
		<li><a href="?curPage=<%=paging.getStartPage() + paging.getPageCount() %>&grade=<%=request.getParameter("grade") %>&field=<%=request.getParameter("field") %>&search=<%=request.getParameter("search") %>">&raquo;</a></li>
		<%	} else { %>
		<li class="disabled"><a>&raquo;</a></li>
		<%	} %>




		<!-- 마지막 페이지로 가기 -->
		<%	if(paging.getCurPage() != paging.getTotalPage()) { %>
		<li><a href="?curPage=<%=paging.getTotalPage() %>&grade=<%=request.getParameter("grade") %>&field=<%=request.getParameter("field") %>&search=<%=request.getParameter("search") %>">&rarr;</a></li>
		<%	} %>
		
	</ul>
</div>