import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import tailwindcss from '@tailwindcss/vite';
import lessonsPlugin from './vite-plugin-lessons.js';

const isGHPages = process.env.GITHUB_ACTIONS === 'true';

export default defineConfig({
  base: isGHPages ? '/cybersec_unimi_ssri2.0/' : '/',
  plugins: [
    react(),
    tailwindcss(),
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
