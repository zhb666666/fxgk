import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useRiskStore = defineStore('risk', () => {
  const risks = ref([])
  const incidents = ref([])
  const assets = ref([])
  const audits = ref([])

  // Initialize with sample data
  const initializeSampleData = () => {
    risks.value = [
      {
        id: 1,
        name: '高空坠落风险',
        description: '工作人员在高处作业时坠落',
        category: '人身安全',
        level: 'high',
        probability: 0.3,
        impact: 0.9,
        status: '活跃',
        owner: '李明',
        department: '生产部',
        createdAt: new Date(2024, 0, 15),
        mitigation: '配备安全绳、安全帽、定期培训'
      },
      {
        id: 2,
        name: '机械伤害风险',
        description: '与机械设备接触导致的伤害',
        category: '机械安全',
        level: 'high',
        probability: 0.4,
        impact: 0.8,
        status: '活跃',
        owner: '王刚',
        department: '维护部',
        createdAt: new Date(2024, 0, 10),
        mitigation: '安装防护装置、定期维护检查'
      },
      {
        id: 3,
        name: '化学物质泄露',
        description: '有毒化学物质意外泄露',
        category: '环境安全',
        level: 'high',
        probability: 0.2,
        impact: 0.95,
        status: '受控',
        owner: '张华',
        department: '仓储部',
        createdAt: new Date(2024, 0, 5),
        mitigation: '安装泄露检测系统、定期检查容器'
      },
      {
        id: 4,
        name: '电气火灾风险',
        description: '电气设备故障导致火灾',
        category: '火灾安全',
        level: 'medium',
        probability: 0.15,
        impact: 0.7,
        status: '活跃',
        owner: '陈军',
        department: '电气部',
        createdAt: new Date(2024, 0, 1),
        mitigation: '定期电气检查、安装灭火器'
      },
      {
        id: 5,
        name: '噪声污染',
        description: '工作环境噪声超标',
        category: '职业健康',
        level: 'low',
        probability: 0.6,
        impact: 0.3,
        status: '活跃',
        owner: '刘英',
        department: '生产部',
        createdAt: new Date(2023, 11, 28),
        mitigation: '配备耳塞、定期听力检查'
      }
    ]

    incidents.value = [
      {
        id: 1,
        title: '员工手部伤害',
        description: '员工操作机器时手指被卡伤',
        type: '工伤事故',
        severity: 'high',
        status: '处理中',
        reportedBy: '王小红',
        reportedDate: new Date(2024, 0, 20),
        location: '生产车间A',
        affectedPersons: 1,
        resolution: '已就医治疗，进行安全培训'
      },
      {
        id: 2,
        title: '设备故障',
        description: '冲床设备突然停止工作',
        type: '设备事故',
        severity: 'medium',
        status: '已解决',
        reportedBy: '李技师',
        reportedDate: new Date(2024, 0, 18),
        location: '生产车间B',
        affectedPersons: 0,
        resolution: '更换液压管路，恢复正常运行'
      },
      {
        id: 3,
        title: '化学物质溅出',
        description: '清洁过程中化学溶液溅出',
        type: '环境事故',
        severity: 'medium',
        status: '已解决',
        reportedBy: '张清洁员',
        reportedDate: new Date(2024, 0, 15),
        location: '仓储区',
        affectedPersons: 0,
        resolution: '及时清理，受伤人员已处理'
      }
    ]

    assets.value = [
      {
        id: 1,
        name: '冲床',
        type: '机械设备',
        model: 'YH-500',
        location: '生产车间A',
        manufacturer: '华机制造',
        purchaseDate: new Date(2020, 5, 15),
        lastMaintenance: new Date(2024, 0, 10),
        nextMaintenance: new Date(2024, 2, 10),
        status: '正常',
        riskLevel: 'high',
        notes: '定期维护中'
      },
      {
        id: 2,
        name: '电焊机',
        type: '电气设备',
        model: 'WS-300',
        location: '焊接车间',
        manufacturer: '焊星电气',
        purchaseDate: new Date(2019, 8, 20),
        lastMaintenance: new Date(2024, 0, 5),
        nextMaintenance: new Date(2024, 3, 5),
        status: '正常',
        riskLevel: 'high',
        notes: '需要定期安全检查'
      },
      {
        id: 3,
        name: '化学品柜',
        type: '安全设施',
        model: 'CB-1000',
        location: '仓储区',
        manufacturer: '安全之家',
        purchaseDate: new Date(2021, 2, 10),
        lastMaintenance: new Date(2024, 0, 1),
        nextMaintenance: new Date(2024, 6, 1),
        status: '正常',
        riskLevel: 'high',
        notes: '存储有毒有害物质'
      }
    ]

    audits.value = [
      {
        id: 1,
        title: '月度安全检查',
        type: '定期检查',
        date: new Date(2024, 0, 15),
        auditor: '安全主任',
        findings: ['电气线路老化', '灭火器过期'],
        score: 85,
        status: '已完成',
        nextAudit: new Date(2024, 1, 15)
      },
      {
        id: 2,
        title: '高处作业安全评估',
        type: '专项检查',
        date: new Date(2024, 0, 10),
        auditor: '外部审计员',
        findings: ['安全绳磨损', '培训记录不完整'],
        score: 78,
        status: '已完成',
        nextAudit: new Date(2024, 3, 10)
      }
    ]
  }

  const addRisk = (risk) => {
    risk.id = Math.max(...risks.value.map(r => r.id), 0) + 1
    risks.value.push(risk)
  }

  const updateRisk = (id, updated) => {
    const index = risks.value.findIndex(r => r.id === id)
    if (index !== -1) {
      risks.value[index] = { ...risks.value[index], ...updated }
    }
  }

  const deleteRisk = (id) => {
    risks.value = risks.value.filter(r => r.id !== id)
  }

  const addIncident = (incident) => {
    incident.id = Math.max(...incidents.value.map(i => i.id), 0) + 1
    incidents.value.push(incident)
  }

  const updateIncident = (id, updated) => {
    const index = incidents.value.findIndex(i => i.id === id)
    if (index !== -1) {
      incidents.value[index] = { ...incidents.value[index], ...updated }
    }
  }

  const deleteIncident = (id) => {
    incidents.value = incidents.value.filter(i => i.id !== id)
  }

  const addAsset = (asset) => {
    asset.id = Math.max(...assets.value.map(a => a.id), 0) + 1
    assets.value.push(asset)
  }

  const updateAsset = (id, updated) => {
    const index = assets.value.findIndex(a => a.id === id)
    if (index !== -1) {
      assets.value[index] = { ...assets.value[index], ...updated }
    }
  }

  const deleteAsset = (id) => {
    assets.value = assets.value.filter(a => a.id !== id)
  }

  const addAudit = (audit) => {
    audit.id = Math.max(...audits.value.map(a => a.id), 0) + 1
    audits.value.push(audit)
  }

  const updateAudit = (id, updated) => {
    const index = audits.value.findIndex(a => a.id === id)
    if (index !== -1) {
      audits.value[index] = { ...audits.value[index], ...updated }
    }
  }

  const deleteAudit = (id) => {
    audits.value = audits.value.filter(a => a.id !== id)
  }

  // Analytics functions
  const getHighRiskCount = computed(() => {
    return risks.value.filter(r => r.level === 'high').length
  })

  const getMediumRiskCount = computed(() => {
    return risks.value.filter(r => r.level === 'medium').length
  })

  const getLowRiskCount = computed(() => {
    return risks.value.filter(r => r.level === 'low').length
  })

  const getRecentIncidents = computed(() => {
    return incidents.value.slice(-5).reverse()
  })

  const getAssetsNeedingMaintenance = computed(() => {
    return assets.value.filter(a => {
      if (!a.nextMaintenance) return false
      return new Date(a.nextMaintenance) <= new Date()
    })
  })

  return {
    risks,
    incidents,
    assets,
    audits,
    initializeSampleData,
    addRisk,
    updateRisk,
    deleteRisk,
    addIncident,
    updateIncident,
    deleteIncident,
    addAsset,
    updateAsset,
    deleteAsset,
    addAudit,
    updateAudit,
    deleteAudit,
    getHighRiskCount,
    getMediumRiskCount,
    getLowRiskCount,
    getRecentIncidents,
    getAssetsNeedingMaintenance
  }
})
