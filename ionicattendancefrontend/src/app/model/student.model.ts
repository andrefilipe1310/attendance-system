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

export type StudentRequestDTO = {           
    name: string;         
    email:string
    password:string
    phone:string
    studentImage?: string; 
    birth: string;         
    guardians:GuardianRequestDTO[];
}


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