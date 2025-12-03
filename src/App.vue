<template>
  <router-view />
</template>

<script setup>
import { useRiskStore } from './stores/riskStore'
import { onMounted } from 'vue'

const riskStore = useRiskStore()

onMounted(() => {
  riskStore.initializeSampleData()
})
</script>

<style scoped>
</style>
