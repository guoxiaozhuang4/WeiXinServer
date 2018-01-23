<%@ include file="/include/head.jsp" %>
<!DOCTYPE>
<!-- forward.jsp -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
	
	window.onload = function () {
		
		//初始化input焦点
		document.getElementById('date').focus()
		
		//button转向事件
		document.getElementById('button').addEventListener('click', function () {
			
			var date = new Date()
			
			var tmp = date.getMonth() + 1 + ''
			var month = tmp.length == 1 ? '0'+tmp : tmp
			tmp = date.getDate() + ''
			var today = tmp.length == 1 ? '0'+tmp : tmp
					
			var add = document.getElementById('date').value
					
			var uri = "http:\/\/localhost:9081\/WeiXinServer\/_jsp\/" + month + today + '\/' + add + '.jsp'
			
			window.location.href = uri
		})
		
		//enter键转向
		document.getElementById('date').addEventListener('keydown', function(event) {
			
			if(event.keyCode == '13') {
				
				document.getElementById('button').click()
			}
		})
	}
	
</script>
</head>
<body>

	<div>
		<span>forward:</span>
		<input type="text" id="date" />
		<button id="button">go</button>
	</div>

</body>
</html>