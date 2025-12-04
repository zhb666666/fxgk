import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout.vue'
import Dashboard from '../views/Dashboard.vue'
import RiskManagement from '../views/RiskManagement.vue'
import IncidentTracking from '../views/IncidentTracking.vue'
import AssetManagement from '../views/AssetManagement.vue'
import SafetyAudit from '../views/SafetyAudit.vue'
import Reports from '../views/Reports.vue'
import Settings from '../views/Settings.vue'

// 生产安全模块
import OperationSafety from '../views/safety/OperationSafety.vue'
import SpecialOperations from '../views/safety/SpecialOperations.vue'
import WorkInjuries from '../views/safety/WorkInjuries.vue'

// 消防安全模块
import FireControls from '../views/safety/FireControls.vue'
import HotWorkPermits from '../views/safety/HotWorkPermits.vue'
import FireDrills from '../views/safety/FireDrills.vue'

// 环保安全模块
import WasteWaters from '../views/safety/WasteWaters.vue'
import WasteGases from '../views/safety/WasteGases.vue'
import HazardousWastes from '../views/safety/HazardousWastes.vue'
import HazardousChemicals from '../views/safety/HazardousChemicals.vue'

// 园区安全模块
import AccessControls from '../views/safety/AccessControls.vue'
import Surveillances from '../views/safety/Surveillances.vue'

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
      },
      // 生产安全
      {
        path: 'operation-safety',
        component: OperationSafety,
        meta: { title: '操作安全', category: '生产安全' }
      },
      {
        path: 'special-operations',
        component: SpecialOperations,
        meta: { title: '特种作业', category: '生产安全' }
      },
      {
        path: 'work-injuries',
        component: WorkInjuries,
        meta: { title: '工伤管理', category: '生产安全' }
      },
      // 消防安全
      {
        path: 'fire-controls',
        component: FireControls,
        meta: { title: '消防控制', category: '消防安全' }
      },
      {
        path: 'hot-work-permits',
        component: HotWorkPermits,
        meta: { title: '动火作业', category: '消防安全' }
      },
      {
        path: 'fire-drills',
        component: FireDrills,
        meta: { title: '消防演练', category: '消防安全' }
      },
      // 环保安全
      {
        path: 'waste-waters',
        component: WasteWaters,
        meta: { title: '废水监测', category: '环保安全' }
      },
      {
        path: 'waste-gases',
        component: WasteGases,
        meta: { title: '废气监测', category: '环保安全' }
      },
      {
        path: 'hazardous-wastes',
        component: HazardousWastes,
        meta: { title: '危险废物', category: '环保安全' }
      },
      {
        path: 'hazardous-chemicals',
        component: HazardousChemicals,
        meta: { title: '危险化学品', category: '环保安全' }
      },
      // 园区安全
      {
        path: 'access-controls',
        component: AccessControls,
        meta: { title: '门禁管理', category: '园区安全' }
      },
      {
        path: 'surveillances',
        component: Surveillances,
        meta: { title: '监控系统', category: '园区安全' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
