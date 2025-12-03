# Factory Safety Management System - 完整架构文档

## 项目概述

这是一个完整的工厂安全管理系统，包括前端和后端两部分：

- **前端**: Vue 3 + Pinia (TypeScript ready)
- **后端**: Spring Boot 3.1.5 + MySQL 8.0

## 系统架构

```
┌─────────────────────────────────────────────────────────────┐
│                     Frontend (Vue 3)                        │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  Components:                                         │   │
│  │  - Dashboard, RiskManagement, IncidentTracking      │   │
│  │  - AssetManagement, SafetyAudit, Reports, Settings  │   │
│  └──────────────────────────────────────────────────────┘   │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  State Management (Pinia)                            │   │
│  │  - Risk Store with all data logic                    │   │
│  └──────────────────────────────────────────────────────┘   │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  API Service Layer                                   │   │
│  │  - Axios/Fetch to Backend API                        │   │
│  └──────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                          ↕ HTTP/REST
┌─────────────────────────────────────────────────────────────┐
│                   Backend (Spring Boot)                     │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  Controller Layer                                    │   │
│  │  - REST Endpoints for each module                    │   │
│  └──────────────────────────────────────────────────────┘   │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  Service Layer                                       │   │
│  │  - Business logic implementation                     │   │
│  └──────────────────────────────────────────────────────┘   │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  Repository Layer                                    │   │
│  │  - JPA queries and database operations               │   │
│  └──────────────────────────────────────────────────────┘   │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  Entity Layer                                        │   │
│  │  - Database model definitions                        │   │
│  └──────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                          ↕ JDBC
┌─────────────────────────────────────────────────────────────┐
│                   MySQL Database                            │
│  - 16 tables for safety management                          │
│  - Character set: utf8mb4                                   │
│  - InnoDB engine for transactions                           │
└─────────────────────────────────────────────────────────────┘
```

## 功能模块

### 1. 生产安全 (Production Safety)

#### 操作安全 (Operation Safety)
- 操作规程管理
- 风险点识别
- 控制措施制定
- 最后复审记录

#### 特种作业 (Special Operation)
- 登高作业、叉车作业、有限空间作业
- 操作证书管理
- 培训计划管理
- 证书过期提醒

#### 工伤管理 (Work Injury)
- 工伤报告
- 伤情分类
- 根本原因分析
- 预防措施制定

### 2. 消防安全 (Fire Safety)

#### 消防控制 (Fire Control)
- 消防设施清单
- 维护计划
- 设施状态监控

#### 动火作业 (Hot Work Permit)
- 动火作业许可证
- 风险评估
- 安全措施审核
- 作业批准流程

#### 消防演练 (Fire Drill)
- 演练计划
- 演练记录
- 发现问题跟踪
- 改进措施制定

### 3. 环保安全 (Environmental Safety)

#### 废水 (Waste Water)
- 污水处理记录
- 水质监测
- 排放合规性检查
- 处理过程跟踪

#### 废气 (Waste Gas)
- 废气排放监测
- 处理方法
- 污染物浓度测量
- 合规性验证

#### 危险废物 (Hazardous Waste)
- 危废分类
- 入库出库记录
- 存储监控
- 处置跟踪

#### 危险化学品 (Hazardous Chemical)
- 化学品库存
- 存储条件管理
- 检验计划
- 应急预案

### 4. 园区安全 (Campus Safety)

#### 门禁控制 (Access Control)
- 门禁设备管理
- 设备维护计划
- 设备状态监控

#### 门禁日志 (Access Log)
- 访客进出记录
- 部门人员管理
- 访问权限跟踪

#### 监控系统 (Surveillance)
- 摄像头设备管理
- 录像保留管理
- 维护计划
- 设备状态

#### 监控日志 (Surveillance Log)
- 监控事件记录
- 事件分类
- 严重程度等级
- 处理状态跟踪

## 数据库表结构

### 生产安全表

| 表名 | 用途 | 主要字段 |
|------|------|--------|
| operation_safety | 操作安全规程 | operation_name, procedure_content, safety_level, department, owner, risk_points, control_measures |
| special_operation | 特种作业记录 | operation_type, operator_name, certificate_number, certificate_expiry_date, training_dates |
| work_injury | 工伤记录 | injury_type, affected_employee, incident_date, severity, treatment, hospital |

### 消防安全表

| 表名 | 用途 | 主要字段 |
|------|------|--------|
| fire_control | 消防设施 | facility_name, facility_type, location, maintenance_dates |
| hot_work_permit | 动火许可证 | permit_number, work_date, work_type, location, approvals |
| fire_drill | 消防演练 | drill_name, drill_date, drill_type, participant_count |

### 环保安全表

| 表名 | 用途 | 主要字段 |
|------|------|--------|
| waste_water | 废水记录 | record_date, source, treatment_process, parameters (pH, COD, etc) |
| waste_gas | 废气记录 | record_date, emission_source, treatment_method, pollutants |
| hazardous_waste | 危废记录 | waste_name, waste_code, quantity, storage_location, disposal |
| hazardous_chemical | 危化品 | chemical_name, formula, classification, stock, inspection_dates |

### 园区安全表

| 表名 | 用途 | 主要字段 |
|------|------|--------|
| access_control | 门禁设备 | device_name, location, device_type, maintenance_dates |
| access_log | 门禁日志 | visitor_name, access_point, access_time, access_type |
| surveillance | 监控设备 | camera_name, location, camera_type, recording_status |
| surveillance_log | 监控日志 | camera_name, event_time, event_type, severity |

## API 端点设计

### URL 结构
```
/api/v1/{module}/{resource}
```

### 模块代码
- `production` - 生产安全
- `fire` - 消防安全
- `environmental` - 环保安全
- `campus` - 园区安全

### 资源代码
- `operation-safety` - 操作安全
- `special-operation` - 特种作业
- `work-injury` - 工伤管理
- `fire-control` - 消防控制
- `hot-work-permit` - 动火作业
- `fire-drill` - 消防演练
- `waste-water` - 废水
- `waste-gas` - 废气
- `hazardous-waste` - 危废
- `hazardous-chemical` - 危化品
- `access-control` - 门禁控制
- `access-log` - 门禁日志
- `surveillance` - 监控系统
- `surveillance-log` - 监控日志

### API 操作
- `GET /` - 获取所有记录
- `GET /{id}` - 获取单个记录
- `POST /` - 创建新记录
- `PUT /{id}` - 更新记录
- `DELETE /{id}` - 删除记录
- `GET /search/{type}/{keyword}` - 搜索

### 响应格式
```json
{
  "code": 200,
  "message": "Operation successful",
  "data": {}
}
```

## 开发流程

### 前端开发

1. 安装依赖
```bash
npm install
```

2. 启动开发服务器
```bash
npm run dev
```

3. 构建生产版本
```bash
npm run build
```

### 后端开发

1. 安装依赖
```bash
cd backend
mvn clean install
```

2. 启动开发服务器
```bash
mvn spring-boot:run
```

3. 构建生产版本
```bash
mvn clean package
```

## 安全考虑

1. **数据验证**
   - 前端字段验证
   - 后端数据验证
   - 数据库约束

2. **访问控制**
   - 启用 CORS (已配置)
   - API 认证 (可扩展)
   - 权限验证 (可扩展)

3. **数据保护**
   - 加密敏感数据
   - 日志记录
   - 备份策略

4. **错误处理**
   - 全局异常处理
   - 统一错误响应
   - 详细日志记录

## 部署指南

### 前端部署
1. 构建生产版本：`npm run build`
2. 输出在 `dist` 目录
3. 部署到 Web 服务器（Nginx、Apache 等）

### 后端部署
1. 构建 JAR 包：`mvn clean package`
2. 输出在 `backend/target` 目录
3. 运行 JAR：`java -jar safety-management-system-1.0.0.jar`
4. 配置环境变量用于数据库连接

## 扩展性

系统设计支持以下扩展：

1. **新模块添加**
   - 按照现有模块结构添加新实体
   - 遵循三层架构

2. **新功能添加**
   - 在现有模块中扩展Service和Controller
   - 添加相应的DTO和Entity

3. **性能优化**
   - 数据库索引优化
   - 缓存层添加（Redis）
   - API 响应分页

4. **安全增强**
   - 添加身份认证（JWT）
   - 角色权限管理（RBAC）
   - 审计日志记录

## 版本信息

- 项目版本: 1.0.0
- Java: 17+
- Spring Boot: 3.1.5
- Vue: 3.3.0+
- MySQL: 8.0+

## 许可证

MIT

## 联系方式

如有任何问题或建议，请联系系统管理员。
