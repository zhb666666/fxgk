<template>
  <div>
    <div class="card-header">
      <h2>操作安全管理</h2>
      <button @click="showAddForm = true" class="btn btn-primary">+ 新增操作规程</button>
    </div>

    <div v-if="showAddForm" class="modal-overlay" @click.self="showAddForm = false">
      <div class="modal">
        <div class="modal-header">
          <h3>{{ editingId ? '编辑' : '新增' }}操作规程</h3>
          <button class="close-btn" @click="showAddForm = false">×</button>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label>规程名称</label>
            <input v-model="formData.procedureName" type="text" placeholder="请输入规程名称">
          </div>
          <div class="form-group">
            <label>部门</label>
            <input v-model="formData.department" type="text" placeholder="请输入部门">
          </div>
          <div class="form-group">
            <label>操作类型</label>
            <input v-model="formData.operationType" type="text" placeholder="请输入操作类型">
          </div>
          <div class="form-group">
            <label>风险等级</label>
            <select v-model="formData.riskLevel">
              <option value="low">低</option>
              <option value="medium">中</option>
              <option value="high">高</option>
            </select>
          </div>
          <div class="form-group">
            <label>安全指南</label>
            <textarea v-model="formData.safetyGuidelines" placeholder="请输入安全指南"></textarea>
          </div>
          <div class="form-group">
            <label>
              <input type="checkbox" v-model="formData.trainingRequired">
              需要培训
            </label>
          </div>
          <div class="form-group">
            <label>审批人</label>
            <input v-model="formData.approver" type="text" placeholder="请输入审批人">
          </div>
          <div class="form-group">
            <label>状态</label>
            <select v-model="formData.status">
              <option value="有效">有效</option>
              <option value="无效">无效</option>
            </select>
          </div>
        </div>
        <div class="modal-footer">
          <button @click="showAddForm = false" class="btn">取消</button>
          <button @click="saveForm" class="btn btn-primary">保存</button>
        </div>
      </div>
    </div>

    <div class="card" style="margin-top: 20px">
      <table v-if="safetyStore.operationSafety.length > 0">
        <thead>
          <tr>
            <th>规程名称</th>
            <th>部门</th>
            <th>操作类型</th>
            <th>风险等级</th>
            <th>需要培训</th>
            <th>审批人</th>
            <th>状态</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in safetyStore.operationSafety" :key="item.id">
            <td>{{ item.procedureName }}</td>
            <td>{{ item.department }}</td>
            <td>{{ item.operationType }}</td>
            <td>
              <span :class="['status-badge', `status-${item.riskLevel}`]">
                {{ item.riskLevel }}
              </span>
            </td>
            <td>{{ item.trainingRequired ? '是' : '否' }}</td>
            <td>{{ item.approver }}</td>
            <td>{{ item.status }}</td>
            <td>
              <button @click="editItem(item)" class="btn-small btn-edit">编辑</button>
              <button @click="deleteItem(item.id)" class="btn-small btn-delete">删除</button>
            </td>
          </tr>
        </tbody>
      </table>
      <div v-else style="text-align: center; padding: 20px; color: #999">
        暂无数据
      </div>
    </div>
  </div>
</template>

<script setup>
import { useSafetyStore } from '../../stores/safetyStore'
import { ref, reactive } from 'vue'

const safetyStore = useSafetyStore()
const showAddForm = ref(false)
const editingId = ref(null)

const formData = reactive({
  procedureName: '',
  department: '',
  operationType: '',
  riskLevel: 'high',
  safetyGuidelines: '',
  trainingRequired: false,
  lastReviewed: new Date(),
  approver: '',
  status: '有效'
})

const resetForm = () => {
  formData.procedureName = ''
  formData.department = ''
  formData.operationType = ''
  formData.riskLevel = 'high'
  formData.safetyGuidelines = ''
  formData.trainingRequired = false
  formData.approver = ''
  formData.status = '有效'
  editingId.value = null
}

const editItem = (item) => {
  Object.assign(formData, item)
  editingId.value = item.id
  showAddForm.value = true
}

const saveForm = () => {
  if (editingId.value) {
    safetyStore.operationSafetyCRUD.update(editingId.value, { ...formData })
  } else {
    safetyStore.operationSafetyCRUD.add({ ...formData })
  }
  showAddForm.value = false
  resetForm()
}

const deleteItem = (id) => {
  if (confirm('确定要删除吗？')) {
    safetyStore.operationSafetyCRUD.delete(id)
  }
}
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.card-header h2 {
  margin: 0;
  font-size: 24px;
  color: var(--color-text-primary);
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.modal {
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.15);
  max-width: 600px;
  width: 90%;
  max-height: 90vh;
  overflow-y: auto;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  border-bottom: 1px solid #eee;
}

.modal-header h3 {
  margin: 0;
  font-size: 20px;
}

.close-btn {
  background: none;
  border: none;
  font-size: 28px;
  cursor: pointer;
  color: #999;
}

.modal-body {
  padding: 20px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  font-weight: 500;
  color: var(--color-text-primary);
}

.form-group input[type="text"],
.form-group input[type="date"],
.form-group select,
.form-group textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
  font-family: inherit;
}

.form-group textarea {
  resize: vertical;
  min-height: 100px;
}

.form-group input[type="checkbox"] {
  margin-right: 8px;
}

.modal-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  padding: 20px;
  border-top: 1px solid #eee;
  background: #f5f5f5;
}

.btn {
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.3s;
}

.btn-primary {
  background-color: #1976d2;
  color: white;
}

.btn-primary:hover {
  background-color: #1565c0;
}

.btn {
  background-color: #f0f0f0;
  color: #333;
}

.btn:hover {
  background-color: #e0e0e0;
}

.btn-small {
  padding: 6px 12px;
  font-size: 12px;
  margin-right: 5px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.btn-edit {
  background-color: #4caf50;
  color: white;
}

.btn-edit:hover {
  background-color: #45a049;
}

.btn-delete {
  background-color: #f44336;
  color: white;
}

.btn-delete:hover {
  background-color: #da190b;
}

table {
  width: 100%;
  border-collapse: collapse;
}

thead {
  background-color: #f5f5f5;
}

th, td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

th {
  font-weight: 600;
  color: var(--color-text-primary);
}

tr:hover {
  background-color: #f9f9f9;
}

.status-badge {
  display: inline-block;
  padding: 4px 12px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 500;
}

.status-high {
  background-color: #ffebee;
  color: #c62828;
}

.status-medium {
  background-color: #fff3e0;
  color: #e65100;
}

.status-low {
  background-color: #e8f5e9;
  color: #2e7d32;
}

.card {
  background: white;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>
