<html>
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
  <meta name="renderer" content="webkit">
  <link rel="stylesheet" href="/layui/css/layui.css?v=20210508">
  <script src="/js/jquery.min.js"></script>
  <script src="/layui/layui.js?v=20220124"></script>
</head>
<body>
  <form class="layui-form" id="add_form" lay-filter="add_form" style="padding-top: 30px;padding-left: 50px;">
    <div class="layui-form-item">
      <label class="layui-form-label">Group</label>
      <div class="layui-input-block">
        <input type="text" name="groupId" id="f_group_id" autocomplete="off" class="layui-input" placeholder="com.example">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">Artifact</label>
      <div class="layui-input-block">
        <input type="text" name="artifact" id="f_artifact" autocomplete="off" class="layui-input" placeholder="demo">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">Description</label>
      <div class="layui-input-block">
        <input type="text" name="description" id="f_description" autocomplete="off" class="layui-input" placeholder="description">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">Mysql url</label>
      <div class="layui-input-block">
        <input type="text" name="mysql_url" id="f_mysql_url" autocomplete="off" class="layui-input" placeholder="127.0.0.1">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">Mysql port</label>
      <div class="layui-input-block">
        <input type="text" name="mysql_port" id="f_mysql_port" autocomplete="off" class="layui-input" placeholder="3306">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">Mysql database</label>
      <div class="layui-input-block">
        <input type="text" name="mysql_database" id="f_mysql_database" autocomplete="off" class="layui-input" placeholder="database">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">Mysql username</label>
      <div class="layui-input-block">
        <input type="text" name="mysql_username" id="f_mysql_username" autocomplete="off" class="layui-input" placeholder="username">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">Mysql password</label>
      <div class="layui-input-block">
        <input type="text" name="mysql_password" id="f_mysql_password" autocomplete="off" class="layui-input" placeholder="password">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">redis host</label>
      <div class="layui-input-block">
        <input type="text" name="redis_host" id="f_redis_host" autocomplete="off" class="layui-input" placeholder="127.0.0.1">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">redis port</label>
      <div class="layui-input-block">
        <input type="text" name="redis_port" id="f_redis_port" autocomplete="off" class="layui-input" placeholder="6379">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">redis password</label>
      <div class="layui-input-block">
        <input type="text" name="redis_password" id="f_redis_password" autocomplete="off" class="layui-input" placeholder="">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">redis database</label>
      <div class="layui-input-block">
        <input type="text" name="redis_database" id="f_redis_database" autocomplete="off" class="layui-input" placeholder="1">
      </div>
    </div>
    <div class="layui-form-item">
      <div class="layui-input-block">
        <button class="layui-btn" lay-submit lay-filter="*">确定</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
      </div>
    </div>
  </form>
</body>

<script>
  function showLoading(msg) {
    return layui.layer.msg(msg, {
      icon: 16,
      shade: [0.5, '#f5f5f5'],
      scrollbar: false,
      offset: 'auto',
      time: 100000
    });
  }

  layui.use(['form', 'layer'], function () {
    var form = layui.form;
    form.on('submit(*)', function(){
      var i = showLoading("正在处理");
      var data = {
        'groupId': $('#f_group_id').val(),
        'artifactId':$('#f_artifact').val(),
        'desc':$('#f_description').val(),
        'mysqlUrl': $('#f_mysql_url').val(),
        'mysqlPort': $('#f_mysql_port').val(),
        'mysqlDatabase': $('#f_mysql_database').val(),
        'mysqlUsername': $('#f_mysql_username').val(),
        'mysqlPassword': $('#f_mysql_password').val(),
        'redisHost': $('#f_redis_host').val(),
        'redisPort': $('#f_redis_port').val(),
        'reidsPassword': $('#f_redis_password').val(),
        'redisDatabase': $('#f_redis_database').val(),
      };
      $.ajax({
        method: 'POST',
        dataType: "json",
        url: 'execute.html',
        data: data,
        success: function(data) {
          hideLoading(i);
          window.open($('#f_artifact').val()+"zip");
        },
        error: function () {
        }
      });
      return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
    form.render();
  });
</script>
</html>