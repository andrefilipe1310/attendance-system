import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.seuapp.id',
  appName: 'Seu App',
  webDir: 'www',
  bundledWebRuntime: false,
  plugins: {
    Camera: {
      webUseInput: false, // Usa entrada nativa da câmera no navegador
    },
  },
};

export default config;