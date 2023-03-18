const role = 'authRole'
const token = 'Authorization'

export function setToken(value) {
  sessionStorage.setItem(token, value)
}

export function getToken() {
  return sessionStorage.getItem(token)
}

export function clearToken() {
  sessionStorage.clear(token)
}

export function setRole(val) {
  sessionStorage.setItem(role, val)
}

export function getRole() {
  return sessionStorage.getItem(role)
}
