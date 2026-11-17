## antcloud-ui 介绍

## antcloud-ui 启动说明

第一步，推荐使用 VSCode 打开 antcloud-ui 项目。

![](https://cdn.tobebetterjavaer.com/stutymore/README-20240329133716.png)

第二步，打开终端，执行 `npm install` 安装依赖：

项目依赖 Nodejs 环境，需要提前安装。此处略过。

![](https://cdn.tobebetterjavaer.com/stutymore/README-20240324122950.png)

不建议直接使用 cnpm 安装依赖，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题。

```bash
npm install --registry=https://registry.npmmirror.com
```

如果出现 `npm ERR! code EIDLETIMEOUT` 错误，一般是因为网络问题导致的，可以重新再执行一次 `npm install`。

第三步，安装完依赖后，执行 `npm run dev` 启动项目：

![](https://cdn.tobebetterjavaer.com/stutymore/README-20240324123905.png)

浏览器会自动打开 `http://localhost:1024`，即可看到 antcloud-ui 项目登录页面。

![](https://cdn.tobebetterjavaer.com/stutymore/README-20240324124027.png)

第四步，微信搜索「苍何」，关注我们的公众号，回复 `antcloud` 获取账号和密码，帮我们增加一个粉丝，哈哈哈，开源不易，请满足一下我的虚荣心（😁）。

![](https://cdn.tobebetterjavaer.com/stutymore/README-20240330204001.png)

第五步，输入账号密码登录，即可看到 antcloud-ui 项目主页。

## antcloud-ui 发布说明

```bash
# 构建测试环境
npm run build:stage

# 构建生产环境
npm run build:prod
````

## 模块划分

- 项目管理：antcloud-project

- 审批管理：antcloud-approval

## git commit规范

| 功能    | commit规范         | 示例                  | 描述                          |
|-------|------------------|---------------------|-----------------------------|
| 新功能   | feat/module_name | feat/multi_merchant | 开发一个新功能                     |
| bug修复 | bugfix/fix_name  | bugfix/user         | 修复某个功能模块的bug                |
| 紧急修复  | hotfix/fix_name  | hotfix/create_order | 紧急修复某个严重bug                 |
| 性能优化  | perf/name        | pref/user_login     | 优化某个功能的性能                   |
| 格式调整  | style/name       | style/log_print     | 做一下不影响任何业务的优化，比如删掉不使用了的注释之类 |
| 重构    | refactor/name    | refactor/user       | 重构某个功能模块                    |   
| 测试    | test/name        | test/user           | 测试相关, 不涉及业务代码的更改            |   
| 文档和注释 | docs/name        | docs/user           | 文档和注释相关                     |   
| 更新依赖等 | chore/name       | chore/user          | 更新依赖/修改脚手架配置等琐事             |   


## 教程

[前端手册](http://doc.ruoyi.vip/ruoyi-vue/document/qdsc.html#%E5%89%8D%E7%AB%AF%E6%89%8B%E5%86%8C)

## 跨域

该项目解决跨域问题通过反向代理，故部署时需对 Nginx 进行对应配置。

可以参考配置如下：

[img](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ee708e10a7204848803282dc2dfc7a4e~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.image)
[docs](http://doc.ruoyi.vip/ruoyi-vue/document/hjbs.html#nginx%E9%85%8D%E7%BD%AE)
