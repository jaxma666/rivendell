#rivendell

##摘要
rivendell是一个轻量级长连接&&推送框架(依赖netty 4.1.2)，可根据自己的业务进行扩展。

##特性

###长连接管理
map存储channel id和ChannelHandlerContext

###业务异步化
业务逻辑异步执行，不阻塞netty io线程
###断线重连
客户端每10秒发送心跳包，超过2次没有响应则进行断线重连

###扩展性
定义了简单的协议SimpleProtocol:


	private boolean success;(本次动作是否成功)
    private String action;(动作类型)
    private String content;(动作内容)

可以基于此协议实现自己的业务，需要实现IActionHandler接口。

当然也可以定义自己的协议。

###监控&&推送后台
可以看到实时长连接数，并提供推送入口。

![](https://raw.githubusercontent.com/majiaji/rivendell/master/screenshots/1.png)
