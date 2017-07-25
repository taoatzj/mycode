<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />


<%
	String path = request.getContextPath();
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ "/";
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
	request.setAttribute("rootPath", rootPath);
	pageContext.setAttribute("newLineChar", "\n");
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<style type="text/css">
.datagrid-mask {
	background: #ccc;
}

.datagrid-mask-msg {
	border-color: #95B8E7;
}

.datagrid-mask-msg {
	background: #ffffff url('../images/loading.gif') no-repeat scroll 5px
		center;
}

.datagrid-mask {
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	opacity: 0.3;
	filter: alpha(opacity = 30);
	display: none;
}

.datagrid-mask-msg {
	position: absolute;
	top: 50%;
	margin-top: -20px;
	padding: 12px 5px 10px 30px;
	width: auto;
	height: 16px;
	border-width: 2px;
	border-style: solid;
	display: none;
}

.list_table {
	border: 1px solid #CCCCCC;
	border-collapse: collapse;
	color: #333333;
	margin: 0 0 0;
	width: 100%;
}

.list_table tbody td {
	border-top: 1px solid #CCCCCC;
	text-align: left;
}

.list_table th {
	line-height: 1.2em;
	vertical-align: top;
}

.list_table td {
	line-height: 2em;
	font-size: 12px;
	vertical-align: central;
	align: left;
}

.list_table td input {
	width: 90%;
}

.list_table tbody tr:hover th,.list_table tbody tr:hover td {
	background: #EEF0F2;
}

.list_table thead tr {
	background: none repeat scroll 0 0 #09f;
	color: #fff;
	font-weight: bold;
	border-bottom: 1px solid #CCCCCC;
	border-right: 1px solid #CCCCCC;
}
</style>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/plugins/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>js/task.js"></script>
</head>
<title>task</title>
<body class="bgray">
	<form id="addForm" method="post">

		<table class="list_table">
			<thead>
				<tr>
					<td style="width: 20xp">任务id&nbsp;&nbsp;&nbsp;</td>
					<td>Bean名称&nbsp;&nbsp;</td>
					<td>方法名</td>
					<td>参数</td>
					<td>cron表达式</td>
					<td>描述</td>
					<td>状态</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="job" items="${taskList}">
					<tr>
						<td>${job.jobId }</td>
						<td>${job.beanName }</td>
						<td>${job.methodName }</td>
						<td>${job.params }</td>
						<td>${job.cronExpression }</td>
						<td>${job.remark }</td>
						<td>
						<c:choose>
								<c:when test="${job.status=='0' }">
									<a href="javascript:;"
										onclick="changeJobStatus('${job.jobId}','stop')">停止</a>&nbsp;
								</c:when>
								<c:otherwise>
									<a href="javascript:;"
										onclick="changeJobStatus('${job.jobId}','start')">开启</a>&nbsp;
								</c:otherwise>
							</c:choose>
						</td>
						<td><a href="javascript:;" onclick="updateCron('${job.jobId}')">更新cron</a>
						&nbsp; || &nbsp; 
						<a href="javascript:void(0);"  onclick="updateScheduleJob('${job.jobId}')"> 修改</a>
						&nbsp; || &nbsp; 
						<a href="javascript:void(0);"  onclick="deleteScheduleJob('${job.jobId}')"> 删除</a>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td>新增</td>
					<td><input type="text" name="beanName" id="beanName" value=""/></td>
					<td><input type="text" name="methodName" id="methodName" value=""/></td>
					<td><input type="text" name="params" id="params" value=""/></td>
					<td><input type="text" name="cronExpression" id="cronExpression"/></td>
					<td><input type="text" name="remark" id="remark"/></td>
					<td><input type="hidden" name="status" id="status" value="1"/></td>
					<td><input type="button" onclick="add()" value="保存" /></td>
				</tr>
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
	/*
	 * 添加新定时任务校验
	 */
	function validateAdd() {
		if ($.trim($('#beanName').val()) == '') {
			alert('beanName不能为空！');
			$('#beanName').focus();
			return false;
		}
		if ($.trim($('#methodName').val()) == '') {
			alert('methodName不能为空！');
			$('#methodName').focus();
			return false;
		}

		if ($.trim($('#cronExpression').val()) == '') {
			alert('cron表达式不能为空！');
			$('#cronExpression').focus();
			return false;
		}

		return true;
	}
	//添加新定时任务
	function add() {
		if (validateAdd()) {
			showWaitMsg();
			$.ajax({
				type : "POST",
				async : false,
				dataType : "JSON",
				cache : false,
				url : "${basePath}scheduleJob/add",
				data : $("#addForm").serialize(),
				success : function(data) {
					hideWaitMsg();
					if (data.flag) {
						location.reload();
					} else {
						alert(data.msg);
					}

				}// end-callback
			});// end-ajax
		}
	}

	//删除定时任务
	function deleteScheduleJob(jobId) {
		layer.confirm('确定删除吗?', {
			icon : 3,
			title : '提示'
		}, function(index) {
			$.ajax({
				url : "${basePath}scheduleJob/deleteScheduleJob",
				type : "post",
				data : {
					"jobId" : jobId
				},
				dataType : "json",
				success : function(data) {
					layer.msg('删除成功');
					layer.close(index);
					location.reload();
				}

			})// -- end ajax
		});// ---end layer
	}
	//修改定时任务
	function updateScheduleJob(jobId) {
		$.ajax({
			url : '${basePath}scheduleJob/getTaskById',
			type : 'POST',
			dataType : 'json',
			async : false,
			data : {
				"jobId" : jobId
			},
			success : function(data) {

				$("#jobId1").val(data.obj.jobId);
				$("#beanName1").val(data.obj.beanName);
				$("#methodName1").val(data.obj.methodName);
				$("#params1").val(data.obj.params);
				$("#cronExpression1").val(data.obj.cronExpression);
				$("#remark1").val(data.obj.remark);
				$("#status1").val(data.obj.status);

				layer.open({
					type : 1,
					title : '修改定时任务',
					area : [ '400px', '300px' ],
					content : $("#updateScheduleJobFormDiv"),
					btn : [ '确认', '取消' ],
					btnAlign : 'c',
					yes : function(index, layero) {
						$.ajax({
							url : '${basePath}scheduleJob/updateScheduleJob',
							type : 'post',
							dataType : 'json',
							async : false,
							data : $("#updateScheduleJobForm").serialize(),
							success : function(data) {
								if (data.flag) {
									layer.msg('修改成功');
									layer.closeAll('page');
									location.reload();
								}
							}
						});

					}
				});// ---layer end
			}
		});// ---end ajax
	}
	//开启暂停定时任务
	function changeJobStatus(jobId, cmd) {
		showWaitMsg();
		$.ajax({
			type : "POST",
			async : false,
			dataType : "JSON",
			url : "${basePath}scheduleJob/changeJobStatus",
			data : {
				jobId : jobId,
				cmd : cmd
			},
			success : function(data) {
				hideWaitMsg();
				if (data.flag) {
					location.reload();
				} else {
					alert(data.msg);
				}

			}// end-callback
		});// end-ajax
	}

	//更新时间表达式
	function updateCron(jobId) {


		$.ajax({
			url : '${basePath}scheduleJob/getTaskById',
			type : 'POST',
			dataType : 'json',
			async : false,
			data : {
				"jobId" : jobId
			},
			success : function(data) {
				$("#cronExpression2").val(data.obj.cronExpression);
				layer.open({
					type : 1,
					title : '修改corn',
					area : [ '300px', '140px' ],
					content : $("#cronDiv"),
					btn : [ '确认', '取消' ],
					btnAlign : 'c',
					yes : function(index, layero) {
						var corn = $("#cronExpression2").val()
						$.ajax({
							type : "POST",
							async : false,
							dataType : "JSON",
							async : false,
							url : "${basePath}scheduleJob/updateCron",
							data : {
								jobId : jobId,
								cron : corn
							},
							success : function(data) {
								console.log(data)
								if (data.flag) {
									layer.msg('修改成功');
									location.reload();
								} else {
									alert(data)
								}
							},
							error : function(data) {
								alert(data)
							}// end-callback
						});// end-ajax
					}
				});// ---layer end

			}
		});// ---end ajax
	}

	function showWaitMsg(msg) {
		if (msg) {

		} else {
			msg = '正在处理，请稍候...';
		}
		var panelContainer = $("body");
		$("<div id='msg-background' class='datagrid-mask' style=\"display:block;z-index:10006;\"></div>").appendTo(panelContainer);
		var msgDiv = $(
				"<div id='msg-board' class='datagrid-mask-msg' style=\"display:block;z-index:10007;left:50%\"></div>")
				.html(msg).appendTo(panelContainer);
		msgDiv.css("marginLeft", -msgDiv.outerWidth() / 2);
	}
	function hideWaitMsg() {
		$('.datagrid-mask').remove();
		$('.datagrid-mask-msg').remove();
	}

	</script>
	<div id= "updateScheduleJobFormDiv" style="display: none">
	<table>
	 	 <form id='updateScheduleJobForm'>
		 	 <tr>
		 	 	<td>bean名称:</td>
		 	 	<td><input type='text' name='beanName' id='beanName1' /></td>
		 	 </tr>
		 	 <tr>
			 	 <td>方法名:</td>
			 	 <td> <input type='text' name='methodName' id='methodName1' /></td>
		 	 </tr>
		 	 <tr>
			 	 <td> 参数:</td>
			 	 <td><input type='text' name='params' id='params1' /></td>
		 	 </tr>
		 	 <tr>
			 	 <td><label>cron表达式 :</label></td>
			 	 <td> <input type='text' name='cronExpression' id='cronExpression1'/></td>
		 	 </tr>
		 	 <tr>
			 	 <td>  描述:</td>
			 	 <td> <input type='text' name='remark' id='remark1'/></td>
		 	 </tr>
            <input type='hidden' name='jobId' id='jobId1' />
            <input type='hidden' name='status' id='status1'  />
         </form>
	</table>
	</div>
	<div id="cronDiv" style="display: none">
		<label>cron表达式 :</label> <input type='text' name='cronExpression' id='cronExpression2'/> <br>
	</div>
</body>
</html>




