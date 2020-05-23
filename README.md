# Apache Dubbo

## 1 分布式基础理论

- 《分布式系统原理与范型》定义：“分布式系统是若干独立计算机的集合，这些计算机对于用户来说就像单个相关系统”分布式系统（distributed system）是建立在网络之上的软件系统。
- 随着互联网的发展，网站应用的规模不断扩大，常规的垂直应用架构已无法应对，分布式服务架构以及流动计算架构势在必行，**亟需一个治理系统**确保架构有条不紊的演进。

### 1.1 分布式系统发展演变

- 分布式系统发展演变：![LkIaEv](https://gitee.com/pxqp9W/testmarkdown/raw/master/imgs/2020/05/LkIaEv.png)
  
  - **单一应用架构**：当网站流量很小时，只需一个应用，将所有功能都部署在一起，以减少部署节点和成本。此时，用于简化增删改查工作量的数据访问框架(ORM)是关键。![HeQhGX](https://gitee.com/pxqp9W/testmarkdown/raw/master/imgs/2020/05/HeQhGX.png)
    
    - **简单说，ORM 就是通过实例对象的语法，完成关系型数据库的操作的技术，是"对象-关系映射"（Object/Relational Mapping） 的缩写。**
    
    - 适用于小型网站，小型管理系统，将所有功能都部署到一个功能里，简单易用。
    
    - 缺点： 
      
      - 1、性能扩展比较难
      
      - 2、协同开发问题
      
      - 3、不利于升级维护
  
  - **垂直应用架构**：将应用拆成互不相干的几个应用，以提升效率。此时，用于加速前端页面开发的 Web 框架(MVC)是关键。![86KV4S](https://gitee.com/pxqp9W/testmarkdown/raw/master/imgs/2020/05/86KV4S.png)
    
    - 通过切分业务来实现各个模块独立部署，降低了维护和部署的难度，团队各司其职更易管理，性能扩展也更方便，更有针对性。
    
    - 例如订单服务需要扩展使，只需要扩展订单应用即可
    
    - 缺点： 
      
      - 公用模块无法重复利用，开发性的浪费
      
      - 应用不可能完全独立，大量的应用之间需要交互
  
  - **分布式服务架构**：当垂直应用越来越多，应用之间交互不可避免，将核心业务抽取出来，作为独立的服务，逐渐形成稳定的服务中心，使前端应用能更快速的响应多变的市场需求。此时，用于提高业务复用及整合的分布式服务框架(RPC)是关键。![q9I3kJ](https://gitee.com/pxqp9W/testmarkdown/raw/master/imgs/2020/05/q9I3kJ.png)
    
    - 不同服务部署在不同服务器上，代码之间如何互调？——**RPC（Remote Procedure Call）远程过程调用**
  
  - **流动计算架构**：当服务越来越多，容量的评估，小服务资源的浪费等问题逐渐显现，此时需增加一个调度中心基于访问压力实时管理集群容量，提高集群利用率。此时，用于提高机器利用率的资源调度和治理中心(SOA)[ Service Oriented Architecture]是关键。![epq8ZI](https://gitee.com/pxqp9W/testmarkdown/raw/master/imgs/2020/05/epq8ZI.png)
    
    - 面向服务的架构（SOA）是一个组件模型，它将应用程序的不同功能单元（称为服务）进行拆分，并通过这些服务之间定义良好的接口和协议联系起来。接口是采用中立的方式进行定义的，它应该独立于实现服务的硬件平台、操作系统和编程语言。这使得构件在各种各样的系统中的服务可以以一种统一和通用的方式进行交互。

### 1.2 RPC

![ziufCe](https://gitee.com/pxqp9W/testmarkdown/raw/master/imgs/2020/05/ziufCe.jpg)

![Qffc6R](https://gitee.com/pxqp9W/testmarkdown/raw/master/imgs/2020/05/Qffc6R.png)

- RPC(Remote Procedure Call)是指远程过程调用，是一种进程间通信方式，他是一种技术的思想，而不是规范。**它允许程序调用另一个地址空间（通常是共享网络的另一台机器上）的过程或函数，而不用程序员显式编码这个远程调用的细节**。即程序员无论是调用本地的还是远程的函数，本质上编写的调用代码基本相同。

- RPC 两个核心模块：通讯，序列化。

- 常见的RPC框架：[DUbbo](https://dubbo.apache.org/zh-cn/) , [gRPC](https://grpc.io/), [Thrift](http://thrift.apache.org/) , [HSF](https://www.alibabacloud.com/help/zh/doc-detail/100087.htm)

### 1.3 Dubbo简介

- Apache Dubbo (incubating) |ˈdʌbəʊ| 是一款高性能、轻量级的开源 Java RPC 框架，它提供了三大核心能力：面向接口的远程方法调用，智能容错和负载均衡，以及服务自动注册和发现。

- Dubbo特性:
  
  - **面向接口代理的高性能RPC调用**:提供高性能的基于代理的远程调用能力，服务以接口为粒度，为开发者屏蔽远程调用底层细节。
  
  - **服务自动注册与发现**:支持多种注册中心服务，服务实例上下线实时感知。
  
  - **智能负载均衡**:内置条件、脚本等路由策略，通过配置不同的路由规则，轻松实现灰度发布，同机房优先等功能。
  
  - **高度可扩展能力**:内置多种负载均衡策略，智能感知下游节点健康状况，显著减少调用延迟，提高系统吞吐量。
  
  - **运行期流量调度**:遵循微内核+插件的设计原则，所有核心能力如Protocol、Transport、Serialization被设计为扩展点，平等对待内置实现和第三方实现。
  
  - **可视化的服务治理与运维**: 提供丰富服务治理、运维工具：随时查询服务元数据、服务健康状态及调用统计，实时下发路由策略、调整配置参数。

- Dubbo基本概念:![1efbdl](https://gitee.com/pxqp9W/testmarkdown/raw/master/imgs/2020/05/1efbdl.png)
  
  - **服务提供者（Provider）**：暴露服务的服务提供方，服务提供者在启动时，向注册中心注册自己**提供**的服务。
  
  - **服务消费者（Consumer）**: 调用远程服务的服务消费方，服务消费者在启动时，向注册中心**订阅**自己所需的服务，服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。
  
  - **注册中心（Registry）**：注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者
  
  - **监控中心（Monitor）**：服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心

- 调用关系说明:
  
  - 服务容器负责启动，加载，运行服务提供者。
  
  - 服务提供者在启动时，向注册中心注册自己提供的服务。
  
  - 服务消费者在启动时，向注册中心订阅自己所需的服务。
  
  - 注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。
  
  - 服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。
  
  - 服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。

### 1.4 Zookeeper注册中心

- [zookeeper](https://dubbo.apache.org/zh-cn/docs/user/references/registry/zookeeper.html)

- [Zookeeper](http://zookeeper.apache.org/) 是 Apache Hadoop 的子项目，是一个树型的目录服务，支持变更推送，适合作为 Dubbo 服务的注册中心，工业强度较高，可用于生产环境，并推荐使用

- [mac启动zookeeper单机模式 - 简书](https://www.jianshu.com/p/b889b86536be)



## 2 Dubbo实践

- 创建两个服务模块进行测试：![eaxxHp](https://gitee.com/pxqp9W/testmarkdown/raw/master/imgs/2020/05/eaxxHp.png)

- 测试预期结果：订单服务 web 模块在 A 服务器，用户服务模块在 B 服务器，A 可以远程调用 B 的功能。

### 2.2 编写公共的common-api模块

- 创建Bean和Service接口![iLJ4f1](https://gitee.com/pxqp9W/testmarkdown/raw/master/imgs/2020/05/iLJ4f1.png)
  
  ```java
  // 订单服务接口
  public interface OrderService {
  
      // 初始化订单
      public void initOrder(String user);
  }
  
  // 用户服务接口
  public interface UserService {
  
      // 根据用户id获取用户所有的收货地址
      public List<UserAddress> getUserAddressList(String userId);
  }
  
  // 用户地址类
  public class UserAddress implements Serializable {
      private Integer id;
      private String userAddress;
      private String userId;
      private String consignee;
      private String phoneNum;
      private String isDefault;
  
      public UserAddress() {
      }
  
      public UserAddress(Integer id, String userAddress, String userId, String consignee, String phoneNum, String isDefault) {
          this.id = id;
          this.userAddress = userAddress;
          this.userId = userId;
          this.consignee = consignee;
          this.phoneNum = phoneNum;
          this.isDefault = isDefault;
      }
  }
  ```

### 2.3 编写服务提供者user-service-provider模块

- 引入common-api的POM依赖

- 引入dubbo和zookeeper客户端的依赖
  
  ```java
          <!--引入dubbo-->
          <!-- https://mvnrepository.com/artifact/org.apache.dubbo/dubbo -->
          <dependency>
              <groupId>org.apache.dubbo</groupId>
              <artifactId>dubbo</artifactId>
              <version>2.7.4.1</version>
          </dependency>
  
          <!--注册中心使用的zookeeper，需要引入操作zk的客户端-->
          <!-- https://mvnrepository.com/artifact/org.apache.curator/curator-framework -->
          <dependency>
              <groupId>org.apache.curator</groupId>
              <artifactId>curator-framework</artifactId>
              <version>2.12.0</version>
          </dependency>
  
          <!--解决org/apache/curator/framework/recipes/cache/TreeCacheListener-->
          <dependency>
              <groupId>org.apache.curator</groupId>
              <artifactId>curator-recipes</artifactId>
              <version>2.8.0</version>
          </dependency>
  ```

- 编写UserService的实现类:
  
  ```java
  public class UserServiceImpl implements UserService {
      public List<UserAddress> getUserAddressList(String userId) {
          UserAddress userAddress1 = new UserAddress(1, "北京中南海", "1", "小明", "13666666666", "Yes");
          UserAddress userAddress2 = new UserAddress(2, "上海外滩", "1", "小明", "13666666666", "Yes");
          return Arrays.asList(userAddress1, userAddress2);
      }
  }
  ```

- 编写Dubbo配置文件向注册中心暴露服务：
  
  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
         xsi:schemaLocation="http://www.springframework.org/schema/beans        http://www.springframework.org/schema/beans/spring-beans-4.3.xsd        http://dubbo.apache.org/schema/dubbo        http://dubbo.apache.org/schema/dubbo/dubbo.xsd">
  
      <!-- 1、指定当前应用的名字（同样的服务名字相同，不要和别的服务同名） -->
      <dubbo:application name="user-service-provider"  />
  
      <!-- 2、指定注册中心的位置 -->
      <dubbo:registry address="zookeeper://127.0.0.1:2181" />
  
      <!-- 3、指定通信规则，用dubbo协议在20880端口暴露服务 -->
      <dubbo:protocol name="dubbo" port="20880" />
  
      <!-- 4、声明需要暴露的服务接口 ， ref指向服务接口的真正实现-->
      <dubbo:service interface="org.example.service.UserService" ref="userServiceImpl" />
  
      <!-- 5、将服务的实现注入到容器 -->
      <bean id="userServiceImpl" class="org.example.service.impl.UserServiceImpl" />
  </beans>
  ```

- 编写服务的主启动类：
  
  ```java
  public class ProviderMainApplication {
      public static void main(String[] args) throws IOException {
          ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("provider.xml");
          ioc.start();
          System.in.read(); //控制台输入任意字符停止程序
      }
  }
  ```

- 在Dubbo-admin中查看服务是否注册成功：![8jltRf](https://gitee.com/pxqp9W/testmarkdown/raw/master/imgs/2020/05/8jltRf.png)



### 2.4 编写服务消费者order-service-consumer

- 同样引入POM依赖

- 编写配置文件`consumer.xml`：
  
  ```java
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:dubbo="http://dubbo.apache.org/schema/dubbo" xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
  
      <!--开启注解扫描-->
      <context:component-scan base-package="org.example.service.impl"></context:component-scan>
      
      <!-- 1、指定当前应用的名字（同样的服务名字相同，不要和别的服务同名） -->
      <dubbo:application name="order-service-consumer"  />
  
      <!-- 2、指定注册中心的位置 -->
      <dubbo:registry address="zookeeper://127.0.0.1:2181" />
  
      <!-- 3、声明需要调用的远程接口，生成远程服务代理-->
      <dubbo:reference id="userService" interface="org.example.service.UserService" />
  
  </beans>
  ```

- 使用`@Autowired`来注入UserService userService

- 编写主启动类:
  
  ```java
  public class ConsumerMainApplication {
      public static void main(String[] args) throws IOException {
          ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("consumer.xml");
          OrderService orderService = ioc.getBean(OrderService.class);
          orderService.initOrder("1");
          System.in.read();
      }
  }
  ```

### 2.5 启动测试

- consumer调用provider成功

- ![9Wr4je](https://gitee.com/pxqp9W/testmarkdown/raw/master/imgs/2020/05/9Wr4je.png)



### 2.6 dubbo-simple-monitor







## 参考资料

- [Dubbo 中文文档](http://dubbo.apache.org/zh-cn/docs/user/new-features-in-a-glance.html)
- [Home · apache/dubbo Wiki](https://github.com/apache/dubbo/wiki)
- [ORM 实例教程 - 阮一峰的网络日志](https://www.ruanyifeng.com/blog/2019/02/orm-tutorial.html)
- [软负载和硬负载 - 晨猫 - OSCHINA](https://my.oschina.net/mengzhang6/blog/1635069)
- [mac启动zookeeper单机模式 - 简书](https://www.jianshu.com/p/b889b86536be)
- 