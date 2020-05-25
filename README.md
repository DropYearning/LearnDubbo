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



### 2.6 整合SpringBoot的过程

- （一）创建`boot-user-service-provider`模块，作为生产者模块，复制之前的类到其中即可

- （二）创建`boot-order-service-consumer`模块作为消费者模块，复制之前的类到其中即可

- （三）在`boot-user-service-provider`模块中导入POM依赖：
  
  ```xml
          <!--dubbo-->
          <dependency>
              <groupId>org.apache.dubbo</groupId>
              <artifactId>dubbo-spring-boot-starter</artifactId>
              <version>2.7.4.1</version>
          </dependency>
  
          <!--apache.curator-->
          <dependency>
              <groupId>org.apache.curator</groupId>
              <artifactId>curator-framework</artifactId>
              <version>2.12.0</version>
          </dependency>
          <dependency>
              <groupId>org.apache.curator</groupId>
              <artifactId>curator-recipes</artifactId>
              <version>2.8.0</version>
          </dependency>
  ```

- （三）使用SpringBoot的配置方式修改`boot-user-service-provider`
  
  - 1、在`boot-user-service-provider`的application.properties中配置:
    
    ```properties
    dubbo.application.name=boot-user-service-provider
    dubbo.registry.address=127.0.0.1:2181
    dubbo.registry.protocol=zookeeper
    dubbo.protocol.name=dubbo
    dubbo.protocol.port=20880
    ```
  
  - 2、在`boot-user-service-provider`中需要的暴露的服务
    
    ```java
    @Service // 注意这个注解是dubbo的，用来暴露服务
    @Component
    public class UserServiceImpl implements UserService {
        public List<UserAddress> getUserAddressList(String userId) {
            UserAddress userAddress1 = new UserAddress(1, "北京中南海", "1", "小明", "13666666666", "Yes");
            UserAddress userAddress2 = new UserAddress(2, "上海外滩", "1", "小明", "13666666666", "Yes");
            return Arrays.asList(userAddress1, userAddress2);
        }
    }
    ```
  
  - 3、在`boot-user-service-provider`的主启动类上`@EnableDubbo`

- （四) SpringBoot的配置方式修改`boot-user-service-provider`
  
  - 1、引入同样的POM依赖
  
  - 2、修改application.properties：
    
    ```properties
    dubbo.application.name=boot-order-service-consumer
    dubbo.registry.address=127.0.0.1:2181
    dubbo.registry.protocol=zookeeper
    dubbo.protocol.name=dubbo
    dubbo.protocol.port=20880
    // 不和dubbo-admin冲突
    server.port=8081 
    ```
  
  - 3、使用`@Reference`注解在OrderServiceImpl中引用UserService服务:
  
  ```java
  @Service // 将整个OrderServiceImpl注入到IOC容器中
  public class OrderServiceImpl implements OrderService {
  
      //@Autowired
      @Reference //dubbo提供的注解
      UserService userService;
  
      public List<UserAddress> initOrder(String userId) {
          System.out.println("用户id:" + userId);
          // 查询用户的收货地址，需要调用user-service-provider的服务
          List<UserAddress> userAddressList = userService.getUserAddressList(userId);
          for (UserAddress userAddress: userAddressList) {
              System.out.println(userAddress.getUserAddress());
          }
          return userAddressList;
      }
  }
  ```
  
  - 4、在主启动类上`@EnableDubbo`

- （五）启动并测试
  
  ![qiSwUL](https://gitee.com/pxqp9W/testmarkdown/raw/master/imgs/2020/05/qiSwUL.png)
  
  ![PUigEj](https://gitee.com/pxqp9W/testmarkdown/raw/master/imgs/2020/05/PUigEj.png)





## 3 Dubbo配置

[dubbo xml配置](https://dubbo.apache.org/zh-cn/docs/user/configuration/xml.html)

[dubbo properties配置](https://dubbo.apache.org/zh-cn/docs/user/configuration/properties.html)

- 属性配置的优先级：![8x3VAj](https://gitee.com/pxqp9W/testmarkdown/raw/master/imgs/2020/05/8x3VAj.jpg)
  
  - 优先级从高到低：
    
    - JVM -D参数，当你部署或者启动应用时，它可以轻易地重写配置，比如，改变dubbo协议端口；
    
    - XML, XML中的当前配置会重写dubbo.properties中的；
    
    - Properties，默认配置，仅仅作用于以上两者没有配置时。

### 3.1 启动时检查

[preflight-check](https://dubbo.apache.org/zh-cn/docs/user/demos/preflight-check.html)

- Dubbo 缺省会在启动时检查依赖的服务是否可用，不可用时会抛出异常，阻止 Spring 初始化完成，以便上线时，能及早发现问题，默认 `check="true"`。

- 可以通过 `check="false"` 关闭检查，比如，测试时，有些服务不关心，或者出现了循环依赖，必须有一方先启动。

- 如果你的 Spring 容器是懒加载的，或者通过 API 编程延迟引用服务，请关闭 check，否则服务临时不可用时，会抛出异常，拿到 null 引用，如果 `check="false"`，总是会返回引用，当服务恢复时，能自动连上。

### 3.2 超时设置

- 由于网络或服务端不可靠，会导致调用出现一种不确定的中间状态（超时）。为了避免超时导致客户端资源（线程）挂起耗尽，必须设置超时时间。

- 单个接口设置超时：`<dubbo:reference id="userService" interface="org.example.service.UserService" timeout="5000"/>`

- 全局设置超时：`<dubbo:consumer timeout="5000"/>`



### 3.3 重试次数（幂等性）

> 幂等方法（查询、删除）上要设置重试次数，非幂等方法（增加）不能设置重试次数

- 远程服务调用重试次数，不包括第一次调用，不需要重试请设为0

- 缺省使用<dubbo:consumer>的retries

- `<dubbo:consumer check="false" timeout="5000" retries="3"/>`

- **幂等性概念**: 在编程中.一个幂等操作的特点是其任意多次执行所产生的影响均与一次执行的影响相同。幂等函数，或幂等方法，是指可以使用相同参数重复执行，并能获得相同结果的函数。这些函数不会影响系统状态，也不用担心重复执行会对系统造成改变。

- [高并发下接口幂等性解决方案 - Ruthless - 博客园](https://www.cnblogs.com/linjiqin/p/9678022.html)



### 3.4 多版本（灰度发布）

[multi-versions](https://dubbo.apache.org/zh-cn/docs/user/demos/multi-versions.html)

- 当一个接口实现，出现不兼容升级时，可以用版本号过渡，版本号不同的服务相互间不引用。可以按照以下的步骤进行版本迁移：
  
  0. 在低压力时间段，先升级一半提供者为新版本
  1. 再将所有消费者升级为新版本
  2. 然后将剩下的一半提供者升级为新版本



### 3.5 本地存根

[local-stub](https://dubbo.apache.org/zh-cn/docs/user/demos/local-stub.html)

[什么是桩代码（Stub）？ - 知乎](https://www.zhihu.com/question/24844900)



- 远程服务后，客户端通常只剩下接口，而实现全在服务器端，但**提供方有些时候想在客户端也执行部分逻辑**，比如：做 ThreadLocal 缓存，提前验证参数，调用失败后伪造容错数据等等，此时就需要在 API 中带上 Stub，客户端生成 Proxy 实例，会把 Proxy 通过构造函数传给 Stub [[1]](https://dubbo.apache.org/zh-cn/docs/user/demos/local-stub.html#fn1)，然后把 Stub 暴露给用户，Stub 可以决定要不要去调 Proxy。

- `<dubbo:service interface="com.foo.BarService" stub="com.foo.BarServiceStub" />`





### 3.6 配置覆盖关系

- 以 timeout 为例，下图显示了配置的查找顺序，其它 retries, loadbalance, actives 等类似：
  
  - 优先级总结：**方法级优先，接口级次之，全局配置再次之**。**如果级别一样，则消费方优先，提供方次之**
  
  - ![OPQ28F](https://gitee.com/pxqp9W/testmarkdown/raw/master/imgs/2020/05/OPQ28F.jpg)
  
  

## 4 Dubbo整合SpringBoot的三种方式



### 4.1 注解配置

- [注解配置](https://dubbo.apache.org/zh-cn/docs/user/configuration/annotation.html)

- 1、导入dubbo-starter

- 2、在application.yml总配置属性

- 3、使用@Service在生产者中暴露服务

- 4、使用@Reference在消费者中来引用服务

- 5、在主启动类要上@EnableDubbo



> 在注解中可以配置相关设置



### 4.2 XMl配置

- [xml配置](https://dubbo.apache.org/zh-cn/docs/user/configuration/xml.html)

- 1、导入dubbo-starter

- 2、编写provider.xml和consumer.xml配置文件来替代application.yaml

- 3、在主启动类上`@ImportResource(locations="classpath:provider.xml")`来导入XML配置文件



### 4.3 使用注解API

- [api](https://dubbo.apache.org/zh-cn/docs/user/configuration/api.html)

- 将每一个组建手动创建到容器中：

```java
@Configuration
public class MyDubboConfig {
	
	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("boot-user-service-provider");
		return applicationConfig;
	}
	
	//<dubbo:registry protocol="zookeeper" address="127.0.0.1:2181"></dubbo:registry>
	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setProtocol("zookeeper");
		registryConfig.setAddress("127.0.0.1:2181");
		return registryConfig;
	}
	
	//<dubbo:protocol name="dubbo" port="20882"></dubbo:protocol>
	@Bean
	public ProtocolConfig protocolConfig() {
		ProtocolConfig protocolConfig = new ProtocolConfig();
		protocolConfig.setName("dubbo");
		protocolConfig.setPort(20882);
		return protocolConfig;
	}
	
	/**
	 *<dubbo:service interface="com.atguigu.gmall.service.UserService" 
		ref="userServiceImpl01" timeout="1000" version="1.0.0">
		<dubbo:method name="getUserAddressList" timeout="1000"></dubbo:method>
	</dubbo:service>
	 */
	@Bean
	public ServiceConfig<UserService> userServiceConfig(UserService userService){
		ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();
		serviceConfig.setInterface(UserService.class);
		serviceConfig.setRef(userService);
		serviceConfig.setVersion("1.0.0");
		
		//配置每一个method的信息
		MethodConfig methodConfig = new MethodConfig();
		methodConfig.setName("getUserAddressList");
		methodConfig.setTimeout(1000);
		
		//将method的设置关联到service配置中
		List<MethodConfig> methods = new ArrayList<>();
		methods.add(methodConfig);
		serviceConfig.setMethods(methods);
		
		//ProviderConfig
		//MonitorConfig
		
		return serviceConfig;
	}
}
```

- 在主启动类上设置扫描包的路径：`@EnableDubbo(scanBasePackages="com.me.xxx")`
  
  









## 参考资料

- [尚硅谷Dubbo教程(dubbo经典之作)_哔哩哔哩 (゜-゜)つロ 干杯~-bilibili](https://www.bilibili.com/video/BV1ns411c7jV?from=search&seid=5064141967898877577)

- [Dubbo 中文文档](http://dubbo.apache.org/zh-cn/docs/user/new-features-in-a-glance.html)

- [Home · apache/dubbo Wiki](https://github.com/apache/dubbo/wiki)

- [ORM 实例教程 - 阮一峰的网络日志](https://www.ruanyifeng.com/blog/2019/02/orm-tutorial.html)

- [软负载和硬负载 - 晨猫 - OSCHINA](https://my.oschina.net/mengzhang6/blog/1635069)

- [mac启动zookeeper单机模式 - 简书](https://www.jianshu.com/p/b889b86536be)

- [dubbo xml配置](https://dubbo.apache.org/zh-cn/docs/user/configuration/xml.html)

- [dubbo properties配置](https://dubbo.apache.org/zh-cn/docs/user/configuration/properties.html)

- [分布式高并发系统如何保证对外接口的幂等性？ - 知乎](https://www.zhihu.com/question/27744795)

- [高并发下接口幂等性解决方案 - Ruthless - 博客园](https://www.cnblogs.com/linjiqin/p/9678022.html)

- [什么是桩代码（Stub）？ - 知乎](https://www.zhihu.com/question/24844900)
  
  