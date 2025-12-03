<template>
  <div>
    <div class="grid-2">
      <div class="card">
        <div class="card-title">风险级别分布</div>
        <div class="chart-container">
          <canvas ref="riskLevelChart"></canvas>
        </div>
      </div>

      <div class="card">
        <div class="card-title">事件类型分布</div>
        <div class="chart-container">
          <canvas ref="incidentTypeChart"></canvas>
        </div>
      </div>
    </div>

    <div class="grid-2" style="margin-top: 24px">
      <div class="card">
        <div class="card-title">资产健康度</div>
        <div class="chart-container">
          <canvas ref="assetHealthChart"></canvas>
        </div>
      </div>

      <div class="card">
        <div class="card-title">审计评分分布</div>
        <div class="chart-container">
          <canvas ref="auditScoreChart"></canvas>
        </div>
      </div>
    </div>

    <div class="card" style="margin-top: 24px">
      <div class="card-title">风险评估矩阵</div>
      <div class="matrix-container">
        <table class="risk-matrix">
          <thead>
            <tr>
              <th>风险评估</th>
              <th colspan="4">概率 (Probability)</th>
            </tr>
            <tr>
              <th>影响度</th>
              <th>很低 (0-0.25)</th>
              <th>低 (0.25-0.5)</th>
              <th>中 (0.5-0.75)</th>
              <th>高 (0.75-1.0)</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>很高 (0.75-1.0)</td>
              <td class="cell-medium">中风险</td>
              <td class="cell-high">高风险</td>
              <td class="cell-high">高风险</td>
              <td class="cell-high">高风险</td>
            </tr>
            <tr>
              <td>高 (0.5-0.75)</td>
              <td class="cell-low">低风险</td>
              <td class="cell-medium">中风险</td>
              <td class="cell-high">高风险</td>
              <td class="cell-high">高风险</td>
            </tr>
            <tr>
              <td>中 (0.25-0.5)</td>
              <td class="cell-low">低风险</td>
              <td class="cell-low">低风险</td>
              <td class="cell-medium">中风险</td>
              <td class="cell-high">高风险</td>
            </tr>
            <tr>
              <td>低 (0-0.25)</td>
              <td class="cell-low">低风险</td>
              <td class="cell-low">低风险</td>
              <td class="cell-low">低风险</td>
              <td class="cell-medium">中风险</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="card" style="margin-top: 24px">
      <div class="card-title">统计摘要</div>
      <div class="summary-grid">
        <div class="summary-item">
          <div class="summary-label">总风险数</div>
          <div class="summary-value">{{ riskStore.risks.length }}</div>
        </div>
        <div class="summary-item">
          <div class="summary-label">高风险数</div>
          <div class="summary-value">{{ riskStore.getHighRiskCount }}</div>
        </div>
        <div class="summary-item">
          <div class="summary-label">中风险数</div>
          <div class="summary-value">{{ riskStore.getMediumRiskCount }}</div>
        </div>
        <div class="summary-item">
          <div class="summary-label">低风险数</div>
          <div class="summary-value">{{ riskStore.getLowRiskCount }}</div>
        </div>
        <div class="summary-item">
          <div class="summary-label">总事件数</div>
          <div class="summary-value">{{ riskStore.incidents.length }}</div>
        </div>
        <div class="summary-item">
          <div class="summary-label">处理中事件</div>
          <div class="summary-value">{{ processingIncidents }}</div>
        </div>
        <div class="summary-item">
          <div class="summary-label">总资产数</div>
          <div class="summary-value">{{ riskStore.assets.length }}</div>
        </div>
        <div class="summary-item">
          <div class="summary-label">待维护资产</div>
          <div class="summary-value">{{ riskStore.getAssetsNeedingMaintenance.length }}</div>
        </div>
      </div>
    </div>

    <div class="card" style="margin-top: 24px">
      <div class="card-title">部门风险统计</div>
      <table v-if="departmentStats.length > 0">
        <thead>
          <tr>
            <th>部门</th>
            <th>高风险数</th>
            <th>中风险数</th>
            <th>低风险数</th>
            <th>总计</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="dept in departmentStats" :key="dept.name">
            <td>{{ dept.name }}</td>
            <td>{{ dept.high }}</td>
            <td>{{ dept.medium }}</td>
            <td>{{ dept.low }}</td>
            <td>{{ dept.total }}</td>
          </tr>
        </tbody>
      </table>
      <div v-else style="text-align: center; padding: 20px; color: #999">
        暂无部门风险数据
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, onMounted, ref } from 'vue'
import { useRiskStore } from '../stores/riskStore'

const riskStore = useRiskStore()
const riskLevelChart = ref(null)
const incidentTypeChart = ref(null)
const assetHealthChart = ref(null)
const auditScoreChart = ref(null)

const processingIncidents = computed(() => {
  return riskStore.incidents.filter(i => i.status === '处理中').length
})

const departmentStats = computed(() => {
  const stats = {}
  for (const risk of riskStore.risks) {
    if (!stats[risk.department]) {
      stats[risk.department] = { name: risk.department, high: 0, medium: 0, low: 0 }
    }
    if (risk.level === 'high') stats[risk.department].high++
    else if (risk.level === 'medium') stats[risk.department].medium++
    else stats[risk.department].low++
  }
  return Object.values(stats).map(s => ({
    ...s,
    total: s.high + s.medium + s.low
  }))
})

const drawRiskLevelChart = () => {
  const canvas = riskLevelChart.value
  if (!canvas) return

  const ctx = canvas.getContext('2d')
  const width = canvas.parentElement.offsetWidth
  const height = 200
  canvas.width = width
  canvas.height = height

  const centerX = width / 2
  const centerY = height / 2
  const radius = 50

  const high = riskStore.getHighRiskCount
  const medium = riskStore.getMediumRiskCount
  const low = riskStore.getLowRiskCount
  const total = high + medium + low

  const colors = ['#f5222d', '#faad14', '#52c41a']
  const values = [high, medium, low]
  const labels = ['高', '中', '低']

  let currentAngle = -Math.PI / 2
  for (let i = 0; i < 3; i++) {
    const sliceAngle = (values[i] / total) * 2 * Math.PI

    ctx.beginPath()
    ctx.arc(centerX, centerY, radius, currentAngle, currentAngle + sliceAngle)
    ctx.lineTo(centerX, centerY)
    ctx.fillStyle = colors[i]
    ctx.fill()

    const labelAngle = currentAngle + sliceAngle / 2
    const labelX = centerX + Math.cos(labelAngle) * 70
    const labelY = centerY + Math.sin(labelAngle) * 70

    ctx.fillStyle = '#262626'
    ctx.font = '12px Arial'
    ctx.textAlign = 'center'
    ctx.fillText(`${labels[i]}: ${values[i]}`, labelX, labelY)

    currentAngle += sliceAngle
  }
}

const drawIncidentTypeChart = () => {
  const canvas = incidentTypeChart.value
  if (!canvas) return

  const ctx = canvas.getContext('2d')
  const width = canvas.parentElement.offsetWidth
  const height = 200
  canvas.width = width
  canvas.height = height

  const types = {}
  for (const incident of riskStore.incidents) {
    types[incident.type] = (types[incident.type] || 0) + 1
  }

  const typeArray = Object.entries(types)
  if (typeArray.length === 0) return

  const barWidth = width / (typeArray.length + 1)
  const padding = 30
  const chartHeight = height - 2 * padding

  ctx.strokeStyle = '#d9d9d9'
  ctx.lineWidth = 1
  ctx.beginPath()
  ctx.moveTo(padding, height - padding)
  ctx.lineTo(width - padding, height - padding)
  ctx.stroke()

  const maxValue = Math.max(...Object.values(types))

  typeArray.forEach((item, index) => {
    const x = padding + (index + 1) * barWidth
    const barHeight = (item[1] / maxValue) * chartHeight
    const y = height - padding - barHeight

    ctx.fillStyle = '#1890ff'
    ctx.fillRect(x - barWidth / 2 + 10, y, barWidth - 20, barHeight)

    ctx.fillStyle = '#262626'
    ctx.font = '12px Arial'
    ctx.textAlign = 'center'
    ctx.fillText(item[0], x, height - 10)
    ctx.fillText(item[1], x, y - 5)
  })
}

const drawAssetHealthChart = () => {
  const canvas = assetHealthChart.value
  if (!canvas) return

  const ctx = canvas.getContext('2d')
  const width = canvas.parentElement.offsetWidth
  const height = 200
  canvas.width = width
  canvas.height = height

  const statuses = {}
  for (const asset of riskStore.assets) {
    statuses[asset.status] = (statuses[asset.status] || 0) + 1
  }

  const statusArray = Object.entries(statuses)
  const colors = { '正常': '#52c41a', '维修中': '#faad14', '报废': '#f5222d' }
  const padding = 30

  ctx.strokeStyle = '#d9d9d9'
  ctx.lineWidth = 1
  ctx.beginPath()
  ctx.moveTo(padding, height - padding)
  ctx.lineTo(width - padding, height - padding)
  ctx.moveTo(padding, padding)
  ctx.lineTo(padding, height - padding)
  ctx.stroke()

  const barWidth = (width - 2 * padding) / statusArray.length
  const maxValue = Math.max(...Object.values(statuses))
  const chartHeight = height - 2 * padding

  statusArray.forEach((item, index) => {
    const x = padding + index * barWidth + barWidth / 2
    const barHeight = (item[1] / maxValue) * chartHeight
    const y = height - padding - barHeight

    ctx.fillStyle = colors[item[0]] || '#1890ff'
    ctx.fillRect(x - barWidth / 3, y, (barWidth * 2) / 3, barHeight)

    ctx.fillStyle = '#262626'
    ctx.font = '12px Arial'
    ctx.textAlign = 'center'
    ctx.fillText(item[0], x, height - 10)
    ctx.fillText(item[1], x, y - 5)
  })
}

const drawAuditScoreChart = () => {
  const canvas = auditScoreChart.value
  if (!canvas) return

  const ctx = canvas.getContext('2d')
  const width = canvas.parentElement.offsetWidth
  const height = 200
  canvas.width = width
  canvas.height = height

  const scores = riskStore.audits.map(a => a.score)
  if (scores.length === 0) return

  const padding = 30
  const chartWidth = width - 2 * padding
  const chartHeight = height - 2 * padding

  ctx.strokeStyle = '#d9d9d9'
  ctx.lineWidth = 1
  ctx.beginPath()
  ctx.moveTo(padding, height - padding)
  ctx.lineTo(width - padding, height - padding)
  ctx.moveTo(padding, padding)
  ctx.lineTo(padding, height - padding)
  ctx.stroke()

  const stepX = chartWidth / (scores.length - 1 || 1)

  ctx.strokeStyle = '#1890ff'
  ctx.lineWidth = 2
  ctx.beginPath()

  scores.forEach((score, index) => {
    const x = padding + index * stepX
    const y = height - padding - (score / 100) * chartHeight
    if (index === 0) {
      ctx.moveTo(x, y)
    } else {
      ctx.lineTo(x, y)
    }
  })
  ctx.stroke()

  ctx.fillStyle = '#1890ff'
  scores.forEach((score, index) => {
    const x = padding + index * stepX
    const y = height - padding - (score / 100) * chartHeight
    ctx.beginPath()
    ctx.arc(x, y, 3, 0, 2 * Math.PI)
    ctx.fill()
  })
}

onMounted(() => {
  setTimeout(() => {
    drawRiskLevelChart()
    drawIncidentTypeChart()
    drawAssetHealthChart()
    drawAuditScoreChart()
  }, 0)

  window.addEventListener('resize', () => {
    drawRiskLevelChart()
    drawIncidentTypeChart()
    drawAssetHealthChart()
    drawAuditScoreChart()
  })
})
</script>

<style scoped>
.chart-container {
  min-height: 200px;
  display: flex;
  align-items: center;
  justify-content: center;
}

canvas {
  max-width: 100%;
}

.matrix-container {
  overflow-x: auto;
}

.risk-matrix {
  width: 100%;
  border-collapse: collapse;
  margin-top: 16px;
}

.risk-matrix th {
  background-color: #fafafa;
  border: 1px solid var(--border-color);
  padding: 12px;
  text-align: center;
  font-weight: 600;
}

.risk-matrix td {
  border: 1px solid var(--border-color);
  padding: 12px;
  text-align: center;
}

.risk-matrix tr:hover {
  background-color: transparent;
}

.cell-low {
  background-color: rgba(82, 196, 26, 0.1);
}

.cell-medium {
  background-color: rgba(250, 173, 20, 0.1);
}

.cell-high {
  background-color: rgba(245, 34, 45, 0.1);
}

.summary-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(120px, 1fr));
  gap: 16px;
  margin-top: 16px;
}

.summary-item {
  background-color: #fafafa;
  border-radius: 4px;
  padding: 16px;
  text-align: center;
}

.summary-label {
  font-size: 12px;
  color: #8c8c8c;
  margin-bottom: 8px;
}

.summary-value {
  font-size: 28px;
  font-weight: bold;
  color: var(--primary-color);
}
</style>
