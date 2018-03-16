# TCC补偿事务

我们通过mom组件可以实现事务最终一致性，但是对于强一致性的要求高的业务，通过消息扭转时效性达不到要求，我们需要通过TCC补偿事务来实现。


## 使用范围
* 强隔离性，强一致性要求的业务
* 适用于执行时间较短的业务

## DEMO说明

通过用户注册后发送积分和优惠券的场景来演示，
tcc的过程 参考 https://github.com/changmingxie/tcc-transaction/issues/132

## 模块说明
* tcc-account ：用户服务
* tcc-coupon ：优惠券服务
* tcc-points ：积分服务

## 怎么运行

1.运行eureka   
2.下载common项目 https://github.com/ambluse/common-elegance  
3.下载本项目  
4.下载tcc框架(原项目没有注释，fork过来添加了相应日志) https://github.com/yonyou-auto-dev/tcc-transaction   
5.脚本在script目录下  

## 测试

* 调用接口 http://localhost:8080/user/sign?userName=14   
* 用户注册后会发放积分和优惠券  
* 如果try过程失败会同步回滚
* 如果confirm和cancel过程失败，会有job来重试


## demo的业务逻辑不严谨，仅用来演示TCC