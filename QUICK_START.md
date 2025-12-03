# 快速开始指南

## 选项 1: 本地开发模式（推荐用于开发）

### 前置要求
- Java 17+
- Maven 3.6+
- MySQL 8.0+
- Node.js 14+
- npm 6+

### 安装步骤

#### 1. 设置 MySQL 数据库

```bash
# 登录 MySQL
mysql -u root -p

# 执行以下命令
CREATE DATABASE safety_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
EXIT;
```

#### 2. 配置后端

编辑 `backend/src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/safety_management
    username: root        # 改为你的 MySQL 用户名
    password: root        # 改为你的 MySQL 密码
```

#### 3. 启动后端服务

```bash
cd backend
mvn spring-boot:run
```

输出应该显示：
```
Tomcat started on port(s): 8080 (http)
```

#### 4. 启动前端应用

打开新的终端窗口：

```bash
npm install
npm run dev
```

应用将在 `http://localhost:5173` 打开

### 验证安装

#### 后端 API 检查
```bash
curl http://localhost:8080/api/v1/production/operation-safety
```

应该返回：
```json
{
  "code": 200,
  "message": "Get all operation safety records successfully",
  "data": []
}
```

#### 前端应用检查
访问 `http://localhost:5173` 应该能看到应用界面

---

## 选项 2: Docker 容器模式（推荐用于部署）

### 前置要求
- Docker 20.10+
- Docker Compose 1.29+

### 安装步骤

#### 1. 构建后端 Docker 镜像

```bash
cd backend
mvn clean package
docker build -t safety-management-backend:1.0.0 .
```

#### 2. 启动所有服务

```bash
# 从项目根目录
docker-compose up -d
```

此命令会启动：
- MySQL 数据库 (端口 3306)
- Spring Boot 后端 (端口 8080)
- Vue 3 前端 (端口 5173)

#### 3. 查看日志

```bash
# 所有服务日志
docker-compose logs -f

# 只看后端日志
docker-compose logs -f backend

# 只看 MySQL 日志
docker-compose logs -f mysql
```

#### 4. 停止服务

```bash
docker-compose down

# 包括删除卷数据
docker-compose down -v
```

### 验证 Docker 安装

```bash
# 检查运行中的容器
docker-compose ps

# 应该显示三个运行中的服务
# CONTAINER ID   IMAGE                        STATUS
# xxxxxxx        safety_management_backend    Up 2 minutes
# xxxxxxx        mysql:8.0                    Up 2 minutes
# xxxxxxx        node:18                      Up 1 minute
```

---

## 常见问题解决

### MySQL 连接失败

**错误信息:**
```
Connection refused
```

**解决方案:**
```bash
# 检查 MySQL 是否运行
mysql -u root -p

# 如果不运行，启动 MySQL
# macOS:
brew services start mysql

# Ubuntu/Debian:
sudo systemctl start mysql

# Windows:
# 使用 Services 管理器或 MySQL Installer
```

### 后端 Maven 依赖下载失败

**解决方案:**
```bash
cd backend

# 清除本地仓库
rm -rf ~/.m2/repository

# 重新下载依赖
mvn clean install -DskipTests
```

### 前端 npm 依赖问题

**解决方案:**
```bash
# 清除缓存
npm cache clean --force

# 删除 node_modules
rm -rf node_modules package-lock.json

# 重新安装
npm install
```

### Docker 容器无法连接数据库

**解决方案:**
```bash
# 重启所有服务
docker-compose restart

# 如果仍然不工作，重建并启动
docker-compose down -v
docker-compose up -d --build
```

### 端口已被占用

**解决方案:**
```bash
# 修改 docker-compose.yml 中的端口配置
# 例如，改变前端端口：
# ports:
#   - "5174:5173"    # 改为 5174

# 或手动启动时指定不同端口
docker-compose up -d -p 5174:5173
```

---

## 下一步

### 1. 浏览 API 文档
参考 `backend/README.md` 了解所有可用的 API 端点

### 2. 了解系统架构
查看 `ARCHITECTURE.md` 了解系统设计

### 3. 开发新功能
参考 `backend/README.md` 中的"添加新模块"部分

### 4. 部署到生产

生产构建命令：
```bash
# 后端
cd backend
mvn clean package -DskipTests

# 前端
npm run build
```

---

## 有用的命令

### 数据库管理

```bash
# 备份数据库
mysqldump -u root -p safety_management > backup.sql

# 恢复数据库
mysql -u root -p safety_management < backup.sql

# 连接数据库
mysql -u root -p safety_management
```

### Maven 命令

```bash
# 编译项目
mvn clean compile

# 运行测试
mvn test

# 构建 JAR
mvn clean package

# 跳过测试进行构建
mvn clean package -DskipTests

# 查看依赖树
mvn dependency:tree
```

### npm 命令

```bash
# 安装依赖
npm install

# 开发模式
npm run dev

# 生产构建
npm run build

# 预览生产版本
npm run preview

# 代码检查（如果配置）
npm run lint
```

---

## API 示例

### 获取所有操作安全记录
```bash
curl http://localhost:8080/api/v1/production/operation-safety
```

### 创建新的操作安全记录
```bash
curl -X POST http://localhost:8080/api/v1/production/operation-safety \
  -H "Content-Type: application/json" \
  -d '{
    "operationName": "电焊作业",
    "procedureContent": "按照GB/T 3375-2008标准执行",
    "safetyLevel": "high",
    "department": "生产部",
    "owner": "李四",
    "riskPoints": "高温、电击、眼睛伤害",
    "controlMeasures": "配备焊接防护装备、定期检查设备、培训操作人员",
    "status": "active"
  }'
```

### 获取单个记录
```bash
curl http://localhost:8080/api/v1/production/operation-safety/1
```

### 更新记录
```bash
curl -X PUT http://localhost:8080/api/v1/production/operation-safety/1 \
  -H "Content-Type: application/json" \
  -d '{
    "operationName": "电焊作业（已更新）",
    "procedureContent": "按照最新安全规范执行",
    "safetyLevel": "high",
    "department": "生产部",
    "owner": "李四",
    "riskPoints": "高温、电击、眼睛伤害",
    "controlMeasures": "配备焊接防护装备、定期检查设备、培训操作人员",
    "status": "active"
  }'
```

### 删除记录
```bash
curl -X DELETE http://localhost:8080/api/v1/production/operation-safety/1
```

---

## 性能监控

### 查看日志
```bash
# 后端日志
cd backend
tail -f logs/spring.log

# Docker 容器日志
docker-compose logs -f backend
```

### 数据库查询监控
```sql
-- 登录 MySQL
mysql -u root -p safety_management

-- 查看表数据
SELECT COUNT(*) FROM operation_safety;
SELECT * FROM operation_safety LIMIT 10;
```

---

## 获取帮助

1. 查看 `README.md` - 项目概述
2. 查看 `ARCHITECTURE.md` - 系统架构
3. 查看 `backend/README.md` - 后端 API 文档
4. 查看 `UPGRADE_GUIDE.md` - 升级指南

---

## 许可证

MIT License

---

**准备好了吗？** 访问 `http://localhost:5173` 开始使用系统！
