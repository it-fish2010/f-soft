# F-Soft 项目介绍
F-Soft泛松后台管理平台，旨在搭建一个接私活的基础框架，方便人员开发自己的业务系统。框架采用比较老的技术，开发完成，打包为传统的war程序包，部署到中间容器（Tomcat）。  
  
	1. SpringBoot版本，待建设……已纳入计划  
	2. 程序启动后，默认访问路径: http://localhost:8080/fsoft/login  

# **更新说明** 
## *2020-02-16 更新*
    1.[优化]-优化BuildTree工具类，实现BaseTreeVo的树形结构返回  
    2.[新增]-实现地区管理功能  
    3.[新增]-实现字典管理功能
    4.常规bug修复  

## *2020-02-14 更新*
	1. 优化SYS_MENU/SYS_ORGANIZE/SYS_DICT/SYS_DICT_ITEM表，使用统一的编码/名称/上级节点命名规则
	2. 实现单位管理功能
	3. 前端引入Layui第三方扩展插件xm-select、eleTree，实现单位管理、菜单管理、用户管理模块中的下拉复选及列表的树形显示
	4. 实体Bean增加BaseTreeVo支持；
	5. 补充项目截图      

# **框架说明** 
## 后端：`Spring+SpringMVC+MyBatis+Shiro+Redis缓存`  
### Spring+SpringMVC
轻量级的控制反转(IoC)和面向切面(AOP)的容器框架。  
> 控制反转——Spring通过一种称作控制反转（IoC）的技术促进了松耦合  
> 面向切面——Spring提供了面向切面编程的丰富支持，允许通过分离应用的业务逻辑与系统级服务（例如审计（auditing）和事务（transaction）管理）进行内聚性的开发  

### MyBatis 
优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。    

	1. MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。
	2. MyBatis 可以使用简单的 XML 或注解来配置和映射原生类型、接口和 Java 的 POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。  
### Apache Shiro
强大且易用的Java安全框架,执行身份验证、授权、密码和会话管理。使用Shiro的易于理解的API,您可以快速、轻松地获得任何应用程序,从最小的移动应用程序到最大的网络和企业应用程序。   
### Redis（全称：Remote Dictionary Server 远程字典服务）
是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API  
`截止2020-02-14版本，没有应用到缓存机制`

## 前端：`Layui+JQuery+Freemarker`
由于本人前端技术水平有限，所以直接使用了开源项目X-admin，唯一要做的事情，仅仅是把html语法转为freemarker语法。  
### **X-admin** [X-admin](http://x.xuebingsi.com/)  
经典前端网站后台管理框架,开源地址：[x-admin(gitee)](https://gitee.com/daniuit/X-admin)  
### ** FreeMarker ** [FreeMarker-百度百科](https://baike.baidu.com/item/freemarker/9489366?fr=aladdin)
`用过FreeMarker之后，都忘记JSP语法了~~~~`  
> FreeMarker是一款模板引擎： 即一种基于模板和要改变的数据， 并用来生成输出文本（HTML网页、电子邮件、配置文件、源代码等）的通用工具。 它不是面向最终用户的，而是一个Java类库，是一款程序员可以嵌入他们所开发产品的组件。    
> FreeMarker是一个用`Java语言编写的模板引擎` ，它基于模板来生成文本输出。FreeMarker与Web容器无关，即在Web运行时，它并不知道Servlet或HTTP。  
   
# **部署配置**
## 数据库初始化
> 执行项目中/doc/`fsoft-dbinstall.sql`文件。(`支持MySQL`)

## db.properties配置
只需要修改`jdbc.url`,`jdbc.username`,`jdbc.password`，其他值保持默认。
 
	jdbc.url=jdbc:mysql://localhost:3306/mysql?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8
	jdbc.username=root
	jdbc.password=root
	jdbc.initialSize=5
	jdbc.maxActive=30
	jdbc.minPoolSize=2
	jdbc.maxIdleTime=30000
	jdbc.idleConnectionTestPeriod=100

* `jdbc.url` 配置数据库连接，暂时仅限于支持mysql数据库
* `jdbc.username` 数据库用户，明文，`（出于安全考虑，建议生产环境使用Spring配置，自行编写解密规则）`
* `jdbc.password` 数据库用户登录密码，明文；`（出于安全考虑，建议生产环境使用Spring配置，自行编写解密规则）`  

# **功能效果截图**
### *登录首页*
![F-Soft登录](./doc/img/fsoft-login.png)  
### *首页*
![F-Soft首页](./doc/img/fsoft-index.png)  
### *角色管理* 
![F-Soft-角色管理-列表](./doc/img/fsoft-role-list.png)  
![F-Soft-角色管理-编辑](./doc/img/fsoft-role-modify.png)
![F-Soft-角色管理-分配权限](./doc/img/fsoft-role-rights.png)  
### *单位管理* 
![F-Soft单位管理-列表](./doc/img/fsoft-org-list.png)  
![F-Soft单位管理-编辑](./doc/img/fsoft-org-modify.png)
![F-Soft单位管理-选择上级](./doc/img/fsoft-org-modify-select.png)  
### *菜单管理* 
![F-Soft菜单管理-列表](./doc/img/fsoft-menu-list.png)  
![F-Soft菜单管理-编辑](./doc/img/fsoft-menu-modify.png)
![F-Soft菜单管理-选择上级](./doc/img/fsoft-menu-modify-parent.png)  
### *用户管理* 
![F-Soft用户管理-列表](./doc/img/fsoft-user-list.png)  
![F-Soft用户管理-列表-禁用](./doc/img/fsoft-user-list-disable.png)  
![F-Soft用户管理-编辑](./doc/img/fsoft-user-modify.png)
![F-Soft用户管理-分配角色](./doc/img/fsoft-user-modify-role.png)  
 


