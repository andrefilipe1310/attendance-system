import { Component, OnInit } from '@angular/core';
import { Camera, CameraResultType, CameraSource } from '@capacitor/camera';
import { AttendanceService } from '../service/attendance.service';
@Component({
  selector: 'app-tab3',
  templateUrl: 'tab3.page.html',
  styleUrls: ['tab3.page.scss']
})
export class Tab3Page {
  photo: string | undefined;
  studentImage: Blob | undefined;

  studentValid:boolean = false

  constructor(private attedanceService: AttendanceService) {}

  async takePhoto() {
    try {
      // Captura a foto usando a câmera
      const image = await Camera.getPhoto({
        quality: 90,
        allowEditing: false,
        resultType: CameraResultType.DataUrl,
        source: CameraSource.Camera, // Garante que a câmera será aberta
      });
  
      // Salva a URL da imagem capturada
      this.photo = image.dataUrl;
  
      // Verifica se a foto foi capturada
      if (!this.photo) {
        console.warn('Nenhuma foto capturada.');
        return;
      }
  
      // Converte a foto para um Blob (para envio ou processamento)
      const byteString = atob(this.photo.split(',')[1]);
      const arrayBuffer = new ArrayBuffer(byteString.length);
      const uint8Array = new Uint8Array(arrayBuffer);
  
      for (let i = 0; i < byteString.length; i++) {
        uint8Array[i] = byteString.charCodeAt(i);
      }
  
      this.studentImage = new Blob([arrayBuffer], { type: 'image/jpeg' });
  
      // Inicia a comparação facial
      this.compareFaceWithStudent(this.studentImage);
    } catch (error) {
      // Log de erro para debugging
      console.error('Erro ao capturar foto:', error);
      if(!(error instanceof Error)){
       return
      }
      // Trata cancelamento do usuário (erro típico no navegador)
      if (error.message?.includes('User cancelled photos app')) {
        console.warn('O usuário cancelou a captura da foto.');
      }
    }
  }

  compareFaceWithStudent(file: Blob,) {
    this.studentValid = true
    const formData = new FormData();
    formData.append('file', file);
    this.attedanceService.compareFaceWithStudent(formData).subscribe({
      next: (response) => {
        this.studentValid = response
        console.log("valor do response da função compareFaceWithStudent",response);
      },
      error: (error: any) => {
        console.error(error);
      }
    });
  }

}
