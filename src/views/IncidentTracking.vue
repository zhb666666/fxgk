<template>
  <div>
    <div style="display: flex; justify-content: space-between; margin-bottom: 16px">
      <div style="display: flex; gap: 8px">
        <input
          v-model="searchText"
          type="text"
          placeholder="搜索事件..."
          style="width: 200px"
        />
        <select v-model="filterStatus" style="width: 120px">
          <option value="">全部状态</option>
          <option value="处理中">处理中</option>
          <option value="已解决">已解决</option>
          <option value="已关闭">已关闭</option>
        </select>
      </div>
      <button class="btn" @click="showAddForm = true">+ 新增事件</button>
    </div>

    <div class="card">
      <table v-if="filteredIncidents.length > 0">
        <thead>
          <tr>
            <th>事件标题</th>
            <th>事件类型</th>
            <th>严重程度</th>
            <th>状态</th>
            <th>报告人</th>
            <th>报告时间</th>
            <th>地点</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="incident in filteredIncidents" :key="incident.id">
            <td>{{ incident.title }}</td>
            <td>{{ incident.type }}</td>
            <td>
              <span :class="['status-badge', `status-${incident.severity}`]">
                {{ incident.severity }}
              </span>
            </td>
            <td>{{ incident.status }}</td>
            <td>{{ incident.reportedBy }}</td>
            <td>{{ formatDate(incident.reportedDate) }}</td>
            <td>{{ incident.location }}</td>
            <td>
              <button class="btn btn-sm" @click="editIncident(incident)">编辑</button>
              <button class="btn btn-sm btn-danger" @click="deleteIncidentItem(incident.id)">删除</button>
            </td>
          </tr>
        </tbody>
      </table>
      <div v-else style="text-align: center; padding: 40px; color: #999">
        暂无事件数据
      </div>
    </div>

    <div v-if="showAddForm || editingIncident" class="modal-overlay" @click="closeForm">
      <div class="modal" @click.stop>
        <div class="modal-header">
          <h2>{{ editingIncident ? '编辑事件' : '新增事件' }}</h2>
          <button class="modal-close" @click="closeForm">×</button>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label>事件标题 *</label>
            <input v-model="formData.title" type="text" placeholder="输入事件标题" />
          </div>

          <div class="form-group">
            <label>事件描述</label>
            <textarea v-model="formData.description" placeholder="输入事件描述"></textarea>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>事件类型</label>
              <select v-model="formData.type">
                <option value="工伤事故">工伤事故</option>
                <option value="设备事故">设备事故</option>
                <option value="环境事故">环境事故</option>
                <option value="其他">其他</option>
              </select>
            </div>
            <div class="form-group">
              <label>严重程度</label>
              <select v-model="formData.severity">
                <option value="high">高</option>
                <option value="medium">中</option>
                <option value="low">低</option>
              </select>
            </div>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>状态</label>
              <select v-model="formData.status">
                <option value="处理中">处理中</option>
                <option value="已解决">已解决</option>
                <option value="已关闭">已关闭</option>
              </select>
            </div>
            <div class="form-group">
              <label>报告人</label>
              <input v-model="formData.reportedBy" type="text" placeholder="输入报告人" />
            </div>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>地点</label>
              <input v-model="formData.location" type="text" placeholder="输入地点" />
            </div>
            <div class="form-group">
              <label>受影响人数</label>
              <input v-model.number="formData.affectedPersons" type="number" min="0" />
            </div>
          </div>

          <div class="form-group">
            <label>处理方案</label>
            <textarea v-model="formData.resolution" placeholder="输入处理方案"></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn" @click="closeForm">取消</button>
          <button class="btn btn-success" @click="saveIncident">保存</button>
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
const filterStatus = ref('')
const showAddForm = ref(false)
const editingIncident = ref(null)

const formData = ref({
  title: '',
  description: '',
  type: '工伤事故',
  severity: 'medium',
  status: '处理中',
  reportedBy: '',
  location: '',
  affectedPersons: 0,
  resolution: ''
})

const filteredIncidents = computed(() => {
  return riskStore.incidents.filter(incident => {
    const matchSearch = incident.title.includes(searchText.value) ||
                       incident.description.includes(searchText.value) ||
                       incident.reportedBy.includes(searchText.value)
    const matchStatus = !filterStatus.value || incident.status === filterStatus.value
    return matchSearch && matchStatus
  })
})

const formatDate = (date) => {
  const d = new Date(date)
  return d.toLocaleDateString('zh-CN')
}

const closeForm = () => {
  showAddForm.value = false
  editingIncident.value = null
  resetForm()
}

const resetForm = () => {
  formData.value = {
    title: '',
    description: '',
    type: '工伤事故',
    severity: 'medium',
    status: '处理中',
    reportedBy: '',
    location: '',
    affectedPersons: 0,
    resolution: ''
  }
}

const editIncident = (incident) => {
  editingIncident.value = incident.id
  formData.value = { ...incident }
  showAddForm.value = true
}

const saveIncident = () => {
  if (!formData.value.title) {
    alert('请输入事件标题')
    return
  }

  if (editingIncident.value) {
    riskStore.updateIncident(editingIncident.value, formData.value)
  } else {
    riskStore.addIncident({
      ...formData.value,
      reportedDate: new Date()
    })
  }
  closeForm()
}

const deleteIncidentItem = (id) => {
  if (confirm('确定删除该事件吗？')) {
    riskStore.deleteIncident(id)
  }
}
</script>

<style scoped>
textarea {
  min-height: 80px;
  resize: vertical;
}

select {
  width: 100%;
  padding: 8px;
  border: 1px solid #d9d9d9;
  border-radius: 4px;
}
</style>
