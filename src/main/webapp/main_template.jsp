<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>YI Bank</title>
</head>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function (){
        $.ajax({
            url : "/<%=request.getAttribute("page")%>",
            method : "GET",
            success : function (data){
                $("#content").html(data);
            },
            error : function (){
                alert("컨텐츠 로드에 실패");
            }
        }),
            // 추가한거
            $("a").click(function(event) {
                var pageUrl = $(this).attr("href"); // 클릭한 링크의 href 값 가져오기
                if ( pageUrl == "/main/main.do" )
                    return;
                event.preventDefault(); // 기본 이벤트(링크 이동) 방지
                if ( pageUrl == "#" )
                    return;
                $.ajax({
                    url: pageUrl, // 서버에 요청할 페이지
                    type: "GET", // HTTP 요청 방식
                    success: function(response) {
                        $("#content").html(response); // 성공 시 content 영역에 결과 표시
                    },
                    error: function(xhr, status, error) {
                        console.error(status + ": " + error); // 에러 처리
                    }
                });
            });
        ;
    });

</script>

<style>
    body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif; /* 선택적으로 폰트 변경 */
    }
    #sidebar {
        position: fixed;
        left: 0;
        top: 0;
        height: 100%;
        width: 250px;
        /*background-color: #555; !* 밝은 배경색 *!*/
        padding: 20px;
        color: #3333; /* 어두운 텍스트 색상 */
    }
    #sidebar ul {
        list-style-type: none;
        padding: 0;
    }
    #sidebar ul li {
        margin-left: 10px;
        padding: 20px;
    }
    #sidebar ul li a{
        color : #333;
    }
    #content {
        margin-left: 270px;
        padding: 20px;
        background-color: #fff; /* 밝은 배경색 */
        color: #333; /* 어두운 텍스트 색상 */
    }

</style>
<body>
<header>
    <div id="sidebar">
        <ul>
            <li><a href="/main/main.do">Home</a></li>
            <li><a href="/main/notice.do">공지사항</a></li>
            <li><a href="/view/notice/notice_list.jsp">공지사항(임시)</a></li>
            <li><a href="#">사원관리</a></li>
            <li><a href="#">사원 권한관리</a></li>
        </ul>
    </div>
</header>
<div id="content">
    main.jsp, 내용 나올 부분
</div>



<footer>
</footer>
</body>
</html>