<template>
  <q-layout view="hHh LpR fFf">

    <q-header reveal elevated class="bg-primary text-white">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />

        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg">
          </q-avatar>
          JCrash
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" side="left" bordered :width="200">
      <MenuList/>
    </q-drawer>

    <q-page-container>
      <div class="q-pa-md">
        <router-view></router-view>
      </div>
    </q-page-container>

  </q-layout>
</template>

<script lang="ts">
import MenuList from "./sidebar/MenuList.vue";
import {
  QAvatar,
  QBtn,
  QDrawer,
  QHeader,
  QItem,
  QItemSection,
  QLayout,
  QList,
  QPageContainer,
  QToolbar,
  QToolbarTitle
} from "quasar";
import {defineComponent, ref} from "vue";

export default defineComponent({
  name: "Layout",
  components: {
    MenuList,
    QLayout,
    QHeader,
    QToolbar,
    QToolbarTitle,
    QBtn,
    QAvatar,
    QDrawer,
    QPageContainer,
    QList,
    QItem,
    QItemSection,
  },
  setup () {
    const leftDrawerOpen = ref(true)

    return {
      leftDrawerOpen,
      toggleLeftDrawer () {
        leftDrawerOpen.value = !leftDrawerOpen.value
      }
    }
  }
})
</script>

<style scoped>

</style>