# F-Soft 项目介绍
F-Soft泛松管理平台，旨在搭建一个接私活的基础框架，方便开发者开发自己的业务系统。框架采用比较老的技术，开发完成，打包为传统的war程序包，部署到中间容器（Tomcat）。  

	1. SpringBoot版本，待建设……已纳入计划  
	2. 程序启动后，默认访问路径: http://localhost:8080/fsoft/login  
## **更新说明** 
### *2020-02-16 更新*
    1.[优化]-优化BuildTree工具类，实现BaseTreeVo的树形结构返回  
    2.[新增]-实现地区管理功能  
    3.[新增]-实现字典管理功能
    4.常规bug修复  

### *2020-02-14 更新*
	1. 优化SYS_MENU/SYS_ORGANIZE/SYS_DICT/SYS_DICT_ITEM表，使用统一的编码/名称/上级节点命名规则
	2. 实现单位管理功能
	3. 前端引入Layui第三方扩展插件xm-select、eleTree，实现单位管理、菜单管理、用户管理模块中的下拉复选及列表的树形显示
	4. 实体Bean增加BaseTreeVo支持；
	5. 补充项目截图      

# **框架说明** 
## 后端：`Spring+SpringMVC+MyBatis+Shiro+Redis缓存`  
### **Mybatis** 
老人家，对于网上的自动生成代码的小工具比较排斥，所有MyBatis的XML配置脚本，基本上手写。SQL语句也经过自己的一些格式化完成美化，增加了代码的可读性。   
### Redis（全称：Remote Dictionary Server 远程字典服务）
太久没有学习，至今还没有完成Radis的配置及应用，`截止2020-02-14版本，没有应用到缓存机制`  
## 前端：`Layui+JQuery+Freemarker`
由于本人前端技术水平有限，所以直接使用了开源项目[X-admin](http://x.xuebingsi.com/)，唯一要做的事情，仅仅是把html语法转为freemarker语法。`关于前后端分离`  

### ** FreeMarker ** [FreeMarker-百度百科](https://baike.baidu.com/item/freemarker/9489366?fr=aladdin)
`用过FreeMarker之后，都忘记JSP语法了~~~~`    
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
 


