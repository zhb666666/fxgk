# Factory Safety Management System - Backend

Spring Boot后端服务，用于工厂安全管理系统。

## 功能模块

### 1. 生产安全 (Production Safety)
- **操作安全** (Operation Safety) - 操作规程和安全管理
- **特种作业** (Special Operation) - 登高作业、叉车作业、有限空间作业
- **工伤管理** (Work Injury) - 工伤报告和统计

### 2. 消防安全 (Fire Safety)
- **消防控制** (Fire Control) - 消防设施管理
- **动火作业** (Hot Work Permit) - 动火作业许可证
- **消防演练** (Fire Drill) - 消防演练记录

### 3. 环保安全 (Environmental Safety)
- **废水** (Waste Water) - 废水处理记录
- **废气** (Waste Gas) - 废气排放监测
- **危废** (Hazardous Waste) - 危险废物管理
- **危化品** (Hazardous Chemical) - 危险化学品管理

### 4. 园区安全 (Campus Safety)
- **门禁控制** (Access Control) - 门禁设备管理
- **门禁日志** (Access Log) - 访问日志记录
- **监控系统** (Surveillance) - 摄像头管理
- **监控日志** (Surveillance Log) - 监控事件记录

## 技术栈

- **Java 17**
- **Spring Boot 3.1.5**
- **Spring Data JPA**
- **MySQL 8.0**
- **Lombok**
- **Maven**

## 项目结构

```
backend/
├── src/main/java/com/factory/safety/
│   ├── SafetyManagementApplication.java      # 主应用程序
│   ├── common/                               # 公共类
│   │   ├── ApiResponse.java                  # 统一响应格式
│   │   ├── GlobalExceptionHandler.java       # 全局异常处理
│   │   └── ResourceNotFoundException.java    # 自定义异常
│   ├── config/                               # 配置类
│   │   └── CorsConfig.java                   # CORS配置
│   ├── controller/                           # 控制器层
│   │   ├── production/                       # 生产安全控制器
│   │   ├── fire/                            # 消防安全控制器
│   │   ├── environmental/                    # 环保安全控制器
│   │   └── campus/                          # 园区安全控制器
│   ├── service/                              # 业务层
│   │   ├── production/                       # 生产安全服务
│   │   ├── fire/                            # 消防安全服务
│   │   ├── environmental/                    # 环保安全服务
│   │   └── campus/                          # 园区安全服务
│   ├── repository/                           # 数据访问层
│   │   ├── production/                       # 生产安全仓储
│   │   ├── fire/                            # 消防安全仓储
│   │   ├── environmental/                    # 环保安全仓储
│   │   └── campus/                          # 园区安全仓储
│   ├── entity/                               # 实体类
│   │   ├── production/                       # 生产安全实体
│   │   ├── fire/                            # 消防安全实体
│   │   ├── environmental/                    # 环保安全实体
│   │   └── campus/                          # 园区安全实体
│   └── dto/                                  # 数据传输对象
│       ├── production/                       # 生产安全DTO
│       ├── fire/                            # 消防安全DTO
│       ├── environmental/                    # 环保安全DTO
│       └── campus/                          # 园区安全DTO
└── src/main/resources/
    └── application.yml                       # 应用配置
```

## API 文档

### Base URL
```
http://localhost:8080/api
```

### 生产安全 API

#### 操作安全
- `GET /v1/production/operation-safety` - 获取所有操作安全记录
- `GET /v1/production/operation-safety/{id}` - 获取单个操作安全记录
- `POST /v1/production/operation-safety` - 创建操作安全记录
- `PUT /v1/production/operation-safety/{id}` - 更新操作安全记录
- `DELETE /v1/production/operation-safety/{id}` - 删除操作安全记录
- `GET /v1/production/operation-safety/search/department/{department}` - 按部门搜索
- `GET /v1/production/operation-safety/search/status/{status}` - 按状态搜索
- `GET /v1/production/operation-safety/search/keyword/{keyword}` - 按关键字搜索

### 消防安全 API

#### 消防控制
- `GET /v1/fire/fire-control` - 获取所有消防控制记录
- `GET /v1/fire/fire-control/{id}` - 获取单个消防控制记录
- `POST /v1/fire/fire-control` - 创建消防控制记录
- `PUT /v1/fire/fire-control/{id}` - 更新消防控制记录
- `DELETE /v1/fire/fire-control/{id}` - 删除消防控制记录

### 环保安全 API

#### 废水
- `GET /v1/environmental/waste-water` - 获取所有废水记录
- `GET /v1/environmental/waste-water/{id}` - 获取单个废水记录
- `POST /v1/environmental/waste-water` - 创建废水记录
- `PUT /v1/environmental/waste-water/{id}` - 更新废水记录
- `DELETE /v1/environmental/waste-water/{id}` - 删除废水记录

### 园区安全 API

#### 门禁控制
- `GET /v1/campus/access-control` - 获取所有门禁控制记录
- `GET /v1/campus/access-control/{id}` - 获取单个门禁控制记录
- `POST /v1/campus/access-control` - 创建门禁控制记录
- `PUT /v1/campus/access-control/{id}` - 更新门禁控制记录
- `DELETE /v1/campus/access-control/{id}` - 删除门禁控制记录

## 启动方式

### 前置条件
- Java 17+
- Maven 3.6+
- MySQL 8.0+

### 安装步骤

1. **创建数据库**
```sql
CREATE DATABASE safety_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. **修改数据库连接配置**
编辑 `src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/safety_management?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: root  # 修改为你的MySQL用户名
    password: root  # 修改为你的MySQL密码
```

3. **编译项目**
```bash
cd backend
mvn clean compile
```

4. **运行项目**
```bash
mvn spring-boot:run
```

5. **验证服务**
访问 `http://localhost:8080/api/v1/production/operation-safety`

## 数据库设计

### 表结构

#### 生产安全
- `operation_safety` - 操作安全规程
- `special_operation` - 特种作业记录
- `work_injury` - 工伤记录

#### 消防安全
- `fire_control` - 消防设施
- `hot_work_permit` - 动火作业许可证
- `fire_drill` - 消防演练

#### 环保安全
- `waste_water` - 废水处理记录
- `waste_gas` - 废气排放记录
- `hazardous_waste` - 危险废物
- `hazardous_chemical` - 危险化学品

#### 园区安全
- `access_control` - 门禁设备
- `access_log` - 门禁日志
- `surveillance` - 监控摄像头
- `surveillance_log` - 监控日志

## 响应格式

### 成功响应
```json
{
  "code": 200,
  "message": "Success",
  "data": {}
}
```

### 错误响应
```json
{
  "code": 400,
  "message": "Error message",
  "data": null
}
```

## 开发指南

### 添加新的模块

1. **创建Entity类**
   - 在 `entity/{module}/` 目录创建实体类
   - 添加 `@Entity`, `@Table`, 和必要的字段注解

2. **创建Repository接口**
   - 在 `repository/{module}/` 目录创建Repository接口
   - 继承 `JpaRepository<Entity, Long>`

3. **创建DTO类**
   - 在 `dto/{module}/` 目录创建DTO类
   - 使用 `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`

4. **创建Service类**
   - 在 `service/{module}/` 目录创建Service类
   - 实现CRUD操作和业务逻辑

5. **创建Controller类**
   - 在 `controller/{module}/` 目录创建Controller类
   - 定义REST API端点

### 命名规范
- Package: `com.factory.safety.{layer}.{module}`
- Entity: `{Name}Entity`
- Repository: `{Name}Repository`
- Service: `{Name}Service`
- Controller: `{Name}Controller`
- DTO: `{Name}DTO`

## 常见问题

### 连接数据库失败
- 检查MySQL服务是否运行
- 检查数据库用户名和密码
- 确保数据库已创建

### 表自动创建失败
- 确保 `application.yml` 中的 `hibernate.ddl-auto` 设置为 `update` 或 `create`
- 检查数据库权限

### CORS 错误
- CORS 已在 `CorsConfig` 中配置，允许所有来源的请求
- 如需限制来源，编辑 `CorsConfig.java`

## 许可证

MIT

## 联系方式

如有问题或建议，请联系系统管理员。
