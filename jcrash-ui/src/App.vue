<template>
  <Layout />
</template>

<script lang="ts">

import {useProvider} from "./context";
import {defineComponent} from "vue";
import Layout from "./components/layout/Layout.vue";

export default defineComponent({
  name: 'App',
  components: {
    Layout,
  },
  setup () {
    useProvider()
    return {

    }
  }
})
</script>