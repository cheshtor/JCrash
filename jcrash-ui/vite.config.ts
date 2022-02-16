import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { quasar, transformAssetUrls } from '@quasar/vite-plugin'

export default defineConfig({
  plugins: [
    vue({
      template: { transformAssetUrls }
    }),

    quasar({
      autoImportComponentCase: 'pascal',
      sassVariables: 'src/quasar-variables.sass'
    })
  ],
  server: {
    port: 3000,
    open: false,
    base: './',
    proxy: {
      '^/api': {
        target: 'http://134.175.70.218:8989',
        changeOrigin: true,
        // rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
})
