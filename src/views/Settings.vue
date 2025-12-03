<template>
  <div class="settings-container">
    <div class="card">
      <div class="card-title">系统设置</div>

      <div class="settings-section">
        <h3>基本设置</h3>
        <div class="form-group">
          <label>系统名称</label>
          <input v-model="settings.systemName" type="text" />
        </div>

        <div class="form-group">
          <label>公司名称</label>
          <input v-model="settings.companyName" type="text" />
        </div>

        <div class="form-group">
          <label>企业类型</label>
          <select v-model="settings.industryType">
            <option value="">请选择</option>
            <option value="制造业">制造业</option>
            <option value="化工">化工</option>
            <option value="建筑">建筑</option>
            <option value="矿业">矿业</option>
            <option value="其他">其他</option>
          </select>
        </div>

        <div class="form-group">
          <label>主要联系人</label>
          <input v-model="settings.contactPerson" type="text" />
        </div>

        <div class="form-group">
          <label>联系电话</label>
          <input v-model="settings.contactPhone" type="tel" />
        </div>

        <div class="form-group">
          <label>联系邮箱</label>
          <input v-model="settings.contactEmail" type="email" />
        </div>
      </div>

      <div class="settings-section">
        <h3>风险配置</h3>
        <div class="form-group">
          <label>
            <input v-model="settings.requireApproval" type="checkbox" />
            风险变更需要审批
          </label>
        </div>

        <div class="form-group">
          <label>
            <input v-model="settings.autoEscalation" type="checkbox" />
            启用自动升级
          </label>
        </div>

        <div class="form-group">
          <label>高风险阈值 (0-1)</label>
          <input v-model.number="settings.highRiskThreshold" type="number" min="0" max="1" step="0.1" />
        </div>

        <div class="form-group">
          <label>中风险阈值 (0-1)</label>
          <input v-model.number="settings.mediumRiskThreshold" type="number" min="0" max="1" step="0.1" />
        </div>
      </div>

      <div class="settings-section">
        <h3>事件配置</h3>
        <div class="form-group">
          <label>
            <input v-model="settings.incidentNotification" type="checkbox" />
            启用事件通知
          </label>
        </div>

        <div class="form-group">
          <label>事件保留天数</label>
          <input v-model.number="settings.incidentRetentionDays" type="number" min="1" />
        </div>
      </div>

      <div class="settings-section">
        <h3>审计配置</h3>
        <div class="form-group">
          <label>定期审计周期 (天)</label>
          <input v-model.number="settings.auditCycle" type="number" min="1" />
        </div>

        <div class="form-group">
          <label>审计评分通过线 (0-100)</label>
          <input v-model.number="settings.auditPassScore" type="number" min="0" max="100" />
        </div>
      </div>

      <div class="settings-section">
        <h3>数据管理</h3>
        <div class="form-group">
          <label>自动备份间隔 (天)</label>
          <input v-model.number="settings.backupInterval" type="number" min="1" />
        </div>

        <div class="form-group">
          <button class="btn" @click="exportData">导出系统数据</button>
          <button class="btn" style="margin-left: 8px" @click="importData">导入系统数据</button>
          <input
            ref="fileInput"
            type="file"
            accept=".json"
            style="display: none"
            @change="handleImport"
          />
        </div>

        <div class="form-group">
          <button class="btn btn-danger" @click="resetAllData">重置所有数据</button>
        </div>
      </div>

      <div class="settings-section">
        <h3>关于系统</h3>
        <div class="info-item">
          <span>系统版本:</span>
          <strong>1.0.0</strong>
        </div>
        <div class="info-item">
          <span>发布日期:</span>
          <strong>2024年1月</strong>
        </div>
        <div class="info-item">
          <span>开发技术:</span>
          <strong>Vue 3 + Vite</strong>
        </div>
        <div class="info-item">
          <span>数据存储:</span>
          <strong>本地浏览器存储</strong>
        </div>
      </div>

      <div class="btn-group" style="margin-top: 24px">
        <button class="btn" @click="resetSettings">取消</button>
        <button class="btn btn-success" @click="saveSettings">保存设置</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRiskStore } from '../stores/riskStore'

const riskStore = useRiskStore()
const fileInput = ref(null)

const settings = ref({
  systemName: '工厂企业风险管控系统',
  companyName: '示例企业',
  industryType: '制造业',
  contactPerson: '管理员',
  contactPhone: '',
  contactEmail: '',
  requireApproval: false,
  autoEscalation: false,
  highRiskThreshold: 0.7,
  mediumRiskThreshold: 0.5,
  incidentNotification: true,
  incidentRetentionDays: 365,
  auditCycle: 30,
  auditPassScore: 80,
  backupInterval: 7
})

const loadSettings = () => {
  const saved = localStorage.getItem('risk_system_settings')
  if (saved) {
    settings.value = { ...settings.value, ...JSON.parse(saved) }
  }
}

const saveSettings = () => {
  localStorage.setItem('risk_system_settings', JSON.stringify(settings.value))
  alert('设置保存成功')
}

const resetSettings = () => {
  loadSettings()
}

const exportData = () => {
  const data = {
    settings: settings.value,
    risks: riskStore.risks,
    incidents: riskStore.incidents,
    assets: riskStore.assets,
    audits: riskStore.audits,
    exportTime: new Date().toISOString()
  }

  const json = JSON.stringify(data, null, 2)
  const blob = new Blob([json], { type: 'application/json' })
  const url = URL.createObjectURL(blob)
  const a = document.createElement('a')
  a.href = url
  a.download = `risk-system-export-${new Date().getTime()}.json`
  a.click()
  URL.revokeObjectURL(url)
}

const importData = () => {
  fileInput.value?.click()
}

const handleImport = (event) => {
  const file = event.target.files?.[0]
  if (!file) return

  const reader = new FileReader()
  reader.onload = (e) => {
    try {
      const data = JSON.parse(e.target?.result)
      if (data.settings) {
        settings.value = { ...settings.value, ...data.settings }
      }
      if (data.risks) {
        riskStore.risks = data.risks
      }
      if (data.incidents) {
        riskStore.incidents = data.incidents
      }
      if (data.assets) {
        riskStore.assets = data.assets
      }
      if (data.audits) {
        riskStore.audits = data.audits
      }
      alert('数据导入成功')
      saveSettings()
    } catch (error) {
      alert('数据导入失败: ' + error.message)
    }
  }
  reader.readAsText(file)

  event.target.value = ''
}

const resetAllData = () => {
  if (
    confirm(
      '确定要重置所有数据吗？此操作将清空所有风险、事件、资产和审计记录。此操作不可撤销。'
    )
  ) {
    riskStore.risks = []
    riskStore.incidents = []
    riskStore.assets = []
    riskStore.audits = []
    riskStore.initializeSampleData()
    alert('数据已重置')
  }
}

onMounted(() => {
  loadSettings()
})
</script>

<style scoped>
.settings-container {
  max-width: 800px;
}

.settings-section {
  margin-bottom: 32px;
  padding-bottom: 24px;
  border-bottom: 1px solid var(--border-color);
}

.settings-section:last-child {
  border-bottom: none;
}

.settings-section h3 {
  font-size: 16px;
  margin: 0 0 16px 0;
  color: var(--text-color);
}

.form-group {
  margin-bottom: 16px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  font-weight: 500;
}

.form-group input[type='checkbox'] {
  margin-right: 8px;
  cursor: pointer;
}

.form-group input[type='text'],
.form-group input[type='tel'],
.form-group input[type='email'],
.form-group input[type='number'],
.form-group select {
  width: 100%;
  padding: 8px 12px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  font-size: 14px;
}

.form-group input:focus,
.form-group select:focus {
  outline: none;
  border-color: var(--primary-color);
  box-shadow: 0 0 0 2px rgba(24, 144, 255, 0.2);
}

.info-item {
  display: flex;
  justify-content: space-between;
  padding: 12px 0;
  border-bottom: 1px solid var(--border-color);
}

.info-item:last-child {
  border-bottom: none;
}

.info-item span {
  color: #8c8c8c;
}

.info-item strong {
  color: var(--text-color);
}

.btn-group {
  display: flex;
  justify-content: flex-end;
  gap: 8px;
}
</style>
