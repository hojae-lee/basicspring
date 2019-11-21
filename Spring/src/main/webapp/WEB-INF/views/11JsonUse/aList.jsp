<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
function paging(pNum){
   
   $.ajax({
      url : "./aList.do",//요청경로
      type : "get",//전송방식
      contentType : "text/html;charset:utf-8",
      data : {nowPage : pNum },//파라미터
      dataType: "html",//응답데이터형식
      success : function(d){//성공시 콜백메소드
         
         $('#boardHTML').html('');
         $('#boardHTML').append('<div style="text-align:center;padding-top:50px;">')
         .append('<img src="../images/loading02.gif">')
         .append('</div>');
         $('#boardHTML').html(d);
      },
      error : function(e){
         alert("실패"+e)
      }
   });
}

function deleteRow(g_idx){
   if(confirm('삭제할까요?')==true){
      $.ajax({
         url : "./deleteAction.do",//요청경로
         type : "get",
         contentType : "text/html;charset:utf-8",
         data : {idx : g_idx},
         dataType : "json",
         success : function(d){
            if(d.statusCode==0){
               //삭제실패시
               alert("게시물 삭제 실패");
            }
            else if(d.statusCode==1){
               //로그인 전 요청
               alert('로그인 후 삭제할 수 있습니다');
               location.href="./login.do";
            }
            else if(d.statusCode==2){
               //삭제성공
               alert('게시물이 삭제되었습니다');
               //hide() 메소드를 통해 해당 엘리먼트를 숨김처리한다. 단 1초동안
               //진행되므로 애니메이션 효과가 있다.
               $('#guest_'+g_idx).hide(1000);
               setInterval("reflash()",1000);
            }
         },
         error : function(e){
            alert("요청실패:" +e.status+":"+e.statusText);
         }
      });
   }
}

function feflash(){
   location.reload();
}

</script>
    
<div class="text-right">
   <c:choose>
      <c:when test="${not empty sessionScope.siteUserInfo }">
         <button class="btn btn-danger" onclick="location.href='logout.do';">
            로그아웃
         </button>
      </c:when>
      <c:otherwise>
         <button class="btn btn-info" onclick="location.href='login.do';">
            로그인
         </button>
      </c:otherwise>
   </c:choose>   
      &nbsp;&nbsp;&nbsp;
      <button class="btn btn-success" onclick="">
         방명록쓰기 기능없음
      </button>
   </div>
      
   <!-- 방명록 반복 부분 s -->
   <c:forEach items="${lists }" var="row">
   <!--  
      각 반복되는 게시물 항목에 "guest_일련번호와 같이 id를 부여한다.
      DB에서 레코드를 삭제한 후 해당 항목은 hide()메소드를 통해 리스트에서
      숨김처리된다.
   -->
      <div class="border mt-2 mb-2" id="guest_${row.idx }">
         <div class="media">
            <div class="media-left mr-3">
               <img src="../images/img_avatar3.png" class="media-object" style="width:60px">
            </div>
            <div class="media-body">
               <h4 class="media-heading">작성자:${row.name }(${row.id })</h4>
               <p>${row.contents }</p>
            </div>     
            <!--  수정,삭제버튼 -->
            <div class="media-right">
            <!-- 작성자 본인에게만 수정/삭제 버튼 보임 -->
            <c:if test="${sessionScope.siteUserInfo.id eq row.id}">
               <button class="btn btn-danger" onclick="javascript:deleteRow(${row.idx});">삭제</button>
            </c:if>
         </div>
         </div>
      </div>
   </c:forEach>
   
   <!-- 방명록 반복 부분 e -->
   <ul class="pagination justify-content-center">
      ${pagingImg }
   </ul>
</div>