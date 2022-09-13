<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
    <title>index</title>
    <script type="text/javascript">

        function doLoginUserCheck(f) {
            if (f.userId.value == "") {
                alert("아이디를 입력하세요");
                f.userId.focus();
                return false;
            }

            if (f.password.value == "") {
                alert("비밀번호를 입력하세요");
                f.password.focus();
                return false;
            }
        }
    </script>
</head>
<body>
<form action="/users/login" method="post" datatype="application/json" onsubmit="return doLoginUserCheck(this);">
    <input type="text" name="userId" value="아이디">
    <input type="password" name="password" value="비밀번호">
    <input type="submit">
</form>

</body>
</html>