import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import lessonsPlugin from './vite-plugin-lessons.js';

export default defineConfig({
  plugins: [
    react(),
    lessonsPlugin(),
  ],
  server: {
    host: '127.0.0.1',
    port: 5180,
    strictPort: true,
  },
  preview: {
    host: '127.0.0.1',
    port: 5180,
  },
  build: {
    outDir: 'dist',
  },
});
