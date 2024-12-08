package com.etepam.attendance_system.domain.service;

import com.etepam.attendance_system.domain.model.student.FaceEntity;
import com.etepam.attendance_system.domain.model.student.Student;
import com.etepam.attendance_system.repository.StudentRepository;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FaceDetectionService {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // Carregar a biblioteca nativa do OpenCV
    }

    @Autowired
    private StudentRepository studentRepository; // Repositório de estudantes

    private List<FaceEntity> faceEntities;
    private Mat image;

    // Método de comparação de imagens
    public boolean compareFaceWithStudents(MultipartFile file) throws Exception {
        // Buscar todos os estudantes no banco de dados
        List<Student> students = studentRepository.findAll();

        // Carregar a imagem recebida
        Mat receivedImage = Imgcodecs.imdecode(new MatOfByte(file.getBytes()), Imgcodecs.IMREAD_COLOR);

        // Se a imagem recebida não for carregada corretamente, retorna false
        if (receivedImage.empty()) {
            return false;
        }

        // Percorrer todos os estudantes e comparar as imagens
        for (Student student : students) {


            byte[] imageBytes = student.getStudentImage();
            System.out.println("Tamanho da imagem em bytes: " + imageBytes.length);
            Mat testImage = Imgcodecs.imdecode(new MatOfByte(file.getBytes()), Imgcodecs.IMREAD_COLOR);
            if (testImage.empty()) {
                System.out.println("Falha ao carregar a imagem recebida!");
            }else {
                System.out.println("Carregamento concluido");
            }
            // Carregar a imagem armazenada no aluno
            Mat studentImage = Imgcodecs.imdecode(new MatOfByte(student.getStudentImage()), Imgcodecs.IMREAD_COLOR);

            Imgcodecs.imwrite("received_image.jpg", receivedImage);
            Imgcodecs.imwrite("student_image.jpg", studentImage);

            System.out.println("received_image.jpg"+ receivedImage.size());
            System.out.println("student_image.jpg"+ studentImage.size());
            // Se a imagem armazenada do aluno não for carregada corretamente, ignora
            if (studentImage.empty()) {
                continue;
            }

            // Realiza a comparação de histograma (simples)
            double similarity = compareHistograms(receivedImage, studentImage);

            // Se a similaridade for maior que 0.9, considera como a mesma pessoa
            if (similarity > 0.9) {
                return true; // Encontrou uma correspondência
            }
        }

        // Se não encontrar nenhuma correspondência
        return false;
    }

    // Método para comparar histogramas de duas imagens
    private double compareHistograms(Mat image1, Mat image2) {
        // Redimensionar imagens para o mesmo tamanho (exemplo: 256x256)
        Size standardSize = new Size(256, 256);
        Imgproc.resize(image1, image1, standardSize);
        Imgproc.resize(image2, image2, standardSize);

        // Convertendo as imagens para escala de cinza
        Imgproc.cvtColor(image1, image1, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(image2, image2, Imgproc.COLOR_BGR2GRAY);

        // Calculando o histograma
        Mat hist1 = new Mat();
        Mat hist2 = new Mat();
        Imgproc.calcHist(List.of(image1), new MatOfInt(0), new Mat(), hist1, new MatOfInt(256), new MatOfFloat(0f, 256f));
        Imgproc.calcHist(List.of(image2), new MatOfInt(0), new Mat(), hist2, new MatOfInt(256), new MatOfFloat(0f, 256f));

        // Normalizando os histogramas
        Core.normalize(hist1, hist1, 0, 1, Core.NORM_MINMAX);
        Core.normalize(hist2, hist2, 0, 1, Core.NORM_MINMAX);

        // Comparando os histogramas
        return Imgproc.compareHist(hist1, hist2, Imgproc.CV_COMP_CORREL);
    }
}
