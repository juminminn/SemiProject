<%@page import="util.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	Paging paging = (Paging) request.getAttribute("paging"); %>
<% String grade = request.getParameter("grade"); %>
<% String field = request.getParameter("field"); %>
<% String search = request.getParameter("search"); %>
<div class="text-center">
	<ul class="pagination pagination-sm">
		<!-- 첫 페이지로 이동 -->
		<%	if(paging.getCurPage() != 1) { //첫 페이지가 아닐 때 보임 %>
		<li><a href="/admin/user/list?grade=<%=grade==null ? "" : grade %>&field=<%=field==null ? "" : field%>&search=<%=search==null ? "" : search %>">&larr;</a></li>
		<%	} %>
		
		<!-- 이전 페이징 리스트로 가기 -->
		<%	if(paging.getStartPage() > paging.getPageCount()) { %>
		<li><a href="/admin/user/list?curPage=<%=paging.getStartPage() - paging.getPageCount() %>&grade=<%=grade==null ? "" : grade %>&field=<%=field==null ? "" : field%>&search=<%=search==null ? "" : search %>">&laquo;</a></li>
		<%	} else { %>
		<li><button class="disabled">&laquo;</button></li>
		<%	} %>
		
		<!-- 이전 페이지로 가기 -->
		<%	if(paging.getCurPage() != 1) { %>
		<li><a href="/admin/user/list?curPage=<%=paging.getCurPage() - 1 %>&grade=<%=grade==null ? "" : grade %>&field=<%=field==null ? "" : field %>&search=<%=search==null ? "" : search %>">&lt;</a></li>
		<%	} %>
		
		
		
		
		<!-- 페이징 리스트 -->
		<%	for(int i=paging.getStartPage(); i<=paging.getEndPage(); i++) { %>
		<%		if( i == paging.getCurPage() ) { %>
		<li><a href="/admin/user/list?curPage=<%=i %>&grade=<%=grade==null ? "" : grade%>&field=<%=field==null ? "" : field %>&search=<%=search==null ? "" : search%>"><%=i %></a></li>
		<%		} else { %>
		<li><a href="/admin/user/list?curPage=<%=i %>&grade=<%= grade==null ? "" : grade %>&field=<%= field==null ? "" : field %>&search=<%=search==null ? "" : search %>"><%=i %></a></li>
		<%		} %>
		<%	} %>
		



		<!-- 다음 페이지로 가기 -->
		<%	if(paging.getCurPage() != paging.getTotalPage()) { %>
		<li><a href="/admin/user/list?curPage=<%=paging.getCurPage() + 1 %>&grade=<%=grade==null ? "" : grade %>&field=<%=field==null ? "" : field %>&search=<%=search==null ? "" : search %>">
		&gt;</a></li>
		<%	} %>




		<!-- 다음 페이징 리스트로 가기 -->
		<%	if(paging.getEndPage() != paging.getTotalPage()) { %>
		<li><a href="/admin/user/list?curPage=<%=paging.getStartPage() + paging.getPageCount() %>&grade=<%=grade==null ? "" : grade %>&field=<%=field==null ? "" : field %>&search=<%=search==null ? "" : search %>">
		&raquo;</a></li>	
		<%	} else { %>
		<li class="disabled"><a>&raquo;</a></li>
		<%	} %>




		<!-- 마지막 페이지로 가기 -->
		<%	if(paging.getCurPage() != paging.getTotalPage()) { %>
		<li><a href="/admin/user/list?curPage=<%=paging.getTotalPage() %>&grade=<%=grade==null ? "" : grade %>&field=<%=field==null ? "" : field %>&search=<%=search==null ? "" : search %>">
		&rarr;</a></li>		
		<%	} %>
	</ul>
</div>