<template>
  <div>
    <div style="display: flex; justify-content: space-between; margin-bottom: 16px">
      <div style="display: flex; gap: 8px">
        <input
          v-model="searchText"
          type="text"
          placeholder="搜索风险..."
          style="width: 200px"
        />
        <select v-model="filterLevel" style="width: 120px">
          <option value="">全部风险级别</option>
          <option value="high">高风险</option>
          <option value="medium">中风险</option>
          <option value="low">低风险</option>
        </select>
      </div>
      <button class="btn" @click="showAddForm = true">+ 新增风险</button>
    </div>

    <div class="card">
      <table v-if="filteredRisks.length > 0">
        <thead>
          <tr>
            <th>风险名称</th>
            <th>类别</th>
            <th>风险级别</th>
            <th>概率</th>
            <th>影响度</th>
            <th>状态</th>
            <th>责任人</th>
            <th>部门</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="risk in filteredRisks" :key="risk.id">
            <td>{{ risk.name }}</td>
            <td>{{ risk.category }}</td>
            <td>
              <span :class="['status-badge', `status-${risk.level}`]">
                {{ risk.level }}
              </span>
            </td>
            <td>{{ (risk.probability * 100).toFixed(0) }}%</td>
            <td>{{ (risk.impact * 100).toFixed(0) }}%</td>
            <td>{{ risk.status }}</td>
            <td>{{ risk.owner }}</td>
            <td>{{ risk.department }}</td>
            <td>
              <button class="btn btn-sm" @click="editRisk(risk)">编辑</button>
              <button class="btn btn-sm btn-danger" @click="deleteRiskItem(risk.id)">删除</button>
            </td>
          </tr>
        </tbody>
      </table>
      <div v-else style="text-align: center; padding: 40px; color: #999">
        暂无风险数据
      </div>
    </div>

    <div v-if="showAddForm || editingRisk" class="modal-overlay" @click="closeForm">
      <div class="modal" @click.stop>
        <div class="modal-header">
          <h2>{{ editingRisk ? '编辑风险' : '新增风险' }}</h2>
          <button class="modal-close" @click="closeForm">×</button>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label>风险名称 *</label>
            <input v-model="formData.name" type="text" placeholder="输入风险名称" />
          </div>

          <div class="form-group">
            <label>描述</label>
            <textarea v-model="formData.description" placeholder="输入风险描述"></textarea>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>类别</label>
              <input v-model="formData.category" type="text" placeholder="输入类别" />
            </div>
            <div class="form-group">
              <label>风险级别 *</label>
              <select v-model="formData.level">
                <option value="high">高</option>
                <option value="medium">中</option>
                <option value="low">低</option>
              </select>
            </div>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>概率 (0-1)</label>
              <input v-model.number="formData.probability" type="number" min="0" max="1" step="0.1" />
            </div>
            <div class="form-group">
              <label>影响度 (0-1)</label>
              <input v-model.number="formData.impact" type="number" min="0" max="1" step="0.1" />
            </div>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>状态</label>
              <select v-model="formData.status">
                <option value="活跃">活跃</option>
                <option value="受控">受控</option>
                <option value="关闭">关闭</option>
              </select>
            </div>
            <div class="form-group">
              <label>责任人</label>
              <input v-model="formData.owner" type="text" placeholder="输入责任人" />
            </div>
          </div>

          <div class="form-group">
            <label>部门</label>
            <input v-model="formData.department" type="text" placeholder="输入部门" />
          </div>

          <div class="form-group">
            <label>缓解措施</label>
            <textarea v-model="formData.mitigation" placeholder="输入缓解措施"></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn" @click="closeForm">取消</button>
          <button class="btn btn-success" @click="saveRisk">保存</button>
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
const filterLevel = ref('')
const showAddForm = ref(false)
const editingRisk = ref(null)

const formData = ref({
  name: '',
  description: '',
  category: '',
  level: 'high',
  probability: 0.5,
  impact: 0.5,
  status: '活跃',
  owner: '',
  department: '',
  mitigation: ''
})

const filteredRisks = computed(() => {
  return riskStore.risks.filter(risk => {
    const matchSearch = risk.name.includes(searchText.value) ||
                       risk.description.includes(searchText.value) ||
                       risk.owner.includes(searchText.value)
    const matchLevel = !filterLevel.value || risk.level === filterLevel.value
    return matchSearch && matchLevel
  })
})

const closeForm = () => {
  showAddForm.value = false
  editingRisk.value = null
  resetForm()
}

const resetForm = () => {
  formData.value = {
    name: '',
    description: '',
    category: '',
    level: 'high',
    probability: 0.5,
    impact: 0.5,
    status: '活跃',
    owner: '',
    department: '',
    mitigation: ''
  }
}

const editRisk = (risk) => {
  editingRisk.value = risk.id
  formData.value = { ...risk }
  showAddForm.value = true
}

const saveRisk = () => {
  if (!formData.value.name) {
    alert('请输入风险名称')
    return
  }

  if (editingRisk.value) {
    riskStore.updateRisk(editingRisk.value, formData.value)
  } else {
    riskStore.addRisk({
      ...formData.value,
      createdAt: new Date()
    })
  }
  closeForm()
}

const deleteRiskItem = (id) => {
  if (confirm('确定删除该风险吗？')) {
    riskStore.deleteRisk(id)
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
