/** 扩展ElementUI的一些方法 */

import Vue from 'vue'

function msgSuccess(message) {
  this.$message({ showClose: true, type: 'success', message })
}

function msgError(message) {
  this.$message({ showClose: true, type: 'error', message })
}

function msgWarning(message) {
  this.$message({ showClose: true, type: 'warning', message })
}

function msgInfo(message) {
  this.$message({ showClose: true, type: 'info', message })
}

Vue.prototype.msgSuccess = msgSuccess
Vue.prototype.msgError = msgError
Vue.prototype.msgWarning = msgWarning
Vue.prototype.msgInfo = msgInfo
