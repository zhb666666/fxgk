import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout.vue'
import Dashboard from '../views/Dashboard.vue'
import RiskManagement from '../views/RiskManagement.vue'
import IncidentTracking from '../views/IncidentTracking.vue'
import AssetManagement from '../views/AssetManagement.vue'
import SafetyAudit from '../views/SafetyAudit.vue'
import Reports from '../views/Reports.vue'
import Settings from '../views/Settings.vue'

const routes = [
  {
    path: '/',
    component: Layout,
    children: [
      {
        path: '',
        component: Dashboard,
        meta: { title: '仪表板' }
      },
      {
        path: 'risks',
        component: RiskManagement,
        meta: { title: '风险管理' }
      },
      {
        path: 'incidents',
        component: IncidentTracking,
        meta: { title: '事件追踪' }
      },
      {
        path: 'assets',
        component: AssetManagement,
        meta: { title: '资产管理' }
      },
      {
        path: 'audit',
        component: SafetyAudit,
        meta: { title: '安全审计' }
      },
      {
        path: 'reports',
        component: Reports,
        meta: { title: '报表分析' }
      },
      {
        path: 'settings',
        component: Settings,
        meta: { title: '系统设置' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
