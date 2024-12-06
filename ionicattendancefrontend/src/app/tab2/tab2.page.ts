import { Component, OnInit,ChangeDetectorRef } from '@angular/core';
import { AttendanceService } from '../service/attendance.service';
import { Student, StudentFeatures, StudentResponseDTO } from '../model/student.model';

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page implements OnInit {

  highlightedDates = [
    {
      date: '2023-01-05',
      textColor: '#800080',
      backgroundColor: '#ffc0cb',
    },
    {
      date: '2023-01-10',
      textColor: '#09721b',
      backgroundColor: '#c8e5d0',
    }
  ];
  students: StudentFeatures[] = []; // Lista de alunos para exibir no dropdown
  filteredStudents: StudentFeatures[] = []; // Lista de alunos filtrados
  student: StudentResponseDTO = {
    id: -1,
    name: "gfgfg",
    birth: null, // null conforme a definição do tipo
    guardians: [{ name: "", phone: "" }, { name: "", phone: "" }],
    email: null, // null conforme a definição do tipo
    password: null, // null conforme a definição do tipo
    phone: "",
    absences: [],
    totalAbsences: 0,
    totalMonthAbsences: 0,
    totalWeekAbsences: 0,
    monthFrequency: {}, // Um objeto vazio, já que o tipo é um mapa de datas com valores booleanos
    weeklyFrequency: {}, // Um objeto vazio, já que o tipo é um mapa de datas com valores booleanos
    todayDate: new Date().toISOString(), // Data atual no formato ISO
  };

  constructor(private attendanceService: AttendanceService, private cdRef: ChangeDetectorRef) {}

  ngOnInit() {
    this.loadStudents();
  }

  // Carrega a lista de alunos
  loadStudents() {
    this.attendanceService.getAllStudentsFeatures().subscribe(
      (response) => {
        this.students = response;
        this.filteredStudents = response; // Inicializa a lista de alunos filtrados
      },
      (error) => {
        console.error('Erro ao carregar os alunos:', error);
      }
    );
  }

  // Busca detalhes do aluno selecionado
  fetchStudentDetails(userId: number) {
    console.log('Buscando detalhes do usuário:', userId);
    this.attendanceService.getStudentById(userId).subscribe(
      (response) => {
        console.log('Resposta do serviço:', response);
        console.log('ID do aluno:', response.id);
        this.student = response;
        this.cdRef.detectChanges();
      },
      (error) => {
        console.error('Erro ao carregar os detalhes do aluno:', error);
      }
    );
  }
  // Filtra os alunos com base no que for digitado no campo de busca
  filterStudents(event: any) {
    const searchTerm = event.target.value.toLowerCase(); // Obtém o texto digitado
    if (searchTerm && searchTerm.trim() !== '') {
      this.filteredStudents = this.students.filter(student => 
        student.name.toLowerCase().includes(searchTerm) // Filtra pelo nome do aluno
      );
    } else {
      this.filteredStudents = this.students; // Se não houver texto, mostra todos os alunos
    }
  }
}
