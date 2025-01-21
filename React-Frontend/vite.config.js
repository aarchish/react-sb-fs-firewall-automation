import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import { resolve } from 'path'

// Define __dirname equivalent
const __dirname = resolve()

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
    // Add this section to handle client-side routing
    historyApiFallback: true
  },
  build: {
    outDir: 'dist', // default for Vite
    // Add this section to handle client-side routing
    rollupOptions: {
      output: {
        manualChunks: undefined,
      },
    },
  },
  resolve: {
    alias: {
      '@': resolve(__dirname, 'src'),
    },
  },
  // Add this section to handle client-side routing
  // esbuild: {
  //   jsxInject: `import React from 'react'`,
  // },
  optimizeDeps: {
    include: ['react', 'react-dom'],
  },
  
})