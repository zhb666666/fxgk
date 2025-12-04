<template>
  <div>
    <div class="card-header">
      <h2>危险废物管理</h2>
      <button @click="showAddForm = true" class="btn btn-primary">+ 新增危废</button>
    </div>

    <div v-if="showAddForm" class="modal-overlay" @click.self="showAddForm = false">
      <div class="modal">
        <div class="modal-header">
          <h3>{{ editingId ? '编辑' : '新增' }}危险废物</h3>
          <button class="close-btn" @click="showAddForm = false">×</button>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label>废物名称</label>
            <input v-model="formData.wasteName" type="text" placeholder="请输入废物名称">
          </div>
          <div class="form-group">
            <label>废物类型</label>
            <input v-model="formData.wasteType" type="text" placeholder="请输入废物类型">
          </div>
          <div class="form-group">
            <label>数量</label>
            <input v-model="formData.quantity" type="number" placeholder="请输入数量">
          </div>
          <div class="form-group">
            <label>单位</label>
            <input v-model="formData.unit" type="text" placeholder="请输入单位">
          </div>
          <div class="form-group">
            <label>存储位置</label>
            <input v-model="formData.storageLocation" type="text" placeholder="请输入存储位置">
          </div>
          <div class="form-group">
            <label>存储日期</label>
            <input v-model="formData.storageDate" type="date">
          </div>
          <div class="form-group">
            <label>过期日期</label>
            <input v-model="formData.expiryDate" type="date">
          </div>
          <div class="form-group">
            <label>危废代码</label>
            <input v-model="formData.hazardCode" type="text" placeholder="请输入危废代码">
          </div>
          <div class="form-group">
            <label>处置方式</label>
            <input v-model="formData.disposalMethod" type="text" placeholder="请输入处置方式">
          </div>
          <div class="form-group">
            <label>状态</label>
            <select v-model="formData.status">
              <option value="储存中">储存中</option>
              <option value="已处置">已处置</option>
              <option value="待处置">待处置</option>
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
      <table v-if="safetyStore.hazardousWastes.length > 0">
        <thead>
          <tr>
            <th>废物名称</th>
            <th>废物类型</th>
            <th>数量</th>
            <th>危废代码</th>
            <th>存储位置</th>
            <th>过期日期</th>
            <th>状态</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in safetyStore.hazardousWastes" :key="item.id">
            <td>{{ item.wasteName }}</td>
            <td>{{ item.wasteType }}</td>
            <td>{{ item.quantity }}{{ item.unit }}</td>
            <td>{{ item.hazardCode }}</td>
            <td>{{ item.storageLocation }}</td>
            <td>{{ formatDate(item.expiryDate) }}</td>
            <td>
              <span :class="['status-badge', `status-${item.status}`]">
                {{ item.status }}
              </span>
            </td>
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

const formatDate = (date) => {
  if (!date) return ''
  const d = new Date(date)
  return `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, '0')}-${String(d.getDate()).padStart(2, '0')}`
}

const formData = reactive({
  wasteName: '',
  wasteType: '',
  quantity: 0,
  unit: '',
  storageLocation: '',
  storageDate: '',
  expiryDate: '',
  hazardCode: '',
  disposalMethod: '',
  status: '储存中'
})

const resetForm = () => {
  formData.wasteName = ''
  formData.wasteType = ''
  formData.quantity = 0
  formData.unit = ''
  formData.storageLocation = ''
  formData.storageDate = ''
  formData.expiryDate = ''
  formData.hazardCode = ''
  formData.disposalMethod = ''
  formData.status = '储存中'
  editingId.value = null
}

const editItem = (item) => {
  Object.assign(formData, {
    ...item,
    storageDate: item.storageDate ? formatDate(item.storageDate) : '',
    expiryDate: item.expiryDate ? formatDate(item.expiryDate) : ''
  })
  editingId.value = item.id
  showAddForm.value = true
}

const saveForm = () => {
  const data = {
    ...formData,
    storageDate: formData.storageDate ? new Date(formData.storageDate) : new Date(),
    expiryDate: formData.expiryDate ? new Date(formData.expiryDate) : new Date(),
    quantity: parseFloat(formData.quantity) || 0
  }
  if (editingId.value) {
    safetyStore.hazardousWastesCRUD.update(editingId.value, data)
  } else {
    safetyStore.hazardousWastesCRUD.add(data)
  }
  showAddForm.value = false
  resetForm()
}

const deleteItem = (id) => {
  if (confirm('确定要删除吗？')) {
    safetyStore.hazardousWastesCRUD.delete(id)
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
.form-group input[type="number"],
.form-group select {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
  font-family: inherit;
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
  font-size: 14px;
}

thead {
  background-color: #f5f5f5;
}

th, td {
  padding: 10px;
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

.status-储存中 {
  background-color: #fff3e0;
  color: #e65100;
}

.status-已处置 {
  background-color: #e8f5e9;
  color: #2e7d32;
}

.status-待处置 {
  background-color: #ffebee;
  color: #c62828;
}

.card {
  background: white;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>
