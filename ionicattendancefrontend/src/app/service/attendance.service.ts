import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student, StudentFeatures, StudentRequestDTO,StudentResponseDTO } from '../model/student.model';
import {  HttpHeaders } from '@angular/common/http';
import { ImageUploadDTO } from '../model/student.model';
@Injectable({
  providedIn: 'root'
})
export class AttendanceService {
  
  private apiUrl:string = 'http://localhost:8080/student'
  constructor(private http:HttpClient) { }
  
  getStudentById(userId:number): Observable<StudentResponseDTO> {
    const headers = new HttpHeaders({'Content-Type': 'application/json',
      'Authorization':`Bearer ${localStorage.getItem("token")}`
    })
    
    return this.http.get<StudentResponseDTO>(this.apiUrl+"/"+userId,{headers, withCredentials:true});
  }
  getAllStudentsFeatures(): Observable<StudentFeatures[]> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });

    return this.http.get<StudentFeatures[]>(`${this.apiUrl}/features`, { headers, withCredentials: true });
  }

  addStudent(student:StudentRequestDTO):Observable<Student>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
    return this.http.post<Student>(this.apiUrl,student,{headers, withCredentials:true})
  }
  uploadImage(formData: FormData, userId: number): Observable<any> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}` // Apenas o token, sem Content-Type
    });
  
    return this.http.put<any>(`${this.apiUrl}/image/${userId}`, formData, {
      headers,
      withCredentials: true
    });
  }

  compareFaceWithStudent(formData:FormData):Observable<boolean>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}` // Apenas o token, sem Content-Type
    });
  
    return this.http.post<boolean>(`${this.apiUrl}/compare-face`, formData, {
      headers,
      withCredentials: true
    });

  }
  
}

