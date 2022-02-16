<template>
  <q-list>
    <MenuItem :routes="routes" />
  </q-list>
</template>

<script lang="ts">
import {QList} from "quasar";
import {useRouter} from "vue-router";
import MenuItem from "./MenuItem.vue";
import {defineComponent} from "vue";

export default defineComponent({
  name: "MenuList",
  components: {
    QList,
    MenuItem
  },
  setup() {
    const router = useRouter()
    let routes = router.getRoutes();
    // 过滤根路由
    routes = routes.filter(route => route.path.lastIndexOf("/") === 0)
    return {
      routes
    }
  }
})
</script>

<style scoped>

</style>