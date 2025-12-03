<template>
  <div>
    <div style="display: flex; justify-content: space-between; margin-bottom: 16px">
      <div style="display: flex; gap: 8px">
        <input
          v-model="searchText"
          type="text"
          placeholder="搜索审计..."
          style="width: 200px"
        />
        <select v-model="filterType" style="width: 150px">
          <option value="">全部类型</option>
          <option value="定期检查">定期检查</option>
          <option value="专项检查">专项检查</option>
          <option value="其他">其他</option>
        </select>
      </div>
      <button class="btn" @click="showAddForm = true">+ 新增审计</button>
    </div>

    <div class="card">
      <table v-if="filteredAudits.length > 0">
        <thead>
          <tr>
            <th>审计标题</th>
            <th>审计类型</th>
            <th>审计员</th>
            <th>审计日期</th>
            <th>评分</th>
            <th>状态</th>
            <th>下次审计</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="audit in filteredAudits" :key="audit.id">
            <td>{{ audit.title }}</td>
            <td>{{ audit.type }}</td>
            <td>{{ audit.auditor }}</td>
            <td>{{ formatDate(audit.date) }}</td>
            <td>
              <span :class="['score-badge', getScoreBadgeClass(audit.score)]">
                {{ audit.score }}
              </span>
            </td>
            <td>{{ audit.status }}</td>
            <td>{{ formatDate(audit.nextAudit) }}</td>
            <td>
              <button class="btn btn-sm" @click="editAudit(audit)">编辑</button>
              <button class="btn btn-sm btn-danger" @click="deleteAuditItem(audit.id)">删除</button>
            </td>
          </tr>
        </tbody>
      </table>
      <div v-else style="text-align: center; padding: 40px; color: #999">
        暂无审计数据
      </div>
    </div>

    <div v-if="showAddForm || editingAudit" class="modal-overlay" @click="closeForm">
      <div class="modal" @click.stop>
        <div class="modal-header">
          <h2>{{ editingAudit ? '编辑审计' : '新增审计' }}</h2>
          <button class="modal-close" @click="closeForm">×</button>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label>审计标题 *</label>
            <input v-model="formData.title" type="text" placeholder="输入审计标题" />
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>审计类型</label>
              <select v-model="formData.type">
                <option value="定期检查">定期检查</option>
                <option value="专项检查">专项检查</option>
                <option value="其他">其他</option>
              </select>
            </div>
            <div class="form-group">
              <label>审计员</label>
              <input v-model="formData.auditor" type="text" placeholder="输入审计员" />
            </div>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>审计日期</label>
              <input v-model="formData.date" type="date" />
            </div>
            <div class="form-group">
              <label>评分 (0-100)</label>
              <input v-model.number="formData.score" type="number" min="0" max="100" />
            </div>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>状态</label>
              <select v-model="formData.status">
                <option value="进行中">进行中</option>
                <option value="已完成">已完成</option>
                <option value="已取消">已取消</option>
              </select>
            </div>
            <div class="form-group">
              <label>下次审计日期</label>
              <input v-model="formData.nextAudit" type="date" />
            </div>
          </div>

          <div class="form-group">
            <label>发现问题</label>
            <textarea
              v-model.number="formData.findings"
              placeholder="输入发现的问题（每行一个）"
            ></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn" @click="closeForm">取消</button>
          <button class="btn btn-success" @click="saveAudit">保存</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRiskStore } from '../stores/riskStore'

const riskStore = useRiskStore()
const searchText = ref('')
const filterType = ref('')
const showAddForm = ref(false)
const editingAudit = ref(null)

const formData = ref({
  title: '',
  type: '定期检查',
  auditor: '',
  date: '',
  score: 80,
  status: '进行中',
  nextAudit: '',
  findings: ''
})

const filteredAudits = computed(() => {
  return riskStore.audits.filter(audit => {
    const matchSearch = audit.title.includes(searchText.value) ||
                       audit.auditor.includes(searchText.value)
    const matchType = !filterType.value || audit.type === filterType.value
    return matchSearch && matchType
  })
})

const formatDate = (date) => {
  if (!date) return '-'
  const d = new Date(date)
  return d.toLocaleDateString('zh-CN')
}

const getScoreBadgeClass = (score) => {
  if (score >= 90) return 'score-excellent'
  if (score >= 80) return 'score-good'
  if (score >= 70) return 'score-average'
  return 'score-poor'
}

const closeForm = () => {
  showAddForm.value = false
  editingAudit.value = null
  resetForm()
}

const resetForm = () => {
  formData.value = {
    title: '',
    type: '定期检查',
    auditor: '',
    date: '',
    score: 80,
    status: '进行中',
    nextAudit: '',
    findings: ''
  }
}

const editAudit = (audit) => {
  editingAudit.value = audit.id
  formData.value = {
    title: audit.title,
    type: audit.type,
    auditor: audit.auditor,
    date: formatDateForInput(audit.date),
    score: audit.score,
    status: audit.status,
    nextAudit: formatDateForInput(audit.nextAudit),
    findings: audit.findings.join('\n')
  }
  showAddForm.value = true
}

const formatDateForInput = (date) => {
  if (!date) return ''
  const d = new Date(date)
  return d.toISOString().split('T')[0]
}

const saveAudit = () => {
  if (!formData.value.title) {
    alert('请输入审计标题')
    return
  }

  const auditData = {
    title: formData.value.title,
    type: formData.value.type,
    auditor: formData.value.auditor,
    date: formData.value.date ? new Date(formData.value.date) : null,
    score: formData.value.score,
    status: formData.value.status,
    nextAudit: formData.value.nextAudit ? new Date(formData.value.nextAudit) : null,
    findings: formData.value.findings.split('\n').filter(f => f.trim() !== '')
  }

  if (editingAudit.value) {
    riskStore.updateAudit(editingAudit.value, auditData)
  } else {
    riskStore.addAudit(auditData)
  }
  closeForm()
}

const deleteAuditItem = (id) => {
  if (confirm('确定删除该审计吗？')) {
    riskStore.deleteAudit(id)
  }
}
</script>

<style scoped>
textarea {
  min-height: 100px;
  resize: vertical;
}

select {
  width: 100%;
  padding: 8px;
  border: 1px solid #d9d9d9;
  border-radius: 4px;
}

.score-badge {
  display: inline-block;
  padding: 4px 12px;
  border-radius: 4px;
  font-size: 14px;
  font-weight: 600;
  color: white;
}

.score-excellent {
  background-color: var(--success-color);
}

.score-good {
  background-color: var(--primary-color);
}

.score-average {
  background-color: var(--warning-color);
}

.score-poor {
  background-color: var(--error-color);
}
</style>
