import { UserConfigFn } from 'vite';
import { overrideVaadinConfig } from './vite.generated';
import path from 'path';
import react from '@vitejs/plugin-react';

const customConfig: UserConfigFn = (env) => ({
  plugins: [react()],
  optimizeDeps: {
    include: ['bpmn-js-properties-panel', 'bpmn-js'],
  },
});

export default overrideVaadinConfig(customConfig);
