<template>
  <q-dialog v-model="dialogDisplay" persistent ref="dialogRef">
    <q-card class="card-container">
      <q-card-section>
        <div class="text-h6 q-mb-xs text-primary">{{title}}</div>
        <slot></slot>
        <q-card-actions align="right">
          <q-btn icon="save" label="保存" color="primary" size="md" @click="save"/>
          <q-btn icon="cancel" label="关闭" color="primary" size="md" @click="cancel"/>
        </q-card-actions>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script lang="ts">
import {QBtn, QCard, QCardActions, QCardSection, QDialog, useDialogPluginComponent} from "quasar";
import {defineComponent, ref, watch} from "vue";

export default defineComponent({
  name: "GDialog",
  components: {
    QBtn,
    QCard,
    QCardActions,
    QCardSection,
    QDialog,
    useDialogPluginComponent
  },
  props: {
    showDialog: {
      type: Boolean,
      required: true
    },
    title: {
      type: String,
      required: true
    }
  },
  emits: ['onSave', 'onCancel'],
  setup(props, context) {

    const { dialogRef, onDialogHide, onDialogOK, onDialogCancel } = useDialogPluginComponent()

    // 单项数据流限制，子组件不能直接修改父组件传递的 props 值，需要复制一份使用
    const dialogDisplay = ref<boolean>(props.showDialog)

    watch(() => props.showDialog, (newVal, oldVal) => {
      dialogDisplay.value = newVal
    })

    function save() {
      context.emit('onSave')
    }

    function cancel() {
      context.emit('onCancel')
    }

    return {
      dialogRef,
      dialogDisplay,
      save,
      cancel,
    }
  }
})
</script>

<style scoped>
  .card-container {
    min-width: 500px;
  }
</style>