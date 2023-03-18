export function rememberMe(userInfo) {
  localStorage.setItem(JSON.stringify(userInfo))
}

export function getRemember() {
  localStorage.getItem(JSON.parse(userInfo))
}