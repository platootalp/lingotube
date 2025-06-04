# lingotube

#### 介绍
lingotube是一个基于Spring Cloud微服务架构的视频学习平台，旨在为用户提供高质量的视频学习资源和词汇管理功能。该平台集成了用户管理、视频管理、词汇管理、搜索服务等多个模块，采用前后端分离的开发模式，前端使用Vue.js框架，后端使用Spring Boot和Spring Cloud技术栈。

#### 软件架构
- **前端**：使用Vue.js框架，基于BootstrapVue Argon Dashboard模板开发，提供用户友好的界面。
- **后端**：采用Spring Cloud微服务架构，包含以下模块：
  - **lingo-gateway**：网关模块，负责请求路由和负载均衡。
  - **lingo-user**：用户管理模块，处理用户注册、登录等功能。
  - **lingo-admin**：管理后台模块，提供系统管理功能。
  - **lingo-video**：视频管理模块，负责视频的上传、播放等功能。
  - **lingo-vocabulary**：词汇管理模块，提供词汇的学习和管理功能。
  - **lingo-search**：搜索服务模块，基于Elasticsearch实现全文搜索。
  - **lingo-oss**：对象存储模块，使用阿里云OSS进行文件存储。
  - **lingo-common**：通用模块，提供公共工具类和配置。
  - **lingo-mbg**：MyBatis生成器模块，用于生成数据库访问代码。

#### 安装教程
1. 克隆项目到本地：
   ```bash
   git clone https://github.com/yourusername/lingotube.git
   ```
2. 进入项目目录：
   ```bash
   cd lingotube
   ```
3. 安装后端依赖：
   ```bash
   mvn clean install
   ```
4. 安装前端依赖：
   ```bash
   cd lingo-ui
   npm install
   ```

#### 使用说明
1. 启动后端服务：
   ```bash
   mvn spring-boot:run
   ```
2. 启动前端服务：
   ```bash
   cd lingo-ui
   npm run serve
   ```
3. 访问前端页面：
   - 打开浏览器，访问 `http://localhost:8080`。

#### 参与贡献
1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

#### 特技
1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5. Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
