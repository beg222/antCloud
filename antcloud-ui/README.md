# antcloud-ui

蚂蚁项目管理前端工程，基于 Vue 2 + Element UI + webpack 4（`@vue/cli-service` 4.4.6）。

## 启动

```bash
npm install          # 安装依赖
npm run dev          # 开发模式，默认 http://localhost:8080
npm run build:prod   # 生产构建，产物在 dist/
```

开发环境通过 `.env.development` 的 `VUE_APP_BASE_API=/dev-api` 走本地代理，
代理目标在 `vue.config.js` 中配置，默认指向前端网关 `http://127.0.0.1:6880`。

> Node.js 17+ 需要 `--openssl-legacy-provider`，仓库里的 `npm run dev` 已带上该参数；
> 若在 PowerShell 下报 `NODE_OPTIONS` 不是内部命令，说明脚本用了 POSIX 写法，改用
> `cross-env` 或手动设置环境变量。

## 目录

```
src
├── api            接口定义，按业务域分目录
├── assets         静态资源、样式、图标
├── components     通用组件（含流程审批 Approval）
├── layout         布局：侧边栏、导航栏、标签页
├── plugins        流程设计器（bpmn-js 封装）
├── router         路由与权限守卫
├── store          Vuex：用户、权限、字典、设置
├── utils         请求封装、加解密、通用方法
└── views          页面，按业务域分目录
```

## 约定

- 接口统一走 `src/utils/request.js`，自动携带 token，401 时跳转登录页。
- 权限控制使用 `v-hasPermi` / `v-hasRole` 指令，权限标识与后端 `@RequiresPermissions` 保持一致。
- 字典数据用 `dict` 混入，页面不再硬编码枚举。
