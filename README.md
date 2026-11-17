# 蚂蚁项目管理（AntCloud）

一套基于 Spring Cloud Alibaba 的微服务项目管理平台，覆盖**项目管理、任务协同、审批流程、系统权限**四个核心域，
前端 Vue 2 + Element UI，后端 Spring Cloud 微服务 + Flowable 工作流引擎，支持分布式事务与消息驱动。

> 后端包名 `com.antcloud`，各业务库统一以 `antcloud-` 前缀命名。

## 技术栈

| 分类 | 组件 | 版本 |
| --- | --- | --- |
| 基础框架 | Spring Boot / Spring Cloud / Spring Cloud Alibaba | 2.7.18 / 2021.0.8 / 2021.0.5.0 |
| 注册与配置 | Nacos | 2.2.3 |
| 网关 | Spring Cloud Gateway | 随 Spring Cloud |
| 服务调用 | OpenFeign + LoadBalancer | 随 Spring Cloud |
| 熔断限流 | Sentinel | 随 SCA |
| 分布式事务 | Seata（AT 模式） | 1.6.1 |
| 消息队列 | RocketMQ | 5.1.0（客户端 5.0.4） |
| 缓存 | Redis + Redisson | 6.x |
| 数据库 | MySQL | 8.0 |
| 持久层 | MyBatis + Druid | 2.2.2 / 1.2.20 |
| 工作流 | Flowable | 随 antcloud-workflow |
| 接口文档 | Knife4j + Swagger | 3.0.3 |
| 前端 | Vue 2 + Element UI + webpack 4 | — |

## 模块说明

| 模块 | 说明 | 端口 |
| --- | --- | --- |
| `antcloud-gateway` | 统一网关，鉴权、路由、限流 | 6880 |
| `antcloud-auth` | 登录认证、令牌签发 | 6800 |
| `antcloud-modules/antcloud-system` | 用户、角色、菜单、部门、字典、日志 | 6801 |
| `antcloud-modules/antcloud-gen` | 代码生成器 | 6802 |
| `antcloud-modules/antcloud-job` | 定时任务（Quartz） | 6803 |
| `antcloud-modules/antcloud-project` | 项目、阶段、任务、成员、文件 | 6806 |
| `antcloud-modules/antcloud-workflow` | 流程定义、审批、待办 | 6808 |
| `antcloud-monitor` | Spring Boot Admin 监控 | 6888 |
| `antcloud-api` | 各服务的 Feign 接口与 DTO | — |
| `antcloud-base` | 公共能力：core / security / datasource / seata / swagger / notice / sensitive | — |
| `antcloud-ui` | 前端工程（Vue 2） | 8080 |

## 环境要求

- JDK 8+（当前在 JDK 21 上编译运行验证通过）
- Maven 3.6+
- MySQL 8.0、Redis 6+、Nacos 2.2.3、RocketMQ 5.x、Seata 1.6.1
- Node.js 16+（前端；Node 17+ 需 `--openssl-legacy-provider`）

## 本地启动

1. **初始化数据库**：依次执行 `sql/` 下的 `antcloud-system.sql`、`antcloud-project.sql`、
   `antcloud-workflow.sql`、`antcloud-gen.sql`，以及 `antcloud_seata.sql`、`antcloud_nacos.sql`。
2. **启动中间件**：Nacos（8848）、Redis（6379）、RocketMQ（9876/10911）、MySQL（3306）、
   Seata（8091/7091，注册到 Nacos 的 `SEATA_GROUP`）。
3. **确认配置**：Nacos 中每个服务对应的 `antcloud-xxx-dev.yml` 里，数据源、Redis 地址、
   Seata 事务组需要与你本地环境一致。
4. **启动后端**：依次启动 `Auth → System → Project → Workflow → Gateway`，可用
   `mvn -o clean install -DskipTests` 构建，或在 IDE 中直接运行各模块的 `*Application`。
5. **启动前端**：

   ```bash
   cd antcloud-ui
   npm install
   npm run dev
   ```

   开发代理 `/dev-api` 指向网关 `http://127.0.0.1:6880`。
6. 浏览器访问 `http://localhost:8080`，默认管理员 `admin / 123456`。

## 目录结构

```
antCloud
├── antcloud-api          对外 Feign 接口与共享 DTO
├── antcloud-auth         认证服务
├── antcloud-base         公共基础能力（多模块）
├── antcloud-gateway      网关
├── antcloud-modules      业务服务（system / gen / job / project / workflow）
├── antcloud-monitor      监控中心
├── antcloud-ui           前端工程
├── docker                docker-compose 与中间件配置
├── docs                  环境安装、部署、开发约定
└── sql                   各库初始化脚本与 Nacos 配置种子
```

## 开发约定

见 [docs/约定.md](docs/约定.md)：包结构（controller / service / mapper / domain）、
接口返回体 `AjaxResult` / `R`、异常统一由 `GlobalExceptionHandler` 处理、
新增业务表后先写 SQL 再用代码生成器生成基础 CRUD。

## 许可

MIT，详见 [LICENSE](LICENSE)。
