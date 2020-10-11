<template>
  <div class="app-container documentation-container">
    <a class="document-btn" target="_blank" href="https://gitee.com/eddievim/cy-code">Gitee项目仓库</a>
    <dropdown-menu class="document-btn" :items="articleList" title="项目相关文档" />
  </div>
</template>

<script>
import DropdownMenu from '@/components/Share/DropdownMenu'

export default {
  name: 'Documentation',
  components: { DropdownMenu },
  data() {
    return {
      articleList: [
        { title: '架构设计', href: '' },
        { title: '接口文档', href: '' },
        { title: '环境部署', href: '' },
        { title: '高并发选课核心设计', href: '' },
        { title: '高并发压力测试', href: '' }
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
.documentation-container {
  margin: 50px;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;

  .document-btn {
    flex-shrink: 0;
    display: block;
    cursor: pointer;
    background: black;
    color: white;
    height: 60px;
    padding: 0 16px;
    margin: 16px;
    line-height: 60px;
    font-size: 20px;
    text-align: center;
  }
}
</style>
