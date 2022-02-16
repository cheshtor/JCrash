<template>
  <div v-for="(route, index) in routes" :key="index">
    <q-item v-if="!route.children || !route.children.length" clickable :to="route.path">
      <q-item-section :style="{paddingLeft: qItemSectionPaddingLeftValue}">
        {{route.meta?.label}}
      </q-item-section>
    </q-item>

    <q-expansion-item
        v-else
        label="{{
          // @ts-ignore
          route.meta?.label
        }}" :header-inset-level="titleIndent">
      <!-- 递归 -->
      <MenuItem :routes="route.children" :parentPath="route.path" :title-indent="titleIndent + 0.3" />
    </q-expansion-item>
  </div>
</template>

<script lang="ts">
import {QExpansionItem, QItem, QItemSection} from "quasar";
import {computed, defineComponent, PropType} from "vue";
import {RouteRecordNormalized, RouteRecordRaw} from "vue-router";

export default defineComponent({
  name: "MenuItem",
  components: {
    QItem,
    QItemSection,
    QExpansionItem,
  },
  props: {
    routes: {
      type: Array as PropType<RouteRecordRaw[]>,
      required: true
    }, // 路由
    parentPath: {
      type: String,
      default: ''
    }, // 上一层路由地址
    titleIndent: {
      type: Number,
      default: 0
    } // 文字缩进
  },
  setup(props, context) {
    let routes = props.routes
    // 过滤掉根路由
    routes = routes.filter(route => route.path !== '/')
    // 拼接上一层路由的地址
    routes = routes.map(route => {
      let prefix = props.parentPath ? props.parentPath + '/' : ''
      route.path = prefix + route.path
      return route
    })

    // 计算 QItemSection 内容的 padding-left 值。
    // 基准值是 QExpansionItem 的 header-inset-level 默认值。
    let qItemSectionPaddingLeftValue = computed(() => {
      if (props.titleIndent === 0) {
        return '0px'
      }
      // 21.6 是基准值，即 header-inset-level = 0.1 时的值
      // 之后每 0.1 增长 5.6px
      // 16 是 QItem 组件里的 QItemSection 自带的 padding 值
      return (props.titleIndent - 0.1) * 56 + 21.6 - 16 + 'px'
    })

    return {
      routes,
      qItemSectionPaddingLeftValue
    }
  }
})
</script>

<style scoped>

</style>