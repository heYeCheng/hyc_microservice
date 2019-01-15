# 说明

本项目实现的功能是商品购买下单 以及 支付，技术栈为基于 Spring cloud + JPA + Consul + zuul + Feign，并使用 rancher 对 docker 进行项目部署。

选择 Consul 做服务注册发现原因：首先是 *Eureka* 不再支持 2.0 版本，因此后续版本可能得不到很好的维护。其次 Consul 是基于 ca 的，而 Eureka 是基于 ap 的，eureka 提供弱一致性的服务发现，而 consul 提供强一致性的服务发现，由于服务上线后相对来说会比较稳定，因此对服务发现的速度不需要太严苛的要求。



另外在后续版本中，将加入 基于 RabbitMQ 消息队列，从而实现事务最终一致性。