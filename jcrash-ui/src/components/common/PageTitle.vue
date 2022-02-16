<template>
  <div class="text-h6 q-pb-md q-pt-md">{{routeTitle}}</div>
</template>

<script>
import {defineComponent, onBeforeUnmount, onMounted} from "vue";
import {useRoute} from "vue-router";
import {useStore} from "../../store/store";

export default defineComponent({
  name: "PageTitle",
  setup() {
    const route = useRoute()
    const routeTitle = route.meta.label
    const pageTitle = routeTitle + ' - JCrash'
    const store = useStore()

    onMounted(() => {
      const currentPageTitle = document.title
      store.commit('saveCurrentPageTitle', currentPageTitle)
      document.title = pageTitle
    })

    onBeforeUnmount(() => {
      document.title = store.state.prevPageTitle
    })

    return {
      routeTitle
    }
  }
})
</script>

<style scoped>

</style>