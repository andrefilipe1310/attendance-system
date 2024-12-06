export type Student = {
    id: number;            
    name: string;         
    email:string
    password:string
    phone:string
    studentImage?: string; 
    birth: string;         
    guardians:Guardian[];
    absences?: Absence[];  
}
export type StudentFeatures = {
    name:string
    userId:number
}
export type StudentRequestDTO = {           
    name: string;         
    email:string
    password:string
    phone:string
    studentImage?: string; 
    birth: string;         
    guardians:GuardianRequestDTO[];
}
export interface StudentResponseDTO {
    id: number;
    name: string;
    birth: string | null;
    guardians: { name: string; phone: string }[];
    email: string | null;
    password: string | null;
    phone: string;
    absences: any[];
    totalAbsences: number;
    totalMonthAbsences: number;
    totalWeekAbsences: number;
    monthFrequency: { [date: string]: boolean }; 
    weeklyFrequency: { [date: string]: boolean }; 
    todayDate: string;
  }

type Frequency = {
    [date: string]: boolean;
};

export type ImageUploadDTO = {
    file:any
}

export interface Guardian {
    id?: number;          
    name: string;        
    phone: string;        
}

export type GuardianRequestDTO = {          
    name: string;        
    phone: string;        
}

export interface Absence {
    id?: number;          
    date: string;         
    justification?: string; 
}