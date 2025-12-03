<template>
  <div>
    <div style="display: flex; justify-content: space-between; margin-bottom: 16px">
      <div style="display: flex; gap: 8px">
        <input
          v-model="searchText"
          type="text"
          placeholder="搜索资产..."
          style="width: 200px"
        />
        <select v-model="filterType" style="width: 150px">
          <option value="">全部类型</option>
          <option value="机械设备">机械设备</option>
          <option value="电气设备">电气设备</option>
          <option value="安全设施">安全设施</option>
        </select>
      </div>
      <button class="btn" @click="showAddForm = true">+ 新增资产</button>
    </div>

    <div class="card">
      <table v-if="filteredAssets.length > 0">
        <thead>
          <tr>
            <th>资产名称</th>
            <th>类型</th>
            <th>型号</th>
            <th>位置</th>
            <th>状态</th>
            <th>风险级别</th>
            <th>最后维护</th>
            <th>下次维护</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="asset in filteredAssets" :key="asset.id">
            <td>{{ asset.name }}</td>
            <td>{{ asset.type }}</td>
            <td>{{ asset.model }}</td>
            <td>{{ asset.location }}</td>
            <td>{{ asset.status }}</td>
            <td>
              <span :class="['status-badge', `status-${asset.riskLevel}`]">
                {{ asset.riskLevel }}
              </span>
            </td>
            <td>{{ formatDate(asset.lastMaintenance) }}</td>
            <td>
              <span :class="{ 'highlight-warning': isMaintenanceOverdue(asset.nextMaintenance) }">
                {{ formatDate(asset.nextMaintenance) }}
              </span>
            </td>
            <td>
              <button class="btn btn-sm" @click="editAsset(asset)">编辑</button>
              <button class="btn btn-sm btn-danger" @click="deleteAssetItem(asset.id)">删除</button>
            </td>
          </tr>
        </tbody>
      </table>
      <div v-else style="text-align: center; padding: 40px; color: #999">
        暂无资产数据
      </div>
    </div>

    <div v-if="showAddForm || editingAsset" class="modal-overlay" @click="closeForm">
      <div class="modal" @click.stop>
        <div class="modal-header">
          <h2>{{ editingAsset ? '编辑资产' : '新增资产' }}</h2>
          <button class="modal-close" @click="closeForm">×</button>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label>资产名称 *</label>
            <input v-model="formData.name" type="text" placeholder="输入资产名称" />
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>资产类型</label>
              <select v-model="formData.type">
                <option value="机械设备">机械设备</option>
                <option value="电气设备">电气设备</option>
                <option value="安全设施">安全设施</option>
                <option value="其他">其他</option>
              </select>
            </div>
            <div class="form-group">
              <label>型号</label>
              <input v-model="formData.model" type="text" placeholder="输入型号" />
            </div>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>位置</label>
              <input v-model="formData.location" type="text" placeholder="输入位置" />
            </div>
            <div class="form-group">
              <label>生产厂商</label>
              <input v-model="formData.manufacturer" type="text" placeholder="输入生产厂商" />
            </div>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>购置日期</label>
              <input v-model="formData.purchaseDate" type="date" />
            </div>
            <div class="form-group">
              <label>状态</label>
              <select v-model="formData.status">
                <option value="正常">正常</option>
                <option value="维修中">维修中</option>
                <option value="报废">报废</option>
              </select>
            </div>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>最后维护日期</label>
              <input v-model="formData.lastMaintenance" type="date" />
            </div>
            <div class="form-group">
              <label>下次维护日期</label>
              <input v-model="formData.nextMaintenance" type="date" />
            </div>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>风险级别</label>
              <select v-model="formData.riskLevel">
                <option value="high">高</option>
                <option value="medium">中</option>
                <option value="low">低</option>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label>备注</label>
            <textarea v-model="formData.notes" placeholder="输入备注"></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn" @click="closeForm">取消</button>
          <button class="btn btn-success" @click="saveAsset">保存</button>
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
const editingAsset = ref(null)

const formData = ref({
  name: '',
  type: '机械设备',
  model: '',
  location: '',
  manufacturer: '',
  purchaseDate: '',
  lastMaintenance: '',
  nextMaintenance: '',
  status: '正常',
  riskLevel: 'medium',
  notes: ''
})

const filteredAssets = computed(() => {
  return riskStore.assets.filter(asset => {
    const matchSearch = asset.name.includes(searchText.value) ||
                       asset.location.includes(searchText.value) ||
                       asset.model.includes(searchText.value)
    const matchType = !filterType.value || asset.type === filterType.value
    return matchSearch && matchType
  })
})

const formatDate = (date) => {
  if (!date) return '-'
  const d = new Date(date)
  return d.toLocaleDateString('zh-CN')
}

const isMaintenanceOverdue = (date) => {
  if (!date) return false
  return new Date(date) <= new Date()
}

const closeForm = () => {
  showAddForm.value = false
  editingAsset.value = null
  resetForm()
}

const resetForm = () => {
  formData.value = {
    name: '',
    type: '机械设备',
    model: '',
    location: '',
    manufacturer: '',
    purchaseDate: '',
    lastMaintenance: '',
    nextMaintenance: '',
    status: '正常',
    riskLevel: 'medium',
    notes: ''
  }
}

const editAsset = (asset) => {
  editingAsset.value = asset.id
  formData.value = {
    ...asset,
    purchaseDate: formatDateForInput(asset.purchaseDate),
    lastMaintenance: formatDateForInput(asset.lastMaintenance),
    nextMaintenance: formatDateForInput(asset.nextMaintenance)
  }
  showAddForm.value = true
}

const formatDateForInput = (date) => {
  if (!date) return ''
  const d = new Date(date)
  return d.toISOString().split('T')[0]
}

const saveAsset = () => {
  if (!formData.value.name) {
    alert('请输入资产名称')
    return
  }

  const assetData = {
    ...formData.value,
    purchaseDate: formData.value.purchaseDate ? new Date(formData.value.purchaseDate) : null,
    lastMaintenance: formData.value.lastMaintenance ? new Date(formData.value.lastMaintenance) : null,
    nextMaintenance: formData.value.nextMaintenance ? new Date(formData.value.nextMaintenance) : null
  }

  if (editingAsset.value) {
    riskStore.updateAsset(editingAsset.value, assetData)
  } else {
    riskStore.addAsset(assetData)
  }
  closeForm()
}

const deleteAssetItem = (id) => {
  if (confirm('确定删除该资产吗？')) {
    riskStore.deleteAsset(id)
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

.highlight-warning {
  color: var(--error-color);
  font-weight: bold;
}
</style>
