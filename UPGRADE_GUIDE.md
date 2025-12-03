# 升级指南 - 从前端应用到完整系统

## 概述

本项目已从纯前端应用升级为完整的前后端分离系统，新增了Spring Boot后端和MySQL数据库支持。

## 主要变更

### 原始状态
- ✅ Vue 3 前端应用
- ✅ LocalStorage 数据存储
- ✅ 基本的风险管理功能

### 升级后
- ✅ Vue 3 前端应用（保留）
- ✅ **新增** Spring Boot 3.1.5 后端
- ✅ **新增** MySQL 8.0 数据库
- ✅ **新增** 完整的生产安全、消防安全、环保安全、园区安全模块
- ✅ **新增** RESTful API 接口
- ✅ **新增** 多层架构（Controller → Service → Repository → Entity）

## 新增功能模块

### 1. 生产安全 (Production Safety)
- 操作安全管理
- 特种作业管理（登高、叉车、有限空间）
- 工伤管理和统计

### 2. 消防安全 (Fire Safety)
- 消防设施管理
- 动火作业许可证
- 消防演练记录

### 3. 环保安全 (Environmental Safety)
- 废水处理监测
- 废气排放监测
- 危险废物管理
- 危险化学品库存管理

### 4. 园区安全 (Campus Safety)
- 门禁系统管理
- 门禁日志记录
- 监控系统管理
- 监控事件日志

## 新增的16个数据库表

### 生产安全
- `operation_safety` - 操作安全规程
- `special_operation` - 特种作业记录
- `work_injury` - 工伤记录

### 消防安全
- `fire_control` - 消防设施
- `hot_work_permit` - 动火作业许可证
- `fire_drill` - 消防演练

### 环保安全
- `waste_water` - 废水处理
- `waste_gas` - 废气排放
- `hazardous_waste` - 危险废物
- `hazardous_chemical` - 危险化学品

### 园区安全
- `access_control` - 门禁设备
- `access_log` - 门禁日志
- `surveillance` - 监控摄像头
- `surveillance_log` - 监控日志

## 项目目录结构

```
factory-safety-management/
├── frontend files (Vue 3 application)
│   ├── src/
│   ├── index.html
│   ├── package.json
│   └── ...
│
├── backend/                              # 新增
│   ├── src/main/java/com/factory/safety/
│   │   ├── SafetyManagementApplication.java      # 主应用程序
│   │   ├── common/                                # 公共工具类
│   │   ├── config/                                # 配置类
│   │   ├── controller/                            # REST 控制器
│   │   │   ├── production/
│   │   │   ├── fire/
│   │   │   ├── environmental/
│   │   │   └── campus/
│   │   ├── service/                               # 业务逻辑层
│   │   ├── repository/                            # 数据访问层
│   │   ├── entity/                                # 数据库实体
│   │   └── dto/                                   # 数据传输对象
│   ├── src/main/resources/
│   │   ├── application.yml                        # Spring Boot 配置
│   │   └── init-db.sql                           # 数据库初始化脚本
│   ├── pom.xml                                    # Maven 配置
│   ├── .gitignore
│   └── README.md
│
├── ARCHITECTURE.md                       # 新增
├── UPGRADE_GUIDE.md                      # 新增（本文件）
└── ...
```

## 迁移步骤

### 步骤 1: 数据库设置

1. **安装 MySQL**
```bash
# macOS (使用 Homebrew)
brew install mysql

# Ubuntu/Debian
sudo apt-get install mysql-server

# Windows 
# 下载安装程序: https://dev.mysql.com/downloads/mysql/
```

2. **启动 MySQL 服务**
```bash
# macOS
brew services start mysql

# Ubuntu/Debian
sudo systemctl start mysql

# Windows
# 使用 MySQL Installer 或 MySQL Shell
```

3. **创建数据库**
```bash
# 登录 MySQL
mysql -u root -p

# 在 MySQL 命令行中执行
CREATE DATABASE safety_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

4. **导入初始化脚本（可选）**
```bash
mysql -u root -p safety_management < backend/src/main/resources/init-db.sql
```

### 步骤 2: 后端配置

1. **修改数据库连接配置**

编辑 `backend/src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/safety_management
    username: root          # 修改为你的 MySQL 用户名
    password: root          # 修改为你的 MySQL 密码
```

2. **编译后端项目**
```bash
cd backend
mvn clean compile
```

### 步骤 3: 启动应用

**终端 1 - 启动后端服务**
```bash
cd backend
mvn spring-boot:run
# 服务将在 http://localhost:8080 启动
# API 端点将在 http://localhost:8080/api 可用
```

**终端 2 - 启动前端开发服务器**
```bash
npm install
npm run dev
# 应用将在 http://localhost:5173 打开
```

### 步骤 4: 验证

1. **检查后端健康状态**
```bash
curl http://localhost:8080/api/v1/production/operation-safety
```

2. **访问前端应用**
打开浏览器访问 `http://localhost:5173`

## API 使用示例

### 获取所有操作安全记录
```bash
curl http://localhost:8080/api/v1/production/operation-safety
```

### 创建新的操作安全记录
```bash
curl -X POST http://localhost:8080/api/v1/production/operation-safety \
  -H "Content-Type: application/json" \
  -d '{
    "operationName": "焊接操作",
    "procedureContent": "按照标准焊接规程进行操作",
    "safetyLevel": "high",
    "department": "生产部",
    "owner": "张三",
    "riskPoints": "高温烫伤、电击",
    "controlMeasures": "配备防护装备、定期检查设备",
    "status": "active"
  }'
```

### 获取消防设施列表
```bash
curl http://localhost:8080/api/v1/fire/fire-control
```

### 获取废水处理记录
```bash
curl http://localhost:8080/api/v1/environmental/waste-water
```

### 获取门禁设备列表
```bash
curl http://localhost:8080/api/v1/campus/access-control
```

## 前端集成

### 更新 API 服务

原始的 localStorage 调用需要被替换为 API 调用。

**示例：从 LocalStorage 迁移到 API**

**原始代码（LocalStorage）:**
```javascript
// stores/riskStore.js
const risks = JSON.parse(localStorage.getItem('risks') || '[]');
```

**新代码（API）:**
```javascript
// services/api.js
import axios from 'axios'

const API_BASE_URL = 'http://localhost:8080/api'

export const operationSafetyApi = {
  getAll() {
    return axios.get(`${API_BASE_URL}/v1/production/operation-safety`)
  },
  getById(id) {
    return axios.get(`${API_BASE_URL}/v1/production/operation-safety/${id}`)
  },
  create(data) {
    return axios.post(`${API_BASE_URL}/v1/production/operation-safety`, data)
  },
  update(id, data) {
    return axios.put(`${API_BASE_URL}/v1/production/operation-safety/${id}`, data)
  },
  delete(id) {
    return axios.delete(`${API_BASE_URL}/v1/production/operation-safety/${id}`)
  }
}
```

## 常见问题

### Q: 如何更改 MySQL 密码？
```bash
mysql -u root -p
ALTER USER 'root'@'localhost' IDENTIFIED BY 'newpassword';
FLUSH PRIVILEGES;
```

### Q: 后端启动失败怎么办？
- 检查 MySQL 是否运行：`mysql -u root -p`
- 检查 application.yml 中的数据库配置
- 查看日志输出获取详细错误信息

### Q: 如何重置数据库？
```bash
mysql -u root -p
DROP DATABASE safety_management;
CREATE DATABASE safety_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### Q: 如何扩展新的安全模块？
遵循现有模块的三层架构：
1. 创建 Entity 类
2. 创建 Repository 接口
3. 创建 Service 类
4. 创建 Controller 类
5. 在前端创建相应的视图和 API 调用

## 性能优化建议

1. **数据库优化**
   - 添加适当的索引（已包含在初始化脚本中）
   - 定期清理过期数据

2. **API 优化**
   - 实现分页查询
   - 添加缓存层（Redis）
   - 使用数据库连接池

3. **前端优化**
   - 实现虚拟滚动
   - 使用图片懒加载
   - 优化包大小

## 安全性建议

1. **立即实施**
   - 修改 MySQL 默认密码
   - 使用环境变量存储敏感信息
   - 启用 HTTPS

2. **进一步安全加固**
   - 实现 JWT 认证
   - 添加 RBAC（角色权限管理）
   - 实现审计日志
   - 对敏感数据加密

## 部署到生产环境

### 前端部署
```bash
npm run build
# 将 dist 目录部署到 Web 服务器
```

### 后端部署
```bash
mvn clean package
# 将 target/safety-management-system-1.0.0.jar 部署到服务器
java -jar safety-management-system-1.0.0.jar
```

## 备份策略

### 数据库备份
```bash
# 备份数据库
mysqldump -u root -p safety_management > safety_management_backup.sql

# 还原数据库
mysql -u root -p safety_management < safety_management_backup.sql
```

## 支持和反馈

如遇到任何问题，请：
1. 查看日志文件
2. 检查配置文件
3. 参考 ARCHITECTURE.md 文档
4. 联系系统管理员

## 版本历史

### v1.0.0 (2024-01)
- 初始发布
- 基础前端应用

### v2.0.0 (2024-当前)
- 添加 Spring Boot 后端
- 添加 MySQL 数据库
- 新增4个主要安全模块
- 新增16个数据库表
- 新增 RESTful API

---

**祝贺！** 您现在拥有一个完整的企业级工厂安全管理系统！
