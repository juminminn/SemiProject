<%@page import="util.AdminComplaintPaging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	AdminComplaintPaging complaintPaging = (AdminComplaintPaging) request.getAttribute("complaintPaging"); %>

<div class="text-center">
	<ul class="pagination .pagination-lg">
	
		<!-- 이전 페이징 리스트로 가기 -->
		<%	if(complaintPaging.getStartPage() > complaintPaging.getPageCount()) { %>
		<li>
			<a href="/admin/complaint/list?curPage=<%=complaintPaging.getStartPage() - complaintPaging.getPageCount() %>">&laquo;</a>
		</li>
		<%	} else { %>
		<li class="disabled"><a>&laquo;</a>
		</li>
		<%	} %>
		
		<!-- 이전 페이지로 가기 -->
		<%	if(complaintPaging.getCurPage() != 1) { %>
		<li>
			<a href="/admin/complaint/list?curPage=<%=complaintPaging.getCurPage() - 1 %>">&lt;</a>
		</li>
		<%	} %>
						
		<!-- 페이징 리스트 -->
		<%	for(int i=complaintPaging.getStartPage(); i<=complaintPaging.getEndPage(); i++) { %>
		<%		if( i == complaintPaging.getCurPage() ) { %>
		<li class="active">
			<a href="/admin/complaint/list?curPage=<%=i %>"><%=i %></a>
		</li>
		<%		} else { %>
		<li>
			<a href="/admin/complaint/list?curPage=<%=i %>"><%=i %></a>
		</li>
		<%		} %>
		<%	} %>
		
		<!-- 다음 페이지로 가기 -->
		<%	if(complaintPaging.getCurPage() != complaintPaging.getTotalPage()) { %>
		<li>
			<a href="/admin/complaint/list?curPage=<%=complaintPaging.getCurPage() + 1 %>">&gt;</a>
		</li>
		<%	} %>

		<!-- 다음 페이징 리스트로 가기 -->
		<%	if(complaintPaging.getEndPage() != complaintPaging.getTotalPage()) { %>
		<li>
			<a href="/admin/complaint/list?curPage=<%=complaintPaging.getStartPage() + complaintPaging.getPageCount() %>">&raquo;</a>
		</li>
		<%	} else { %>
		<li class="disabled"><a>&raquo;</a></li>
		<%	} %>

	</ul>
</div>