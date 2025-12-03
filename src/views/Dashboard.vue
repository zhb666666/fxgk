<template>
  <div>
    <div class="grid-4">
      <div class="stat-card">
        <div class="stat-number high">{{ riskStore.getHighRiskCount }}</div>
        <div class="stat-label">高风险</div>
      </div>
      <div class="stat-card">
        <div class="stat-number medium">{{ riskStore.getMediumRiskCount }}</div>
        <div class="stat-label">中风险</div>
      </div>
      <div class="stat-card">
        <div class="stat-number low">{{ riskStore.getLowRiskCount }}</div>
        <div class="stat-label">低风险</div>
      </div>
      <div class="stat-card">
        <div class="stat-number">{{ riskStore.incidents.length }}</div>
        <div class="stat-label">事件总数</div>
      </div>
    </div>

    <div class="grid-2" style="margin-top: 24px">
      <div class="card">
        <div class="card-title">风险分布</div>
        <div class="chart-placeholder">
          <canvas ref="riskChart"></canvas>
        </div>
      </div>

      <div class="card">
        <div class="card-title">事件趋势</div>
        <div class="chart-placeholder">
          <canvas ref="trendChart"></canvas>
        </div>
      </div>
    </div>

    <div class="card" style="margin-top: 24px">
      <div class="card-title">近期事件</div>
      <table v-if="riskStore.getRecentIncidents.length > 0">
        <thead>
          <tr>
            <th>事件标题</th>
            <th>严重程度</th>
            <th>状态</th>
            <th>报告人</th>
            <th>报告时间</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="incident in riskStore.getRecentIncidents" :key="incident.id">
            <td>{{ incident.title }}</td>
            <td>
              <span :class="['status-badge', `status-${incident.severity}`]">
                {{ incident.severity }}
              </span>
            </td>
            <td>{{ incident.status }}</td>
            <td>{{ incident.reportedBy }}</td>
            <td>{{ formatDate(incident.reportedDate) }}</td>
          </tr>
        </tbody>
      </table>
      <div v-else style="text-align: center; padding: 20px; color: #999">
        暂无事件记录
      </div>
    </div>

    <div class="grid-2" style="margin-top: 24px">
      <div class="card">
        <div class="card-title">高风险项目</div>
        <div v-if="highRisks.length > 0">
          <div v-for="risk in highRisks" :key="risk.id" class="risk-item">
            <div class="risk-header">
              <h4>{{ risk.name }}</h4>
              <span class="status-badge status-high">{{ risk.level }}</span>
            </div>
            <p class="risk-desc">{{ risk.description }}</p>
            <p class="risk-owner">责任人: {{ risk.owner }}</p>
          </div>
        </div>
        <div v-else style="text-align: center; padding: 20px; color: #999">
          暂无高风险项目
        </div>
      </div>

      <div class="card">
        <div class="card-title">待维护资产</div>
        <div v-if="riskStore.getAssetsNeedingMaintenance.length > 0">
          <div v-for="asset in riskStore.getAssetsNeedingMaintenance" :key="asset.id" class="asset-item">
            <div class="asset-header">
              <h4>{{ asset.name }}</h4>
              <span class="status-badge status-warning">待维护</span>
            </div>
            <p class="asset-info">类型: {{ asset.type }}</p>
            <p class="asset-info">位置: {{ asset.location }}</p>
          </div>
        </div>
        <div v-else style="text-align: center; padding: 20px; color: #999">
          暂无待维护资产
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, onMounted, ref } from 'vue'
import { useRiskStore } from '../stores/riskStore'

const riskStore = useRiskStore()
const riskChart = ref(null)
const trendChart = ref(null)

const highRisks = computed(() => {
  return riskStore.risks.filter(r => r.level === 'high')
})

const formatDate = (date) => {
  const d = new Date(date)
  return d.toLocaleDateString('zh-CN')
}

const drawRiskChart = () => {
  const canvas = riskChart.value
  if (!canvas) return

  const ctx = canvas.getContext('2d')
  const width = canvas.parentElement.offsetWidth
  const height = 200
  canvas.width = width
  canvas.height = height

  const high = riskStore.getHighRiskCount
  const medium = riskStore.getMediumRiskCount
  const low = riskStore.getLowRiskCount
  const total = high + medium + low

  // Draw pie chart
  const centerX = width / 2
  const centerY = height / 2
  const radius = 60
  let currentAngle = -Math.PI / 2

  const colors = ['#f5222d', '#faad14', '#52c41a']
  const values = [high, medium, low]
  const labels = ['高风险', '中风险', '低风险']

  for (let i = 0; i < 3; i++) {
    const sliceAngle = (values[i] / total) * 2 * Math.PI
    
    // Draw slice
    ctx.beginPath()
    ctx.arc(centerX, centerY, radius, currentAngle, currentAngle + sliceAngle)
    ctx.lineTo(centerX, centerY)
    ctx.fillStyle = colors[i]
    ctx.fill()

    // Draw label
    const labelAngle = currentAngle + sliceAngle / 2
    const labelX = centerX + Math.cos(labelAngle) * (radius + 20)
    const labelY = centerY + Math.sin(labelAngle) * (radius + 20)
    
    ctx.fillStyle = '#262626'
    ctx.font = '12px Arial'
    ctx.textAlign = 'center'
    ctx.fillText(`${labels[i]}: ${values[i]}`, labelX, labelY)

    currentAngle += sliceAngle
  }
}

const drawTrendChart = () => {
  const canvas = trendChart.value
  if (!canvas) return

  const ctx = canvas.getContext('2d')
  const width = canvas.parentElement.offsetWidth
  const height = 200
  canvas.width = width
  canvas.height = height

  // Draw sample trend line
  const padding = 40
  const graphWidth = width - 2 * padding
  const graphHeight = height - 2 * padding

  // Draw axes
  ctx.strokeStyle = '#d9d9d9'
  ctx.lineWidth = 1
  ctx.beginPath()
  ctx.moveTo(padding, height - padding)
  ctx.lineTo(width - padding, height - padding)
  ctx.moveTo(padding, padding)
  ctx.lineTo(padding, height - padding)
  ctx.stroke()

  // Draw line
  const incidents = riskStore.incidents
  const points = [
    { x: padding + graphWidth * 0.2, y: height - padding - 30 },
    { x: padding + graphWidth * 0.4, y: height - padding - 50 },
    { x: padding + graphWidth * 0.6, y: height - padding - 40 },
    { x: padding + graphWidth * 0.8, y: height - padding - 60 },
    { x: padding + graphWidth, y: height - padding - 45 }
  ]

  ctx.strokeStyle = '#1890ff'
  ctx.lineWidth = 2
  ctx.beginPath()
  ctx.moveTo(points[0].x, points[0].y)
  for (let i = 1; i < points.length; i++) {
    ctx.lineTo(points[i].x, points[i].y)
  }
  ctx.stroke()

  // Draw points
  ctx.fillStyle = '#1890ff'
  for (const point of points) {
    ctx.beginPath()
    ctx.arc(point.x, point.y, 3, 0, 2 * Math.PI)
    ctx.fill()
  }
}

onMounted(() => {
  setTimeout(() => {
    drawRiskChart()
    drawTrendChart()
  }, 0)

  window.addEventListener('resize', () => {
    drawRiskChart()
    drawTrendChart()
  })
})
</script>

<style scoped>
.stat-card {
  background-color: white;
  border-radius: 4px;
  padding: 24px;
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.03);
  text-align: center;
}

.stat-number {
  font-size: 36px;
  font-weight: bold;
  margin-bottom: 8px;
}

.stat-number.high {
  color: #f5222d;
}

.stat-number.medium {
  color: #faad14;
}

.stat-number.low {
  color: #52c41a;
}

.stat-label {
  color: #8c8c8c;
  font-size: 14px;
}

.chart-placeholder {
  min-height: 200px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.risk-item {
  padding: 12px 0;
  border-bottom: 1px solid #f0f0f0;
}

.risk-item:last-child {
  border-bottom: none;
}

.risk-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 8px;
}

.risk-header h4 {
  margin: 0;
  font-size: 14px;
}

.risk-desc {
  margin: 8px 0;
  font-size: 12px;
  color: #8c8c8c;
}

.risk-owner {
  margin: 4px 0;
  font-size: 12px;
  color: #8c8c8c;
}

.asset-item {
  padding: 12px 0;
  border-bottom: 1px solid #f0f0f0;
}

.asset-item:last-child {
  border-bottom: none;
}

.asset-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 8px;
}

.asset-header h4 {
  margin: 0;
  font-size: 14px;
}

.asset-info {
  margin: 4px 0;
  font-size: 12px;
  color: #8c8c8c;
}

canvas {
  max-width: 100%;
}
</style>
