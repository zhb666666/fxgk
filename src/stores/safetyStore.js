import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useSafetyStore = defineStore('safety', () => {
  // 生产安全模块
  const operationSafety = ref([])
  const specialOperations = ref([])
  const workInjuries = ref([])

  // 消防安全模块
  const fireControls = ref([])
  const hotWorkPermits = ref([])
  const fireDrills = ref([])

  // 环保安全模块
  const wasteWaters = ref([])
  const wasteGases = ref([])
  const hazardousWastes = ref([])
  const hazardousChemicals = ref([])

  // 园区安全模块
  const accessControls = ref([])
  const surveillances = ref([])

  const initializeSampleData = () => {
    // 生产安全 - 操作安全
    operationSafety.value = [
      {
        id: 1,
        procedureName: '机床操作规程',
        department: '生产部',
        operationType: '机械操作',
        riskLevel: 'high',
        safetyGuidelines: '穿戴防护用品，严禁超速运行',
        trainingRequired: true,
        lastReviewed: new Date(2024, 0, 15),
        approver: '李明',
        status: '有效'
      },
      {
        id: 2,
        procedureName: '化学品使用规程',
        department: '仓储部',
        operationType: '化学处理',
        riskLevel: 'high',
        safetyGuidelines: '佩戴防护面罩，使用于通风环境',
        trainingRequired: true,
        lastReviewed: new Date(2024, 0, 10),
        approver: '王刚',
        status: '有效'
      }
    ]

    // 生产安全 - 特种作业
    specialOperations.value = [
      {
        id: 1,
        workerName: '张三',
        workType: '登高作业',
        permitNumber: 'GZ-2024-001',
        issueDate: new Date(2024, 0, 15),
        expiryDate: new Date(2024, 11, 15),
        location: '厂房三层',
        supervisor: '李明',
        status: '有效'
      },
      {
        id: 2,
        workerName: '李四',
        workType: '叉车作业',
        permitNumber: 'CJ-2024-001',
        issueDate: new Date(2024, 0, 10),
        expiryDate: new Date(2024, 11, 10),
        location: '仓库',
        supervisor: '王刚',
        status: '有效'
      },
      {
        id: 3,
        workerName: '王五',
        workType: '有限空间作业',
        permitNumber: 'YX-2024-001',
        issueDate: new Date(2024, 0, 5),
        expiryDate: new Date(2024, 11, 5),
        location: '处理池',
        supervisor: '张华',
        status: '有效'
      }
    ]

    // 生产安全 - 工伤管理
    workInjuries.value = [
      {
        id: 1,
        injuryName: '手部割伤',
        injuryDate: new Date(2024, 0, 20),
        workerName: '赵六',
        department: '生产部',
        severity: 'light',
        location: '生产车间A',
        description: '操作刀具时不小心割伤',
        treatmentStatus: '已治疗',
        approver: '李明'
      },
      {
        id: 2,
        injuryName: '烫伤',
        injuryDate: new Date(2024, 0, 18),
        workerName: '孙七',
        department: '维护部',
        severity: 'medium',
        location: '锅炉房',
        description: '接触高温管道导致烫伤',
        treatmentStatus: '治疗中',
        approver: '王刚'
      }
    ]

    // 消防安全 - 消防控制
    fireControls.value = [
      {
        id: 1,
        facilityName: '消防栓 A1',
        facilityType: '消防栓',
        location: '一楼走廊',
        installDate: new Date(2022, 0, 15),
        lastInspection: new Date(2024, 0, 10),
        nextInspection: new Date(2024, 3, 10),
        status: '正常',
        capacity: '100升',
        inspector: '李明'
      },
      {
        id: 2,
        facilityName: '灭火器 A2',
        facilityType: '灭火器',
        location: '生产车间',
        installDate: new Date(2023, 0, 15),
        lastInspection: new Date(2024, 0, 5),
        nextInspection: new Date(2024, 3, 5),
        status: '正常',
        capacity: '5kg',
        inspector: '王刚'
      }
    ]

    // 消防安全 - 动火作业
    hotWorkPermits.value = [
      {
        id: 1,
        permitNumber: 'HW-2024-001',
        workDate: new Date(2024, 0, 20),
        location: '焊接车间',
        workType: '焊接作业',
        applicant: '张师傅',
        supervisor: '李明',
        safetyMeasures: '配备灭火器，安排监护人',
        approver: '安全主任',
        status: '已批准'
      },
      {
        id: 2,
        permitNumber: 'HW-2024-002',
        workDate: new Date(2024, 0, 15),
        location: '维修部',
        workType: '切割作业',
        applicant: '王师傅',
        supervisor: '王刚',
        safetyMeasures: '清理易燃物品，配备安全防护',
        approver: '安全主任',
        status: '已完成'
      }
    ]

    // 消防安全 - 消防演练
    fireDrills.value = [
      {
        id: 1,
        drillName: '2024年一月消防演练',
        drillDate: new Date(2024, 0, 15),
        location: '全厂',
        participantCount: 250,
        duration: 60,
        organizer: '安全部',
        director: '李明',
        achievements: '全员迅速撤离，用时3分钟',
        issues: '部分人员对集合点不熟悉',
        status: '已完成'
      },
      {
        id: 2,
        drillName: '2024年冬季应急演练',
        drillDate: new Date(2024, 0, 10),
        location: '生产车间',
        participantCount: 100,
        duration: 45,
        organizer: '安全部',
        director: '王刚',
        achievements: '成功模拟火灾处置流程',
        issues: '无',
        status: '已完成'
      }
    ]

    // 环保安全 - 废水
    wasteWaters.value = [
      {
        id: 1,
        monitoringPoint: '排水口 A',
        location: '厂区东侧',
        monitoringDate: new Date(2024, 0, 20),
        pH: 7.2,
        BOD: 15,
        COD: 45,
        suspendedSolids: 20,
        status: '达标',
        inspector: '张华'
      },
      {
        id: 2,
        monitoringPoint: '排水口 B',
        location: '厂区西侧',
        monitoringDate: new Date(2024, 0, 18),
        pH: 7.5,
        BOD: 12,
        COD: 40,
        suspendedSolids: 18,
        status: '达标',
        inspector: '刘英'
      }
    ]

    // 环保安全 - 废气
    wasteGases.value = [
      {
        id: 1,
        monitoringPoint: '车间排放口1',
        location: '生产车间A',
        monitoringDate: new Date(2024, 0, 20),
        dustConcentration: 25,
        SO2: 15,
        NOx: 35,
        pollutants: '颗粒物、硫化物',
        status: '达标',
        inspector: '陈军'
      },
      {
        id: 2,
        monitoringPoint: '车间排放口2',
        location: '生产车间B',
        monitoringDate: new Date(2024, 0, 18),
        dustConcentration: 20,
        SO2: 10,
        NOx: 30,
        pollutants: '颗粒物',
        status: '达标',
        inspector: '王刚'
      }
    ]

    // 环保安全 - 危险废物
    hazardousWastes.value = [
      {
        id: 1,
        wasteName: '废油',
        wasteType: '危险废物',
        quantity: 50,
        unit: '升',
        storageLocation: '危废库1',
        storageDate: new Date(2024, 0, 15),
        expiryDate: new Date(2024, 3, 15),
        hazardCode: 'HW08',
        disposalMethod: '委外处理',
        status: '储存中'
      },
      {
        id: 2,
        wasteName: '废活性炭',
        wasteType: '危险废物',
        quantity: 100,
        unit: '千克',
        storageLocation: '危废库2',
        storageDate: new Date(2024, 0, 10),
        expiryDate: new Date(2024, 3, 10),
        hazardCode: 'HW49',
        disposalMethod: '委外处理',
        status: '储存中'
      }
    ]

    // 环保安全 - 危险化学品
    hazardousChemicals.value = [
      {
        id: 1,
        chemicalName: '硫酸',
        chemicalType: '强腐蚀性液体',
        quantity: 200,
        unit: '升',
        storageLocation: '化学品库1',
        storageDate: new Date(2024, 0, 1),
        expiryDate: new Date(2024, 11, 31),
        hazardSymbol: '腐蚀',
        supplier: '化工公司A',
        status: '在库'
      },
      {
        id: 2,
        chemicalName: '氢氧化钠',
        chemicalType: '强碱性固体',
        quantity: 150,
        unit: '千克',
        storageLocation: '化学品库2',
        storageDate: new Date(2024, 0, 5),
        expiryDate: new Date(2024, 11, 31),
        hazardSymbol: '腐蚀',
        supplier: '化工公司B',
        status: '在库'
      }
    ]

    // 园区安全 - 门禁
    accessControls.value = [
      {
        id: 1,
        deviceName: '主门门禁',
        location: '厂区大门',
        deviceType: '人脸识别',
        installDate: new Date(2023, 0, 15),
        manufacturer: '安全科技有限公司',
        status: '正常',
        recordCount: 1250,
        lastMaintenance: new Date(2024, 0, 10)
      },
      {
        id: 2,
        deviceName: '生产区门禁',
        location: '生产车间入口',
        deviceType: '刷卡识别',
        installDate: new Date(2022, 6, 20),
        manufacturer: '安全科技有限公司',
        status: '正常',
        recordCount: 3500,
        lastMaintenance: new Date(2024, 0, 5)
      }
    ]

    // 园区安全 - 监控
    surveillances.value = [
      {
        id: 1,
        cameraName: '一号摄像头',
        location: '厂区大门',
        cameraType: '高清网络摄像机',
        resolution: '2560*1920',
        installDate: new Date(2023, 0, 15),
        status: '运行中',
        recordingDays: 30,
        lastMaintenance: new Date(2024, 0, 10)
      },
      {
        id: 2,
        cameraName: '二号摄像头',
        location: '生产车间',
        cameraType: '红外网络摄像机',
        resolution: '1920*1080',
        installDate: new Date(2023, 6, 20),
        status: '运行中',
        recordingDays: 30,
        lastMaintenance: new Date(2024, 0, 8)
      }
    ]
  }

  // 通用CRUD操作函数工厂
  const createCRUD = (storeRef) => {
    return {
      getAll: () => storeRef.value,
      getById: (id) => storeRef.value.find(item => item.id === id),
      add: (item) => {
        item.id = Math.max(...storeRef.value.map(i => i.id), 0) + 1
        storeRef.value.push(item)
        return item
      },
      update: (id, updated) => {
        const index = storeRef.value.findIndex(item => item.id === id)
        if (index !== -1) {
          storeRef.value[index] = { ...storeRef.value[index], ...updated }
          return storeRef.value[index]
        }
        return null
      },
      delete: (id) => {
        const index = storeRef.value.findIndex(item => item.id === id)
        if (index !== -1) {
          storeRef.value.splice(index, 1)
          return true
        }
        return false
      }
    }
  }

  return {
    // 生产安全
    operationSafety,
    operationSafetyCRUD: createCRUD(operationSafety),
    specialOperations,
    specialOperationsCRUD: createCRUD(specialOperations),
    workInjuries,
    workInjuriesCRUD: createCRUD(workInjuries),

    // 消防安全
    fireControls,
    fireControlsCRUD: createCRUD(fireControls),
    hotWorkPermits,
    hotWorkPermitsCRUD: createCRUD(hotWorkPermits),
    fireDrills,
    fireDrillsCRUD: createCRUD(fireDrills),

    // 环保安全
    wasteWaters,
    wasteWatersCRUD: createCRUD(wasteWaters),
    wasteGases,
    wasteGasesCRUD: createCRUD(wasteGases),
    hazardousWastes,
    hazardousWastesCRUD: createCRUD(hazardousWastes),
    hazardousChemicals,
    hazardousChemicalsCRUD: createCRUD(hazardousChemicals),

    // 园区安全
    accessControls,
    accessControlsCRUD: createCRUD(accessControls),
    surveillances,
    surveillancesCRUD: createCRUD(surveillances),

    initializeSampleData
  }
})
