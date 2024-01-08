<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String noticeNo = request.getParameter( "noticeNo" ); %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>공지사항 수정</title>
    <!-- jQuery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <!-- 여기에 필요한 CSS 스타일과 head 내용 추가 -->
    <!-- ... -->
</head>
<body>
<!-- 공지사항 수정을 위한 폼 추가 -->
<form id="updateNoticeForm">

    <label for="subjectInput">제목:</label>
    <input type="text" id="subjectInput" name="subject"><br><br>

    <label for="writerInput">작성자:</label>
    <input type="text" id="writerInput" name="writer"><br><br>

    <label for="contentInput">내용:</label>
    <textarea id="contentInput" name="content"></textarea><br><br>

    <button id="updateNoticeBtn" type="button">공지사항 수정</button>
</form>

<script>


    $(document).ready(function () {

        function fillFormWithData(data) {

            $("#subjectInput").val(data.subject);
            $("#writerInput").val(data.writer);
            $("#contentInput").val(data.content);
        }
            $.ajax({
                url: "/main/getNotice.do",
                type: 'POST',
                data: {noticeNo: <%=noticeNo%>},
                dataType: 'json',
                success: function (data) {
                    // 성공적으로 데이터를 받아왔을 때 폼에 데이터를 넣어줍니다.
                    fillFormWithData(data);
                },
                error: function (err) {
                    // 에러 처리
                    console.log("데이터 가져오기 실패");
                    console.log( <%=noticeNo%> );
                    console.log( err );
                    console.log( err.responseText );
                }
            });

        $('#updateNoticeBtn').on('click', function (e) {
            // e.preventDefault();

            let subject = $('#subjectInput').val();
            let writer = $('#writerInput').val();
            let content = $('#contentInput').val();


            let data = {
                subject: subject,
                writer: writer,
                writeDate: new Date().toISOString(),
                content: content,
                no:<%=noticeNo%>
            };

            console.log(data);

            $.ajax({
                url: "/main/updateNotice.do",
                type: 'POST',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(data),
                success: function (response) {

                    alert(response);
                    window.location.href = '/main/main.do#';

                },
                error: function (xhr, status, error) {
                    // 실패했을 때의 처리
                    alert('공지사항 추가 실패');
                    console.error(error);
                }
            });
        });
    });



</script>
</body>
</html>
