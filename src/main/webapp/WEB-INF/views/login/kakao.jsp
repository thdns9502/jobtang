<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
</head>
<body>
    <button id="kakao-login-btn">카카오 로그인</button>

    <script>
        Kakao.init('30d243de5a9fb4b68db2b30c366d06cc');

        document.getElementById('kakao-login-btn').onclick = function() {
            Kakao.Auth.login({
                success: function(authObj) {
                    console.log(authObj);
                    // 로그인 성공 시 사용자 정보 요청
                    getUserInfo(authObj.access_token);
                },
                fail: function(err) {
                    console.error(err);
                }
            });
        };

        // 사용자 정보를 요청
        function getUserInfo(accessToken) {
            Kakao.API.request({
                url: '/v2/user/me',
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }
            }).then(function(res) { // 요청 성공시
                console.log(res);
                alert(`안녕하세요, ${res.kakao_account.profile.nickname}님!`);
            }).catch(function(err) { // 요청 실패시
                console.error(err);
            });
        }
    </script>
</body>
</html>