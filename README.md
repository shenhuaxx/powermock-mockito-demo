# powermock-mockito-demo
powermock-mockito-demo with springboot

使用在springboot项目中使用powermock-mockito进行单元测试,演示mock static、private、whenNew、exception的测试。

注意

示例使用spring-boot 2.0.3.RELEASE  
它间接引用 junit-4.12  mockito-core-2.15.0  
但是官网powermock 1.7.x只支持2.8.0-2.8.9,如果使用默认引入版本,会出现找不到某些方法的异常.  
需要自定义mockito-core为相应版本。  


ServiceAspect只是为了验证当spy实体在被代理的情况下powermockito是否还会生效。  
1、ServiceAspect没有被注释,userService为代理对象,testPrivate执行失败.  
2、ServiceAspect被注释，userService为普通对象，testPrivate执行成功。  
