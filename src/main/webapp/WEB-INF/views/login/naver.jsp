<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <title>���̹� �α���</title>
  <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>

<body>
  <!-- ���̹� �α��� ��ư ���� ���� -->
  <div id="naver_id_login"></div>
  <!-- //���̹� �α��� ��ư ���� ���� -->
  
  
  <script type="text/javascript">
  	var naver_id_login = new naver_id_login("oH0Wj3DkR7GR6Co0XFJz", "http://localhost:8080/naver");
  	var state = naver_id_login.getUniqState();
  	naver_id_login.setButton("white", 2,40);
  	naver_id_login.setDomain("");
  	naver_id_login.setState(state);
  	naver_id_login.setPopup();
  	naver_id_login.init_naver_id_login();
  </script>



</body>

</html>