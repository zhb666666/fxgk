<template>
  <div class="container">
    <aside class="sidebar">
      <div class="logo">
        <h1>企业风险管控</h1>
      </div>
      <nav class="menu">
        <router-link
          v-for="item in menuItems"
          :key="item.path"
          :to="item.path"
          :class="['menu-item', { active: isActive(item.path) }]"
        >
          <span class="icon">{{ item.icon }}</span>
          <span class="label">{{ item.label }}</span>
        </router-link>
      </nav>
    </aside>

    <div class="main-content">
      <header class="header">
        <div class="header-left">
          <h2>{{ currentPageTitle }}</h2>
        </div>
        <div class="header-right">
          <span class="user-info">管理员</span>
          <span class="logout-btn" @click="handleLogout">退出</span>
        </div>
      </header>

      <div class="content">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

const menuItems = [
  { path: '/', label: '仪表板', icon: '📊' },
  { path: '/risks', label: '风险管理', icon: '⚠️' },
  { path: '/incidents', label: '事件追踪', icon: '📋' },
  { path: '/assets', label: '资产管理', icon: '🔧' },
  { path: '/audit', label: '安全审计', icon: '✓' },
  { path: '/reports', label: '报表分析', icon: '📈' },
  // 生产安全
  { path: '/operation-safety', label: '操作安全', icon: '🏭', category: '生产安全' },
  { path: '/special-operations', label: '特种作业', icon: '🪜', category: '生产安全' },
  { path: '/work-injuries', label: '工伤管理', icon: '🏥', category: '生产安全' },
  // 消防安全
  { path: '/fire-controls', label: '消防控制', icon: '🔴', category: '消防安全' },
  { path: '/hot-work-permits', label: '动火作业', icon: '🔥', category: '消防安全' },
  { path: '/fire-drills', label: '消防演练', icon: '🚒', category: '消防安全' },
  // 环保安全
  { path: '/waste-waters', label: '废水监测', icon: '💧', category: '环保安全' },
  { path: '/waste-gases', label: '废气监测', icon: '💨', category: '环保安全' },
  { path: '/hazardous-wastes', label: '危险废物', icon: '⚠️', category: '环保安全' },
  { path: '/hazardous-chemicals', label: '危险化学品', icon: '🧪', category: '环保安全' },
  // 园区安全
  { path: '/access-controls', label: '门禁管理', icon: '🔐', category: '园区安全' },
  { path: '/surveillances', label: '监控系统', icon: '📹', category: '园区安全' },
  { path: '/settings', label: '系统设置', icon: '⚙️' }
]

const currentPageTitle = computed(() => {
  const item = menuItems.find(m => m.path === route.path)
  return item ? item.label : '仪表板'
})

const isActive = (path) => {
  return route.path === path
}

const handleLogout = () => {
  alert('已退出登录')
  location.reload()
}
</script>

<style scoped>
.sidebar {
  background-color: #001529;
  color: white;
  padding: 0;
  display: flex;
  flex-direction: column;
  overflow-y: auto;
}

.logo {
  padding: 20px 16px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.15);
  font-size: 18px;
  font-weight: bold;
}

.logo h1 {
  font-size: 16px;
  margin: 0;
  white-space: nowrap;
}

.menu {
  flex: 1;
  list-style: none;
  padding: 12px 0;
}

.menu-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px 20px;
  color: rgba(255, 255, 255, 0.65);
  text-decoration: none;
  transition: all 0.3s ease;
  border-left: 3px solid transparent;
}

.menu-item:hover {
  color: white;
  background-color: rgba(255, 255, 255, 0.1);
}

.menu-item.active {
  color: white;
  background-color: #1890ff;
  border-left-color: #1890ff;
}

.menu-item .icon {
  font-size: 18px;
  min-width: 20px;
}

.menu-item .label {
  font-size: 14px;
  white-space: nowrap;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 24px;
  background-color: white;
  border-bottom: 1px solid #e8e8e8;
}

.header h2 {
  font-size: 18px;
  margin: 0;
  color: #262626;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 24px;
}

.user-info {
  color: #595959;
  font-size: 14px;
}

.logout-btn {
  color: #1890ff;
  cursor: pointer;
  font-size: 14px;
  transition: color 0.3s ease;
}

.logout-btn:hover {
  color: #40a9ff;
}

@media (max-width: 768px) {
  .sidebar {
    width: 180px;
  }

  .logo h1 {
    font-size: 14px;
  }

  .menu-item {
    padding: 10px 16px;
    gap: 8px;
  }

  .menu-item .icon {
    font-size: 16px;
  }

  .menu-item .label {
    font-size: 12px;
  }

  .header {
    padding: 12px 16px;
  }

  .header-right {
    gap: 16px;
  }
}
</style>
