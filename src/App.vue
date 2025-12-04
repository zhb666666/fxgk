<template>
  <router-view />
</template>

<script setup>
import { useRiskStore } from './stores/riskStore'
import { useSafetyStore } from './stores/safetyStore'
import { onMounted } from 'vue'

const riskStore = useRiskStore()
const safetyStore = useSafetyStore()

onMounted(() => {
  riskStore.initializeSampleData()
  safetyStore.initializeSampleData()
})
</script>

<style scoped>
</style>
